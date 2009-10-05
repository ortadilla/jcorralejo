package dondeando.bean;

import static dondeando.modelo.servicio.ServicioLocal.RANGO_PRECIO_10_30;
import static dondeando.modelo.servicio.ServicioLocal.RANGO_PRECIO_30_50;
import static dondeando.modelo.servicio.ServicioLocal.RANGO_PRECIO_MAYOR_50;
import static dondeando.modelo.servicio.ServicioLocal.RANGO_PRECIO_MENOR_10;
import static utilidades.varios.NombresBean.GESTION_LOCALES_BEAN;
import static utilidades.varios.NombresBean.GESTION_LOCALES_BINDING;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.EntidadConCodigo;
import utilidades.varios.HerramientasList;
import utilidades.varios.MensajesCore;
import utilidades.varios.SelectItemBuilder;
import dondeando.binding.GestionLocalesBinding;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_LOCALES_BEAN)
public class GestionLocalesBean {
	
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
	
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	
	//Utilidades
	@In(value=GESTION_LOCALES_BINDING, create=true)
	private GestionLocalesBinding binding;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	//Servicios
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;
	
	
	
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
		selectPrecio = SelectItemBuilder.creaSelectItems(obtenerRangosPrecio(), 
														 EntidadConCodigo.ATRIBUTO_VALOR, 
														 EntidadConCodigo.ATRIBUTO_ETIQUETA,
														 true);
	}
	
	/**
	 * Devuelve una lista con los rangos de precios en los que están clasificados
	 * los Locales
	 * @return Lista con los rangos de precios en los que están clasificados
	 * los Locales
	 */
	private List<EntidadConCodigo> obtenerRangosPrecio(){
		List<EntidadConCodigo> rangosPrecios = new ArrayList<EntidadConCodigo>();
		rangosPrecios.add(new EntidadConCodigo(1, mensajesCore.obtenerTexto(RANGO_PRECIO_MENOR_10), RANGO_PRECIO_MENOR_10));
		rangosPrecios.add(new EntidadConCodigo(2, mensajesCore.obtenerTexto(RANGO_PRECIO_10_30), RANGO_PRECIO_10_30));
		rangosPrecios.add(new EntidadConCodigo(3, mensajesCore.obtenerTexto(RANGO_PRECIO_30_50), RANGO_PRECIO_30_50));
		rangosPrecios.add(new EntidadConCodigo(4, mensajesCore.obtenerTexto(RANGO_PRECIO_MAYOR_50), RANGO_PRECIO_MAYOR_50));
		return rangosPrecios;
	}
	
	/**
	 * Busca los Locales que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaLocales = servicioLocal.encontrarLocalesPorNombreTipoProvinciaYPrecio(criterioNombre, 
																				   criterioTipoLocal, 
																				   criterioProvincia,
																				   criterioPrecio);
		servicioLocal.rellenarPropiedadesNoMapeadas(listaLocales);
		desplegado = false;
		binding.getBusqueda().setDisclosed(desplegado);
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
	}
	
	/**
	 * Navega a la pantalla de edición de locales para agregar uno nuevo
	 * @return Regla de navegación de la pantalla de edición de locales 
	 */
	public String agregar(){
		return null;
	}
	
	public String detalles(){
		return null;
	}

	/**
	 * Navega a la pantalla de edición de locales para modificar el seleccionado
	 * @return Regla de navegación de la pantalla de edición de locales
	 */
	public String modificar(){
		return null;
	}
	
	/**
	 * Elimina el local seleccionado
	 */
	public void eliminar(){
	}
	
	/**
	 * Recupera el local seleccionado
	 */
	public void recuperar(){
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

}
