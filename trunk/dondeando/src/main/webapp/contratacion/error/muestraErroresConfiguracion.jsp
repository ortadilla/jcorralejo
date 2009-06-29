<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/strict.dtd"
              doctype-public="-//W3C//DTD HTML 4.01//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Seleccione">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>
        <h:form>
          <tr:panelBox  background="medium" inlineStyle="width: 100%;" text="Se han detectado errores de configuración" >
              <f:facet name="footer"/>
              <tr:spacer width="10" height="10"/>
              <tr:table width="100%"  rows="25" var="lista"
                        value="#{erroresConfiguracion}">
                <tr:column 	headerText="Detalle">
                           <tr:outputText value="#{lista}"></tr:outputText>
                  <tr:outputText/>
                </tr:column>

              </tr:table>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>