package midlets;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class EditarContacto extends MIDlet {

	static EditarContacto instance;
	WizardEngine engine;
	public static String answer1;
	public static String answer2;

	public EditarContacto() {
		instance = this;
	}


	public void startApp()
	{
		// create an wizard engine
		engine = new WizardEngine( Display.getDisplay( this ) );

		// add 4 wizard dialogs to the engine
		engine.addDialog ( new WPage1b() );
		engine.addDialog ( new WPage2b() );
//		engine.addDialog ( new WPage3() );
//		engine.addDialog ( new WPage4() );

		// start the wizard
		engine.startWizard();
	}

	/** Handle pausing the MIDlet */
	public void pauseApp() {
	}

	/** Handle destroying the MIDlet */
	public void destroyApp(boolean unconditional) {
	}

	/** Quit the MIDlet */
	public static void quitApp() {
		instance.destroyApp(true);
		instance.notifyDestroyed();
		instance = null;
	}


}
