package utilidades.varios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class GeosStringConverter implements Converter {
	
	public static final String CONVERTER_ID = "com.hp.geos.core.utilidades.GeosStringConverter";
	
	public GeosStringConverter() {}
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		
		if (context == null) throw new NullPointerException("facesContext");
		if (component == null) throw new NullPointerException("uiComponent");

		if (value != null) {
			value = value.trim();
			if (value.length() > 0)
				return value.trim();
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		if (context == null) throw new NullPointerException("facesContext");
        if (component == null) throw new NullPointerException("uiComponent");
        
        if (value instanceof String)
            return (String)value;
        else
        	return "";
	}

}
