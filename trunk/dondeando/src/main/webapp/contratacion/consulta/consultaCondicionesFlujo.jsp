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
      <trh:head title="Consulta Condiciones de flujo">
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
          <tr:panelHeader text="Condiciones de flujo"/>
          <tr:panelBox text="Datos de transición" background="medium" inlineStyle="width: 100%;">

            <tr:panelLabelAndMessage label="Código: ">
            	<tr:outputText value="#{tablaCondicionesFlujoBean.transicionVista.codigo}" />
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Descripción: ">
            	<tr:outputText value="#{tablaCondicionesFlujoBean.transicionVista.descripcion}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Estado Inicial: ">
            	<tr:outputText value="#{tablaCondicionesFlujoBean.transicionVista.estadosInicio}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Estado Final: ">
            	<tr:outputText value="#{tablaCondicionesFlujoBean.transicionVista.estadosFin}"/>
            </tr:panelLabelAndMessage>
          </tr:panelBox>
          <tr:spacer height="10" />
          <tr:table emptyText="No items were found" width="100%" rows="10"
                    selectionListener="#{tablaCondicionesFlujoBean.eventoSeleccion}"
                    var="condicion" autoSubmit="true" rowSelection="single"
                    value="#{tablaCondicionesFlujoBean.modelo}"
                    partialTriggers="cbBorrar">
            <f:facet name="selection">

            </f:facet>
            <tr:column  headerText="Tipo de condición">
              <tr:outputText value="#{condicion.tipo}"/>
            </tr:column>
            <tr:column  headerText="Impide mostrar transición">
		      	<tr:selectBooleanCheckbox value="#{condicion.condicionSeleccionarTransicion}" disabled="true" />
            </tr:column>
            <tr:column  headerText="Descripción">
              <tr:outputText value="#{condicion.descripcion}"/>
            </tr:column>
            <f:facet name="actions">
               <tr:panelButtonBar>
               <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}" />

                  <tr:commandButton id="cbBorrar" text="Borrar" action="#{borrarCondicionFlujo.ejecuta}"
                  					onclick="if(!confirm('¿Desea borrar la condición de flujo?')) return false"/>
                  <tr:commandButton text="Modificar" action="#{modificarCondicionFlujo.ejecuta}" />
                </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Volver" action="#{tablaCondicionesFlujoBean.volver}"/>
            <tr:commandButton text="Menú principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
		  </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
