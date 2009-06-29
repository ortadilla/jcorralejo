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
      <trh:head title="Consulta de Elementos configurables por momento">
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
          <tr:panelHeader text="Elementos configurados por momento"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>

          <tr:panelTabbed id="panelTab" partialTriggers=" " inlineStyle="width:100%" >
              <tr:showDetailItem text="Acciones sobre los expedientes" >

		          <tr:spacer width="10" height="10"/>
		          <tr:table width="100%" rows="100"
		                    value="#{gestionElementosConfigurablesPorMomento.accionesExpediente}"
		                    var="linea"  >

			            <tr:column  headerText="Elemento">
			              <tr:outputText value="#{linea.descripcion}"/>
			            </tr:column>
			            <tr:column  headerText="Detalle">
			              <tr:outputText value="#{linea.ayuda}"/>
			            </tr:column>

			            <tr:column  headerText="" width="100px" >
			              <tr:commandLink text="[ Momentos ]"
										action="#{gestionElementosConfigurablesPorMomento.cambiarMomentos}"
										useWindow="true"
										windowHeight="560" windowWidth="700">
								  <tr:setActionListener from="#{linea}"  to="#{pageFlowScope.elemento}"/>

						  </tr:commandLink>
			            </tr:column>
		           </tr:table>



          	  </tr:showDetailItem>
          	  <tr:showDetailItem text="Necesidades" >

		          <tr:spacer width="10" height="10"/>
		          <tr:table width="100%" rows="100"
		                    value="#{gestionElementosConfigurablesPorMomento.accionesNecesidad}"
		                    var="linea"  >

			            <tr:column  headerText="Elemento">
			              <tr:outputText value="#{linea.descripcion}"/>
			            </tr:column>
			            <tr:column  headerText="Detalle">
			              <tr:outputText value="#{linea.ayuda}"/>
			            </tr:column>

			            <tr:column  headerText="" width="100px">
			              <tr:commandLink text="[ Momentos ]"
										action="#{gestionElementosConfigurablesPorMomento.cambiarMomentos}"
										useWindow="true"
										windowHeight="560" windowWidth="700">
								  <tr:setActionListener from="#{linea}"  to="#{pageFlowScope.elemento}"/>

						  </tr:commandLink>
			            </tr:column>
		           </tr:table>



          	  </tr:showDetailItem>
          	  <tr:showDetailItem text="Anuncio y publicación" >

		          <tr:spacer width="10" height="10"/>
		          <tr:table width="100%" rows="100"
		                    value="#{gestionElementosConfigurablesPorMomento.elementosAnuncioPublicador}"
		                    var="linea"  >

			            <tr:column  headerText="Elemento">
			              <tr:outputText value="#{linea.descripcion}"/>
			            </tr:column>
			            <tr:column  headerText="Detalle">
			              <tr:outputText value="#{linea.ayuda}"/>
			            </tr:column>

			            <tr:column  headerText="" width="100px">
			              <tr:commandLink text="[ Momentos ]"
										action="#{gestionElementosConfigurablesPorMomento.cambiarMomentos}"
										useWindow="true"
										windowHeight="560" windowWidth="700">
								  <tr:setActionListener from="#{linea}"  to="#{pageFlowScope.elemento}"/>

						  </tr:commandLink>
			            </tr:column>
		           </tr:table>



          	  </tr:showDetailItem>

          	   <tr:showDetailItem text="Otros Elementos" >

		          <tr:spacer width="10" height="10"/>
		          <tr:table width="100%" rows="100"
		                    value="#{gestionElementosConfigurablesPorMomento.otros}"
		                    var="linea"  >

			            <tr:column  headerText="Elemento">
			              <tr:outputText value="#{linea.descripcion}"/>
			            </tr:column>
			            <tr:column  headerText="Detalle">
			              <tr:outputText value="#{linea.ayuda}"/>
			            </tr:column>

			            <tr:column  headerText="" width="100px">
			              <tr:commandLink text="[ Momentos ]"
										action="#{gestionElementosConfigurablesPorMomento.cambiarMomentos}"
										useWindow="true"
										windowHeight="560" windowWidth="700">
								  <tr:setActionListener from="#{linea}"  to="#{pageFlowScope.elemento}"/>

						  </tr:commandLink>
			            </tr:column>
		           </tr:table>



          	  </tr:showDetailItem>

          </tr:panelTabbed>

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
