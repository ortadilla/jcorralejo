package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_OPINIONES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_VALORACIONES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.IMAGENES_LOCAL;
import static utilidades.varios.NombresBean.DETALLES_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.SERVICIO_SERVICIO;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.servicio.ServicioServicio;
import dondeando.modelo.servicio.ServicioTipoLocal;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(DETALLES_LOCAL_BEAN)
public class DetallesLocalBean {


	//Atributos
	private String nombre;
	private Provincia provincia;
	private String localidad;
	private TipoVia tipoVia;
	private List<Integer> tiposLocal;
	private List<Servicio> servicios;
	private String nombreVia;
	private Integer numero;
	private Integer codigoPostal;
	private String descripcion;
	private String telefono;
	private String email;
	private String horario;
	private BigDecimal precioMedio;
	private String otraInformacion;
	
	private SelectItem[] selectProvincia;
	private SelectItem[] selectTiposLocal;
	private SelectItem[] selectPrecio;
	private SelectItem[] selectTipoVia;
	private SelectItem[] selectServicios;
	
	private Local localEdicion;
	
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	//Servicios
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_SERVICIO, create=true)
	private ServicioServicio servicioServicio;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	private ProtocoloEdicion protocoloEdicion;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		
//		selectTiposLocal = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioTipoLocal.encontrarTodos(),TipoLocal.ATRIBUTO_DESCRIPCION), 
//														     TipoLocal.ATRIBUTO_ID, 
//														     TipoLocal.ATRIBUTO_DESCRIPCION);
//		selectServicios = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioServicio.encontrarTodos(),Servicio.ATRIBUTO_DESCRIPCION), 
//															null, 
//															Servicio.ATRIBUTO_DESCRIPCION);
	}
	
	/**
	 * Limpia las variables asociadas al formulario de edicios
	 */
	private void limpiarFormulario(){
		nombre = null;
		provincia = null;
		localidad = null;
		tipoVia = null;
		tiposLocal = null;
		nombreVia = null;
		numero = null;
		codigoPostal = null;
		descripcion = null;
		telefono = null;
		email = null;
		horario = null;
		precioMedio = null;
		otraInformacion = null;
		servicios = null;
		
		localEdicion = null;
	}

	/**
	 * Vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String volver(){
		String outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
		limpiarFormulario();
		return outcome;
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();

		if(protocoloEdicion!=null){
			if(protocoloEdicion.getObjeto()!=null){
				localEdicion = (Local)protocoloEdicion.getObjeto();
				nombre = localEdicion.getNombre();
				descripcion = localEdicion.getDescripcion();
				provincia = localEdicion.getDireccion().getProvincia();
				localidad = localEdicion.getDireccion().getLocalidad();
				tipoVia = localEdicion.getDireccion().getTipoVia();
				nombreVia = localEdicion.getDireccion().getNombreVia();
				numero = localEdicion.getDireccion().getNumero();
				codigoPostal = localEdicion.getDireccion().getCodigoPostal();
//				tiposLocal = HerramientasList.obtenerAtributos(localEdicion.getTiposLocal(), TipoLocal.ATRIBUTO_ID);
				precioMedio = localEdicion.getPrecioMedio();
				telefono = localEdicion.getTelefono();
				email = localEdicion.getEmail();
				horario = localEdicion.getHorario();
				otraInformacion = localEdicion.getOtraInformacion();
//				if(servicios==null) servicios = new ArrayList<Servicio>();
//				servicios.addAll(localEdicion.getServicios());
				selectTiposLocal = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(new ArrayList<TipoLocal>(localEdicion.getTiposLocal()),TipoLocal.ATRIBUTO_DESCRIPCION), 
																	 TipoLocal.ATRIBUTO_ID, 
																	 TipoLocal.ATRIBUTO_DESCRIPCION);
				selectServicios = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(new ArrayList<Servicio>(localEdicion.getServicios()),Servicio.ATRIBUTO_DESCRIPCION), 
																	null, 
																	Servicio.ATRIBUTO_DESCRIPCION);
				
			}
		}
	}
	
	/**
	 * Navega para ver las imágenes del local
	 * @return Regla de navegación
	 */
	public String irImagenes(){
		
		ProtocoloEdicion protocolo = new ProtocoloEdicion(localEdicion,
														  ConstantesReglasNavegacion.DETALLES_LOCAL,
														  ImagenesLocalBean.OPERACION_DETALLES_IMAGENES);
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
		
		return IMAGENES_LOCAL;
	}

	/**
	 * Navega para ver las opiniones que se han vertido sobre el local
	 * @return Regla de navegación
	 */
	public String irOpiniones(){
		
		ProtocoloEdicion protocolo = new ProtocoloEdicion(localEdicion,
														  ConstantesReglasNavegacion.DETALLES_LOCAL,
														  null);
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
		
		return GESTION_OPINIONES_LOCAL;
	}
	
	/**
	 * Navega para ver las valoraciones que se han hecho sobre el local
	 * @return Regla de navegación
	 */
	public String irValoraciones(){
		
		ProtocoloEdicion protocolo = new ProtocoloEdicion(localEdicion,
														  ConstantesReglasNavegacion.DETALLES_LOCAL,
														  null);
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
		
		return GESTION_VALORACIONES_LOCAL;
	}
	
	/**
	 * indica si hay que mostrar el selectMany con los servicios del local
	 * @return TRUE si hay que mostrarlo
	 */
	public boolean getMostrarServicios(){
		return selectServicios!=null && selectServicios.length>0;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public TipoVia getTipoVia() {
		return tipoVia;
	}
	public void setTipoVia(TipoVia tipoVia) {
		this.tipoVia = tipoVia;
	}
	public String getNombreVia() {
		return nombreVia;
	}
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public BigDecimal getPrecioMedio() {
		return precioMedio;
	}
	public void setPrecioMedio(BigDecimal precioMedio) {
		this.precioMedio = precioMedio;
	}
	public String getOtraInformacion() {
		return otraInformacion;
	}
	public void setOtraInformacion(String otraInformacion) {
		this.otraInformacion = otraInformacion;
	}
	public SelectItem[] getSelectProvincia() {
		return selectProvincia;
	}
	public void setSelectProvincia(SelectItem[] selectProvincia) {
		this.selectProvincia = selectProvincia;
	}
	public SelectItem[] getSelectPrecio() {
		return selectPrecio;
	}
	public void setSelectPrecio(SelectItem[] selectPrecio) {
		this.selectPrecio = selectPrecio;
	}
	public SelectItem[] getSelectTipoVia() {
		return selectTipoVia;
	}
	public void setSelectTipoVia(SelectItem[] selectTipoVia) {
		this.selectTipoVia = selectTipoVia;
	}
	public SelectItem[] getSelectTiposLocal() {
		return selectTiposLocal;
	}
	public void setSelectTiposLocal(SelectItem[] selectTiposLocal) {
		this.selectTiposLocal = selectTiposLocal;
	}
	public List<Integer> getTiposLocal() {
		return tiposLocal;
	}
	public void setTiposLocal(List<Integer> tiposLocal) {
		this.tiposLocal = tiposLocal;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public SelectItem[] getSelectServicios() {
		return selectServicios;
	}

	public void setSelectServicios(SelectItem[] selectServicios) {
		this.selectServicios = selectServicios;
	}

}
