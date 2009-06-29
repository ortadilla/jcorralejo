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
    <f:loadBundle basename="mensajesCore" var="resCore"/>
    <trh:html>
      <trh:head title="#{resCore['ERROR']}">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
      </trh:head>
      <trh:body>
	  	<tr:panelPage>
           <tr:panelHeader text="#{resCore['ERROR']}"/>
           <tr:form defaultCommand="volver">
                <tr:spacer height="10"/>
               <tr:outputText value="#{resCore['ERROR_CONCURRENCIA_BORRADO']}"/>
               <tr:spacer height="10"/>
               <!-- Se vuelve al menú porque no se puede recuperar la conversación, y por tanto
                    no se puede volver a la pantalla anterior -->
               <tr:commandButton id="volver" text="#{resCore['VOLVER']}" action="#{core_ErrorBean.volverMenu}"/>
               <tr:spacer height="10"/>
           </tr:form>
        </tr:panelPage>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>

