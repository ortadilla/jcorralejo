package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.FORO_DE_NUEVO_MENSAJE;
import static utilidades.jsf.ConstantesArgumentosNavegacion.TEMA_DE_NUEVO_MENSAJE;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_MENSAJE_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_MENSAJES_TEMA;
import static utilidades.varios.NombresBean.GESTION_MENSAJES_TEMA_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.ActionEvent;

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
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioMensajeForo;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_MENSAJES_TEMA_BEAN)
public class GestionMensajesTemaBean {
	
	//Constantes
	private static final String ACCION_NUEVA_RESPUESTA = "_nuevaRespuesta_";
	private static final String ACCION_ELIMINAR_RESPUESTA = "_eliminarRespuesta_";
	private static final String ACCION_EDITAR_RESPUESTA = "_editarRespuesta_";
	//TODO: ¿Añadir la acción "mover a otro Foro"?
	
	//Atributos
	private List<MensajeForo> listaMensajesTema;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean mostrarEliminarRespuesta;
	private boolean mostrarEditarRespuesta;
	private boolean mostrarAgregar;
	private String tituloPagina;
	
	private MensajeForo tema;
	
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
		//Se podrán eliminar mensajes si se tiene permiso para gestionar los mensajes,
		//si se es moderador del foro, o si somos los creadores
		boolean mostrar =  servicioUsuario.isUsuarioActivoAdmin()
				       || listaMensajesTema!=null && !listaMensajesTema.isEmpty() 
				       && (listaMensajesTema.get(0).getForo().getModeradores().contains(servicioUsuario.devolverUsuarioActivo())
				       ||  listaMensajesTema.get(0).getAutor().equals(servicioUsuario.devolverUsuarioActivo()));
		mostrarEditarRespuesta = mostrar;
		mostrarEliminarRespuesta = mostrar;
		
