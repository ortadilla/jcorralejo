package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.LOCAL_DE_NUEVA_OPINION;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_OPINION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_OPINIONES_LOCAL;
import static utilidades.varios.NombresBean.GESTION_OPINION_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_OPINION;
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
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioOpinion;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_OPINION_LOCAL_BEAN)
public class GestionOpinionesLocal {

	//Constantes
	private static final String ACCION_NUEVA_OPINION = "_nuevaOpinion_";
	private static final String ACCION_ELIMINAR_OPINION = "_eliminarOpinion_";
	private static final String ACCION_EDITAR_OPINION = "_editarOpinion_";
	
	//Atributos
	private List<Opinion> listaOpiniones;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private String tituloPagina;
	
	private Local local;
	
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
	@In(value=SERVICIO_OPINION, create=true)
	private ServicioOpinion servicioOpinion;
	
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
				local = (Local)protocoloEdicion.getObjeto();
				tituloPagina = mensajesCore.obtenerTexto("OPINIONES_DE", local.getNombre());
				buscar();
			}
		}

	}
	
	/**
	 * Busca los Foros que coincidan con los criterios indicados en pantalla
	 */
	private void buscar(){
		if(listaOpiniones==null)
			listaOpiniones = new ArrayList<Opinion>();
		listaOpiniones.clear();
		if(local.getOpiniones()!=null)
			listaOpiniones.addAll(local.getOpiniones());
		HerramientasList.ordenar(listaOpiniones, Opinion.ATRIBUTO_FECHA);
		servicioOpinion.rellenarPropiedadesNoMapeadas(listaOpiniones);
		if(listaOpiniones!=null && listaOpiniones.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar una nueva respuesta al tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_NUEVA_OPINION);
	}
	
	/**
	 * Elimina el mensaje seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_OPINION);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para editar el mensaje seleccionado
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String editar(){
		return realizarOperacion(ACCION_EDITAR_OPINION);
	}
	
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaOpiniones != null ? listaOpiniones.size() : "0");
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
		|| ACCION_NUEVA_OPINION.equals(operacion)){
			
			if(ACCION_NUEVA_OPINION.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_OPINIONES_LOCAL, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				mapaArgumentos.setArgumento(LOCAL_DE_NUEVA_OPINION, local);
		
				outcome = EDITAR_OPINION;
				operacionRealizada = true;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Opinion opinion = listaOpiniones.get(seleccion);
				
				if(servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_OPINIONES_LOCAL)
				|| opinion.getUsuario().equals(servicioUsuario.devolverUsuarioActivo())){
					
					if(ACCION_ELIMINAR_OPINION.equals(operacion)){
						if(opinion!=null){
							//Comprobamos las respuestas justo antes de eliminar
							servicioOpinion.eliminarOpinion(opinion);
							listaOpiniones.remove(opinion);
							utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("OPINION_ELIMINADA"));
							operacionRealizada = true;
						}else
							utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_FORO_ELIMINADO"));
					}
					
					else if(ACCION_EDITAR_OPINION.equals(operacion)){
						if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
						mapaArgumentos.limpiaMapa();
						ProtocoloEdicion protocolo = new ProtocoloEdicion(opinion, GESTION_OPINIONES_LOCAL, null);
						mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				
						outcome = EDITAR_OPINION;
					}
				}else
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("NO_PERMISO_ACCION"));

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
		valorarOpinion(identificador, true);
	}
	
	/**
	 * Vota en contra del mensaje
	 * @param actionEvent
	 */
	public void accionListenerEnContra(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idMensaje");
		valorarOpinion(identificador, false);
	}
	
	/**
	 * Valora el mensaje con id indicado como positivo o negativo
	 * @param id Id el mensaje a valorar
	 * @param positivo Voto positivo o negativo
	 */
	private void valorarOpinion(Integer id, boolean positivo){
		if(id!=null){
			List<Opinion> opiniones = HerramientasList.obtenerElementos(listaOpiniones, Opinion.ATRIBUTO_ID, id);
			if(opiniones.size()==1){
				Usuario usuarioActivo = servicioUsuario.devolverUsuarioActivo();
				if(opiniones.get(0).getUsuariosValoraciones().contains(usuarioActivo))
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("YA_HA_VALORADO"));
				else if(opiniones.get(0).getUsuario().equals(usuarioActivo))
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("NO_VALORAR_PROPIO"));
				else{
					opiniones.get(0).setValoracionUsuarios(opiniones.get(0).getValoracionUsuarios() + (positivo ? 1 : -1));
					if(opiniones.get(0).getUsuariosValoraciones()==null)
						opiniones.get(0).setUsuariosValoraciones(new HashSet<Usuario>());
					opiniones.get(0).getUsuariosValoraciones().add(usuarioActivo);
					
					servicioUsuario.actualizarKarma(positivo ? ServicioUsuario.OPERACION_RECIBIR_VOTO_POSITIVO_OPINION 
															 : ServicioUsuario.OPERACION_RECIBIR_VOTO_NEGATIVO_OPINION, 
													null, 
													opiniones.get(0).getUsuario());
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

	public List<Opinion> getListaOpiniones() {
		return listaOpiniones;
	}

	public void setListaOpiniones(List<Opinion> listaOpiniones) {
		this.listaOpiniones = listaOpiniones;
	}

}
