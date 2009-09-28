package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.servicio.ServicioUsuario;

@Scope(CONVERSATION)
@Name(MENU_PRINCIPAL_BEAN)
public class MenuPrincipalBean {
	
	public static String JSF_MENU_PRINCIPAL = "/dondeando/menuPrincipal.jsf";
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		System.out.println("INICIALIZAR DEL MENU PRINCIPAL");
		
	}
	
	public String gestionUsuarios(){
		return GESTION_USUARIOS;
	}
	
	public String gestionLocales(){
		return GESTION_LOCALES;
	}

}
