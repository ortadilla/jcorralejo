package biblioTec.modelo.entidades;

public interface PermisoPerfil {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Perfil getPerfil();
	public void setPerfil(Perfil perfil);
	
	public Integer getPermiso();
	public void setPermiso(Integer permiso);

}
