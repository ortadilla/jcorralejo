package patronMenu;

import javax.microedition.lcdui.*;
import java.util.*;
/**
 * antiguo MenuElement
 * @author Administrador
 *
 */
public class OpcionDeMenu {

	public Vector listaDeopciones = new Vector();
	Hashtable navegacionMap = new Hashtable();

	public String text; // menu label or title

	public OpcionDeMenu(String s) {
		text = s;
	}

	public void addOpcion(OpcionDeMenu child,
			OpcionDeMenu next_container) {
		listaDeopciones.addElement(child);
		navegacionMap.put(child, next_container);
	}

	public void addOpcion(OpcionDeMenu child, Displayable display) {
		listaDeopciones.addElement(child);
		navegacionMap.put(child, display);

	}

}
