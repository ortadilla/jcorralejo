package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.TipoUsuario;

public class TipoUsuarioImpl implements TipoUsuario {

	private Integer id;
	private Integer version;
	private String descripcion;
	private boolean activo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
//		if (getClass() != obj.getClass()) return false;
		if ( !(obj instanceof TipoUsuarioImpl) ) return false;
		
		final TipoUsuarioImpl other = (TipoUsuarioImpl) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
