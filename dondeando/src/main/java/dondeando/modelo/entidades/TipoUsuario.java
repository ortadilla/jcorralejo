package dondeando.modelo.entidades;

public interface TipoUsuario {

	public static String ID_TIPO_USUARIO_ANONIMO = "2";
	public static String ID_TIPO_USUARIO_REGISTRADO = "3";
	public static String ATRIBUTO_DESCRIPCION = "descripcion";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public boolean isActivo();
	public void setActivo(boolean activo);
	
}
