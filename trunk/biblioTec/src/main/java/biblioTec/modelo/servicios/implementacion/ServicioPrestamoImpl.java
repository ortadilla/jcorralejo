package biblioTec.modelo.servicios.implementacion;

import static biblioTec.utilidades.NombresBean.PRESTAMO_DAO;
import static biblioTec.utilidades.NombresBean.SERVICIO_PRESTAMO;

import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PrestamoDAO;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.PrestamoImpl;
import biblioTec.modelo.servicios.ServicioPrestamo;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_PRESTAMO)
public class ServicioPrestamoImpl implements ServicioPrestamo{

    @In(value=PRESTAMO_DAO, create=true) 
    private PrestamoDAO prestamoDAO;
    
	public List<Prestamo> encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin) {
		return prestamoDAO.encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(usuario, libro, fechaInicio, fechaFin);
	}
	
	public void actualizarDatosPrestamo(Prestamo prestamo, Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin) {
		if(prestamo!=null){
			prestamo.setUsuario(usuario);
			prestamo.setLibro(libro);
			prestamo.setFechaFin(fechaFin);
			prestamo.setFechaInicio(fechaInicio);
			
			prestamoDAO.flushear();
		}
	}
	
	public Prestamo crearPrestamo(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin) {
		Prestamo prestamo = new PrestamoImpl();
		
		prestamo.setUsuario(usuario);
		prestamo.setLibro(libro);
		prestamo.setFechaFin(fechaFin);
		prestamo.setFechaInicio(fechaInicio);
		
		prestamoDAO.guadar(prestamo);
		prestamoDAO.flushear();
		
		return prestamo;
	}
	
	public void borrarPrestamo(Prestamo prestamo) {
		prestamoDAO.borrar(prestamo);
		prestamoDAO.flushear();
	}
	
	public void devolverLibro(Prestamo prestamo) {
		prestamo.setDevuelto(true);
		prestamoDAO.flushear();
	}
	
}
