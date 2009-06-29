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
      <trh:head title="#{consultaLogBean.descripcionAcceso}">
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
          <tr:panelHeader text="#{consultaLogBean.descripcionAcceso}"/>
          <tr:spacer width="10" height="10"/>
          <tr:messages />
          <tr:panelBox text="Datos Expediente" background="medium">
           	<tr:panelLabelAndMessage label="Número del Expediente: ">
	          	<tr:outputText value="#{consultaLogBean.codigoExpediente}"/>
	        </tr:panelLabelAndMessage>
          	<tr:panelLabelAndMessage label="Nº Accesos a Expediente: ">
	          	<tr:outputText value="#{consultaLogBean.numeroTotalAccesos}"/>
	        </tr:panelLabelAndMessage>
          </tr:panelBox>
          <tr:spacer height="10"/>
              <tr:table emptyText="No items were found" width="800"
                        var="fila"
                        value="#{consultaLogBean.accesosExpediente}" rows="15"
                        >
              	<tr:column  headerText="Nº Accesos">
                	<tr:outputText value="#{fila.numeroAccesos}"/>
	            </tr:column>
	            <tr:column  headerText="Último Acceso">
                  <tr:outputText value="#{fila.fechaUltimoAcceso}">
					<tr:convertDateTime pattern="dd/MM/yyyy HH:mm:ss"/>
                  </tr:outputText>
                </tr:column>
                <tr:column  headerText="Nombre">
                  <tr:outputText value="#{fila.nombre}">
                  </tr:outputText>
                </tr:column>
                <tr:column  headerText="Teléfono">
                  <tr:outputText value="#{fila.telefono}">
                  </tr:outputText>
                </tr:column>
                <tr:column  headerText="Correo">
                  <tr:outputText value="#{fila.correo}">
                    </tr:outputText>
                </tr:column>
                <tr:column  headerText="#{consultaLogBean.campoOpcional}">
                  <tr:outputText value="#{fila.campoAdicional}"/>
                </tr:column>
                <tr:column  headerText="Area Funcional">
                  <tr:outputText value="#{fila.areaFuncional}"/>
                </tr:column>
				<f:facet name="detailStamp">
					<tr:panelGroupLayout>
					<tr:iterator value="#{fila.fechasAcceso}" var="fecha">
					<tr:panelHorizontalLayout>
						<tr:spacer width="20"/>
						  <tr:outputFormatted value="#{fecha}" styleUsage="pageStamp">
						  <tr:convertDateTime pattern="dd/MM/yyyy HH:mm:ss"/>
						</tr:outputFormatted>
					</tr:panelHorizontalLayout>
	                </tr:iterator>
	                </tr:panelGroupLayout>
            	</f:facet>
            </tr:table>
          <tr:commandButton text="Volver" action="#{consultaLogBean.volver}"/>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>