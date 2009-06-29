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

          <tr:panelBox background="medium"  inlineStyle="width:100%" >
          		<tr:panelLabelAndMessage label="Elemento:" > <tr:outputText inlineStyle="color:blue"  value="#{cambiarMomentosElementoBean.elemento.descripcion}" /> </tr:panelLabelAndMessage>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" rows="20"
                    selectionListener="#{tablaElementosConfigurablesPorMomentoBean.eventoSeleccion}"
                    selectedRowKeys="#{tablaElementosConfigurablesPorMomentoBean.seleccionadas}"
                    value="#{tablaElementosConfigurablesPorMomentoBean.modelo}" autoSubmit="true"
                    sortListener="#{tablaElementosConfigurablesPorMomentoBean.eventoOrdenacion}"
                    first="#{tablaElementosConfigurablesPorMomentoBean.filaInicial}"
                    rangeChangeListener="#{tablaElementosConfigurablesPorMomentoBean.eventoCambioRango}"
                    var="linea" partialTriggers="cbSelEstadosAsociados tabla:borrar" id="tabla" >
            <!--
             	<tr:column  headerText="descripcion" sortable="true" sortProperty="descripcion">
            		<tr:outputText value="#{linea.descripcion}"  />
            	</tr:column>
             -->
            <tr:column  headerText="Momento">
              <tr:column  headerText="Rol" sortable="true" sortProperty="rol">
            		<tr:outputText value="#{linea.rol}" />
            	</tr:column>
            	<tr:column  headerText="Estado" sortable="true" sortProperty="estado">
            		<tr:outputText value="#{linea.estado}"  />
            	</tr:column>


            </tr:column>
            	<tr:column  >
            		<tr:commandLink id="borrar"
                                            action="#{borrarElementoConfigurablePorMomento.ejecuta}" partialSubmit="true"
                          					shortDesc="Borrar">
                               				 <tr:image source="/imagenes/btnEliminar.gif"
                                            shortDesc="Limpiar"/>
                                <tr:setActionListener from="#{linea}" to="#{tablaElementosConfigurablesPorMomentoBean.seleccionado}"/>
                         </tr:commandLink>
            	</tr:column>

            <f:facet name="actions">
             <tr:panelButtonBar>
             	<tr:commandButton action="#{agregarElementoConfigurableAccion.entrar}" text="Agregar momento"></tr:commandButton>
              </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="volver"
                              action="#{botonCerrarPopUpBean.volver}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
