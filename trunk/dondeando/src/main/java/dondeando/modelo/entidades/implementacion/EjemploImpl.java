package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.Ejemplo;

public class EjemploImpl implements Ejemplo {

	private Integer id;
	private Integer version;
	private String nombre;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
