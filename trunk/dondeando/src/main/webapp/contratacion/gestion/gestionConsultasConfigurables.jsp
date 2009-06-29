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
      <trh:head title="Gestion Momentos">
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
          <tr:panelHeader text="Gestion Momentos"/>
          <tr:messages/>
          <tr:panelBox background="medium" inlineStyle="width:100%" >
            <tr:panelFormLayout>
             <tr:panelLabelAndMessage label="Consulta:" > <tr:outputText inlineStyle="color:blue"
          			 value="#{gestionConsultasConfigurablesBean.consultaConfigurable}" /> </tr:panelLabelAndMessage>
               <tr:separator/>
              <tr:panelLabelAndMessage label="Rol">
                <tr:selectOneChoice simple="true" value="#{gestionConsultasConfigurablesBean.rol}"
                	unselectedLabel=" "  >
                	 <f:selectItems value="#{gestorListaPerfilesWF.listaElementos}" />
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Estados">
                <tr:spacer width="10" height="10"/>
                <tr:panelHorizontalLayout>
                  <tr:showDetail disclosed="true">
              		<tr:table emptyText="---" 
              				  id="tabEstados"
              				  var="estado"
              				  partialTriggers="cbSelEstados"
              				  value="#{selectorEstadosConsultasConfigurables.seleccionados}"
                              width="500" >
			            <tr:column  headerText="Seleccionados">
				              <tr:outputText value="#{estado.label}"/>
			            </tr:column>
					</tr:table>					
                  </tr:showDetail>
                  <tr:spacer width="10" height="10"/>
                  <tr:commandButton text="Seleccionar"
                                    action="#{selectorEstadosConsultasConfigurables.seleccionar}"
                                    useWindow="true"
                                    returnListener="#{selectorEstadosConsultasConfigurables.modificaListaSeleccionados}"
                                    windowHeight="345" windowWidth="355"
                                    id="cbSelEstados" partialSubmit="true" >
						<tr:setActionListener from="#{gestorListaEstadosWorkflow.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
						<tr:setActionListener from="#{'Seleccionar Estados'}" to="#{pageFlowScope.titulo}" />
						<tr:setActionListener from="#{gestionConsultasConfigurablesBean.estados}" to="#{pageFlowScope.elementosSeleccionados}" />
                  </tr:commandButton>
                </tr:panelHorizontalLayout>
              </tr:panelLabelAndMessage>



            </tr:panelFormLayout>
          </tr:panelBox>

          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
          	<tr:commandButton text="Aceptar" useWindow="false"
                                  action="#{gestionConsultasConfigurablesBean.aceptar}"/>
            <tr:commandButton text="cancelar" immediate="true"
                              action="#{botonCerrarPopUpBean.volver}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
