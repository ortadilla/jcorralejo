package dondeando.modelo.dao.hibernate;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.persister.collection.AbstractCollectionPersister;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.Joinable;
import org.jboss.seam.annotations.In;

import dondeando.modelo.dao.Conversor;
import dondeando.modelo.dao.DAOGenerico;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.dao.excepciones.EntidadNoEncontradaExcepcion;

import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.NombresBean;


/**
 * Implementa las operaciones de acceso a datos genéricas para ABM's usando
 * la API de Hibernate.
 * <p>
 * Implements the generic CRUD data access operations using Hibernate APIs.
 * <p>
 * To write a DAO, subclass and parameterize this class with your persistent class.
 * Of course, assuming that you have a traditional 1:1 appraoch for Entity:DAO design.
 * <p>
 * You have to inject the <tt>Class</tt> object of the persistent class and a current
 * Hibernate <tt>Session</tt> to construct a DAO (ñapeado).
 * 
 * @see com.hp.geos.modelo.dao.DAOGenerico
 * 
 */
public abstract class HibernateDAOGenerico<T, ID extends Serializable> implements DAOGenerico<T, ID> {
    
    @In(value=NombresBean.SESION_HIBERNATE, create=true)
    private Session laDatabase;
    @In(value=NombresBean.SESION_HIBERNATE_ESCRITURA, create=true)
    private Session laDatabaseEscritura;
    
    @SuppressWarnings("unchecked")
    @In(value=NombresBean.CONVERSOR_SQL, create=true )
    private Conversor conversor = new ConversorHibernate(); //TODO Provisional, para evitar NPE en pruebas donde no se ha inicializado

    private Class<T> clasePersistente;

    @SuppressWarnings("unchecked")
    protected HibernateDAOGenerico(Class clasePersistente) {
        this.clasePersistente = (Class<T>)clasePersistente;
    }
  
    /* (non-Javadoc)
	 * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarTodos()
	 */   
    @SuppressWarnings("unchecked")
    public List<T> encontrarTodos() {
        return encontrarPorCondicion(new ArrayList<Criterio>());
    }  

    /* (non-Javadoc)
	 * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarPorId(ID id, boolean lock)
	 */   
    @SuppressWarnings("unchecked")
    public T encontrarPorId(ID id, Boolean bloquear) throws DAOExcepcion {
        if (id == null || bloquear == null)
            return null;
        T entity = bloquear ? (T) laDatabase.get(getClasePersistente(), id, LockMode.UPGRADE)
                            : (T) laDatabase.get(getClasePersistente(), id);
        if (entity == null)
        	throw new EntidadNoEncontradaExcepcion(getClasePersistente().toString(), id.toString());
        return entity;
    }
    
    /* (non-Javadoc) 
	 * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarPorId(Integer id)
	 */   
    public T encontrarPorId(ID id) throws DAOExcepcion {
    	return encontrarPorId(id, Boolean.FALSE);
    }

    /* (non-Javadoc)
	 * @see com.hp.geos.modelo.dao.DAOGenerico#hacerPersistente()
	 */   
    public T hacerPersistente(T entity) {
    	laDatabase.saveOrUpdate(entity);
        return entity;
    }
    
    /* (non-Javadoc)
	 * @see com.hp.geos.modelo.dao.DAOGenericoExtendido#hacerTransitorio()
	 */   
    public void hacerTransitorio(T entity){
    	laDatabase.evict(entity);
    	laDatabaseEscritura.delete(entity);
    }
    

    /* (non-Javadoc)
     * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarPorCondicion(Criterio... criterios)
     */   
    @SuppressWarnings("unchecked")
    public List<T> encontrarPorCondicion(Criterio... criterios) {
        return encontrarPorCondicion(Arrays.asList(criterios));
    }

