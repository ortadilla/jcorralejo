package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.TIPO_USUARIO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoUsuarioDAO;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.implementacion.TipoUsuarioImpl;

@Name(TIPO_USUARIO_DAO)
@Scope(CONVERSATION)
public class TipoUsuarioDAOHibernate extends HibernateDAOGenerico<TipoUsuario, Integer> implements TipoUsuarioDAO {
	
	public TipoUsuarioDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public TipoUsuarioDAOHibernate(){
		super(TipoUsuarioImpl.class);
	}
}
