package biblioTec.modelo.entidades;

import java.util.Date;

public interface Prestamo {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	
	public Libro getLibro();
	public void setLibro(Libro libro);
	
	public Date getFechaInicio();
	public void setFechaInicio(Date fechaInicio);

	public Date getFechaFin();
	public void setFechaFin(Date fechaFin);
	
}
