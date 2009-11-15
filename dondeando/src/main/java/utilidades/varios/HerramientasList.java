package utilidades.varios;

import java.util.*;


/**
 * Utilidad para ordenar listas según propiedades de sus objetos.
 * 
 */
public class HerramientasList {

    /**
     * Ordena una lista según las propiedades indicadas (usando CONSTANTES). La propiedad
     * final por la que se ordena debe implementar Comparable. Acepta:
     * <ul>
     * <li> Una o varias propiedades: ordenar(lista, "fechaInicio", "codigo");
     * <li> Ordenación descendente:   ordenar(lista, "fechaInicio DESC", "codigo");
     * <li> Propiedades compuestas:   ordenar(lista, "linea.pedido.fechaInicio");
     * </ul>
     * <br>
     * Los elementos nulos o con alguna de las propiedades indicadas nula se considerar
     * los mayores (como hace Oracle).
     * <br>
     * El orden es estable (mantiene el orden de elementos "iguales") y de orden n*log(n).
     * <br>
     * MODIFICA la lista original.
     * 
     * @param lista     Lista a ordenar.
     * @param string    Una o varias propiedades. Usar CONSTANTES de atributos. Si no se indica
     * 					ninguna propiedad, se intentará ordenar por su orden natural.
     *
     * @return          La MISMA lista ya ordenada.
     * @throws RuntimeException Si alguna de las propiedades no existe en los objs de la lista o no se
     *                          indica ninguna propiedad.
     * @see Comparable
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> ordenar(List<T> lista, final String... propiedades) {
        return ordenar(lista, true, propiedades);
    }

    /**
     * Igual que {@link #ordenar(List, String...)} pero:
     * @param nulosSonMayores   Indica si los nulos se consideran los mayores (true) o los menores (false)
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> ordenar(List<T> lista, final boolean nulosSonMayores, final String... propiedades) {
        if(propiedades.length==0)
        	return ordenar_priv(lista, null, nulosSonMayores);

        Comparator<T> comparator = new Comparator<T>(){
            @SuppressWarnings("unchecked")
            public int compare(T obj1, T obj2) {
                for(int i=0; i<propiedades.length; i++){
                    String[] propiedad = propiedades[i].split(" ");
                    boolean ascendente = propiedad.length == 1 || 
                                         !propiedad[1].toUpperCase().equals("DESC");
                    
                    Object valor1 = ReflexionUtils.getAtributo(obj1, propiedad[0]);
                    Object valor2 = ReflexionUtils.getAtributo(obj2, propiedad[0]);
                    if(valor1!=null || valor2!=null){ //Dos nulos se consideran iguales, no se viola el orden
                        if(valor1==null)
                            return ascendente ^ nulosSonMayores ? -1 : 1;
                        else if(valor2==null)
                            return ascendente ^ nulosSonMayores ? 1 : -1;
                        else{
                            int res = ((Comparable)valor1).compareTo(valor2);
                            if(res != 0)
                                return ascendente ? res : -res;
                        }
                    }
                }
                return 0;
            }
        
        };
        
        return ordenar_priv(lista, comparator, nulosSonMayores); 
    }
    
    /**
     * Obtiene la lista de elementos contenido en la Colección enviada por parámetro
     * donde el valor del atributo coincide con el valor recibido en los parámetros.<br>
     * El orden de la lista será el mismo que devuelva el Iterator de la colección.
     * 
     * @param coleccion: Colección de la cual queremos extraer los coincidentes
     * @param atributo: nombre del atributo a invocar para obtener el valor determinante. Puede ser
     *                  compuesto (p.e. "linea.pedido.fechaInicio");
     * @param valor: valor que deben cumplir los elementos a obtener
     * 
     * @return Una lista del mismo tipo que la recibida pero con los elementos coincidentes únicamente, en
     *         caso de no encontrar ningún elemento coincidente, devolverá una lista vacía.
     */
    public static <T> List<T> obtenerElementos(Collection<T> coleccion, String atributo, Object valor){
        List<T> elementosCoincidentes = new ArrayList<T>();

        //Se recorre la lista de elementos para buscar alguno que cumpla la condicion del valor
        for(T elemento: coleccion ){
            Object valorObtenido = ReflexionUtils.getAtributo(elemento, atributo);
            //Si el valor obtenido es igual que el que se nos pasa por parámetro, entonces este elemento será
            //uno de los que buscamos y por ello se introduce en la lista de resultados
            if(HerramientasComparacion.sonIgualesONulos(valor, valorObtenido))
                elementosCoincidentes.add(elemento);
        }
        return elementosCoincidentes;
    }
    
