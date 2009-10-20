package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_FOROS;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.Permisos;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(CONVERSATION)
@Name(MENU_PRINCIPAL_BEAN)
public class MenuPrincipalBean {
	
	public static String JSF_MENU_PRINCIPAL = "/dondeando/menuPrincipal.jsf";
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;

	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;
	
	private boolean mostrarGestionUsuarios;
	private boolean mostrarGestionLocales;
	private boolean mostrarGestionForos;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	
	public void cargarArgumentosDeEntrada(){
		pintarBotones();
	}
	
	/**
	 * Muestra u oculta los botones dependiendo del perfil del usuario
	 */
	private void pintarBotones(){
		mostrarGestionUsuarios = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_USUARIOS);
		mostrarGestionLocales = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_LOCALES);
		mostrarGestionForos = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_FOROS);
	}

	/**
	 * Navega a la gestión de Usuarios
	 * @return Outcome de la gestión de Usuarios
	 */
	public String gestionUsuarios(){
		return GESTION_USUARIOS;
	}
	
	/**
	 * Navega a la gestión de Locales
	 * @return Outcome de la gestión de Locales
	 */
	public String gestionLocales(){
		return GESTION_LOCALES;
	}

	/**
	 * Navega a la gestión de Foros
	 * @return Outcome de la gestión de Foros
	 */
	public String gestionForos(){
		return GESTION_FOROS;
	}
	
	public boolean isMostrarGestionUsuarios() {
		return mostrarGestionUsuarios;
	}

	public void setMostrarGestionUsuarios(boolean mostrarGestionUsuarios) {
		this.mostrarGestionUsuarios = mostrarGestionUsuarios;
	}

	public boolean isMostrarGestionLocales() {
		return mostrarGestionLocales;
	}

	public void setMostrarGestionLocales(boolean mostrarGestionLocales) {
		this.mostrarGestionLocales = mostrarGestionLocales;
	}


	public boolean isMostrarGestionForos() {
		return mostrarGestionForos;
	}


	public void setMostrarGestionForos(boolean mostrarGestionForos) {
		this.mostrarGestionForos = mostrarGestionForos;
	}

}
