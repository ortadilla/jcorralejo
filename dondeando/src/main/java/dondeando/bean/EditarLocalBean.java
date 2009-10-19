package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.CAMBIAR_CREAR_LOCAL_POR_EDITAR_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.IMAGENES_LOCAL;
import static utilidades.varios.NombresBean.EDITAR_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_SERVICIO;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_VIA;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.myfaces.trinidad.validator.RegExpValidator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioServicio;
import dondeando.modelo.servicio.ServicioTipoLocal;
import dondeando.modelo.servicio.ServicioTipoVia;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_LOCAL_BEAN)
public class EditarLocalBean {

	private static final String OPERACION_EDITAR_LOCAL = "_editarLocal_";
	private static final String OPERACION_CREAR_LOCAL = "_crearLocal_";
	
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
	private String tituloPagina;
	private String operacion;
	private String urlImagenPrincipal;
	private RegExpValidator validatorEmail;
	
	private Local localEdicion;
	
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	//Servicios
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_TIPO_VIA, create=true)
	private ServicioTipoVia servicioTipoVia;
	
	@In(value=SERVICIO_SERVICIO, create=true)
	private ServicioServicio servicioServicio;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;
	
	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;

	
	
	private ProtocoloEdicion protocoloEdicion;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		
		//Bug Trinidad 1.0.10
		inicializarValidador();
		selectTiposLocal = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioTipoLocal.encontrarTodos(),TipoLocal.ATRIBUTO_DESCRIPCION), 
														     TipoLocal.ATRIBUTO_ID, 
														     TipoLocal.ATRIBUTO_DESCRIPCION);
		selectProvincia = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioProvincia.encontrarTodos(), Provincia.ATRIBUTO_NOMBRE), 
															null, 
															Provincia.ATRIBUTO_NOMBRE);
