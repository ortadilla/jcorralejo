package dondeando.modelo.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import dondeando.modelo.dao.excepciones.DAOExcepcion;

import utilidades.busquedas.consultas.Criterio;



/**
 * Interface compartida por todos los business DAO's .
 * <p>
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared accross all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UDPATE statement function) that provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 *
 */
public interface DAOGenerico<T, ID extends Serializable> {
    
    /**
     * Indica cada cuantas filas hay que registrar los cambios
     * en la BD para los traspasos masivos de información
     */
    static final Integer REGISTRAR_CAMBIOS_CADA_X = 200; 

	/**
     * Metodo encargado de buscar según unos criterios dados.
     * @param criterios Criterios de búsqueda.
     * @return Lista de objetos (entidades) que cumplen los criterios de búsqueda
     */
	List<T> encontrarPorCondicion(Criterio... criterios);
    
    /**
     * Metodo encargado de buscar según unos criterios dados.
     * @param criterios Criterios de búsqueda.
     * @return Lista de objetos (entidades) que cumplen los criterios de búsqueda
     */
    List<T> encontrarPorCondicion(List<Criterio> criterios);
    
    /**
     * Metodo encargado de buscar (retornando únicamente los identificadores) según unos criterios dados.
     * @param criterios Criterios de búsqueda.
     * @return Lista de objetos (identificadores) que cumplen los criterios de búsqueda
     */
    List<ID> encontrarIdentificadoresPorCondicion(Criterio... criterios);

    /**
     * Metodo encargado de buscar (retornando únicamente los identificadores) según unos criterios dados.
     * @param criterios Criterios de búsqueda.
     * @return Lista de objetos (identificadores) que cumplen los criterios de búsqueda
     */
    List<ID> encontrarIdentificadoresPorCondicion(List<Criterio> criterios);
    
    /**
	 * Metodo encargado de encontrar una entidad por Id con opción de bloquear.
	 * @param id	Id de la entidad que se quiere recuperar.
	 * @param lock  Booleano que indica si se quiere bloquear o no.
	 * @return		Entidad recuperada.
     * @throws DAOExcepcion    Si no encuentra la entidad en la BD.
	 */
    T encontrarPorId(ID id, Boolean lock)  throws DAOExcepcion;

    /** 
     * Metodo encargado de encontrar una entidad por Id. No bloquea.
     * @param  id Id de la entidad que se quiere recuperar.
     * @return Entidad recuperada.
     * @throws DAOExcepcion    Si no encuentra la entidad en la BD.
     */
    T encontrarPorId(ID id)  throws DAOExcepcion;
    
    /**
     * Metodo encargado de encontrar todas las entidades de un determinado tipo.
     * @return lista con todas las entidades.
     */
    List<T> encontrarTodos();
    
    /**
     * Metodo de encargado de hacer persistente (salvar en BDD) una entidad que es transitoria.
     * @param entity Entidad que se quiere hacer persistente.
     * @return
     */
    T hacerPersistente(T entity);

    /**
     * Metodo encargado de retornar cual es la clase persistente concreta.
     * @return Clase persistente concreta.
     */
	Class<T> getClasePersistente();
	
    /**
     * Metodo de encargado de hacer transitoria (borrar de BDD) una entidad que es persistente.
     * @param entity Entidad que se quiere hacer transitoria.
     */
    void hacerTransitorio(T entity);		
    
    /**
     * Devuelve el siguiente valor de una secuencia en la BD.
     * 
     * @param nombreSecuencia      Nombre de la secuencia a leer
     * @return                     Valor recuperado.
     * @throws DAOExcepcion        Si no existe la secuencia en la BD.
     * @throws ArithmeticException (RuntimeException) Si la secuencia contuviese un valor que no es Integer.
     */
    BigInteger encontrarValorSecuencia(String nombreSecuencia) throws DAOExcepcion;
    
    /**
     * Hace efectivas en la BD las operaciones pendientes 
     * @throws DAOExcepcion Si ocurre algún error SQL al realizar las operaciones.
     */
    void flush() throws DAOExcepcion;
    
    /**
     * Vuelve a leer el objeto de la BD, recuperando su estado original.
     */
    void refrescar(T entity);
    
    /**
     * Indica si el objeto está asociado a la session actual o no.
     */
    boolean isEnSesion(T entity);
    
    /**
     * Devuelve un objeto de una clase mapeada cualquiera (no tiene que ser la clase soportada
     * por el DAO). 
     * @param clase     Clase del objeto a recuperar.
     * @param id        ID del objeto.
     * @return          Objeto recuperado.
     * @throws          RuntimeException (HibernateException) si no existe ese objeto
     */
    Object encontrarPorIdYClase(Class<?> clase, Serializable id);

    /**
     * Indica si la propiedad que se indica está mapeada en la clase del DAO.
     * @param propiedad
     * @return  True si está mapeada.
     */
    boolean isMapeada(String propiedad);

    /**
     * Devuelve el nombre de la propiedad mapeada como ID en la clase persistente del DAO.
     * @return Nombre de la propiedad ID.
     */
    String getPropiedadId();
    
    /**
     * Devuelve el nombre de la propiedad mapeada como VERSION en la clase persistente del DAO.
     * @return Nombre de la propiedad VERSION.
     */
    String getPropiedadVersion();
    
    /** 
     * Indica si la propiedad que se indica es una colección obtenida de una tabla de
     * relación no mapeada (many-to-many);
     */
    boolean isRelacionNoMapeada(String propiedadColeccion);
    
    /** Indica si la la entidad mapeada está soportada po una vista (true) o por una tabla (false) */
    boolean isVista();

    
    /**
     * Elimina el obj de la caché de la sesión. Los posibles cambios pendientes del objeto no se
     * enviarán a la BD. Hará lo mismo con los objetos asociados mapeados con <tt>cascade="evict"</tt>.
     */
    void descartar(T entity);
    /**Como {@link #descartar(Object)} pero con todos los objs de la colección*/
    void descartar(Collection<T> objs);
    
    /**
     * @return Devuelve el nombre del esquema de base de datos (usuario de la BD) al que se ha conectado la aplicación.
     * @throws DAOExcepcion Si no se puede conectar a la BD por algún motivo.
     */
    String getEsquemaBD() throws DAOExcepcion;

    /** Inicia una transacción en BD. Normalmente no es necesario. Sólo para procesos masivos de carga */
    void iniciarTransaccion();
    /** Commit de la transacción de BD antes iniciada. Normalmente no es necesario. Sólo para procesos masivos de carga */
    void commitTransaccion();
    /** Rollback de la transacción de BD antes iniciada. Normalmente no es necesario. Sólo para procesos masivos de carga */
    void rollbackTransaccion();
    
    public Object obtenerObjetoCaliente(String condsAdicionales) throws DAOExcepcion;
    
    /**
     * Le pregunta a la metainformación de Hibernate cuáles son las propiedades de 
     * la entidad que no pueden ser nulas en la base de datos.
     * @return La lista de las propiedades de la clase que no pueden ser nulas cuando
     * 		   se inserten en base de datos. Excluye las colecciones.
     */
    public List<String> getPropiedadesRequeridas();
}
