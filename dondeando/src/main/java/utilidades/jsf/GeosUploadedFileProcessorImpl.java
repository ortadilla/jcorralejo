package utilidades.jsf;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.myfaces.trinidad.logging.TrinidadLogger;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.util.ClassLoaderUtils;
import org.apache.myfaces.trinidad.webapp.UploadedFileProcessor;
import org.apache.myfaces.trinidadinternal.config.upload.UploadedFileProcessorImpl;
import org.apache.myfaces.trinidadinternal.context.external.ServletApplicationMap;
import org.apache.myfaces.trinidadinternal.context.external.ServletInitParameterMap;
import org.apache.myfaces.trinidadinternal.context.external.ServletRequestMap;


/**
 * Se implementa la interfaz UploadedFileProcessor.java para poder controlar la excepci�n en caso
 * de que se supere el tama�o m�ximo permitido
 * 
 * @author maaguilar
 */
public class GeosUploadedFileProcessorImpl extends UploadedFileProcessorImpl implements UploadedFileProcessor
{
	public GeosUploadedFileProcessorImpl()
	{
		super();
	}

	 public void init(Object context)
	  {
	    ContextInfo info;
	    if(_PORTLET_CONTEXT_CLASS != null && _PORTLET_CONTEXT_CLASS.isInstance(context))
	    {
	      info = _getPortletContextInfo(context);
	    }
	    else
	    {
	      info = _getServletContextInfo(context);
	    }

	    //
	    // Get MaxMemory and TempDir properties from servlet init params
	    //
	    if (_maxMemory == -1)
	    {
	      String maxMemory = info.initParams.get(MAX_MEMORY_PARAM_NAME);
	      if (maxMemory != null)
	      {
	        try
	        {
	          _maxMemory = Integer.parseInt(maxMemory);
	        }
	        catch (NumberFormatException nfe)
	        {
	          _maxMemory = _DEFAULT_MAX_MEMORY;
	        }
	      }
	      else
	      {
	        _maxMemory = _DEFAULT_MAX_MEMORY;
	      }
	    }

	    if (_maxDiskSpace == -1)
	    {
	      String maxDiskSpace = info.initParams.get(MAX_DISK_SPACE_PARAM_NAME);
	      if (maxDiskSpace != null)
	      {
	        try
	        {
	          _maxDiskSpace = Integer.parseInt(maxDiskSpace);
	        }
	        catch (NumberFormatException nfe)
	        {
	          _maxMemory = _DEFAULT_MAX_DISK_SPACE;
	        }
	      }
	      else
	      {
	        _maxDiskSpace = _DEFAULT_MAX_DISK_SPACE;
	      }
	    }

	    if (_tempDir == null)
	    {
	      _tempDir = info.initParams.get(TEMP_DIR_PARAM_NAME);
	      // Use the webapp temporary directory if the temporary directory
	      // has not been explicitly set.
	      if (_tempDir == null)
	      {
	        File tempDirFile = (File)
	          info.attributes.get("javax.servlet.context.tempdir");
	        if (tempDirFile != null)
	          _tempDir = tempDirFile.getAbsolutePath();
	      }
	    }
	  }
	  
	  public FacesContext getFacesContext(HttpServletRequest request,HttpServletResponse response){

	      //TODO Falta conseguir el response para obtener de una manera limpia el FacesContext 
	      ServletContext servletContext = ((HttpServletRequest)request).getSession().getServletContext();
	      FacesContextFactory contextFactory = (FacesContextFactory)FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
	      LifecycleFactory lifecycleFactory =(LifecycleFactory)FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
	      Lifecycle lifecycle =lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
	      FacesContext facesContext =contextFactory.getFacesContext(servletContext, request, response,lifecycle);

	      return facesContext;
	  }

