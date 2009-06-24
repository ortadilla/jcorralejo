package pruebas;

import java.util.Date;

import net.sourceforge.floggy.persistence.Persistable;

public class Libro implements Persistable{
	
	String nombre;
	Date fecha;
	Integer ISBN;
	
	public Libro(){
		super();
	}
	public String toString() {
		return nombre +" "+ fecha + " "+ ISBN;
	}

}
