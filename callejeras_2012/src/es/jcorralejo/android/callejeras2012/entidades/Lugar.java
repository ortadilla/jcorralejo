package es.jcorralejo.android.callejeras2012.entidades;

import java.io.Serializable;
import java.util.Date;

public class Lugar  implements Serializable {
	private static final long serialVersionUID = 8976801267922925720L;
	
	private int id;
	private double latitud;
	private double longitud;
	private Agrupacion agrupacion;
	private String descripcion;
	private String nombre;
	private Date ultimaActualizacion;
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public Agrupacion getAgrupacion() {
		return agrupacion;
	}
	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
