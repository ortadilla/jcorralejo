package es.jcorralejo.android.entidades;

public class Video {
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
