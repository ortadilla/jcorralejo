package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.TIPO_VIA_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoViaDAO;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.entidades.implementacion.TipoViaImpl;

@Name(TIPO_VIA_DAO)
@Scope(CONVERSATION)
public class TipoViaDAOHibernate extends HibernateDAOGenerico<TipoVia, Integer> implements TipoViaDAO{

	public TipoViaDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public TipoViaDAOHibernate(){
		super(TipoViaImpl.class);
	}

}
