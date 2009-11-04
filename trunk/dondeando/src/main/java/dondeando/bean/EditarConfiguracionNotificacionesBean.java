package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.USUARIO_DE_CONFIGURACION_NOTIFICACIONES;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_CONFIGURACION_NOTIFICACIONES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.varios.NombresBean.EDITAR_CONFIGURACION_NOTIFICACIONES_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_BUSQUEDA;
import static utilidades.varios.NombresBean.PROTOCOLO_RESULTADO;
import static utilidades.varios.NombresBean.SERVICIO_FORO;
import static utilidades.varios.NombresBean.SERVICIO_INTERES;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_INTERES;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloBusqueda;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioForo;
import dondeando.modelo.servicio.ServicioInteres;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioTipoInteres;

@Scope(ScopeType.CONVERSATION)
@Name(EDITAR_CONFIGURACION_NOTIFICACIONES_BEAN)
public class EditarConfiguracionNotificacionesBean {

	private String tituloPagina;
	private TipoInteres tipoNotificacion;
	private boolean mostrarProvincias;
	private boolean mostrarBusquedaLocal;
	private boolean mostrarForos;
	private Boolean enviarEmail;
	private Provincia provincia;
	private Local local;
	private Foro foro;
	
	private SelectItem[] selectTipoNotificacion;
	private SelectItem[] selectProvincias;
	private SelectItem[] selectForos;
	private SelectItem[] selectSiNo;

	private Usuario usuario;
	
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
	@In(value=SERVICIO_INTERES, create=true)
	private ServicioInteres servicioInteres;
	
