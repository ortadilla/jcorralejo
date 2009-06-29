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
      <trh:head title="Documentaci�n asociada al expediente">
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

        <tr:form usesUpload="true" onsubmit="bloquearPantalla(this)">
          <tr:panelHeader text="Documentaci�n asociada al expediente"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:panelHorizontalLayout halign="right">
                    <tr:commandButton text="Cerrar ventana" action="#{consultaDocumentacionExpediente.cerrarVentana}" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>

              <tr:table var="documentacion" value="#{consultaDocumentacionExpediente.listadoDocumentacion}"
              emptyText="No existe documentaci�n asociada al expediente"
        	   rows="5" >

                <f:facet name="header">
                    <tr:outputText value="Documentaci�n asociada al expediente" />
                </f:facet>

       			<tr:column>
			 		<tr:commandLink onclick="noBloquearPantalla()" text="#{documentacion.descripcion}" actionListener="#{muestraDocumentacion.descargaFichero}">
						<f:param value="#{documentacion.id}" name="id"/>
						<f:param value="#{documentacion.nombre}" name="nombreFichero"/>
					</tr:commandLink>
       			</tr:column>

			  </tr:table>

            </tr:panelFormLayout>
          </tr:panelBox>
        </tr:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>