package es.jcorralejo.android.carnavapp.app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.entidades.Enlace;
import es.jcorralejo.android.carnavapp.entidades.InfoAnio;
import es.jcorralejo.android.carnavapp.entidades.Noticia;
import es.jcorralejo.android.carnavapp.utils.Constantes;
import es.jcorralejo.android.carnavapp.utils.RssDownloadHelper;

public class CarnavappApplication extends Application {
	
	private List<Integer> favoritas = new ArrayList<Integer>();
	private List<InfoAnio> infoAnios;
	private List<Noticia> noticias;
	private Map<String, List<Enlace>> enlaces;
	private int anioActual;
	
	private ActualizarPostAsyncTask tarea;
	private boolean error = false;
	private boolean actualizando;
	
	@Override
	public void onCreate() {
		super.onCreate();
		infoAnios = new ArrayList<InfoAnio>();
		noticias = new ArrayList<Noticia>();
		enlaces = new HashMap<String, List<Enlace>>();
		
//		cargarDatos(null);
		System.out.println(" ------ COMENZANDO APPLICATION ------ ");
	}
	
	public String getRssUrl(){
		return "https://jcorralejo.googlecode.com/svn/trunk/Carnavapp/carnavapp.xml";
	}
	
//	public String getTextoDia(String dia){
//		String result = "";
//		if(getConcurso().get(Constantes.FASE_PREELIMINAR).contains(dia))
//			result = "Preliminar "+ (getConcurso().get(Constantes.FASE_PREELIMINAR).indexOf(dia)+1);
//		else if(getConcurso().get(Constantes.FASE_CUARTOS).contains(dia))
//			result = "Cuarto de Final "+ (getConcurso().get(Constantes.FASE_CUARTOS).indexOf(dia)+1);
//		else if(getConcurso().get(Constantes.FASE_SEMIS).contains(dia))
//			result = "Semifinal "+ (getConcurso().get(Constantes.FASE_SEMIS).indexOf(dia)+1);
//		else if(getConcurso().get(Constantes.FASE_FINAL).contains(dia))
//			result = "Final";
//		return result;
//	}
	
//	public boolean isPreeliminar(){
//		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
//		String dia = sdf.format(new Date());
//		return concurso.get(Constantes.FASE_PREELIMINAR).contains(dia);
//	}
//
//	public boolean isCuartos(){
//		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
//		String dia = sdf.format(new Date());
//		return concurso.get(Constantes.FASE_CUARTOS).contains(dia);
//	}
	
	
	public List<Integer> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(List<Integer> favoritas) {
		this.favoritas = favoritas;
	}

	public Map<String, List<Enlace>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(Map<String, List<Enlace>> enlaces) {
		this.enlaces = enlaces;
	}
	
	
	public void cargarDatos(ProgressDialog pd){
		//Comprobamos si hay conexión ha internet
		if(networkAvailable()){
			if(!actualizando){
				tarea = new ActualizarPostAsyncTask(pd, this);
				tarea.execute();
				error = false;
			}else{
				ocultarPD(pd);
				error = false;
			}
		}else{
			ocultarPD(pd);
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_conexion), Toast.LENGTH_LONG).show();
			error = true;
		}
	}
	
	private boolean networkAvailable() {
		ConnectivityManager connectMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectMgr != null) {
			NetworkInfo[] netInfo = connectMgr.getAllNetworkInfo();
			if (netInfo != null) {
				for (NetworkInfo net : netInfo) {
					if (net.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} 
		return false;
	}
	
	class ActualizarPostAsyncTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		private CarnavappApplication app;
		
		public ActualizarPostAsyncTask(ProgressDialog pd, CarnavappApplication app) {
			this.pd = pd;
			this.app = app;
		}

		@Override
		protected Void doInBackground(Void... params) {
			actualizando = true;
			RssDownloadHelper.updateRssData(getRssUrl(), app);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			ocultarPD(pd);
			actualizando = false;
			super.onPostExecute(result);
		}
		
		@Override
		protected void onCancelled() {
			ocultarPD(pd);
			actualizando = false;
			super.onCancelled();
		}
		
	}
	
	private void ocultarPD(ProgressDialog pd){
		if(pd!=null && pd.isShowing()){
			try{
				pd.dismiss();
				pd = null;
			}catch (Exception e) {
			}
		}
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isActualizando() {
		return actualizando;
	}

	public void setActualizando(boolean actualizando) {
		this.actualizando = actualizando;
	}

	public int getAnioActual() {
		return anioActual;
	}

	public void setAnioActual(int anioActual) {
		//Sólo setearemos el año actual que viene del XML si el usuario
		//no ha guardado uno
		SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
		int ultima = prefs.getInt(Constantes.CTE_ANIO_ACTUAL_USUARIO, -1);
		if(ultima<0)
			this.anioActual = anioActual;
		else
			this.anioActual = ultima;
	}
	
	public InfoAnio getInfoAnioActual(){
		InfoAnio infoAnioActual = null;
		int anioAtual = getAnioActual();
		for(InfoAnio infoAnio : getInfoAnios()){
			if(infoAnio.getAnio() == anioAtual){
				infoAnioActual = infoAnio;
				break;
			}
		}
		return infoAnioActual;
	}

	public List<InfoAnio> getInfoAnios() {
		return infoAnios;
	}

	public void setInfoAnios(List<InfoAnio> infoAnios) {
		this.infoAnios = infoAnios;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

}
