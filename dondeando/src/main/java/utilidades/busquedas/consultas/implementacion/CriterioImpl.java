package utilidades.busquedas.consultas.implementacion;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import dondeando.modelo.dao.Conversor;

import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.HerramientasComparacion;

public class CriterioImpl implements Criterio {
    
    protected static final int NUM_OPERADORES = 25; //para los test
    private static final String[] nomCrit = new String[]{
        "0", "IGUAL", "MAYOR_QUE", "MENOR_QUE", "MAYOR_IGUAL_QUE", "MENOR_IGUAL_QUE", "DISTINTO", "LIKE",
        "Y", "NO_EN_SUBCONSULTA", "COLECCION", "NUM_MAX_RESULTADOS", "ORDENADO_ASC_POR", "ORDENADO_DESC_POR",
        "SIN_REPETIDOS", "O_LOGICO_CRITERIOS", "O_LOGICO_CONDICIONES", "NO_EXISTE", "SQL", "EN", "NO",
        "ENTRE", "COLECCION_OUTER", "VACIO", "NO_VACIO", "IGNORAR_ACENTOS"
    };
    private static final String[] descCrit = new String[]{
        "0", "={0}", ">{0}", "<{0}", ">={0}", "<={0}", "!={0}", " like {0}",
        " and {0}", " not in ({0}) (para {1}.{2})", " join ({0})", "max {0} results", "order by {0}", "order by {0} desc",
        "unique", " or {0}", " or ({0})", "not exists {0} (para {1})", " {0} ", " in {0}", "not {0}",
        " between {0}", " outer join({0})", " is empty {0}", " not is empty {0}", "Ignorando acentos"
    };
    
    private String atributo;
    private int operador = -1;
    private Object valor;
    private Class<?> claseSubquery;
    private String atributoSubquery;
    private Conversor<?, ?, ?> conversor;
    
    /**
     * Constructor con todos los atributos básicos.
     * @param atributo Nombre del atributo sobre el que se establece el criterio
     * @param operador Operador de comparación 
     * @param valor Valor utilizado para la comparación.
     */
    public CriterioImpl(String atributo, int operador, Object valor) {
        this(atributo, operador, valor, null, null);
    }

    /**
     * Sobrecarga para recibir los parámetros necesarios para subqueries. Podría hacerse con setters a posteriori,
     * pero el objetivo es seguir chequeando la corrección en el constructor, lo que facilita las
     * pruebas en aislamiento.
     * 
     * Para la correcta asignación de estos parámetros, dependientes de cada operador, usar las
     * funciones del @link{ServicioCriterios}
     * 
     * @param claseSubquery         Clase sobre la que se ejecuta la subconsulta
     * @param atributoSubquery      Atributo de esa clase que devuelve la subconsulta. Si es nulo o vacío, se usa el ID.
     */
    public CriterioImpl(String atributo, int operador, Object valor, Class<?> claseSubquery, String atributoSubquery) {
        if (operador < 1 || operador > NUM_OPERADORES)
            throw new IllegalArgumentException("El operador pasado no está definido: "+operador);

        this.atributo         = atributo;
        this.operador         = operador;
        this.valor            = valor;
        
        // Según el operador se comprueban si los parámetros pasados son correctos
        switch(operador){
        	case NO_EN_SUBCONSULTA:   //Parámetros idénticos al siguiente...
        	case NO_EXISTE:    	      comprobarValor(true,  Criterio.class,  1, null, Criterio[].class, Criterio.class); break;
        	case COLECCION_OUTER:     //Parámetros idénticos al siguiente...
            case COLECCION:           comprobarValor(false, Criterio.class,  0, null, Condicion.class, Criterio.class); break;
            case Y:                   comprobarValor(false, Criterio.class,  1, null, List.class, Criterio[].class); break;
            case O:                   comprobarValor(false, Criterio.class,  0, null, List.class, Criterio[].class, Condicion.class); break;
            case O_LOGICO_CONDICIONES:comprobarValor(false, Condicion.class, 1, null, List.class); break;
            case EN:                  comprobarValor(false, null,            1, null, Collection.class, Object[].class); break;
            case NO:                  comprobarValor(false, null,            0, null, Criterio.class); break;
            case ENTRE:               comprobarValor(false, null,            2, 2,    List.class, Object[].class); break;
            case NUM_MAX_RESULTADOS:  comprobarValor(false, null,            1, null, Integer.class); break;
            case ORDENADO_ASC_POR:    //Parámetros idénticos al siguiente...
            case ORDENADO_DESC_POR:   comprobarValor(false, null,            1, null, String.class); break;
            case NUM_OPERADORES+1:    break; //Sólo para hacer que se actualice la constante
        }
        
        //Para éstos, la clase de la subconsulta es obligatoria
        if((operador==NO_EN_SUBCONSULTA || operador==NO_EXISTE) && claseSubquery==null)
            throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" se debe indicar la clase de la subconsulta");

