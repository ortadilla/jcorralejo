package biblioTec.modelo.servicios;

import java.util.Date;
import java.util.List;

import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;

public interface ServicioPrestamo {
	
	public List<Prestamo> encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin);
	
	public void actualizarDatosPrestamo(Prestamo prestamo, Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin);
	
	public Prestamo crearPrestamo(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin);
	
	public void borrarPrestamo(Prestamo prestamo);
	
	public void devolverLibro(Prestamo prestamo);

}
