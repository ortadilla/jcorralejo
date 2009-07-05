package dondeando.modelo.entidades.implementacion;

import java.math.BigDecimal;
import java.util.Set;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoLocal;

public class LocalImpl implements Local{

	private Integer id;
	private Integer version;
	private String descripcion;
	private String nombre;
	private TipoLocal tipoLocal;
	private String direccion;
	private String telefono;
	private String web;
	private String email;
	private String horario;
	private String precioMedio;
	private BigDecimal valoracion;
	private String otraInformacion;
	private Set<Servicio> servicios;
	
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
	public TipoLocal getTipoLocal() {
		return tipoLocal;
	}
	public void setTipoLocal(TipoLocal tipoLocal) {
		this.tipoLocal = tipoLocal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
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
	public String getPrecioMedio() {
		return precioMedio;
	}
	public void setPrecioMedio(String precioMedio) {
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
	
	
}
