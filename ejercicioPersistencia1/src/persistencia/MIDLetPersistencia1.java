package persistencia;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;


public class MIDLetPersistencia1 extends MIDlet implements CommandListener {

	private static final int NUM_MAX_RS = 10;
	
	private Form formHome;
	private Form formCrearRegistro;
	private Command commandCrear;
	private Command commandConfirmar;
	private Display display;
	
	private RecordStore[] rs = new RecordStore[NUM_MAX_RS];
	private int sizeRS = 0;


	public MIDLetPersistencia1() {
		display = Display.getDisplay(this);

		formHome = new Form("LISTA DBs");
		//Añadir al formHome el lista de RecordStore
		commandCrear = new Command("Crear RS", Command.ITEM, 1);
		formHome.addCommand(commandCrear);
		formHome.setCommandListener(this);
		formHome.setCommandListener(this);

		formCrearRegistro = new Form("CREAR RECORD STORE");
		commandConfirmar = new Command("Confirmar", Command.ITEM, 1);
		formCrearRegistro.addCommand(commandConfirmar);
		formCrearRegistro.setCommandListener(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.out.println("Fin del Midlet\n");
	}

	protected void pauseApp() {
		System.out.println("Midlet Pausado\n");
	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Midlet Activo\n");
        display.setCurrent(formHome);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if(arg0.equals(commandCrear) && arg1.equals(formHome)){
			//Comprobamos si hemos llegado al número máximo de RS
			if(sizeRS<NUM_MAX_RS-1){
				//Vamos al form para crear el RS
				display.setCurrent(formCrearRegistro);
				formCrearRegistro.deleteAll();

				//Creamos el TextField
				TextField textField = new TextField("Nombre RS", null, 10, TextField.ANY);
				formCrearRegistro.append(textField);
			}else{
				//TODO: Nombre erróneo
				Alert alerta = new Alert("ERROR",
									 	 "Número máximo de RS",
									 	 null, 
									 	 AlertType.INFO);
				display.setCurrent(alerta);
			}
		}
		else if(arg0.equals(commandConfirmar) && arg1.equals(formCrearRegistro)){
			//Obtenemos el texto del input
			TextField textField = (TextField)formCrearRegistro.get(0);
			String nombre = textField.getString();
			if(nombre!=null && !nombre.equals("")){
				openRecStore(nombre);
				display.setCurrent(formHome);
			}else{
				//TODO: Nombre erróneo
				Alert alerta = new Alert("ERROR",
									 	 "El nombre del RS es obligatorio",
									 	 null, 
									 	 AlertType.ERROR);
				display.setCurrent(alerta);

			}
		}
	}
	
	
	 private void openRecStore(String nombre){
	    try{
	    	rs[sizeRS] = RecordStore.openRecordStore(nombre, true );
	    	sizeRS++;
	    }
	    catch (Exception e){throw new RuntimeException("Error al crear el RS "+e);}
	  } 

}
