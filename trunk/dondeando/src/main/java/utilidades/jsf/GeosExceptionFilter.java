package utilidades.jsf;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.contexts.Lifecycle;
import org.jboss.seam.core.Exceptions;
import org.jboss.seam.log.LogProvider;
import org.jboss.seam.log.Logging;
import org.jboss.seam.mock.MockApplication;
import org.jboss.seam.mock.MockExternalContext;
import org.jboss.seam.mock.MockFacesContext;
import org.jboss.seam.mock.MockViewHandler;
import org.jboss.seam.web.ExceptionFilter;


public class GeosExceptionFilter extends ExceptionFilter {
    
    private static final LogProvider log = Logging.getLogProvider(GeosExceptionFilter.class);

    /* (non-Javadoc)
     * @see org.jboss.seam.web.ExceptionFilter#endWebRequestAfterException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Exception)
     */
    @Override
    protected void endWebRequestAfterException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException{
        log.debug("ending request");
        //the FacesContext is gone - create a fake one for Redirect and HttpError to call
        
        //Estas son las únicas líneas sobrescritas del método, con la intención de asignarle
        //nuestro GeosMockViewHandler
        MockFacesContext facesContext = new MockFacesContext( new MockExternalContext(getServletContext(), request, response), new MockApplication() );
        facesContext.getApplication().setViewHandler(new GeosMockViewHandler());
        
        
        facesContext.setCurrent();
        Lifecycle.beginExceptionRecovery( facesContext.getExternalContext() );
        try
        {
           Exceptions.instance().handle(e);
        }
        catch (ServletException se)
        {
           throw se;
        }
        catch (IOException ioe)
        {
           throw ioe;
        }
        catch (Exception ehe)
        {
           throw new ServletException(ehe);
        }
        finally
        {
           try 
           {
              Lifecycle.endRequest( facesContext.getExternalContext() );
              facesContext.release();
              log.debug("ended request");
           }
           catch (Exception ere)
           {
              log.error("could not destroy contexts", e);
           }
        }
    }
    
    class GeosMockViewHandler extends MockViewHandler{

        /* (non-Javadoc)
         * @see org.jboss.seam.mock.MockViewHandler#getActionURL(javax.faces.context.FacesContext, java.lang.String)
         */
        @Override
        public String getActionURL(FacesContext ctx, String viewId){
            //Si se redirije a la página de error, devolvemos a pelo la URL destino para corregir el
            //comportamiento indeseado en OC4J
//            if(viewId.equals(Pages.JSP_ERROR))
//                return ctx.getExternalContext().getRequestContextPath()+Pages.toJSF(viewId);
            return super.getActionURL(ctx, viewId);
        }
        
    }
    
}
