package dondeando.modelo.entidades;

public interface ImagenLocal {

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public Local getLocal();
	public void setLocal(Local local);
	
	public Imagen getImagen();
	public void setImagen(Imagen imagen);
}
