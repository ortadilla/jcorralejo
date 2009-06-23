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
		//Añadir al formHome el lista de RecordStore
		actualizarListadoRS();
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
	
	private void actualizarListadoRS(){
		//Creamos el listado y le anadimos las opciones
		ChoiceGroup listaRS = new ChoiceGroup("Lista DBs",List.EXCLUSIVE);
		String[] rs = RecordStore.listRecordStores();
		if(rs!=null)
			for(int i=0; i<rs.length;i++)
				listaRS.append(rs[i],null);
		formHome.append(listaRS);
	}
	
	private void actualizarListadoRegistrosRG(String nombreRS){
		ChoiceGroup listaRegistros = new ChoiceGroup("Lista Registros",List.EXCLUSIVE);
		try {
			RecordStore rs = RecordStore.openRecordStore(nombreRS, false);//Se supone que ya está creado
			RecordEnumeration re = rs.enumerateRecords(null, null, false);
			System.out.println("Hay " + re.numRecords()+ " en el RecordStore");
			while(re.hasNextElement()) {
				byte tmp[] = re.nextRecord() ;
				System.out.println(tmp[0] + " " + tmp[1]) ;
				listaRegistros.append(String.valueOf(tmp[0]),null);
			}
		} catch (RecordStoreException e) {
		} 

		formHomeRegistro.append(listaRegistros);
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
				formHome.deleteAll();
				actualizarListadoRS();
			}else{
				//TODO: Nombre erróneo
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
			formCrearRegistroDB.setTitle(nombreRS);
			actualizarListadoRegistrosRG(nombreRS);
		}
		else if (arg0.equals(commandCrearReg) && arg1.equals(formHomeRegistro)){
			//Vamos al form para crear el RS
			display.setCurrent(formRegistro);
			formRegistro.deleteAll();

			//Creamos el TextField
			TextField textField = new TextField("Dato", null, 10, TextField.ANY);
			formRegistro.append(textField);
		}
		else if(arg0.equals(commandCancelar) && arg1.equals(formHomeRegistro)){
			display.setCurrent(formHomeRegistro);
		}
		else if(arg0.equals(commandConfirmar) && arg1.equals(formHomeRegistro)){
			//Obtenemos el texto del input
			TextField textField = (TextField)formHomeRegistro.get(0);
			String dato = textField.getString();
			if(dato!=null && !dato.equals("")){
				display.setCurrent(formHomeRegistro);
				formHomeRegistro.deleteAll();
				actualizarListadoRegistrosRG(formCrearRegistroDB.getTitle());
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
