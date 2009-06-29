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
      <trh:head title="Selecci�n de clasificaciones de art�culo">
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
          <tr:panelHeader text="Selecci�n de clasificaciones de art�culo"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" inlineStyle="width: 100%;">
            <tr:panelFormLayout>
              <tr:table value="#{gestionClasificacionSeleccionableBean.componentesClasificacionesSeleccionables}"
              			var="clasificacion"
              			id="idTablaClasifSeleccionable">
              	<tr:column headerText="Clasificaci�n">
              		<tr:outputText id="idClasificacion" value="#{clasificacion.label}"/>
              	</tr:column>
              	<tr:column headerText="Seleccionable">
              		<tr:selectBooleanCheckbox value="#{clasificacion.value}" />
              	</tr:column>
              </tr:table>
              <tr:spacer height="10"/>
                   <tr:commandButton text="Actualizar selecciones"
                    		          action="#{gestionClasificacionSeleccionableBean.actualizaClasificacionesSeleccionables}"/>
             </tr:panelFormLayout>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
      		<tr:commandButton text="Men� principal"
                        action="#{botonMenuAdminBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>