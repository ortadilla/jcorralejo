package dondeando.modelo.servicio.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PROVINCIA_DAO;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ProvinciaDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.servicio.ServicioProvincia;

@Scope(CONVERSATION)
@Name(SERVICIO_PROVINCIA)
public class ServicioProvinciaImpl implements ServicioProvincia{

	@In(value=PROVINCIA_DAO, create=true) 
	private ProvinciaDAO provinciaDAO;


	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioProvincia#encontrarTodos()
	 */
	public List<Provincia> encontrarTodos() {
		return provinciaDAO.encontrarTodos();
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioProvincia#encontrarProvinciaPorId(java.lang.Integer)
	 */
	public Provincia encontrarProvinciaPorId(Integer id) {
		try {
			return provinciaDAO.encontrarPorId(id);
		} catch (DAOExcepcion e) {
			return null;
		}
	}
}
