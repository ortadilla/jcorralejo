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
  <f:loadBundle basename="mensajesOrg"  var="resOrg"/>
    <trh:html>
      <trh:head title="#{resOrg['IDENTIFICACION_DE_USUARIOS']}">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
<!--        <meta http-equiv="expires" content="0" />-->
<!--		<meta http-equiv="pragma" content="no-cache" />-->
<!--		<meta http-equiv="cache-control" content="max-age=0, no-cache, no-store, must-revalidate"/>-->
      </trh:head>
      <trh:body initialFocusId="#{organizacion_login.username==null ? 'user' : 'passw' }"> <!-- onload="var campo=document.getElementById('user');campo.focus();campo.select()" -->
        <tr:form defaultCommand="btnAceptar" id="formLogin">
          <tr:panelHorizontalLayout>
            <tr:image source="/imagenes/#{organizacion_login.logo}" styleClass="estiloImagenLogin"/>
            <tr:spacer width="10"/>
            <tr:panelBox text="#{resOrg['IDENTIFICACION_DE_USUARIOS']}">
              <tr:spacer width="225" height="10"/>
              <tr:panelFormLayout>
                <tr:panelLabelAndMessage label="#{resOrg['USUARIO']}">
                  <tr:inputText columns="15" id="user" value="#{organizacion_login.username}" simple="true"/>
                </tr:panelLabelAndMessage>
                <tr:spacer width="10" height="10"/>
                <tr:panelLabelAndMessage label="#{resOrg['CONTRASEÑA']}">
                  <tr:inputText columns="15" id= "passw" value="#{organizacion_login.password}" secret="true" simple="true"/>
                </tr:panelLabelAndMessage>
                <tr:spacer width="10" height="10"/>
              </tr:panelFormLayout>
              <tr:panelHorizontalLayout halign="center">
                <tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar" action="#{organizacion_login.login}"/>
              </tr:panelHorizontalLayout>
            </tr:panelBox>
          </tr:panelHorizontalLayout>
          <tr:messages/>
        </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
