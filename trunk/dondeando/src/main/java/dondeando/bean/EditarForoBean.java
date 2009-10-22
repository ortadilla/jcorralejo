package dondeando.bean;

import static utilidades.varios.NombresBean.EDITAR_FORO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_FORO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.servicio.ServicioForo;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_FORO_BEAN)
public class EditarForoBean {

	private static final String OPERACION_EDITAR_FORO = "_editarForo_";
	private static final String OPERACION_CREAR_FORO = "_crearForo_";
	
	private String titulo;
	private String descripcion;

	private String operacion;
	private String tituloPagina;

	private Foro foroEdicion;
	
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
	@In(value=SERVICIO_FORO, create=true)
	private ServicioForo servicioForo;
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			//Configuramos la página dependiente de la operación
			operacion = protocoloEdicion.getObjeto()!=null ? OPERACION_EDITAR_FORO : OPERACION_CREAR_FORO;

			if(OPERACION_CREAR_FORO.equals(operacion)){
				tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_FORO");
			}
			else if(OPERACION_EDITAR_FORO.equals(operacion)){
				foroEdicion = (Foro)protocoloEdicion.getObjeto();
				titulo = foroEdicion.getTitulo();
				descripcion = foroEdicion.getDescripcion();
				tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_FORO");
			}
		}
	}
	
	
	/**
	 * Ejecutado al pulsar el botón aceptar, intenta guardar el usuario indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegación
	 */
	public String aceptar(){
		String outcome = "";
		
		List<String> errores = comprobarAgregarEditarForo();
		
		if(errores.isEmpty()){
				
			if(OPERACION_CREAR_FORO.equals(operacion)){
				servicioForo.crearForo(titulo, descripcion);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("FORO_CORRECTO"));
			}else if (OPERACION_EDITAR_FORO.equals(operacion) && foroEdicion!=null){
				servicioForo.editarForo(foroEdicion, titulo, descripcion);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("FORO_ACTUALIZADO"));
			}
			outcome = protocoloEdicion!=null && protocoloEdicion.getOutcomeVuelta()!=null ? protocoloEdicion.getOutcomeVuelta() 
											 											  : ConstantesReglasNavegacion.MENU_PRINCIPAL;
		}else{
			utilJsfContext.insertaMensajesAdvertencia(errores);
		}
		
		if(!"".equals(outcome)){
			protocoloEdicion = null;
			limpiarFormulario();
		}
		
		return outcome;
	}
	
	/**
	 * Comprueba si se puede agregar el foro con los datos indicados en la pantalla
	 * @return Lista de errores encontrados
	 */
	private List<String> comprobarAgregarEditarForo(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(titulo==null || "".equals(titulo)
		|| descripcion==null || "".equals(descripcion))
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_EDITAR_USUARIO"));
		
		//El foro no debe existir
		if(titulo!=null){
			Foro foroLogin = servicioForo.encontrarForoPorTitulo(titulo);
			if(foroLogin!=null && (foroEdicion==null || !foroEdicion.equals(foroLogin)))
				errores.add(mensajesCore.obtenerTexto("FORO_YA_EXISTE", titulo));
		}

		
		return errores;
	}
	
	/**
	 * Cancela la edición del usuario y vuelve a la pantalla anterior
	 * @return Regla de navegación
	 */
	public String cancelar(){
		String outcome = protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta() : "";
		protocoloEdicion = null; //para que al volver no se cargue nada
		limpiarFormulario();
		return outcome;
	}

	/**
	 * Limpia las variables asociadas al formulario de edición
	 */
	private void limpiarFormulario(){
		titulo = null;
		descripcion = null;
		tituloPagina = null;
		operacion = null;

		foroEdicion = null;
	}
	
	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}
	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}
	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