		mostrarAgregar = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_MENSAJES_FOROS);
	}
	
	public void cargarArgumentosDeEntrada(){
		//Cargar los datos y lanzar la búsqueda
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			if(protocoloEdicion.getObjeto()!=null){
				tema = (MensajeForo)protocoloEdicion.getObjeto();
				tituloPagina = mensajesCore.obtenerTexto("RESPUESTAS_A", tema.getTitulo());
				buscar();
			}
		}

	}
	
	/**
	 * Busca los Foros que coincidan con los criterios indicados en pantalla
	 */
	private void buscar(){
		if(listaMensajesTema==null)
			listaMensajesTema = new ArrayList<MensajeForo>();
		listaMensajesTema.clear();
		if(tema.getRespuestas()!=null)
			listaMensajesTema.addAll(tema.getRespuestas());
		listaMensajesTema.add(tema);
		HerramientasList.ordenar(listaMensajesTema, MensajeForo.ATRIBUTO_FECHA);
		servicioMensajeForo.rellenarPropiedadesNoMapeadas(listaMensajesTema);
		if(listaMensajesTema!=null && listaMensajesTema.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar una nueva respuesta al tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_NUEVA_RESPUESTA);
	}
	
	/**
	 * Elimina el mensaje seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_RESPUESTA);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para editar el mensaje seleccionado
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String editar(){
		return realizarOperacion(ACCION_EDITAR_RESPUESTA);
	}
	
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaMensajesTema != null ? listaMensajesTema.size() : "0");
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
		|| ACCION_NUEVA_RESPUESTA.equals(operacion)){
			
			if(ACCION_NUEVA_RESPUESTA.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_MENSAJES_TEMA, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				mapaArgumentos.setArgumento(FORO_DE_NUEVO_MENSAJE, tema.getForo());
				mapaArgumentos.setArgumento(TEMA_DE_NUEVO_MENSAJE, tema);
		
				outcome = EDITAR_MENSAJE_FORO;
				operacionRealizada = true;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				MensajeForo respuesta = listaMensajesTema.get(seleccion);
				if(ACCION_ELIMINAR_RESPUESTA.equals(operacion)){
					if(respuesta!=null){
						//Comprobamos las respuestas justo antes de eliminar
						boolean esTema = respuesta.getRespondeA()==null;
						servicioMensajeForo.eliminarMensaje(respuesta);
						listaMensajesTema.remove(respuesta);
						//Si es el primer mensaje (es el tema) eliminamos todas las respuestas
						listaMensajesTema.removeAll(HerramientasList.obtenerElementos(listaMensajesTema, MensajeForo.ATRIBUTO_RESPONDE_A, respuesta));
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto(esTema?"TEMA_ELIMINADO":"RESPUESTA_ELIMINADA"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_FORO_ELIMINADO"));
				}
				
				else if(ACCION_EDITAR_RESPUESTA.equals(operacion)){
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(respuesta, GESTION_MENSAJES_TEMA, null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			
					outcome = EDITAR_MENSAJE_FORO;
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


	/**
	 * Vota a favor del mensaje
	 * @param actionEvent
	 */
	public void accionListenerAFavor(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idMensaje");
		valorarMensaje(identificador, true);
	}
	
	/**
	 * Vota en contra del mensaje
	 * @param actionEvent
	 */
	public void accionListenerEnContra(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idMensaje");
		valorarMensaje(identificador, false);
	}
	
	/**
	 * Valora el mensaje con id indicado como positivo o negativo
	 * @param id Id el mensaje a valorar
	 * @param positivo Voto positivo o negativo
	 */
	private void valorarMensaje(Integer id, boolean positivo){
		if(id!=null){
			List<MensajeForo> mensajesForo = HerramientasList.obtenerElementos(listaMensajesTema, MensajeForo.ATRIBUTO_ID, id);
			if(mensajesForo.size()==1){
				Usuario usuarioActivo = servicioUsuario.devolverUsuarioActivo();
				if(mensajesForo.get(0).getUsuariosValoraciones().contains(usuarioActivo))
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("YA_HA_VALORADO"));
				else if(mensajesForo.get(0).getAutor().equals(usuarioActivo))
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("NO_VALORAR_PROPIO"));
				else{
					mensajesForo.get(0).setValoracionUsuarios(mensajesForo.get(0).getValoracionUsuarios() + (positivo ? 1 : -1));
					if(mensajesForo.get(0).getUsuariosValoraciones()==null)
						mensajesForo.get(0).setUsuariosValoraciones(new HashSet<Usuario>());
					mensajesForo.get(0).getUsuariosValoraciones().add(usuarioActivo);
					
					servicioUsuario.actualizarKarma(positivo ? ServicioUsuario.OPERACION_RECIBIR_VOTO_POSITIVO_FORO 
															 : ServicioUsuario.OPERACION_RECIBIR_VOTO_NEGATIVO_FORO, 
													null, 
													mensajesForo.get(0).getAutor());
				}
			}
		}
	}

	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}
	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public List<MensajeForo> getListaMensajesTema() {
		return listaMensajesTema;
	}

	public void setListaMensajesTema(List<MensajeForo> listaMensajesTema) {
		this.listaMensajesTema = listaMensajesTema;
	}

	public boolean isMostrarEliminarRespuesta() {
		return mostrarEliminarRespuesta;
	}

	public void setMostrarEliminarRespuesta(boolean mostrarEliminarRespuesta) {
		this.mostrarEliminarRespuesta = mostrarEliminarRespuesta;
	}

	public boolean isMostrarEditarRespuesta() {
		return mostrarEditarRespuesta;
	}

	public void setMostrarEditarRespuesta(boolean mostrarEditarRespuesta) {
		this.mostrarEditarRespuesta = mostrarEditarRespuesta;
	}

	public boolean isMostrarAgregar() {
		return mostrarAgregar;
	}

	public void setMostrarAgregar(boolean mostrarAgregar) {
		this.mostrarAgregar = mostrarAgregar;
	}

}
