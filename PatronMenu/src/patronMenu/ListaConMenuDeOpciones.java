package patronMenu;

import javax.microedition.lcdui.*;
/**
 * Antiguo MenuList
 * @author Administrador
 *
 */
public class ListaConMenuDeOpciones extends List
{
  Display display = null; // Display instance of current midlet

  public ListaConMenuDeOpciones( Display d )
  {
    super("Untitled", List.IMPLICIT);
    this.display = d;
    addCommand( List.SELECT_COMMAND );
  }

  OpcionDeMenu opcionesDeListaModelo;
  public void pintaOpcionesDeMenu( OpcionDeMenu opcionesDeMenu )
  {
    deleteAll();
    this.opcionesDeListaModelo=opcionesDeMenu;
    display.setCurrent( this );

    for (int i=0; i< opcionesDeMenu.listaDeopciones.size(); i++ )
    {
      OpcionDeMenu item = (OpcionDeMenu) opcionesDeMenu.listaDeopciones.elementAt( i );
      append( item.text, null );
    }
    this.setTitle( opcionesDeMenu.text );
    
    setCommandListener( new ControladorListaConMenuDeOpciones() );

  }

  public Display getDisplay()
  {
    return display;
  }

  public void deleteAll()
  {
    int s = size();
    for ( int i=0; i< s; i++)
        delete(0);
  }
}
