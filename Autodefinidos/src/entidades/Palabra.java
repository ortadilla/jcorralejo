package entidades;

import java.io.Serializable;

public class Palabra implements Serializable{
	
	public static final String DIFICULTAD_FACIL = "FACIL";
	public static final String DIFICULTAD_NORMAL = "NORMAL";
	public static final String DIFICULTAD_DIFICIL = "DIFICIL";
	
	private static final long serialVersionUID = -3165576754401688970L;
	
	private Integer id;
	private String palabra;
	private String definicion;
	private String dificultad;
	
	
	
	public Palabra(Integer id, String palabra, String definicion, String dificultad) {
		super();
		this.id = id;
		this.palabra = palabra;
		this.definicion = definicion;
		this.dificultad = dificultad;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public String getDefinicion() {
		return definicion;
	}
	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

}
