package biblioTec.modelo.daos.implementacion;

import static biblioTec.utilidades.NombresBean.PERMISO_PERFIL_DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PermisoPerfilDAO;
import biblioTec.modelo.entidades.PermisoPerfil;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.PermisoPerfilImpl;
import biblioTec.utilidades.NombresBean;

@Scope(ScopeType.CONVERSATION)
@Name(PERMISO_PERFIL_DAO)
public class PermisoPerfilDAOImpl implements PermisoPerfilDAO{

	@In(value=NombresBean.SESION_HIBERNATE, create=true)
	private Session session;

	public boolean hayPermisoParaUsuario(Usuario usuario, Integer permiso) {
		boolean hay = false;
		if(!usuario.getPerfiles().isEmpty()){
			Criteria criteria = session.createCriteria(PermisoPerfilImpl.class);
			criteria.add(Restrictions.eq(PermisoPerfil.ATRIBUTO_PERMISO, permiso));
			criteria.add(Restrictions.in(PermisoPerfil.ATRIBUTO_PERFIL, usuario.getPerfiles()));
			hay = !criteria.list().isEmpty();
		}
		return hay;
	}

}
