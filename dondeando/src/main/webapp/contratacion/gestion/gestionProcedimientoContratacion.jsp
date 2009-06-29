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
      <trh:head title="Gestión de procedimientos de contratación">
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
          <tr:panelHeader text="Gestión de procedimientos de contratación"/>
          <tr:messages />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
            <tr:panelFormLayout>
              <f:facet name="footer">
                <h:panelGroup>
                  <tr:panelHorizontalLayout halign="center">
                    <tr:commandButton text="Aceptar" action="#{gestionProcedimientoContratacionBean.actualizarYSalir}"
                                      />
                    <tr:commandButton text="Cancelar" action="#{gestionProcedimientoContratacionBean.cancelar}" immediate="true" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
              </f:facet>
              <tr:panelLabelAndMessage label="Código">
                <tr:inputText value="#{gestionProcedimientoContratacionBean.codigo}" simple="true" maximumLength="1" />
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="descripción">
                <tr:inputText value="#{gestionProcedimientoContratacionBean.descripcion}"  simple="true" maximumLength="250" />
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Formas de adjudicacion">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
              		<tr:table emptyText="---" 
              				  id="tabFormasAdjudicacion"
              				  var="formaAdjudicacion"
              				  partialTriggers="cbSelFormasAdjudicacion"
              				  value="#{selectorFormasAdjudicacionProcedimientosContratacion.seleccionados}"
                              width="500" >
			            <tr:column  headerText="Seleccionados">
				              <tr:outputText value="#{formaAdjudicacion.label}"/>
			            </tr:column>
					</tr:table>					
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorFormasAdjudicacionProcedimientosContratacion.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorFormasAdjudicacionProcedimientosContratacion.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelFormasAdjudicacion" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaFormaAdjudicacion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Formas de Adjudicacion'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionProcedimientoContratacionBean.idsFormasAdjudicacion}" to="#{pageFlowScope.elementosSeleccionados}" />
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
              				  value="#{selectorProcedimientoComplementarioProcedimientosContratacion.seleccionados}"
                              width="500" >
			            <tr:column  headerText="Seleccionados">
				              <tr:outputText value="#{procedimientoComplementario.label}"/>
			            </tr:column>
					</tr:table>					
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorProcedimientoComplementarioProcedimientosContratacion.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorProcedimientoComplementarioProcedimientosContratacion.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelProcedimientosComplementarios" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaProcedimientoComplementarioSeleccion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Procedimiento complementario de selección'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionProcedimientoContratacionBean.idsProcedimientosComplementariosSeleccion}" to="#{pageFlowScope.elementosSeleccionados}" />
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