package dondeando.modelo.entidades;

import java.math.BigDecimal;
import java.util.Date;

public interface Puntuacion {
	
	public static final String ATRIBUTO_USUARIO = "usuario";
	public static final String ATRIBUTO_ID = "id";
	public static final String ATRIBUTO_FECHA = "fecha";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	
	public Local getLocal();
	public void setLocal(Local local);
	
	public Date getFecha();
	public void setFecha(Date fecha);
	
	public BigDecimal getComida();
	public void setComida(BigDecimal comida);
	
	public BigDecimal getAmbiente();
	public void setAmbiente(BigDecimal ambiente);
	
	public BigDecimal getServicio();
	public void setServicio(BigDecimal servicio);
	
	public BigDecimal getCalidadPrecio();
	public void setCalidadPrecio(BigDecimal calidadPrecio);
	
	public String getLoMejor();
	public void setLoMejor(String loMejor);
	
	public String getLoPeor();
	public void setLoPeor(String loPeor);
	
	public BigDecimal getKarmaUsuario();
	public void setKarmaUsuario(BigDecimal karmaUsuario);
	
	public BigDecimal getMediaCalculada();
	public void setMediaCalculada(BigDecimal mediaCalculada);
	


}
