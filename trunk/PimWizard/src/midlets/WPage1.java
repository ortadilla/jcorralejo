package midlets;


/**
 * Wizard Design Pattern.
 * Wizard dialog 1.
 *
 * @author Ben Hui
 */

public class WPage1 extends WDialog
{
  public WPage1()
  {
  }

  public void initDialog()
  {
    setTitle( "Paso 1" );
    append( "Pulsa Next para crear un nuevo contacto" );
  }
}