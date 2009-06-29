package seam;

import static dondeando.bean.MenuPrincipalBean.JSF_MENU_PRINCIPAL;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;

import dondeando.bean.MenuPrincipalBean;


/**
 * Façade para la clase Redirect de Seam, en previsión de que será movida en Seam 2.0.0 
 */
public class Redirect{
    
    /** Redirige a la página indicada si no es nula. Si es nula no hace nada. */
    public static void redirigir(String viewId){
        redirigir(viewId, null, true);
    }

    /**
     * Redirige a la página indicada si no es nula, con los parámetros indicados, si no son nulos.
     * Si es nula no hace nada.
     */
    public static void redirigir(String viewId, Map<String, Object> params, boolean incluirIdConversation) {
        if(viewId!=null) {
            if(viewId.toLowerCase().startsWith("http"))
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(viewId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            else{
                org.jboss.seam.core.Redirect redireccion = org.jboss.seam.core.Redirect.instance();
                redireccion.setViewId(viewId);
                redireccion.setConversationPropagationEnabled(incluirIdConversation);
                if(params!=null)
                    for(String param : params.keySet())
                        redireccion.setParameter(param, params.get(param));
    
                redireccion.execute();
            }
        }
    }
    
    public static void redirigirInicio(){
        redirigir(JSF_MENU_PRINCIPAL);
    }
    
    /** Devuelve todos los parámetros actuales del request actual */
    public static Map<String, Object> getParametrosActuales(){
        org.jboss.seam.core.Redirect redirect = org.jboss.seam.core.Redirect.instance();
        redirect.captureCurrentView();
        return redirect.getParameters();
    }



}
