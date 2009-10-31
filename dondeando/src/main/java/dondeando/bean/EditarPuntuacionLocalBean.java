package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.LOCAL_DE_NUEVA_PUNTUACION;
import static utilidades.varios.NombresBean.EDITAR_PUNTUACION_LOCAL_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_PUNTUACION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.math.BigDecimal;
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
import dondeando.modelo.servicio.ServicioPuntuacion;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_PUNTUACION_LOCAL_BEAN)
public class EditarPuntuacionLocalBean {

	private static final String OPERACION_CREAR_PUNTUACION_LOCAL = "_crearPuntuacionLocal_";
	
	private BigDecimal comida;
	private BigDecimal ambiente;
	private BigDecimal servicio;
	private BigDecimal calidadPrecio;
	private String loMejor;
	private String loPeor;

	private String operacion;
	private String tituloPagina;
	private Local local;

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
	@In(value=SERVICIO_PUNTUACION, create=true)
	private ServicioPuntuacion servicioPuntuacion;
	
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
			
			if(mapaArgumentos!=null && mapaArgumentos.contiene(LOCAL_DE_NUEVA_PUNTUACION))
				local = (Local)mapaArgumentos.getArgumento(LOCAL_DE_NUEVA_PUNTUACION);
			
			//Configuramos la página dependiente de la operación
			operacion = OPERACION_CREAR_PUNTUACION_LOCAL;

			if(OPERACION_CREAR_PUNTUACION_LOCAL.equals(operacion))
				tituloPagina = mensajesCore.obtenerTexto("REGISTRAR_VALORACION");
		}
	}
	
	
	/**
	 * Ejecutado al pulsar el botón aceptar, intenta guardar el usuario indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegación
	 */
	public String aceptar(){
		String outcome = "";
		
		List<String> errores = comprobarAgregarEditarPuntuacion();
		
		if(errores.isEmpty()){
				
			if(OPERACION_CREAR_PUNTUACION_LOCAL.equals(operacion)){
				servicioPuntuacion.crearPuntuacionLocal(local, comida, ambiente, servicio, calidadPrecio, loMejor, loPeor, servicioUsuario.devolverUsuarioActivo());
				utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("VALORACION_CORRECTA"));
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
	private List<String> comprobarAgregarEditarPuntuacion(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(ambiente==null || comida==null || servicio==null || calidadPrecio==null)
			errores.add(mensajesCore.obtenerTexto("CAMPOS_OBLIGATORIOS_CREAR_VALORACION"));
		
		//Valoraciones positivas
		if(errores.isEmpty()){
			BigDecimal cero = new BigDecimal(0);
			BigDecimal diez = new BigDecimal(10);
			if(comida.compareTo(cero)<0 || ambiente.compareTo(cero)<0 
			|| servicio.compareTo(cero)<0 || calidadPrecio.compareTo(cero)<0)
				errores.add(mensajesCore.obtenerTexto("VALORACIONES_POSITIVAS"));
			if(comida.compareTo(diez)>0 || ambiente.compareTo(diez)>0 
					|| servicio.compareTo(diez)>0 || calidadPrecio.compareTo(diez)>0)
				errores.add(mensajesCore.obtenerTexto("VALORACIONES_MENOR_DIEZ"));
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
		tituloPagina = null;
		comida = null;
		ambiente = null;
		servicio = null;
		calidadPrecio = null;
		operacion = null;

		local = null;
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

	public BigDecimal getComida() {
		return comida;
	}

	public void setComida(BigDecimal comida) {
		this.comida = comida;
	}

	public BigDecimal getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(BigDecimal ambiente) {
		this.ambiente = ambiente;
	}

	public BigDecimal getServicio() {
		return servicio;
	}

	public void setServicio(BigDecimal servicio) {
		this.servicio = servicio;
	}

	public BigDecimal getCalidadPrecio() {
		return calidadPrecio;
	}

	public void setCalidadPrecio(BigDecimal calidadPrecio) {
		this.calidadPrecio = calidadPrecio;
	}

	public String getLoMejor() {
		return loMejor;
	}

	public void setLoMejor(String loMejor) {
		this.loMejor = loMejor;
	}

	public String getLoPeor() {
		return loPeor;
	}

	public void setLoPeor(String loPeor) {
		this.loPeor = loPeor;
	}

}
