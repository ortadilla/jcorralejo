package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.INTERES_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.InteresDAO;
import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.implementacion.InteresImpl;

@Name(INTERES_DAO)
@Scope(CONVERSATION)
public class InteresDAOHibernate extends HibernateDAOGenerico<Interes, Integer> implements InteresDAO{

	public InteresDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public InteresDAOHibernate(){
		super(InteresImpl.class);
	}

}
