package utilidades.varios;

import java.util.HashMap;
import java.util.Map;


public class ProtocoloBusqueda {
    
    /** Valores a usar como criterios de b�squeda predefinidos */
    private Map<String, Object> paramsBusqueda = new HashMap<String, Object>();
    
    /** Si se debe lanzar directamente la b�squeda o esperar a que el usuario lo indique */
    private boolean lanzaConsulta = false;
    
    /** Si se debe devolver alg�n elemento */
    private boolean paraDevolver = false;
    
    /** Outcome al que debe volver tras buscar. No deber�a ser un viewID */
    private String  outcomeVuelta = null;
    
    
    
    /** 
     * Crea un nuevo protocolo con los valores indicados y el resto por defecto: Sin par�metros,
     * no lanza la consulta y est� en la misma conversaci�n. 

     * @param outcomeVuelta  Outcome al que se debe volver. Imprescindible si no es anidada ni popup.
     */
    public ProtocoloBusqueda(String outcomeVuelta){
        this.outcomeVuelta = outcomeVuelta;
        this.paramsBusqueda = new HashMap<String, Object>();
    }
    
    /** 
     * Crea un nuevo protocolo. 
     * @param paramsBusqueda        Parametros a cargar en el formulario de b�squeda. 
     * @param lanzaConsulta         Si se debe lanzar la consulta directamente o debe lanzarla el usuario.
     * @param outcomeVuelta         Outcome al que se debe volver. Imprescindible si no es anidada ni popup.
     */
    public ProtocoloBusqueda(Map<String, Object> paramsBusqueda, boolean lanzaConsulta, String outcomeVuelta, boolean paraDevolver) {
        super();
        this.paramsBusqueda = paramsBusqueda;
        this.lanzaConsulta = lanzaConsulta;
        this.outcomeVuelta = outcomeVuelta;
        this.paraDevolver = paraDevolver;
    }
    
    /** 
     * A�ade un par�metro de b�squeda.
     * @return El propio protocolo, para que sea f�cil concatenarlos.
     */
    public ProtocoloBusqueda addParametro(String clave, Object valor){
    	if(paramsBusqueda==null)
    		paramsBusqueda = new HashMap<String, Object>();
        paramsBusqueda.put(clave, valor);
        return this;
    }

	public Map<String, Object> getParamsBusqueda() {
		return paramsBusqueda;
	}

	public void setParamsBusqueda(Map<String, Object> paramsBusqueda) {
		this.paramsBusqueda = paramsBusqueda;
	}

	public boolean isLanzaConsulta() {
		return lanzaConsulta;
	}

	public void setLanzaConsulta(boolean lanzaConsulta) {
		this.lanzaConsulta = lanzaConsulta;
	}

	public String getOutcomeVuelta() {
		return outcomeVuelta;
	}

	public void setOutcomeVuelta(String outcomeVuelta) {
		this.outcomeVuelta = outcomeVuelta;
	}

	public boolean isParaDevolver() {
		return paraDevolver;
	}

	public void setParaDevolver(boolean paraDevolver) {
		this.paraDevolver = paraDevolver;
	}
}
