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
      <trh:head title="Gesti�n de centros">
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
          <tr:panelHeader text="Gesti�n de centros"/>
          <tr:messages></tr:messages>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:showDetailHeader text="Criterios de b�squeda" disclosed="#{!consultaCentroBean.haRecuperadoDatos}">
              <tr:panelFormLayout>
                <f:facet name="footer">
                  <h:panelGroup>
                    <tr:panelHorizontalLayout halign="right">
                      <tr:commandButton text="Buscar" action="#{consultaCentroBean.encontrar}" />
                    </tr:panelHorizontalLayout>
                  </h:panelGroup>
                </f:facet>
                <tr:panelLabelAndMessage label="Nombre del campo">
                  <tr:inputText simple="true"/>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
            </tr:showDetailHeader>
          </tr:panelBox>
          <tr:table emptyText="No items were found" width="800" selectionListener="#{tablaCentroBean.eventoSeleccion}"
          	var="fila" autoSubmit="true" rowSelection="single"
          	value="#{tablaCentroBean.modelo}"
          	partialTriggers="cbBaja" >

            <tr:column  headerText="Nombre">
              <tr:outputText value="#{fila.nombre}"/>
            </tr:column>
            <f:facet name="actions">
             <tr:panelButtonBar>

              <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}" />
              <tr:commandButton text="Modificar"  action="#{modificarCentro.ejecuta}" >
                </tr:commandButton>
                <tr:commandButton id="cbBaja" text="Baja" action="#{borrarCentro.ejecuta}"
                                  />
             </tr:panelButtonBar>

            </f:facet>
          </tr:table>
          <tr:commandButton text="Men� principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>