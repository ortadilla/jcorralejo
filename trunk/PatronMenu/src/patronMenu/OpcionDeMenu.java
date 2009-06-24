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
	public OpcionDeMenu opcionAnterior;

	public OpcionDeMenu(String s) {
		text = s;
	}

	public void addOpcion(OpcionDeMenu child,
						  OpcionDeMenu next_container) {
		listaDeopciones.addElement(child);
		navegacionMap.put(child, next_container);
//		child.opcionAnterior = this;
//		next_container.opcionAnterior = child;
		next_container.opcionAnterior = this;
	}

	public void addOpcion(OpcionDeMenu child, Displayable display) {
		listaDeopciones.addElement(child);
		navegacionMap.put(child, display);
		child.opcionAnterior = this;
	}

}
