package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
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
	}
	
	public String gestionUsuarios(){
		return GESTION_USUARIOS;
	}
	
	public String gestionLocales(){
		return GESTION_LOCALES;
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

}