	  public UploadedFile processFile(
	      Object request, UploadedFile tempFile) throws IOException
	  {
	     
	    GeosUploadedFileImpl file=null;
	    try{  
	     
	        RequestInfo info = _getRequestInfo(request);
	       
	        // Process one new file, loading only as much as can fit
	        // in the remaining memory and disk space.
	        file = new GeosUploadedFileImpl();
	        file.loadFile(tempFile,
	                      _maxMemory - info.totalBytesInMemory,
	                      _maxDiskSpace - info.totalBytesOnDisk,
	                      _tempDir);
	    
	        // Keep a tally of how much we've stored in memory and on disk.
	        long length = file.getLength();
	        if (file.__isInMemory())
	        {
	          info.totalBytesInMemory += length;
	        }
	        else
	        {
	          info.totalBytesOnDisk += length;
	        }
	    }
	    catch(Exception e){
	        HttpServletRequest req = (HttpServletRequest)request;
	        req.setAttribute(MENSAJE_ERROR,"El archivo supera el tama�o m�ximo permitido ("+(_maxMemory/1024)+" KB)");
	        file=null;
	    }
	    return file;
	    
	  }

	  private RequestInfo _getRequestInfo(Object request)
	  {
	    Map<String, Object> attributes;
	    if (_PORTLET_REQUEST_CLASS != null && _PORTLET_REQUEST_CLASS.isInstance(request))
	    {
	      attributes = _getPortletRequestMap(request);
	    }
	    else
	    {
	      attributes = _getServletRequestMap(request);
	    }


	    RequestInfo info = (RequestInfo) attributes.get(_REQUEST_INFO_KEY);

	    if (info == null)
	    {
	      info = new RequestInfo();
	      attributes.put(_REQUEST_INFO_KEY, info);
	    }

	    return info;
	  }

	  private static final ContextInfo _getServletContextInfo(final Object context)
	  {
	    assert(context instanceof ServletContext);

	    final ServletContext sContext = (ServletContext)context;
	    return new ContextInfo(
	             new ServletInitParameterMap(sContext),
	             new ServletApplicationMap(sContext));
	  }

	  private static final ContextInfo _getPortletContextInfo(final Object context)
	  {
	    //assert(context instanceof javax.portlet.PortletContext);

//	    final PortletContext pContext = (PortletContext)context;
//	    return new ContextInfo(
//	             new PortletInitParameterMap(pContext),
//	             new PortletApplicationMap(pContext));
	      return null;
	  }

	  private static final Map<String, Object> _getServletRequestMap(final Object request)
	  {
	    assert(request instanceof ServletRequest);

	    return new ServletRequestMap((ServletRequest) request);
	  }

	  private static final Map<String, Object> _getPortletRequestMap(final Object request)
	  {
//	    assert(request instanceof PortletRequest);
	//
//	    return new PortletRequestMap((PortletRequest) request);
	      return null;
	  }


	  static private class RequestInfo
	  {
	    public long totalBytesInMemory;
	    public long totalBytesOnDisk;
	  }

	  static private class ContextInfo
	  {
	    public ContextInfo(Map<String,String> init, Map<String, Object> attrib)
	    {
	      initParams= init;
	      attributes = attrib;
	    }

	    public Map<String, String> initParams;
	    public Map<String, Object> attributes;
	  }

	  private long   _maxMemory = -1;
	  private long   _maxDiskSpace = -1;
	  private String _tempDir = null;

	  private static final long _DEFAULT_MAX_MEMORY = 102400;
	  private static final long _DEFAULT_MAX_DISK_SPACE = 2048000;
	  public static final String MENSAJE_ERROR = "FACE_MENSAJE";

	  private static final String _REQUEST_INFO_KEY = GeosUploadedFileProcessorImpl.class.getName()+
	    ".UploadedFilesInfo";

	  private static final TrinidadLogger _LOG =
	    TrinidadLogger.createTrinidadLogger(GeosUploadedFileProcessorImpl.class);

	  private static final Class<?>        _PORTLET_CONTEXT_CLASS;
	  private static final Class<?>       _PORTLET_REQUEST_CLASS;

	  static
	  {
	    Class<?> context;
	    Class<?> request;
	    try
	    {
	      context = ClassLoaderUtils.loadClass("javax.portlet.PortletContext");
	      request = ClassLoaderUtils.loadClass("javax.portlet.PortletRequest");
	    }
	    catch (final ClassNotFoundException e)
	    {
	      _LOG
	          .fine("Portlet API is not available on the classpath.  Portlet configurations are disabled.");
	      context = null;
	      request = null;
	    }

	    _PORTLET_CONTEXT_CLASS = context;
	    _PORTLET_REQUEST_CLASS = request;
	  } 

}
