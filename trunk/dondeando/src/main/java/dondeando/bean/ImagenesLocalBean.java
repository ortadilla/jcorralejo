package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.DESCRIPCION_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.IMAGEN_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.MOSTRAR_DESCRIPCION_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.NOMBRE_IMAGEN_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesReglasNavegacion.ANIADIR_IMAGEN;
import static utilidades.varios.NombresBean.IMAGENES_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN_LOCAL;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesArgumentosNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.ImagenLocal;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioImagenLocal;

@Scope(ScopeType.CONVERSATION)
@Name(IMAGENES_LOCAL_BEAN)
public class ImagenesLocalBean {
	
	public static final String OPERACION_EDITAR_IMAGENES = "_editarImagenes_";
	public static final String OPERACION_DETALLES_IMAGENES = "_detallesImagenes_";
	
	//Atributos
	private String descripcionLocal;
	private String urlImagenMostrada;
	private String descripcionImagenMostrada;
	private boolean mostrarImagenes;
	private boolean mostrarAnterior;
	private boolean mostrarSiguiente;
	private boolean mostrarBorrar;
	
	private List<ImagenLocal> imagenesLocal = new ArrayList<ImagenLocal>();
	private int indiceImagenes = -1;
	private String operacion;
	private Local local;
	
	private boolean ignorarCarga = false;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	private ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;
	
	@In(value=SERVICIO_IMAGEN_LOCAL, create=true)
	private ServicioImagenLocal servicioImagenLocal;
	
	
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		operacion = protocoloEdicion.getOperacion();
		if(protocoloEdicion.getObjeto()!=null){
			
			if(!ignorarCarga){
				local = (Local)protocoloEdicion.getObjeto();
				descripcionLocal = local.getNombre();
				imagenesLocal.addAll(local.getImagenes());
			}
			
			if(imagenesLocal==null || imagenesLocal.isEmpty()){
				mostrarImagenes = false;
				mostrarAnterior = false;
				mostrarSiguiente = false;
				mostrarBorrar = false;
			}
			if(imagenesLocal!=null && imagenesLocal.size()>0){
				mostrarImagenes = true;
				mostrarBorrar = true;
				if(indiceImagenes < 0) 
					indiceImagenes = 0;
				
				urlImagenMostrada = servicioImagen.calcularUrlImagen((imagenesLocal.get(indiceImagenes)).getImagen());
				descripcionImagenMostrada = imagenesLocal.get(indiceImagenes).getDescripcion();
				
				mostrarAnterior = indiceImagenes>0;
				mostrarSiguiente = indiceImagenes<imagenesLocal.size()-1;
			}
		}else{
			mostrarImagenes = false;
			mostrarAnterior = false;
			mostrarSiguiente = false;
			mostrarBorrar = false;
		}
	}
	
	/**
	 * Muestra la imagen anterior
	 */
	public void imagenAnterior(){
		ignorarCarga = true;
		indiceImagenes--;
	}
	
	/**
	 * Muestra la imagen siguiente
	 */
	public void imagenSiguiente(){
		ignorarCarga = true;
		indiceImagenes++;
	}
	
	/**
	 * Vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String volver(){
		String outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
		limpiarFormulario();
		return outcome;
	}
	
	/**
	 * Elimina la imagen actual del local
	 * @throws DAOExcepcion Si ocurre algún error al borrar
	 */
	public void borrar() throws DAOExcepcion{
		ignorarCarga = true;
		ImagenLocal imagenEliminada = imagenesLocal.get(indiceImagenes);
		local.getImagenes().remove(imagenEliminada);
		servicioImagenLocal.flush();
		imagenesLocal.remove(imagenEliminada);
		indiceImagenes--;
	}
	
	/**
	 * Añade una imagen al local
	 */
	public String aniadir(){
		ignorarCarga = true;
		MapaArgumentos mapaPopUp = new MapaArgumentos();
		mapaPopUp.setArgumento(MOSTRAR_DESCRIPCION_ANIADIR_IMAGEN, true);
		RequestContext.getCurrentInstance().getPageFlowScope().put(MAPA_ARGUMENTOS, mapaPopUp);
		return ANIADIR_IMAGEN;
	}
	
	/**
	 * Almacena la nueva imagen
	 * @param event 
	 * @throws DAOExcepcion 
	 */
	public void returnAniadir(ReturnEvent event) throws DAOExcepcion{
        MapaArgumentos mapaArgumentosDevuelto = (MapaArgumentos)event.getReturnValue();
        // Si el mapa de argumentos viene a null, es porque he pulsado el botón de cancelar 
        if(mapaArgumentosDevuelto != null){
        	File file = (File)mapaArgumentosDevuelto.getArgumento(IMAGEN_DEVUELTA_ANIADIR_IMAGEN);
        	String descripcion = (String)mapaArgumentosDevuelto.getArgumento(DESCRIPCION_DEVUELTA_ANIADIR_IMAGEN);
        	String nombreFile = (String)mapaArgumentosDevuelto.getArgumento(NOMBRE_IMAGEN_DEVUELTA_ANIADIR_IMAGEN);
        	if(file!=null && descripcion!=null && !"".equals(descripcion)){ //pslm
        		Imagen imagen = servicioImagen.guardarImagen(nombreFile, servicioImagen.devuelveFileInputStream(file), false);
        		if(imagen!=null){
        			ImagenLocal imagenLocal = servicioImagenLocal.crearImagenLocal(imagen, local, descripcion);
        			imagenesLocal.add(imagenLocal);
        			indiceImagenes = imagenesLocal.size()-1;
        		}else
        			//Si no se puede obtener el inputStream...
        			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN"));
        	}
        }
	}
	
	/**
	 * Limpia el formulario
	 */
	private void limpiarFormulario(){
		descripcionLocal = null;
		urlImagenMostrada = null;
		descripcionImagenMostrada = null;
		imagenesLocal.clear();
		operacion = null;
		local = null;
		protocoloEdicion = null;
		ignorarCarga = false;
		indiceImagenes = -1;
		mostrarImagenes = false;
	}
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		
	}

	public String getDescripcionLocal() {
		return descripcionLocal;
	}

	public void setDescripcionLocal(String descripcionLocal) {
		this.descripcionLocal = descripcionLocal;
	}

	public String getUrlImagenMostrada() {
		return urlImagenMostrada;
	}

	public void setUrlImagenMostrada(String urlImagenMostrada) {
		this.urlImagenMostrada = urlImagenMostrada;
	}

	public String getDescripcionImagenMostrada() {
		return descripcionImagenMostrada;
	}

	public void setDescripcionImagenMostrada(String descripcionImagenMostrada) {
		this.descripcionImagenMostrada = descripcionImagenMostrada;
	}

	public boolean isMostrarImagenes() {
		return mostrarImagenes;
	}

	public void setMostrarImagenes(boolean mostrarImagenes) {
		this.mostrarImagenes = mostrarImagenes;
	}

	public boolean isMostrarAnterior() {
		return mostrarAnterior;
	}

	public void setMostrarAnterior(boolean mostrarAnterior) {
		this.mostrarAnterior = mostrarAnterior;
	}

	public boolean isMostrarSiguiente() {
		return mostrarSiguiente;
	}

	public void setMostrarSiguiente(boolean mostrarSiguiente) {
		this.mostrarSiguiente = mostrarSiguiente;
	}

	public boolean isMostrarBorrar() {
		return mostrarBorrar;
	}

	public void setMostrarBorrar(boolean mostrarBorrar) {
		this.mostrarBorrar = mostrarBorrar;
	}

}
