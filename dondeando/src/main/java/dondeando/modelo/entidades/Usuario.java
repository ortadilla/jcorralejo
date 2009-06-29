package dondeando.modelo.entidades;

import java.math.BigDecimal;
import java.util.Date;

public interface Usuario {
	
	public static String USUARIO_ANONIMO = "anonimo";
	public static final String ATRIBUTO_LOGIN = "login";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getLogin();
	public void setLogin(String login);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getNombre();
	public void setNombre(String nombre);
	
	public String getApellidos();
	public void setApellidos(String apellidos);
	
	public boolean isActivo();
	public void setActivo(boolean activo);
	
	public String getDireccion();
	public void setDireccion(String direccion);

	public String getEmail();
	public void setEmail(String email);
	
	public BigDecimal getKarma();
	public void setKarma(BigDecimal karma);
	
	public TipoUsuario getTipoUsuario();
	public void setTipoUsuario(TipoUsuario tipoUsuario);
	
	public Date getFechaModificacion();
	public void setFechaModificacion(Date fechaMoficacion);
	
	public Usuario getUsuarioModificacion();
	public void setUsuarioModificacion(Usuario usuarioModificacion);
	
	public String getNombreCompleto();
}
