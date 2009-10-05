package dondeando.modelo.entidades;

public interface Direccion {
	
	public static final String ATRIBUTO_PROVINCIA = "provincia";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Provincia getProvincia();
	public void setProvincia(Provincia provincia);
	
	public String getNombreVia();
	public void setNombreVia(String nombreVia);
	
	public Integer getNumero();
	public void setNumero(Integer numero);
	
	public Integer getCodigoPostal();
	public void setCodigoPostal(Integer codigoPostal);
	
	public String getLocalidad();
	public void setLocalidad(String localidad);
	
	public TipoVia getTipoVia();
	public void setTipoVia(TipoVia tipoVia);

}
