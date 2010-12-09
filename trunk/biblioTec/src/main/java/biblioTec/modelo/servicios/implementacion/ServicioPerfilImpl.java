package biblioTec.modelo.servicios.implementacion;

import static biblioTec.utilidades.NombresBean.PERFIL_DAO;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERFIL;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.PerfilDAO;
import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.servicios.ServicioPerfil;

@Scope(CONVERSATION)
@Name(SERVICIO_PERFIL)
public class ServicioPerfilImpl implements ServicioPerfil{

	@In(value=PERFIL_DAO, create=true)
	private PerfilDAO perfilDAO;
	
	public List<Perfil> encontrarTodos() {
		return perfilDAO.encontrarTodos();
	}

}
