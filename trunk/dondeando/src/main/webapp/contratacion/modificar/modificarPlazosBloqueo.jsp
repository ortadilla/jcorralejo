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
      <trh:head title="Administración de plazos de bloqueo">
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
          <tr:panelHeader text="Administración de plazos de bloqueo"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:spacer width="10" height="10"/>
                  <tr:panelHorizontalLayout halign="right">
                    <tr:commandButton text="Actualizar todos" action="#{modificarPlazosBloqueo.ejecuta}" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>
              <tr:table emptyText="No items were found" width="100%" rows="20"
                    var="fila"
                    value="#{tablaPlazosBloqueoBean.modelo}" >
                <tr:column
                           rowHeader="false" width="60%">
                  <f:facet name="header">
                    <tr:panelHorizontalLayout halign="center">
                      <tr:outputText value="Modelo de pliego"/>
                    </tr:panelHorizontalLayout>
                  </f:facet>
                  <tr:panelHorizontalLayout>
                    <tr:spacer width="10" height="10"/>
                    <tr:outputText value="#{fila.descripcion}" />
                  </tr:panelHorizontalLayout>
                </tr:column>
                <tr:column  width="20%">
                  <f:facet name="header">
                    <h:panelGroup>
                      <tr:panelBorderLayout>
                        <f:facet name="top"/>
                        <f:facet name="bottom"/>
                        <f:facet name="start"/>
                        <f:facet name="end"/>
                        <f:facet name="innerBottom">
                          <tr:panelHorizontalLayout halign="center">
                            <tr:outputText value="(en días naturales)"/>
                          </tr:panelHorizontalLayout>
                        </f:facet>
                        <f:facet name="innerTop">
                          <tr:panelHorizontalLayout halign="center">
                            <tr:outputText value="Plazo de inactividad"/>
                          </tr:panelHorizontalLayout>
                        </f:facet>
                      </tr:panelBorderLayout>
                    </h:panelGroup>
                  </f:facet>
                  <tr:panelHorizontalLayout halign="center">
                    <tr:inputText rows="1" columns="5"
                                  value='#{fila.plazoInactividad}'/>
                  </tr:panelHorizontalLayout>
                </tr:column>
                <tr:column  width="20%">
                  <f:facet name="header">
                    <h:panelGroup>
                      <tr:panelBorderLayout>
                        <f:facet name="top"/>
                        <f:facet name="bottom"/>
                        <f:facet name="start"/>
                        <f:facet name="end"/>
                        <f:facet name="innerBottom">
                          <h:panelGroup>
                            <tr:panelHorizontalLayout halign="center">
                              <tr:outputText value="(en días naturales)"/>
                            </tr:panelHorizontalLayout>
                          </h:panelGroup>
                        </f:facet>
                        <f:facet name="innerTop">
                          <h:panelGroup>
                            <tr:panelHorizontalLayout halign="center">
                              <tr:outputText value="Plazo de adjudicación"/>
                            </tr:panelHorizontalLayout>
                          </h:panelGroup>
                        </f:facet>
                      </tr:panelBorderLayout>
                    </h:panelGroup>
                  </f:facet>
                  <tr:panelHorizontalLayout halign="center">
                    <tr:inputText rows="1" columns="5"
                                  value="#{fila.plazoAdjudicacion}"/>
                  </tr:panelHorizontalLayout>
                </tr:column>
              </tr:table>
            </tr:panelFormLayout>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Menú principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>