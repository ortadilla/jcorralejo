package utilidades.busquedas.consultas;



public interface Condicion extends DatosBusqueda{

    public void agregar(Criterio criterio);
    
    public Criterio[] getCriterios();
    
}
