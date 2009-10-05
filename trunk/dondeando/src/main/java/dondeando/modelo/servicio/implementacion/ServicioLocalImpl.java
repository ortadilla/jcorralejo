package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.LOCAL_DAO;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.MensajesCore;
import dondeando.modelo.dao.LocalDAO;
import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.entidades.implementacion.LocalImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioLocal;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_LOCAL)
public class ServicioLocalImpl implements ServicioLocal{
	
	public static final String BASE_URL_IR_MAPA = "http://maps.google.es/maps?f=q&hl=es&geocode=&q=";
	
    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
    
    @In(value=LOCAL_DAO, create=true)
    private LocalDAO localDAO;
    
    @In(value=MENSAJES_CORE, create=true)
    private MensajesCore mensajesCore;
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioLocal#encontrarLocalesPorNombreTipoProvinciaYPrecio(java.lang.String, java.util.List, dondeando.modelo.entidades.Provincia, java.lang.String)
     */
	public List<Local> encontrarLocalesPorNombreTipoProvinciaYPrecio(String nombre, List<Integer> tiposLocal, Provincia provincia, String precio){
		List<Criterio> criterios = new ArrayList<Criterio>();
		
		if(nombre!=null && !"".equals(nombre))
			criterios.add(servicioCriterios.construyeCriterio(Local.ATRIBUTO_NOMBRE, Criterio.IGUAL, nombre));
		
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
			if(precio.compareTo(new BigDecimal(10))<=0){
				imagenPrecio = "/imagenes/dondeando/precio1.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_MENOR_10);
			}
			else if(precio.compareTo(new BigDecimal(10))>0 && precio.compareTo(new BigDecimal(30))<=0){
				imagenPrecio = "/imagenes/dondeando/precio2.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_10_30);
			}
			else if(precio.compareTo(new BigDecimal(30))>0 && precio.compareTo(new BigDecimal(50))<=0){
				imagenPrecio = "/imagenes/dondeando/precio3.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_30_50);
			}
			else if(precio.compareTo(new BigDecimal(50))>0){
				imagenPrecio = "/imagenes/dondeando/precio4.png";
				shortDescPrecio = mensajesCore.obtenerTexto(RANGO_PRECIO_MAYOR_50);
			}
			localImpl.setImagenPrecio(imagenPrecio);
			localImpl.setShortDescPrecio(shortDescPrecio);
			
			//Dirección en modo "humano"
			localImpl.setDireccionHumana(local.getDireccion().getTipoVia().getDescripcion() + " "
									   + local.getDireccion().getNombreVia()+", "
									   + local.getDireccion().getNumero()+ " "
									   + local.getDireccion().getLocalidad()
									   + " ("+local.getDireccion().getProvincia().getNombre()+")") ;
			
			//URL para ver el mapa 
			localImpl.setUrlVerMapa(BASE_URL_IR_MAPA + local.getDireccion().getTipoVia().getDescripcion()+", "
													 + local.getDireccion().getNombreVia() +", "
													 + local.getDireccion().getNumero()+", "
													 + local.getDireccion().getCodigoPostal()+", " 
													 + local.getDireccion().getLocalidad()+", "
													 + local.getDireccion().getProvincia().getNombre()+", "
													 + "ES"); //España
													 
		}
	}

}