	@In(value=SERVICIO_TIPO_INTERES, create=true)
	private ServicioTipoInteres servicioTipoInteres;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_FORO, create=true)
	private ServicioForo servicioForo;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		selectProvincias = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioProvincia.encontrarTodos(), Provincia.ATRIBUTO_NOMBRE), 
															 null, 
															 Provincia.ATRIBUTO_NOMBRE,
															 true);
		selectTipoNotificacion = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioTipoInteres.encontrarTodos(), TipoInteres.ATRIBUTO_DESCRIPCION), 
																   null, 
																   TipoInteres.ATRIBUTO_DESCRIPCION,
																   true);
		selectForos = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioForo.encontrarTodos(), Foro.ATRIBUTO_TITULO), 
														null, 
														Foro.ATRIBUTO_TITULO,
														true);
		selectSiNo = SelectItemBuilder.creaSelectItemsSiNo();
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			if(mapaArgumentos!=null && mapaArgumentos.contiene(USUARIO_DE_CONFIGURACION_NOTIFICACIONES))
				usuario = (Usuario)mapaArgumentos.getArgumento(USUARIO_DE_CONFIGURACION_NOTIFICACIONES);
			tituloPagina = mensajesCore.obtenerTexto("CONFIGURAR_NOTIFICACIONES");
		}
		
		//Si traemos un protocolo resultado, es que hemos ido a buscar un nuevo moderador
		if(mapaArgumentos!=null && mapaArgumentos.contiene(PROTOCOLO_RESULTADO)){
			local = (Local)mapaArgumentos.getArgumento(PROTOCOLO_RESULTADO);
		}
	}
	
	
	/**
	 * Ejecutado al pulsar el botón aceptar, intenta guardar el usuario indicado
	 * por los datos introducidos en pantalla
	 * @return	Outcome de navegación
	 */
	public String aceptar(){
		String outcome = "";

		List<String> errores = comprobarAgregarEditarInteres();

		if(errores.isEmpty()){
			servicioInteres.crearInteres(usuario, tipoNotificacion, foro, local, provincia, enviarEmail);
			utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("INTERES_CORRECTO"));
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
	private List<String> comprobarAgregarEditarInteres(){
		List<String> errores = new ArrayList<String>();
		
		//Campos obligatorios
		if(tipoNotificacion==null )
			errores.add(mensajesCore.obtenerTexto("TIPO_INTERES_OBLIGATORIO"));
		
		else{
			if((ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(tipoNotificacion.getId())
			|| ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(tipoNotificacion.getId())
			|| ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(tipoNotificacion.getId()))
			&& local==null)
				errores.add(mensajesCore.obtenerTexto("LOCAL_OBLIGATORIO_TIPO_INTERES"));
			
			if(ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(tipoNotificacion.getId())
			&& foro==null)
				errores.add(mensajesCore.obtenerTexto("FORO_OBLIGATORIO_TIPO_INTERES"));
			
			if((ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(tipoNotificacion.getId())
			||  ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(tipoNotificacion.getId()))
			&&  provincia==null)
				errores.add(mensajesCore.obtenerTexto("PROVINCIA_OBLIGATORIO_TIPO_INTERES"));
			
			if(enviarEmail==null)
				errores.add(mensajesCore.obtenerTexto("EMAIL_OBLIGATORIO_TIPO_INTERES"));
			
			if(errores.isEmpty()){
				Integer idObj = local!=null ? local.getId() 
											: foro!=null ? foro.getId() 
														 : provincia!=null ? provincia.getId() : null;
																		
				//Comprobamos que no exista ya el mismo interés para el usuario
				if(servicioInteres.encontrarInteresPorUsuarioTipoYObjeto(usuario, tipoNotificacion, idObj)!=null)
					errores.add(mensajesCore.obtenerTexto("YA_EXISTE_TIPO_INTERES"));
			}
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
	
	public String buscarLocal(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloBusqueda protocolo = new ProtocoloBusqueda(null, false, EDITAR_CONFIGURACION_NOTIFICACIONES);
		mapaArgumentos.setArgumento(PROTOCOLO_BUSQUEDA, protocolo);

		return GESTION_LOCALES;
	}
	
	public void eliminarLocal(){
		local = null;
	}

	/**
	 * Limpia las variables asociadas al formulario de edición
	 */
	private void limpiarFormulario(){
		tituloPagina = null;
		tipoNotificacion = null;
		mostrarBusquedaLocal = false;
		mostrarForos = false;
		mostrarProvincias = false;
		provincia = null;
		foro = null;
		local = null;
		enviarEmail = null;
		
		usuario = null;
	}
	
	public void cambioTipoNotificacion(ValueChangeEvent evento){
		
		TipoInteres tipoInt = (TipoInteres)evento.getNewValue();
		if(tipoInt!=null){
			
			limpiarObjetosTipos();
			
			mostrarBusquedaLocal = ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(tipoInt.getId())
								|| ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(tipoInt.getId())
								|| ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(tipoInt.getId());
			
			mostrarForos = ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(tipoInt.getId());
			
			mostrarProvincias = ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(tipoInt.getId())
							 || ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(tipoInt.getId());
		}else
			limpiarObjetosTipos();		
	}

	private void limpiarObjetosTipos(){
		mostrarBusquedaLocal = false;
		mostrarForos = false;
		mostrarProvincias = false;

		foro = null;
		local = null;
		provincia = null;
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

	public boolean isMostrarProvincias() {
		return mostrarProvincias;
	}

	public void setMostrarProvincias(boolean mostrarProvincias) {
		this.mostrarProvincias = mostrarProvincias;
	}

	public boolean isMostrarBusquedaLocal() {
		return mostrarBusquedaLocal;
	}

	public void setMostrarBusquedaLocal(boolean mostrarBusquedaLocal) {
		this.mostrarBusquedaLocal = mostrarBusquedaLocal;
	}

	public boolean isMostrarForos() {
		return mostrarForos;
	}

	public void setMostrarForos(boolean mostrarForos) {
		this.mostrarForos = mostrarForos;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public SelectItem[] getSelectTipoNotificacion() {
		return selectTipoNotificacion;
	}

	public void setSelectTipoNotificacion(SelectItem[] selectTipoNotificacion) {
		this.selectTipoNotificacion = selectTipoNotificacion;
	}

	public SelectItem[] getSelectProvincias() {
		return selectProvincias;
	}

	public void setSelectProvincias(SelectItem[] selectProvincias) {
		this.selectProvincias = selectProvincias;
	}

	public SelectItem[] getSelectForos() {
		return selectForos;
	}

	public void setSelectForos(SelectItem[] selectForos) {
		this.selectForos = selectForos;
	}

	public TipoInteres getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(TipoInteres tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public Boolean getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(Boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public SelectItem[] getSelectSiNo() {
		return selectSiNo;
	}

	public void setSelectSiNo(SelectItem[] selectSiNo) {
		this.selectSiNo = selectSiNo;
	}


}
