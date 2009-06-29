package utilidades.jsf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que sirve para servir al usuario ficheros. 
 * La clase que lo use ha de subir a la sesion hhtp el fichero en una variable llamada
 *  "ficheroTemporal". 
 */
public class ServletRedireccionFicheros extends HttpServlet{
	
	private static final long serialVersionUID = 3093929803096222539L;
	private static String dirTemporal;
	static{
		try {
			File tempFile = File.createTempFile("servlet", ".tmp");
			dirTemporal = tempFile.getParent();
			tempFile.delete();
		} catch (IOException e) {
			// TODO qué hacer si falla ??
			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		doGet(request, response);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		String nombreFichero = request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'));
		
		InputStream in = null;
		try {
			
			OutputStream out = response.getOutputStream( );
			
			byte[ ] buf = new byte[4 * 1024]; // 4K buffer
			in = new BufferedInputStream(new FileInputStream(dirTemporal + nombreFichero));
			int bytesRead;

			while ((bytesRead = in.read(buf)) != -1) {

				out.write(buf, 0, bytesRead);				
			
			}
			in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	
}
