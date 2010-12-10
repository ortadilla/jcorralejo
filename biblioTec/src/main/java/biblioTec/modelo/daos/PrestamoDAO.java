package biblioTec.modelo.daos;

import java.util.Date;
import java.util.List;

import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;

public interface PrestamoDAO {
	
	public List<Prestamo> encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin);
	
	public void flushear();
	
	public void guadar(Prestamo prestamo);
	
	public void borrar(Prestamo prestamo);

}
