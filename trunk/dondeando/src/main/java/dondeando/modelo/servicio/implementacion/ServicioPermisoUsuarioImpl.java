package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.PERMISO_USUARIO_DAO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;

import dondeando.modelo.dao.PermisoUsuarioDAO;
import dondeando.modelo.entidades.PermisoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_PERMISO_USUARIO)
public class ServicioPermisoUsuarioImpl implements ServicioPermisoUsuario{
	
	@In(value=PERMISO_USUARIO_DAO, create=true) 
    private PermisoUsuarioDAO permisoUsuarioDAO;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=SERVICIO_CRITERIOS, create=true)
	private ServicioCriterios servicioCriterios;
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioPermisoUsuario#hayPermiso(int)
	 */
	public boolean hayPermiso(int permiso) {
		
		Usuario usuario = servicioUsuario.devolverUsuarioActivo();
		Criterio criterioTipoUsuario = servicioCriterios.construyeCriterio(PermisoUsuario.ATRIBUTO_TIPO_USUARIO,
																		   Criterio.IGUAL, 
																		   usuario.getTipoUsuario());
		Criterio criterioPermiso = servicioCriterios.construyeCriterio(PermisoUsuario.ATRIBUTO_PERMISO, 
																	   Criterio.IGUAL, 
																	   permiso);	
		List<PermisoUsuario> permisos = permisoUsuarioDAO.encontrarPorCondicion(criterioPermiso, criterioTipoUsuario);
		
		return !permisos.isEmpty();
	}

}
