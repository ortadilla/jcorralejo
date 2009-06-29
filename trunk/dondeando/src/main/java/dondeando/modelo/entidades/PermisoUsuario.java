package dondeando.modelo.entidades;

import dondeando.modelo.entidades.TipoUsuario;

public interface PermisoUsuario {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Integer getPermiso();
	public void setPermiso(Integer permiso);
	
	public TipoUsuario getTipoUsuario();
	public void setTipoUsuario(TipoUsuario tipoUsuario);

}
