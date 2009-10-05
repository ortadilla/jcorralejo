package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PROVINCIA_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ProvinciaDAO;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.implementacion.ProvinciaImpl;

@Name(PROVINCIA_DAO)
@Scope(CONVERSATION)
public class ProvinciaDAOHibernate extends HibernateDAOGenerico<Provincia, Integer> implements ProvinciaDAO{

	public ProvinciaDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public ProvinciaDAOHibernate(){
		super(ProvinciaImpl.class);
	}

}
