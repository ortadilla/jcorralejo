package biblioTec.binding;

import static biblioTec.utilidades.NombresBean.GESTION_LIBROS_BINDING;

import static org.jboss.seam.ScopeType.EVENT;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailHeader;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(EVENT)
@Name(GESTION_LIBROS_BINDING)
public class GestionLibrosBinding {
	
	private CoreShowDetailHeader busqueda;

	public CoreShowDetailHeader getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(CoreShowDetailHeader busqueda) {
		this.busqueda = busqueda;
	}

}
