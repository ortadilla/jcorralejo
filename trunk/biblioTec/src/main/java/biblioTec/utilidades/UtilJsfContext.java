package biblioTec.utilidades;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name(NombresBean.UTIL_JSF_CONTEXT)
@Scope(ScopeType.EVENT)
public class UtilJsfContext {
    
	public void insertaMensajes(List<String> mensajes){
		if (mensajes != null)
    		for(String mensaje : mensajes)
    		    insertaMensaje(mensaje);
	}
	
	public void insertaMensaje(String mensaje){
		if (mensaje != null)
		    insertarMensaje(mensaje, null, FacesMessage.SEVERITY_INFO);
	}	
	
	public void insertaMensajesInformacion(List<String> mensajes) {
		if(mensajes != null)
			for(String mensaje : mensajes)
				insertaMensajeInformacion(mensaje);
	}
	
	public void insertaMensajeInformacion(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_INFO);
	}
	
	public void insertaMensajesAdvertencia(List<String> mensajes) {
		if(mensajes != null && !mensajes.isEmpty())
			for(String mensaje : mensajes)
				insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	public void insertaMensajeAdvertencia(String mensaje){
		insertarMensaje(mensaje, null, FacesMessage.SEVERITY_WARN);
	}
	
	private void insertarMensaje(String sumario, String detalle, Severity severidad){
	    getJsfContext().addMessage(null, new FacesMessage(severidad, sumario, detalle));
	}

	public FacesContext getJsfContext() {
		return FacesContext.getCurrentInstance();
	}
	
	
}
