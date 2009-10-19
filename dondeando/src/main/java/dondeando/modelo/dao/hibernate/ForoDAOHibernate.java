package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.FORO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ForoDAO;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.implementacion.ForoImpl;

@Name(FORO_DAO)
@Scope(CONVERSATION)
public class ForoDAOHibernate extends HibernateDAOGenerico<Foro, Integer> implements ForoDAO{

	public ForoDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public ForoDAOHibernate(){
		super(ForoImpl.class);
	}

}
