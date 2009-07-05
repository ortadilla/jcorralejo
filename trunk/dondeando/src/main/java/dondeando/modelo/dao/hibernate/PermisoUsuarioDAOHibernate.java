package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PERMISO_USUARIO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.PermisoUsuarioDAO;
import dondeando.modelo.entidades.PermisoUsuario;
import dondeando.modelo.entidades.implementacion.PermisoUsuarioImpl;

@Name(PERMISO_USUARIO_DAO)
@Scope(CONVERSATION)
public class PermisoUsuarioDAOHibernate extends HibernateDAOGenerico<PermisoUsuario, Integer> implements PermisoUsuarioDAO {

	public PermisoUsuarioDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public PermisoUsuarioDAOHibernate(){
		super(PermisoUsuarioImpl.class);
	}
}
