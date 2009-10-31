package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PUNTUACION_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.PuntuacionDAO;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.implementacion.PuntuacionImpl;

@Name(PUNTUACION_DAO)
@Scope(CONVERSATION)
public class PuntuacionDAOHibernate extends HibernateDAOGenerico<Puntuacion, Integer> implements PuntuacionDAO{

	public PuntuacionDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public PuntuacionDAOHibernate(){
		super(PuntuacionImpl.class);
	}

}
