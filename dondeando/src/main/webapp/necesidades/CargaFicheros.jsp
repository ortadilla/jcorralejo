<%@ page language="java" 
import="org.xml.sax.SAXParseException,org.hibernate.exception.*,org.hibernate.*,javazoom.upload.*,java.util.*,java.io.*,com.hp.geos.necesidades.vista.bean.*,com.hp.geos.core.modelo.servicio.excepciones.*,java.lang.IllegalArgumentException.*,java.lang.Exception.*,com.hp.geos.necesidades.modelo.servicio.implementacion.ServicioGestionNecesidadImpl,com.hp.geos.necesidades.modelo.servicio.implementacion.ParseadorXMLImpl" 
%>

<jsp:useBean id="upBean" 
			scope="page" 
			class="javazoom.upload.UploadBean" />

<jsp:useBean id="cargaNecesidades" 
			 scope="page" 
			class="com.hp.geos.necesidades.vista.bean.CargaSolicitudNecesidadesBeanImpl"/>

<%
//Creamos un configurador de los bean necesarios
ConfiguradorJSPCargaSolicitudNecesidadImpl configuradorJSPCargaSolicitudNecesidad=
	 new ConfiguradorJSPCargaSolicitudNecesidadImpl();

//Inicializamos los atributos del bean upBean
configuradorJSPCargaSolicitudNecesidad.configuraUploadBean(upBean);

//Configuramos la cabecera de la respuesta de la página
configuradorJSPCargaSolicitudNecesidad.configuraCabecera(request, response,upBean );
    
	
%>
<html>
<head>
<title></title>

<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1"">
</head>
<body>
<ul class="style1">
<%

	
if (MultipartFormDataRequest.isMultipartFormData(request))
{
   //Parseamos la HTTP request utilizando la clase MultipartFormDataRequest
   MultipartFormDataRequest mrequest = null;
   try
   {
   	mrequest = new MultipartFormDataRequest(request,null,-1,MultipartFormDataRequest.CFUPARSER,"ISO-8859-1",CargaBean.RESUMEN);
   } catch (Exception e)
 	{
  	 //Lanzamos una excepción y cancelamos la subida del fichero
    throw new Exception(e.getMessage());   
  	 
 	}   
 
	   String account = mrequest.getParameter("account");
	  
	   if (account == null) 
		   account="";
	   else if (!account.startsWith("/")) 
		   account = "/"+account;
	  
     upBean.setFolderstore(upBean.getFolderstore()+account);
     Hashtable files = mrequest.getFiles();
     
     
     if ( (files != null) && (!files.isEmpty()) )
     {
       UploadFile file = (UploadFile) files.get("uploadfile");      
             
      	
       
       //Soporte para la creacion de carpetas y subcarpetas
      configuradorJSPCargaSolicitudNecesidad.configurarCarpetas(mrequest,file,upBean);
       
                        
       
      	 upBean.store(mrequest, "uploadfile");
      	
      	 try{
      	 
      	 Session sessionHibernate=configuradorJSPCargaSolicitudNecesidad.inicializaServicios(cargaNecesidades);
      	 cargaNecesidades.setSession(sessionHibernate);        	 
								
			//Procesamos el archivo	
			
			    boolean archivoProcesado=cargaNecesidades.procesaArchivo(file.getFileName(),file.getContentType(),CargaBean.PATH);
					            
			if(archivoProcesado==true){
       
       	out.println("Archivo procesado con exito");
       	out.println("<input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
           }
			else{
				out.println("El archivo no se ha procesado con exito<br/>");
				out.println("<br/><input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
			}
			}catch(ServicioExcepcion e){
				List listaErrores=cargaNecesidades.getListaErrores();
				Iterator it=listaErrores.iterator();
				while(it.hasNext()){
					String error=(String)it.next();
					out.println(error+"<br/>");
				}
			
				out.println(e.getMessage());
				out.println("<br/><input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
			}
       	catch(IllegalArgumentException e){
				out.println(e.getMessage());
				out.println("<br/><input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
			}
       	catch(ConstraintViolationException e){
       		out.println("No se han insertado todas las solicitudes en la base de datos. Compruebe que los campos sean correctos<br/>"+e.getMessage());
       		out.println("<br/><input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
       	}
       	
    
   	  
       }        
     

     else
     {
       String emptydirectory = mrequest.getParameter("emptydirectory");
       if ((emptydirectory != null) && (!emptydirectory.equals("")))
       {
         File dir = new File(upBean.getFolderstore()+account+"/"+emptydirectory);
         dir.mkdirs();
       }
       out.println("<br/>No se ha encontrado ningun archivo");
       out.println("<input name=\"button\" type=\"button\" onclick=\"window.close();\" value=\"Cerrar\" /> ");
       
     }
}



      
      

         
         
        
      
     

%>



</body>
</html>
