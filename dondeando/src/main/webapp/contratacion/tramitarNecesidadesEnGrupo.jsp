<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"

          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:ctr="http://contratacion/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Necesidades de contratación">
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

        <h:form onsubmit="bloquearPantalla(this)" id="formTramitarNecesidadEnGrupo">
          <tr:panelHeader text="Tramitar necesidades en grupo" />
          <tr:messages  />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox id="pnlNec" background="medium" 
                       text="Necesidades de contratación" inlineStyle="width:100%">

          <tr:table emptyText="No se encontraron necesidades de contratación"
                      width="100%" rows="10" var="fila"
                      varStatus="varStaus"
                      value="#{tramitarNecesidadesEnGrupoBean.necesidadesTramitacionEnGrupo}"
                      id="tabNecesidades" >
              		<tr:column  headerText="Clasificación">
                      <tr:outputText id="outCodArt" value="#{fila.codigoArticulo}"  />
                    </tr:column>
                    <tr:column  headerText="Nombre">
                      <tr:outputText id="outDescArt" value="#{fila.descripcionArticulo}"/>
                    </tr:column>
                    <tr:column  headerText="Cantidad">
                      <tr:outputText id="outCanti" value="#{fila.cantidad}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6" muestraMoneda="false" /></tr:outputText>
                    </tr:column>
                     <tr:column
                               headerText="Presupuesto"   align="right" noWrap="true">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outSIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outPIVA" value="#{fila.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outCIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                    </tr:column>
                     <tr:column  headerText="Estado">
                      <tr:outputText id="outEstado" value="#{fila.estado}"/>
                    </tr:column>
              </tr:table>
              <tr:separator></tr:separator>

          </tr:panelBox>

		  <tr:group id="tramitacionWorkflow">
            		<tr:panelBox id="pnlTramit" background="medium" text="Tramitación"
						inlineStyle="width: 100%;">
						<tr:panelHeader text="Trámites posibles" />
						<tr:selectOneRadio id="sorTramite" value="#{workflowNecesidadesEnGrupoBean.idTramite}">
							<f:selectItems value="#{workflowNecesidadesEnGrupoBean.transicionesPosibles}" />
						</tr:selectOneRadio>
						<tr:panelFormLayout>
							<f:facet name="footer">
								<tr:panelHorizontalLayout halign="right">
									<tr:commandButton id="btnTramitar" text="Tramitar"
										action="#{tramitarNecesidadesEnGrupoBean.tramitar}">
									</tr:commandButton>
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:inputText id="intObs" label="Observaciones" rows="4" columns="60"
								value="#{workflowNecesidadesEnGrupoBean.observaciones}"/>
						</tr:panelFormLayout>
					</tr:panelBox>
          </tr:group>

		<tr:separator/>


          <tr:panelHorizontalLayout halign="center">
          	<tr:commandButton id="btnVolver" text="Volver" action="#{tramitarNecesidadesEnGrupoBean.cancelar}" immediate="true" />
            <tr:commandButton id="btnMenPrin" text="Menú principal"
                              action="#{volverMenuPrincipalBean.ejecuta}" immediate="true"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
