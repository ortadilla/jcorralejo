package dondeando.modelo.entidades;

import java.math.BigDecimal;
import java.util.Set;

public interface Local {
	
	public static final String ATRIBUTO_NOMBRE = "nombre";
	public static final String ATRIBUTO_ID = "id";
	public static final String ATRIBUTO_TIPOS_LOCAL = "tiposLocal";
	public static final String ATRIBUTO_PRECIO_MEDIO = "precioMedio";
	public static final String ATRIBUTO_PROVINCIA = "provincia";
	public static final String ATRIBUTO_DIRECCION = "direccion";
	public static final String ATRIBUTO_ACTIVO = "activo";
	public static final String ATRIBUTO_VALORACION = "valoracion";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public String getNombre();
	public void setNombre(String nombre);
	
	public Set<TipoLocal> getTiposLocal();
	public void setTiposLocal(Set<TipoLocal> tiposLocal);
	
	public Direccion getDireccion();
	public void setDireccion(Direccion direccion);
	
	public String getTelefono();
	public void setTelefono(String telefono);
	
	public String getWeb();
	public void setWeb(String web);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getHorario();
	public void setHorario(String horario);
	
	public BigDecimal getPrecioMedio();
	public void setPrecioMedio(BigDecimal precioMedio);
	
	public BigDecimal getValoracion();
	public void setValoracion(BigDecimal valoracion);
	
	public boolean isActivo();
	public void setActivo(boolean activo);
	
	public String getOtraInformacion();
	public void setOtraInformacion(String otraInformacion);
	
	public Set<Servicio> getServicios();
	public void setServicios(Set<Servicio> servicios);
	
	public Set<ImagenLocal> getImagenes();
	public void setImagenes(Set<ImagenLocal> imagenes);
	
	public Set<Opinion> getOpiniones();
	public void setOpiniones(Set<Opinion> opiniones);
	
	public Set<Puntuacion> getPuntuaciones();
	public void setPuntuaciones(Set<Puntuacion> puntuaciones);
	
}
