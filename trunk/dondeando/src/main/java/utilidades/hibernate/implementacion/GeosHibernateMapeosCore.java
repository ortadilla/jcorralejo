package utilidades.hibernate.implementacion;

import java.util.ArrayList;
import java.util.List;

import utilidades.hibernate.GeosHibernateMapeos;

public  class GeosHibernateMapeosCore implements GeosHibernateMapeos{
	public static String path="mapeos/";


    public List<String> dameMapeosModulo() {
        List<String> mapeos=new ArrayList<String>();
        
        mapeos.add(path+"Ejemplo.hbm.xml");
        mapeos.add(path+"Usuario.hbm.xml");
        mapeos.add(path+"TipoUsuario.hbm.xml");
        mapeos.add(path+"PermisoUsuario.hbm.xml");
        mapeos.add(path+"Imagen.hbm.xml");
        mapeos.add(path+"TipoLocal.hbm.xml");
        mapeos.add(path+"Servicio.hbm.xml");
        
        return mapeos;
    }

	public List<String> dameMapeosModuloyDependientes() {
		return dameMapeosModulo();
	}
}
