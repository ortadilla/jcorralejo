package es.jcorralejo.android.callejeras2012.utils;

import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;
import es.jcorralejo.android.callejeras2012.entidades.Lugar;


public class RssDownloadHelper {

	public static void updateRssData(String rssUrl, List<Agrupacion> agrupaciones, List<Lugar> puntosInteres) {
		try {
			URL url = new URL(rssUrl);

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			RssHandler rssHandler = new RssHandler(agrupaciones, puntosInteres);
			saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", rssHandler);
			XMLReader xr = saxParser.getXMLReader();			
			xr.setContentHandler(rssHandler);
			
			InputSource is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			
			//Una vez obtenido el archivo y antes de parsear, limpiamos las variables
			agrupaciones.clear();
			puntosInteres.clear();
			xr.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
