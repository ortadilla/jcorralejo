package dondeando.modelo.entidades.implementacion;

import java.math.BigDecimal;
import java.util.Date;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.Usuario;

public class PuntuacionImpl implements Puntuacion{
	
	private Integer id;
	private Integer version;
	private Usuario usuario;
	private Date fecha;
	private Local local;
	private BigDecimal comida;
	private BigDecimal servicio;
	private BigDecimal ambiente;
	private BigDecimal calidadPrecio;
	private String loMejor;
	private String loPeor;
	private BigDecimal mediaCalculada;  
	private BigDecimal karmaUsuario;  
	
	private String autorYFecha;
	
	public String getDescripcion(){
		return local.getNombre()+": "+mediaCalculada+" ("+usuario.getLogin()+")";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof PuntuacionImpl))
			return false;
		final PuntuacionImpl other = (PuntuacionImpl) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public BigDecimal getComida() {
		return comida;
	}

	public void setComida(BigDecimal comida) {
		this.comida = comida;
	}

	public BigDecimal getServicio() {
		return servicio;
	}

	public void setServicio(BigDecimal servicio) {
		this.servicio = servicio;
	}

	public BigDecimal getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(BigDecimal ambiente) {
		this.ambiente = ambiente;
	}

	public BigDecimal getCalidadPrecio() {
		return calidadPrecio;
	}

	public void setCalidadPrecio(BigDecimal calidadPrecio) {
		this.calidadPrecio = calidadPrecio;
	}

	public String getLoMejor() {
		return loMejor;
	}

	public void setLoMejor(String loMejor) {
		this.loMejor = loMejor;
	}

	public String getLoPeor() {
		return loPeor;
	}

	public void setLoPeor(String loPeor) {
		this.loPeor = loPeor;
	}

	public String getAutorYFecha() {
		return autorYFecha;
	}

	public void setAutorYFecha(String autorYFecha) {
		this.autorYFecha = autorYFecha;
	}

	public BigDecimal getMediaCalculada() {
		return mediaCalculada;
	}

	public void setMediaCalculada(BigDecimal mediaCalculada) {
		this.mediaCalculada = mediaCalculada;
	}

	public BigDecimal getKarmaUsuario() {
		return karmaUsuario;
	}

	public void setKarmaUsuario(BigDecimal karmaUsuario) {
		this.karmaUsuario = karmaUsuario;
	}

}
