package biblioTec.modelo.servicios.implementacion;

import static biblioTec.utilidades.NombresBean.LIBRO_DAO;
import static biblioTec.utilidades.NombresBean.SERVICIO_LIBRO;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.LibroDAO;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.implementacion.LibroImpl;
import biblioTec.modelo.servicios.ServicioLibro;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_LIBRO)
public class ServicioLibroImpl implements ServicioLibro {
	
    
    @In(value=LIBRO_DAO, create=true) 
    private LibroDAO libroDAO;
    
	
	public List<Libro> encontrarLibrosPorTituloAutorYIsbn(String titulo, String autor, String isbn) {
		return libroDAO.encontrarLibrosPorTituloAutorYIsbn(titulo, autor, isbn);
	}
	
    public void actualizarDatosLibro(Libro libro, String titulo, String autor, String isbn, Integer unidadesDisponibles) {
    	if(libro!=null){
    		libro.setTitulo(titulo);
    		libro.setAutor(autor);
    		libro.setIsbn(isbn);
    		libro.setUnidadesDisponibles(unidadesDisponibles);
    		
    		libroDAO.flushear();
    	}
    }
    
    public Libro crearLibro(String titulo, String autor, String isbn, Integer unidadesDisponibles) {
    	Libro libro = new LibroImpl();
    	
    	libro.setTitulo(titulo);
    	libro.setAutor(autor);
    	libro.setIsbn(isbn);
    	libro.setUnidadesDisponibles(unidadesDisponibles);
    	
    	libroDAO.guadar(libro);
    	libroDAO.flushear();
    	
    	return libro;
    }
    
    public void borrarLibro(Libro libro) {
    	libroDAO.borrar(libro);
    	libroDAO.flushear();
    }
    
	public boolean tienePrestamosPendientes(Libro libro){
		boolean hay = false;
		if(libro.getPrestamos()!=null){
			for(Prestamo prestamo : libro.getPrestamos()){
				if(!prestamo.isDevuelto()){
					hay = true;
					break;
				}
			}
		}
		return hay;
	}
	
	public boolean tieneUnidadesLibres(Libro libro) {
		return !libro.getUnidadesDisponibles().equals(0) && !((LibroImpl)libro).getUnidadesPrestamo().equals(0);
	}

}
