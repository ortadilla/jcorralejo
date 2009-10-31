package dondeando.modelo.entidades;

public interface Interes {

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
