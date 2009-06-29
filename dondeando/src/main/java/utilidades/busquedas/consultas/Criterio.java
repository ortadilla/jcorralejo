package utilidades.busquedas.consultas;

import dondeando.modelo.dao.Conversor;


public interface Criterio {

    static final int IGUAL           = 1;
    static final int MAYOR_QUE       = 2;
    static final int MENOR_QUE       = 3;
    static final int MAYOR_IGUAL_QUE = 4;
    static final int MENOR_IGUAL_QUE = 5;
    static final int DISTINTO        = 6;
    static final int LIKE            = 7;
    
    /**
     * AND de los criterios pasados. Normalmente INNECESARIO, ya que equivale a buscar por varios
     * criterios. Se usa para facilitar ciertas construcciones o funciones que manejan un �nico Criterio.
     * Espera un Array o una List de Criterios.
     */
    static final int Y = 8;

    /** Contruir con @link{ServicioCriterios#construyeCriterioNoEnSubconsulta()} 
     * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioNoEnSubconsulta(String, Class, String, Criterio[]) 
     */
    static final int NO_EN_SUBCONSULTA= 9;

    /** Condiciones sobre colecciones o atributos que son otras entidades. Espera una Condicion o un Criterio*/
    static final int COLECCION		= 10;    
    
    /** Limita el m�ximo n�mero de resultados que se pueden obtener */
    static final int NUM_MAX_RESULTADOS = 11;
    
    /** Indica la columna para ordenaci�n ascendente */
    static final int ORDENADO_ASC_POR = 12;

    /** Indica la columna para ordenaci�n descendente */
    static final int ORDENADO_DESC_POR = 13;
    
    /** 
     * Indicamos que s�lo deseamos objetos que sean distintos, es decir que no haya repetidos.
     * No necesita ni atributo ni valor
     */ 
    static final int SIN_REPETIDOS = 14;
    
    // A�adimos un criterio para hacer OR de n criterios, siempre que �stos, 
    //se encuentren al mismo nivel, es decir se hagan sobre elementos de la 
    //misma clase
    /**
     * OR de los criterios pasados. Espera una Condicion, un Array o una List de Criterios.
     */
    static final int O = 15;
    /** @deprecated     Usar simplemente "O" */
    @Deprecated
    static final int O_LOGICO_CRITERIOS = 15;
    
    // A�adimos un criterio que hace OR l�gico de n condiciones, es decir
    //realizar� las diferentes consultas por las condiciones pasadas, y devolver�
    //el OR l�gico (suma) de los resultados de las consultas de cada una de las condiciones
    static final int O_LOGICO_CONDICIONES = 16;

    /** Contruir con @link{ServicioCriterios#construyeCriterioNoExiste()} 
     * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioNoExiste(Class, Criterio[]) 
     */
    static final int NO_EXISTE = 17;
    
    /** Contruir con @link{ServicioCriterios#construyeCriterioSQL()} 
     * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioSQL(String, Object[]) 
     */
    static final int SQL = 18;
    
    /** Espera como valor un Array de objetos o una Collection */
    static final int EN = 19;
    
    /** Espera como valor otro criterio. El atributo no es necesario (se ignora) */
    static final int NO = 20;
    
    /** Espera un Array o una List con dos valores */
    static final int ENTRE = 21;
    
    /** Igual que {@link #COLECCION} pero realizando un "left outer join" (selecciona los registros
     * de la tabla que no tienen elementos en la colecci�n) */
    static final int COLECCION_OUTER = 22;
    
    /** Aplicable s�lo a atributos que sean COLECCIONES. Devolver� los objetos que
     *  tienen esa colecci�n sin elementos. El valor no es necesario. */
    static final int VACIO = 23;
    
    /** Aplicable s�lo a atributos que sean COLECCIONES. Devolver� los objetos que
     *  tienen alg�n elemento en esa colecci�n. El valor no es necesario. */
    static final int NO_VACIO = 24;
    
    /** Altera la sesi�n de oracle para que ignore los acentos en esta b�squeda.
     *  No necesita propiedad ni valor. <br>
     *  OJO!! Puede hacer la select mucho m�s ineficiente, ya que no se toman los posibles �ndices
     *  afectados, aparte del propio tiempo de los 4 "alter session" necesarios.
     */
    static final int IGNORAR_ACENTOS = 25;

    
    
    //------------- GETTERS // SETTERS --------------------//
    
    String getAtributo();

    int getOperador();
    
    Object getValor();
    
    /** @return Nombre del atributo que debe devolver una subConsulta */
    String getAtributoSubquery();

    /** @return Clase sobre la que se crea una subConsulta */
    Class<?> getClaseSubquery();
    
    /** Develve el nombre del criterio. Para debug y mensajes de error */
    String getDescripcion();
    
    /**
     * Devuelve un array con los Elementos pasados como valor, cuando proceda, independientemente
     * de la clase de ese valor (Collection, Array, Condici�n...). Si no procede, devuelve null.
     */
    Object[] getArrayValores();

    /**@return Conversor a Hibernate asignado a este criterio. Si es nulo, se usa el est�ndar. */
    Conversor<?, ?, ?> getConversor();
    void setConversor(Conversor<?, ?, ?> conversor);
}
