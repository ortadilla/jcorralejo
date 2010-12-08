package biblioTec.utilidades;

import static biblioTec.utilidades.NombresBean.MENSAJES_CORE;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;

@Scope(ScopeType.APPLICATION)
@Name(NombresBean.MENSAJES_CORE)
public class MensajesCore {
    
    @Logger
    Log log; 
    
    private ResourceBundle mensajes;
    
    protected String nombreBundle = "mensajesCore";
    
    @In(value=NombresBean.UTIL_JSF_CONTEXT, required=false)
    private UtilJsfContext jsfContext;
    
    public String obtenerTexto(String key, Object... params){
        if(mensajes==null) {
            Locale locale = getLocale(); 
            mensajes = locale==null ? ResourceBundle.getBundle(nombreBundle) :
                                      ResourceBundle.getBundle(nombreBundle, locale);
        }
            
        String texto = null;
        try {
        	texto = mensajes.getString(key);
        } catch (MissingResourceException e) {
        	log.warn("Mensaje \""+key+"\" no encontrado en \""+nombreBundle+"\"");
            return null;
        } 
        
        return params.length>0 ? MessageFormat.format(texto,params) : texto;
    }

    private Locale getLocale() {
        return jsfContext!=null && jsfContext.getJsfContext()!=null && jsfContext.getJsfContext().getViewRoot()!=null 
        	 ? jsfContext.getJsfContext().getViewRoot().getLocale() : null;
    }
    
    public static MensajesCore instancia(){
        return Contexts.isApplicationContextActive() 
                    ? (MensajesCore)Component.getInstance(MENSAJES_CORE, true)
                    : new MensajesCore();
    }

}

