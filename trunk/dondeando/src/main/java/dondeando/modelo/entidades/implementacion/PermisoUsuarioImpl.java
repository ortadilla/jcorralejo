package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.PermisoUsuario;
import dondeando.modelo.entidades.TipoUsuario;

public class PermisoUsuarioImpl implements PermisoUsuario{
	
	private Integer Id;
	private Integer version;
	private Integer permiso;
	private TipoUsuario tipoUsuario;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
