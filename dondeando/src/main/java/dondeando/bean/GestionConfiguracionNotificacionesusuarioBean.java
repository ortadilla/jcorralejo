package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.USUARIO_DE_CONFIGURACION_NOTIFICACIONES;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_CONFIGURACION_NOTIFICACIONES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_COFIGURACION_NOTIFICACIONES_USUARIO;
import static utilidades.varios.NombresBean.GESTION_CONFIGURACION_NOTIFICACIONES_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_INTERES;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
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
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioInteres;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_CONFIGURACION_NOTIFICACIONES_BEAN)
public class GestionConfiguracionNotificacionesusuarioBean {


	//Constantes
	private static final String ACCION_AGREGAR = "_agregar_";
	private static final String ACCION_ELIMINAR = "_eliminar_";
	
	//Atributos
	private List<Interes> listaInteres;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private String tituloPagina;
	
	private Usuario usuario;
	
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
	@In(value=SERVICIO_INTERES, create=true)
	private ServicioInteres servicioInteres;
	
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
				usuario = (Usuario)protocoloEdicion.getObjeto();
				tituloPagina = mensajesCore.obtenerTexto("CONFIGURACION_PARA", usuario.getLogin());
				buscar();
			}
		}

	}
	
	/**
	 * Busca los Foros que coincidan con los criterios indicados en pantalla
	 */
	private void buscar(){
		if(listaInteres==null)
			listaInteres = new ArrayList<Interes>();
		listaInteres.clear();
		if(usuario.getIntereses()!=null)
			listaInteres.addAll(usuario.getIntereses());
		HerramientasList.ordenar(listaInteres, Interes.ATRIBUTO_TIPO_INTERES+"."+TipoInteres.ATRIBUTO_DESCRIPCION);
		servicioInteres.rellenarPropiedadesNoMapeadas(listaInteres);
		if(listaInteres!=null && listaInteres.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar una nueva respuesta al tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_AGREGAR);
	}
	
	/**
	 * Elimina el mensaje seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR);
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaInteres != null ? listaInteres.size() : "0");
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
		|| ACCION_AGREGAR.equals(operacion)){
			
			if(ACCION_AGREGAR.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_COFIGURACION_NOTIFICACIONES_USUARIO, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				mapaArgumentos.setArgumento(USUARIO_DE_CONFIGURACION_NOTIFICACIONES, usuario);
		
				outcome = EDITAR_CONFIGURACION_NOTIFICACIONES;
				operacionRealizada = true;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Interes interes = listaInteres.get(seleccion);
				if(ACCION_ELIMINAR.equals(operacion)){
					if(interes!=null){
						//Comprobamos las respuestas justo antes de eliminar
						servicioInteres.eliminarOpinion(interes);
						listaInteres.remove(interes);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("INTERES_ELIMINADO"));
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

	public List<Interes> getListaInteres() {
		return listaInteres;
	}

	public void setListaInteres(List<Interes> listaInteres) {
		this.listaInteres = listaInteres;
	}

}
