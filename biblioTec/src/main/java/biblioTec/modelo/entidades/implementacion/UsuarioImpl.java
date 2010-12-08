package biblioTec.modelo.entidades.implementacion;

import biblioTec.modelo.entidades.Usuario;


public class UsuarioImpl implements Usuario {
	
	private Integer id;
	private Integer version;
	private String nombre;
	private String login;
	private String pass;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if ( !(obj instanceof UsuarioImpl) ) return false;
		
		final UsuarioImpl other = (UsuarioImpl) obj;
		if (login == null) {
			if (other.getLogin() != null) return false;
		} else if (!login.equals(other.getLogin()))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}
