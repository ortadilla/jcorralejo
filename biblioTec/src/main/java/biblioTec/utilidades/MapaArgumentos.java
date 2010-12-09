package biblioTec.utilidades;


import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;

import java.util.*;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name(MAPA_ARGUMENTOS)
@Scope(ScopeType.EVENT)
public class MapaArgumentos{
	
	private Map<Object, Object> mapa = new HashMap<Object, Object>();
	
	
    public MapaArgumentos() {}

    public boolean contiene(String argumento){
        return mapa.containsKey(argumento);
    }

    public void limpiaMapa(){
		mapa.clear();
	}
	
	public void setArgumento(Object nombreArgumento, Object valorArgumento){
		if (nombreArgumento == null)
			throw new IllegalArgumentException("Se está intentado insertar un argumento con nombre nulo");
		mapa.put(nombreArgumento, valorArgumento);
	}
	
	public Object getArgumento (Object nombreArgumento){
		if (nombreArgumento == null)
			return null;
		return mapa.get(nombreArgumento);
	}
	
	public Set<Object> getClaves(){
	    return mapa.keySet();
	}

	public Collection<Object> getValores(){
	    return mapa.values();
	}

	public Object borrar(Object argumento){
	    return mapa.remove(argumento);
	}

}
