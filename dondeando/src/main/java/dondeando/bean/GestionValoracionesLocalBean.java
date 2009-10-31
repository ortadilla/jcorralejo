package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.LOCAL_DE_NUEVA_PUNTUACION;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_VALORACION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_VALORACIONES_LOCAL;
import static utilidades.varios.NombresBean.GESTION_VALORACIONES_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_PUNTUACION;
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
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioPuntuacion;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_VALORACIONES_LOCAL_BEAN)
public class GestionValoracionesLocalBean {


	//Constantes
	private static final String ACCION_NUEVA_VALORACION = "_nuevaOpinion_";
	
	//Atributos
	private List<Puntuacion> listaPuntuaciones;
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
	@In(value=SERVICIO_PUNTUACION, create=true)
	private ServicioPuntuacion servicioPuntuacion;
	
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
				tituloPagina = mensajesCore.obtenerTexto("VALORACIONES_DE", local.getNombre());
				buscar();
			}
		}

	}
	
	/**
	 * Busca las Puntuaciones del local
	 */
	private void buscar(){
		if(listaPuntuaciones==null)
			listaPuntuaciones = new ArrayList<Puntuacion>();
		listaPuntuaciones.clear();
		if(local.getOpiniones()!=null)
			listaPuntuaciones.addAll(local.getPuntuaciones());
		HerramientasList.ordenar(listaPuntuaciones, Opinion.ATRIBUTO_FECHA);
		servicioPuntuacion.rellenarPropiedadesNoMapeadas(listaPuntuaciones);
		if(listaPuntuaciones!=null && listaPuntuaciones.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Navega a la pantalla de edición de mensajes para agregar una nueva respuesta al tema
	 * @return Regla de navegación de la pantalla de edición de mensajes
	 */
	public String agregar(){
		return realizarOperacion(ACCION_NUEVA_VALORACION);
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaPuntuaciones != null ? listaPuntuaciones.size() : "0");
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
		|| ACCION_NUEVA_VALORACION.equals(operacion)){
			
			if(ACCION_NUEVA_VALORACION.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_VALORACIONES_LOCAL, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
				mapaArgumentos.setArgumento(LOCAL_DE_NUEVA_PUNTUACION, local);
		
				outcome = EDITAR_VALORACION;
				operacionRealizada = true;
			}
			
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Puntuacion puntuacion = listaPuntuaciones.get(seleccion);
				
				if(servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_PUNTUACIONES_LOCAL)
				|| puntuacion.getUsuario().equals(servicioUsuario.devolverUsuarioActivo())){
					//Sólo se pueden añadir
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

	public List<Puntuacion> getListaPuntuaciones() {
		return listaPuntuaciones;
	}

	public void setListaPuntuaciones(List<Puntuacion> listaPuntuaciones) {
		this.listaPuntuaciones = listaPuntuaciones;
	}


}
