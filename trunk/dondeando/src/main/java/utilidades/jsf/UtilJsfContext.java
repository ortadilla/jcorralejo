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
	
	//Mensajes de Información
	
	/**
	 * Método para insertar mensajes de información en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como parámetro sea nulo o la lista vacía, no se insertará ningún mensaje.
	 * @param String mensaje
	 */
	public void insertaMensajesInformacion(List<String> mensajes) {
		if(mensajes != null)
			for(String mensaje : mensajes)
				insertaMensajeInformacion(mensaje);
	}
	
	/**
	 * Método para insertar un mensaje de información en el contexto jsf actual. Dicho mensage vendrá dado en dos partes: el sumario
	 * o mensage principal, y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
	 */
	public void insertaMensajeInformacion(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_INFO);
	}
	
	/**
	 * Método para insertar un mensaje de información en el contexto jsf actual. 
	 */
	public void insertaMensajeInformacion(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_INFO);
	}
	
	
	//Mensajes de Error
	/**
	 * Método para insertar mensajes de error en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como parámetro sea nulo o la lista vacía, no se insertará ningún mensaje.
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
	 * Método para insertar mensajes de error en el contexto jsf actual. Dicho mensaje vendrá dado en dos partes, el mensaje principal y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar
     */
    @Deprecated
	public void insertaMensajeError(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_ERROR);
	}
	
	/**
	 * Método para insertar un mensaje de error en el contexto jsf
	 * @param String mensaje
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar.
     */
    @Deprecated
	public void insertaMensajeError(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_ERROR);
	}
	
	
	//Mensajes de Advertencia
	/**
	 * Método para insertar mensajes de advertencia en el contexto jsf actual. 
	 * En caso que la lista de mensajes pasada como parámetro sea nulo o la lista vacía, no se insertará ningún mensaje.
	 * @param String mensaje
	 */
	public void insertaMensajesAdvertencia(List<String> mensajes) {
		if(mensajes != null && !mensajes.isEmpty())
			for(String mensaje : mensajes)
				insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	/**
	 * Método para insertar mensajes de advertencia en el contexto jsf actual. Dicho mensaje vendrá dado en dos partes, el mensaje principal y el detalle.
	 * @param mensajeSumario
	 * @param mensajeDetalle
	 */
	public void insertaMensajeAdvertencia(String mensajeSumario, String mensajeDetalle){
		insertarMensaje(mensajeSumario, mensajeDetalle, FacesMessage.SEVERITY_WARN);
	}
	
	/**
	 * Método para insertar un mensaje de advertencia en el contexto jsf
	 * @param String mensaje
	 */
	public void insertaMensajeAdvertencia(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	
	/**
	 * Método para insertar un mensaje de advertencia en el contexto jsf
	 * @param String mensaje
     * @deprecated Hay que evitar dar mensajes de ERROR al usuario. Usar Advertencias en su lugar
     */
    @Deprecated
	public void insertaMensajeErrorGrave(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_FATAL);
	}
	
	
	/**
	 * Método para insertar un único mensaje en el contexto jsf actual
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
