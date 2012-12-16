package es.jcorralejo.android.carnavapp;

import com.actionbarsherlock.app.SherlockFragment;

public class NamedFragment extends SherlockFragment{
	protected String titulo;
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo!=null ? titulo.toUpperCase() : "";
	}
}
