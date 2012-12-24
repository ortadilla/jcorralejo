package es.jcorralejo.android.carnavapp.utils;


import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import es.jcorralejo.android.carnavapp.app.CarnavappApplication;


public class RssDownloadHelper {

	public static void updateRssData(String rssUrl, CarnavappApplication app) {
		try {
			URL url = new URL(rssUrl);

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			RssHandler rssHandler = new RssHandler(app);
			saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", rssHandler);
			XMLReader xr = saxParser.getXMLReader();			
			xr.setContentHandler(rssHandler);
			
			InputSource is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			
			//Una vez obtenido el archivo y antes de parsear, limpiamos las variables
			app.getInfoAnios().clear();
			app.getNoticias().clear();
			app.getEnlaces().clear();
			
			xr.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
