package dondeando.modelo.entidades;

public interface Imagen {

	public static final String ATRIBUTO_NOMBRE = "nombre";

	public static final String IMAGEN_TEMPORAL = "imagenTemp";
	public static final String TAMANO_MAXIMO = "500";
    public static final int ALTURA_MINIMA = 50;
    public static final int ALTURA_MAXIMA = 1000;
    public static final int ANCHURA_MINIMA = 50;
    public static final int ANCHURA_MAXIMA = 1000;
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getNombre();
	public void setNombre(String nombre);
	
	public byte[] getContenido();
	public void setContenido(byte[] contenido);
}
