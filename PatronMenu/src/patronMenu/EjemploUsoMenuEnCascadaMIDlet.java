package patronMenu;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;



public class EjemploUsoMenuEnCascadaMIDlet extends MIDlet {
  static EjemploUsoMenuEnCascadaMIDlet instance;
  ListaConMenuDeOpciones listaConOpciones = null;

  /** Constructor */
  public EjemploUsoMenuEnCascadaMIDlet() {
    instance = this;
  }

  /** Main method */
  public void startApp() {

    // initialize MenuList with Display object
    listaConOpciones = new ListaConMenuDeOpciones( Display.getDisplay(this) );

    // create three menus
    // use MenuElement for a menu container and
    // use MenuElement also for menu item in the container
    // link each menu item to a sample MDisplayable
    OpcionDeMenu menu3 = new OpcionDeMenu( "Attractions" );
    menu3.addOpcion( new OpcionDeMenu( "Museum"), new ModeloTextBox() );
    menu3.addOpcion( new OpcionDeMenu( "Art Galleries"), new ModeloTextBox() );
    menu3.addOpcion( new OpcionDeMenu( "Exhibitions"), new ModeloTextBox() );

    OpcionDeMenu menu2 = new OpcionDeMenu( "Restaurants" );
    menu2.addOpcion( new OpcionDeMenu( "Bakeries"), new ModeloTextBox() );
    menu2.addOpcion( new OpcionDeMenu( "Coffee Shop"), new ModeloTextBox() );
    menu2.addOpcion( new OpcionDeMenu( "Diner"), new ModeloTextBox() );

    OpcionDeMenu menu1 = new OpcionDeMenu( "Shops" );
    menu1.addOpcion( new OpcionDeMenu( "Automotives"), new ModeloTextBox() );
    menu1.addOpcion( new OpcionDeMenu( "Books"), new ModeloTextBox() );
    menu1.addOpcion( new OpcionDeMenu( "Fashions"), new ModeloTextBox() );

    // create the main menu,
    // and link the menu item to the menu containers create above
    OpcionDeMenu main = new OpcionDeMenu( "Main Menu" );
    main.addOpcion( new OpcionDeMenu( "Shops"), menu1 );
    main.addOpcion( new OpcionDeMenu( "Restaurants"), menu2 );
    main.addOpcion( new OpcionDeMenu( "Attractions"), menu3 );

    // show the main menu, this will make the main menu as the current screen
    listaConOpciones.pintaOpcionesDeMenu( main );
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
