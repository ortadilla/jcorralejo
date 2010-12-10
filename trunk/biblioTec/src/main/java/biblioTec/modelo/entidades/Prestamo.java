package biblioTec.modelo.entidades;

import java.util.Date;

public interface Prestamo {
	
	public static final String ATRIBUTO_USUARIO = "usuario";
	public static final String ATRIBUTO_LIBRO = "libro";
	public static final String ATRIBUTO_FECHA_INICIO = "fechaInicio";
	public static final String ATRIBUTO_FECHA_FIN = "fechaFin";
	
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
	
	public boolean isDevuelto();
	public void setDevuelto(boolean devuelto);
	
}
