package midlets;

import java.util.Enumeration;

import javax.microedition.lcdui.*;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;

/**
 * Wizard Design Pattern.
 * Wizard dialog 2.
 *
 * @author Ben Hui
 */


public class WPage2b extends WDialog
{
	ChoiceGroup question1;
	public WPage2b()
	{
	}

	public void initDialog()
	{
		//Obtenemos los contactos
		try {
			ContactList contList = null;
			Enumeration contacts = null;
			PIM pimInst = PIM.getInstance();
			contList = (ContactList) pimInst.openPIMList(PIM.CONTACT_LIST, PIM.READ_ONLY);
			contacts = contList.items();

			if (contacts == null)
				return;

			question1 = new ChoiceGroup("Selecciona el contacto", ChoiceGroup.EXCLUSIVE);
			int i=0;
			while (contacts.hasMoreElements()) {
				try{
					Contact tCont = (Contact) contacts.nextElement();
					String[] nameValues = tCont.getStringArray(Contact.NAME, 0);
					String firstName = nameValues[Contact.NAME_GIVEN];
					String telefono  = tCont.getString(Contact.TEL, 0);
					question1.append(firstName, null);
					System.out.println("Elemento "+i+": "+firstName+" "+telefono);
					i++;
				}catch(IndexOutOfBoundsException e){
					//No está el nombre ni el teléfono
				}
			}

			setTitle("Paso 2");
			append( question1 );

		} catch (Exception ex) {
			return;
		}
	}

	public int onLeave( int dir )
	{
		if ( dir == WDialog.FORWARD )
		{
			int idx = question1.getSelectedIndex();
			// make sure user has selected something and set the answer a global variable
			if ( idx == -1 )
			{
				return WDialog.REJECT;
			} else
			{
				String answer = question1.getString( idx );
				Wizardlet.answer1 = answer;
				return OK;
			}
		} else
			return OK;
	}

}