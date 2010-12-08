package biblioTec.modelo.entidades;

import java.util.Set;

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
	
	public Set<Prestamo> getPrestamos();
	public void setPrestamos(Set<Prestamo> prestamos);
	
	public Set<Perfil> getPerfiles();
	public void setPerfiles(Set<Perfil> perfiles);
}
