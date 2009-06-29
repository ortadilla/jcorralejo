package dondeando.modelo.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Subqueries;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.Conversor;

import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.NombresBean;


/**
 * Convierte los Criterios a Criteria de Hibernate
 */
@Name(NombresBean.CONVERSOR_SQL)
@Scope(ScopeType.CONVERSATION)
public class ConversorHibernate implements Conversor<Session, CriteriaSpecification, Criterion> {
    
    boolean ignorarAcentos;
    
    Log log = LogFactory.getLog(ConversorHibernate.class);
    
    public static final LockMode BLOQUEO_FOR_UPDATE = LockMode.UPGRADE;
    
    @SuppressWarnings("unchecked")
    public <T> List<?> encontrarPorCondicion(Session sesion, Class<T> clase, List<Criterio> criterios) {
      return this.encontrarPorCondicion(sesion, clase, criterios, false);
    }

    @SuppressWarnings("unchecked")
    public <T> List<?> encontrarPorCondicion(Session sesion, Class<T> clase, List<Criterio> criterios, boolean soloIdentificadores) {
        List resultados = new ArrayList();
        if(criterios!=null){
            //Si hay un sólo criterio y es O_LOGICO_CONDICIONES, se lanzan las condiciones por separado
            //TODO ¿Mejor forma? ¿No se pueden unir las condiciones?
            if(criterios.size()==1 && criterios.get(0).getOperador()==Criterio.O_LOGICO_CONDICIONES){
                // Se almacenan los resultados individuales en un Set para que no se repitan
                HashSet<Object> conjunto = new HashSet<Object>();
                for (Condicion condicion : (List<Condicion>)criterios.get(0).getValor())
                    conjunto.addAll(encontrarPorCondicion(sesion, clase, Arrays.asList(condicion.getCriterios()), soloIdentificadores));

                resultados.addAll(conjunto);
            }else{
                //Transformamos nuestros criterios a Hibernate
                Criteria criteria = sesion.createCriteria(clase);
                
                //La siguiente variable podría dar algún problema de concurrecia, en cuyo caso habría que
                //synchronizar su acceso, pero con el tratamiento por conversación de esta clase sería muy
                //raro y poco grave, con lo que no voy a penalizar con la sincronización
                ignorarAcentos = false;
                
                transformarCriterios(criteria, criterios);
                if(soloIdentificadores)
                  criteria.setProjection(Projections.id());
                
                if(ignorarAcentos){
                    sesion.createSQLQuery("ALTER SESSION SET NLS_COMP = LINGUISTIC").executeUpdate();
                    sesion.createSQLQuery("ALTER SESSION SET NLS_SORT = BINARY_AI").executeUpdate();
                }
            
                resultados = criteria.list();
                
                if(log.isDebugEnabled()){
                    log.debug("Criterios: "+criterios);
                    log.debug(resultados.size()==1 ? "1 fila encontrada" : resultados.size()+" filas encontradas");
                }

                if(ignorarAcentos){
                    sesion.createSQLQuery("ALTER SESSION SET NLS_COMP = BINARY").executeUpdate();
                    sesion.createSQLQuery("ALTER SESSION SET NLS_SORT = BINARY").executeUpdate();
                    ignorarAcentos = false; //limpiamos para futuros usos, por si acaso
                }
            }
        }
        return resultados;
    }

