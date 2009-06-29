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
      <trh:head title="Gestión de tipos de contrato">
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
          <tr:panelHeader text="Gestión de tipos de contrato"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:panelHorizontalLayout halign="center">
                    <tr:commandButton text="Aceptar" action="#{gestionTipoContratoBean.actualizarYSalir}"
                                      />
                    <tr:commandButton text="Cancelar" action="#{gestionTipoContratoBean.cancelar}" immediate="true" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>
              <tr:panelLabelAndMessage label="Código">
                <tr:inputText value="#{gestionTipoContratoBean.codigo}" simple="true" maximumLength="3" />
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="descripción">
                <tr:inputText value="#{gestionTipoContratoBean.descripcion}"  simple="true" maximumLength="250"/>
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Procedimientos de contratación">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
              		<tr:table emptyText="---" 
              				  id="tabProcedimientosContratacion"
              				  var="procedimientoContratacion"
              				  partialTriggers="cbSelProcedimientosContratacion"
              				  value="#{selectorProcedimientosContratacionTiposContrato.seleccionados}"
                              width="500" >
			            <tr:column  headerText="Seleccionados">
				              <tr:outputText value="#{procedimientoContratacion.label}"/>
			            </tr:column>
					</tr:table>					
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorProcedimientosContratacionTiposContrato.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorProcedimientosContratacionTiposContrato.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelProcedimientosContratacion" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaProcedimientoContratacion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Procedimientos de Contratación'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionTipoContratoBean.idsProcedimientosContratacion}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>



              <tr:panelLabelAndMessage label="Procedimientos complementarios de selección">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
              		<tr:table emptyText="---" 
              				  id="tabProcedimientosComplementarios"
              				  var="procedimientoComplementario"
              				  partialTriggers="cbSelProcedimientosComplementarios"
              				  value="#{selectorProcedimientoComplementarioTiposContrato.seleccionados}"
                              width="500" >
			            <tr:column  headerText="Seleccionados">
				              <tr:outputText value="#{procedimientoComplementario.label}"/>
			            </tr:column>
					</tr:table>					
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorProcedimientoComplementarioTiposContrato.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorProcedimientoComplementarioTiposContrato.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelProcedimientosComplementarios" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaProcedimientoComplementarioSeleccion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Procedimiento complementario de selección'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionTipoContratoBean.idsProcedimientosComplementariosSeleccion}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>


            </tr:panelFormLayout>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>