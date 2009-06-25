package midlets;

import javax.microedition.lcdui.*;

/**
 * Wizard design pattern.
 * WDialog class is the abstract class that all wizard dialog must extend from.
 * This is a MIDP Form with additional features.
 *
 * @author Ben Hui
 */

public abstract class WDialog extends Form implements CommandListener
{
  public final static int NEXT = -1;
  public final static int BACK = -2;

  // parameters for onEnter and onLeave
  public final static int FORWARD = -3;
  public final static int BACKWARD = -4;

  // return values for onEnter and onLeave
  public final static int OK = -5;
  public final static int REJECT = -6;

  // commands for Next and Back button
  public final static Command NEXT_COMMAND = new Command("NEXT", Command.OK, 1);
  public final static Command BACK_COMMAND = new Command("BACK", Command.BACK, 1);

  // user CommandListener
  CommandListener listener = null;
  WizardEngine engine = null;

  public WDialog()
  {
    super(null);
  }

  /**
   * init function invoked by WizardEngine
   * @param e the engine
   */
  public void initByEngine( WizardEngine e )
  {
    initDialog();

    // Each wizard dialog must contains Next and Back commands
    // A better solution is not to include Back command on the first dialog
    // and not to include Next command on the last dialog
    // This leave as an exercise to readers
    addCommand( NEXT_COMMAND );
    addCommand( BACK_COMMAND );

    this.engine = e;

    super.setCommandListener( this );
  }

  /**
   * This method is invoked when WizardEngine is about to show this dialog.
   * The parameter, direction, indicates whether the user pressed Next or Back command to
   * reach this screen.
   * @param dir either FORWARD or BACKWARD
   * @return OK to proceed, REJECT to deny
   */
  public int onEnter( int dir )
  {
    return OK;
  }

  /**
   * This method is invoked when WizardEngine is about to hide this dialog.
   * The parameter, direction, indicates whether the user pressed Next or Back command to
   * leave this screen.
   * @param dir either FORWARD or BACKWARD
   * @return OK to proceed, REJECT to deny
   */
  public int onLeave( int dir )
  {
    return OK;
  }

  /**
   * This method must be overriden because WDialog is a CommandListener
   * proxy. Next and Back command will be handled by this class, all other commands
   * will be fowarded to user command listener
   * @param l
   */
  public void setCommandListner( CommandListener l )
  {
    this.listener = l;
  }

  public void commandAction(Command command, Displayable displayable)
  {
    if ( command == NEXT_COMMAND || command == BACK_COMMAND )
    {
      engine.commandAction( command, displayable );
    } else if ( listener != null )
    {
      listener.commandAction( command, displayable );
    }
  }

  /**
   * Subclass to implement this method to initialize this wizard dialog.
   */
  public abstract void initDialog();

}