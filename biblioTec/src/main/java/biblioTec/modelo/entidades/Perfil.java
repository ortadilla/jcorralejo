package biblioTec.modelo.entidades;

public interface Perfil {
	
	public static final String ATRIBUTO_DESCRIPCION = "descripcion";
	public static final String ATRIBUTO_ID = "id";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);

}
