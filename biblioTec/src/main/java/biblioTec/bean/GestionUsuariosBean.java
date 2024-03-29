package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.DEVOLVER_OBJETO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.OBJETO_DEVUELTO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.VOLVER_A;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_USUARIO;
import static biblioTec.utilidades.NombresBean.GESTION_USUARIOS_BEAN;
import static biblioTec.utilidades.NombresBean.GESTION_USUARIOS_BINDING;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERFIL;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.List;

import javax.faces.model.SelectItem;

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

import biblioTec.binding.GestionUsuariosBinding;
import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioPerfil;
import biblioTec.modelo.servicios.ServicioUsuario;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.SelectItemBuilder;
import biblioTec.utilidades.UtilJsfContext;


@Scope(CONVERSATION)
@Name(GESTION_USUARIOS_BEAN)
public class GestionUsuariosBean {

	private List<Usuario> listaUsuarios;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private String criterioUsuario;
	private Perfil criterioPerfil;
	private boolean buscando;
	private String volverA;
	private SelectItem[] selectPerfil;

	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;

	@In(value=SERVICIO_PERFIL, create=true)
	private ServicioPerfil servicioPerfil;

	@In(value=GESTION_USUARIOS_BINDING, create=true)
	private GestionUsuariosBinding binding;

	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;

	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private biblioTec.utilidades.MapaArgumentos mapaArgumentos;

	private MensajesCore mensajesCore = MensajesCore.instancia();

	@Create
	@Begin(join=true)
	public void inicializar(){
		selectPerfil = SelectItemBuilder.creaSelectItems(servicioPerfil.encontrarTodos(), 
				null, 
				Perfil.ATRIBUTO_DESCRIPCION,
				true);
		desplegado = true;
	}

	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null && mapaArgumentos.contiene(DEVOLVER_OBJETO)){
			buscando = true;
			if(mapaArgumentos.contiene(VOLVER_A))
				volverA = (String)mapaArgumentos.getArgumento(VOLVER_A);
		}
	}


	public void buscar(){
		listaUsuarios = servicioUsuario.encontrarUsuariosPorLoginYPerfil(criterioUsuario, criterioPerfil);
		desplegado = false;
		if(binding.getBusqueda()==null) binding.setBusqueda(new CoreShowDetailHeader());
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaUsuarios!=null && listaUsuarios.size()==1)
			estadoDeSeleccionTabla.add(0);
	}

	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioUsuario = null;
		criterioPerfil = null;
	}

	@End
	public String menuPrincipal(){
		return MENU_PRINCIPAL;
	}

	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaUsuarios != null ? listaUsuarios.size() : "0");
	}

	public String detalles(){

		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(USUARIO, usuario);
			mapaArgumentos.setArgumento(ACCION, ACCION_DETALLES_USUARIO);
			outcome = MTO_USUARIO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}

	public String modificar(){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(USUARIO, usuario);
			mapaArgumentos.setArgumento(ACCION, ACCION_EDITAR_USUARIO);
			outcome = MTO_USUARIO;
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}

		return outcome;
	}

	public String agregar(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(ACCION, ACCION_ANIADIR_USUARIO);

		return MTO_USUARIO;
	}

	public void eliminar(){
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(!servicioUsuario.tienePrestamosPendientes(usuario)){
				servicioUsuario.borrarUsuario(usuario);
				listaUsuarios.remove(usuario);
				estadoDeSeleccionTabla.clear();
			}else{
				utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("PRESTAMOS_PENDIENTES"));
			}
		}else{
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		}
	}

	public String cancelar(){
		String outcome = volverA!=null ? volverA : "";
		limpiarFormulario();
		return outcome;
	}

	private void limpiarFormulario(){
		estadoDeSeleccionTabla.clear();
		buscando = false;
		listaUsuarios = null;
		criterioPerfil = null;
		criterioUsuario = null;
		desplegado = true;
		binding = null;		
		volverA = null;
	}

	public String aceptar(){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(OBJETO_DEVUELTO, usuario);

			outcome = volverA!=null ? volverA : "";
			limpiarFormulario();
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));

		return outcome;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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

	public String getCriterioUsuario() {
		return criterioUsuario;
	}

	public void setCriterioUsuario(String criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}

	public Perfil getCriterioPerfil() {
		return criterioPerfil;
	}

	public void setCriterioPerfil(Perfil criterioPerfil) {
		this.criterioPerfil = criterioPerfil;
	}

	public SelectItem[] getSelectPerfil() {
		return selectPerfil;
	}

	public void setSelectPerfil(SelectItem[] selectPerfil) {
		this.selectPerfil = selectPerfil;
	}

	public GestionUsuariosBinding getBinding() {
		return binding;
	}

	public void setBinding(GestionUsuariosBinding binding) {
		this.binding = binding;
	}

	public boolean isBuscando() {
		return buscando;
	}

	public void setBuscando(boolean buscando) {
		this.buscando = buscando;
	}

}
