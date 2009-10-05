package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.DIRECCION_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.DireccionDAO;
import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.implementacion.DireccionImpl;

@Name(DIRECCION_DAO)
@Scope(CONVERSATION)
public class DireccionDAOHibernate extends HibernateDAOGenerico<Direccion, Integer> implements DireccionDAO{

	public DireccionDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public DireccionDAOHibernate(){
		super(DireccionImpl.class);
	}

}
