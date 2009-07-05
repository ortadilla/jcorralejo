package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.TIPO_LOCAL_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoLocalDAO;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.entidades.implementacion.TipoLocalImpl;

@Name(TIPO_LOCAL_DAO)
@Scope(CONVERSATION)
public class TipoLocalDAOHibernate extends HibernateDAOGenerico<TipoLocal, Integer> implements TipoLocalDAO{

	public TipoLocalDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public TipoLocalDAOHibernate(){
		super(TipoLocalImpl.class);
	}
}
