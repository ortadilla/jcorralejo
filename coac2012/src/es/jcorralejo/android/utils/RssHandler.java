package es.jcorralejo.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import android.content.ContentValues;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.entidades.Componente;

public class RssHandler extends DefaultHandler implements LexicalHandler {
	
	public static final String AGRUPACION = "agrupacion";
	public static final String ID = "id";
	public static final String MODALIDAD = "modalidad";
	public static final String NOMBRE = "nombre";
	public static final String AUTOR = "autor";
	public static final String DIRECTOR = "director";
	public static final String LOCALIDAD = "localidad";
	public static final String COAC2011 = "coac2011";
	public static final String CABEZA_SERIE = "cabeza_serie";
	public static final String URL_CC = "url_cc";
	public static final String URL_FOTO = "url_foto";
	public static final String URL_VIDEOS = "url_videos";
	public static final String COMPONENTE = "componente";
	public static final String VOZ = "voz";
	public static final String VIDEO = "video";
	public static final String URL = "url";
	public static final String FOTO = "foto";
	public static final String DIA = "dia";
	public static final String FECHA = "fecha";
	public static final String PUESTO = "puesto";
	public static final String NO = "no";
	public static final String GRUPO = "grupo";

	public static final String TITLE = "title";
	public static final String LINK = "link";
	public static final String COMMENTS = "comments";
	public static final String PUB_DATE = "pub_date";
	public static final String CREATOR = "creator";
	public static final String DESCRIPTION = "description";
	
	private List<Agrupacion> agrupaciones;
	private Map<Date, List<Agrupacion>> calendario;
	private Map<String,List<Agrupacion>> modalidades;
	
	// Donde iremos guardando los datos del registro a guardar
	ContentValues rssItem;
	
	Agrupacion agrupacionActual;
	private List<Agrupacion> agrupacionesDiaActual;
	private Date diaActual;

	// Flags para saber en que nodo estamos
	private boolean in_agrupacion = false;
	private boolean in_componente = false;
	private boolean in_video = false;
	private boolean in_foto = false;
	private boolean in_dia = false;
	private boolean in_puesto = false;
	
	
    public RssHandler(List<Agrupacion> agrupaciones, Map<Date, List<Agrupacion>> calendario, Map<String,List<Agrupacion>> modalidades) {
		this.agrupaciones = agrupaciones;
		this.calendario = calendario;
		this.modalidades = modalidades;
	}

    /** 
     * Se llama cuando se abre un tag:
     * <tag>
     * Puede recibir atributos cuando el xml es del estilo:
     * <tag attribute="attributeValue">
     * 
     **/
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    	if(localName.equalsIgnoreCase(AGRUPACION)) {
    		in_agrupacion = true;
    		agrupacionActual = new Agrupacion();
    		agrupacionActual.setId(Integer.valueOf(atts.getValue(ID)));
    		agrupacionActual.setModalidad(atts.getValue(MODALIDAD));
    		agrupacionActual.setNombre(atts.getValue(NOMBRE));
    		agrupacionActual.setAutor(atts.getValue(AUTOR));
    		agrupacionActual.setDirector(atts.getValue(DIRECTOR));
    		agrupacionActual.setLocalidad(atts.getValue(LOCALIDAD));
    		agrupacionActual.setCoac2011(atts.getValue(COAC2011));
    		agrupacionActual.setCabezaSerie(Boolean.TRUE.toString().equals(atts.getValue(CABEZA_SERIE)));
    		agrupacionActual.setUrl_cc(atts.getValue(URL_CC));
    		agrupacionActual.setUrl_foto(atts.getValue(URL_FOTO));
    		agrupacionActual.setUrl_videos(atts.getValue(URL_VIDEOS));
    	} else if(localName.equalsIgnoreCase(COMPONENTE)) {
    		in_componente = true;
    		Componente componente = new Componente();
    		componente.setNombre(atts.getValue(NOMBRE));
    		componente.setVoz(atts.getValue(VOZ));
    		if(agrupacionActual.getComponentes()==null)
    			agrupacionActual.setComponentes(new ArrayList<Componente>());
    		agrupacionActual.getComponentes().add(componente);
    	} else if(localName.equalsIgnoreCase(VIDEO)) {
    		in_video = true;
    		if(agrupacionActual.getVideos()==null)
    			agrupacionActual.setVideos(new ArrayList<String>());
    		agrupacionActual.getVideos().add(atts.getValue(URL));
    	} else if(localName.equalsIgnoreCase(FOTO)) {
     		in_foto = true;
     		if(agrupacionActual.getFotos()==null)
     			agrupacionActual.setFotos(new ArrayList<String>());
     		agrupacionActual.getFotos().add(atts.getValue(URL));
     	} else if(localName.equalsIgnoreCase(DIA)) {
     		in_dia = true;
     		if(calendario==null)
     			calendario = new HashMap<Date, List<Agrupacion>>();
     		agrupacionesDiaActual = new ArrayList<Agrupacion>();
     		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
     		try {
				diaActual = sdf.parse(atts.getValue(FECHA));
			} catch (ParseException e) {
				new RuntimeException("ERROR AL OBTENER EL CALENDARIO");
			}
     		if(!calendario.containsKey(diaActual))
     			calendario.put(diaActual, new ArrayList<Agrupacion>());
     	} else if(localName.equalsIgnoreCase(PUESTO)) {
     		in_puesto = true;
     		int index = Integer.parseInt(atts.getValue(NO));
     		int agrupacion = Integer.parseInt(atts.getValue(GRUPO));
     		agrupacionesDiaActual.add(index, getAgrupacionPorId(agrupacion));
     	}
    }
    
    private Agrupacion getAgrupacionPorId(int id){
    	for(Agrupacion a : agrupaciones){
    		if(a.getId()==id)
    			return a;
    	}
    	return null;
    }
    
    /**
     * Llamado cuando se cierra el tag:
     * </tag> 
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	
    	if(localName.equalsIgnoreCase(AGRUPACION)) {
    		in_agrupacion = false;
    		if(agrupaciones==null)
    			agrupaciones = new ArrayList<Agrupacion>();
    		agrupaciones.add(agrupacionActual);
    		if(modalidades==null)
    			modalidades = new HashMap<String, List<Agrupacion>>();
    		if(!modalidades.containsKey(agrupacionActual.getModalidad()))
    			modalidades.put(agrupacionActual.getModalidad(), new ArrayList<Agrupacion>());
    		modalidades.get(agrupacionActual.getModalidad()).add(agrupacionActual);
    		agrupacionActual = null;
    	} else if(localName.equalsIgnoreCase(COMPONENTE)) {
    		in_componente = false;
    	} else if(localName.equalsIgnoreCase(VIDEO)) {
    		in_video = false;
    	} else if(localName.equalsIgnoreCase(FOTO)) {
     		in_foto = false;
     	} else if(localName.equalsIgnoreCase(DIA)) {
     		in_dia = false;
     		diaActual = null;
     	} else if(localName.equalsIgnoreCase(PUESTO)) {
     		in_puesto = false;
     		calendario.get(diaActual).addAll(agrupacionesDiaActual);
     		agrupacionesDiaActual.clear();
     	}
    }
    
    /** 
     * Se llama cuando estamos dentro de un tag:
     * <tag>characters</tag> 
     */
	@Override
	public void characters(char ch[], int start, int length) {
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
