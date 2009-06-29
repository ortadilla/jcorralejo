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
  <f:loadBundle basename="mensajesPed" var="mensajesPedidos"/>
  <f:loadBundle basename="mensajesOrg" var="mensajesOrganizacion"/>
  <f:loadBundle basename="mensajesCat" var="mensajesCatalogo"/>
  <f:loadBundle basename="mensajesNec" var="mensajesNecesidades"/>
  <f:loadBundle basename="mensajesCore" var="mensajesCore"/>
  <trh:html>
    <trh:head title="#{mensajesPedidos['CONFIGURACION_COLOR_PEDIDOS']}">
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
    </trh:head>
    <trh:body>
      <h:form>
          <tr:panelBox inlineStyle="width: 60%;" text="#{mensajesPedidos['CONFIGURACION_COLOR_PEDIDOS']}">
            <tr:panelHorizontalLayout>
              <tr:panelFormLayout>
                <tr:forEach var="variable"
                            items="#{pedidos_ConfiguracionColorPedidosBean.listaEstados}">
                    <tr:inputColor label="#{variable.descripcion}"
				                   value="#{variable.color}" 
				                   compact="true"/>
                    <tr:spacer width="0" height="10"/>
                  </tr:forEach>
              </tr:panelFormLayout>
            </tr:panelHorizontalLayout>
          </tr:panelBox>
        <tr:spacer width="0" height="15"/>
        <tr:panelGroupLayout>
          <tr:commandButton text="#{mensajesCore['ACEPTAR']}"
                            action="#{pedidos_ConfiguracionColorPedidosBean.guardarParametro}"/>
          <tr:commandButton text="#{mensajesCore['CANCELAR']}"
                            action="#{pedidos_ConfiguracionColorPedidosBean.terminarEjecucion}"/>
        </tr:panelGroupLayout>
      </h:form>
    </trh:body>
  </trh:html>
</f:view>
</jsp:root>
