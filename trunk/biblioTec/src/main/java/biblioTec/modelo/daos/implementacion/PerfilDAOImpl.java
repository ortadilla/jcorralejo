package biblioTec.modelo.daos.implementacion;

import static biblioTec.utilidades.NombresBean.PERFIL_DAO;

import java.util.List;

import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PerfilDAO;
import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.implementacion.PerfilImpl;
import biblioTec.utilidades.NombresBean;

@Scope(ScopeType.CONVERSATION)
@Name(PERFIL_DAO)
public class PerfilDAOImpl implements PerfilDAO{
	
	@In(value=NombresBean.SESION_HIBERNATE, create=true)
	private Session session;
	
	
	@SuppressWarnings("unchecked")
	public List<Perfil> encontrarTodos() {
		return session.createCriteria(PerfilImpl.class).list();
	}

	

}
