package midlets;

import javax.microedition.lcdui.*;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
/**
 * Wizard Design Pattern.
 * Wizard dialog 4.
 *
 * @author Ben Hui
 */


public class WPage4 extends WDialog
{
	StringItem text;
	private ContactList contList = null;

	public WPage4()
	{
	}
	public void initDialog()
	{
		setTitle("Paso 4");
		text = new StringItem(null, null);
		append( text );
	}

	public int onEnter( int dir )
	{
		try {
			PIM pimInst = PIM.getInstance();
			contList = (ContactList) pimInst.openPIMList(PIM.CONTACT_LIST,PIM.READ_WRITE);
			Contact ct = contList.createContact();
			String[] name = new String[contList.stringArraySize(Contact.NAME)];
			name[Contact.NAME_GIVEN] = Wizardlet.answer1;
			ct.addStringArray(Contact.NAME, Contact.ATTR_NONE, name);
			ct.addString(Contact.TEL, Contact.ATTR_HOME, Wizardlet.answer2);
			ct.commit();

			if (contList != null)
				contList.close();
			contList = null;

		} catch (Exception ex) {
			return WDialog.REJECT;
		}

		text.setText( "Se ha guardado el contacto: "+Wizardlet.answer1+" con el teléfono "+Wizardlet.answer2);
		return WDialog.OK;
	}
}