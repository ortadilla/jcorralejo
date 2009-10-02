package utilidades.varios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;


/**
* Clase builder para SelectItems. 
* <p>
* Se utiliza para transformar coleciones de objetos en 
* array's de SelectItems.
* 
* <p>
*/
public class SelectItemBuilder {

	public static final String LEYENDA_INACTIVOS = "(#)";
	public static final String CODIGO_INACTIVOS = "#";

	/**
	 * M�todo que a partir de una lista de objetos, crea un array de selectItem. Cada uno de los distintos SelectItem tomar� el valor y la etiqueta
	 * de las propiedades del objeto que se le pasan como par�metro. Se supondr� cierto que dichas propiedades no existiesen se elevar� una
	 * excepci�n InvalidParameterException indicando el error ocurrido.
	 * Los elementos del array devuelto, no siguen ning�n tipo de ordenaci�n.
	 * 
	 * @param Collection colecci�n de objetos
	 * @param String propiedad valor
	 * @param String propiedad etiqueta
	 * @return SelectItem[] array de selectItem
	 */
	public static SelectItem[] creaSelectItems(Collection coleccion,String propiedadValor,String propiedadEtiqueta) {
		return creacionArraySelectItem(coleccion, null, propiedadValor, propiedadEtiqueta,false, false);
	}
    
	public static SelectItem[] creaSelectItems(Collection coleccion,String propiedadValor,String propiedadEtiqueta, boolean conItemVacio) {
        return creacionArraySelectItem(coleccion, null, propiedadValor, propiedadEtiqueta,false, conItemVacio);
    }
	
	/**
	 * M�todo que a partir de una lista de objetos, crea un array de selectItem. Cada uno de los distintos SelectItem tomar� el valor y la etiqueta
	 * de las propiedades del objeto que se le pasan como par�metro. En caso de que el elemento no se encuentre en la lista coleccionSeleccionable
	 * (Siempre y cuando esta no sea nula) el item ser� desactivado. Se supondr� cierto que dichas propiedades no existiesen se elevar� una
	 * excepci�n InvalidParameterException indicando el error ocurrido.
	 * Los elementos del array devuelto, no siguen ning�n tipo de ordenaci�n.
	 * 
	 * @param Collection colecci�n de objetos
	 * @param Collection Subconjunto de la colecci�n anterior que deben estar activos
	 * @param String propiedad valor
	 * @param String propiedad etiqueta
	 * @return SelectItem[] array de selectItem
	 */
	public static SelectItem[] creaSelectItems(Collection coleccion, Collection coleccionSeleccionable,String propiedadValor,String propiedadEtiqueta) {	
		return creacionArraySelectItem(coleccion, coleccionSeleccionable, propiedadValor, propiedadEtiqueta,false, false);
	}
	
	public static SelectItem[] creaSelectItemMarcandoDesactivados(Collection coleccion, Collection coleccionSeleccionable,String propiedadValor,String propiedadEtiqueta, boolean soloMarcar, boolean conItemVacio){
		return creacionArraySelectItem(coleccion, coleccionSeleccionable, propiedadValor, propiedadEtiqueta, soloMarcar, conItemVacio);
	}
	
	/**
	 * Crea el array de selectItem
	 * @param coleccion        			Coleci�n de objetos a partir de la cual se crea el array de selectItem
	 * @param coleccionSeleccionable	Colecci�n de objetos que deben aparecer activos en la lista de items
	 * @param propiedadValor   			Propiedad de los objetos para el valor del selectItem. Si es NULL, se usa el objeto entero como valor.
	 * @param String propiedad 			Propiedad para la etiqueta del selectItem. No puede ser NULL.
	 * @param boolean soloMarcar		Propiedad para determinar si los elemento no seleccionables se deben deshabilitar o s�lo marcar
     * @param boolean conItemVacio      Propiedad para determinar si debemos crear un item vac�o al principio del array.
	 */
	private static SelectItem[] creacionArraySelectItem(Collection coleccion, Collection coleccionSeleccionable, String propiedadValor, String propiedadEtiqueta, boolean soloMarcar, boolean conItemVacio){
		
		if (coleccion == null || coleccion.size()==0) return new SelectItem[0];
		
        int numItems = coleccion.size() + (conItemVacio ?1:0);
        
		SelectItem[] items = new SelectItem[numItems];
		
		int i = 0;
        if(conItemVacio){
            SelectItem itemVacio = new SelectItem();
            itemVacio.setLabel("");
            items[0] = itemVacio;
            i = 1;
        }
        
		for (Object objeto : coleccion) {
			SelectItem item = new SelectItem();
            Object label;
            if(propiedadEtiqueta!=null)
                label = getProperty(objeto,propiedadEtiqueta);
            else
                label = objeto.toString();
			item.setLabel(label!=null ? label.toString() : "");
			item.setDescription(label!=null ? label.toString() : "");
			item.setValue(propiedadValor!=null ? getProperty(objeto,propiedadValor) 
					                           : objeto);
			if(coleccionSeleccionable!=null && !coleccionSeleccionable.contains(objeto)){
				if(soloMarcar){
					item.setLabel(item.getLabel() + CODIGO_INACTIVOS);
				}else
					item.setDisabled(true);
			}
			items[i++] = item;	
		}
		
		return items;
	}
	
	
	/**
	 * Devuelve el valor de una propiedad
	 * @param objeto
	 * @param propiedadValue
	 * @return
	 * @throws IllegalArgumentException Si falla la invocaci�n del getter
	 */
	private static Object getProperty(Object obj, String propiedad){
        try {
    		return obj.getClass().getMethod(HerramientasString.getGetter(propiedad))
    		                     .invoke(obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("No existe la propiedad \""+propiedad+"\" en "+obj.getClass().getName(), e);
        }
	}
	
	/**
	 * Devuelve un selectItem con los valores Vacio, Si y No
	 */
	public static SelectItem[] creaSelectItemsSiNo(){
		return creaSelectItems(devolverValoresSiNo(), 
						       EntidadConCodigo.ATRIBUTO_VALOR, 
						       EntidadConCodigo.ATRIBUTO_ETIQUETA,
						       true);
	}
	
    /**
     * Devuelve una lista de Entidades con los valores S� y No
     * @param hueco Si se debe a�adir un hueco a la lista
     * @return Lista con los valores S� y No
     */
    private static List<EntidadConCodigo> devolverValoresSiNo(){
    	List<EntidadConCodigo> res = new ArrayList<EntidadConCodigo>();
    	res.add(new EntidadConCodigo(1,"S�", Boolean.TRUE));
    	res.add(new EntidadConCodigo(2,"No", Boolean.FALSE));
    	return res;
    }
	
	
}
