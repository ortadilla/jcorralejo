package dondeando.modelo.entidades;

import java.math.BigDecimal;
import java.util.Set;

public interface Usuario {
	
	public static String USUARIO_ANONIMO = "anonimo";
	
	public static final String ATRIBUTO_LOGIN = "login";
	public static final String ATRIBUTO_ACTIVO = "activo";
	public static final String ATRIBUTO_TIPO_USUARIO = "tipoUsuario";
	
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
	
	public Imagen getAvatar();
	public void setAvatar(Imagen imagen);
	
	public Set<Opinion> getOpiniones();
	public void setOpiniones(Set<Opinion> opiniones);
	
	public Set<Puntuacion> getPuntuaciones();
	public void setPuntuaciones(Set<Puntuacion> puntuaciones);
	
	public String getNombreCompleto();
}
