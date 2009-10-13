package dondeando.modelo.servicio.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_VIA;
import static utilidades.varios.NombresBean.TIPO_VIA_DAO;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoViaDAO;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.servicio.ServicioTipoVia;


@Scope(CONVERSATION)
@Name(SERVICIO_TIPO_VIA)
public class ServicioTipoViaImpl implements ServicioTipoVia {

	@In(value=TIPO_VIA_DAO, create=true) 
	private TipoViaDAO tipoViaDAO;


	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioTipoVia#encontrarTodos()
	 */
	public List<TipoVia> encontrarTodos() {
		return tipoViaDAO.encontrarTodos();
	}

}
