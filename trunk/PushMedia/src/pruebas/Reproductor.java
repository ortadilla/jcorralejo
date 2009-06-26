package pruebas;

import java.io.InputStream;
import java.util.Date;

import javax.microedition.io.PushRegistry;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Reproductor extends MIDlet implements CommandListener{

	private Display display;
	private Form formAlarma;
	private TextField sonido;
	//No puede funcionar un atributo del propio Midlet, ya que para que salte 
	//la alarma hay que detener el Midlet...
	private Command guardar; 

	public Reproductor() {
		display=Display.getDisplay(this);

		formAlarma = new Form ("Reproductor");
		sonido = new TextField("Nombre:","",50,TextField.ANY);
		formAlarma.append(sonido);

		guardar = new Command("Aceptar",Command.OK, 1);
		formAlarma.addCommand(guardar);
		formAlarma.setCommandListener(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(formAlarma);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0.equals(arg0) && arg1.equals(formAlarma)) {
			try{
				//Obtenemos la canción
				String cancion = sonido.getString();
				Player player;
				if(cancion!=null && cancion.startsWith("http://")){
					//Llamada http
					player = Manager.createPlayer(cancion);
				}else if (cancion!=null && !cancion.equals("") && !cancion.startsWith("http://")){
					//Recurso interno
					InputStream is = getClass().getResourceAsStream(cancion);
					player = Manager.createPlayer(is, "audio/X-wav");
				}else{
					System.out.println("Canción incorrecta");
					return;
				}

				player.start();
			}catch (Exception e) {}
		}
		else 
			System.out.println("Otro comando pulsado");
	}

}
