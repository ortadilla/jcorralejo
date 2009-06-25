package midlets;

import javax.microedition.lcdui.*;
import java.util.*;

/**
 * Wizard design pattern.
 * WizardEngine that control the flow and logic of wizard application.
 *
 * @author Ben Hui
 */

public class WizardEngine
{
  Display display = null;

  Vector dialogs = new Vector(); // a sequence of all wizard dialog

  /**
   * Constructor.
   * take Display object as parameter
   * @param d
   */
  public WizardEngine( Display d )
  {
    this.display = d;
  }

  /**
   * Add a dialog to this engine, and initialize the dialog.
   * removeDialog() should be implemented as well. This leave as an exercise
   * to readers.
   * @param dialog the dialog to add
   * @return the index of the dialog being added
   */
  public int addDialog( WDialog dialog )
  {
    dialogs.addElement( dialog );
    dialog.initByEngine( this );
    return dialogs.size() - 1;
  }

  /**
   * Start the wizard screen flow.
   * Always start with the first dialog.
   */
  public void startWizard()
  {
    if ( dialogs.size() > 0 )
    {
      WDialog dialog = (WDialog) dialogs.elementAt( 0 ); // get the first dialog in this wizard
      display.setCurrent( dialog );
    }
  }

  public Display getDisplay()
  {
    return display;
  }

  /**
   * this commandAction is only invoked by WDialog
   * This function control the flow of dialogs when user press Next or Back
   * button. This function also invoke onEnter() and onLeave() on WDialog. If a dialog
   * return WDialog.REJECT on either onEnter() or onLeave(), WizardEngine will not
   * switch screen.
   * @param command
   * @param displayable
   */
  public void commandAction(Command command, Displayable displayable)
  {
    WDialog cur_dialog = (WDialog)displayable;

    if ( command == WDialog.NEXT_COMMAND )
    {
      int i1 = dialogs.indexOf( displayable );
      if ( i1 < dialogs.size() - 1 )
      {
        WDialog next_dialog = (WDialog)dialogs.elementAt( i1 + 1 );
        if ( cur_dialog.onLeave( WDialog.FORWARD ) != WDialog.OK )
           return;
        if ( next_dialog.onEnter( WDialog.FORWARD ) != WDialog.OK )
           return;
        display.setCurrent( next_dialog );
      }

    } else if ( command == WDialog.BACK_COMMAND )
    {
      int i1 = dialogs.indexOf( displayable );
      if ( i1 > 0 )
      {
        WDialog prev_dialog = (WDialog)dialogs.elementAt( i1 - 1 );
        if ( cur_dialog.onLeave( WDialog.BACKWARD ) != WDialog.OK )
           return;
        if ( prev_dialog.onEnter( WDialog.BACKWARD ) != WDialog.OK )
           return;

        display.setCurrent( prev_dialog );
      }
    }
  }

}