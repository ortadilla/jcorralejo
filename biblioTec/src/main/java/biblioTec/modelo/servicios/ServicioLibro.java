package biblioTec.modelo.servicios;

import java.util.List;

import biblioTec.modelo.entidades.Libro;

public interface ServicioLibro {

	public List<Libro> encontrarLibrosPorTituloAutorYIsbn(String titulo, String autor, String isbn);
	
	public void actualizarDatosLibro(Libro libro, String titulo, String autor, String isbn, Integer unidadesDisponibles);
	
	public Libro crearLibro(String titulo, String autor, String isbn, Integer unidadesDisponibles);
	
	public void borrarLibro(Libro libro);

	public boolean tienePrestamosPendientes(Libro libro);
}
