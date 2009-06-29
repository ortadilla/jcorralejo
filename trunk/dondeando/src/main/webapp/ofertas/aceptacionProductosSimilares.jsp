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
      <trh:head title="Aceptación Masiva">
        <meta http-equiv="Content-Type"
              content="text/html; charset=windows-1252"/>
      </trh:head>
      <trh:body>
        <tr:form>
            <tr:panelHorizontalLayout halign="center">
            	<tr:outputText value="#{ofertas_aceptacionProductosSimilaresBean.mensaje}"/>	
          	</tr:panelHorizontalLayout>
            <tr:spacer width="10" height="10"/>            
            <tr:panelHorizontalLayout halign="center">            
            	<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}" 
					  rowBandingInterval="1"
					  columnBandingInterval="0"
                      var="var" rows="10"
                      id="tablaResultados"
                      rendered="#{ofertas_aceptacionProductosSimilaresBean.mostrarTabla}"
                      value="#{ofertas_aceptacionProductosSimilaresBean.listaOfertas}"
                      immediate="false">
              			<tr:column sortable="false">
                			<tr:outputText value="#{var.descripcionCompleta}"/>
              			</tr:column>
            	</tr:table>            
            </tr:panelHorizontalLayout>
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout  halign="center">
            	<tr:commandButton text="#{resCore['ACEPTAR']}"
              	 	action="#{ofertas_aceptacionProductosSimilaresBean.botonAceptarPopUP}"/>
              	<tr:spacer width="10" height="10"/>             
              	<tr:commandButton text="#{resCore['CANCELAR']}"
              		action="#{ofertas_aceptacionProductosSimilaresBean.botonCancelarPopUP}"/>
            </tr:panelHorizontalLayout>           
          </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>