package dondeando.modelo.entidades;

public interface TipoInteres {
	
	public final static String ATRIBUTO_DESCRIPCION = "descripcion";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public boolean isNecesitaObjeto();
	public void setNecesitaObjeto(boolean necesitaObjeto);
	

}
