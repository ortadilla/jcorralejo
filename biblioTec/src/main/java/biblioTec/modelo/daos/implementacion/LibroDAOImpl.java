package biblioTec.modelo.daos.implementacion;

import static biblioTec.utilidades.NombresBean.LIBRO_DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.LibroDAO;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.implementacion.LibroImpl;
import biblioTec.utilidades.HerramientasCriteria;
import biblioTec.utilidades.NombresBean;

@Scope(ScopeType.CONVERSATION)
@Name(LIBRO_DAO)
public class LibroDAOImpl implements LibroDAO {
	
	@In(value=NombresBean.SESION_HIBERNATE, create=true)
	private Session session;
	
	@SuppressWarnings("unchecked")
	public List<Libro> encontrarLibrosPorTituloAutorYIsbn(String titulo, String autor, String isbn) {

		Criteria criteria = session.createCriteria(LibroImpl.class);
		
    	if(titulo!=null && !"".equals(titulo))
    		criteria.add(Restrictions.like(Libro.ATRIBUTO_TITULO, HerramientasCriteria.getValorLike(titulo)));
    	if(autor!=null){
    		criteria.add(Restrictions.like(Libro.ATRIBUTO_AUTOR, HerramientasCriteria.getValorLike(autor)));
    	}
    	if(isbn!=null){
    		criteria.add(Restrictions.like(Libro.ATRIBUTO_ISBN, HerramientasCriteria.getValorLike(isbn)));
    	}
    	
    	return criteria.list();
	}

    public void flushear() {
    	session.flush();
    }
    
    public void guadar(Libro libro) {
    	session.saveOrUpdate(libro);
    }

    public void borrar(Libro libro) {
    	session.delete(libro);
    }   

}
