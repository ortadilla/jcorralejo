package dondeando.modelo.servicio.implementacion;



import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Busqueda;
import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;
import utilidades.busquedas.consultas.implementacion.CondicionImpl;
import utilidades.busquedas.consultas.implementacion.CriterioImpl;
import utilidades.varios.Fecha;

import dondeando.modelo.servicio.ServicioCriterios;


@Name(SERVICIO_CRITERIOS)
@Scope(ScopeType.CONVERSATION)
public class ServicioCriteriosImpl implements ServicioCriterios {

    public Criterio construyeCriterio(String atributo, int comparador, Object valor) {
        return new CriterioImpl(atributo, comparador, valor);
    }
    
    public Criterio construyeCriterioNoEnSubconsulta(String atributo, Class<?> claseSubc, String atributoSubc, Criterio... criteriosSubc){
        return new CriterioImpl(atributo, Criterio.NO_EN_SUBCONSULTA, criteriosSubc, claseSubc, atributoSubc);
    }

    public Criterio construyeCriterioNoExiste(Class<?> claseSubc, Criterio... criteriosSubc){
        return new CriterioImpl(null, Criterio.NO_EXISTE, criteriosSubc, claseSubc, null);
    }
    
	public Criterio construyeCriterioSQL(String sql, Object... parametros) {
        return new CriterioImpl(sql, Criterio.SQL, parametros);
    }

    public void aniadeCriterioACondicion(Condicion condicion, Criterio criterio) {
		condicion.agregar(criterio);

	}

	public Condicion creaCondicion() {
		return new CondicionImpl();
	}

	public Condicion aniadeCriterioACondicion(String atributo, int comparador, Object valor) {
		Condicion condicion = creaCondicion();
		Criterio criterio = construyeCriterio(atributo, comparador, valor);
		aniadeCriterioACondicion(condicion, criterio);
		return condicion;
	}

	public void incluirColeccion(String atributo, Condicion condicionParent, Condicion condicionHija) {
		if (condicionHija.getCriterios().length > 0) {
			Criterio criterioColeccion = construyeCriterio(atributo, Criterio.COLECCION, condicionHija);
			aniadeCriterioACondicion(condicionParent, criterioColeccion);
		}
	}

	public void incluirBusqueda(String atributo, Busqueda busqueda, Busqueda busquedaSinConstruir) {
		if (busquedaSinConstruir != null) {
			Condicion condicion = busquedaSinConstruir.construyeCondicion();
			incluirColeccion(atributo, busqueda, condicion);
		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioRangoFechas(com.hp.geos.core.comun.utilidades.Fecha, java.lang.String, boolean, java.lang.String, boolean)
	 */
	public Criterio construyeCriterioRangoFechas(Fecha fecha, String atributoInicioRango, boolean inicioNull, 
	                                                          String atributoFinRango, boolean finNull) {
		//Si no se indica la fecha se toma el día de hoy
	    Fecha fec = fecha != null? fecha : new Fecha();
	    //El inicio tendrá que ser menor que el día siguiente, para no tener en cuenta las horas.
		Fecha diaSiguiente = new Fecha(fec.toDate()).agregarDias(1);
		
		Criterio criterioIni = construyeCriterio(atributoInicioRango ,Criterio.MENOR_QUE, diaSiguiente.toDate());
		if(inicioNull)
		    criterioIni = construyeCriterio("",Criterio.O, new Criterio[]{criterioIni,
		                                    construyeCriterio(atributoInicioRango,Criterio.IGUAL, null)});

		Criterio criterioFin = construyeCriterio(atributoFinRango ,Criterio.MAYOR_IGUAL_QUE, fec.toDate());
		if(finNull)
		    criterioFin = construyeCriterio("",Criterio.O, new Criterio[]{criterioFin,
		                                    construyeCriterio(atributoFinRango,Criterio.IGUAL, null)});
		
		//Devolvemos el AND de los criterios
		return construyeCriterio("",Criterio.Y, new Criterio[]{criterioIni,criterioFin}); 
		
	}

    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioOR(com.hp.geos.core.comun.consultas.Criterio[])
     */
    public Criterio construyeCriterioOR(Criterio... criterios) {
        return new CriterioImpl(null, Criterio.O, criterios);
    }
    /* (non-Javadoc)
     * @see com.hp.geos.core.modelo.servicio.ServicioCriterios#construyeCriterioAND(com.hp.geos.core.comun.consultas.Criterio[])
     */
    public Criterio construyeCriterioAND(Criterio... criterios) {
        return new CriterioImpl(null, Criterio.Y, criterios);
    }

}
