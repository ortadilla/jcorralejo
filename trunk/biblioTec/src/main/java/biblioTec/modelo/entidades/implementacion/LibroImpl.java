package biblioTec.modelo.entidades.implementacion;

import java.util.Set;

import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;

public class LibroImpl implements Libro {

	private Integer id;
	private Integer version;
	private String titulo;
	private String autor;
	private String isbn;
	private Integer unidadesDisponibles;
	private Set<Prestamo> prestamos;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if ( !(obj instanceof LibroImpl) ) return false;
		
		final LibroImpl other = (LibroImpl) obj;
		if (id == null) {
			if (other.getId() != null) return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getUnidadesDisponibles() {
		return unidadesDisponibles;
	}
	public void setUnidadesDisponibles(Integer unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}
	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
	
	
}
