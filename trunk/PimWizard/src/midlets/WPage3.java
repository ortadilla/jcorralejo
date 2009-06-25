package midlets;

import javax.microedition.lcdui.*;
/**
 * Wizard Design Pattern.
 * Wizard dialog 3.
 *
 * @author Ben Hui
 */


public class WPage3 extends WDialog
{
	TextField question2;

	public WPage3() {
	}
	public void initDialog()
	{
		setTitle("Paso 3");

		question2 = new TextField("Introduce el n�mero de tel�fono", null, 9, TextField.ANY );
		append( question2 );
	}

	public int onLeave( int dir )
	{
		if ( dir == WDialog.FORWARD )
		{
			String answer = question2.getString();
			// 9 n�meros
			if ( answer.length() != 9 )
			{
				Alert alert = new Alert("Error en el n�mero");
				alert.setString( "Debe introducir 9 n�meros" );
				super.engine.getDisplay().setCurrent( alert, this );
				return WDialog.REJECT;
			}
			else{
				boolean ok = true;
				for(int i=0; i<answer.length();i++){
					try{
						String numero = answer.substring(i,i+1);
						Integer.parseInt(numero);
					}catch (NumberFormatException e) {
						ok = false;
						break;
					}
				}
				if(ok){
					Wizardlet.answer2 = answer;
					return WDialog.OK;
				}else{
					Alert alert = new Alert("Error en el n�mero");
					alert.setString( "Debe introducir 9 n�meros" );
					super.engine.getDisplay().setCurrent( alert, this );
					return WDialog.REJECT;
				}
			}
		}
		return OK;
	}
}