package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.COMPROBAR_DATO_A_DEVOLVER;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.DEVOLVER_OBJETO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.OBJETO_DEVUELTO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.PRESTAMO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.VOLVER_A;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_LIBROS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_USUARIOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_PRESTAMO;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.MTO_PRESTAMO_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_PRESTAMO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.Date;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioPrestamo;
import biblioTec.utilidades.ConstantesArgumentosNavegacion;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.UtilJsfContext;



@Scope(CONVERSATION)
@Name(MTO_PRESTAMO_BEAN)
public class MtoPrestamoBean {
	
	private Usuario usuario;
	private Libro libro;
	private Date fechaInicio;
	private Date fechaFin;
	
	private boolean detalles;
	private Prestamo prestamoEdicion;
	
	private boolean prueba;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=SERVICIO_PRESTAMO, create=true)
	private ServicioPrestamo servicioPrestamo;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
    @Create
	@Begin(join=true)
	public void inicializar(){
	}
    
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null){
			Prestamo prestamo = null;
			if(mapaArgumentos.contiene(PRESTAMO)){
				prestamo = (Prestamo) mapaArgumentos.getArgumento(PRESTAMO); 
				usuario = prestamo.getUsuario();
				libro = prestamo.getLibro();
				fechaFin = prestamo.getFechaFin();
				fechaInicio = prestamo.getFechaInicio();
			}
			
			if(mapaArgumentos.contiene(ACCION)){
				String accion = (String)mapaArgumentos.getArgumento(ACCION);
				if(ACCION_ANIADIR_PRESTAMOS.equals(accion)){
					detalles = false;
				}
				else if (ACCION_EDITAR_PRESTAMOS.equals(accion)){
					detalles = false;
					prestamoEdicion = prestamo; 
				}
				else if (ACCION_DETALLES_PRESTAMOS.equals(accion)){
					detalles = true;
				}
			}
			
			if(mapaArgumentos.contiene(OBJETO_DEVUELTO)){
				Object objDevuelto = mapaArgumentos.getArgumento(OBJETO_DEVUELTO);
				if(objDevuelto instanceof Libro)
					libro = (Libro)objDevuelto;
				else if (objDevuelto instanceof Usuario)
					usuario = (Usuario) objDevuelto;
			}

		}
	}
	
	public String aceptar(){

		String outcome = "";
		
		if(usuario==null || libro==null
		|| fechaFin==null || fechaInicio==null){
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("DATOS_LIBRO_OBLIGATORIOS"));
		}else{
			if(prestamoEdicion!=null){
				servicioPrestamo.actualizarDatosPrestamo(prestamoEdicion, usuario, libro, fechaInicio, fechaFin);
			}else{
				servicioPrestamo.crearPrestamo(usuario, libro, fechaInicio, fechaFin);
				limpiarFormulario();
			}
			outcome = GESTIONAR_PRESTAMOS;
		}
		return outcome;
	}
	
	public String cancelar(){
		limpiarFormulario();
		return GESTIONAR_PRESTAMOS;
	}
	
	public String volver(){
		limpiarFormulario();
		return GESTIONAR_PRESTAMOS;
	}
	
	private void limpiarFormulario(){
		usuario = null;
		libro = null;
		fechaFin = null;
		fechaInicio = null;
	}
	
	public String buscarUsuario(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(VOLVER_A, MTO_PRESTAMO);
		mapaArgumentos.setArgumento(DEVOLVER_OBJETO, true);

		return GESTIONAR_USUARIOS;
	}
	
	public void eliminarUsuario(){
		usuario = null;
	}
	
	public String buscarLibro(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(VOLVER_A, MTO_PRESTAMO);
		mapaArgumentos.setArgumento(DEVOLVER_OBJETO, true);
		mapaArgumentos.setArgumento(COMPROBAR_DATO_A_DEVOLVER, true);
		
		return GESTIONAR_LIBROS;
	}
	
	public void eliminarLibro(){
		libro = null;
	}
	
	public String verPrestamos(){
		return null;
	}


	public boolean isDetalles() {
		return detalles;
	}

	public void setDetalles(boolean detalles) {
		this.detalles = detalles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Prestamo getPrestamoEdicion() {
		return prestamoEdicion;
	}

	public void setPrestamoEdicion(Prestamo prestamoEdicion) {
		this.prestamoEdicion = prestamoEdicion;
	}

	public boolean isPrueba() {
		return prueba;
	}

	public void setPrueba(boolean prueba) {
		this.prueba = prueba;
	}

}
