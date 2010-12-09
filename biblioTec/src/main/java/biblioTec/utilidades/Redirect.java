package biblioTec.utilidades;


import static biblioTec.utilidades.ConstantesReglasNavegacion.JSF_MENU_PRINCIPAL;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;


public class Redirect{
    
    public static void redirigir(String viewId){
        redirigir(viewId, null, true);
    }

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
    
}
