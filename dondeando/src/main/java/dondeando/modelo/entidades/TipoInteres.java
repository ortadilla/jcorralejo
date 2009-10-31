package dondeando.modelo.entidades;

public interface TipoInteres {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public boolean isNecesitaObjeto();
	public void setNecesitaObjeto(boolean necesitaObjeto);
	

}
