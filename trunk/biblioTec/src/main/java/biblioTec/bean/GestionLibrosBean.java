package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_LIBRO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LIBRO;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_LIBRO;
import static biblioTec.utilidades.NombresBean.GESTION_LIBROS_BEAN;
import static biblioTec.utilidades.NombresBean.GESTION_LIBROS_BINDING;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.SERVICIO_LIBRO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

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

import biblioTec.binding.GestionLibrosBinding;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.servicios.ServicioLibro;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.UtilJsfContext;


@Scope(CONVERSATION)
@Name(GESTION_LIBROS_BEAN)
public class GestionLibrosBean {

	private List<Libro> listaLibros;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private String criterioTitulo;
	private String criterioAutor;
	private String criterioISBN;

	@In(value=SERVICIO_LIBRO, create=true)
	private ServicioLibro servicioLibro;

	@In(value=GESTION_LIBROS_BINDING, create=true)
	private GestionLibrosBinding binding;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private biblioTec.utilidades.MapaArgumentos mapaArgumentos;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		desplegado = true;
	}

	public void buscar(){
		listaLibros = servicioLibro.encontrarLibrosPorTituloAutorYIsbn(criterioTitulo, criterioAutor, criterioISBN);
		desplegado = false;
		if(binding.getBusqueda()==null) binding.setBusqueda(new CoreShowDetailHeader());
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaLibros!=null && listaLibros.size()==1)
			estadoDeSeleccionTabla.add(0);
	}

	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioTitulo = null;
		criterioAutor = null;
		criterioISBN = null;
	}

	@End
	public String menuPrincipal(){
		return MENU_PRINCIPAL;
	}

	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaLibros!=null ? listaLibros.size() : "0");
	}

	public String detalles(){

		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Libro libro = listaLibros.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(LIBRO, libro);
			mapaArgumentos.setArgumento(ACCION, ACCION_DETALLES_LIBRO);
			outcome = MTO_LIBRO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}
	
	public String modificar(){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Libro libro = listaLibros.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(LIBRO, libro);
			mapaArgumentos.setArgumento(ACCION, ACCION_EDITAR_LIBRO);
			outcome = MTO_LIBRO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}
	
	public String agregar(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(ACCION, ACCION_ANIADIR_LIBRO);
		
		return MTO_LIBRO;
	}
	
	public void eliminar(){
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Libro libro = listaLibros.get(seleccion);
			if(!servicioLibro.tienePrestamosPendientes(libro)){
				servicioLibro.borrarLibro(libro);
				listaLibros.remove(libro);
				estadoDeSeleccionTabla.clear();
			}else{
				utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("PRESTAMOS_PENDIENTES"));
			}
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}
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

	public String getCriterioTitulo() {
		return criterioTitulo;
	}

	public void setCriterioTitulo(String criterioTitulo) {
		this.criterioTitulo = criterioTitulo;
	}

	public String getCriterioAutor() {
		return criterioAutor;
	}

	public void setCriterioAutor(String criterioAutor) {
		this.criterioAutor = criterioAutor;
	}

	public String getCriterioISBN() {
		return criterioISBN;
	}

	public void setCriterioISBN(String criterioISBN) {
		this.criterioISBN = criterioISBN;
	}

	public List<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(List<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public GestionLibrosBinding getBinding() {
		return binding;
	}

	public void setBinding(GestionLibrosBinding binding) {
		this.binding = binding;
	}

}
