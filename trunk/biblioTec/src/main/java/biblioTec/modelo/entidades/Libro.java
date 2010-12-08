package biblioTec.modelo.entidades;

public interface Libro {

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getTitulo();
	public void setTitulo(String titulo);
	
	public String getAutor();
	public void setAutor(String autor);
	
	public String getIsbn();
	public void setIsbn(String isbn);
	
	public Integer getUnidadesDisponibles();
	public void setUnidadesDisponibles(Integer unidadesDisponibles);
	
	
}
