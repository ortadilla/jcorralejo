package aplicacion;
import android.app.Application;
import android.graphics.Typeface;


public class AutodefinidosApplication extends Application {
	
	private boolean free = false;
	private Typeface fuenteApp;
	
	@Override
	public void onCreate() {
		super.onCreate();
		fuenteApp = Typeface.createFromAsset(getAssets(), "handsean.ttf");
	}
	
	

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Typeface getFuenteApp() {
		return fuenteApp;
	}

	public void setFuenteApp(Typeface fuenteApp) {
		this.fuenteApp = fuenteApp;
	}

}
