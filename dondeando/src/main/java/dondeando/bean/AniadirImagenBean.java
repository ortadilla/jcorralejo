package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.DESCRIPCION_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.IMAGEN_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.MOSTRAR_DESCRIPCION_ANIADIR_IMAGEN;
import static utilidades.jsf.ConstantesArgumentosNavegacion.NOMBRE_IMAGEN_DEVUELTA_ANIADIR_IMAGEN;
import static utilidades.varios.NombresBean.ANIADIR_IMAGEN_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidadinternal.config.upload.UploadedFileImpl;
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
import dondeando.modelo.servicio.ServicioImagen;

@Scope(ScopeType.CONVERSATION)
@Name(ANIADIR_IMAGEN_BEAN)
public class AniadirImagenBean {
	
	//Atributos
	private UploadedFile file;
	private File fileTemporal;
	private boolean mostrarDescripcion;
	private String descripcion;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	//Servicios
	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;
	
    
	@Create
	@Begin(join=true)
	public void inicializar(){
		mapaArgumentos = (MapaArgumentos)RequestContext.getCurrentInstance().getPageFlowScope().get(MAPA_ARGUMENTOS);
		if(mapaArgumentos!=null){
			if(mapaArgumentos.contiene(MOSTRAR_DESCRIPCION_ANIADIR_IMAGEN))
				mostrarDescripcion = (Boolean)mapaArgumentos.getArgumento(MOSTRAR_DESCRIPCION_ANIADIR_IMAGEN);
		}
	}

	/**
	 * Devuelve la nueva imagen y descripción a la pantalla llamante
	 */
	public void aceptar(){
		
		List<String> errores = new ArrayList<String>();
		if(file!=null){
			try {
				errores.addAll(servicioImagen.comprobarAgregarImagen(file.getContentType(), 
																	 file.getLength(),
																	 file.getInputStream()));
			} catch (IOException e1) {
				//Si no se puede obtener el inputStream, no comprobamos los errores de la imagen
				errores.add(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN"));
				file = null;
			}
		}else
			errores.add(mensajesCore.obtenerTexto("IMAGEN_OBLIGATORIA"));
		
		if(errores.isEmpty())
			try {
				fileTemporal = servicioImagen.devuelveFicheroTemporal(file.getInputStream());
			} catch (IOException e) {
				errores.add(mensajesCore.obtenerTexto("ERROR_GUARDAR_IMAGEN"));
				file = null;
			}
		if(mostrarDescripcion && (descripcion==null || "".equals(descripcion)))
			errores.add(mensajesCore.obtenerTexto("DESCRIPCION_OBLIGATORIA"));
		
		if(errores.isEmpty()){
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(DESCRIPCION_DEVUELTA_ANIADIR_IMAGEN, descripcion);
			mapaArgumentos.setArgumento(IMAGEN_DEVUELTA_ANIADIR_IMAGEN, fileTemporal);
			mapaArgumentos.setArgumento(NOMBRE_IMAGEN_DEVUELTA_ANIADIR_IMAGEN, file.getFilename());
			RequestContext.getCurrentInstance().returnFromDialog(mapaArgumentos, null);
		}else
			utilJsfContext.insertaMensajesAdvertencia(errores);
	}
	
	/**
	 * Vuelve a la pantalla anterior
	 */
	public void cancelar(){
		RequestContext.getCurrentInstance().returnFromDialog(null, null);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isMostrarDescripcion() {
		return mostrarDescripcion;
	}

	public void setMostrarDescripcion(boolean mostrarDescripcion) {
		this.mostrarDescripcion = mostrarDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
