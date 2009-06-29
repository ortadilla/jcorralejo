<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Gestión de Documentación">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>
        <h:form>
			<tr:messages />
          <tr:spacer width="10" height="10"/>
           <tr:panelFormLayout>
                <tr:commandLink text="pruebaConexion" action="#{accionesMacroBean.lanzarMacro}">
	                <f:param name="idMacro" value="1"/>
                </tr:commandLink>
                
                <tr:table var="dato" value="#{accionesMacroBean.datosLeidos}" emptyText="La consulta no ha obtenido datos" rows="5">
	                <f:facet name="header">
    	                <tr:outputText value="Consulta a Jupiter" />
        	        </f:facet>
                
       				<tr:column>
						<tr:outputText value="#{dato.nombreDatoLeer}" />
	       			</tr:column>       	
	       			
    	   			<tr:column>
						<tr:outputText value="#{dato.datoLeidoPantalla}" />
       				</tr:column>       	
				</tr:table>
         	</tr:panelFormLayout>
		</h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>