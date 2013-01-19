package es.jcorralejo.android.carnavapp.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import android.content.ContentValues;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.entidades.Comentario;
import es.jcorralejo.android.carnavapp.entidades.Componente;
import es.jcorralejo.android.carnavapp.entidades.DiaActuacion;
import es.jcorralejo.android.carnavapp.entidades.Enlace;
import es.jcorralejo.android.carnavapp.entidades.Foto;
import es.jcorralejo.android.carnavapp.entidades.InfoAnio;
import es.jcorralejo.android.carnavapp.entidades.Noticia;
import es.jcorralejo.android.carnavapp.entidades.Puntuacion;
import es.jcorralejo.android.carnavapp.entidades.Video;

public class RssHandler extends DefaultHandler implements LexicalHandler {
	
	public static final String CARNAVAPP = "carnavapp";
	public static final String CALENDARIO = "calendario";
	public static final String AGRUPACION = "agrupacion";
	public static final String COMENTARIO = "comentario";
	public static final String ID = "id";
	public static final String MODALIDAD = "modalidad";
	public static final String NOMBRE = "nombre";
	public static final String AUTOR = "autor";
	public static final String DIRECTOR = "director";
	public static final String LOCALIDAD = "localidad";
	public static final String CABEZA_SERIE = "cabeza_serie";
	public static final String URL_CC = "url_cc";
	public static final String INFO = "info";
	public static final String URL_FOTO = "url_foto";
	public static final String COMPONENTE = "componente";
	public static final String VOZ = "voz";
	public static final String VIDEO = "video";
	public static final String URL = "url";
	public static final String FOTO = "foto";
	public static final String DIA = "dia";
	public static final String FECHA = "fecha";
	public static final String PUESTO = "puesto";
	public static final String NO = "no";
	public static final String DESCRIPCION = "descripcion";
	public static final String ORIGEN = "origen";
	public static final String ENLACE = "enlace";
	public static final String TIPO = "tipo";
	public static final String ANIO = "anio";
	public static final String OTROS_ANIOS = "otros_anios";
	public static final String OTRO_ANIO = "otro_anio";
	public static final String WEB = "web";
	public static final String FASE = "fase";
	public static final String NOTICIA = "noticia";
	public static final String TITULO = "titulo";
	public static final String URLIMAGEN = "urlImagen";
	public static final String URLENLACE = "urlEnlace";
	public static final String PUNTUACION = "puntuacion";
	public static final String PUNTOS = "puntos";
	public static final String ANIOACTUAL = "anioActual";

	private CarnavappApplication app;
	
	// Donde iremos guardando los datos del registro a guardar
	ContentValues rssItem;
	
	Agrupacion agrupacionActual;
	private List<Agrupacion> agrupacionesDiaActual;
	private String diaActual;
	private String faseActual;
	private int anioActual;

