package pruebas;

import java.util.Date;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.PersistableManager;

public class HolaFloggy extends MIDlet {

	public HolaFloggy() {
		Libro libroCargar = new Libro();
		Libro libroGuardar = new Libro();
		libroGuardar.fecha = new Date();
		libroGuardar.ISBN = new Integer(1234);
		libroGuardar.nombre = "Nombre libro";
		
		PersistableManager pm = PersistableManager.getInstance();
		try {
			int idLibro = pm.save(libroGuardar);
			Form form = new Form("OK!!: "+idLibro);
			Display.getDisplay(this).setCurrent(form);
			
			pm.load(libroCargar, idLibro);
			form.append(libroCargar.toString());
		} catch (FloggyException e) {
			
		}
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

}
