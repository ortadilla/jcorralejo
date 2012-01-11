package es.jcorralejo.android.utils;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import android.content.ContentValues;
import android.util.Log;

public class RssHandler extends DefaultHandler implements LexicalHandler {
	
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String LINK = "link";
	public static final String COMMENTS = "comments";
	public static final String PUB_DATE = "pub_date";
	public static final String CREATOR = "creator";
	public static final String DESCRIPTION = "description";
	
	// Donde iremos guardando los datos del registro a guardar
	ContentValues rssItem;

	// Flags para saber en que nodo estamos
	private boolean in_item = false;
	private boolean in_title = false;
	private boolean in_link = false;
	private boolean in_pubDate = false;
	private boolean in_description = false;

    /** 
     * Se llama cuando se abre un tag:
     * <tag>
     * Puede recibir atributos cuando el xml es del estilo:
     * <tag attribute="attributeValue">
     * 
     **/
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    	// Nos vamos a centrar solo en los items
    	if(localName.equalsIgnoreCase("item")) {
    		in_item = true;
    		rssItem = new ContentValues();
    	} else if(localName.equalsIgnoreCase("title")) {
    		in_title = true;
    	} else if(localName.equalsIgnoreCase("link")) {
    		in_link = true;
    	} else if(localName.equalsIgnoreCase("comments")){
//    		in_comments = true;
    	} else if(localName.equalsIgnoreCase("pubDate")) {
    		in_pubDate = true;
    	} else if(localName.equalsIgnoreCase("dc:creator")) {
//    		in_dcCreator = true;
    	} else if(localName.equalsIgnoreCase("description")) {
    		in_description = true;
    	}
    }
    
    /**
     * Llamado cuando se cierra el tag:
     * </tag> 
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
                    throws SAXException {
    	
    	// Nos vamos a centrar solo en los items
    	if(localName.equalsIgnoreCase("item")) {

			Log.d("guardado item", ""+rssItem);
    		   		
			//Guardar información
			
    		rssItem = new ContentValues();
    		
    		in_item = false;
 
    	} else if(localName.equalsIgnoreCase("title")) {
    		in_title = false;
    	} else if(localName.equalsIgnoreCase("link")) {
    		in_link = false;
    	} else if(localName.equalsIgnoreCase("comments")){
//    		in_comments = false;
    	} else if(localName.equalsIgnoreCase("pubDate")) {
    		in_pubDate = false;
    	} else if(localName.equalsIgnoreCase("dc:creator")) {
//    		in_dcCreator = false;
    	} else if(localName.equalsIgnoreCase("description")) {
    		in_description = false;
    	}
    }
    
    /** 
     * Se llama cuando estamos dentro de un tag:
     * <tag>characters</tag> 
     */
	@Override
	public void characters(char ch[], int start, int length) {
		if (in_item) { // Estamos dentro de un item
			if (in_title) {
				rssItem.put(TITLE, new String(ch, start, length));
			} else if (in_link) {
				rssItem.put(LINK, new String(ch, start, length));
			} else if (in_description) {
				rssItem.put(DESCRIPTION, new String(ch, start,
						length));
			} else if (in_pubDate) {
				String strDate = new String(ch, start, length);
				try {
					long fecha = Date.parse(strDate);
					rssItem.put(PUB_DATE, fecha);
				} catch (Exception e) {
					Log.d("RssHandler", "Error al parsear la fecha");
				}
			}

		}
	}



	@Override
	public void endCDATA() throws SAXException {
//		in_CDATA = false;
	}
	@Override
	public void startCDATA() throws SAXException {
//		in_CDATA = true;
	}

	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {}

	@Override
	public void endDTD() throws SAXException {}

	@Override
	public void endEntity(String name) throws SAXException {}

	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {}

	@Override
	public void startEntity(String name) throws SAXException {}
}
