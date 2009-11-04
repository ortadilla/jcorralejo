package dondeando.modelo.entidades;

public interface Interes {
	
	public final static String ATRIBUTO_TIPO_INTERES = "tipoInteres";
	public final static String ATRIBUTO_USUARIO = "usuario";
	public final static String ATRIBUTO_OBJETO_INTERES = "objetoInteres";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	
	public TipoInteres getTipoInteres();
	public void setTipoInteres(TipoInteres tipoInteres);
	
	public Integer getObjetoInteres();
	public void setObjetoInteres(Integer objetoInteres);
	
	public boolean isEnviarEmail();
	public void setEnviarEmail(boolean enviarEmail);
}
