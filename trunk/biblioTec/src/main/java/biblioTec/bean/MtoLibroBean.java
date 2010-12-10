package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LANZAR_BUSQUEDA;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.VOLVER_A;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_LIBROS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_LIBRO;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.MTO_LIBRO_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_LIBRO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.servicios.ServicioLibro;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.UtilJsfContext;



@Scope(CONVERSATION)
@Name(MTO_LIBRO_BEAN)
public class MtoLibroBean {
	
	private String titulo;
	private String autor;
	private String isbn;
	private Integer unidadesDisponibles;
	private boolean detalles;
	private Libro libroEdicion;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=SERVICIO_LIBRO, create=true)
	private ServicioLibro servicioLibro;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
    @Create
	@Begin(join=true)
	public void inicializar(){
	}
    
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null){
			Libro libro = null;
			if(mapaArgumentos.contiene(LIBRO)){
				libro = (Libro) mapaArgumentos.getArgumento(LIBRO); 
				autor = libro.getAutor();
				titulo = libro.getTitulo();
				isbn = libro.getIsbn();
				unidadesDisponibles = libro.getUnidadesDisponibles();
			}
			
			if(mapaArgumentos.contiene(ACCION)){
				String accion = (String)mapaArgumentos.getArgumento(ACCION);
				if(ACCION_ANIADIR_LIBRO.equals(accion)){
					detalles = false;
					unidadesDisponibles = 1;
				}
				else if (ACCION_EDITAR_LIBRO.equals(accion)){
					detalles = false;
					libroEdicion = libro; 
				}
				else if (ACCION_DETALLES_LIBRO.equals(accion)){
					detalles = true;
					libroEdicion = libro;
				}
			}
		}
	}
	
	public String aceptar(){

		String outcome = "";
		
		if(titulo==null || "".equals(titulo)
		|| autor==null || "".equals(autor)
		|| isbn==null || "".equals(isbn)
		|| unidadesDisponibles==null){
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("DATOS_LIBRO_OBLIGATORIOS"));
		}else{
			if(libroEdicion!=null){
				servicioLibro.actualizarDatosLibro(libroEdicion, titulo, autor, isbn, unidadesDisponibles);
			}else{
				servicioLibro.crearLibro(titulo, autor, isbn, unidadesDisponibles);
				limpiarFormulario();
			}
			outcome = GESTIONAR_LIBROS;
		}
		return outcome;
	}
	
	public String cancelar(){
		limpiarFormulario();
		return GESTIONAR_LIBROS;
	}
	
	public String volver(){
		limpiarFormulario();
		return GESTIONAR_LIBROS;
	}
	
	private void limpiarFormulario(){
		titulo = null;
		autor = null;
		isbn = null;
		unidadesDisponibles = null;
	}
	
	public String verPrestamos(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(LANZAR_BUSQUEDA, true);
		mapaArgumentos.setArgumento(LIBRO, libroEdicion);
		mapaArgumentos.setArgumento(VOLVER_A, MTO_LIBRO);
		return GESTIONAR_PRESTAMOS;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isDetalles() {
		return detalles;
	}

	public void setDetalles(boolean detalles) {
		this.detalles = detalles;
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

	public Libro getLibroEdicion() {
		return libroEdicion;
	}

	public void setLibroEdicion(Libro libroEdicion) {
		this.libroEdicion = libroEdicion;
	}


}
