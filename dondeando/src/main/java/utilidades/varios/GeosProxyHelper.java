package utilidades.varios;

import org.hibernate.proxy.HibernateProxy;
import org.jboss.seam.intercept.Proxy;

public class GeosProxyHelper {
	
	public static Class<?> devolverClaseReal(Object objeto) {
		if(HibernateProxy.class.isAssignableFrom(objeto.getClass())) {
			return ((HibernateProxy)objeto).getHibernateLazyInitializer().getImplementation().getClass();
		}
		else if(Proxy.class.isAssignableFrom(objeto.getClass())) {
			return org.jboss.seam.util.Proxy.deproxy(objeto.getClass());
		}
		
		return objeto.getClass();
	}
}
