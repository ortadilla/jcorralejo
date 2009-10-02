package dondeando.modelo.servicio.implementacion;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.TIPO_LOCAL_DAO;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.TipoLocalDAO;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(CONVERSATION)
@Name(SERVICIO_TIPO_LOCAL)
public class ServicioTipoLocalImpl implements ServicioTipoLocal{

	
	 @In(value=TIPO_LOCAL_DAO, create=true) 
	 private TipoLocalDAO tipoLocalDAO;
	 
	 
	 public List<TipoLocal> encontrarTodos() {
		return tipoLocalDAO.encontrarTodos();
	}
}
