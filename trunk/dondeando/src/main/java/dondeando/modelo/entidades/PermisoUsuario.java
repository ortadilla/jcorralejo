package dondeando.modelo.entidades;

import dondeando.modelo.entidades.TipoUsuario;

public interface PermisoUsuario {
	
	//Constantes
	public static final String ATRIBUTO_PERMISO = "permiso";
	public static final String ATRIBUTO_TIPO_USUARIO = "tipoUsuario";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Integer getPermiso();
	public void setPermiso(Integer permiso);
	
	public TipoUsuario getTipoUsuario();
	public void setTipoUsuario(TipoUsuario tipoUsuario);

}
