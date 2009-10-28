package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.OPINION_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.OpinionDAO;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.implementacion.OpinionImpl;

@Name(OPINION_DAO)
@Scope(CONVERSATION)
public class OpinionDAOHibernate extends HibernateDAOGenerico<Opinion, Integer> implements OpinionDAO{

	public OpinionDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public OpinionDAOHibernate(){
		super(OpinionImpl.class);
	}

}
