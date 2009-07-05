package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.SERVICIO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ServicioDAO;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.implementacion.ServicioImpl;

@Name(SERVICIO_DAO)
@Scope(CONVERSATION)
public class ServicioDAOHibernate extends HibernateDAOGenerico<Servicio, Integer> implements ServicioDAO{
	
	public ServicioDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public ServicioDAOHibernate(){
		super(ServicioImpl.class);
	}

}