    /* (non-Javadoc)
     * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarPorCondicion(Criterio... criterios)
     */   
    @SuppressWarnings("unchecked")
    public List<T> encontrarPorCondicion(List<Criterio> criterios) {
        return conversor.encontrarPorCondicion(laDatabase, clasePersistente, criterios);
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#encontrarIdentificadoresPorCondicion(java.util.List)
     */
    public List<ID> encontrarIdentificadoresPorCondicion(Criterio... criterios) {
        return encontrarIdentificadoresPorCondicion(Arrays.asList(criterios));
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#encontrarIdentificadoresPorCondicion(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public List<ID> encontrarIdentificadoresPorCondicion(List<Criterio> criterios) {
        return (List<ID>)conversor.encontrarPorCondicion(laDatabase, clasePersistente, criterios, true);
    }

    /* (non-Javadoc)
     * @see com.hp.geos.modelo.dao.DAOGenerico#encontrarValorSecuencia(String nombreSecuencia)
     */ 
    public BigInteger encontrarValorSecuencia(String nombreSecuencia) throws DAOExcepcion{
        try{
            return ((BigDecimal)laDatabase.createSQLQuery("select "+nombreSecuencia+".nextval from dual").uniqueResult()).toBigInteger();
        }catch(Exception e){
            //La capturamos para dar un mensaje más explicativo
            throw new DAOExcepcion("La secuencia "+nombreSecuencia+" no existe");
        }
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#flush()
     */
    public void flush() throws DAOExcepcion {
        try{
            laDatabase.flush();                	
            laDatabaseEscritura.flush();
        }catch(RuntimeException re){
        	re.printStackTrace();
        	laDatabaseEscritura.clear();
            throw new DAOExcepcion(re.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#refrescar(T)
     */
    public void refrescar(T entity) {
        laDatabase.refresh(entity);
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#isEnSesion(T)
     */
    public boolean isEnSesion(T entity) {
        return laDatabase.contains(entity);
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#cargarObjetoPorIdYClase(Class, Serializable)
     */
    public Object encontrarPorIdYClase(Class<?> clase, Serializable id) {   	
    	return laDatabase.load(clase, id);
    }
    
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#estaMapeada(java.lang.String)
     */
    public boolean isMapeada(String propiedad) {
        ClassMetadata metadata = laDatabase.getSessionFactory().getClassMetadata(getClasePersistente());
        String[] nombres = metadata.getPropertyNames();
        for(int i=0; i<nombres.length; i++)
            if(nombres[i].equals(propiedad))
                return true;
        //Comprobamos si se trata del id, que no se encuentra dentro de las getPropertyNames()
        if(propiedad.equals(metadata.getIdentifierPropertyName()))
            return true;
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#getPropiedadId()
     */
    public String getPropiedadId() {
        return laDatabase.getSessionFactory().getClassMetadata(getClasePersistente()).getIdentifierPropertyName();
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#getPropiedadVersion()
     */
    public String getPropiedadVersion() {
        ClassMetadata metaData = laDatabase.getSessionFactory().getClassMetadata(getClasePersistente());
        return metaData.getPropertyNames()[metaData.getVersionProperty()];
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#isRelacionNoMapeada(java.lang.String)
     */
    public boolean isRelacionNoMapeada(String propiedadColeccion) {
        //Leemos el metadata asociado a la propiedad indicada
        String role = getClasePersistente().getName()+"."+propiedadColeccion;
        CollectionMetadata collectionMetadata = laDatabase.getSessionFactory().getCollectionMetadata(role);
        
        //Si existe, comprobamos si es un many-to-many
        if(collectionMetadata!=null 
        && collectionMetadata instanceof AbstractCollectionPersister)
            return ((AbstractCollectionPersister) collectionMetadata).isManyToMany();
        
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#isVista()
     */
    public boolean isVista(){
        ClassMetadata metaData = laDatabase.getSessionFactory().getClassMetadata(getClasePersistente());
        if(metaData instanceof Joinable){
            String nombreTabla = ((Joinable)metaData).getTableName();
            return !laDatabase.createSQLQuery("select 1 from all_views where lower(view_name) = ?")
                              .setString(0, nombreTabla.toLowerCase())
                              .list().isEmpty();
        }
        return false;
    }
    
    private List<String> propsRequeridas = null;
    @SuppressWarnings("unchecked")
    public List<String> getPropiedadesRequeridas(){
        if(propsRequeridas==null){
            propsRequeridas = new ArrayList<String>();
            ClassMetadata metaData = laDatabase.getSessionFactory().getClassMetadata(getClasePersistente());
            if((metaData instanceof Joinable)
            && (metaData instanceof AbstractEntityPersister)){
                
                String nombreTabla = ((Joinable)metaData).getTableName();
                List<String> listaCols = laDatabase.createSQLQuery("select upper(column_name) from user_tab_columns where table_name = upper(?) and NULLABLE='N'")
                                                   .setString(0, nombreTabla)
                                                   .list();
    
                for(String prop : metaData.getPropertyNames()){
                	//Leemos el metadata asociado a la propiedad indicada para evitar quedarnos con colecciones.
                    String role = getClasePersistente().getName()+"."+prop;
                    CollectionMetadata collectionMetadata = laDatabase.getSessionFactory().getCollectionMetadata(role);
                    if(collectionMetadata==null) {
                    	String[] cols = ((AbstractEntityPersister)metaData).getPropertyColumnNames(prop);
//                    	System.out.println(prop+" -->"+Arrays.asList(cols));
                    	if(listaCols.contains(cols[0].toUpperCase()))
                    		propsRequeridas.add(prop);
                    }
                }
            }
        }
        return propsRequeridas;
        
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#descartar(java.lang.Object)
     */
    public void descartar(T entity) {
        if(entity!=null)
            laDatabase.evict(entity);
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#descartar(java.util.Collection)
     */
    public void descartar(Collection<T> objs) {
        if(objs!=null)
            for(T o : objs)
                descartar(o);
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#iniciarTransaccion()
     */
    public void iniciarTransaccion() {
        laDatabase.getTransaction().begin();
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#commitTransaccion()
     */
    public void commitTransaccion() {
        laDatabase.getTransaction().commit();
    }

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#rollbackTransaccion()
     */
    public void rollbackTransaccion() {
        laDatabase.getTransaction().rollback();
    }
    
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.dao.DAOGenerico#getEsquemaBD()
     */
    public String getEsquemaBD() throws DAOExcepcion{
        try {
            return laDatabase.connection().getMetaData().getUserName();
        } catch (Exception e) {
            throw new DAOExcepcion("No se pudo obtener el esquema de la BD: "+e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
	public Object obtenerObjetoCaliente(String condAdicional) throws DAOExcepcion {
    	String query = "select max(id) from " +getClasePersistente().getSimpleName();
		if(condAdicional!=null && !"".equals(condAdicional))
			query += " where "+condAdicional;
		
    	List res = laDatabase.createQuery(query).list();
    	if(res!=null && !res.isEmpty())
    		return encontrarPorId((ID)res.get(0));
    	
   		return null;
    }

    
    //----------------- GETTERS /SETTERS -------------------------//


    public Class<T> getClasePersistente() {
        return clasePersistente;
    }
    public void setClasePersistente(Class<T> clasePersistente) {
        this.clasePersistente = clasePersistente;
    }
    
    protected Session getLaDatabase() {
        return laDatabase;
    }
    public void setLaDatabase(Session laDatabase) {
        this.laDatabase = laDatabase;
    }

    @SuppressWarnings("unchecked")
    public Conversor getConversor() {
        return conversor;
    }
    @SuppressWarnings("unchecked")
    public void setConversor(Conversor conversor) {
        this.conversor = conversor;
    }

	public Session getLaDatabaseEscritura() {
		return laDatabaseEscritura;
	}
	public void setLaDatabaseEscritura(Session laDatabaseEscritura) {
		this.laDatabaseEscritura = laDatabaseEscritura;
	}

}

