package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.FORO_DE_NUEVO_MENSAJE;
import static utilidades.jsf.ConstantesArgumentosNavegacion.TEMA_DE_NUEVO_MENSAJE;
import static utilidades.varios.NombresBean.EDITAR_MENSAJE_FORO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
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
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.servicio.ServicioMensajeForo;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_MENSAJE_FORO_BEAN)
public class EditarMensajeForoBean {

	private static final String OPERACION_EDITAR_MENSAJE_FORO = "_editarMensajeForo_";
	private static final String OPERACION_CREAR_MENSAJE_FORO = "_crearMensajeForo_";
	
	private String asunto;
	private String mensaje;

	private String operacion;
	private String tituloPagina;
	private MensajeForo tema;
	private Foro foro;

	private MensajeForo mensajeForoEdicion;
	
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
	@In(value=SERVICIO_MENSAJE_FORO, create=true)
	private ServicioMensajeForo servicioMensajeForo;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			
			if(mapaArgumentos.contiene(TEMA_DE_NUEVO_MENSAJE))
				tema = (MensajeForo)mapaArgumentos.getArgumento(TEMA_DE_NUEVO_MENSAJE);
			if(mapaArgumentos.contiene(FORO_DE_NUEVO_MENSAJE))
				foro = (Foro)mapaArgumentos.getArgumento(FORO_DE_NUEVO_MENSAJE);
			
			//Configuramos la página dependiente de la operación
			operacion = protocoloEdicion.getObjeto()!=null ? OPERACION_EDITAR_MENSAJE_FORO : OPERACION_CREAR_MENSAJE_FORO;

			if(OPERACION_CREAR_MENSAJE_FORO.equals(operacion)){
				tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_MENSAJE");
			}
			else if(OPERACION_EDITAR_MENSAJE_FORO.equals(operacion)){
				mensajeForoEdicion = (MensajeForo)protocoloEdicion.getObjeto();
				asunto = mensajeForoEdicion.getTitulo();
				mensaje = mensajeForoEdicion.getMensaje();
				tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_MENSAJE");
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
		
		List<String> errores = comprobarAgregarEditarMensajeForo();
		
		if(errores.isEmpty()){
				
			if(OPERACION_CREAR_MENSAJE_FORO.equals(operacion)){
				servicioMensajeForo.crearMensajeForo(foro, tema, asunto, mensaje, servicioUsuario.devolverUsuarioActivo());
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("MENSAJE_CORRECTO"));
			}else if (OPERACION_EDITAR_MENSAJE_FORO.equals(operacion) && mensajeForoEdicion!=null){
				servicioMensajeForo.editarMensajeForo(mensajeForoEdicion, foro, tema, asunto, mensaje, servicioUsuario.devolverUsuarioActivo());
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("MENSAJE_ACTUALIZADO"));
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
	private List<String> comprobarAgregarEditarMensajeForo(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(asunto==null || "".equals(asunto)
		|| mensaje==null || "".equals(mensaje))
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_EDITAR_MENSAJE"));
		
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
		tituloPagina = null;
		asunto = null;
		mensaje = null;
		operacion = null;

		mensaje = null;
		tema = null;
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
