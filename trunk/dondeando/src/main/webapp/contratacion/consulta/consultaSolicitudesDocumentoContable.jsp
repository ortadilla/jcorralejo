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
      <trh:head title="Gestión de solicitudes de documento contable">
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
          <tr:panelHeader text="Gestión de solicitudes de documento contable"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:messages/>
            <tr:showDetailHeader id="detalle" text="Criterios de búsqueda" disclosed="#{!consultaSolicitudesDocumentoContableBean.haRecuperadoDatos}">
			<tr:panelFormLayout labelWidth="230">
	            <tr:spacer width="10" height="10"/>
				<tr:panelLabelAndMessage label="Fecha solicitud de">
					<tr:panelHorizontalLayout>
						<tr:inputDate simple="true"
							value="#{formBusquedaSolicitudesDocumentoContable.fechaDesde}" />
						<tr:spacer width="10" height="10" />
						<tr:outputText value="a" />
						<tr:spacer width="10" height="10" />
						<tr:inputDate simple="true"
							value="#{formBusquedaSolicitudesDocumentoContable.fechaHasta}" />
						<tr:spacer width="10" height="10" />
					</tr:panelHorizontalLayout>
				</tr:panelLabelAndMessage>
				<tr:panelLabelAndMessage label="Estado">
					<tr:selectOneChoice value="#{formBusquedaSolicitudesDocumentoContable.estado}" unselectedLabel="Todas">
						<f:selectItem itemValue="P" itemLabel="Pendientes"/>
						<f:selectItem itemValue="R" itemLabel="Resueltas"/>
					</tr:selectOneChoice>
				</tr:panelLabelAndMessage>
	            <tr:spacer width="10" height="10"/>
			</tr:panelFormLayout>
            </tr:showDetailHeader>
			<h:panelGroup>
				<tr:panelHorizontalLayout halign="right">
					<tr:commandButton text="Buscar"
						action="#{consultaSolicitudesDocumentoContableBean.encontrar}" />
				</tr:panelHorizontalLayout>
			</h:panelGroup>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" var="solicitud" rowSelection="single"
          			value="#{tablaSolicitudesDocumentoContableBean.modelo}" autoSubmit="true"
                    selectionListener="#{tablaSolicitudesDocumentoContableBean.eventoSeleccion}"
                    partialTriggers="tblSolicitudes:cmdAceptar tblSolicitudes:cmdRechazar" id="tblSolicitudes" >
            <tr:column  headerText="Expediente">
		 		<tr:commandLink text="#{solicitud.numeroExpediente}" action="#{verExpedientePorIdAccion.ejecuta}">
		 			<tr:setActionListener to="#{verExpedientePorIdAccion.idExpediente}" from="#{solicitud.identificadorExpediente}"/>
				</tr:commandLink>
            </tr:column>
            <tr:column  headerText="Fecha solicitud">
              <tr:outputText value="#{solicitud.fechaSolicitud}"/>
            </tr:column>
            <tr:column  headerText="Tipo solicitud">
              <tr:outputText value="#{solicitud.tipoSolicitud}"/>
            </tr:column>
            <tr:column  headerText="Centro">
              <tr:outputText value="#{solicitud.centro}" >
              </tr:outputText>
            </tr:column>
			<tr:column  headerText="Estado">
              <tr:outputText value="#{solicitud.estado}" />
            </tr:column>
            <tr:column  headerText="Usuario">
              <tr:outputText value="#{solicitud.usuario}" />
            </tr:column>

            <f:facet name="actions">
            <tr:panelButtonBar>
              <tr:commandButton text="Aceptar" id="cmdAceptar" action="#{aceptarSolicitudDocumentoContableAccion.ejecuta}"/>
              <tr:commandButton text="Rechazar" id="cmdRechazar" action="#{rechazarSolicitudDocumentoContableAccion.ejecuta}"
                        returnListener="#{rechazarSolicitudDocumentoContableSinComprobarEstadoAccion.ejecutarRetornoPopUp}"
                        useWindow="true" windowHeight="300" windowWidth="700"
                        partialSubmit="true" >
		 			<tr:setActionListener to="#{pageFlowScope.titulo}"
		 					from="#{'Rechazo de la solictud'}"/>
		 			<tr:setActionListener to="#{pageFlowScope.etiqueta}"
		 					from="#{'Motivo:'}"/>
              </tr:commandButton>
            </tr:panelButtonBar>
            </f:facet>
			<f:facet name="detailStamp">
			  <tr:panelGroupLayout>
	              <tr:outputText value="Motivo: #{solicitud.motivo}"/>
	              <tr:outputText value="CCA: #{solicitud.informacionAdicional}"/>
              </tr:panelGroupLayout>
			</f:facet>
          </tr:table>
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