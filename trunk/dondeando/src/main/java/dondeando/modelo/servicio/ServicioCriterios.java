package dondeando.modelo.servicio;

import utilidades.busquedas.consultas.Busqueda;
import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.Fecha;

public interface ServicioCriterios {

	Criterio construyeCriterio(String atributo, int comparador, Object valor);
    
    /**
     * Criterio del tipo "atributo NOT IN (SELECT atributoSubc FROM claseSubc WHERE condicionSubc)"
     * 
     * @param atributo          Atributo al que se aplica la subconsulta
     * @param claseSubc         Clase sobre la que se realiza la subconsulta
     * @param atributoSubc      Atributo que devuelve la subconsulta (ID si nulo o vacio)
     * @param condicionSubc     Condición de la subconsulta. Puede ser nula.
     * 
     * @return Nuevo criterio generado.
     */
    Criterio construyeCriterioNoEnSubconsulta(String atributo, Class<?> claseSubc, String atributoSubc, Criterio... criteriosSubc);

    /**
     * Criterio del tipo "NOT EXISTS (SELECT ID FROM claseSubc WHERE condicionSubc)"
     * 
     * @param claseSubc         Clase sobre la que se realiza la subconsulta
     * @param condicionSubc     Condición de la subconsulta. Puede ser nula.
     * 
     * @return Nuevo criterio generado.
     */
    Criterio construyeCriterioNoExiste(Class<?> claseSubc, Criterio... criteriosSubc);
    
    /**
     * Crea un criterio directamente en SQL. Usar con precaución, si no hay más alternativa y
     * acompañándolo de algún tipo de prueba de integración que proteja de futuros cambios en la BD.
     *  
     * @param sql           Cadena que forma el criterio. Usar "?" para los parámetros y "{alias}" 
     *                      para referirse a la tabla principal dentro de una subselect. P.e.:
     *                      "not exists(select 1 from cat_articuloorgano  where articulo={alias}.id
     *                       and organogestor=?)".
     * @param parametros    Objeto(s) por el que se sustituirán las "?".
     * 
     * @return Nuevo criterio generado.
     */
    Criterio construyeCriterioSQL(String sql, Object... parametros);

    /**
     * Crea un criterio para obtener los elementos cuya fecha de inicio es <= que
     * la indicada (o nula) y la fecha de fin es >= que la indicada (o nula). 
     *
     * @param fecha:                Fecha de deberá estar dentro del rango. 
     *                              No se consideran hora. Hoy si null.
     * @param atributoInicioRango:  Atributo que indica la fecha de inicio.
     * @param inicioNull            True indica que atributoInicioRango puede ser null.
     * @param atributoFinRango      Atributo que indica la fecha de fin.
     * @param finNull               True indica que atributoFinRango puede ser null
     * 
     * @return Nuevo criterio generado.
     * @author jcorralejo
     */
    Criterio construyeCriterioRangoFechas(Fecha fecha, String atributoInicioRango, boolean inicioNull, 
                                                       String atributoFinRango,    boolean finNull);
    
    /** Contruye un criterio OR con los criterios pasados */
    Criterio construyeCriterioOR(Criterio... criterios);

    /** Contruye un criterio AND con los criterios pasados */
    Criterio construyeCriterioAND(Criterio... criterios);
    
	void aniadeCriterioACondicion(Condicion condicion, Criterio criterio);

	Condicion creaCondicion();

	Condicion aniadeCriterioACondicion(String atributo, int comparador, Object valor);

	void incluirBusqueda(String atributo, Busqueda busqueda, Busqueda busquedaSinConstruir);
	
		
}
