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
      <trh:head title="Consultass configurables por Rol-Estados">
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
          <tr:panelHeader text="Consultas configurables por Rol-Estados"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>

             <tr:spacer width="10" height="10"/>
		          <tr:table width="100%" rows="100"
		                    value="#{listaConsultasConfigurables}"
		                    var="linea"  >

			            <tr:column  headerText="Consulta">
			              <tr:outputText value="#{linea}"/>
			            </tr:column>

			            <tr:column  headerText="" width="120px" >
			              <tr:commandLink text="[ Configurar ]"
										action="dialog:gestionConsultasConfigurables"
										useWindow="true"
										windowHeight="560" windowWidth="700">
								  <tr:setActionListener from="#{linea}"  to="#{pageFlowScope.consultaConfigurable}"/>
						  </tr:commandLink>
			            </tr:column>
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
