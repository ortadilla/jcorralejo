package dondeando.modelo.entidades.implementacion;

import java.math.BigDecimal;
import java.util.Set;

import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoLocal;

public class LocalImpl implements Local{

	private Integer id;
	private Integer version;
	private String descripcion;
	private String nombre;
	private Direccion direccion;
	private String telefono;
	private String web;
	private String email;
	private String horario;
	private BigDecimal precioMedio;
	private BigDecimal valoracion;
	private String otraInformacion;
	private Set<TipoLocal> tiposLocal;
	private Set<Servicio> servicios;
	private Set<Imagen> imagenes;
	
	private String cadenaTiposLocal;
	private String imagenPrecio;
	private String shortDescPrecio;
	private String urlVerMapa;
	private String direccionHumana;
	
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
		if (!(obj instanceof LocalImpl))
			return false;
		final LocalImpl other = (LocalImpl) obj;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public BigDecimal getPrecioMedio() {
		return precioMedio;
	}
	public void setPrecioMedio(BigDecimal precioMedio) {
		this.precioMedio = precioMedio;
	}
	public BigDecimal getValoracion() {
		return valoracion;
	}
	public void setValoracion(BigDecimal valoracion) {
		this.valoracion = valoracion;
	}
	public String getOtraInformacion() {
		return otraInformacion;
	}
	public void setOtraInformacion(String otraInformacion) {
		this.otraInformacion = otraInformacion;
	}
	public Set<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
	public Set<Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	public Set<TipoLocal> getTiposLocal() {
		return tiposLocal;
	}
	public void setTiposLocal(Set<TipoLocal> tiposLocal) {
		this.tiposLocal = tiposLocal;
	}
	public String getCadenaTiposLocal() {
		return cadenaTiposLocal;
	}
	public void setCadenaTiposLocal(String cadenaTiposLocal) {
		this.cadenaTiposLocal = cadenaTiposLocal;
	}
	public String getImagenPrecio() {
		return imagenPrecio;
	}
	public void setImagenPrecio(String imagenPrecio) {
		this.imagenPrecio = imagenPrecio;
	}
	public String getShortDescPrecio() {
		return shortDescPrecio;
	}
	public void setShortDescPrecio(String shortDescPrecio) {
		this.shortDescPrecio = shortDescPrecio;
	}
	public String getUrlVerMapa() {
		return urlVerMapa;
	}
	public void setUrlVerMapa(String urlVerMapa) {
		this.urlVerMapa = urlVerMapa;
	}
	public String getDireccionHumana() {
		return direccionHumana;
	}
	public void setDireccionHumana(String direccionHumana) {
		this.direccionHumana = direccionHumana;
	}
	
	
}
