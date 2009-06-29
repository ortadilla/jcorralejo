package utilidades.jsf;

import javax.faces.model.SelectItem;


/**
*
* Clase que hereda de SelectItem, permitirá la ordenación de los SelectItems que usemos
* para los CoreSelectOneChoice.<p>
* Implementa la Interfaz Comparable para poder realizar el método compareTo.<p>
* También proporciona un método para realizar dicha ordenación
*
* @author emiranda
* @author jablaya
*
*/
public class SelectItemOrdenado extends SelectItem implements Comparable{
	
	private static final long serialVersionUID = 1L;

	public int compareTo(Object objeto) {

		if(objeto == null || !(objeto instanceof SelectItemOrdenado)) return -1;
		
		SelectItemOrdenado selectItemSiglo = (SelectItemOrdenado)objeto;
		
		String label1 = selectItemSiglo.getLabel();
		String label2 = this.getLabel();
		
		int comparacion = label1.compareTo(label2);
		
		if(comparacion<0) return 1;
		else if(comparacion>0) return -1;
		else return 0;
		
	}

}
