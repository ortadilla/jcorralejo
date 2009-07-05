package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_CREAR_USUARIO;
import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_DETALLES_USUARIO;
import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_EDITAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.CREAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.jsf.ConstantesReglasNavegacion.MODIFICAR_PASSWORD;
import static utilidades.varios.NombresBean.CABECERA_PAGINA_BEAN;
import static utilidades.varios.NombresBean.CREAR_USUARIO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.validator.RegExpValidator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.componentes.CabeceraPaginasBean;
import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioTipoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(CREAR_USUARIO_BEAN)
public class CrearUsuarioBean {
	
	private String login;
	private String password;
	private String password2;
	private String nombre;
	private String apellidos;
	private String direccion; //TODO: Pendiente de GoogleMaps
	private String email;
	private TipoUsuario tipoUsuario;
	
	private SelectItem[] selectTipoUsuario;
	private boolean deshabilitarTipoUsuario;
	private boolean detalles;
	/** Indica si se puede modificar los datos del usuario */
	private boolean modificarUsuario;
	/** Indica si se puede modificar el password del usuario */
	private boolean modificarPass;
	/** Indica si se puede eliminar el usuario */
	private boolean eliminarUsuario;
	private boolean mostrarPass;
	private boolean mostrarVolver;
	private RegExpValidator validatorEmail;
	private String operacion;
	private String tituloPagina;
	private String urlImagenUsuario;

	private Usuario usuarioEdicion;
	private boolean reentrando;
	private UploadedFile fileAvatar;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
    @In(value=CABECERA_PAGINA_BEAN, create=true)
    private CabeceraPaginasBean cabeceraPaginasBean;
	
	private ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	@In(value=SERVICIO_TIPO_USUARIO, create=true)
	private ServicioTipoUsuario servicioTipoUsuario;

	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	public void cargarArgumentosDeEntrada(){
		//Bug Trinidad 1.0.10
		inicializarValidador();
		
		//limpiamos el formulario, por si se quedaron datos obsoletos, pero s�lo en el caso
		//de que hayamos entrado desde fuera
		if(!reentrando){
			limpiarFormulario(false);
		}else
			reentrando = false;
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			operacion = protocoloEdicion.getOperacion();
			mostrarVolver = protocoloEdicion.getOutcomeVuelta()!=null;
			
			//Comprobamos si estamos editando un usuario ya existente
			if(protocoloEdicion.getObjeto()!=null){
				usuarioEdicion = (Usuario)protocoloEdicion.getObjeto();
				login = usuarioEdicion.getLogin();
				password = null;
				password2 = null;
				nombre = usuarioEdicion.getNombre();
				apellidos = usuarioEdicion.getApellidos();
				direccion = usuarioEdicion.getDireccion();
				email = usuarioEdicion.getEmail();
				tipoUsuario = usuarioEdicion.getTipoUsuario();
				
				urlImagenUsuario = servicioImagen.calcularUrlImagenUsuario(usuarioEdicion);
			}else
				urlImagenUsuario = servicioImagen.calcularUrlImagenUsuarioNuevo();
				
		}

		//Configuramos la p�gina dependiente de la operaci�n
		Usuario usuarioActivo = servicioUsuario.devolverUsuarioActivo();
		if(OPERACION_CREAR_USUARIO.equals(operacion)){
			tipoUsuario = servicioTipoUsuario.devolverTipoUsuarioRegistrado();
			mostrarPass = true;
			detalles = false;
			modificarPass = false;
			modificarUsuario = false;
			eliminarUsuario = false;
			tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_USUARIO");
		}
		else if(OPERACION_DETALLES_USUARIO.equals(operacion)){
			detalles = true;
			mostrarPass = false;
			tituloPagina = mensajesCore.obtenerTexto("DETALLES_USUARIO");
			modificarPass = usuarioActivo.equals(usuarioEdicion);
			modificarUsuario = usuarioActivo.equals(usuarioEdicion);
			eliminarUsuario = usuarioActivo.equals(usuarioEdicion);
		}
		else if(OPERACION_EDITAR_USUARIO.equals(operacion)){
			detalles = false;
			mostrarPass = false;
			tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_USUARIO");
			modificarPass = usuarioActivo.equals(usuarioEdicion);
			modificarUsuario = false;
			eliminarUsuario = true;
		}
		
