package es.jcorralejo.android.callejeras2012.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class ActualizarPosicionService extends Service {

	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 1000;
	
	private int count = 0;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		_startService();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		_shutdownService();
	}
	
	private void _startService() {
		timer.scheduleAtFixedRate(
			new TimerTask() {
				public void run() {
					count++;
					handler.sendEmptyMessage(0);
				}
			},
			0,
			UPDATE_INTERVAL);
	}
	
	private void _shutdownService() {
		if (timer != null) timer.cancel();
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			System.out.println("¡¡¡CONTADOR ACTUALIZADO!!! --> "+count);
		}
	};

}
