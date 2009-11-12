package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_FOROS;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENU_PRINCIPAL_BEAN;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
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
import utilidades.varios.SelectItemBuilder;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioProvincia;
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
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;

	
	private boolean mostrarGestionUsuarios;
	private boolean mostrarGestionLocales;
	private boolean mostrarGestionForos;
	private Provincia provincia;
	private SelectItem[] selectProvincia;
	private List<TipoLocal> listaTiposLocales;
	
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

}
