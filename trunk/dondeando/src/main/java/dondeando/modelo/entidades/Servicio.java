package dondeando.modelo.entidades;

public interface Servicio {
	
	public static String ATRIBUTO_DESCRIPCION = "descripcion";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
}
