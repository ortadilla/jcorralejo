package biblioTec.utilidades;

public class EntidadConCodigo {
	
	public static String ATRIBUTO_ID = "id";
	public static String ATRIBUTO_ETIQUETA = "etiqueta";
	public static String ATRIBUTO_VALOR = "valor";

	private Integer id;
	private String etiqueta;
	private Object valor;
	
	public EntidadConCodigo(Integer id, String etiqueta, Object valor) {
		this.id = id;
		this.etiqueta = etiqueta;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
}
