package dondeando.binding;

import static utilidades.varios.NombresBean.GESTION_FOROS_BINDING;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailHeader;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name(GESTION_FOROS_BINDING)
@Scope(ScopeType.EVENT)
public class GestionForosBinding {

	private CoreShowDetailHeader busqueda;

	public CoreShowDetailHeader getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(CoreShowDetailHeader busqueda) {
		this.busqueda = busqueda;
	} 
}
