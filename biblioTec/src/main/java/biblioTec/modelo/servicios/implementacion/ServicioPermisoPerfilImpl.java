package biblioTec.modelo.servicios.implementacion;

import static biblioTec.utilidades.NombresBean.PERMISO_PERFIL_DAO;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERMISO_PERFIL;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PermisoPerfilDAO;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioPermisoPerfil;
import biblioTec.modelo.servicios.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_PERMISO_PERFIL)
public class ServicioPermisoPerfilImpl implements ServicioPermisoPerfil{
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=PERMISO_PERFIL_DAO, create=true)
	private PermisoPerfilDAO permisoPerfilDAO;
	
	public boolean hayPermiso(int permiso) {
		Usuario usuario = servicioUsuario.devolverUsuarioActivo();
		return permisoPerfilDAO.hayPermisoParaUsuario(usuario, permiso);
	}

}
