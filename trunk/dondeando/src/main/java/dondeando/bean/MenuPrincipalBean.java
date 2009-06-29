package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(CONVERSATION)
@Name(MENU_PRINCIPAL_BEAN)
public class MenuPrincipalBean {
	
	public static String JSF_MENU_PRINCIPAL = "/dondeando/menuPrincipal.jsf";

}
