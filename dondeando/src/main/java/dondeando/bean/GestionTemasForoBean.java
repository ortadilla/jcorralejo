package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.FORO_DE_NUEVO_MENSAJE;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_MENSAJE_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_MENSAJES_TEMA;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_TEMAS_FORO;
import static utilidades.varios.NombresBean.GESTION_TEMAS_FORO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.List;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.UtilJsfContext;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.servicio.ServicioMensajeForo;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_TEMAS_FORO_BEAN)
public class GestionTemasForoBean {


	//Constantes
	private static final String ACCION_AGREGAR_TEMA = "_agregarTema_";
	private static final String ACCION_ELIMINAR_TEMA = "_eliminarTema_";
	private static final String ACCION_VER_MENSAJES = "_verMensaje_";
	//TODO: ¿Añadir la acción "mover a otro Foro"?
	
	//Atributos
	private List<MensajeForo> listaTemasForo;
	private Foro foro;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean mostrarEliminarTema;
	private boolean mostrarAgregar;
	private String tituloPagina;
	
	//Utilidades
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	@In(value=SERVICIO_MENSAJE_FORO, create=true)
	private ServicioMensajeForo servicioMensajeForo;
	
	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;

	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}
	
	public void cargarArgumentosDeEntrada(){
		
		//Cargar los datos y lanzar la búsqueda
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			if(protocoloEdicion.getObjeto()!=null){
				foro = (Foro)protocoloEdicion.getObjeto();
				tituloPagina = mensajesCore.obtenerTexto("FORO", foro.getTitulo());
				buscar();
			}
		}
		
		//Se podrán eliminar temas si se tiene permiso para gestionar los mensajes
		//o si se es moderador del foro
		mostrarEliminarTema = servicioUsuario.isUsuarioActivoAdmin()
						   || foro!=null &&  foro.getModeradores()!=null && foro.getModeradores().contains(servicioUsuario.devolverUsuarioActivo());
		mostrarAgregar = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_MENSAJES_FOROS);

	}
	
	/**
	 * Busca los Foros que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaTemasForo = servicioMensajeForo.encontrarTemasDeForo(foro);
		servicioMensajeForo.rellenarPropiedadesNoMapeadas(listaTemasForo);
		//Ordenamos para que aparezca primero el que tenga una respusta más nueva
		//por eso hay que hacerlo después de rellenar propiedades no mapeadas
		HerramientasList.ordenar(listaTemasForo, MensajeForo.ATRIBUTO_FECHA + " DESC");
		if(listaTemasForo!=null && listaTemasForo.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar uno nuevo tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_AGREGAR_TEMA);
	}
	
	/**
	 * Elimina el tema seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_TEMA);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar uno nuevo tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String verMensajes(){
		return realizarOperacion(ACCION_VER_MENSAJES);
	}
	
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaTemasForo != null ? listaTemasForo.size() : "0");
    }
	
	
	/**
	 * Realiza la operación concreta sobre el usuario seleccionado, que puede ser eliminar,
	 * ver detalles y modificar
	 * @param edicion Indica si se navega para editar el usuario, o para ver los detalles
	 * @return	Regla de navegación necesaria para realizar la acción
	 */
	private String realizarOperacion(String operacion){
		String outcome = "";
		boolean operacionRealizada = false;
		if(estadoDeSeleccionTabla.size()==1 
		|| ACCION_AGREGAR_TEMA.equals(operacion)){
			
			if(ACCION_AGREGAR_TEMA.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_TEMAS_FORO, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				mapaArgumentos.setArgumento(FORO_DE_NUEVO_MENSAJE, foro);
		
				outcome = EDITAR_MENSAJE_FORO;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				MensajeForo tema = listaTemasForo.get(seleccion);
				if(ACCION_ELIMINAR_TEMA.equals(operacion)){
					if(tema!=null){
						servicioMensajeForo.eliminarTema(tema);
						listaTemasForo.remove(tema);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("TEMA_ELIMINADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_FORO_ELIMINADO"));
				}
				
				else if(ACCION_VER_MENSAJES.equals(operacion)){
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(tema, GESTION_TEMAS_FORO, null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
					
					outcome = GESTION_MENSAJES_TEMA;
				}

			}
			
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		if(operacionRealizada)
			estadoDeSeleccionTabla.clear();
		
		return outcome;
	}

	/**
	 * Vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String volver(){
		return protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
	}



	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}
	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}

	public boolean isMostrarEliminarTema() {
		return mostrarEliminarTema;
	}

	public void setMostrarEliminarTema(boolean mostrarEliminarTema) {
		this.mostrarEliminarTema = mostrarEliminarTema;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public List<MensajeForo> getListaTemasForo() {
		return listaTemasForo;
	}

	public void setListaTemasForo(List<MensajeForo> listaTemasForo) {
		this.listaTemasForo = listaTemasForo;
	}

	public boolean isMostrarAgregar() {
		return mostrarAgregar;
	}

	public void setMostrarAgregar(boolean mostrarAgregar) {
		this.mostrarAgregar = mostrarAgregar;
	}

}
