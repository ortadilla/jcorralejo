package dondeando.binding;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailHeader;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.NombresBean;

@Name(NombresBean.GESTION_NOTIFICACIONES_BINDING)
@Scope(ScopeType.EVENT)
public class GestionNotificacionesBinding {
	
	private CoreShowDetailHeader busqueda;

	public CoreShowDetailHeader getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(CoreShowDetailHeader busqueda) {
		this.busqueda = busqueda;
	}

}