    /** 
     * Transformación entre las condiciones trasportadas y las criteria de hibernate
     * 
     * @param criteria  @link{Criteria} o @link{DetachedCriteria} ya inicializada a la que se
     *                  añadirán los criterios traducidos.
     * @param criterios Uno o más criterios que se añadiran traducidos al criteria
     * @return La propia criteria modificada. Se pone para no tener que declarar explícitamente el
     *         criteria en la clase llamante y así no ligarse a la Impl. 
     */
    @SuppressWarnings("unchecked")
    public void transformarCriterios(CriteriaSpecification criteria, List<Criterio> criterios){
        if(criteria==null || criterios==null)
            return;
                
        // Recorremos todos los criterios pasados añadiéndolos al criteria pasado
        for (Criterio criterio : criterios) {
            //Si el criterio tiene su propio conversor, lo gestionamos con él...
            if(criterio.getConversor()!=null){
                Conversor<?, CriteriaSpecification, ?> suConversor = (Conversor<?, CriteriaSpecification, ?>)criterio.getConversor();
                suConversor.transformarCriterios(criteria, Collections.singletonList(criterio));
                continue;
            }
            String atr = criterio.getAtributo();
            Object val = criterio.getValor();
            
            int join = Criteria.INNER_JOIN;
            
            switch (criterio.getOperador()){
                case Criterio.O_LOGICO_CONDICIONES:
                    // Se lanza una excepción, ya que el OR lógico sólo se hace en el primer nivel
                    throw new IllegalArgumentException("No se puede hacer un OR lógico de condiciones recursivo");                          
            
                case Criterio.COLECCION_OUTER:
                    join = Criteria.LEFT_JOIN;
                    // y continúa con la siguiente...
                    //TODO Por ahora, la DetachedCriteria no tiene la posibilidad de pasarle un join
                    //     diferente. Si surge la necesidad (subquery de subquery con "outer"), tendremos
                    //     que implementar nuestra propia DetachedCriteria ampliada.
                case Criterio.COLECCION:
                    // Criterio para una colección con una condición que se resolverá recursivamente,
                    // asignando sus critetios a una subCritrera del Criteria actual.
                    CriteriaSpecification subCriteria = criteria instanceof Criteria 
                                             ? ((Criteria)criteria).createCriteria(atr, join)
                                             : ((DetachedCriteria)criteria).createCriteria(atr);

                    List<Criterio> listaCriterios = val instanceof Criterio 
                                                        ? Arrays.asList((Criterio)val)
                                                        : Arrays.asList(((Condicion)val).getCriterios());
                    transformarCriterios(subCriteria, listaCriterios);
                    break;
                    
                case Criterio.NO_EN_SUBCONSULTA:
                    add(criteria, Subqueries.propertyNotIn(atr, 
                                        crearSubconsulta(criterio.getClaseSubquery(),
                                                         criterio.getAtributoSubquery(),
                                                         Arrays.asList((Criterio[])val))));
                    break;
                    
                case Criterio.NO_EXISTE:
                    add(criteria, Subqueries.notExists(
                                        crearSubconsulta(criterio.getClaseSubquery(),
                                                         null,
                                                         Arrays.asList((Criterio[])val))));
                    break;

                case Criterio.NUM_MAX_RESULTADOS: //Límite máximo de objetos a obtener de la consulta
                    //Sólo aplicable al primer nivel (Criteria)
                    ((Criteria)criteria).setMaxResults((Integer)val);
                    break;
                    
                case Criterio.ORDENADO_ASC_POR: //Ordenación ascendente según la bd
                    if(criteria instanceof Criteria)
                        ((Criteria)criteria).addOrder(Order.asc((String)val));
                    else
                        ((DetachedCriteria)criteria).addOrder(Order.asc((String)val));
                    break;
                    
                case Criterio.ORDENADO_DESC_POR: //Ordenación descendente según la bd
                    if(criteria instanceof Criteria)
                        ((Criteria)criteria).addOrder(Order.desc((String)val));
                    else
                        ((DetachedCriteria)criteria).addOrder(Order.desc((String)val));
                    break;
                    
                case Criterio.SIN_REPETIDOS:
                    if(criteria instanceof Criteria)
                        ((Criteria)criteria).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                    else
                        ((DetachedCriteria)criteria).setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
                    break;
                    
                case Criterio.IGNORAR_ACENTOS:
                    ignorarAcentos = true;
                    break;
                default: //Criterio sencillo --> directamente Criterion
                    add(criteria, convertirCriterioSimple2(criterio));
            }
        }
    }

