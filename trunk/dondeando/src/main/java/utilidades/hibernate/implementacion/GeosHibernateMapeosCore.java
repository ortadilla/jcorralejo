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
        mapeos.add(path+"Local.hbm.xml");
        mapeos.add(path+"ImagenLocal.hbm.xml");
        mapeos.add(path+"Provincia.hbm.xml");
        mapeos.add(path+"TipoVia.hbm.xml");
        mapeos.add(path+"Direccion.hbm.xml");
        mapeos.add(path+"Foro.hbm.xml");
        mapeos.add(path+"MensajeForo.hbm.xml");
        mapeos.add(path+"Opinion.hbm.xml");
        mapeos.add(path+"Puntuacion.hbm.xml");
        mapeos.add(path+"TipoInteres.hbm.xml");
        mapeos.add(path+"Interes.hbm.xml");
        mapeos.add(path+"Notificacion.hbm.xml");
        
        return mapeos;
    }

	public List<String> dameMapeosModuloyDependientes() {
		return dameMapeosModulo();
	}
}
