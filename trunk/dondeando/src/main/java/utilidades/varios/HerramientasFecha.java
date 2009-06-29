package utilidades.varios;

import java.util.Date;

/**
 * @author jjove
 * Métodos de uso común para fechas
 */
public class HerramientasFecha {
	
	
	/**
	 * Calcula el número de días entre fecha inicial y la fecha final.
	 * No tiene en cuenta horas-minutos-segundos-milisegundos.
	 * 
	 * @param FechaInicio
	 * @param fechaFin
	 * @return Número de dias entre la fecha inicial y la fecha final. 0 si la fecha inicial es
     *         mayor que la final.
	 */
	public static Integer numeroDiasEntreFechas(Date fechaInicio, Date fechaFin) {
        int dias = new Fecha(fechaInicio).compareTo(new Fecha(fechaFin));
        return dias<0 ? -dias : 0;  
	}

	/**
	 * Suma días a una fecha recibida como argumento.
	 * 
	 * @param fecha
	 * @param diasAumentar Días que queremos aumentar a la fecha
	 * @return Nuevo Date con la fecha calculada.
	 */
	public static Date agregaDias(Date fecha, Integer diasAumentar) {
	    if (diasAumentar == null )
	        throw new IllegalArgumentException("diasAumentar");
        
        return new Fecha(fecha).agregarDias(diasAumentar).toDate();
	}

    		
	
}
