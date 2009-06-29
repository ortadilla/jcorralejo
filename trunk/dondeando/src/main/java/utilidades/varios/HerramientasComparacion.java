package utilidades.varios;

import org.hibernate.proxy.HibernateProxy;


/**
 * Metodos de uso comun para comparaci�n de objetos.
 */
public class HerramientasComparacion {

    /**
     * Retorna true si los dos objetos son nulos o son equals.
     */
    public static boolean sonIgualesONulos(Object obj1, Object obj2) {
    	//Comprobamos si los objetos son proxies antes de comparar, ya que puede ser que en los 
        //'equal' se est� preguntando directamente por el valor de las propiedades y no a trav�s 
        //de su getter. En un proxy, eso podr�a dar lugar a resultados err�neos.
    	if(obj1 instanceof HibernateProxy)
    		obj1 = ((HibernateProxy)obj1).getHibernateLazyInitializer().getImplementation();
    	if(obj2 instanceof HibernateProxy)
    		obj2 = ((HibernateProxy)obj2).getHibernateLazyInitializer().getImplementation();
    	
    	return ((obj1 == null &&  obj2 == null) ||
    			(obj1 != null && obj2 != null && obj1.equals(obj2))
    		   ); 
    }		
    
    /**
     *Compara un objeto con una propiedad de otro
     *@param obj1      Objeto a comparar con la propiedad del otro.
     *@param obj2      Objeto cuya propiedad se comparar�.
     *@param propiedad Nombre de la propiedad del segundo objeto.
     * 
     *@return Cierto si los dos objetos son nulos o 
     *               si el primer objeto es igual a la propiedad del segundo o
     *               si el primer objeto es nulo y la propiedad del segundo es nula.
     * 
     */
    public static boolean sonIgualesONulos(Object obj1, Object obj2,String propiedad) {
        
        if (obj2 == null) return obj1 == null;
            
        // recuperamos el valor de la propiedad
        Object valorPropiedad= null;
        try {
            valorPropiedad = ReflexionUtils.invocaGetter(obj2, propiedad);
        } catch (Exception e) {
            throw new IllegalArgumentException("La propiedad \""+propiedad+"\" no existe en el objeto "+obj2, e);
        }
        
        //Comprobamos si los objetos son proxies antes de comparar, ya que puede ser que en los 
        //'equal' se est� preguntando directamente por el valor de las propiedades y no a trav�s 
        //de su getter. En un proxy, eso podr�a dar lugar a resultados err�neos.
        if(obj1 instanceof HibernateProxy)
    		obj1 = ((HibernateProxy)obj1).getHibernateLazyInitializer().getImplementation();
    	if(obj2 instanceof HibernateProxy)
    		valorPropiedad = ((HibernateProxy)valorPropiedad).getHibernateLazyInitializer().getImplementation();
    	
        return (obj1 == null && valorPropiedad == null) ||
               (obj1 != null && valorPropiedad != null && obj1.equals(valorPropiedad));
    }        
}
