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

public class Ejemplo3 extends MIDlet implements CommandListener {

	private Form form;
	private Command salir;
	private Command cambiar;
	private Display display;

	private StringItem salida;
	private int cuenta;

	public Ejemplo3() {
		cuenta = 0;

		display = Display.getDisplay(this);

		form = new Form ("Ejemplo 3");
		form.append("Ejemplos de StringItem\n");
		
		salida=new StringItem("CADENA:", "Contador ="+cuenta);
		form.append(salida);

		salir = new Command("Salir", Command.EXIT, 1);
		form.addCommand(salir);
		cambiar = new Command("Cambiar", Command.ITEM, 2);
		form.addCommand(cambiar);

		form.setCommandListener(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.out.println("Fin del Midlet\n");
	}

	protected void pauseApp() {
		System.out.println("Midlet Pausado\n");
	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Midlet Activo\n");
        display.setCurrent(form);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if(arg0.equals(salir) && arg1.equals(form)){
			try {
				destroyApp(true);
				notifyDestroyed();
			} catch (MIDletStateChangeException e) {
				throw new RuntimeException("BUUUUM");
			}
		}
		if(arg0.equals(cambiar) && arg1.equals(form)){
			cuenta++;
			if(cuenta%2==0)
				salida.setLabel(null);
			else
				salida.setLabel("CADENA:");
			
			salida.setText("Contador = "+cuenta);
		}else
			System.out.println("Otro comando pulsado");
	}
}
