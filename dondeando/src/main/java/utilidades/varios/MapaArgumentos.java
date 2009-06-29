package utilidades.varios;


import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;

import java.util.*;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Clase de utilidad que nos permitir� el paso de argumentos entre p�ginas durante la navegaci�n. 
 */
@Name(MAPA_ARGUMENTOS)
@Scope(ScopeType.EVENT)
public class MapaArgumentos{
	
	/**
	 * Contenedor de los argumentos que se pasan durante la navegaci�n entre p�ginas
	 * Ser� un mapa en el que cada elemento es una tupla (nombreParametro, valorParametro).
	 */
	private Map<Object, Object> mapa = new HashMap<Object, Object>();
	
	
    public MapaArgumentos() {}

    /** Constructor r�pido para cuando necesitamos un argumento */
    public MapaArgumentos(Object nombreArgumento, Object valorArgumento) {
        setArgumento(nombreArgumento, valorArgumento);
    }

    /** Constructor r�pido para cuando ya tenemos un mapa con valores */
    public MapaArgumentos(Map<? extends Object, ? extends Object> argumentos) {
        setArgumentos(argumentos);
    }

    /**
     * Si el mapa incluye una entrada con el protocolo, devuelve ese protocolo. Si no, devuelve
     * un protocolo inicializado con los argumentos de busqueda inidicados, y el resto con los valores
     * por defecto. Ver {@link ProtocoloBusqueda}.
     * <br>
     * Si no se incluye ninguno, devuelve NULL. 
     * 
     * @return Protocolo incluido en el mapa (directamente o mediante valores individuales).
     *         NULL si no hay protocolo ni valores.
     * @see ProtocoloBusqueda
     */
    @SuppressWarnings("unchecked")
    public ProtocoloBusqueda getProtocoloBusqueda(){
        ProtocoloBusqueda protocolo = null;
        if(mapa.containsKey(NombresBean.PROTOCOLO_BUSQUEDA))
            protocolo = (ProtocoloBusqueda)mapa.get(NombresBean.PROTOCOLO_BUSQUEDA);
        return protocolo;
    }
    
    /**
     * Si el mapa incluye una entrada con el protocolo de edici�n, lo devuelve. Si no, devuelve NULL. 
     * 
     * @return Protocolo de edici�n incluido en el mapa.
     * @see ProtocoloEdicion
     */
    @SuppressWarnings("unchecked")
    public ProtocoloEdicion getProtocoloEdicion(){
        return contieneProtocoloEdicion() ? (ProtocoloEdicion)mapa.get(NombresBean.PROTOCOLO_EDICION)
                                          : null;
    }

    public boolean contieneProtocoloBusqueda(){
        return mapa.containsKey(NombresBean.PROTOCOLO_BUSQUEDA);
    }
    public boolean contieneProtocoloEdicion(){
        return mapa.containsKey(NombresBean.PROTOCOLO_EDICION);
    }
    

    /** Indica si el mapa contiene el argumento indicado */
    public boolean contiene(String argumento){
        return mapa.containsKey(argumento);
    }
	
	/**
	 * M�todo que limpia los valores del mapa.
	 */
	public void limpiaMapa(){
		mapa.clear();
	}
	
	/**
	 * M�todo que devuelve el mapa completo.
	 * 
	 * @return Map El objeto mapa que contiene los par�metros.
	 * @deprecated No se debe usar el mapa interno directamente. Usa {@link #contiene(String)}.
	 */
	@SuppressWarnings("unchecked")
    @Deprecated
	public Map getMapa(){
		return mapa;
	}
	
	/**
	 * Inserta un argumento al mapa.
	 * 
	 * @param nombreArgumento Nombre del argumento que se quiere cargar en el mapa.
	 * @param valorArgumento Valor del argumento que se quiere cargar en el mapa.
	 */
	public void setArgumento(Object nombreArgumento, Object valorArgumento){
		
		if (nombreArgumento == null)
			throw new IllegalArgumentException("Se est� intentado insertar un argumento con " +
					"nombre nulo");
		
		mapa.put(nombreArgumento, valorArgumento);
	}
	
	/**
	 * M�todo que devuelve el valor de un argumento cargado en el mapa.
	 * 
	 * @param nombreArgumento Nombre del argumento al que se quiere buscar el valor.
	 * @return Object Valor del argumento buscado en el mapa
	 */
	public Object getArgumento (Object nombreArgumento){
		
		if (nombreArgumento == null)
			return null;
		
		return mapa.get(nombreArgumento);
	}
	
	/** Devuelve un set con los nombres de los arguementos que contiene el mapa */
	public Set<Object> getClaves(){
	    return mapa.keySet();
	}
	/** Devuelve una colecci�n con los valores que contienen los argumentos del mapa */
	public Collection<Object> getValores(){
	    return mapa.values();
	}
    /** Inserta todos los argumentos pasados en el mapa */
	public void setArgumentos(Map<? extends Object,? extends Object> argumentos){
	    if(argumentos!=null)
	        mapa.putAll(argumentos);
	}
	/** Quita el argumento indicado y devuelve su valor, si exist�a o null si no exist�a */
	public Object borrar(Object argumento){
	    return mapa.remove(argumento);
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Mapa de Argumentos: "+mapa.toString();
    }
	
	

}
