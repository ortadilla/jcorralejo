package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.IMAGEN_DAO;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.MensajesCore;
import dondeando.modelo.dao.ImagenDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.Parametros;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.ImagenImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioImagen;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_IMAGEN)
public class ServicioImagenImpl implements ServicioImagen{

	private Log log = LogFactory.getLog(ServicioImagenImpl.class);

	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;

	@In(value=IMAGEN_DAO, create=true)
	private ImagenDAO imagenDAO;

	@In(value=SERVICIO_CRITERIOS, create=true)
	private ServicioCriterios servicioCriterios;
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#devuelveFicheroTemporal(java.io.InputStream)
	 */
	public File devuelveFicheroTemporal(InputStream inputStream){

		File ficheroTemporal=null;
		File directorio=null;
		try {
			directorio = new File(Parametros.PARAMETRO_DIRECTORIO_GUARDAR_IMAGENES);
			if(!directorio.exists())
				directorio.mkdirs();
			//Creamos el archivo temporal en el directorio que nos dice el parámetro
			File temp;
			temp = File.createTempFile(Imagen.IMAGEN_TEMPORAL, ".tmp",directorio);

			// Borramos cuando salga
			temp.deleteOnExit();

			FileOutputStream fos = new FileOutputStream(temp);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			Integer data=null;
			while((data=inputStream.read()) != -1)
				bos.write(data);

			inputStream.close();
			bos.flush();
			bos.close();

			ficheroTemporal=temp;
		} catch (IOException e) {
			log.debug("Error al crear el fichero temporal "+ inputStream + ": " +e);
		}
		return ficheroTemporal;
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#devuelveFileInputStream(java.io.File)
	 */
	public FileInputStream devuelveFileInputStream(File temp){
		try {
			return new FileInputStream(temp);
		} catch (FileNotFoundException e) {
			log.debug("Error al devolver el FileInputStream: " +e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#comprobarAgregarImagen(java.lang.String, java.lang.Long, java.io.InputStream)
	 */
	public List<String> comprobarAgregarImagen(String uploadedFileType,	Long uploadedFileSize, InputStream input) {
		List<String> listaErrores = new ArrayList<String>();

		if(uploadedFileType==null || uploadedFileSize==null || input==null) 
			throw new IllegalArgumentException("Los parámetros son obligatorios");

		//Comprobamos tamaño...
		if((uploadedFileSize.compareTo(new Long(new Long(Imagen.TAMANO_MAXIMO)).longValue()*1024)>0))
			listaErrores.add(mensajesCore.obtenerTexto("ERROR_TAMANO_IMAGEN", Imagen.TAMANO_MAXIMO));

		//Comprobamos el tipo..
		if(!uploadedFileType.equals("image/jpeg") 
				&& !uploadedFileType.equals("image/pjpeg") 
				&& !uploadedFileType.equals("image/gif"))
			listaErrores.add(mensajesCore.obtenerTexto("ERROR_TIPO_IMAGEN"));

		//comprobamos alto y ancho
		if(!altoYAnchoCorrecto(input,uploadedFileSize))
			listaErrores.add(mensajesCore.obtenerTexto("ERROR_PIXEL_IMAGEN", 
					Imagen.ANCHURA_MINIMA, 
					Imagen.ALTURA_MINIMA,
					Imagen.ANCHURA_MAXIMA,
					Imagen.ALTURA_MAXIMA));

		return listaErrores;
	}


	/**
	 * Comprueba el ancho y la altura en píxeles de una imagen
	 * @param input, el inputStream del archivo
	 * @param uploadedFileSize, tamaño del archivo
	 * @return TRUE si el tamaño es correcto
	 */
	private boolean altoYAnchoCorrecto(InputStream input,Long uploadedFileSize)
	{
		boolean correcto = false;
		try{
			//Creamos la imagen
			BufferedImage bI = ImageIO.read(input);
			if(bI!=null){ //Sólo si es una imagen
				int w = bI.getWidth(null);
				int h = bI.getHeight(null);

				//Comprobamos la anchura y altura
				if(w>=Imagen.ANCHURA_MINIMA && w<=Imagen.ANCHURA_MAXIMA 
						&& h>=Imagen.ALTURA_MINIMA && h<=Imagen.ALTURA_MAXIMA)
					correcto = true;
			}

		}catch(IOException io) {
			throw new RuntimeException("No se puede crear el BufferedImage", io);
		}

		return correcto;
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#guardarImagen(java.lang.String, java.io.InputStream, boolean)
	 */
	public Imagen guardarImagen(String nombre, InputStream inputStream, boolean guardarEnBD){

		Imagen imagenCreada = null;
		//Si se recibe algún nulo la iamgen no se subirá
		if(inputStream!=null){
			try {
				
				//Primero la guardamos en el servidor
				Integer data;
				File directorio= new File(Parametros.PARAMETRO_DIRECTORIO_GUARDAR_IMAGENES);
				//Si el directorio donde guardar la foto aún no existe, lo creamos
				if(!directorio.exists())
					directorio.mkdirs();

				//Creamos el archivo nuevo con el nombre correspondiente
				File destino = new File(directorio.getAbsolutePath()+"/"+nombre);
				//Copiamos el archivo creado en el directorio correspondiente
				OutputStream outputStream = new FileOutputStream(destino);
				while((data=inputStream.read()) != -1)
					outputStream.write(data);
				inputStream.close();
				outputStream.close();


				imagenCreada = new ImagenImpl();    
				imagenCreada.setNombre(destino.getName());
				
				if(guardarEnBD){
					//Si lo indican, la guardamos en BD
					FileInputStream fi;
					fi = new FileInputStream(destino);

					//Pasamos el inputStream a byteArray
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					data = fi.read();
					while(data!=-1) {
						baos.write(data);
						data = fi.read();
					}
					baos.flush();

					imagenCreada.setContenido(baos.toByteArray());
				}

				imagenDAO.hacerPersistente(imagenCreada);
				imagenDAO.flush();
			} catch (Exception e) {
				log.debug("Error al guardar la imagen "+nombre);
			}
		}
		return imagenCreada;
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#calcularUrlImagenUsuario(dondeando.modelo.entidades.Usuario)
	 */
	public String calcularUrlImagenUsuario(Usuario usuario){
		String url = "";
		if(usuario.getAvatar()!=null)
			url = Parametros.PARAMETRO_URL_IMAGENES + usuario.getAvatar().getNombre();
		return url;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#calcularUrlImagenUsuarioNuevo()
	 */
	public String calcularUrlImagenUsuarioNuevo() {
		return Parametros.PARAMETRO_URL_IMAGENES+ Parametros.PARAMETRO_NOMBRE_IMAGENEN_USUARIO_GENERICO;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioImagen#encontrarImagenUsuarioGenerico()
	 */
	public Imagen encontrarImagenUsuarioGenerico() {
		Criterio criterioNombre = servicioCriterios.construyeCriterio(Imagen.ATRIBUTO_NOMBRE, 
																	  Criterio.IGUAL, 
																	  Parametros.PARAMETRO_NOMBRE_IMAGENEN_USUARIO_GENERICO); 
		List<Imagen> imagen = imagenDAO.encontrarPorCondicion(criterioNombre);
		return !imagen.isEmpty() ? imagen.get(0) : null;
	}
}
