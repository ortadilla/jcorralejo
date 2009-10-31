package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.LOCAL_DE_NUEVA_OPINION;
import static utilidades.varios.NombresBean.EDITAR_OPINION_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_OPINION;
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
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.servicio.ServicioOpinion;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_OPINION_LOCAL_BEAN)
public class EditarOpinionLocalBean {

	private static final String OPERACION_EDITAR_OPINION_LOCAL = "_editarOpinionLocal_";
	private static final String OPERACION_CREAR_OPINION_LOCAL = "_crearOpinionLocal_";
	
	private String opinion;

	private String operacion;
	private String tituloPagina;
	private List<MensajeForo> listaMensajesTema;
	private Local local;

	private Opinion opinionEdicion;
	
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
	@In(value=SERVICIO_OPINION, create=true)
	private ServicioOpinion servicioOpinion;
	
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
			
			if(mapaArgumentos.contiene(LOCAL_DE_NUEVA_OPINION))
				local = (Local)mapaArgumentos.getArgumento(LOCAL_DE_NUEVA_OPINION);
			
			//Configuramos la página dependiente de la operación
			operacion = protocoloEdicion.getObjeto()!=null ? OPERACION_EDITAR_OPINION_LOCAL : OPERACION_CREAR_OPINION_LOCAL;

			if(OPERACION_CREAR_OPINION_LOCAL.equals(operacion)){
				tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_OPINION");
			}
			else if(OPERACION_EDITAR_OPINION_LOCAL.equals(operacion)){
				opinionEdicion = (Opinion)protocoloEdicion.getObjeto();
				opinion = opinionEdicion.getOpinion();
				tituloPagina = mensajesCore.obtenerTexto("MODIFICAR_OPINION");
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
		
		List<String> errores = comprobarAgregarEditarOpinion();
		
		if(errores.isEmpty()){
				
			if(OPERACION_CREAR_OPINION_LOCAL.equals(operacion)){
				servicioOpinion.crearOpinion(local, opinion, servicioUsuario.devolverUsuarioActivo());
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("OPINION_CORRECTA"));
			}else if (OPERACION_EDITAR_OPINION_LOCAL.equals(operacion) && opinionEdicion!=null){
				servicioOpinion.editarOpinion(opinionEdicion, opinion);
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("OPINION_ACTUALIZADA"));
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
	private List<String> comprobarAgregarEditarOpinion(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(opinion==null || "".equals(opinion))
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_EDITAR_OPINION"));
		
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
		opinion = null;
		opinionEdicion = null;
		operacion = null;

		listaMensajesTema = null;
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de respuestas
	 * @return mensaje con el número de elementos de la tabla de respuestas
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaMensajesTema != null ? listaMensajesTema.size() : "0");
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

	public List<MensajeForo> getListaMensajesTema() {
		return listaMensajesTema;
	}

	public void setListaMensajesTema(List<MensajeForo> listaMensajesTema) {
		this.listaMensajesTema = listaMensajesTema;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
