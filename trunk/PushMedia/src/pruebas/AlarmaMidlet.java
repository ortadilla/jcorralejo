package pruebas;

import java.util.Date;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.PushRegistry;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class AlarmaMidlet extends MIDlet implements CommandListener {

	private Display display;
	private Form formAlarma;
	private Form formSonido;
	private TextField sonido;
	private TextField tiempo;
	private Command guardar; 

	public AlarmaMidlet() {
		display=Display.getDisplay(this);

		formAlarma = new Form ("Alarma");
		sonido = new TextField("Nombre:","",50,TextField.ANY);
		formAlarma.append(sonido);
		tiempo = new TextField("Tiempo:","",2,TextField.NUMERIC);
		formAlarma.append(tiempo);

		guardar = new Command("Aceptar",Command.OK, 1);
		formAlarma.addCommand(guardar);
		formAlarma.setCommandListener(this);
		
		formSonido = new Form("¡¡Alarma sonando!!");
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		// Obtenemos la lista de conexiones del MIDlet
	    String[] connections = javax.microedition.io.PushRegistry.listConnections(true);
	    
	    if ((connections == null) || (connections.length == 0)){
	        // El MIDlet ha sido iniciado por el usuario a través del interfaz gráfico del terminal
	    	display.setCurrent(formAlarma);
	    } else {
	        // El MIDlet ha sido iniciado por una conexión entrante o una alarma.
	    	display.setCurrent(formSonido);
	    }
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0.equals(arg0) && arg1.equals(formAlarma)) {
			boolean error = false;

			//Obtenemos la canción
			String cancion = sonido.getString();
			if(cancion!=null && cancion.startsWith("http://")){
				//TODO: Llamada http
			}else if (cancion!=null && !cancion.equals("") && !cancion.startsWith("http://")){
				//TODO: Recurso interno
			}else{
				System.out.println("Canción incorrecta");
				error = true;
			}

			//Obtenemos el tiempo de la alarma
			String segundos = tiempo.getString();
			if(segundos==null || segundos.equals("") || Integer.parseInt(segundos)<=0){
				//Error: No se ha introducido nada o es negativo
				System.out.println("Tiempo incorrecto");
				error = true;
			}

			if(error)
				return;//TODO: Añadir Alarm con aviso
			else{
				try {

					String fullClassName = this.getClass().getName();
					long nextAlarmTime = (new Date().getTime() + Integer.parseInt(segundos)*1000);
					PushRegistry.registerAlarm(fullClassName, nextAlarmTime);
					formAlarma.deleteAll();
					formAlarma.append(new StringItem("Estado","La alarma ha sido establecida correctamente." +
															  "Sonará dentro de "+nextAlarmTime+" segundos"));
				} catch (Exception e) {
					System.out.println("Error al establecer la alarma");
				}

			}


		} 
		else 
			System.out.println("Otro comando pulsado");
	}

}
