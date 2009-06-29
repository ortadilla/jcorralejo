package utilidades.varios;
import static utilidades.varios.NombresBean.MENSAJES_CORE;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;

import utilidades.jsf.UtilJsfContext;


/**
 * Se indica scope APPLICATION porque debe estar accesible en entornos donde a�n no hay
 * sesi�n HTTP (por ejemplo, el programador de tareas interno). Aparte, esta clase es, de hecho,
 * com�n para toda la aplicaci�n.
 */
@Scope(ScopeType.APPLICATION)
@Name(MENSAJES_CORE)
public class MensajesCore {
    
    private static final String BUNDLE_CORE = "mensajesCore";

    @Logger
    Log log; 
    
    private ResourceBundle mensajes;
    
    protected String nombreBundle = BUNDLE_CORE; //Se sobreescribir� en las clases hijas
    
    @In(value=NombresBean.UTIL_JSF_CONTEXT, required=false)
    private UtilJsfContext jsfContext;
    
    /**
     * M�todo que intenta obtener el texto asociado a la clave que se le pasa como par�metro
     * @param key
     * @param params
     * @return null si no encuentra la traducci�n para la clave proporcionada
     */
    public String obtenerTexto(String key, Object... params){
        //El bundle no se puede inicializar en el constructor porque a�n no tenemos la
        //inyecci�n de jsf_context. Lo inicializamos la primera vez que se acceda.
        if(mensajes==null) {
            Locale locale = getLocale(); //�Conocemos el Locale del cliente? 
            mensajes = locale==null ? ResourceBundle.getBundle(nombreBundle) :
                                      ResourceBundle.getBundle(nombreBundle, locale);
        }
            
        String texto = null;
        try {
        	texto = mensajes.getString(key);
        } catch (MissingResourceException e) { //Me la trago como el Gavin King
            /*
             * S�lo mostramos WARN cuando sea el core. Si es una clase hija, s�lo un DEBUG. El objetivo
             * es no saturar la consola con mensajes que no est�n en las hijas pero s� en core.
             */ 
            if(log!=null && log.isWarnEnabled() && BUNDLE_CORE.equals(this.nombreBundle))
                log.warn("Mensaje \""+key+"\" no encontrado en \""+nombreBundle+"\"");
            else if(log!=null && log.isDebugEnabled())
                log.debug("Mensaje \""+key+"\" no encontrado en \""+nombreBundle+"\"");
            return null;
        } 
        
        return params.length>0 ? MessageFormat.format(texto,params) : texto;
    }

    private Locale getLocale() {
        if(jsfContext==null                                //En pruebas no hay jsfContext
        || jsfContext.getJsfContext()==null                //En las tareas del programador no hay FacesContext, ya que se ejecutan en un thread en background
        || jsfContext.getJsfContext().getViewRoot()==null) //Para evitar NPE, pslm... 
            return null;
            
        return jsfContext.getJsfContext().getViewRoot().getLocale();
    }
    
    /** Obtiene una instancia del componente Seam (en ejecuci�n) o creando un new obj (para pruebas) */
    public static MensajesCore instancia(){
        return Contexts.isApplicationContextActive() 
                    ? (MensajesCore)Component.getInstance(MENSAJES_CORE, true)
                    : new MensajesCore();
    }

}

