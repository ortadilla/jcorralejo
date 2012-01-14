package es.jcorralejo.android.entidades;

import java.io.Serializable;

public class Video implements Serializable{
	private static final long serialVersionUID = -8533270663571629186L;
	private String descripcion;
	private String url;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Video(String descripcion, String url) {
		super();
		this.descripcion = descripcion;
		this.url = url;
	}
}
