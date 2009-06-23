package persistencia;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;


public class MIDLetPersistencia1 extends MIDlet implements CommandListener {

	private Form formHome;
	private Form formCrearRegistroDB;
	private Form formHomeRegistro;
	private Form formRegistro;
	private Command commandCrear;
	private Command commandConfirmar;
	private Command commandCancelar;
	private Command commandCrearReg;
	private Command commandEditarReg;
	private Command commandEliminarReg;
	private Command commandMenu;
	private Command commandSeleccionar;
	private Display display;
	

	public MIDLetPersistencia1() {
		display = Display.getDisplay(this);

		formHome = new Form("LISTA DBs");
		actualizarListadoRS(false, formHome);
		commandCrear = new Command("Crear RS", Command.ITEM, 1);
		commandSeleccionar = new Command("Seleccionar", Command.ITEM, 2);
		formHome.addCommand(commandCrear);
		formHome.addCommand(commandSeleccionar);
		formHome.setCommandListener(this);

		formCrearRegistroDB = new Form("CREAR RECORD STORE");
		commandConfirmar = new Command("Confirmar", Command.ITEM, 1);
		commandCancelar = new Command("Cancelar", Command.ITEM, 2);
		formCrearRegistroDB.addCommand(commandConfirmar);
		formCrearRegistroDB.addCommand(commandCancelar);
		formCrearRegistroDB.setCommandListener(this);
		
		formHomeRegistro = new Form("DB SELECCIONADA");
		commandCrearReg = new Command("Crear reg.", Command.ITEM, 1);
		commandEditarReg = new Command("Editar reg.", Command.ITEM, 2);
		commandEliminarReg = new Command("Eliminar reg.", Command.ITEM, 3);
		commandMenu = new Command("Menú Principal", Command.ITEM, 4);
		formHomeRegistro.addCommand(commandCrearReg);
		formHomeRegistro.addCommand(commandEditarReg);
		formHomeRegistro.addCommand(commandEliminarReg);
		formHomeRegistro.addCommand(commandMenu);
		formHomeRegistro.setCommandListener(this);
		
		formRegistro = new Form("Registro");
		formRegistro.addCommand(commandConfirmar);
		formRegistro.addCommand(commandCancelar);
		formRegistro.setCommandListener(this);
	}
	
	private void actualizarListadoRS(boolean limpiarFormulario, Form formulario){
		if(limpiarFormulario)
			formulario.deleteAll();
		//Creamos el listado y le anadimos las opciones
		ChoiceGroup listaRS = new ChoiceGroup("Lista DBs",List.EXCLUSIVE);
		String[] rs = RecordStore.listRecordStores();
		if(rs!=null)
			for(int i=0; i<rs.length;i++)
				listaRS.append(rs[i],null);
		formulario.append(listaRS);
	}
	
	private void actualizarListadoRegistrosRG(boolean limpiarFormulario, 
											  Form formulario, 
											  String nombreRS, 
											  String dato){
		if(limpiarFormulario)
			formulario.deleteAll();
		
		ChoiceGroup listaRegistros = new ChoiceGroup("Lista Registros",List.EXCLUSIVE);
		try {
			RecordStore rs = RecordStore.openRecordStore(nombreRS, false);//Se supone que ya está creado
			
			if(dato!=null)
				rs.addRecord(dato.getBytes(),0, dato.length());

			//TODO: Mecanismo de conversión de byte[] a Object y viceversa
//			RecordEnumeration re = rs.enumerateRecords(null, null, false);
//			System.out.println("Hay " + re.numRecords()+ " en el RecordStore");
//			while(re.hasNextElement()) {
//				byte tmp[] = re.nextRecord() ;
//				new String(tmp, 0, tmp.length);
//				listaRegistros.append(String.valueOf(tmp[0]),null);
//			}
			
			byte[] recData = new byte[5]; 
			int len;
			for (int i = 1; i <= rs.getNumRecords(); i++)      
			{
				if (rs.getRecordSize(i) > recData.length)
					recData = new byte[rs.getRecordSize(i)];

				len = rs.getRecord(i, recData, 0);
				listaRegistros.append(new String(recData, 0, len), null);
			}

		} catch (RecordStoreException e) {
		} 

		formulario.append(listaRegistros);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.out.println("Fin del Midlet\n");
	}

