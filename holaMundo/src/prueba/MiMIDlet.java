package prueba;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class MiMIDlet extends MIDlet implements CommandListener{

	private Form formInicio;
	private Form formFin;
	private Command avanza;
	private Command retrocede;
	Display display;
	
	public MiMIDlet() {
		display = Display.getDisplay(this);
		formInicio = new Form("INICIO");
		formFin = new Form("FIN");
		display.setCurrent(formInicio);
		
		avanza = new Command("Avanza", Command.ITEM, 1);
		formInicio.addCommand(avanza);
		
		retrocede = new Command("Retrocede", Command.ITEM, 1);
		formFin.addCommand(retrocede);
		formInicio.setCommandListener(this);
		formFin.setCommandListener(this);
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

	public void commandAction(Command arg0, Displayable arg1) {
		if(arg0.equals(avanza) && arg1.equals(formInicio)){
			display.setCurrent(formFin);
		}
		else if(arg0.equals(retrocede) && arg1.equals(formFin)){
			display.setCurrent(formInicio);
		}
	}

}
