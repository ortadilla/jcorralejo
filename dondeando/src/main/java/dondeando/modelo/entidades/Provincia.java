package dondeando.modelo.entidades;

public interface Provincia {
	
	public static final String ATRIBUTO_NOMBRE = "nombre";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getNombre();
	public void setNombre(String nombre);
	
}
