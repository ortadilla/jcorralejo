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
      <trh:head title="Gestión de condiciones de flujo">
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
          <tr:panelHeader text="Gestión de condiciones de flujo"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium"  >
           <tr:panelFormLayout>
              <tr:panelLabelAndMessage label="Descripción">
                <tr:inputText value="#{gestionCondicionesFlujoBean.descripcion}" simple="true"
                              columns="60" rows="2"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Definida 'en positivo'" >
		      	<tr:selectBooleanCheckbox simple="true" value="#{gestionCondicionesFlujoBean.caminoPermitido}"
		      	autoSubmit="true" id="ispositivo" />
              </tr:panelLabelAndMessage >
              <tr:panelLabelAndMessage label="Impide seleccionar transición" >
		      	<tr:selectBooleanCheckbox simple="true" value="#{gestionCondicionesFlujoBean.condicionSeleccionarTransicion}"
		      	autoSubmit="true" id="ispre" />
              </tr:panelLabelAndMessage >

              <tr:panelHorizontalLayout partialTriggers="ispre">
              <tr:switcher facetName="#{gestionCondicionesFlujoBean.condicionSeleccionarTransicion?'nada':'mensaje'}">
              	<f:facet name="mensaje">
              		<tr:panelLabelAndMessage label="Mensaje">
		      			<tr:inputText value="#{gestionCondicionesFlujoBean.mensaje}" simple="true"
                              columns="60" rows="2"/>
              	   </tr:panelLabelAndMessage>
		      	</f:facet>

		      </tr:switcher>
		      </tr:panelHorizontalLayout>
              <tr:panelLabelAndMessage label="Modelos de pliego">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---" id="tabModelosPliego" var="modeloPliego"
                              partialTriggers="cbSelModelosPliego" 
                              value="#{selectorModelosPliegoCondicionesFlujo.seleccionados}"
                              width="500" >
                      <tr:column  headerText="Descripción">
                        <tr:outputText value="#{modeloPliego.label}"/>
                      </tr:column>
                    </tr:table>
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>

                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorModelosPliegoCondicionesFlujo.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorModelosPliegoCondicionesFlujo.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelModelosPliego" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaModeloPliego.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Modelos de Pliego'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionCondicionesFlujoBean.idsModelosPliegoSeleccionados}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>

                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Tipos de fiscalización">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:selectOneRadio unselectedLabel="No aplica" simple="true"
                                       value="#{gestionCondicionesFlujoBean.tipoFicalizacion}">
                      <f:selectItems value="#{gestorListaTipoFiscalizacion.listaElementos}"/>
                    </tr:selectOneRadio>
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Tipos de centro">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---" id="tabTiposOrganoGestor" var="tipoOrganoGestor"
                              partialTriggers="cbSelTiposOrganoGestor" 
                              value="#{selectorTiposCentroCondicionesFlujo.seleccionados}"
                              width="500" >
                      <tr:column  headerText="Descripción">
                        <tr:outputText value="#{tipoOrganoGestor.label}"/>
                      </tr:column>
                    </tr:table>
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorTiposCentroCondicionesFlujo.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorTiposCentroCondicionesFlujo.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelTiposOrganoGestor" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaTipoOrganoGestor.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Modelos de Pliego'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionCondicionesFlujoBean.idsTiposOrganoGestorSeleccionados}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Centros">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout partialTriggers="cbSelCentros">
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---" id="tabCentros" var="organoGestor"
                              partialTriggers="cbSelCentros" 
                              value="#{selectorCentrosCondicionesFlujo.seleccionados}"
                              width="500" >
                      <tr:column  headerText="Descripción"
                                 width="500">
                        <tr:outputText value="#{organoGestor.label}"/>
                      </tr:column>
                    </tr:table>
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorCentrosCondicionesFlujo.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorCentrosCondicionesFlujo.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelCentros" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaOrganoGestor.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Organos Gestores'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionCondicionesFlujoBean.idsOrganosGestoresSeleccionados}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Ocurrencias de transición">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---" id="tabOcurrenciaTransicion" rowSelection="single"
                              var="transicionPrecedente"
                              partialTriggers="cbAgrOcurrenciasTransicion cbBorOcurrenciasTransicion"
                              width="700"
                              value="#{gestionCondicionesFlujoBean.modeloOcurrenciasTransicion}">

                      <tr:column  headerText="Estado inicial">
                        <tr:outputText value="#{transicionPrecedente.descripcionEstadoInicial}"/>
                      </tr:column>
                      <tr:column  headerText="Fue a">
                        <tr:outputText value="#{transicionPrecedente.flagSiNo}"/>
                      </tr:column>
                      <tr:column  headerText="Estado final">
                        <tr:outputText value="#{transicionPrecedente.descripcionEstadoFinal}"/>
                      </tr:column>
                      <f:facet name="actions">
                      	<tr:panelButtonBar>
                            <tr:commandButton id="cbBorOcurrenciasTransicion"
                                            text="Borrar"
                                            actionListener="#{botonBorraSelecionadoTabla.borraSeleccionado}"
                                            onclick="if(!confirm('¿Desea borrar la transición?')) return false"/>
                    	</tr:panelButtonBar>
                      </f:facet>
                    </tr:table>
                    <tr:commandButton text="Agregar"
                                      action="#{gestionCondicionesFlujoBean.excluirOcurrenciasTransicion}"
                                      useWindow="true"
                                      launchListener="#{gestionCondicionesFlujoBean.parametrosSelOcurrenciasTransicion}"
                                      returnListener="#{gestionCondicionesFlujoBean.retornaSelOcurrenciasTransicion}"
                                      windowHeight="345" windowWidth="355"
                                      id="cbAgrOcurrenciasTransicion"
                                      partialSubmit="true"/>
                  </tr:showDetail>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Ocurrencias de tramite">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---" rowSelection="single"
                    		  id="tabOcurrenciaTramite" var="tramitePrecedente"
                              partialTriggers="cbAgrOcurrenciasTramite cbBorOcurrenciasTramite" width="700"
                              value="#{gestionCondicionesFlujoBean.modeloOcurrenciasTramite}" >

                      <tr:column  headerText="">
                        <tr:outputText value="#{tramitePrecedente.flagSiNo}"/>
                      </tr:column>
                      <tr:column  headerText="Estuvo en estado">
                        <tr:outputText value="#{tramitePrecedente.descripcionEstado}"/>
                      </tr:column>
                      <f:facet name="actions">
                      		<tr:panelButtonBar>
                                <tr:commandButton id="cbBorOcurrenciasTramite" text="Borrar"
                                            actionListener="#{botonBorraSelecionadoTabla.borraSeleccionado}"
				            				onclick="if(!confirm('¿Desea borrar el trámite?')) return false"/>
				            </tr:panelButtonBar>
                      </f:facet>
  		            </tr:table>
                    <tr:commandButton text="Agregar"
                                      action="#{gestionCondicionesFlujoBean.excluirOcurrenciasTramite}"
                                      useWindow="true"
                                      launchListener="#{gestionCondicionesFlujoBean.parametrosSelOcurrenciasTramite}"
                                      returnListener="#{gestionCondicionesFlujoBean.retornaSelOcurrenciasTramite}"
                                      windowHeight="345" windowWidth="355"
                                      id="cbAgrOcurrenciasTramite"
 	                                      partialSubmit="true"/>
                  </tr:showDetail>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Anuncios confeccionados" rendered="#{gestionPendientes.visible}">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
                    <tr:table emptyText="---"
                              id="tabAnunciosConfeccionados"
                              partialTriggers="cbSelAnunciosConfeccionados"
                              width="500" >
                      <tr:column  headerText="col1">
                        <tr:outputText value="#{row.col1}"/>
                      </tr:column>
                      <tr:column  headerText="col2">
                        <tr:outputText value="#{row.col2}"/>
                      </tr:column>
                    </tr:table>
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{gestionCondicionesFlujoBean.excluirAnunciosConfeccionados}"
                                    useWindow="true"
                                    launchListener="#{gestionCondicionesFlujoBean.parametrosSelAnunciosConfeccionados}"
                                    returnListener="#{gestionCondicionesFlujoBean.retornaSelAnunciosConfeccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelAnunciosConfeccionados"
                                    partialSubmit="true"/>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>
            </tr:panelFormLayout>
          </tr:panelBox>
          <tr:separator />
			<tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="center">
                <tr:commandButton text="Aceptar" action="#{gestionCondicionesFlujoBean.actualizarYSalir}"/>
                <tr:commandButton text="Cancelar" action="#{gestionCondicionesFlujoBean.cancelar}" />
              </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>