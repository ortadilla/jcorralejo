package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.USUARIO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.UsuarioDAO;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.UsuarioImpl;

@Name(USUARIO_DAO)
@Scope(CONVERSATION)
public class UsuarioDAOHibernate extends HibernateDAOGenerico<Usuario, Integer> implements UsuarioDAO {


	public UsuarioDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public UsuarioDAOHibernate(){
		super(UsuarioImpl.class);
	}

}
