package utilidades.hibernate.implementacion;

import static org.jboss.seam.InterceptionType.NEVER;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Intercept;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import utilidades.hibernate.GeosHibernateAplicacion;

@Scope(ScopeType.APPLICATION)
@Intercept(NEVER)
@Startup(depends = "org.jboss.seam.core.microcontainer")
public class GeosHibernateAplicacionCore extends GeosHibernateAplicacion {
	
	public List<String> cargarMapeos() {
		return new GeosHibernateMapeosCore().dameMapeosModuloyDependientes();
	
	}

}
