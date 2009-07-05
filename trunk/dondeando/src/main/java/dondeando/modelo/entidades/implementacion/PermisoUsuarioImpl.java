package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.PermisoUsuario;
import dondeando.modelo.entidades.TipoUsuario;

public class PermisoUsuarioImpl implements PermisoUsuario{
	
	private Integer id;
	private Integer version;
	private Integer permiso;
	private TipoUsuario tipoUsuario;
	
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
		if (!(obj instanceof PermisoUsuarioImpl))
			return false;
		final PermisoUsuarioImpl other = (PermisoUsuarioImpl) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
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
	public Integer getPermiso() {
		return permiso;
	}
	public void setPermiso(Integer permiso) {
		this.permiso = permiso;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
