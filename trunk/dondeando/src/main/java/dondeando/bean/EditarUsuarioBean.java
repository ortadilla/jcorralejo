package dondeando.bean;

import static utilidades.varios.NombresBean.EDITAR_USUARIO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioTipoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_USUARIO_BEAN)
public class EditarUsuarioBean {
	
	private static final String OPERACION_EDITAR_USUARIO = "_editarUsuario_";
	private static final String OPERACION_CREAR_USUARIO = "_crearUsuario_";
	
	private String login;
	private String password;
	private String password2;
	private String nombre;
	private String apellidos;
	private String direccion; //TODO: Pendiente de GoogleMaps
	private String email;
	private TipoUsuario tipoUsuario;
	
	private SelectItem[] selectTipoUsuario;
	private boolean mostrarTipoUsuario;
	private boolean mostrarPass;
	private RegExpValidator validatorEmail;
	private String operacion;
	private String tituloPagina;
	private String urlImagenUsuario;

	private Usuario usuarioEdicion;
	private UploadedFile fileAvatar;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	private ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	@In(value=SERVICIO_TIPO_USUARIO, create=true)
	private ServicioTipoUsuario servicioTipoUsuario;

	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		selectTipoUsuario = SelectItemBuilder.creaSelectItems(servicioTipoUsuario.devolverTodosTipoUsuarioMenosAnonimo(), 
															  null, 
															  TipoUsuario.ATRIBUTO_DESCRIPCION);

	}

	public void cargarArgumentosDeEntrada(){
		//Bug Trinidad 1.0.10
		inicializarValidador();
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		//Configuramos la página dependiente de la operación
		operacion = servicioUsuario.isUsuarioActivoAnonimo() ? OPERACION_CREAR_USUARIO : OPERACION_EDITAR_USUARIO;
		if(OPERACION_CREAR_USUARIO.equals(operacion)){
			//Al crear un usuario nuevo desde el registro siempre será uno normal
			//y no se podrá modificar
			tipoUsuario = servicioTipoUsuario.devolverTipoUsuarioRegistrado();
			mostrarPass = true;
			tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_USUARIO");
			mostrarTipoUsuario = false;
			urlImagenUsuario = servicioImagen.calcularUrlImagenUsuarioNuevo();
		}
		else if(OPERACION_EDITAR_USUARIO.equals(operacion)){
			
			if(protocoloEdicion!=null){
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
				}
			}

			mostrarPass = false;
			tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_USUARIO");
			mostrarTipoUsuario = servicioPermisoUsuario.hayPermiso(Permisos.ASIGNAR_TIPO_USUARIO);
		}
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
	 * Ejecutado al pulsar el botón aceptar, intenta guardar el usuario indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegación
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
				utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN_USUARIO"));
				fileAvatar = null;
			}
		
		if(errores.isEmpty()){
			//Si no se añadió ninguna imagen tomamos la de por defecto
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
			outcome = protocoloEdicion!=null && protocoloEdicion.getOutcomeVuelta()!=null ? protocoloEdicion.getOutcomeVuelta() 
											 											  : ConstantesReglasNavegacion.MENU_PRINCIPAL;
		}else{
			utilJsfContext.insertaMensajesAdvertencia(errores);
		}
		
		if(!"".equals(outcome))
			protocoloEdicion = null;
		
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
			errores.add(mensajesCore.obtenerTexto(OPERACION_EDITAR_USUARIO.equals(operacion) 
												? "CAMPOS_OBLIGATORIOS_EDITAR_USUARIO" 
												: "CAMPOS_OBLIGATORIOS_AGREGAR_USUARIO"));
		
		//Contraseñas correctas
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
	
	/**
	 * Cancela la edición del usuario y vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String cancelar(){
		String outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
		protocoloEdicion = null; //para que al volver no se cargue nada
		return outcome;
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

	public boolean isMostrarTipoUsuario() {
		return mostrarTipoUsuario;
	}

	public void setMostrarTipoUsuario(boolean mostrarTipoUsuario) {
		this.mostrarTipoUsuario = mostrarTipoUsuario;
	}
}
