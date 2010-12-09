package biblioTec.bean;

import static biblioTec.utilidades.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static biblioTec.utilidades.NombresBean.GESTION_USUARIOS_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERFIL;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
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
import org.jboss.seam.annotations.Scope;

import biblioTec.binding.GestionUsuariosBinding;
import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioPerfil;
import biblioTec.modelo.servicios.ServicioUsuario;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.NombresBean;
import biblioTec.utilidades.SelectItemBuilder;


@Scope(CONVERSATION)
@Name(GESTION_USUARIOS_BEAN)
public class GestionUsuariosBean {

	private List<Usuario> listaUsuarios;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private String criterioUsuario;
	private Perfil criterioPerfil;
	
	private SelectItem[] selectPerfil;

	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=SERVICIO_PERFIL, create=true)
	private ServicioPerfil servicioPerfil;
	
	@In(value=NombresBean.GESTION_USUARIOS_BINDING, create=true)
	private GestionUsuariosBinding binding;
	
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

}
