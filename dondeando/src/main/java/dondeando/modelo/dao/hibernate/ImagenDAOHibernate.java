package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.IMAGEN_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ImagenDAO;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.implementacion.ImagenImpl;

@Name(IMAGEN_DAO)
@Scope(CONVERSATION)
public class ImagenDAOHibernate extends HibernateDAOGenerico<Imagen, Integer> implements ImagenDAO {


	public ImagenDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public ImagenDAOHibernate(){
		super(ImagenImpl.class);
	}
}