//		selectPrecio = SelectItemBuilder.creaSelectItems(servicioLocal.obtenerRangosPrecio(), 
//														 EntidadConCodigo.ATRIBUTO_VALOR, 
//														 EntidadConCodigo.ATRIBUTO_ETIQUETA);
		selectTipoVia = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioTipoVia.encontrarTodos(),TipoVia.ATRIBUTO_DESCRIPCION), 
														  null, 
														  TipoVia.ATRIBUTO_DESCRIPCION);
		selectServicios = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioServicio.encontrarTodos(),Servicio.ATRIBUTO_DESCRIPCION), 
															null, 
															Servicio.ATRIBUTO_DESCRIPCION);

	}
	
	/**
	 * Configura el validador para el email. necesario por un bug en la versión 1.0.10 de Trinidad
	 */
	private void inicializarValidador(){
		if(validatorEmail==null){
			validatorEmail = new RegExpValidator();
			validatorEmail.setPattern("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$");
			validatorEmail.setMessageDetailNoMatch(mensajesCore.obtenerTexto("ERROR_PATRON_EMAIL"));
		}
	}
	
	
	/**
	 * Ejecutado al pulsar el botón aceptar, intenta guardar el local indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegación
	 */
	public String aceptar(){
		String outcome = "";
		List<String> errores = comprobarAgregarEditarLocal();
		
		if(errores.isEmpty()){
			if(OPERACION_CREAR_LOCAL.equals(operacion)){
				servicioLocal.crearLocal(nombre, provincia, localidad, tipoVia, tiposLocal, nombreVia, numero, codigoPostal, descripcion, telefono, email, horario, precioMedio, otraInformacion, servicios);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("LOCAL_CORRECTO"));
			}else if (OPERACION_EDITAR_LOCAL.equals(operacion) && localEdicion!=null){
				servicioLocal.editarLocal(localEdicion, nombre, provincia, localidad, tipoVia, tiposLocal, nombreVia, numero, codigoPostal, descripcion, telefono, email, horario, precioMedio, otraInformacion, servicios);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("LOCAL_ACTUALIZADO"));
			}
			outcome = protocoloEdicion!=null && protocoloEdicion.getOutcomeVuelta()!=null ? protocoloEdicion.getOutcomeVuelta() 
											 											  : ConstantesReglasNavegacion.MENU_PRINCIPAL;
		}else
			utilJsfContext.insertaMensajesAdvertencia(errores);
		
		if(!"".equals(outcome)){
			protocoloEdicion = null;
			limpiarFormulario();
		}
		
		return outcome;
	}
	
	/**
	 * Comprueba si se puede agregar/editar el local con los datos indicados en la pantalla
	 * @return Lista de errores encontrados
	 */
	private List<String> comprobarAgregarEditarLocal(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(nombre==null || "".equals(nombre)
		|| provincia == null
		|| localidad == null || "".equals(localidad)
		|| tipoVia == null
		|| codigoPostal==null
		|| tiposLocal==null || tiposLocal.isEmpty()
		|| precioMedio==null || "".equals(precioMedio))
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_EDITAR_LOCAL"));
		
		//No debe existir un local con el mismo nombre en esa localidad 
		if(errores.isEmpty()){
			Local local = servicioLocal.encontrarLocalPorNombreLocalidadProvincia(nombre,localidad,provincia);
			if(local!=null && (localEdicion==null || !local.equals(localEdicion)))
				errores.add(mensajesCore.obtenerTexto("LOCAL_YA_EXISTE", nombre, localidad, provincia.getNombre()));
		}

		return errores;
	}


	
	/**
	 * Cancela la edición del usuario y vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String cancelar(){
		String outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
		protocoloEdicion = null; //para que al volver no se cargue nada
		limpiarFormulario();
		return outcome;
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
		
		tituloPagina = null;
		operacion = null;
		urlImagenPrincipal = null;
		
		localEdicion = null;
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			//Configuramos la página dependiente de la operación
			operacion = protocoloEdicion.getObjeto()!=null ? OPERACION_EDITAR_LOCAL : OPERACION_CREAR_LOCAL;
			if(mapaArgumentos!=null && mapaArgumentos.contiene(CAMBIAR_CREAR_LOCAL_POR_EDITAR_LOCAL) 
			&& OPERACION_CREAR_LOCAL.equals(operacion)){
				operacion = OPERACION_EDITAR_LOCAL;
				mapaArgumentos.borrar(CAMBIAR_CREAR_LOCAL_POR_EDITAR_LOCAL);
			}
			if(OPERACION_CREAR_LOCAL.equals(operacion)){
				tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_USUARIO");
				urlImagenPrincipal = servicioImagen.calcularUrlImagenUsuarioNuevo();
			}
			else if(OPERACION_EDITAR_LOCAL.equals(operacion)){
				
				if(protocoloEdicion!=null){
					//Comprobamos si estamos editando un usuario ya existente
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
						tiposLocal = HerramientasList.obtenerAtributos(localEdicion.getTiposLocal(), TipoLocal.ATRIBUTO_ID);
						precioMedio = localEdicion.getPrecioMedio();
						telefono = localEdicion.getTelefono();
						email = localEdicion.getEmail();
						horario = localEdicion.getHorario();
						otraInformacion = localEdicion.getOtraInformacion();
						if(servicios==null) servicios = new ArrayList<Servicio>();
						servicios.addAll(localEdicion.getServicios());
						
					}
				}
	
				tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_LOCAL");
			}
		}
	}
	
	/**
	 * Navega para ver las imágenes del local
	 * @return Regla de navegación
	 */
	public String irImagenes(){
		
		String outcome = "";
		List<String> errores = comprobarAgregarEditarLocal();
		
		if(errores.isEmpty()){
			if(OPERACION_CREAR_LOCAL.equals(operacion)){
				localEdicion = servicioLocal.crearLocal(nombre, provincia, localidad, tipoVia, tiposLocal, nombreVia, numero, codigoPostal, descripcion, telefono, email, horario, precioMedio, otraInformacion, servicios);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("LOCAL_CORRECTO"));
			}
			ProtocoloEdicion protocolo = new ProtocoloEdicion(localEdicion,
															  ConstantesReglasNavegacion.EDITAR_LOCAL,
															  ImagenesLocalBean.OPERACION_EDITAR_IMAGENES);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
			outcome = IMAGENES_LOCAL;
		}else
			utilJsfContext.insertaMensajesAdvertencia(errores);
		
		return outcome;
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
	public String getTituloPagina() {
		return tituloPagina;
	}
	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}
	public String getUrlImagenPrincipal() {
		return urlImagenPrincipal;
	}
	public void setUrlImagenPrincipal(String urlImagenPrincipal) {
		this.urlImagenPrincipal = urlImagenPrincipal;
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


	public RegExpValidator getValidatorEmail() {
		return validatorEmail;
	}


	public void setValidatorEmail(RegExpValidator validatorEmail) {
		this.validatorEmail = validatorEmail;
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
