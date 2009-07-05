package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(CONVERSATION)
@Name(MENU_PRINCIPAL_BEAN)
public class MenuPrincipalBean {
	
	public static String JSF_MENU_PRINCIPAL = "/dondeando/menuPrincipal.jsf";
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}
	
	public String gestionUsuarios(){
		return GESTION_USUARIOS;
	}

}
