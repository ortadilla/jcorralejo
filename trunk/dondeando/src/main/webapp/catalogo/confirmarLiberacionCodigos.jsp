<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=windows-1252"/>
  <f:view>
	<f:loadBundle basename="mensajesCore" var="resCore" />
    <trh:html>
      <trh:head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
      </trh:head>
      <trh:body>
        <tr:form>
        	<tr:panelPage>
        		<tr:panelHeader></tr:panelHeader>
	        	<tr:panelHorizontalLayout halign="center"> 
	            	<tr:panelBox> 		
	            		<tr:panelHorizontalLayout halign="center">            	
	            			<tr:outputText value="#{catalogo_confirmarLiberacionCodigos.mensaje}"/>
	            		</tr:panelHorizontalLayout>
	            	</tr:panelBox>
	            </tr:panelHorizontalLayout>				
	            <tr:spacer width="10" height="10"/>            
	            <tr:panelHorizontalLayout  halign="center">
	            	<tr:commandButton text="#{resCore['ACEPTAR']}"
	              	 	action="#{catalogo_confirmarLiberacionCodigos.botonAceptarPopUP}"/>
	              	<tr:spacer width="10" height="10"/>             
	              	<tr:commandButton text="#{resCore['CANCELAR']}"
	              		action="#{catalogo_confirmarLiberacionCodigos.botonCancelarPopUP}"/>
	            </tr:panelHorizontalLayout>
	        </tr:panelPage>     
	     </tr:form>
    	</trh:body>
    </trh:html>
  </f:view>
</jsp:root>