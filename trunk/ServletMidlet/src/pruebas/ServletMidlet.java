package pruebas;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class ServletMidlet extends MIDlet {

	private Display display;
	String url = "http://localhost:8080/ServletEjemplo/ServletEjemplo?id=Ejemplo";


	public ServletMidlet() {
		display = Display.getDisplay(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		try {
			HttpConnection c = null;
			InputStream is = null;
			StringBuffer b = new StringBuffer();
			TextBox t = null;
			try {
				c = (HttpConnection)Connector.open(url);
				c.setRequestMethod(HttpConnection.GET);
				c.setRequestProperty("User-Agent","Profile/MIDP-1.0 Confirguration/CLDC-1.0");
				c.setRequestProperty("Content-Language", "en-CA");
				is = c.openDataInputStream();
				int ch;
				while ((ch = is.read()) != -1) {
					b.append((char) ch);
				}
				t = new TextBox("First Servlet", b.toString(), 1024, 0);
			} finally {
				if(is!= null) {
					is.close();
				}
				if(c != null) {
					c.close();
				}
			}
			display.setCurrent(t);
		} catch (IOException e) {
			System.out.println("IOException " + e);
			e.printStackTrace();
		}
	}

}
