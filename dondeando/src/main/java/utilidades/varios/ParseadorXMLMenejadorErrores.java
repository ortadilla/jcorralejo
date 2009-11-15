package utilidades.varios;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Clase que se encarga de manejar los errores que se producen al parsear un xml mal formado,
 * es decir, que no cumple con su DTD.
 * 
 */
public class ParseadorXMLMenejadorErrores implements ErrorHandler {

	/**
	 * Método que recoge la excepción que se lanza cuando se produce un error de tipo
	 * "error" durante el parseo de un xml.
	 * Los errores de tipo "error" son una violación de las reglas de especificación.
	 * Los resultados son imprevistos. Son los fallos que se lanzan cuando se violan 
	 * las palabras MUST, REQUIRED, MUST NOT, SHALL y SHALL NOT de la especificación
	 * de un DTD. Son errores que pueden ser detectados y corregidos.
	 * (fuente: W3C XML 1.0 Recommendation)
	 * 
	 */
	public void error(SAXParseException exception) throws SAXException {
		throw new SAXException (exception.getMessage());

	}
	
	/**
	 * Método que recoge la excepción que se lanza cuando se produce un error de tipo
	 * "fatal error" durante el parseo de un xml.
	 * Los errores de tipo "fatal error" deben ser detectados y reportados a la aplicación.
	 * Después de encontrar un error, la aplicación puede seguir buscando otros errores.
	 * De acuerdo con lo que se haga para corregir el error, la aplicación puede no procesar
	 * el fichero, ya que el procesado de datos con estos errores puede provocar un 
	 * comportamiento anormal de la aplicación.
	 * (fuente: W3C XML 1.0 Recommendation)
	 * 
	 */
	public void fatalError(SAXParseException exception) throws SAXException {
		throw new SAXException (exception.getMessage());

	}

	/**
	 * Método que recoge la excepción que se lanza cuando se produce un error de tipo
	 * "warning" durante el parseo de un xml.
	 * Los errores de tipo "warning" pueden ser tratados y no provocar un comportamiento
	 * anormal de la aplicación.
	 * Son errores de este tipo los que se producen cuando un elemento del xml no esta definido
	 * en el DTD o cuando un elemento aparece definido varias veces en el DTD.
	 * (fuente: W3C XML 1.0 Recommendation)
	 */
	public void warning(SAXParseException exception) throws SAXException {
		throw new SAXException (exception.getMessage());

	}

}