	// Flags para saber en que nodo estamos
	private boolean in_agrupacion = false;
	private boolean in_componente = false;
	private boolean in_video = false;
	private boolean in_foto = false;
	private boolean in_dia = false;
	private boolean in_puesto = false;
	private boolean in_otros_anios = false;
	private boolean in_noticia = false;
	private boolean in_puntuacion = false;
	
	
    public RssHandler(CarnavappApplication app) {
		this.app = app;
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
    	if(localName.equalsIgnoreCase(CARNAVAPP)){
    		app.setAnioActual(Integer.valueOf(atts.getValue(ANIOACTUAL)));
    	} else if(localName.equalsIgnoreCase(ANIO)){
    		anioActual = Integer.valueOf(atts.getValue(ANIO));
    		app.getInfoAnios().add(new InfoAnio(anioActual));
    	}else if(localName.equalsIgnoreCase(AGRUPACION)) {
    		in_agrupacion = true;
    		agrupacionActual = new Agrupacion();
    		agrupacionActual.setId(Integer.valueOf(atts.getValue(ID)));
    		agrupacionActual.setModalidad(atts.getValue(MODALIDAD));
    		agrupacionActual.setNombre(atts.getValue(NOMBRE));
    		agrupacionActual.setAutor(atts.getValue(AUTOR));
    		agrupacionActual.setDirector(atts.getValue(DIRECTOR));
    		agrupacionActual.setLocalidad(atts.getValue(LOCALIDAD));
    		agrupacionActual.setCabezaSerie(Boolean.TRUE.toString().equals(atts.getValue(CABEZA_SERIE)));
    		agrupacionActual.setInfo(atts.getValue(INFO));
    		agrupacionActual.setUrl_cc(atts.getValue(URL_CC));
    		agrupacionActual.setUrl_foto(atts.getValue(URL_FOTO));
    		agrupacionActual.setAnio(anioActual);
    		agrupacionActual.setWeb(atts.getValue(WEB));
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
    			agrupacionActual.setVideos(new ArrayList<Video>());
    		agrupacionActual.getVideos().add(new Video(atts.getValue(DESCRIPCION), atts.getValue(URL)));
    	} else if(localName.equalsIgnoreCase(OTRO_ANIO)) {
    		in_otros_anios = true;
    		if(agrupacionActual.getOtrosAnios()==null)
    			agrupacionActual.setOtrosAnios(new ArrayList<Agrupacion>());
    		Agrupacion otroAnio = getAgrupacionPorId(Integer.valueOf(atts.getValue(AGRUPACION)));
    		if(otroAnio!=null)
    			agrupacionActual.getOtrosAnios().add(otroAnio);
    	} else if(localName.equalsIgnoreCase(COMENTARIO)) {
    		if(agrupacionActual.getComentarios()==null)
    			agrupacionActual.setComentarios(new ArrayList<Comentario>());
    		agrupacionActual.getComentarios().add(new Comentario(atts.getValue(ORIGEN), atts.getValue(URL)));
    	} else if(localName.equalsIgnoreCase(FOTO)) {
     		in_foto = true;
     		if(agrupacionActual.getFotos()==null)
     			agrupacionActual.setFotos(new ArrayList<Foto>());
     		agrupacionActual.getFotos().add(new Foto(atts.getValue(DESCRIPCION), atts.getValue(URL)));
     	} else if(localName.equalsIgnoreCase(DIA)) {
     		in_dia = true;
     		agrupacionesDiaActual = new ArrayList<Agrupacion>();
     		diaActual = atts.getValue(FECHA);
     		faseActual = atts.getValue(FASE);
     		InfoAnio infoAnio = app.getInfoAnios().get(app.getInfoAnios().size()-1);
     		if(!infoAnio.getConcurso().getFases().containsKey(faseActual))
     			infoAnio.getConcurso().getFases().put(faseActual, new ArrayList<DiaActuacion>());
     	} else if(localName.equalsIgnoreCase(PUESTO)) {
     		in_puesto = true;
     		int index = Integer.parseInt(atts.getValue(NO));
     		int agrupacion = -1;
     		try{
     			agrupacion = Integer.parseInt(atts.getValue(AGRUPACION));
     		}catch (Exception e) {
     			agrupacion = Integer.MIN_VALUE;
     		}
     		Agrupacion agr = getAgrupacionPorId(agrupacion);
     		if(agr!=null)
     			agrupacionesDiaActual.add(agr);
     	} else if(localName.equalsIgnoreCase(ENLACE)) {
    		Enlace enlace = new Enlace();
    		enlace.setDescripcion(atts.getValue(DESCRIPCION));
    		String tipo = atts.getValue(TIPO);
			enlace.setTipo(tipo);
    		enlace.setUrl(atts.getValue(URL));
    		
    		if(!app.getEnlaces().containsKey(tipo))
    			app.getEnlaces().put(tipo, new ArrayList<Enlace>());
    		app.getEnlaces().get(tipo).add(enlace);
     	}else if(localName.equalsIgnoreCase(NOTICIA)) {
    		in_agrupacion = true;
    		Noticia noticia = new Noticia();
    		noticia.setId(Integer.valueOf(atts.getValue(ID)));
    		noticia.setDescripcion(atts.getValue(DESCRIPCION));
    		noticia.setTitulo(atts.getValue(TITULO));
    		noticia.setFecha(new Date(atts.getValue(FECHA)));
    		noticia.setUrlEnlace(atts.getValue(URLENLACE));
    		noticia.setUrlImagen(atts.getValue(URLIMAGEN));
    		
    		app.getNoticias().add(noticia);
    	} else if(localName.equalsIgnoreCase(PUNTUACION)) {
    		Agrupacion agr = getAgrupacionPorId(Integer.valueOf(atts.getValue(AGRUPACION)));
    		
    		Puntuacion puntuacion = new Puntuacion();
			puntuacion.setAgrupacion(agr);
			puntuacion.setFase(atts.getValue(FASE));
			puntuacion.setPuntos(Float.valueOf(atts.getValue(PUNTOS)));
			
			InfoAnio infoAnio = app.getInfoAnios().get(app.getInfoAnios().size()-1);
			infoAnio.getConcurso().getPuntuaciones().add(puntuacion);
			if(agr.getPuntuaciones()==null)
				agr.setPuntuaciones(new ArrayList<Puntuacion>());
			agr.getPuntuaciones().add(puntuacion);
    	}
    }
    
