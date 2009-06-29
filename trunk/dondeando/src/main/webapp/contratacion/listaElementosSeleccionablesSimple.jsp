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
          <tr:panelBox  background="medium" inlineStyle="width: 100%;" text="#{listaSeleccionSimple.titulo}" >
            <tr:panelFormLayout inlineStyle="width: 100%;">
              <f:facet name="footer"/>
              <tr:spacer width="10" height="10"/>
              <tr:table width="100%" rowSelection="single"
              emptyText="No items were found" rows="25" var="lista" binding="#{listaSeleccionSimple.tabla}"
                        value="#{listaSeleccionSimple.elementosSeleccionables}">
                <tr:column sortProperty="label"
                           headerText="Descripcion">
                           <tr:outputText value="#{lista.label}"></tr:outputText>
                  <tr:outputText/>
                </tr:column>

              </tr:table>
              <tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="center">
                <tr:commandButton text="Aceptar" useWindow="false"
                                  action="#{listaSeleccionSimple.acceptar}"/>
                <tr:commandButton text="Cancelar"
                                  action="#{listaSeleccionSimple.cancelar}"
                                  />
              </tr:panelHorizontalLayout>
            </tr:panelFormLayout>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>