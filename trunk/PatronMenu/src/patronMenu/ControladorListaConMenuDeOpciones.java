package patronMenu;

import javax.microedition.lcdui.*;

import java.util.*;

/**
 * rol de controlador 
 * separado del modelo MenuElement antiguo o OpcionDeMenu actual
 * @author Administrador
 *
 */
public class ControladorListaConMenuDeOpciones implements CommandListener
{


  public void commandAction(Command command, Displayable displayable)
  {
    if ( command.equals( List.SELECT_COMMAND ) && displayable instanceof ListaConMenuDeOpciones )
    {
      ListaConMenuDeOpciones list = (ListaConMenuDeOpciones) displayable;
      int i = list.getSelectedIndex();
      OpcionDeMenu item = (OpcionDeMenu) list.opcionesDeListaModelo.listaDeopciones.elementAt( i );
      Object opcionSeleccionada = list.opcionesDeListaModelo.navegacionMap.get( item );

      if ( opcionSeleccionada instanceof OpcionDeMenu )
      {
        list.pintaOpcionesDeMenu( (OpcionDeMenu) opcionSeleccionada );

      } else if ( opcionSeleccionada instanceof ModeloVisualizable && opcionSeleccionada instanceof Displayable )
      {
        
    	ModeloVisualizable modeloVisualizable = (ModeloVisualizable)opcionSeleccionada;

    	modeloVisualizable.incluirModelo( item );

        list.getDisplay().setCurrent( (Displayable) modeloVisualizable );

      } else if ( opcionSeleccionada instanceof Displayable )
      {
        list.getDisplay().setCurrent( (Displayable) opcionSeleccionada );
      }
    }
    
    else if (displayable instanceof ListaConMenuDeOpciones 
    	 && ((ListaConMenuDeOpciones)displayable).commandAtras.equals(command)){
    	System.out.println("Pulsado botón atrás...");

    	ListaConMenuDeOpciones list = (ListaConMenuDeOpciones) displayable;
    	int i = list.getSelectedIndex();
    	OpcionDeMenu item = (OpcionDeMenu) list.opcionesDeListaModelo.listaDeopciones.elementAt( i );
    	
    	if(item.opcionAnterior!=null && item.opcionAnterior.opcionAnterior!=null)
    		list.pintaOpcionesDeMenu( (OpcionDeMenu) item.opcionAnterior.opcionAnterior.opcionAnterior);
    }
  }
}
