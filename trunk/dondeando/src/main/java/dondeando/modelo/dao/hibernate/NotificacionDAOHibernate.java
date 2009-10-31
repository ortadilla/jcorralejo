package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.NOTIFICACION_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.NotificacionDAO;
import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.implementacion.NotificacionImpl;

@Name(NOTIFICACION_DAO)
@Scope(CONVERSATION)
public class NotificacionDAOHibernate extends HibernateDAOGenerico<Notificacion, Integer> implements NotificacionDAO{

	public NotificacionDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public NotificacionDAOHibernate(){
		super(NotificacionImpl.class);
	}

}
