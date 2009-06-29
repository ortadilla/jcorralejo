package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PRUEBA_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.entidades.Ejemplo;

@Scope(CONVERSATION)
@Name(PRUEBA_DAO)
public class PruebaDAOHibernate extends HibernateDAOGenerico<Ejemplo, Integer> {

	public PruebaDAOHibernate(Class<Ejemplo> clasePersistente) {
		super(clasePersistente);
	}
	
	public PruebaDAOHibernate() {
		super(Ejemplo.class);
	}


}
