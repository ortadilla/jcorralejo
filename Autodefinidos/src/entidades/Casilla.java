package entidades;

import java.util.List;

public class Casilla {
	
	public static final int DIRECCION_DERECHA = 0;
	public static final int DIRECCION_DERECHA_ABAJO = 1;
	public static final int DIRECCION_ABAJO = 2;
	public static final int DIRECCION_ABAJO_DERECHA = 3;
	
	private String letraUsuario;
	private String letraCorrecta;
	private List<Palabra> palabras;
	private List<Integer> direcciones;
	private int numPalabras;
	private boolean pregunta;
	
	public boolean isDerecha(){
		return isDireccion(DIRECCION_DERECHA);
	}
	
	public boolean isDerechaAbajo(){
		return isDireccion(DIRECCION_DERECHA_ABAJO);
	}
	
	public boolean isAbajo(){
		return isDireccion(DIRECCION_ABAJO);
	}
	
	public boolean isAbajoDerecha(){
		return isDireccion(DIRECCION_ABAJO_DERECHA);
	}
	
	private boolean isDireccion(int direccion){
		boolean res = false;
		if(direcciones!=null && !direcciones.isEmpty()){
			for(Integer dir : direcciones){
				if(direccion==dir){
					res = true;
					break;
				}
			}
		}
		return res;
	}
	
	
	
	public Casilla(String letraUsuario, String letraCorrecta, List<Palabra> palabras, int numPalabras, List<Integer> direcciones, boolean pregunta) {
		super();
		this.letraUsuario = letraUsuario;
		this.letraCorrecta = letraCorrecta;
		this.palabras = palabras;
		this.numPalabras = numPalabras;
		this.direcciones = direcciones;
		this.pregunta = pregunta;
	}
	
	public List<Integer> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<Integer> direcciones) {
		this.direcciones = direcciones;
	}
	public boolean isPregunta() {
		return pregunta;
	}
	public void setPregunta(boolean pregunta) {
		this.pregunta = pregunta;
	}
	public List<Palabra> getPalabras() {
		return palabras;
	}
	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}
	public String getLetraUsuario() {
		return letraUsuario;
	}
	public void setLetraUsuario(String letraUsuario) {
		this.letraUsuario = letraUsuario;
	}
	public String getLetraCorrecta() {
		return letraCorrecta;
	}
	public void setLetraCorrecta(String letraCorrecta) {
		this.letraCorrecta = letraCorrecta;
	}
	public int getNumPalabras() {
		return numPalabras;
	}
	public void setNumPalabras(int numPalabras) {
		this.numPalabras = numPalabras;
	}
	
}
