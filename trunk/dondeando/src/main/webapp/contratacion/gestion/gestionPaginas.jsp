<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Gesti�n de p�ginas">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
      </trh:head>
      <trh:body>

<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
            <tr:statusIndicator id="indicador">
                <f:facet name="busy">
                    <f:verbatim>
                            <div id="divEspera">
                                <p style="margin-top: 60px; text-align:center; width: 100%;">
                                [    Cargando datos, por favor espere...    ]</p>
                            </div>
                    </f:verbatim>
                </f:facet>
                <f:facet name="ready">
                </f:facet>
            </tr:statusIndicator>

			<trh:script source="/include/libreriaGEOS.js">
			                <!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

        <h:form onsubmit="bloquearPantalla(this)">
          <tr:panelHeader text="Gesti�n de p�ginas"/>
          <tr:spacer width="10" height="10"/>
          <tr:messages></tr:messages>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout labelWidth="250">

              <tr:panelLabelAndMessage label="Descripci�n">
                <tr:inputText value="#{gestionPaginasBean.descripcion}" maximumLength="250" simple="true"
                columns="100" rows="2"/>
              </tr:panelLabelAndMessage>
            	<tr:spacer height="20" width="10"/>

              </tr:panelFormLayout>

          </tr:panelBox>

            <tr:separator/>
           <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Aceptar"
                             action="#{gestionPaginasBean.actualizarYSalir}" />
            <tr:commandButton text="Cancelar"
                             action="#{gestionPaginasBean.cancelar}" />

          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>