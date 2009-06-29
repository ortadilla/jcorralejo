package utilidades.busquedas.consultas.implementacion;


import java.util.ArrayList;

import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;

public class CondicionImpl implements Condicion {

    public void agregar(Criterio criterio) {
        if (criterio != null)
            criterios.add(criterio);
    }

    public Criterio[] getCriterios() {
        return criterios.toArray(new Criterio[criterios.size()]);
    }
    
    /** Limpia la lista interna de criterios */
    protected void resetCriterios(){
        criterios = new ArrayList<Criterio>();
    }

    
    /**
     * Metodo equals del objeto.
     * @param other Objeto con el que se compara
     * @return booleano con el resultado de la comparación
     */
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this==other) return true;
        
        if ( !(other instanceof CondicionImpl) ) return false;
        final CondicionImpl that = (CondicionImpl) other;
       
        return criterios.equals(that.criterios);
    }

    /**
     * Devuelve el hashCode del objeto.
     * @return hashCode del objeto.
     */
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (null == criterios ? 0 : criterios.hashCode());       
        return hash;
    }    
    
    private ArrayList<Criterio> criterios = new ArrayList<Criterio>();
    
}
