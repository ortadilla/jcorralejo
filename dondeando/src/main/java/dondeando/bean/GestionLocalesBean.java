package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_OPINIONES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_VALORACIONES_LOCAL;
import static utilidades.varios.NombresBean.GESTION_LOCALES_BEAN;
import static utilidades.varios.NombresBean.GESTION_LOCALES_BINDING;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.List;

import javax.faces.model.SelectItem;

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
import utilidades.varios.EntidadConCodigo;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.binding.GestionLocalesBinding;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_LOCALES_BEAN)
public class GestionLocalesBean {
	
	//Constantes
	private static final String ACCION_AGREGAR_LOCAL = "_agregarLocal_";
	private static final String ACCION_DETALLES_LOCAL = "_detallesLocal_";
	private static final String ACCION_MODIFICAR_LOCAL = "_modificarLocal_";
	private static final String ACCION_ELIMINAR_LOCAL = "_eliminarLocal_";
	private static final String ACCION_RECUPERAR_LOCAL = "_recuperarLocal_";
	private static final String ACCION_VER_OPNIONES = "_verLocales_";
	private static final String ACCION_VER_VALORACIONES = "_verValoraciones_";
	
	//Atributos
	private boolean desplegado;
	private String criterioNombre;
	private List<Integer> criterioTipoLocal;
	private SelectItem[] selectTipoLocal;
	private List<Local> listaLocales;
	private Provincia criterioProvincia;
	private SelectItem[] selectProvincia;
	private String criterioPrecio;
	private SelectItem[] selectPrecio;
	private Boolean criterioActivo = Boolean.TRUE;
	private boolean mostrarCriterioActivo = false;
	private SelectItem[] selectSiNo;
	
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	
	//Utilidades
	@In(value=GESTION_LOCALES_BINDING, create=true)
	private GestionLocalesBinding binding;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	//Servicios
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;
	
	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;

	
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		desplegado = true;
		selectTipoLocal = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioTipoLocal.encontrarTodos(),TipoLocal.ATRIBUTO_DESCRIPCION), 
														    TipoLocal.ATRIBUTO_ID, 
														    TipoLocal.ATRIBUTO_DESCRIPCION,
														    true);
		selectProvincia = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioProvincia.encontrarTodos(), Provincia.ATRIBUTO_NOMBRE), 
															null, 
															Provincia.ATRIBUTO_NOMBRE,
															true);
		selectPrecio = SelectItemBuilder.creaSelectItems(servicioLocal.obtenerRangosPrecio(), 
														 EntidadConCodigo.ATRIBUTO_VALOR, 
														 EntidadConCodigo.ATRIBUTO_ETIQUETA,
														 true);
		selectSiNo = SelectItemBuilder.creaSelectItemsSiNo();
		
		mostrarCriterioActivo = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_LOCALES);
	}
	
	/**
	 * Busca los Locales que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaLocales = servicioLocal.encontrarLocalesPorNombreTipoProvinciaYPrecio(criterioNombre, 
																				   criterioTipoLocal, 
																				   criterioProvincia,
																				   criterioPrecio,
																				   criterioActivo);
		servicioLocal.rellenarPropiedadesNoMapeadas(listaLocales);
		desplegado = false;
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaLocales!=null && listaLocales.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Limpia los criterios de búsqueda
	 */
	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioNombre = null;
		criterioTipoLocal = null;
		criterioPrecio = null;
		criterioProvincia = null;
		criterioActivo = null;
	}
	
	/**
	 * Navega a la pantalla de edición de locales para agregar uno nuevo
	 * @return Regla de navegación de la pantalla de edición de locales 
	 */
	public String agregar(){
		return realizarOperacion(ACCION_AGREGAR_LOCAL);
	}
	
	/**
	 * Navega a la pantalla de detalles del local
	 * @return Regla de navegación de la pantalla de detalles del local
	 */
	public String detalles(){
		return realizarOperacion(ACCION_DETALLES_LOCAL);
	}

	/**
	 * Navega a la pantalla de edición de locales para modificar el seleccionado
	 * @return Regla de navegación de la pantalla de edición de locales
	 */
	public String modificar(){
		return realizarOperacion(ACCION_MODIFICAR_LOCAL);
	}
	
	/**
	 * Elimina el local seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_LOCAL);
	}
	
	/**
	 * Recupera el local seleccionado
	 */
	public void recuperar(){
		realizarOperacion(ACCION_RECUPERAR_LOCAL);
	}

	/**
	 * Navega para ver las opiniones del local
	 */
	public String verOpiniones(){
		return realizarOperacion(ACCION_VER_OPNIONES);
	}
	
	/**
	 * Navega para ver las valoraciones del local
	 */
	public String verValoraciones(){
		return realizarOperacion(ACCION_VER_VALORACIONES);
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaLocales != null ? listaLocales.size() : "0");
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
		if(estadoDeSeleccionTabla.size()==1 || ACCION_AGREGAR_LOCAL.equals(operacion)){
			
			if(ACCION_AGREGAR_LOCAL.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_LOCALES,null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		
				outcome = EDITAR_LOCAL;
			}
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Local local = listaLocales.get(seleccion);
				if(ACCION_MODIFICAR_LOCAL.equals(operacion) || ACCION_DETALLES_LOCAL.equals(operacion)){
					
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(local,GESTION_LOCALES,null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			
					outcome = ACCION_MODIFICAR_LOCAL.equals(operacion) ? EDITAR_LOCAL : DETALLES_LOCAL;
					operacionRealizada = true;
				
				}else if(ACCION_ELIMINAR_LOCAL.equals(operacion)){
					
					if(local!=null && local.isActivo()){
						servicioLocal.desactivarLocal(local);
						servicioLocal.descartarLocal(local);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("LOCAL_ELIMINADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_LOCAL_ELIMINADO"));
	
				}else if(ACCION_RECUPERAR_LOCAL.equals(operacion)){
	
					if(local!=null && !local.isActivo()){
						servicioLocal.activarLocal(local);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("LOCAL_RECUPERADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_RECUPERAR_LOCAL_ACTIVO"));
					
				}else if(ACCION_VER_OPNIONES.equals(operacion)){
	
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(local,GESTION_LOCALES,null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			
					outcome = GESTION_OPINIONES_LOCAL;
					operacionRealizada = true;
					
				}else if(ACCION_VER_VALORACIONES.equals(operacion)){
	
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(local,GESTION_LOCALES,null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			
					outcome = GESTION_VALORACIONES_LOCAL;
					operacionRealizada = true;
				}
			}
			
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		if(operacionRealizada)
			estadoDeSeleccionTabla.clear();
		
		return outcome;
	}



	public boolean isDesplegado() {
		return desplegado;
	}
	public void setDesplegado(boolean desplegado) {
		this.desplegado = desplegado;
	}
	public GestionLocalesBinding getBinding() {
		return binding;
	}
	public void setBinding(GestionLocalesBinding binding) {
		this.binding = binding;
	}


	public String getCriterioNombre() {
		return criterioNombre;
	}


	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre = criterioNombre;
	}

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public void setListaLocales(List<Local> listaLocales) {
		this.listaLocales = listaLocales;
	}

	public Provincia getCriterioProvincia() {
		return criterioProvincia;
	}

	public void setCriterioProvincia(Provincia criterioProvincia) {
		this.criterioProvincia = criterioProvincia;
	}

	public SelectItem[] getSelectProvincia() {
		return selectProvincia;
	}

	public void setSelectProvincia(SelectItem[] selectProvincia) {
		this.selectProvincia = selectProvincia;
	}

	public String getCriterioPrecio() {
		return criterioPrecio;
	}

	public void setCriterioPrecio(String criterioPrecio) {
		this.criterioPrecio = criterioPrecio;
	}

	public SelectItem[] getSelectPrecio() {
		return selectPrecio;
	}

	public void setSelectPrecio(SelectItem[] selectPrecio) {
		this.selectPrecio = selectPrecio;
	}

	public List<Integer> getCriterioTipoLocal() {
		return criterioTipoLocal;
	}

	public void setCriterioTipoLocal(List<Integer> criterioTipoLocal) {
		this.criterioTipoLocal = criterioTipoLocal;
	}

	public SelectItem[] getSelectTipoLocal() {
		return selectTipoLocal;
	}

	public void setSelectTipoLocal(SelectItem[] selectTipoLocal) {
		this.selectTipoLocal = selectTipoLocal;
	}

	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}

	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}

	public Boolean getCriterioActivo() {
		return criterioActivo;
	}

	public void setCriterioActivo(Boolean criterioActivo) {
		this.criterioActivo = criterioActivo;
	}

	public SelectItem[] getSelectSiNo() {
		return selectSiNo;
	}

	public void setSelectSiNo(SelectItem[] selectSiNo) {
		this.selectSiNo = selectSiNo;
	}

	public boolean isMostrarCriterioActivo() {
		return mostrarCriterioActivo;
	}

	public void setMostrarCriterioActivo(boolean mostrarCriterioActivo) {
		this.mostrarCriterioActivo = mostrarCriterioActivo;
	}

}
