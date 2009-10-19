package dondeando.modelo.entidades.implementacion;

import java.util.Date;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;

public class MensajeForoImpl implements MensajeForo {
	
	private Integer id;
	private Integer version;
	private String titulo;
	private String mensaje;
	private Usuario autor;
	private Date fecha;
	private MensajeForo respondeA;
	private Foro foro;
	
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
		if (!(obj instanceof MensajeForoImpl))
			return false;
		final MensajeForoImpl other = (MensajeForoImpl) obj;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public MensajeForo getRespondeA() {
		return respondeA;
	}
	public void setRespondeA(MensajeForo respondeA) {
		this.respondeA = respondeA;
	}
	public Foro getForo() {
		return foro;
	}
	public void setForo(Foro foro) {
		this.foro = foro;
	}
	
	

}
