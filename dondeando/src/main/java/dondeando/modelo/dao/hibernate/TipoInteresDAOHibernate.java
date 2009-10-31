package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.TIPO_INTERES_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoInteresDAO;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.implementacion.TipoInteresImpl;

@Name(TIPO_INTERES_DAO)
@Scope(CONVERSATION)
public class TipoInteresDAOHibernate extends HibernateDAOGenerico<TipoInteres, Integer> implements TipoInteresDAO{

	public TipoInteresDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public TipoInteresDAOHibernate(){
		super(TipoInteresImpl.class);
	}

}
