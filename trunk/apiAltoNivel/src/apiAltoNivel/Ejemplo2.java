package apiAltoNivel;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class Ejemplo2 extends MIDlet implements CommandListener {

	private Form form;
	private Command command1;
	private Command command2;
	private Display display;
	
	private String cadena1 = "cadena1\n";
	private String cadena2 = "cadena2\n";
	
	public Ejemplo2() {
		display = Display.getDisplay(this);
		
		form = new Form("TITULO");
		Ticker ticker = new Ticker("Ticker en movimiento");
		form.setTicker(ticker);
		form.append(cadena1);
		display.setCurrent(form);
		
		command1 = new Command("Salir", Command.EXIT, 1);
		form.addCommand(command1);
		command2 = new Command("Modifica cadena", Command.ITEM, 2);
		form.addCommand(command2);

		form.setCommandListener(this);

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
		if(arg0.equals(command1) && arg1.equals(form)){
			 try {
				destroyApp(true);
				notifyDestroyed();
			} catch (MIDletStateChangeException e) {
				throw new RuntimeException("BUUUUM");
			}
		}
		if(arg0.equals(command2) && arg1.equals(form)){
			String cadena = ((StringItem)form.get(0)).getText().equals(cadena1) ? cadena2 : cadena1; 
			form.deleteAll();
			form.append(cadena);
		}else
			System.out.println("Otro comando pulsado");
	}

}
