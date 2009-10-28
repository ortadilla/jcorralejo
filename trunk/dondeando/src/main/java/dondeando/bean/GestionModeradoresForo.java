package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_MODERADORES_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.varios.NombresBean.GESTION_MODERADORES_FORO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_BUSQUEDA;
import static utilidades.varios.NombresBean.PROTOCOLO_RESULTADO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.ArrayList;
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
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloBusqueda;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioForo;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_MODERADORES_FORO_BEAN)
public class GestionModeradoresForo {

	//Constantes
	private static final String ACCION_ANIADIR_MODERADOR = "_aniadirModerador_";
	private static final String ACCION_ELIMINAR_MODERADOR = "_eliminarModerador_";
	
	//Atributos
	private List<Usuario> listaModeradores;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private String tituloPagina;
	private Foro foro;
	
	//Utilidades
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=NombresBean.SERVICIO_FORO, create=true)
	private ServicioForo servicioForo; 
	
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	ProtocoloEdicion protocoloEdicion;
	
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
				tituloPagina = mensajesCore.obtenerTexto("MODERADORES_FORO", foro.getTitulo());
				buscar();
			}
		}
		
		//Si traemos un protocolo resultado, es que hemos ido a buscar un nuevo moderador
		if(mapaArgumentos!=null && mapaArgumentos.contiene(PROTOCOLO_RESULTADO)){
			Usuario moderador = (Usuario)mapaArgumentos.getArgumento(PROTOCOLO_RESULTADO);
			if(foro!=null){
				if(!foro.getModeradores().contains(moderador)){
					servicioForo.aniadirModerador(foro, moderador);
					listaModeradores.add(moderador);
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("MODERADOR_AÑADIDO"));
				}else
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("YA_EXISTE_MODERADOR"));
			}
		}
	}
	
	/**
	 * Busca los moderadores del foro
	 */
	private void buscar(){
		if(listaModeradores==null)
			listaModeradores = new ArrayList<Usuario>();
		listaModeradores.clear();
		if(foro.getModeradores()!=null)
			listaModeradores.addAll(foro.getModeradores());
		HerramientasList.ordenar(listaModeradores, Usuario.ATRIBUTO_LOGIN);
		if(listaModeradores!=null && listaModeradores.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar una nueva respuesta al tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_ANIADIR_MODERADOR);
	}
	
	/**
	 * Elimina el mensaje seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_MODERADOR);
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaModeradores != null ? listaModeradores.size() : "0");
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
		|| ACCION_ANIADIR_MODERADOR.equals(operacion)){
			
			if(ACCION_ANIADIR_MODERADOR.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloBusqueda protocolo = new ProtocoloBusqueda(null, true, GESTION_MODERADORES_FORO);
				mapaArgumentos.setArgumento(PROTOCOLO_BUSQUEDA, protocolo);
		
				outcome = GESTION_USUARIOS;
				operacionRealizada = true;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Usuario moderador = listaModeradores.get(seleccion);
				if(ACCION_ELIMINAR_MODERADOR.equals(operacion)){
					if(moderador!=null){
						servicioForo.eliminarModerador(foro, moderador);
						listaModeradores.remove(moderador);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("MODERADOR_ELIMINADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_FORO_ELIMINADO"));
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

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public List<Usuario> getListaModeradores() {
		return listaModeradores;
	}

	public void setListaModeradores(List<Usuario> listaModeradores) {
		this.listaModeradores = listaModeradores;
	}

}