		//Al crear un usuario nuevo desde el registro siempre ser� uno normal
		//y no se podr� modificar
		deshabilitarTipoUsuario = true; //TODO: Debe depender de un permiso
		selectTipoUsuario = SelectItemBuilder.creaSelectItems(servicioTipoUsuario.devolverTodosTipoUsuarioMenosAnonimo(), 
															  null, 
															  TipoUsuario.ATRIBUTO_DESCRIPCION);
	}
	
	
	/**
	 * Configura el validador para el email. necesario por un bug en la versi�n 1.0.10 de Trinidad
	 */
	private void inicializarValidador(){
		if(validatorEmail==null){
			validatorEmail = new RegExpValidator();
			validatorEmail.setPattern("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$");
			validatorEmail.setMessageDetailNoMatch(mensajesCore.obtenerTexto("ERROR_PATRON_EMAIL"));
		}
	}

	
	/**
	 * Limpia el formulario de registro
	 * @param soloElementosPagina Indica si s�lo se limpian los elementos que se pintan en la p�gina.
	 * Si es false se resetean todas las propiedades internas
	 */
	private void limpiarFormulario(boolean soloElementosPagina){
		login = null;
		password = null;
		password2 = null;
		nombre = null;
		apellidos = null;
		direccion = null;
		tipoUsuario = null;
		email = null;
		fileAvatar = null;
		urlImagenUsuario = null;
		
		if(!soloElementosPagina){
			operacion = null;
			usuarioEdicion = null;
			mostrarPass = true;
			detalles = true;
		}
	}
	
	public String volver(){
		return protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta()
									  : MENU_PRINCIPAL;
	}
	
	/**
	 * Ejecutado al pulsar el bot�n aceptar, intenta guardar el usuario indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegaci�n
	 */
	public String aceptar(){
		String outcome = "";
		
		List<String> errores = comprobarAgregarEditarUsuario();
		if(fileAvatar!=null)
			try {
				errores.addAll(servicioImagen.comprobarAgregarImagen(fileAvatar.getContentType(), 
																	 fileAvatar.getLength(),
																	 fileAvatar.getInputStream()));
			} catch (IOException e1) {
				//Si no se puede obtener el inputStream, no comprobamos los errores de la imagen
				//seleccionada, y le asociamos la de por defecto
				utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN"));
				fileAvatar = null;
			}
		
		if(errores.isEmpty()){
			//Si no se a�adi� ninguna imagen tomamos la de por defecto
			Imagen imagenUsuario = servicioImagen.encontrarImagenUsuarioGenerico();
			if(fileAvatar!=null)
				try {
					imagenUsuario = servicioImagen.guardarImagen(fileAvatar.getFilename(), fileAvatar.getInputStream(), false);
				} catch (IOException e) {
					//Si no se puede obtener el inputStream, le asociamos la imagen por defecto
					utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN"));
				}
				
			if(OPERACION_CREAR_USUARIO.equals(operacion)){
				servicioUsuario.crearUsuario(login, password, nombre, apellidos, direccion, email, tipoUsuario, imagenUsuario);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_CORRECTO"));
			}else if (OPERACION_EDITAR_USUARIO.equals(operacion) && usuarioEdicion!=null){
				servicioUsuario.editarUsuario(usuarioEdicion,login, password, nombre, apellidos, direccion, email, tipoUsuario, imagenUsuario);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_ACTUALIZADO"));
			}
			outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() 
											 : ConstantesReglasNavegacion.MENU_PRINCIPAL;
		}else{
			utilJsfContext.insertaMensajesAdvertencia(errores);
			reentrando = true;
		}
		
		return outcome;
	}
	
	/**
	 * Comprueba si se puede agregar el usuario con los datos indicados en la pantalla
	 * @return Lista de errores encontrados
	 */
	private List<String> comprobarAgregarEditarUsuario(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(login==null || "".equals(login)
		|| mostrarPass && (password==null || "".equals(password))
		|| mostrarPass && (password2==null || "".equals(password2))
		|| nombre==null || "".equals(nombre)
		|| email==null || "".equals(email)
		|| tipoUsuario==null || "".equals(tipoUsuario))
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_AGREGAR_USUARIO"));
		
		//Contrase�as correctas
		if(password!=null && password2!=null && !password.equals(password2))
			errores.add(mensajesCore.obtenerTexto("PASSWORDS_INCORRECTOS"));
		
		//El usuario no debe existir
		if(login!=null){
			Usuario usuarioLogin = servicioUsuario.encontrarUsuarioPorLogin(login);
			if(usuarioLogin!=null && (usuarioEdicion==null || !usuarioEdicion.equals(usuarioLogin)))
				errores.add(mensajesCore.obtenerTexto("USUARIO_YA_EXISTE"));
		}

		return errores;
	}
	
	
	public String cancelar(){
		
		String outcome = "";
		
		if(OPERACION_CREAR_USUARIO.equals(operacion)){
			limpiarFormulario(false);
			outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : ""; 
		}
		else if(OPERACION_EDITAR_USUARIO.equals(operacion)){
			protocoloEdicion.setOperacion(OPERACION_DETALLES_USUARIO);
		}
		
		return outcome;
	}

	
	public void modificar(){
		protocoloEdicion.setOperacion(OPERACION_EDITAR_USUARIO);
	}
	
	public String modificarContr(){
		
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(usuarioEdicion,CREAR_USUARIO, null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		
		return MODIFICAR_PASSWORD;
	}
	
	public String eliminar(){
		if(usuarioEdicion!=null){
			servicioUsuario.desactivarUsuario(usuarioEdicion);
			cabeceraPaginasBean.logout();
			servicioUsuario.descartarUsuario(usuarioEdicion);
			utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_ELIMINADO"));
		}
		return MENU_PRINCIPAL;
	}
	
	/**
	 * M�todo que recoge el UploadedFile introducido por el usuario 
	 * para subir una foto nueva
	 * @param valueChangeEvent
	 */
	public void cambiarImagen(ValueChangeEvent valueChangeEvent){
		if(valueChangeEvent.getNewValue()!=null){
			UploadedFile uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();

			if(uploadedFile!=null && uploadedFile.getLength()>0){
				Long uploadedFileSize = uploadedFile.getLength();
				String uploadedFileType = uploadedFile.getContentType();
				String nombre = uploadedFile.getFilename();    
				InputStream inputStream = null;

				try {
					inputStream = uploadedFile.getInputStream();
//					File ficheroTemporal = servicioImagen.devuelveFicheroTemporal(inputStream);
//					FileInputStream fileInput = servicioImagen.devuelveFileInputStream(ficheroTemporal);
					List<String> errores = servicioImagen.comprobarAgregarImagen(uploadedFileType, uploadedFileSize,uploadedFile.getInputStream());
					if(errores!=null && errores.isEmpty()){
						//Guardamos la imagen en el servidor
						Imagen imagen = servicioImagen.guardarImagen(nombre, uploadedFile.getInputStream(), false);
						//TODO: Asociar al usuario y eliminar imagen anterior
					}else
						utilJsfContext.insertaMensajesAdvertencia(errores);
					
				} catch (IOException e) {
					utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("ERROR_GUARDAR_FOTO"+e.getMessage()));
				}

			}else
				utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("ERROR_FOTO_NO_SELECCIONADA"));
		}
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public SelectItem[] getSelectTipoUsuario() {
		return selectTipoUsuario;
	}
	public void setSelectTipoUsuario(SelectItem[] selectTipoUsuario) {
		this.selectTipoUsuario = selectTipoUsuario;
	}
	public boolean isDeshabilitarTipoUsuario() {
		return deshabilitarTipoUsuario;
	}
	public void setDeshabilitarTipoUsuario(boolean deshabilitarTipoUsuario) {
		this.deshabilitarTipoUsuario = deshabilitarTipoUsuario;
	}
	public RegExpValidator getValidatorEmail() {
		return validatorEmail;
	}
	public void setValidatorEmail(RegExpValidator validatorEmail) {
		this.validatorEmail = validatorEmail;
	}

	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}

	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}

	public boolean isMostrarPass() {
		return mostrarPass;
	}

	public void setMostrarPass(boolean mostrarPass) {
		this.mostrarPass = mostrarPass;
	}

	public boolean isDetalles() {
		return detalles;
	}

	public void setDetalles(boolean detalles) {
		this.detalles = detalles;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public String getUrlImagenUsuario() {
		return urlImagenUsuario;
	}

	public void setUrlImagenUsuario(String urlImagenUsuario) {
		this.urlImagenUsuario = urlImagenUsuario;
	}

	public UploadedFile getFileAvatar() {
		return fileAvatar;
	}

	public void setFileAvatar(UploadedFile fileAvatar) {
		this.fileAvatar = fileAvatar;
	}

	public boolean isModificarUsuario() {
		return modificarUsuario;
	}

	public void setModificarUsuario(boolean modificarUsuario) {
		this.modificarUsuario = modificarUsuario;
	}

	public boolean isModificarPass() {
		return modificarPass;
	}

	public void setModificarPass(boolean modificarPass) {
		this.modificarPass = modificarPass;
	}

	public boolean isEliminarUsuario() {
		return eliminarUsuario;
	}

	public void setEliminarUsuario(boolean eliminarUsuario) {
		this.eliminarUsuario = eliminarUsuario;
	}

	public boolean isMostrarVolver() {
		return mostrarVolver;
	}

	public void setMostrarVolver(boolean mostrarVolver) {
		this.mostrarVolver = mostrarVolver;
	}
}
