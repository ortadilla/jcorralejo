package utilidades.busquedas.consultas;

public interface Busqueda extends Condicion{
	
	
	
	String idObjetoPropiedad = ".id";
	boolean esObjeto = true;
	boolean noEsObjeto = false;

	Condicion construyeCondicion();
}
