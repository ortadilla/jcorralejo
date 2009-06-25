package midlets;

import javax.microedition.lcdui.*;

/**
 * Wizard Design Pattern.
 * Wizard dialog 2.
 *
 * @author Ben Hui
 */


public class WPage2 extends WDialog
{
	TextField question1;
	public WPage2()
	{
	}



	public void initDialog()
	{
		setTitle("Paso 2");

		question1 = new TextField("Introduce el nombre", null, 40, TextField.ANY );
		append( question1 );  
	}

	public int onLeave( int dir )
	{
		 if ( dir == WDialog.FORWARD )
		    {
		      String answer = question1.getString();
		      // make sure user has entered 3 characters, and set the answer to a global variable
		      if ( answer.length() < 3 )
		      {
		        // show an alert screen
		        Alert alert = new Alert("Error en el nombre");
		        alert.setString( "El nombre debe contener más de 3 caracteres" );
		        super.engine.getDisplay().setCurrent( alert, this );
		        return WDialog.REJECT;
		      } else
		      {
		        Wizardlet.answer1 = answer;
		        return WDialog.OK;
		      }
		    } else
		      return OK;
	}

}