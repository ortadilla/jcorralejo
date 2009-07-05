package dondeando.modelo.dao.hibernate;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.IMAGEN_LOCAL_DAO;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ImagenLocalDAO;
import dondeando.modelo.entidades.ImagenLocal;
import dondeando.modelo.entidades.implementacion.ImagenLocalImpl;

@Name(IMAGEN_LOCAL_DAO)
@Scope(CONVERSATION)
public class ImagenLocalDAOHibernate extends HibernateDAOGenerico<ImagenLocal, Integer> implements ImagenLocalDAO{

	public ImagenLocalDAOHibernate(Class clasePersistente) {
		super(clasePersistente);
	}

	public ImagenLocalDAOHibernate(){
		super(ImagenLocalImpl.class);
	}
}
