<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>
<f:view>
    <f:loadBundle basename="mensajesCore" var="resCore" />
  <trh:html id="html1">
    <trh:head title="Confirmar" id="head1">
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
    </trh:head>
    <trh:body id="body1">
      <h:form id="form1">
        <tr:panelFormLayout id="panelForm1">
          <f:facet name="footer"/>
          <tr:outputText value="#{catalogo_confirmacionBean.mensaje}"
                         id="outputText1"/>
          <tr:panelHorizontalLayout id="panelHorizontal1">
            <tr:commandButton text="#{resCore['ACEPTAR']}"
                              id="commandButton1"
                              action="#{catalogo_confirmacionBean.salir('Aceptar')}"/>
            <tr:commandButton text="#{resCore['CANCELAR']}"
                              action="#{catalogo_confirmacionBean.salir('Cancelar')}"
                              id="commandButton2"/>
          </tr:panelHorizontalLayout>
        </tr:panelFormLayout>
      </h:form>
    </trh:body>
  </trh:html>
</f:view>