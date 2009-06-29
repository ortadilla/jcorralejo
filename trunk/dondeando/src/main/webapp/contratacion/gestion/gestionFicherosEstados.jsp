<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Gestión de Documentación">
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
			<tr:messages />
          <tr:spacer width="10" height="10"/>
          	<tr:panelBox text="Añadir estado obligatorio" background="medium" >
              <tr:panelFormLayout>

                <tr:panelLabelAndMessage label="Estado">
                  <tr:panelHorizontalLayout>
                  	<tr:selectOneChoice value="#{gestionFicherosEstados.codigoEstadoOrigen}">
						<f:selectItems value="#{gestionFicherosEstados.estadosExpedientes}" />
					</tr:selectOneChoice>
                  </tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>

                <tr:commandButton id="insertarEstado" action="#{gestionFicherosEstados.insertarEstado}" text="Añadir" partialSubmit="true"/>
              </tr:panelFormLayout>
			</tr:panelBox>

          <tr:spacer width="10" height="10"/>

          <tr:panelBox text="Listado de Estados obligatorios" background="medium" >
            <tr:panelFormLayout partialTriggers="insertarEstado">
            <tr:table emptyText="No se ha encontrado estados obligatorios disponibles" var="estado" width="100%"
                   value="#{gestionFicherosEstados.tablaEstados}" partialTriggers="insertarEstado">
				<tr:column headerText="Estado">
					<tr:outputText value="#{estado.codigoEstadoOrigen}"/>
				</tr:column>
				<tr:column headerText="Accion">
					<tr:commandLink action="#{gestionFicherosEstados.parametrosEliminarEstado}" onclick="if(!confirm('¿Desea eliminar el rango?')) return false">
						<tr:image source="../imagenes/eliminar.gif" shortDesc="Eliminar Rango"/>
						<f:param value="#{estado.codigoEstadoOrigen}" name="codigoEstado"/>
					</tr:commandLink>
				</tr:column>
            </tr:table>
            </tr:panelFormLayout>
          </tr:panelBox>

          <tr:spacer width="10" height="10"/>

	<tr:panelHorizontalLayout halign="center">
                <h:panelGroup>
		           <tr:panelHorizontalLayout halign="center">
        		    <tr:commandButton text="Aceptar" action="#{gestionFicherosEstados.aceptar}" />
		            <tr:commandButton text="Cancelar" action="#{gestionFicherosEstados.cancelar}" />
    		      </tr:panelHorizontalLayout>
                </h:panelGroup>
    </tr:panelHorizontalLayout>


		</h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>