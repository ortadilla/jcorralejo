package es.jcorralejo.android.callejeras2012;


import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;
import es.jcorralejo.android.callejeras2012.entidades.Lugar;
import es.jcorralejo.android.callejeras2012.utils.RssDownloadHelper;

public class CallejerasApplication extends Application {
	
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private List<Lugar> puntosInteres = new ArrayList<Lugar>();
	private List<Integer> favoritas = new ArrayList<Integer>();
	
	private ActualizarPostAsyncTask tarea;
	private boolean error = false;
	private Boolean actualizando = Boolean.FALSE;
	
	@Override
	public void onCreate() {
		super.onCreate();
		agrupaciones = new ArrayList<Agrupacion>();
		puntosInteres = new ArrayList<Lugar>();
		cargarDatos(null);
		System.out.println(" ------ COMENZANDO APPLICATION ------ ");
	}
	
	public String getRssUrl(){
		return "http://jcorralejo.googlecode.com/svn/trunk/coac2012/coac2012.xml";
	}
	
	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}
	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}
	public List<Integer> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(List<Integer> favoritas) {
		this.favoritas = favoritas;
	}

	public void cargarDatos(ProgressDialog pd){
		//Comprobamos si hay conexión ha internet
		if(networkAvailable()){
			if(!actualizando){
				tarea = new ActualizarPostAsyncTask(pd);
				tarea.execute();
				error = false;
			}
		}else{
			Toast.makeText(getApplicationContext(), "COAC2012 necesita una conexión a Internet para funcionar. Por favor, vuelva a intentarlo más tarde", Toast.LENGTH_LONG).show();
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
	
    public Agrupacion getAgrupacionPorId(int id){
    	for(Agrupacion a : getAgrupaciones()){
    		if(a.getId()==id)
    			return a;
    	}
    	return null;
    }
    
    public Agrupacion getAgrupacionPorUsuarioPass(String usuario, String pass){
    	for(Agrupacion a : getAgrupaciones()){
    		if(a.getUsuario()!=null && a.getUsuario().equals(usuario)
    		&& a.getPass()!=null && a.getPass().equals(pass))
    			return a;
    	}
    	return null;
    }
    
	
	class ActualizarPostAsyncTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		
		public ActualizarPostAsyncTask(ProgressDialog pd) {
			this.pd = pd;
		}

		@Override
		protected Void doInBackground(Void... params) {
			actualizando = true;
			RssDownloadHelper.updateRssData(getRssUrl(), getAgrupaciones(), getPuntosInteres());
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			ocultarPD();
			actualizando = false;
			super.onPostExecute(result);
		}
		
		@Override
		protected void onCancelled() {
			ocultarPD();
			actualizando = false;
			super.onCancelled();
		}
		
		private void ocultarPD(){
			if(pd!=null && pd.isShowing()){
				try{
					pd.dismiss();
					pd = null;
				}catch (Exception e) {
				}
			}
		}

		
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<Lugar> getPuntosInteres() {
		return puntosInteres;
	}

	public void setPuntosInteres(List<Lugar> puntosInteres) {
		this.puntosInteres = puntosInteres;
	}

	public Boolean getActualizando() {
		return actualizando;
	}

	public void setActualizando(Boolean actualizando) {
		this.actualizando = actualizando;
	}

}
