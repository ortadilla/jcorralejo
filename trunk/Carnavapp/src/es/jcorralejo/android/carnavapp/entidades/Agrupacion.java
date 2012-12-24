package es.jcorralejo.android.carnavapp.entidades;


import java.io.Serializable;
import java.util.List;

public class Agrupacion implements Serializable{
	
	private static final long serialVersionUID = -59448126852170073L;
	
	private int id;
	private String modalidad;
	private String nombre;
	private String autor;
	private String director;
	private String localidad;
	private boolean cabezaSerie;
	private String info;
	private String url_cc;
	private String url_foto;
	private int anio;
	private String web;
	private List<Foto> fotos;
	private List<Video> videos;
	private List<Componente> componentes;
	private List<Comentario> comentarios;
	private List<Agrupacion> otrosAnios;
	private List<Puntuacion> puntuaciones;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public boolean isCabezaSerie() {
		return cabezaSerie;
	}
	public void setCabezaSerie(boolean cabezaSerie) {
		this.cabezaSerie = cabezaSerie;
	}
	public String getUrl_cc() {
		return url_cc;
	}
	public void setUrl_cc(String url_cc) {
		this.url_cc = url_cc;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	public List<Componente> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public List<Agrupacion> getOtrosAnios() {
		return otrosAnios;
	}
	public void setOtrosAnios(List<Agrupacion> otrosAnios) {
		this.otrosAnios = otrosAnios;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}
	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

}
