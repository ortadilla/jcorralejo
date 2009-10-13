package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.IMAGEN_LOCAL_DAO;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN_LOCAL;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.ImagenLocalDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.ImagenLocal;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.implementacion.ImagenLocalImpl;
import dondeando.modelo.servicio.ServicioImagenLocal;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_IMAGEN_LOCAL)
public class ServicioImagenLocalImpl implements ServicioImagenLocal{
	
    @In(value=IMAGEN_LOCAL_DAO, create=true)
    private ImagenLocalDAO imagenLocalDAO;

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioImagenLocal#hacerTransitoria(dondeando.modelo.entidades.ImagenLocal)
     */
    public void hacerTransitoria(ImagenLocal imagenLocal){
    	imagenLocalDAO.hacerTransitorio(imagenLocal);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioImagenLocal#flush()
     */
    public void flush() throws DAOExcepcion{
    	imagenLocalDAO.flush();
    }

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioImagenLocal#crearImagenLocal(dondeando.modelo.entidades.Imagen, dondeando.modelo.entidades.Local, java.lang.String)
     */
    public ImagenLocal crearImagenLocal(Imagen imagen, Local local, String descripcion){
    	ImagenLocal imagenLocal = new ImagenLocalImpl();
    	
    	imagenLocal.setDescripcion(descripcion);
    	imagenLocal.setLocal(local);
    	imagenLocal.setImagen(imagen);
    	imagenLocalDAO.hacerPersistente(imagenLocal);
    	local.getImagenes().add(imagenLocal);
    	
    	return imagenLocal;
    }
}

