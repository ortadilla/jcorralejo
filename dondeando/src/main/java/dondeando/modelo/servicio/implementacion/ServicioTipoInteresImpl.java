package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.SERVICIO_TIPO_INTERES;
import static utilidades.varios.NombresBean.TIPO_INTERES_DAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoInteresDAO;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.servicio.ServicioTipoInteres;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_TIPO_INTERES)
public class ServicioTipoInteresImpl implements ServicioTipoInteres{
	
	//Utilidades
	@In(value= TIPO_INTERES_DAO, create=true)
	private TipoInteresDAO tipoInteresDAO;
	
	private Log log = LogFactory.getLog(ServicioTipoInteresImpl.class);
	
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioTipoInteres#encontrarTodos()
	 */
	public List<TipoInteres> encontrarTodos() {
		return tipoInteresDAO.encontrarTodos();
	}

}
