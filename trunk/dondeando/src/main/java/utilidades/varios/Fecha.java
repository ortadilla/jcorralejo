/**
 * 
 */
package utilidades.varios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Mantiene el concepto de fecha s�lo como d�a, sin considerar la hora, y proporciona utilidades para
 * trabajar con Date y Calendar, as� como otras funciones para comparar, obtener rangos, etc.
 */
public class Fecha implements Comparable {
    
    public static final double MILISEGUNDOS_DIA = 24*60*60*1000;
    
    private Calendar fecha = Calendar.getInstance();
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    {
        dateFormat.setLenient(false); //Para que considere err�neos los "fuera de rango"
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
            throw new IllegalArgumentException("El par�metro fecha no puede ser nulo");
        
        try {
            this.fecha.setTime(dateFormat.parse(fecha));
        } catch (ParseException e) {
            // Cambiamos la excepcion a una Runtime para que no haya que manejarla obligatoriamente,
            // lo que ser�a molesto cuando se trabaje con fechas que sabemos que est�n bien y no fallar�n.
            throw new IllegalArgumentException("Formato de fecha incorrecto: "+fecha,e);
        }
    }
    
    /**
     * Crea la Fecha a partir de un Date, ignorando su hora (usar� 00:00:00) 
     * 
     * @param date Date con el que contruir la Fecha
     * @throws IllegalArgumentException Si el Date es nulo 
     */
    public Fecha(Date date){
        if (date==null)
            throw new IllegalArgumentException("El par�metro date no puede ser nulo");
        
        this.fecha.setTime(date);
        quitarHora();
    }


    /**
     * Crea la Fecha a partir de un Calendar, ignorando su hora (usar� 00:00:00) 
     * 
     * @param calendar Calendar con el que contruir la Fecha
     * @throws IllegalArgumentException Si el Calendar es nulo 
     */
    public Fecha(Calendar calendar) {
        if (calendar==null)
            throw new IllegalArgumentException("El par�metro calendar no puede ser nulo");
        
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
     * Devuelve el n�mero de d�as desde esta fecha hasta la que se pasa por par�metro, de forma que si
     * el par�metro es "hace una semana", devolver� 7, y si es "ma�ana", devolver� -1.
     * @param otraFecha Fecha a comparar.
     * @return N�mero de d�as desde esta fecha hasta la indicada. Positivo para fechas anteriores y viceversa.
     * @throws ClassCastException Si el objeto indicado no es de tipo Fecha.
     */
    public int compareTo(Object otraFecha) {
        if (otraFecha==null)
            throw new IllegalArgumentException("El par�metro 'otraFecha' no puede ser nulo");
        
        return (int)Math.round((this.fecha.getTimeInMillis() - ((Fecha)otraFecha).toCalendar().getTimeInMillis())
                                                   / MILISEGUNDOS_DIA);
    }

    /** A�ade (o resta) el n�mero de d�as indicado a esta fecha. Si es cero, devuelve la misma fecha.
     * 
     * @param dias N�mero de d�as a sumar o restar.
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
     * Devuelve el a�o de esta fecha.
     * @return A�o
     */
    public int obtenerAnio() {
        return fecha.get(Calendar.YEAR);
    }

}
