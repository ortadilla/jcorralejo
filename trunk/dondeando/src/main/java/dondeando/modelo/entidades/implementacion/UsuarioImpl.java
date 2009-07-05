package dondeando.modelo.entidades.implementacion;

import java.math.BigDecimal;

import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;

public class UsuarioImpl implements Usuario{

	private Integer id;
	private Integer version;
	private String login;
	private String password;
	private String nombre;
	private String apellidos;
	private boolean activo;
	private String direccion;
	private String email;
	private BigDecimal karma;
	private TipoUsuario tipoUsuario;
	private Imagen avatar;
	
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
	
	public String getNombreCompleto() {
        String result = "";
        if(apellidos!=null)                      result += apellidos;
        if(nombre!=null && result.length()>0)    result += ", ";
        if(nombre!=null)                         result += nombre;
		return result;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getKarma() {
		return karma;
	}
	public void setKarma(BigDecimal karma) {
		this.karma = karma;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Imagen getAvatar() {
		return avatar;
	}
	public void setAvatar(Imagen avatar) {
		this.avatar = avatar;
	}
	
}
