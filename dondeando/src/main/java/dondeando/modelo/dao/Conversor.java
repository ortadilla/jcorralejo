package dondeando.modelo.dao;

import java.util.List;

import utilidades.busquedas.consultas.Criterio;


/**
 * @param <T0>  Clase de la sesión de conexion a la BD.
 * @param <T1>  Clase que agrupa conjuntos de criterios (Criteria de Hibernate)
 * @param <T2>  Clase de los criterios indivicuales (Criterion de Hibernate)
 */
public interface Conversor<T0, T1, T2> {
    
    <T> List<?> encontrarPorCondicion(T0 sesion, Class<T> clase, List<Criterio> criterios, boolean soloIdentificadores);

    <T> List<?> encontrarPorCondicion(T0 sesion, Class<T> clase, List<Criterio> criterios);

    
    void transformarCriterios(T1 criteria, List<Criterio> criterios);
    

}
