package biblioTec.modelo.daos.implementacion;

import static biblioTec.utilidades.NombresBean.PRESTAMO_DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PrestamoDAO;
import biblioTec.modelo.entidades.Libro;
import biblioTec.modelo.entidades.Prestamo;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.PrestamoImpl;
import biblioTec.utilidades.NombresBean;

@Scope(ScopeType.CONVERSATION)
@Name(PRESTAMO_DAO)
public class PrestamoDAOImpl implements PrestamoDAO {

	@In(value=NombresBean.SESION_HIBERNATE, create=true)
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Prestamo> encontrarPrestamoPorUsuarioLibroFechaInicioYfechaFin(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin) {
		Criteria criteria = session.createCriteria(PrestamoImpl.class);

		if(usuario!=null){
			criteria.add(Restrictions.eq(Prestamo.ATRIBUTO_USUARIO, usuario));
		}    	
		if(libro!=null){
			criteria.add(Restrictions.eq(Prestamo.ATRIBUTO_LIBRO, libro));
		}    	
		if(fechaInicio!=null){
			criteria.add(Restrictions.eq(Prestamo.ATRIBUTO_FECHA_INICIO, fechaInicio));
		}    	
		if(fechaFin!=null){
			criteria.add(Restrictions.eq(Prestamo.ATRIBUTO_FECHA_FIN, fechaFin));
		}    	
		
		return criteria.list();
	}

	public void flushear() {
		session.flush();
	}

	public void guadar(Prestamo prestamo) {
		session.saveOrUpdate(prestamo);
	}

	public void borrar(Prestamo prestamo) {
		session.delete(prestamo);
	}   


}
