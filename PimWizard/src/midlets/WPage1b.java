package midlets;


/**
 * Wizard Design Pattern.
 * Wizard dialog 1.
 *
 * @author Ben Hui
 */

public class WPage1b extends WDialog
{
  public WPage1b()
  {
  }

  public void initDialog()
  {
    setTitle( "Paso 1" );
    append( "Pulsa Next para editar un nuevo contacto" );
  }
}