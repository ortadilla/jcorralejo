package utilidades.varios;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.util.Reflections;
import org.jboss.seam.util.Strings;

import dondeando.excepciones.Excepcion;
import dondeando.modelo.dao.DAOGenerico;


/**
 * Clase de utilidades para invocar por reflexión de forma simple getters,
 * setters y métodos de cualquier tipo.
 * 
 * @author rperez
 */
public class ReflexionUtils {
	
	private static Log log = LogFactory.getLog(ReflexionUtils.class);
	
	public static List<Field> obtenerPropiedadesMapeadasClase(Class clase, DAOGenerico dao){
		List<Field> todosCampos=Arrays.asList(clase.getDeclaredFields());
		List<Field> camposMapeados= new ArrayList<Field>();
		for(Field campo: todosCampos){
			if (dao.isMapeada(campo.getName()))
				camposMapeados.add(campo);
		}
		return camposMapeados;
	}
	
	/**
	 * Invoca al getter de la propiedad indicada sobre el objeto indicado.
	 * @param objeto Objeto sobre el que se invoca el método
	 * @param nombrePropiedad Nombre de la propiedad a la que queremos hacer el get.
	 * @return El valor resultante del getter
     * @throws IllegalArgumentException Si por cualquier motivo no se pudo invocar el setter por reflexión.
	 */
	public static Object invocaGetter(Object objeto, String nombrePropiedad) throws IllegalArgumentException {
	    if(objeto==null)
	        return null;
		
	    Class<?> claseObjeto = GeosProxyHelper.devolverClaseReal(objeto);
        Method getter;
        try{
            getter = getGetterMethod(claseObjeto, nombrePropiedad);
        }catch(IllegalArgumentException iae){
            log.info("No existe el getter de la propiedad '"+nombrePropiedad+"' de la clase '"+claseObjeto.getSimpleName()+"'.");
            throw iae;
        }
		try {
			return getter.invoke(objeto);
		} catch (InvocationTargetException e) {
		    log.error("No se ha podido invocar el getter de la propiedad '"+nombrePropiedad+"' de la clase '"+claseObjeto+"'.");
		    throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			log.error("El getter de la propiedad '"+nombrePropiedad+"' en la clase '"+claseObjeto+"' no tiene los modificadores" +
					  "de acceso adecuados para ser llamado por reflexión.");
			throw new IllegalArgumentException(e);
		}
	}
	
	public static Method getGetterMethod(Class<?> clazz, String name)
	{
		Method[] methods = clazz.getMethods();
		for (Method method: methods)
		{
			String methodName = method.getName();
			if ( methodName.matches("^(get|is).*") && method.getParameterTypes().length==0 )
			{
				int offset = methodName.startsWith("is") ? 2 : 3;

				if ( Introspector.decapitalize( methodName.substring(offset) ).equals(name) )
				{
					return method;
				}
			}
		}
		throw new IllegalArgumentException("no such getter method: " + clazz.getName() + '.' + name);
	}
	
