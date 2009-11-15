/**
 * 
 */
package utilidades.varios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Mantiene el concepto de fecha sólo como día, sin considerar la hora, y proporciona utilidades para
 * trabajar con Date y Calendar, así como otras funciones para comparar, obtener rangos, etc.
 */
public class Fecha implements Comparable {
    
    public static final double MILISEGUNDOS_DIA = 24*60*60*1000;
    
    private Calendar fecha = Calendar.getInstance();
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    {
        dateFormat.setLenient(false); //Para que considere erróneos los "fuera de rango"
    }

    /**
     * Crea un objeto representando la fecha actual
     */
    public Fecha() {
        quitarHora();
    }
    
    /**
     * Crea la Fecha a partir de una cadena con el formato dd/mm/aaaa.
     * 
     * @param fecha Cadena con el formato dd/mm/aaaa
     * 
     * @throws IllegalArgumentException Si la cadena es nula o el formato de la fecha no es dd/mm/aaaa
     */
    public Fecha(String fecha){
        if (fecha==null)
            throw new IllegalArgumentException("El parámetro fecha no puede ser nulo");
        
        try {
            this.fecha.setTime(dateFormat.parse(fecha));
        } catch (ParseException e) {
            // Cambiamos la excepcion a una Runtime para que no haya que manejarla obligatoriamente,
            // lo que sería molesto cuando se trabaje con fechas que sabemos que están bien y no fallarán.
            throw new IllegalArgumentException("Formato de fecha incorrecto: "+fecha,e);
        }
    }
    
    /**
     * Crea la Fecha a partir de un Date, ignorando su hora (usará 00:00:00) 
     * 
     * @param date Date con el que contruir la Fecha
     * @throws IllegalArgumentException Si el Date es nulo 
     */
    public Fecha(Date date){
        if (date==null)
            throw new IllegalArgumentException("El parámetro date no puede ser nulo");
        
        this.fecha.setTime(date);
        quitarHora();
    }


    /**
     * Crea la Fecha a partir de un Calendar, ignorando su hora (usará 00:00:00) 
     * 
     * @param calendar Calendar con el que contruir la Fecha
     * @throws IllegalArgumentException Si el Calendar es nulo 
     */
    public Fecha(Calendar calendar) {
        if (calendar==null)
            throw new IllegalArgumentException("El parámetro calendar no puede ser nulo");
        
        this.fecha.setTimeInMillis(calendar.getTimeInMillis());
        quitarHora();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(!(obj instanceof Fecha)) return false;
        
        return fecha.equals(((Fecha)obj).toCalendar());
    }

    @Override
    public int hashCode() {
        return fecha.hashCode();
    }

    @Override
    /**
     * Devuelve la Fecha como una cadena con el formato dd/mm/aaaa
     */
    public String toString() {
        return dateFormat.format(fecha.getTime());
    }

    public Date toDate() {
        return fecha.getTime();
    }

    public Calendar toCalendar() {
        return fecha;
    }
    
    /**
     * Devuelve el número de días desde esta fecha hasta la que se pasa por parámetro, de forma que si
     * el parámetro es "hace una semana", devolverá 7, y si es "mañana", devolverá -1.
     * @param otraFecha Fecha a comparar.
     * @return Número de días desde esta fecha hasta la indicada. Positivo para fechas anteriores y viceversa.
     * @throws ClassCastException Si el objeto indicado no es de tipo Fecha.
     */
    public int compareTo(Object otraFecha) {
        if (otraFecha==null)
            throw new IllegalArgumentException("El parámetro 'otraFecha' no puede ser nulo");
        
        return (int)Math.round((this.fecha.getTimeInMillis() - ((Fecha)otraFecha).toCalendar().getTimeInMillis())
                                                   / MILISEGUNDOS_DIA);
    }

    /** Añade (o resta) el número de días indicado a esta fecha. Si es cero, devuelve la misma fecha.
     * 
     * @param dias Número de días a sumar o restar.
     * @return La propia Fecha ya modificada.
     */ 
    public Fecha agregarDias(int dias) {
        this.fecha.add(Calendar.DATE, dias);
        return this;
    }
    
    private void quitarHora() {
        fecha.set(Calendar.HOUR_OF_DAY,0);
        fecha.set(Calendar.MINUTE,0);
        fecha.set(Calendar.SECOND,0);
        fecha.set(Calendar.MILLISECOND,0);
    }

    /**
     * Devuelve el año de esta fecha.
     * @return Año
     */
    public int obtenerAnio() {
        return fecha.get(Calendar.YEAR);
    }

}
