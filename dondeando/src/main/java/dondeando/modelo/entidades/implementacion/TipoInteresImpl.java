package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.TipoInteres;

public class TipoInteresImpl implements TipoInteres{
	
	private Integer id;
	private Integer version;
	private String descripcion;
	private boolean necesitaObjeto;
	
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
		if (!(obj instanceof TipoInteresImpl))
			return false;
		final TipoInteresImpl other = (TipoInteresImpl) obj;
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
	public boolean isNecesitaObjeto() {
		return necesitaObjeto;
	}
	public void setNecesitaObjeto(boolean necesitaObjeto) {
		this.necesitaObjeto = necesitaObjeto;
	}

}
