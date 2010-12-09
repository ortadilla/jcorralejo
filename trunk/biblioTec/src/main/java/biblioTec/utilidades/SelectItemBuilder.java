package biblioTec.utilidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;


public class SelectItemBuilder {


	@SuppressWarnings("unchecked")
	public static SelectItem[] creaSelectItems(Collection coleccion,String propiedadValor,String propiedadEtiqueta, boolean conItemVacio) {
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
			item.setValue(propiedadValor!=null ? getProperty(objeto,propiedadValor) : objeto);
			items[i++] = item;	
		}

		return items;
	}

	private static Object getProperty(Object obj, String propiedad){
		try {
			return obj.getClass().getMethod(getGetter(propiedad)).invoke(obj);
		} catch (Exception e) {
			throw new IllegalArgumentException("No existe la propiedad \""+propiedad+"\" en "+obj.getClass().getName(), e);
		}
	}

	public static SelectItem[] creaSelectItemsSiNo(){
		return creaSelectItems(devolverValoresSiNo(), 
							   EntidadConCodigo.ATRIBUTO_VALOR, 
							   EntidadConCodigo.ATRIBUTO_ETIQUETA,
							   true);
	}

	private static List<EntidadConCodigo> devolverValoresSiNo(){
		List<EntidadConCodigo> res = new ArrayList<EntidadConCodigo>();
		res.add(new EntidadConCodigo(1,"Sí", Boolean.TRUE));
		res.add(new EntidadConCodigo(2,"No", Boolean.FALSE));
		return res;
	}

	private static String getGetter(String propiedad) {
		if(propiedad==null) return null;
		String p = propiedad.trim();
		return "get" + (p.length()>0 ? p.substring(0,1).toUpperCase()+p.substring(1) : "");
	}	
}
