package patronMenu;

import javax.microedition.lcdui.*;

public class ModeloTextBox extends TextBox implements ModeloVisualizable {

	public ModeloTextBox() {
		super("Sample Menu Action", "Sample Content", 40, TextField.ANY);
	}

	public void incluirModelo(OpcionDeMenu modelo) {
		this.setString("You have selected menu item " + modelo.text);
		
	}

}