	protected void pauseApp() {
		System.out.println("Midlet Pausado\n");
	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Midlet Activo\n");
        display.setCurrent(formHome);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if(arg0.equals(commandCrear) && arg1.equals(formHome)){
			//Vamos al form para crear el RS
			display.setCurrent(formCrearRegistroDB);
			formCrearRegistroDB.deleteAll();

			//Creamos el TextField
			TextField textField = new TextField("Nombre RS", null, 10, TextField.ANY);
			formCrearRegistroDB.append(textField);
		}
		else if(arg0.equals(commandCancelar) && arg1.equals(formCrearRegistroDB)){
			display.setCurrent(formHome);
		}
		else if(arg0.equals(commandConfirmar) && arg1.equals(formCrearRegistroDB)){
			//Obtenemos el texto del input
			TextField textField = (TextField)formCrearRegistroDB.get(0);
			String nombre = textField.getString();
			if(nombre!=null && !nombre.equals("")){
				openRecStore(nombre);
				display.setCurrent(formHome);
				actualizarListadoRS(true, formHome);
			}else{
				Alert alerta = new Alert("ERROR",
									 	 "El nombre del RS es obligatorio",
									 	 null, 
									 	 AlertType.ERROR);
				display.setCurrent(alerta);

			}
		}
		else if(arg0.equals(commandSeleccionar) && arg1.equals(formHome)){
			ChoiceGroup listaRS = (ChoiceGroup)formHome.get(0); 
			String nombreRS = listaRS.getString(listaRS.getSelectedIndex());
			
			display.setCurrent(formHomeRegistro);
			formHomeRegistro.setTitle(nombreRS);
			actualizarListadoRegistrosRG(false, formHomeRegistro, nombreRS, null);
		}
		else if (arg0.equals(commandCrearReg) && arg1.equals(formHomeRegistro)){
			//Vamos al form para crear el RS
			display.setCurrent(formRegistro);
			formRegistro.deleteAll();

			//Creamos el TextField
			TextField textField = new TextField("Dato", null, 10, TextField.ANY);
			formRegistro.append(textField);
		}
		else if (arg0.equals(commandEditarReg) && arg1.equals(formHomeRegistro)){
			//Vamos al form para crear el RS
			display.setCurrent(formRegistro);
			formRegistro.deleteAll();

			//Obtenemos el valor actual
			ChoiceGroup listaRS = (ChoiceGroup)formHomeRegistro.get(0); 
			int indexReg = listaRS.getSelectedIndex();
			String nombreReg = formHomeRegistro.getTitle();
			RecordStore rs;
			String valorActual = "";
			try {
				rs = RecordStore.openRecordStore(nombreReg, false);
				byte[] recData = new byte[5]; 
				int len;
				if (rs.getRecordSize(indexReg) > recData.length)
						recData = new byte[rs.getRecordSize(indexReg)];

					len = rs.getRecord(indexReg, recData, 0);
					valorActual = new String(recData, 0, len);
				
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Creamos el TextField
			TextField textField = new TextField("Dato", valorActual, 10, TextField.ANY);
			formRegistro.append(textField);
		}
		else if(arg0.equals(commandMenu) && arg1.equals(formHomeRegistro)){
			display.setCurrent(formHome);
		}
		else if(arg0.equals(commandCancelar) && arg1.equals(formRegistro)){
			display.setCurrent(formHomeRegistro);
		}
		else if(arg0.equals(commandConfirmar) && arg1.equals(formRegistro)){
			//Obtenemos el texto del input
			TextField textField = (TextField)formRegistro.get(0);
			String dato = textField.getString();
			if(dato!=null && !dato.equals("")){
				display.setCurrent(formHomeRegistro);
				actualizarListadoRegistrosRG(true, formHomeRegistro, formHomeRegistro.getTitle(), dato);
			}else{
				//Dato erróneo
				Alert alerta = new Alert("ERROR",
									 	 "El dato obligatorio",
									 	 null, 
									 	 AlertType.ERROR);
				display.setCurrent(alerta);
			}
		}
	}
	
	
	 private void openRecStore(String nombre){
	    try{
	    	RecordStore.openRecordStore(nombre, true );
	    }
	    catch (Exception e){throw new RuntimeException("Error al crear el RS "+e);}
	  } 

}
