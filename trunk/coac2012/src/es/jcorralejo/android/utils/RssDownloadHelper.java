package es.jcorralejo.android.utils;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import es.jcorralejo.android.entidades.Agrupacion;

public class RssDownloadHelper {

	public static void updateRssData(String rssUrl, List<Agrupacion> agrupaciones, Map<Date, List<Agrupacion>> calendario, Map<String,List<Agrupacion>> modalidades) {
		try {
			URL url = new URL(rssUrl);

			// Obtenemos el SAXParser
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			
			// Creamos el Handler
			RssHandler rssHandler = new RssHandler(agrupaciones, calendario, modalidades);
			
			// Definimos el manejador l�xico
			saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", rssHandler);
			
			// Obtenemos el Reader
			XMLReader xr = saxParser.getXMLReader();			
			xr.setContentHandler(rssHandler);
			
			// Parseamos el contenido
			InputSource is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			xr.parse(is);
			// El parseo ha concluido
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