        this.claseSubquery    = claseSubquery;
        this.atributoSubquery = atributoSubquery;
    }
    
    /**
     * Lanza una excepción si el valor del criterio no es correcto
     * @param nulo              Si el valor puede ser nulo
     * @param claseElementos    Si no nula, clase que deben tener los elementos si el valor es una colección, array, etc
     * @param minSize           Si no nulo, tamaño mínimo que debe tener la colección.
     * @param maxSize           Si no nulo, tamaño máximo que debe tener la colección.
     * @param clasesValor       Distintas clases que puede ser el valor
     */
    private void comprobarValor(boolean nulo, Class<?> claseElementos, 
                                Integer minSize, Integer maxSize, Class<?>... clasesValor){
        if(!nulo && valor==null)
            throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" se debe pasar un valor no nulo");
        
        if(valor!=null){
            Object[] elementos = null;
            boolean claseCorrecta = false;
            String clasesPermitidas = ""; //Para el posible mensaje de error
            
            for(Class<?> clase : clasesValor){
                if(clase.isAssignableFrom(valor.getClass())){
                    claseCorrecta = true;
                    elementos = getArrayValores();
                    break;
                }
                clasesPermitidas += (clasesPermitidas.length()>0 ? " o " : "")+clase.getSimpleName();
            }
            if(!claseCorrecta)
                throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" el valor debe ser de tipo "+clasesPermitidas);
            
            //Si era algún conjunto de valores, los comprobamos
            if(elementos!=null){
                if(claseElementos!=null)
                    for(Object elemento : elementos)
                        if(elemento==null || !claseElementos.isAssignableFrom(elemento.getClass()))
                            throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" los elementos del valor deben ser no nulos y de tipo "+claseElementos.getSimpleName());
                if(minSize!=null && elementos.length<minSize)
                    throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" se deben pasar al menos "+minSize+" elementos");
                if(maxSize!=null && elementos.length>maxSize)
                    throw new IllegalArgumentException("Para el operador "+nomCrit[operador]+" se deben pasar a lo sumo "+maxSize+" elementos");
            }
        }
        
    }

    /**
     * Metodo equals del objeto.
     * @param other Objeto con el que se compara
     * @return booleano con el resultado de la comparación
     */
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this==other) return true;
        if (!(other instanceof Criterio)) return false;
        CriterioImpl that = (CriterioImpl) other;
        return (HerramientasComparacion.sonIgualesONulos(this.atributo,that.getAtributo()) && 
                this.operador == that.getOperador() &&
                HerramientasComparacion.sonIgualesONulos(this.valor,that.getValor()));
    }

    /**
     * Devuelve el hashCode del objeto.
     * @return hashCode del objeto.
     */
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (null == atributo ? 0 : atributo.hashCode());
        hash = 31 * hash + (-1 == operador ? 0 : operador+3);
        hash = 17 * hash + (null == valor ? 0 : valor.hashCode());
        return hash;
    }    

    
    /**
     * Devuelve IGUAL, si elTexto NO contiene el literal "%", o LIKE si lo contiene.
     * @param elTexto   Cadena por la que se va a buscar.
     * @return          Operador a usar (igualdad o busqueda aproximada).
     */
     public static int getOperador(String elTexto) {
        return elTexto==null ||  elTexto.indexOf("%") == -1 ? IGUAL : LIKE;
    }    
     
    @Override
    public String toString() {
        return (atributo!=null ? atributo : "") + 
               (operador<0 || operador>descCrit.length 
                       ? String.valueOf(operador) 
                       : MessageFormat.format(descCrit[operador], obtenerDescParaToString(valor), claseSubquery, atributo));
    }
    
    private String obtenerDescParaToString(Object valor){
        if(valor==null)                     return "null";
        if(valor instanceof Integer)        return valor.toString(); //Se anticipa porque es muy habitual
        if(valor instanceof String)         return "'"+valor+"'";
        if(valor instanceof Date)           return "'"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(valor)+"'";
        
        Collection<?> collection = valor instanceof Collection ? ((Collection<?>)valor)
                                 : valor.getClass().isArray()  ? Arrays.asList((Object[])valor)
                                                               : null;
        if(collection!=null && !collection.isEmpty()){
            String res = "";
            for(Object obj : collection)
                res += (res.length()==0 ? "[":", ")+obtenerDescParaToString(obj);
            return res+"]";
        }
        
        return valor.toString();
    }

    
    
    //----------- GETTERS/SETTERS --------//
    public String getAtributo() {
        return atributo;
    }
    
    public int getOperador() {
        return operador;
    }
    
    public Object getValor() {
        return valor;
    }

    public String getAtributoSubquery() {
        return atributoSubquery;
    }

    public Class<?> getClaseSubquery() {
        return claseSubquery;
    }
    
    public String getDescripcion(){
        return (operador<0 || operador>nomCrit.length) ? String.valueOf(operador) 
                                                       : nomCrit[operador];
    }
    
    public Object[] getArrayValores(){
        if(valor!=null){
            if(valor instanceof Condicion)  return ((Condicion)valor).getCriterios();
            if(valor instanceof Collection) return ((Collection<?>)valor).toArray();
            if(valor instanceof Object[])   return (Object[])valor;
        }
        return null;
    }

    public dondeando.modelo.dao.Conversor<?, ?, ?> getConversor() {
        return this.conversor;
    }
    public void setConversor(Conversor<?, ?, ?> conversor) {
        this.conversor = conversor;
    }

}
