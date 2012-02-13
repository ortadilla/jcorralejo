package es.jcorralejo.android.callejeras2012.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class ActualizarPosicionService extends Service {

	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 1000;
	private ActualizaPosicionLocationListener mListener;
	private LocationManager lm;
	
	private int count = 0;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mListener = new ActualizaPosicionLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, mListener);

		_startService();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		_shutdownService();
	}
	
	private void _startService() {
//		timer.scheduleAtFixedRate(
//			new TimerTask() {
//				public void run() {
//					count++;
//					handler.sendEmptyMessage(0);
//				}
//			},
//			0,
//			UPDATE_INTERVAL);
	}
	
	private void _shutdownService() {
		if (timer != null) timer.cancel();
		lm.removeUpdates(mListener);
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			System.out.println("¡¡¡CONTADOR ACTUALIZADO!!! --> "+count);
		}
	};
	
	
	
	private class ActualizaPosicionLocationListener implements LocationListener {
		public ActualizaPosicionLocationListener(){
		}
		
		public void onLocationChanged(Location location) {
			System.out.println("Nueva posición: "+location.getLongitude()+","+location.getLatitude());
		}
		
		public void onProviderDisabled(String provider) {}
		public void onProviderEnabled(String provider) {}
		public void onStatusChanged(String provider, int status, Bundle extras) {}
	}

}
