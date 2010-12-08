package biblioTec.modelo.entidades;

public interface Usuario {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getNombre();
	public void setNombre(String nombre);

	public String getLogin();
	public void setLogin(String login);
	
	public String getPass();
	public void setPass(String pass);
	
}
