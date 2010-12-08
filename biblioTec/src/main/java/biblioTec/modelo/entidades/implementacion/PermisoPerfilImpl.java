package biblioTec.modelo.entidades.implementacion;

import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.PermisoPerfil;

public class PermisoPerfilImpl implements PermisoPerfil {

	private Integer id;
	private Integer version;
	private Perfil perfil;
	private Integer permiso;
	
	
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
		if ( !(obj instanceof PermisoPerfilImpl) ) return false;
		
		final PermisoPerfilImpl other = (PermisoPerfilImpl) obj;
		if (id == null) {
			if (other.getId() != null) return false;
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
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Integer getPermiso() {
		return permiso;
	}
	public void setPermiso(Integer permiso) {
		this.permiso = permiso;
	}
}