    /**
     * Obtiene la lista de elementos contenido en la Colección enviada por parámetro
     * donde el valor del atributo "es como" el valor recibido como parámetro. La comparación
     * se obtiene como el criterio LIKE de BD, usando * y ? como comodines. Si no tiene ningún
     * comodin, se añade * a la derecha automáticamente.<br>
     * ES INSENSIBLE a mayúsculas.<br>
     * Sólo tiene sentido para ATRIBUTOS STRING.<br>
     * El orden de la lista será el mismo que devuelva el Iterator de la colección.
     * 
     * @param coleccion: Colección de la cual queremos extraer los coincidentes
     * @param atributo: nombre del atributo a invocar para obtener el valor determinante. Puede ser
     *                  compuesto (p.e. "linea.pedido.fechaInicio");
     * @param expresionRegular: valor que deben cumplir los elementos a obtener, con comodines ? y * (o se
     *                          añade * a la derecha automáticamente)
     * 
     * @return Una lista del mismo tipo que la recibida pero con los elementos coincidentes únicamente, en
     *         caso de no encontrar ningún elemento coincidente, devolverá una lista vacía.
     */
    public static <T> List<T> obtenerElementosLike(Collection<T> coleccion, String atributo, String expresionRegular){
        List<T> elementosCoincidentes = new ArrayList<T>();

        //Se recorre la lista de elementos para buscar alguno que cumpla la condicion del valor
        for(T elemento: coleccion ){
            Object valorObtenido = ReflexionUtils.getAtributo(elemento, atributo);
            //Si el valor obtenido es igual que el que se nos pasa por parámetro, entonces este elemento será
            //uno de los que buscamos y por ello se introduce en la lista de resultados
            if((expresionRegular == null &&  valorObtenido == null) 
            || (expresionRegular != null && valorObtenido != null 
            &&  HerramientasString.isLike(expresionRegular, (String)valorObtenido)))
                elementosCoincidentes.add(elemento);
        }
        return elementosCoincidentes;
    }
    
    /**
     * Obtiene una lista con los valores del atributo indicado de cada elemento
     * de la colección.<br> 
     * El orden de la lista será el mismo que devuelva el Iterator de la colección.
     * 
     * @param coleccion: Colección de la cual queremos extraer los valores del atributo.
     * @param atributo:  Nombre del atributo a invocar para obtener el valor. Puede
     *                   ser compuesto (p.e. "linea.pedido.fechaInicio");
     * 
     * @return Una lista del mismo tamaño que la colección, con los valores obtenidos del atributo.
     *         Si algún valor es null, también se incluye en la lista como null.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> obtenerAtributos(Collection<?> col, String atributo){
        if(col==null || atributo==null)
            return null;
        List<T> res = new ArrayList<T>(col.size());
        for(Object o : col)
            res.add((T)ReflexionUtils.getAtributo(o, atributo));
        return res; 
    }
    
   
	@SuppressWarnings("unchecked")
    private static <T> List<T> ordenar_priv(List<T> lista, Comparator<T> comparador, boolean nulosSonMayores) {
        if(lista==null || lista.isEmpty() || lista.size()==1) return lista;
        List<T> listaNulos = quitarNulos(lista);
        
        T[] array = (T[])lista.toArray();
        if(comparador==null)
            Arrays.sort(array);
        else
            Arrays.sort(array, comparador);
        
        //recomponemos la lista
        lista.clear();
        
        for(int i=0; i<array.length; i++)
            lista.add((T)array[i]);
        
        if(nulosSonMayores)
            lista.addAll(listaNulos); //nulos al final
        else
            lista.addAll(0, listaNulos); // nulos al principio
        
        return lista;
    }
    
    private static <T> List<T> quitarNulos(List<T> lista){
        List<T> lNulos = new ArrayList<T>();
        for(int i=lista.size()-1; i>=0; i--)
            if(lista.get(i)==null)
                lNulos.add(lista.remove(i));
        return lNulos;
    }
    

}
