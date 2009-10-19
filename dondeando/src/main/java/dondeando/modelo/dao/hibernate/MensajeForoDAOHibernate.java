package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.MENSAJE_FORO_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.MensajeForoDAO;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.implementacion.MensajeForoImpl;

@Name(MENSAJE_FORO_DAO)
@Scope(CONVERSATION)
public class MensajeForoDAOHibernate extends HibernateDAOGenerico<MensajeForo, Integer> implements MensajeForoDAO{

	public MensajeForoDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public MensajeForoDAOHibernate(){
		super(MensajeForoImpl.class);
	}

}
