package utilidades.varios;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Metodos de uso comun para manejo de String's.
 */
public class HerramientasString {

    /**
     * Retorna true si el string recibido es nulo o vacio.
     */
    public static boolean estaVacio(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * Devuelve el nombre del metodo getter de la propiedad indicada
     * @param propiedad Cadena con el nombre de la propiedad de la que se obtendrá su getter.
     * @return Nombre del getter (get+Propiedad). Null si propiedad es null.
     */
    public static String getGetter(String propiedad) {
        if(propiedad==null) return null;
        String p = propiedad.trim();
        return "get" + (p.length()>0 ? p.substring(0,1).toUpperCase()+p.substring(1) : "");
    }		
	
    /**
     * Devuelve el nombre del metodo setter de la propiedad indicada
     * @param propiedad Cadena con el nombre de la propiedad de la que se obtendrá su setter.
     * @return Nombre del setter (set+Propiedad). Null si propiedad es null.
     */
    public static String getSetter(String propiedad) {
        if(propiedad==null) return null;
        String p = propiedad.trim();
        return "set" + (p.length()>0 ? p.substring(0,1).toUpperCase()+p.substring(1) : "");
    }

    /**
     * Devuelve el nombre de la propiedad asociada a un getter/setter.
     * @param getterOSetter Cadena con el nombre del método getter o setrer. Acepta "is..."
     * @return Nombre de la propiedad. Null si getterOSetter es null.
     * @throws IllegalArgumentException Si el nombre del método no es válido (get, set o is...) 
     */
    public static String getPropiedad(String getterOSetter) {
        String propiedad = null;
        if(getterOSetter!=null){
            boolean valido = true;
            if(getterOSetter.startsWith("get") || getterOSetter.startsWith("set"))
                propiedad = getterOSetter.substring(3);
            else if(getterOSetter.startsWith("is"))
                propiedad = getterOSetter.substring(2);
            else
                valido = false;
            
            if(!valido || propiedad.length()<1)
                throw new IllegalArgumentException("El método indicado no es un getter/setter válido");
            
            //Pasamos la 1ª letra a minúscula
            propiedad = propiedad.substring(0,1).toLowerCase()+propiedad.substring(1);
        }
        return propiedad;
    }       
    
    /**
     * Devuelve el valor de una propiedad para un objeto invocando por reflexión a su getter. Si no lo
     * encuentra, lo intenta con "is", por si está a sí definida por ser booleana.
     * @param obj:          Objeto del que se obtiene el valor
     * @param propiedad:    Propiedad del obj que se lee
     * 
     * @return              Valor actual de la propiedad. NULL si el obj o la propiedad son nulos.
     * @throws NoSuchMethodException Si no existe el getter (ni "get" ni "is")
     * @throws Exception             Si por otro motivo no se pudo invocar el método    
     */
    public static Object getValor(Object obj, String propiedad) throws NoSuchMethodException, Exception{
        if(obj==null || propiedad==null)
            return null;
        
        String getter = getGetter(propiedad);
        Method metodo;
        try{
            metodo = obj.getClass().getMethod(getter, (Class[])null);
        }catch(NoSuchMethodException nsme){
            //Lo intentamos con "is", por si la propiedad es boolean
            try{
                metodo = obj.getClass().getMethod(getter.replaceFirst("get", "is"), (Class[])null);
            }catch(NoSuchMethodException nsme2){
                //ponemos un mensaje más acorde para que no despiste con lo de "isXxx"
                throw new NoSuchMethodException("No existe el getter para "+obj.getClass().getSimpleName()+"."+propiedad);
            }
        }
        return metodo.invoke(obj, (Object[])null);
    }

    /**
     * Método que devuelve un número convertido en texto bien formateado:
     *	<ul>
     * 		<li>Utiliza la coma como separador decimal</li>
     * 		<li>Utiliza el punto como separador de miles</li>
     * 		<li>Si tiene más decimales que los indicados realiza un redondeo hacia arriba</li>
     *	</ul>
     *
     * @param java.math.BigDecimal cantidad: El número a ser formateado.
     * @param int nDecimales: El número de decimales a mostrar.
     * @return java.lang.String Número ya formateado
     */
    public static String formatearNumero(BigDecimal importe, int nDecimales) {
    	//Preparo un objeto para realizar el formateo
    	NumberFormat formateo = NumberFormat.getNumberInstance();
    	formateo.setMaximumFractionDigits(nDecimales);
    	formateo.setMinimumFractionDigits(nDecimales);
    	return formateo.format(importe);
    }
    
    
    /**
     * Indica si la cadena "es como" el patrón indicado. La comparación se realiza
     * como el criterio LIKE de BD, usando *, % y ? como comodines. Si no tiene ningún
     * comodin, se añade * a la derecha automáticamente.<br>
     * ES INSENSIBLE a mayúsculas.<br>
     * @param patron: valor que deben cumplir los elementos a obtener, con comodines ?, % y * (o se
     *                añade * a la derecha automáticamente si no hay ninguno)
     * @param cadena: String a comparar con el patrón
     */
    public static boolean isLike(String patron, String cadena){
        if(patron==null || cadena==null)
            return false;
        String regexp = new String(patron.toLowerCase()).replace(".", "\\.")
                                                        .replace("*", ".*")
                                                        .replace("%", ".*") //Éste por similitud con la BD
                                                        .replace('?', '.');
        //Si el usuario no puso comodines, añadimos nosotros al final el asterisco
        if(!regexp.contains(".")) 
            regexp += ".*";
        
        return cadena.toLowerCase().matches(regexp);
    }
}
