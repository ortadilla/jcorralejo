package dondeando.modelo.entidades.implementacion;

import java.util.Date;
import java.util.Set;

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
	private Set<MensajeForo> respuestas;
	
	private String autorYFecha;
	private int numeroRespuestas;
	private Date fechaUltimaRespuesta;
	private String autorYFechaUltimaRespuesta;
	
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
	public String getAutorYFecha() {
		return autorYFecha;
	}
	public void setAutorYFecha(String autorYFecha) {
		this.autorYFecha = autorYFecha;
	}
	public Date getFechaUltimaRespuesta() {
		return fechaUltimaRespuesta;
	}
	public void setFechaUltimaRespuesta(Date fechaUltimaRespuesta) {
		this.fechaUltimaRespuesta = fechaUltimaRespuesta;
	}
	public String getAutorYFechaUltimaRespuesta() {
		return autorYFechaUltimaRespuesta;
	}
	public void setAutorYFechaUltimaRespuesta(String autorYFechaUltimaRespuesta) {
		this.autorYFechaUltimaRespuesta = autorYFechaUltimaRespuesta;
	}
	public Set<MensajeForo> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(Set<MensajeForo> respuestas) {
		this.respuestas = respuestas;
	}
	public int getNumeroRespuestas() {
		return numeroRespuestas;
	}
	public void setNumeroRespuestas(int numeroRespuestas) {
		this.numeroRespuestas = numeroRespuestas;
	}
	
	

}
