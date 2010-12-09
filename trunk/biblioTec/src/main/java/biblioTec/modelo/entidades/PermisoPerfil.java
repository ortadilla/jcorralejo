package biblioTec.modelo.entidades;

public interface PermisoPerfil {
	
	public static final String ATRIBUTO_PERMISO = "permiso";
	public static final String ATRIBUTO_PERFIL = "perfil";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Perfil getPerfil();
	public void setPerfil(Perfil perfil);
	
	public Integer getPermiso();
	public void setPermiso(Integer permiso);

}
