package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.SERVICIO_DAO;
import static utilidades.varios.NombresBean.SERVICIO_SERVICIO;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ServicioDAO;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.servicio.ServicioServicio;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_SERVICIO)
public class ServicioServicioImpl implements ServicioServicio{
	
	 @In(value=SERVICIO_DAO, create=true) 
	 private ServicioDAO servicioDAO;
	 
	 /*
	  * (non-Javadoc)
	  * @see dondeando.modelo.servicio.ServicioServicio#encontrarTodos()
	  */
	 public List<Servicio> encontrarTodos() {
		return servicioDAO.encontrarTodos();
	}

}
