package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoVia;

public class DireccionImpl implements Direccion{

	private Integer id;
	private Integer version;
	private Provincia provincia;
	private String nombreVia;
	private Integer numero;
	private Integer codigoPostal;
	private String localidad;
	private TipoVia tipoVia;
	
	
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
		if (!(obj instanceof DireccionImpl))
			return false;
		final DireccionImpl other = (DireccionImpl) obj;
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
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombreVia() {
		return nombreVia;
	}
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public TipoVia getTipoVia() {
		return tipoVia;
	}
	public void setTipoVia(TipoVia tipoVia) {
		this.tipoVia = tipoVia;
	}
}
