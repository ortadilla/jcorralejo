package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.DEVOLVER_OBJETO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LANZAR_BUSQUEDA;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.OBJETO_DEVUELTO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.PRESTAMO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.VOLVER_A;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_LIBROS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_USUARIOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_PRESTAMO;
import static biblioTec.utilidades.NombresBean.GESTION_PRESTAMOS_BEAN;
import static biblioTec.utilidades.NombresBean.GESTION_PRESTAMOS_BINDING;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERMISO_PERFIL;
import static biblioTec.utilidades.NombresBean.SERVICIO_PRESTAMO;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.Date;
import java.util.List;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailHeader;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.binding.GestionPrestamosBinding;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioPermisoPerfil;
import biblioTec.modelo.servicios.ServicioPrestamo;
import biblioTec.modelo.servicios.ServicioUsuario;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.Permisos;
import biblioTec.utilidades.UtilJsfContext;


@Scope(CONVERSATION)
@Name(GESTION_PRESTAMOS_BEAN)
public class GestionPrestamosBean {

	private List<Prestamo> listaPrestamos;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private Usuario criterioUsuario;
	private Libro criterioLibro;
	private Date criterioFechaInicio;
	private Date criterioFechaFin;
	private boolean permisoGestionarPrestamos;
	private boolean lanzarBusqueda = false;
	private String volverA;
	
	@In(value=SERVICIO_PRESTAMO, create=true)
	private ServicioPrestamo servicioPrestamo;

	@In(value=GESTION_PRESTAMOS_BINDING, create=true)
	private GestionPrestamosBinding binding;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=SERVICIO_PERMISO_PERFIL, create=true)
	private ServicioPermisoPerfil servicioPermisoPerfil;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private biblioTec.utilidades.MapaArgumentos mapaArgumentos;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		permisoGestionarPrestamos = servicioPermisoPerfil.hayPermiso(Permisos.GESTIONAR_PRESTAMOS);
		if(!permisoGestionarPrestamos)
			criterioUsuario = servicioUsuario.devolverUsuarioActivo();
		desplegado = true;
	}
	
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null){
			if(mapaArgumentos.contiene(OBJETO_DEVUELTO)){
				Object objDevuelto = mapaArgumentos.getArgumento(OBJETO_DEVUELTO);
				if(objDevuelto instanceof Libro)
					criterioLibro = (Libro)objDevuelto;
				else if (objDevuelto instanceof Usuario)
					criterioUsuario = (Usuario) objDevuelto;
			}
			
			if(mapaArgumentos.contiene(LANZAR_BUSQUEDA)){
				criterioUsuario = (Usuario)mapaArgumentos.getArgumento(USUARIO);
				criterioLibro = (Libro)mapaArgumentos.getArgumento(LIBRO);
				lanzarBusqueda = true;
				if(mapaArgumentos.contiene(VOLVER_A))
					volverA = (String)mapaArgumentos.getArgumento(VOLVER_A);
				buscar();
			}
		}
	}

	public void buscar(){
		listaPrestamos = servicioPrestamo.encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(criterioUsuario, criterioLibro, criterioFechaInicio, criterioFechaFin);
		desplegado = false;
		if(binding.getBusqueda()==null) binding.setBusqueda(new CoreShowDetailHeader());
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaPrestamos!=null && listaPrestamos.size()==1)
			estadoDeSeleccionTabla.add(0);
	}

	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioUsuario = null;
		criterioLibro = null;
		criterioFechaFin = null;
		criterioFechaInicio = null;
	}

	@End
	public String menuPrincipal(){
		return MENU_PRINCIPAL;
	}

	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaPrestamos!=null ? listaPrestamos.size() : "0");
	}

	public String detalles(){

		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Prestamo prestamo = listaPrestamos.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(PRESTAMO, prestamo);
			mapaArgumentos.setArgumento(ACCION, ACCION_DETALLES_PRESTAMOS);
			outcome = MTO_PRESTAMO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}
	
	public String modificar(){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Prestamo prestamo = listaPrestamos.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(PRESTAMO, prestamo);
			mapaArgumentos.setArgumento(ACCION, ACCION_EDITAR_PRESTAMOS);
			outcome = MTO_PRESTAMO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}
	
	public String agregar(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(ACCION, ACCION_ANIADIR_PRESTAMOS);
		
		return MTO_PRESTAMO;
	}
	
	public void eliminar(){
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Prestamo prestamo= listaPrestamos.get(seleccion);
			if(prestamo.isDevuelto()){
				servicioPrestamo.borrarPrestamo(prestamo);
				listaPrestamos.remove(prestamo);
				estadoDeSeleccionTabla.clear();
			}else{
				utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("PRESTAMO_NO_DEVUELTO"));
			}
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}
	}
	
	public void devolver(){
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Prestamo prestamo = listaPrestamos.get(seleccion);
			if(!prestamo.isDevuelto())
				prestamo.setDevuelto(true);
			else
				utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("LIBRO_YA_DEVUELTO"));
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}
	}
	
	public String buscarUsuario(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(VOLVER_A, GESTIONAR_PRESTAMOS);
		mapaArgumentos.setArgumento(DEVOLVER_OBJETO, true);

		return GESTIONAR_USUARIOS;
	}
	
	public void eliminarUsuario(){
		criterioUsuario = null;
	}
	
	public String buscarLibro(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(VOLVER_A, GESTIONAR_PRESTAMOS);
		mapaArgumentos.setArgumento(DEVOLVER_OBJETO, true);
		
		return GESTIONAR_LIBROS;
	}
	
	public void eliminarLibro(){
		criterioLibro = null;
	}
	
	public String volver(){
		String outcome = volverA!=null ? volverA : "";
		limpiar();
		lanzarBusqueda = false;
		volverA = null;
		return outcome;
	}

	
	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}

	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}

	public boolean isDesplegado() {
		return desplegado;
	}

	public void setDesplegado(boolean desplegado) {
		this.desplegado = desplegado;
	}

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public Usuario getCriterioUsuario() {
		return criterioUsuario;
	}

	public void setCriterioUsuario(Usuario criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}

	public Libro getCriterioLibro() {
		return criterioLibro;
	}

	public void setCriterioLibro(Libro criterioLibro) {
		this.criterioLibro = criterioLibro;
	}

	public Date getCriterioFechaInicio() {
		return criterioFechaInicio;
	}

	public void setCriterioFechaInicio(Date criterioFechaInicio) {
		this.criterioFechaInicio = criterioFechaInicio;
	}

	public Date getCriterioFechaFin() {
		return criterioFechaFin;
	}

	public void setCriterioFechaFin(Date criterioFechaFin) {
		this.criterioFechaFin = criterioFechaFin;
	}

	public boolean isPermisoGestionarPrestamos() {
		return permisoGestionarPrestamos;
	}

	public void setPermisoGestionarPrestamos(boolean permisoGestionarPrestamos) {
		this.permisoGestionarPrestamos = permisoGestionarPrestamos;
	}

	public GestionPrestamosBinding getBinding() {
		return binding;
	}

	public void setBinding(GestionPrestamosBinding binding) {
		this.binding = binding;
	}

	public boolean isLanzarBusqueda() {
		return lanzarBusqueda;
	}

	public void setLanzarBusqueda(boolean lanzarBusqueda) {
		this.lanzarBusqueda = lanzarBusqueda;
	}


}
