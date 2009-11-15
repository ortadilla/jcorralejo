package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_FOROS;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_VALORACIONES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_FORO;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;
import static utilidades.varios.NombresBean.SERVICIO_OPINION;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_PUNTUACION;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.util.Arrays;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.NombresBean;
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloBusqueda;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioForo;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioMensajeForo;
import dondeando.modelo.servicio.ServicioOpinion;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioPuntuacion;
import dondeando.modelo.servicio.ServicioTipoLocal;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(CONVERSATION)
@Name(MENU_PRINCIPAL_BEAN)
public class MenuPrincipalBean {
	
	public static String JSF_MENU_PRINCIPAL = "/dondeando/menuPrincipal.jsf";
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;

	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;
	
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_PUNTUACION, create=true)
	private ServicioPuntuacion servicioPuntuacion;
	
	@In(value=SERVICIO_FORO, create=true)
	private ServicioForo servicioForo;
	
	@In(value=SERVICIO_MENSAJE_FORO, create=true)
	private ServicioMensajeForo servicioMensajeForo;
	
	@In(value=SERVICIO_OPINION, create=true)
	private ServicioOpinion servicioOpinion;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;

	
	private boolean mostrarGestionUsuarios;
	private boolean mostrarGestionLocales;
	private boolean mostrarGestionForos;
	private Provincia provincia;
	private SelectItem[] selectProvincia;
	private List<TipoLocal> listaTiposLocales;
	private List<Foro> listaForos;
	private List<Opinion> listaOpiniones;
	private List<Puntuacion> listaValoraciones;
	private List<MensajeForo> listaMensajes;
	private List<Local> listaDestacados;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		selectProvincia = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioProvincia.encontrarTodos(), Provincia.ATRIBUTO_NOMBRE), 
															null, 
															Provincia.ATRIBUTO_NOMBRE);
		provincia = servicioProvincia.encontrarProvinciaPorId(43);//Sevilla
		listaTiposLocales = HerramientasList.ordenar(servicioTipoLocal.encontrarTodos(),TipoLocal.ATRIBUTO_DESCRIPCION);
	}


	public void cargarArgumentosDeEntrada(){
		pintarBotones();
		
		selectProvincia = SelectItemBuilder.creaSelectItems(HerramientasList.ordenar(servicioProvincia.encontrarTodos(), Provincia.ATRIBUTO_NOMBRE), 
															null, 
															Provincia.ATRIBUTO_NOMBRE);
		provincia = servicioProvincia.encontrarProvinciaPorId(43);//Sevilla
		listaForos = HerramientasList.ordenar(servicioForo.encontrarTodos(),Foro.ATRIBUTO_TITULO);
		listaOpiniones = HerramientasList.ordenar(servicioOpinion.encontrarTodas(),Opinion.ATRIBUTO_FECHA+" DESC");
		if(listaOpiniones.size()>5)
			listaOpiniones = listaOpiniones.subList(0, 5);
		listaValoraciones = HerramientasList.ordenar(servicioPuntuacion.encontrarTodas(),Puntuacion.ATRIBUTO_FECHA+" DESC");
		if(listaValoraciones.size()>5)
			listaValoraciones = listaValoraciones.subList(0, 5);
		listaMensajes = HerramientasList.ordenar(servicioMensajeForo.encontrarTodos(),MensajeForo.ATRIBUTO_FECHA+" DESC");
		if(listaMensajes.size()>5)
			listaMensajes = listaMensajes.subList(0, 5);
		listaDestacados = HerramientasList.ordenar(servicioLocal.encontrarTodos(),Local.ATRIBUTO_VALORACION+" DESC");
		if(listaDestacados.size()>3)
			listaDestacados = listaDestacados.subList(0, 3);
		servicioLocal.rellenarPropiedadesNoMapeadas(listaDestacados);

	}
	
	/**
	 * Muestra u oculta los botones dependiendo del perfil del usuario
	 */
	private void pintarBotones(){
		mostrarGestionUsuarios = servicioUsuario.isUsuarioActivoAdmin();
		mostrarGestionLocales = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_LOCALES);
		mostrarGestionForos = servicioPermisoUsuario.hayPermiso(Permisos.GESTIONAR_FOROS);
	}

	/**
	 * Navega a la gestión de Usuarios
	 * @return Outcome de la gestión de Usuarios
	 */
	public String gestionUsuarios(){
		return GESTION_USUARIOS;
	}
	
	/**
	 * Navega a la gestión de Locales
	 * @return Outcome de la gestión de Locales
	 */
	public String gestionLocales(){
		return GESTION_LOCALES;
	}

	/**
	 * Navega a la gestión de Foros
	 * @return Outcome de la gestión de Foros
	 */
	public String gestionForos(){
		return GESTION_FOROS;
	}
	
	/**
	 * Prepara el mapaargumentos para navegar a la gestión de locales filtrando por el tipo
	 * seleccionado
	 * @param actionEvent
	 */
	public void accionListenerTipoLocal(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idTipoLocal");
		if(identificador!=null){
			List<TipoLocal> tipos = HerramientasList.obtenerElementos(listaTiposLocales, TipoLocal.ATRIBUTO_ID, identificador);
			if(tipos.size()==1){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloBusqueda protocolo = new ProtocoloBusqueda(null, true, MENU_PRINCIPAL, false);
				protocolo.addParametro(Local.ATRIBUTO_TIPOS_LOCAL, Arrays.asList(identificador));
				protocolo.addParametro(Local.ATRIBUTO_PROVINCIA, provincia);
				mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_BUSQUEDA, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de locales
	 * @return Regla de navegación
	 */
	public String accionTipoLocal(){
		return ConstantesReglasNavegacion.GESTION_LOCALES;
	}


	/**
	 * Prepara el mapaargumentos para navegar a los temas del foro
	 * seleccionado
	 * @param actionEvent
	 */
	public void accionListenerForo(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idForo");
		if(identificador!=null){
			List<Foro> foro = HerramientasList.obtenerElementos(listaForos, Foro.ATRIBUTO_ID, identificador);
			if(foro.size()==1){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(foro.get(0), MENU_PRINCIPAL, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de foros
	 * @return Regla de navegación
	 */
	public String accionForos(){
		return ConstantesReglasNavegacion.GESTION_TEMAS_FORO;
	}
	
	/**
	 * Prepara el mapaargumentos para navegar a la gestión de opiniones filtrando por el local
	 * @param actionEvent
	 */
	public void accionListenerOpiniones(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idOpinion");
		if(identificador!=null){
			List<Opinion> opinion = HerramientasList.obtenerElementos(listaOpiniones, Opinion.ATRIBUTO_ID, identificador);
			if(opinion.size()==1){
				ProtocoloEdicion protocolo = new ProtocoloEdicion(opinion.get(0).getLocal(),
																  MENU_PRINCIPAL,
																  null);
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de opiniones
	 * @return Regla de navegación
	 */
	public String accionOpiniones(){
		return ConstantesReglasNavegacion.GESTION_OPINIONES_LOCAL;
	}
	
	/**
	 * Prepara el mapaargumentos para navegar a la gestión valoraciones filtrando por el local
	 * @param actionEvent
	 */
	public void accionListenerValoraciones(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idValoracion");
		if(identificador!=null){
			List<Puntuacion> valoracino = HerramientasList.obtenerElementos(listaValoraciones, Puntuacion.ATRIBUTO_ID, identificador);
			if(valoracino.size()==1){
				ProtocoloEdicion protocolo = new ProtocoloEdicion(valoracino.get(0).getLocal(),
																  MENU_PRINCIPAL,
																  null);
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de valoraciones
	 * @return Regla de navegación
	 */
	public String accionValoraciones(){
		return GESTION_VALORACIONES_LOCAL;
	}
	
	/**
	 * Prepara el mapaargumentos para navegar a la gestión valoraciones filtrando por el local
	 * @param actionEvent
	 */
	public void accionListenerMensajes(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idMensaje");
		if(identificador!=null){
			List<MensajeForo> mensaje = HerramientasList.obtenerElementos(listaMensajes, MensajeForo.ATRIBUTO_ID, identificador);
			if(mensaje.size()==1){
				ProtocoloEdicion protocolo = new ProtocoloEdicion(mensaje.get(0).getRespondeA()!=null 
																? mensaje.get(0).getRespondeA() : mensaje.get(0),
																  MENU_PRINCIPAL,
																  null);
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de valoraciones
	 * @return Regla de navegación
	 */
	public String accionMensajes(){
		return ConstantesReglasNavegacion.GESTION_MENSAJES_TEMA;
	}
	
	/**
	 * Prepara el mapaargumentos para navegar a la gestión valoraciones filtrando por el local
	 * @param actionEvent
	 */
	public void accionLocalConcreto(ActionEvent actionEvent){
		Integer identificador = (Integer) actionEvent.getComponent().getAttributes().get("idLocal");
		if(identificador!=null){
			List<Local> local = HerramientasList.obtenerElementos(listaDestacados, Local.ATRIBUTO_ID, identificador);
			if(local.size()==1){
				ProtocoloEdicion protocolo = new ProtocoloEdicion(local.get(0),MENU_PRINCIPAL,null);
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				mapaArgumentos.setArgumento(NombresBean.PROTOCOLO_EDICION, protocolo);
			}
		}
	}
	
	/**
	 * Navega a la gestión de valoraciones
	 * @return Regla de navegación
	 */
	public String accionLocalConcreto(){
		return ConstantesReglasNavegacion.DETALLES_LOCAL;
	}



	
	public boolean isMostrarGestionUsuarios() {
		return mostrarGestionUsuarios;
	}

	public void setMostrarGestionUsuarios(boolean mostrarGestionUsuarios) {
		this.mostrarGestionUsuarios = mostrarGestionUsuarios;
	}

	public boolean isMostrarGestionLocales() {
		return mostrarGestionLocales;
	}

	public void setMostrarGestionLocales(boolean mostrarGestionLocales) {
		this.mostrarGestionLocales = mostrarGestionLocales;
	}


	public boolean isMostrarGestionForos() {
		return mostrarGestionForos;
	}


	public void setMostrarGestionForos(boolean mostrarGestionForos) {
		this.mostrarGestionForos = mostrarGestionForos;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public SelectItem[] getSelectProvincia() {
		return selectProvincia;
	}


	public void setSelectProvincia(SelectItem[] selectProvincia) {
		this.selectProvincia = selectProvincia;
	}


	public List<TipoLocal> getListaTiposLocales() {
		return listaTiposLocales;
	}


	public void setListaTiposLocales(List<TipoLocal> listaTiposLocales) {
		this.listaTiposLocales = listaTiposLocales;
	}


	public List<Foro> getListaForos() {
		return listaForos;
	}
	public void setListaForos(List<Foro> listaForos) {
		this.listaForos = listaForos;
	}
	public List<Opinion> getListaOpiniones() {
		return listaOpiniones;
	}
	public void setListaOpiniones(List<Opinion> listaOpiniones) {
		this.listaOpiniones = listaOpiniones;
	}


	public List<Puntuacion> getListaValoraciones() {
		return listaValoraciones;
	}


	public void setListaValoraciones(List<Puntuacion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}


	public List<MensajeForo> getListaMensajes() {
		return listaMensajes;
	}


	public void setListaMensajes(List<MensajeForo> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}


	public List<Local> getListaDestacados() {
		return listaDestacados;
	}


	public void setListaDestacados(List<Local> listaDestacados) {
		this.listaDestacados = listaDestacados;
	}



}
