package utilidades.jsf;

import java.io.IOException;

import javax.faces.application.ViewHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filtro para que se fuerce la codificación de caracteres especificada
 * en el "web.xml". Acepta los parámetros "codificacion", en el que se 
 * indicará qué codificación es la que se quiere forzar y 
 * "prevaleceSobreCliente" si se ha de forzar el cambio.
 * 
 */
public class GeosFiltroCodificacionCaracteres implements Filter {

	private String codificacion;
	private boolean prevaleceSobreCliente;
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if ( codificacion!=null && ( prevaleceSobreCliente || request.getCharacterEncoding() == null ) ) {
			request.setCharacterEncoding(codificacion);
			((HttpServletRequest)request).getSession().setAttribute(ViewHandler.CHARACTER_ENCODING_KEY, codificacion);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		setCodificacion( filterConfig.getInitParameter("codificacion") );
	    setPrevaleceSobreCliente( "true".equals( filterConfig.getInitParameter("prevaleceSobreCliente") ) );
	}

	public String getCodificacion() {
		return codificacion;
	}

	public void setCodificacion(String codificacion) {
		this.codificacion = codificacion;
	}

	public boolean isPrevaleceSobreCliente() {
		return prevaleceSobreCliente;
	}

	public void setPrevaleceSobreCliente(boolean prevaleceSobreCliente) {
		this.prevaleceSobreCliente = prevaleceSobreCliente;
	}

}