	/**
	 * Invoca al setter de la propiedad indicada sobre el objeto indicado.
	 * @param objeto Objeto sobre el que se invoca el método
	 * @param nombrePropiedad Nombre de la propiedad a la que queremos hacer el set.
	 * @param valor Valor a setear en la propiedad.
	 * @throws IllegalArgumentException Si por cualquier motivo no se pudo invocar el setter por reflexión.
	 */
	public static void invocaSetter(Object objeto, String nombrePropiedad, Object valor) throws IllegalArgumentException{
        if(objeto==null)
            return;
		Class<?> claseObjeto = GeosProxyHelper.devolverClaseReal(objeto);
	    Method metodo;
	    try{
	        metodo = Reflections.getSetterMethod(claseObjeto, nombrePropiedad);
	    }catch(IllegalArgumentException iae){
            log.error("No existe el setter de la propiedad '"+nombrePropiedad+"' de la clase '"+claseObjeto.getSimpleName()+"'.");
	        throw iae;
	    }
		try {
            metodo.invoke(objeto, new Object[] {valor});
		} catch (InvocationTargetException e) {
			log.error("No se ha podido invocar el getter de la propiedad '"+nombrePropiedad+"' de la clase '"+claseObjeto+"'.");
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e){
            log.error("El getter de la propiedad '"+nombrePropiedad+"' en la clase '"+claseObjeto+"' no tiene los modificadores" +
                      "de acceso adecuados para ser llamado por reflexión.");
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Invoca el método cuyo nombre se le pasa sobre el objeto que se le pasa también como parámetro.
	 * Los parámetros del método se pasan como una lista de objetos. Deben aparecer en el mismo orden 
	 * con el que aparezcan en la signatura del método.
	 * 
	 * @param nombreMetodo Nombre del método a ejecutar.
	 * @param objeto Objecto sobre el que se ejecuta el método.
	 * @param parametros Parámetros que se le pasan al método.
	 * @return El resultado de la invocación.
	 * @throws Exception Si algo hay ido realmente mal.
	 */
	public static Object invocar(String nombreMetodo, Object objeto, List<Object> parametros) throws Exception {
		Object[] arrayParametros;
		if(parametros!=null) {
			arrayParametros = new Object[parametros.size()];
			arrayParametros = parametros.toArray(arrayParametros);
		}else{
			arrayParametros = new Object[] {};
		}
		return invocar(nombreMetodo, objeto, arrayParametros);
	}
	
	/**
	 * Invoca el método cuyo nombre se le pasa sobre el objeto que se le pasa también como parámetro.
	 * Los parámetros del método se pasan como una lista de objetos. Deben aparecer en el mismo orden 
	 * con el que aparezcan en la signatura del método.
	 * 
	 * @param nombreMetodo Nombre del método a ejecutar.
	 * @param objeto Objecto sobre el que se ejecuta el método.
	 * @param parametros Parámetros que se le pasan al método.
	 * @return El resultado de la invocación.
	 * @throws Exception Si algo hay ido realmente mal.
	 */
	public static Object invocar(String nombreMetodo, Object objeto, Object... parametros) throws Exception {
	    if(nombreMetodo==null) throw new IllegalArgumentException("El nombre del método no puede ser nulo");
	    if(objeto==null)       throw new IllegalArgumentException("El objeto no puede ser nulo");
		
		Method[] metodos = GeosProxyHelper.devolverClaseReal(objeto).getMethods();
		for(Method metodo : metodos) {
			//Si encontramos el método por su nombre y además tiene el mismo número de 
			//parámetros, entonces intentamos invocarlo.
			if(metodo.getName().equals(nombreMetodo)
			&& metodo.getParameterTypes().length==parametros.length) {
			    boolean coinciden = true;
			    for(int i=0; coinciden && i<parametros.length; i++)
			        if(parametros[i]!=null 
			        && !metodo.getParameterTypes()[i].isAssignableFrom(parametros[i].getClass()))
			            coinciden = false;

			    if(coinciden)
                    try {
                        return Reflections.invoke(metodo, objeto, parametros);
                    } catch (Exception e) {
                        throw Excepcion.getCausa(e);
                    }   
			}
		}

        //Si llegamos a este punto, es que no existe el método en la clase 
		throw new NoSuchMethodException("No existe el método "+objeto.getClass().getSimpleName()+"."+nombreMetodo+"() con parámetros: (" + Strings.toClassNameString(", ", parametros) +")");
	}
	
	/** 
     * Copia las propiedades del origen en el destino, exceptuando las excluidas.<p>
     * Normalmente serán ID y Versión, pero como éstas no son fijas para todas las entidades, dejamos
     * que sea el llamador quién las indique, normalmente recurriendo al DAO de la clase y llamando a
     * sus fundiones:
     * <ul>
     *   <li>DAO.getPropiedadId()
     *   <li>DAO.getPropiedadVersion()
     * </ul>
     * @param origen
     * @param destino
     * @param propiedadesExcluidas
     */
	@SuppressWarnings("unchecked")
    public static void copiarPropiedadesObjeto(Object origen, Object destino, String... propiedadesExcluidas) {
    
        Field[] propiedades = GeosProxyHelper.devolverClaseReal(destino).getDeclaredFields();
        
        for(Field propiedad : propiedades) {
            //Se comprueba que no está entre las excluidas
            if(!esIgnorable(propiedad, propiedadesExcluidas)){
                try {
                    Object valor = invocaGetter(origen, propiedad.getName());
                    invocaSetter(destino, propiedad.getName(), valor);
                } catch(Exception e) {
                    log.info("No se ha podido copiar la propiedad \""+propiedad.getName()+"\" para la clase \""+propiedad.getType().getSimpleName()+"\"");
                }
            }else if(Collection.class.isAssignableFrom(propiedad.getType())){
                //Aprovechando que pasamos por aquí, inicializamos las colecciones que estén a null
                try {
                    if(invocaGetter(destino, propiedad.getName())==null)
                        inicializarColeccion(destino, propiedad);
                } catch(Exception e) {
                    log.debug("No se ha podido inicializar la colección \""+propiedad.getName()+"\" para la clase \""+propiedad.getType().getSimpleName()+"\"");
                }
            }
        }               
    }

    /** Inicializa la colección del obj indicado con un HashSet o un ArrayList según su tipo
     * @return Collection con la que se inicializa. NULL si se produce alguna excepción al asignar.
     */
    @SuppressWarnings("unchecked")
    public static Collection<?> inicializarColeccion(Object obj, Field coleccion) {
        Collection<?> col = null;
		if(Set.class.isAssignableFrom(coleccion.getType()))
		    col = new HashSet();
		else if(List.class.isAssignableFrom(coleccion.getType()))
		    col = new ArrayList();
		if(col!=null)
		    invocaSetter(obj, coleccion.getName(), col);
        return col;
    }
	
	/**
	 * Devuelve un nuevo obj de la misma clase y con las propiedades copiadas,
	 * según {@link #copiarPropiedadesObjeto(Object, Object, String...)}
	 * 
	 * @param origen            Obj a copiar.
	 * @return                  Nuevo obj copiado
	 * @throws RuntimeException Si no se puede crear una nueva instancia de la clase del obj.
	 */
	public static Object copiarObjeto(Object origen){
	    Object nuevo;
        try {
            nuevo = GeosProxyHelper.devolverClaseReal(origen).newInstance();
        } catch (Exception e) {
            //la convertimos en Runtime porque no debe pasar el ejecución
            throw new RuntimeException(e);
        }
	    copiarPropiedadesObjeto(origen, nuevo); 
	    return nuevo;
	}
	
	/**
	 * Wrap para el método del core de Seam que obtiene el tipo de una colección.
	 * @param coleccionGenericType El resultado de hacer field.getGenericType()
	 * @return La clase declarada de los elementos de la colección
	 */
	@SuppressWarnings("unchecked")
    public static Class obtenerTipoColeccion(Type coleccionGenericType) {		
		return Reflections.getCollectionElementType(coleccionGenericType);
	}
	
	/**
	 * 
	 * @param clasePadre Clase del objeto Padre (necesaria pq puede que los otros dos objetos sean
	 * 					 proxies y no permitan encontrar el nombre de la propiedad buscada)
	 * @param objPadre   Objeto del que se quiere encontrar el nombre de la propiedad que contiene 
	 * 					 la colección hija
	 * @param objHijo	 Objeto que indica el tipo de los elementos de la colección
	 * @return			 El nombre de la propiedad del objeto padre que contiene la colección del
	 * 					 tipo de los objetos hijo.
	 */
	@SuppressWarnings("unchecked")
    public static String obtenerNombrePropiedadHijo(Class<?> clasePadre, Object objPadre, Object objHijo) {
		String nombrePropBuscado = null;
		
		Field [] propiedades = clasePadre.getDeclaredFields();
		
		for(Field propiedad: propiedades) {
			if(Collection.class.isAssignableFrom(propiedad.getType())
			   && ReflexionUtils.obtenerTipoColeccion(propiedad.getGenericType()).isAssignableFrom(objHijo.getClass())) {
				
				try {
					Collection col = (Collection)ReflexionUtils.invocaGetter(objPadre, propiedad.getName()); 
					if( col!=null && col.contains(objHijo) )
						nombrePropBuscado = propiedad.getName();
				} catch (IllegalArgumentException e) {
					log.error("Los argumentos pasados no son válidos", e);
				}
			}
		}
		
		return nombrePropBuscado;
	}
	
    /**
     * Obtiene el valor de un atributo de un objeto, a través de su getter. El atributo puede
     * ser compuesto, separado por puntos (p.e. "linea.pedido.fechaInicio").<br>
     * Se deben usar constantes de atributo para indicar los nombres.
     * 
     * @param obj: Objeto del que se extrae el atributo.
     * @param atributo: Nombre del atributo. Puede ser compuesto (p.e. "linea.pedido.fechaInicio");
     * 
     * @return Valor del atributo. Si es compuesto y alguno de los valores intermedios es null,
     *         devuelve null.
     * @throws RuntimeException Si alguno de los atributos indicados no es accesible para el obj.
     */
	public static Object getAtributo(Object obj, String atributo){
        String[] atributos = atributo.split("\\.");
        Object valor = obj;
        //Se recorre el array de propiedades hasta la última que es de la que se obtiene el valor
        for(String atr: atributos){
            try {
                if(valor!=null)
                    valor = invocaGetter(valor, atr);
            } catch (Exception e) {
                throw new RuntimeException("Atributo "+atr+" no accesible para "+valor.getClass().getSimpleName());
            }
        }
        return valor;
	}
	
	/**
	 * Indica si una propiedad se puede ignorar para asignaciones por reflexión u otros tratamientos.
	 * Considera ignorables:
	 * <ul>
//	 * <li>Las Colecciones (si así lo indica "ignorarColecciones")
//	 * <li>Las Colecciones
	 * <li>Las propiedades estáticas
	 * <li>Las propiedades finales
	 * <li>Las propiedades indicadas en "propiedadesIgnorables"
	 * </ul>
	 * 
	 * @param propiedad              Campo a evaluar
//	 * @param ignorarColecciones     Si se deben ignorar las colecciones o no.
	 * @param propiedadesIgnorables  Serie de nombres de propiedades que se ignoran
	 * @return Si se puede ignorar la propiedad.
	 */
//	public static boolean esIgnorable(Field propiedad, boolean ignorarColecciones, String... propiedadesIgnorables){
    public static boolean esIgnorable(Field propiedad, String... propiedadesIgnorables){
	    return Collection.class.isAssignableFrom(propiedad.getType())
	        || Modifier.isFinal(propiedad.getModifiers())
	        || Modifier.isStatic(propiedad.getModifiers())
	        || ArrayUtils.contains(propiedadesIgnorables, propiedad.getName()); 
	}
    
    /**
     * Obtiene un mapa con pares [nombrePropiedad, valor] con los
     * valores de las propiedades del objeto, INCLUYENDO LA NULAS.
     * @param objetoConValores Objeto del que queremos extraer los valores
     * @return El mapa con todos los valores del objeto.
     */
    public static Map<String, Object> obtenerMapaValores(Object objetoConValores) {
    	Map<String, Object> res = new HashMap<String, Object>();
    	
    	if(objetoConValores!=null) {
    		Class<?> claseObj = GeosProxyHelper.devolverClaseReal(objetoConValores);

    		for(Field field : claseObj.getDeclaredFields())
    		    res.put(field.getName(), invocaGetter(objetoConValores, field.getName()));
    	}
    	
    	return res;
    }
    
}
 