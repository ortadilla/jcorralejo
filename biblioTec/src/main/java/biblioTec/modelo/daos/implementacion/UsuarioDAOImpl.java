package biblioTec.modelo.daos.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.UsuarioDAO;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.UsuarioImpl;
import biblioTec.utilidades.NombresBean;

@Name(NombresBean.USUARIO_DAO)
@Scope(CONVERSATION)
public class UsuarioDAOImpl implements UsuarioDAO{
	
    @In(value=NombresBean.SESION_HIBERNATE, create=true)
    private Session session;
	
	
	@SuppressWarnings("unchecked")
	public Usuario encontrarUsuarioPorLogin(String login) {
		if (login == null) 
			throw new IllegalArgumentException("El argumento login no debe ser nulo");
		
		Criteria criteria = session.createCriteria(UsuarioImpl.class);
		criteria.add(Restrictions.eq(Usuario.ATRIBUTO_LOGIN, login));
		
		List<Usuario> res = criteria.list();
		
		return res.size() > 0 ? res.get(0) : null;
	}
	
	public Usuario encontrarPorId(Integer id) {
		return (Usuario) session.get(UsuarioImpl.class, id);
	}

}
