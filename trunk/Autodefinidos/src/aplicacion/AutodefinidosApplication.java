package aplicacion;
import android.app.Application;


public class AutodefinidosApplication extends Application {
	
	private boolean free = false;

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
