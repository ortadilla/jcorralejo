package dondeando.modelo.servicio.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_USUARIO;
import static utilidades.varios.NombresBean.TIPO_USUARIO_DAO;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoUsuarioDAO;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.servicio.ServicioTipoUsuario;

@Scope(CONVERSATION)
@Name(SERVICIO_TIPO_USUARIO)
public class ServicioTipoUsuarioImpl implements ServicioTipoUsuario {

	 @In(value=TIPO_USUARIO_DAO, create=true) 
	private TipoUsuarioDAO tipoUsuarioDAO;
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioTipoUsuario#devolverTodosTipoUsuario()
	 */
	public List<TipoUsuario> devolverTodosTipoUsuarioMenosAnonimo() {
		List<TipoUsuario> tiposUsuarios = tipoUsuarioDAO.encontrarTodos();
		TipoUsuario tipoAnonimo = null;
		try {
			 tipoAnonimo = tipoUsuarioDAO.encontrarPorId(Integer.valueOf(TipoUsuario.ID_TIPO_USUARIO_ANONIMO));
		} catch (Exception e) {
			// Si no encontramos el tipousuario anónimo no pasa nada,
			// no lo mostramos, que realmente en lo ue buscamos
		}
		if(tipoAnonimo!=null)
			tiposUsuarios.remove(tipoAnonimo);
		
		return tiposUsuarios;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioTipoUsuario#devolverTipoUsuarioRegistrado()
	 */
	public TipoUsuario devolverTipoUsuarioRegistrado() {
		TipoUsuario tipoRegistrado = null;
		try{
			tipoRegistrado = tipoUsuarioDAO.encontrarPorId(Integer.valueOf(TipoUsuario.ID_TIPO_USUARIO_REGISTRADO));
		}catch (Exception e) {
			// Si no encontramos el tipousuario registrado no lo devolvemos
		}
		return tipoRegistrado;
	}

}
