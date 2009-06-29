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
      <trh:head title="Excepción">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
      </trh:head>
      <trh:body>
           <tr:panelHeader text="Excepción"/>
           <tr:form defaultCommand="volver">
               <tr:spacer height="10"/>
               <tr:outputText value="#{core_ErrorBean.excepcion}"/>
               <tr:spacer height="10"/>
               <tr:commandButton id="volver" text="#{resCore['VOLVER']}" action="#{core_ErrorBean.getUltimaURLVisitada}"/>
               <tr:spacer height="10"/>
               <tr:showDetail undisclosedText="StackTrace">
                 <tr:inputText columns="120" rows="20" readOnly="true" wrap="off"
                               value="#{core_ErrorBean.stackTrace}"/>
               </tr:showDetail>
           </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>

