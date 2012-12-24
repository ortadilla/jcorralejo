package es.jcorralejo.android.carnavapp.entidades;

import java.util.Date;

public class Noticia {
	
	private Integer id;
	private String titulo;
	private String descripcion;
	private String urlImagen;
	private String urlEnlace;
	private Date fecha;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public String getUrlEnlace() {
		return urlEnlace;
	}
	public void setUrlEnlace(String urlEnlace) {
		this.urlEnlace = urlEnlace;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
