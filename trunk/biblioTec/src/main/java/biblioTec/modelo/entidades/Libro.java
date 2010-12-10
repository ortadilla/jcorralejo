package biblioTec.modelo.entidades;

import java.util.Set;

public interface Libro {
	
	public static final String ATRIBUTO_TITULO = "titulo";
	public static final String ATRIBUTO_AUTOR = "autor";
	public static final String ATRIBUTO_ISBN = "isbn";

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
	
	public Set<Prestamo> getPrestamos();
	public void setPrestamos(Set<Prestamo> prestamos);
	
	
}
