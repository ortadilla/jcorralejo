package es.jcorralejo.android.carnavapp.entidades;


public class InfoAnio {
	
	private int anio;
	private Concurso concurso;
	
	public InfoAnio(int anio) {
		this.anio = anio;
		concurso = new Concurso();
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

}
