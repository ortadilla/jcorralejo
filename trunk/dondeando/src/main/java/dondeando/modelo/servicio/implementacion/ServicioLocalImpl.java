package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.LOCAL_DAO;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_DIRECCION;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.EntidadConCodigo;
import utilidades.varios.MensajesCore;
import dondeando.modelo.dao.LocalDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.entidades.implementacion.LocalImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioDireccion;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_LOCAL)
public class ServicioLocalImpl implements ServicioLocal{
	
	public static final String BASE_URL_IR_MAPA = "http://maps.google.es/maps?f=q&hl=es&geocode=&q=";
	
    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
    
    @In(value=SERVICIO_DIRECCION, create=true)
    private ServicioDireccion servicioDireccion;
    
    @In(value=SERVICIO_TIPO_LOCAL, create=true)
    private ServicioTipoLocal servicioTipoLocal;
    
    @In(value=LOCAL_DAO, create=true)
    private LocalDAO localDAO;
    
    @In(value=MENSAJES_CORE, create=true)
    private MensajesCore mensajesCore;
    
    private Log log = LogFactory.getLog(ServicioLocalImpl.class);
    

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioLocal#encontrarLocalesPorNombreTipoProvinciaYPrecio(java.lang.String, java.util.List, dondeando.modelo.entidades.Provincia, java.lang.String, java.lang.Boolean)
     */
	public List<Local> encontrarLocalesPorNombreTipoProvinciaYPrecio(String nombre, List<Integer> tiposLocal, Provincia provincia, String precio, Boolean activo){
		List<Criterio> criterios = new ArrayList<Criterio>();
		
		if(nombre!=null && !"".equals(nombre))
			criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_NOMBRE, Criterio.LIKE, nombre));
		
		if(tiposLocal!=null && !tiposLocal.isEmpty())
			criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_TIPOS_LOCAL, 
															  Criterio.COLECCION, 
															  servicioCriterios.construyeCriterio(TipoLocal.ATRIBUTO_ID, 
																	  							  Criterio.EN, 
																	  							  tiposLocal)));
		if(provincia!=null)
			criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_DIRECCION, 
															  Criterio.COLECCION, 
															  servicioCriterios.construyeCriterio(Direccion.ATRIBUTO_PROVINCIA, 
																	  							  Criterio.IGUAL, 
																	  							  provincia)));
		if(precio!=null && !"".equals(precio)){
			if(RANGO_PRECIO_MENOR_10.equals(precio))
				criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MENOR_IGUAL_QUE, new BigDecimal(10)));
			else if(RANGO_PRECIO_10_30.equals(precio))
				criterios.add(servicioCriterios.construyeCriterioAND(
						servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MAYOR_QUE, new BigDecimal(10)),
						servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MENOR_IGUAL_QUE, new BigDecimal(30))));
			else if(RANGO_PRECIO_30_50.equals(precio))
				criterios.add(servicioCriterios.construyeCriterioAND(
						servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MAYOR_QUE, new BigDecimal(30)),
						servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MENOR_IGUAL_QUE, new BigDecimal(50))));
			else if(RANGO_PRECIO_MAYOR_50.equals(precio))
				criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_PRECIO_MEDIO, Criterio.MAYOR_QUE, new BigDecimal(50)));
		}
    	if(activo!=null)
    		criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_ACTIVO, Criterio.IGUAL, activo));

		
		return localDAO.encontrarPorCondicion(criterios);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
	public void rellenarPropiedadesNoMapeadas(List<Local> locales){
		for(Local local: locales){
			LocalImpl localImpl = (LocalImpl)local;
			
			//Tipos local
			String tiposLocal = "";
			for(TipoLocal tipoLocal : local.getTiposLocal())
				tiposLocal += tipoLocal.getDescripcion() + " / ";
			localImpl.setCadenaTiposLocal(tiposLocal.substring(0, tiposLocal.length()-2));
			
			//Imagen y desc. para el precio
			BigDecimal precio = localImpl.getPrecioMedio();
			String imagenPrecio = "";
			String shortDescPrecio = "";
			String rangoPrecio = obtenerRangoPrecioDePrecio(precio);
			if(RANGO_PRECIO_MENOR_10.equals(rangoPrecio)){
				imagenPrecio = "/imagenes/dondeando/precio1.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_MENOR_10);
			}
			else if(RANGO_PRECIO_10_30.equals(rangoPrecio)){
				imagenPrecio = "/imagenes/dondeando/precio2.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_10_30);
			}
			else if(RANGO_PRECIO_30_50.equals(rangoPrecio)){
				imagenPrecio = "/imagenes/dondeando/precio3.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_30_50);
			}
			else if(RANGO_PRECIO_MAYOR_50.equals(rangoPrecio)){
				imagenPrecio = "/imagenes/dondeando/precio4.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_MAYOR_50);
			}
			localImpl.setImagenPrecio(imagenPrecio);
			localImpl.setShortDescPrecio(shortDescPrecio);
			
			//Dirección en modo "humano"
			localImpl.setDireccionHumana(local.getDireccion().getTipoVia().getDescripcion() + " "
									   + local.getDireccion().getNombreVia()+", "
									   + (local.getDireccion().getNumero()!=null && !"".equals(local.getDireccion().getNumero()) 
									   ? local.getDireccion().getNumero()+ " " : "")
									   + local.getDireccion().getLocalidad()
									   + " ("+local.getDireccion().getProvincia().getNombre()+")") ;
			
			//URL para ver el mapa 
			localImpl.setUrlVerMapa(BASE_URL_IR_MAPA + local.getDireccion().getTipoVia().getDescripcion()+", "
													 + local.getDireccion().getNombreVia() +", "
													 + (local.getDireccion().getNumero()!=null && !"".equals(local.getDireccion().getNumero()) 
													 ? local.getDireccion().getNumero()+", " : "")
													 + local.getDireccion().getCodigoPostal()+", " 
													 + local.getDireccion().getLocalidad()+", "
													 + local.getDireccion().getProvincia().getNombre()+", "
													 + "ES"); //España
													 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#desactivarLocal(dondeando.modelo.entidades.Local)
	 */
    public void desactivarLocal(Local local) {
    	local.setActivo(false);
    	try {
			localDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del local "+e);
		}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioLocal#activarLocal(dondeando.modelo.entidades.Local)
     */
    public void activarLocal(Local local) {
    	local.setActivo(true);
    	try {
    		localDAO.flush();
    	} catch (DAOExcepcion e) {
    		log.debug("Error al actualizar los datos del local "+e);
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioLocal#descartarLocal(dondeando.modelo.entidades.Local)
     */
    public void descartarLocal(Local local) {
    	localDAO.descartar(local);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioLocal#obtenerRangosPrecio()
     */
	public List<EntidadConCodigo> obtenerRangosPrecio(){
		List<EntidadConCodigo> rangosPrecios = new ArrayList<EntidadConCodigo>();
		rangosPrecios.add(new EntidadConCodigo(1, mensajesCore.obtenerTexto(RANGO_PRECIO_MENOR_10), RANGO_PRECIO_MENOR_10));
		rangosPrecios.add(new EntidadConCodigo(2, mensajesCore.obtenerTexto(RANGO_PRECIO_10_30), RANGO_PRECIO_10_30));
		rangosPrecios.add(new EntidadConCodigo(3, mensajesCore.obtenerTexto(RANGO_PRECIO_30_50), RANGO_PRECIO_30_50));
		rangosPrecios.add(new EntidadConCodigo(4, mensajesCore.obtenerTexto(RANGO_PRECIO_MAYOR_50), RANGO_PRECIO_MAYOR_50));
		return rangosPrecios;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#obtenerRangoPrecioDePrecio(java.math.BigDecimal)
	 */
	public String obtenerRangoPrecioDePrecio(BigDecimal precio){
		String rango = null;
		
		if(precio.compareTo(new BigDecimal(10))<=0)
			rango = RANGO_PRECIO_MENOR_10;
		else if(precio.compareTo(new BigDecimal(10))>0 && precio.compareTo(new BigDecimal(30))<=0)
			rango = RANGO_PRECIO_10_30;
		else if(precio.compareTo(new BigDecimal(30))>0 && precio.compareTo(new BigDecimal(50))<=0)
			rango = RANGO_PRECIO_30_50;
		else if(precio.compareTo(new BigDecimal(50))>0)
			rango = RANGO_PRECIO_MAYOR_50;
		
		return rango;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#encontrarLocalPorNombreLocalidadProvincia(java.lang.String, java.lang.String, dondeando.modelo.entidades.Provincia)
	 */
	public Local encontrarLocalPorNombreLocalidadProvincia(String nombre,String localidad, Provincia provincia) {
		Local local = null;
		
		List<Criterio> criterios = new ArrayList<Criterio>();
		
		if(nombre!=null && !"".equals(nombre))
			criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_NOMBRE, Criterio.IGUAL, nombre));
		if(localidad!=null && !"".equals(localidad) && provincia!=null)
			criterios.add(servicioCriterios
						 .construyeCriterio(Local.ATRIBUTO_DIRECCION, 
										    Criterio.COLECCION,
										    servicioCriterios.construyeCriterioAND(servicioCriterios.construyeCriterio(Direccion.ATRIBUTO_LOCALIDAD, Criterio.IGUAL, localidad),
												  								   servicioCriterios.construyeCriterio(Direccion.ATRIBUTO_PROVINCIA, Criterio.IGUAL, provincia))));
		criterios.add(servicioCriterios.construyeCriterio(null, Criterio.IGNORAR_ACENTOS, null));
		
		return local;
	}


	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#crearLocal(java.lang.String, dondeando.modelo.entidades.Provincia, java.lang.String, dondeando.modelo.entidades.TipoVia, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.util.List)
	 */
	public Local crearLocal(String nombre, Provincia provincia, String localidad, 
							TipoVia tipoVia, List<Integer> tiposLocal, String nombreVia, 
							Integer numero, Integer codigoPostal, String descripcion, String telefono,
							String email, String horario, BigDecimal precioMedio, String otraInformacion,
							List<Servicio> servicios){
		
		Local local = new LocalImpl();
		setearDatosLocal(local, nombre, provincia, localidad, tipoVia, tiposLocal, nombreVia, 
						 numero, codigoPostal, descripcion, telefono, email, horario, precioMedio, 
						 otraInformacion, servicios, true);
		localDAO.hacerPersistente(local);
		return local;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#editarLocal(dondeando.modelo.entidades.Local, java.lang.String, dondeando.modelo.entidades.Provincia, java.lang.String, dondeando.modelo.entidades.TipoVia, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.util.List)
	 */
	public void editarLocal(Local local, String nombre, Provincia provincia, String localidad, 
						    TipoVia tipoVia, List<Integer> tiposLocal, String nombreVia, 
						    Integer numero, Integer codigoPostal, String descripcion, String telefono,
 						    String email, String horario, BigDecimal precioMedio, String otraInformacion,
 						    List<Servicio> servicios){
		
		setearDatosLocal(local, nombre, provincia, localidad, tipoVia, tiposLocal, nombreVia, 
						 numero, codigoPostal, descripcion, telefono, email, horario, precioMedio, 
						 otraInformacion, servicios, local.isActivo());
		try {
			localDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del local");
		}
	}
	
	/**
	 * Setea al local los datos indicados
	 * @param local Local al que setear los datos
	 * @param nombre Nombre del local
	 * @param provincia Provincia donde está ubicado el local
	 * @param localidad	Localidad donde está ubicado el local
	 * @param tipoVia	Tipo de vía donde está ubicado el local
	 * @param tiposLocal Tipos en los que sw puede clasificar el local
	 * @param nombreVia Nombre de la vía donde está ubicado el local
	 * @param numero	Número donde está ubicado el local
	 * @param codigoPostal Código postal donde está ubicado el local
	 * @param descripcion Descripción del local
	 * @param telefono Teléfono del local
	 * @param email	Email del local
	 * @param horario	Horario del local
	 * @param precioMedio	Precio medio del local	
	 * @param otraInformacion Otra información sobre el local
	 * @param servicios Servicios que dispone el local
	 */
	private void setearDatosLocal(Local local, String nombre, Provincia provincia, String localidad, 
								  TipoVia tipoVia, List<Integer> tiposLocal, String nombreVia, 
								  Integer numero, Integer codigoPostal, String descripcion, String telefono,
								  String email, String horario, BigDecimal precioMedio, String otraInformacion,
								  List<Servicio> servicios, boolean activo){

		//Estamos creando el local
		if(local.getId()==null)
			//Debemos crear una dirección a la que asociar el local
			local.setDireccion(servicioDireccion.crearDireccion(provincia, localidad, tipoVia, nombreVia, numero, codigoPostal));
		else
			servicioDireccion.editarDireccion(local.getDireccion(), provincia, localidad, tipoVia, nombreVia, numero, codigoPostal);
		
		local.setNombre(nombre);
		local.setDescripcion(descripcion);
		local.setTelefono(telefono);
		local.setEmail(email);
		local.setHorario(horario);
		local.setPrecioMedio(precioMedio);
		local.setOtraInformacion(otraInformacion);
		local.setActivo(activo);
		
		if(local.getTiposLocal()==null) local.setTiposLocal(new HashSet<TipoLocal>());
		local.getTiposLocal().clear();
		if(tiposLocal!=null)
			local.getTiposLocal().addAll(servicioTipoLocal.encontrarPorIds(tiposLocal));
		
		if(local.getServicios()==null) local.setServicios(new HashSet<Servicio>());
		local.getServicios().clear();
		if(servicios!=null)
			local.getServicios().addAll(servicios);
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioLocal#encontrarLocalPorId(java.lang.Integer)
	 */
	public Local encontrarLocalPorId(Integer id) {
		try {
			return localDAO.encontrarPorId(id);
		} catch (DAOExcepcion e) {
			return  null;
		}
	}
}