    /**
     * Transforma un Criterio nuestro a un Criterion de Hibernate.
     * Si el criterio tiene seteado su propio conversor, usará ése en vez de éste.
     */
    public Criterion convertirCriterioSimple2(Criterio criterio) {
//        if(criterio.getConversor()!=null)
//            return (Criterion)criterio.getConversor().convertirCriterioSimple2(criterio);
        
        Criterion criterion = null;
        String    atr       = criterio.getAtributo();
        Object    val       = criterio.getValor();
        Object[]  arrayVal  = criterio.getArrayValores();
        switch (criterio.getOperador()) {
            case Criterio.IGUAL:           criterion = val!=null
                                                     ? Expression.eq(atr, val)
                                                     : Expression.isNull(atr); break;
            case Criterio.MAYOR_QUE:       criterion = Expression.gt(atr, val); break;
            case Criterio.MENOR_QUE:       criterion = Expression.lt(atr, val); break;
            case Criterio.MAYOR_IGUAL_QUE: criterion = Expression.ge(atr, val); break;
            case Criterio.MENOR_IGUAL_QUE: criterion = Expression.le(atr, val); break;
            case Criterio.DISTINTO:        criterion = val!=null
                                                     ? Expression.ne(atr, val)
                                                     : Expression.isNotNull(atr); break;
            case Criterio.LIKE:      
            	Calendar c = Calendar.getInstance();
            	if(val instanceof Date){
            		c.setTime((Date)val);
            		c.add(Calendar.DATE, 1);
            		c.getTime();
            	}
            										 criterion = val instanceof String
                                                     ? Expression.like(atr, getValorLike((String)val))
                                                                 .ignoreCase()
                                                     : val instanceof Date
                                                     ? Expression.and(Expression.ge(atr, val),
                                                       Expression.lt(atr, c))
                                                     : Expression.like(atr, val); break;
            case Criterio.SQL:             criterion = Expression.sql(atr, obtenerArray(val), 
                                                                           obtenerTypes(val)); break;
            case Criterio.EN:              criterion = Expression.in(atr, arrayVal); break;
            case Criterio.NO:              criterion = Expression.not(convertirCriterioSimple2((Criterio)val)); break;
            case Criterio.ENTRE:           criterion = Expression.between(atr, arrayVal[0], arrayVal[1]); break;
            case Criterio.VACIO:           criterion = Expression.isEmpty(atr); break;
            case Criterio.NO_VACIO:        criterion = Expression.isNotEmpty(atr); break;
            case Criterio.O:               //Tratamiento igual que el siguiente
            case Criterio.Y:               for (Object criterioOr : arrayVal)
                                               criterion = criterion==null 
                                                     ? convertirCriterioSimple2((Criterio)criterioOr) // La primera vez lo inicializamos
                                                     : Criterio.O == criterio.getOperador()
                                                         ? Expression.or (criterion, convertirCriterioSimple2((Criterio)criterioOr))                 
                                                         : Expression.and(criterion, convertirCriterioSimple2((Criterio)criterioOr));                 
                                           break;
            default:
                throw new UnsupportedOperationException("Operador del criterio desconocido: "+criterio.getDescripcion()); 
        }
        return criterion;
    }

    /** 
     * Crea una subconsulta con los criterios indicados
     * 
     * @param criterios   El primero indica la clase sobre la que hace la subconsulta, el segundo
     *                    la propiedad que devuelve y el resto las condiciones. 
     * @return            Subconsulta generada para añadir a un Criteria.
     */
    private DetachedCriteria crearSubconsulta(Class<?> clase, String propiedadADevolver, List<Criterio> criterios){
        if (clase == null)
            throw new IllegalArgumentException("El criterio debe contener la clase sobre la que se lanza la subconsulta (Criterio.setClaseSubquery()");
        //Creamos una consulta de la clase pasada independiente de la sesión 
        DetachedCriteria criteria = DetachedCriteria.forClass(clase);
        
        // Añadimos sus criterios
        transformarCriterios(criteria, criterios);
        
        // Añadimos el campo que devolverá la subconsulta, si se ha indicado
        return criteria.setProjection(propiedadADevolver!=null && propiedadADevolver.length()>0
                                         ? Projections.property(propiedadADevolver)
                                         : Projections.id());
    }
    
    private void add(CriteriaSpecification criteria, Criterion criterion){
        if(criterion!=null)
            if (criteria instanceof Criteria)
                ((Criteria)criteria).add(criterion);
            else
                ((DetachedCriteria)criteria).add(criterion);
    }


    private Object[] obtenerArray(Object v) {
        if(v==null)               return new Object[]{};
        if(v instanceof Object[]) return (Object[]) v;
        return new Object[]{v};
    }
    private Type[] obtenerTypes(Object v) {
        Object[] objs = obtenerArray(v);
        Type[] arr = new Type[objs.length];
        for(int i = 0; i<objs.length; i++){
            arr[i] = TypeFactory.basic(objs[i].getClass().getName());
            if(arr[i]==null)
                throw new IllegalArgumentException("Clase del parámetro no implementada: "+objs[i].getClass().getName()+". Ver TypeFactory.BASIC_TYPES");
        }
        return arr;
    }
    
    private String getValorLike(String valor){
        String res = new String(valor).replace('*', '%')
                                      .replace('?', '_');
        //Si el usuario no puso comodines, añadimos nosotros al final el asterisco
        if(!res.contains("%") && !res.contains("_")) 
            res += "%";
        return res;
    }

}
