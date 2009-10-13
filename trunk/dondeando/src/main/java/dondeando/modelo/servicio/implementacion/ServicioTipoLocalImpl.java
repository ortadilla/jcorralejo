package dondeando.modelo.servicio.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.TIPO_LOCAL_DAO;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import dondeando.modelo.dao.TipoLocalDAO;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(CONVERSATION)
@Name(SERVICIO_TIPO_LOCAL)
public class ServicioTipoLocalImpl implements ServicioTipoLocal{

	
	 @In(value=TIPO_LOCAL_DAO, create=true) 
	 private TipoLocalDAO tipoLocalDAO;
	 
	 @In(value=SERVICIO_CRITERIOS, create=true) 
	 private ServicioCriterios servicioCriterios;
	 

	 /*
	  * (non-Javadoc)
	  * @see dondeando.modelo.servicio.ServicioTipoLocal#encontrarTodos()
	  */
	 public List<TipoLocal> encontrarTodos() {
		return tipoLocalDAO.encontrarTodos();
	 }

	 /*
	  * (non-Javadoc)
	  * @see dondeando.modelo.servicio.ServicioTipoLocal#encontrarPorIds(java.util.List)
	  */
	 public List<TipoLocal> encontrarPorIds(List<Integer> ids){
		 return tipoLocalDAO.encontrarPorCondicion(servicioCriterios.construyeCriterio(TipoLocal.ATRIBUTO_ID, Criterio.EN, ids));
	 }
}
