package utilidades.jsf;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.NombresBean;


@Name(NombresBean.UTIL_JSF_CONTEXT)
@Scope(ScopeType.EVENT)
public class UtilJsfContext {
    
    /** Inserta el Contexto jsf actual un conjunto de mensajes de INFO. */
	public void insertaMensajes(List<String> mensajes){
		if (mensajes != null)
    		for(String mensaje : mensajes)
    		    insertaMensaje(mensaje);
	}
	
	/** Inserta el Contexto jsf actual un mensaje de INFO */
	public void insertaMensaje(String mensaje){
		if (mensaje != null)
		    insertarMensaje(mensaje, null, FacesMessage.SEVERITY_INFO);
	}	
	
	//Mensajes de Informaci�n
	
	/**
	 * M�todo para insertar mensajes de informaci�n en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como par�metro sea nulo o la lista vac�a, no se insertar� ning�n mensaje.
	 * @param String mensaje
	 */
	public void insertaMensajesInformacion(List<String> mensajes) {
		if(mensajes != null)
			for(String mensaje : mensajes)
				insertaMensajeInformacion(mensaje);
	}
	
	/**
	 * M�todo para insertar un mensaje de informaci�n en el contexto jsf actual. Dicho mensage vendr� dado en dos partes: el sumario
	 * o mensage principal, y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
	 */
	public void insertaMensajeInformacion(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_INFO);
	}
	
	/**
	 * M�todo para insertar un mensaje de informaci�n en el contexto jsf actual. 
	 */
	public void insertaMensajeInformacion(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_INFO);
	}
	
	
	//Mensajes de Error
	/**
	 * M�todo para insertar mensajes de error en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como par�metro sea nulo o la lista vac�a, no se insertar� ning�n mensaje.
	 * @param String mensaje
	 * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar
	 */
	@Deprecated
	public void insertaMensajesError(List<String> mensajes) {
		if(mensajes != null && !mensajes.isEmpty())
			for(String mensaje : mensajes)
				insertarMensaje(mensaje, null, FacesMessage.SEVERITY_ERROR);
	}
	
	/**
	 * M�todo para insertar mensajes de error en el contexto jsf actual. Dicho mensaje vendr� dado en dos partes, el mensaje principal y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar
     */
    @Deprecated
	public void insertaMensajeError(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_ERROR);
	}
	
	/**
	 * M�todo para insertar un mensaje de error en el contexto jsf
	 * @param String mensaje
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar.
     */
    @Deprecated
	public void insertaMensajeError(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_ERROR);
	}
	
	
	//Mensajes de Advertencia
	/**
	 * M�todo para insertar mensajes de advertencia en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como par�metro sea nulo o la lista vac�a, no se insertar� ning�n mensaje.
	 * @param String mensaje
	 */
	public void insertaMensajesAdvertencia(List<String> mensajes) {
		if(mensajes != null && !mensajes.isEmpty())
			for(String mensaje : mensajes)
				insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	/**
	 * M�todo para insertar mensajes de advertencia en el contexto jsf actual. Dicho mensaje vendr� dado en dos partes, el mensaje principal y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
	 */
	public void insertaMensajeAdvertencia(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_WARN);
	}
	
	/**
	 * M�todo para insertar un mensaje de advertencia en el contexto jsf
	 * @param String mensaje
	 */
	public void insertaMensajeAdvertencia(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	
	/**
	 * M�todo para insertar un mensaje de advertencia en el contexto jsf
	 * @param String mensaje
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar
     */
    @Deprecated
	public void insertaMensajeErrorGrave(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_FATAL);
	}
	
	
	/**
	 * M�todo para insertar un �nico mensaje en el contexto jsf actual
	 * @param String mensaje principal
	 * @param String mensaje secundario
	 * @param Severity tipo de mensaje que se desea insertar
	 */
	private void insertarMensaje(String sumario, String detalle, Severity severidad){
	    getJsfContext().addMessage(null, new FacesMessage(severidad, sumario, detalle));
	}

	//getter / setter
	public FacesContext getJsfContext() {
		return FacesContext.getCurrentInstance();
	}
	
	
}