    private Agrupacion crearAgrupacionDescanso(){
    	Agrupacion descanso = new Agrupacion();
    	descanso.setId(Integer.MIN_VALUE);
    	descanso.setNombre(Constantes.TEXTO_DESCANSO);
    	return descanso;
    }
    
    private Agrupacion getAgrupacionPorId(int id){
    	if(Integer.MIN_VALUE==id)
    		return crearAgrupacionDescanso();
    	
    	for(InfoAnio infoAnio : app.getInfoAnios()){
    		for(Agrupacion a : infoAnio.getConcurso().getAgrupaciones()){
    			if(a.getId()==id)
    				return a;
    		}
    	}
    	return null;
    }
    
    /**
     * Llamado cuando se cierra el tag:
     * </tag> 
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	
    	InfoAnio infoAnio = app.getInfoAnios().get(app.getInfoAnios().size()-1);
    	if(localName.equalsIgnoreCase(AGRUPACION)) {
    		in_agrupacion = false;
    		infoAnio.getConcurso().getAgrupaciones().add(agrupacionActual);
    		infoAnio.getConcurso().getModalidades().get(agrupacionActual.getModalidad()).add(agrupacionActual);
    		agrupacionActual = null;
    	} else if(localName.equalsIgnoreCase(COMPONENTE)) {
    		in_componente = false;
    	} else if(localName.equalsIgnoreCase(VIDEO)) {
    		in_video = false;
    	} else if(localName.equalsIgnoreCase(FOTO)) {
     		in_foto = false;
     	} else if(localName.equalsIgnoreCase(DIA)) {
     		in_dia = false;
     		infoAnio.getConcurso().getFases().get(faseActual).add(new DiaActuacion(diaActual, new ArrayList<Agrupacion>(agrupacionesDiaActual)));
     		infoAnio.getConcurso().getDias().put(diaActual, new ArrayList<Agrupacion>(agrupacionesDiaActual));
     		diaActual = null;
     		faseActual = null;
     		agrupacionesDiaActual.clear();
     	} else if(localName.equalsIgnoreCase(PUESTO)) {
     		in_puesto = false;
     	}
    }
    
    /** 
     * Se llama cuando estamos dentro de un tag:
     * <tag>characters</tag> 
     */
	@Override
	public void characters(char ch[], int start, int length) {
	}



	public void endCDATA() throws SAXException {
//		in_CDATA = false;
	}
	public void startCDATA() throws SAXException {
//		in_CDATA = true;
	}

	public void comment(char[] ch, int start, int length) throws SAXException {}

	public void endDTD() throws SAXException {}

	public void endEntity(String name) throws SAXException {}

	public void startDTD(String name, String publicId, String systemId) throws SAXException {}

	public void startEntity(String name) throws SAXException {}
}
