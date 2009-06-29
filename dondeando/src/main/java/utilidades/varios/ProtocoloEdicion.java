package utilidades.varios;

import java.util.HashMap;
import java.util.Map;

public class ProtocoloEdicion {
    
    /** Objeto a editar. Si es null, indicará que se quiere crear un nuevo obj */
    private Object objeto;
    /** Outcome al que debe volver tras editar. Puede ser un viewID */
    private String  outcomeVuelta = null;
    /** Operacion que se desea iniciar*/
    private String operacion;
    
    public ProtocoloEdicion(Object objeto, String outcomeVuelta, String operacion) {
        this.objeto = objeto;
        this.outcomeVuelta = outcomeVuelta;
        this.operacion = operacion;
    }
	public Object getObjeto() {
		return objeto;
	}
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	public String getOutcomeVuelta() {
		return outcomeVuelta;
	}
	public void setOutcomeVuelta(String outcomeVuelta) {
		this.outcomeVuelta = outcomeVuelta;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
}
