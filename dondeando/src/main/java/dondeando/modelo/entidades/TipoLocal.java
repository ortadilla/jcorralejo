package dondeando.modelo.entidades;

public interface TipoLocal {
	
	public static String ATRIBUTO_DESCRIPCION = "descripcion";
	public static String ATRIBUTO_ID = "id";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);

}
