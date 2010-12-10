package biblioTec.modelo.daos;

import java.util.List;

import biblioTec.modelo.entidades.Libro;

public interface LibroDAO {
	
	public List<Libro> encontrarLibrosPorTituloAutorYIsbn(String titulo, String autor, String isbn);
	
	public void flushear();
	
	public void guadar(Libro libro);
	
	public void borrar(Libro libro);


}
