package biblioTec.bean;

import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_LIBROS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_USUARIOS;
import static biblioTec.utilidades.NombresBean.MENU_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERMISO_PERFIL;
import static org.jboss.seam.ScopeType.CONVERSATION;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.servicios.ServicioPermisoPerfil;
import biblioTec.utilidades.Permisos;

@Scope(CONVERSATION)
@Name(MENU_BEAN)
public class MenuBean {
	
	@In(value=SERVICIO_PERMISO_PERFIL, create=true)
	private ServicioPermisoPerfil servicioPermisoPerfil;
	
	private boolean mostrarGestionUsuarios;
	private boolean mostrarGestionLibros;
	private boolean mostrarGestionPrestamos;
	
	
	public void cargarArgumentosDeEntrada(){
		mostrarGestionUsuarios = servicioPermisoPerfil.hayPermiso(Permisos.GESTIONAR_USUARIOS); 
		mostrarGestionLibros = servicioPermisoPerfil.hayPermiso(Permisos.GESTIONAR_LIBROS)
							|| servicioPermisoPerfil.hayPermiso(Permisos.CONSULTAR_LIBROS);
		mostrarGestionPrestamos = servicioPermisoPerfil.hayPermiso(Permisos.GESTIONAR_PRESTAMOS)
							   || servicioPermisoPerfil.hayPermiso(Permisos.CONSULTAR_PRESTAMOS);
	}
	
	public String gestionarUsuarios(){
		return GESTIONAR_USUARIOS;
	}
	
	public String gestionarLibros(){
		return GESTIONAR_LIBROS;
	}
	
	public String gestionarPrestamos(){
		return GESTIONAR_PRESTAMOS;
	}
	
	
	public boolean isMostrarGestionUsuarios() {
		return mostrarGestionUsuarios;
	}
	public void setMostrarGestionUsuarios(boolean mostrarGestionUsuarios) {
		this.mostrarGestionUsuarios = mostrarGestionUsuarios;
	}
	public boolean isMostrarGestionLibros() {
		return mostrarGestionLibros;
	}
	public void setMostrarGestionLibros(boolean mostrarGestionLibros) {
		this.mostrarGestionLibros = mostrarGestionLibros;
	}
	public boolean isMostrarGestionPrestamos() {
		return mostrarGestionPrestamos;
	}
	public void setMostrarGestionPrestamos(boolean mostrarGestionPrestamos) {
		this.mostrarGestionPrestamos = mostrarGestionPrestamos;
	}
	
}
