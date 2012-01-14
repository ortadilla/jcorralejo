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

	public static void updateRssData(String rssUrl, List<Agrupacion> agrupaciones, Map<String, List<Agrupacion>> calendario, Map<String,List<Agrupacion>> modalidades) {
		try {
			URL url = new URL(rssUrl);

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			RssHandler rssHandler = new RssHandler(agrupaciones, calendario, modalidades);
			saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", rssHandler);
			XMLReader xr = saxParser.getXMLReader();			
			xr.setContentHandler(rssHandler);
			
			InputSource is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			
			//Una vez obtenido el archivo y antes de parsear, limpiamos las variables
			agrupaciones.clear();
			calendario.clear();
			modalidades.get(Constantes.MODALIDAD_CHIRIGOTA).clear();
			modalidades.get(Constantes.MODALIDAD_COMPARSA).clear();
			modalidades.get(Constantes.MODALIDAD_CUARTETO).clear();
			modalidades.get(Constantes.MODALIDAD_CORO).clear();
			
			xr.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
