package utilidades.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import utilidades.varios.MensajesCore;


public class ConversorBooleanoCadena implements Converter{

	//Para guardarlo
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		
		Boolean valor=new Boolean(value);
		return valor?MensajesCore.instancia().obtenerTexto("SI"):utilidades.varios.MensajesCore.instancia().obtenerTexto("NO");
	}

	//Para mostrarlo
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		if (arg2==null) return null;
		
		String valor= arg2.toString();
		return valor.equals(utilidades.varios.MensajesCore.instancia().obtenerTexto("SI"))?Boolean.TRUE.toString():Boolean.FALSE.toString();
	}

}
