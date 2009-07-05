package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.LOCAL_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.LocalDAO;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.implementacion.LocalImpl;

@Name(LOCAL_DAO)
@Scope(CONVERSATION)
public class LocalDAOHibernate extends HibernateDAOGenerico<Local, Integer> implements LocalDAO{

	public LocalDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public LocalDAOHibernate(){
		super(LocalImpl.class);
	}
}
