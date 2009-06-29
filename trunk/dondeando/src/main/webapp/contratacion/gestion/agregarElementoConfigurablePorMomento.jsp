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
      <trh:head title="Agregar Momento">
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
          <tr:panelHeader text="Agregar Momento"/>
          <tr:messages></tr:messages>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" >
            <tr:panelFormLayout>
              <f:facet name="footer"></f:facet>
              <tr:panelLabelAndMessage label="Elemento:" > <tr:outputText inlineStyle="color:blue"  value="#{cambiarMomentosElementoBean.elemento.descripcion}" /> </tr:panelLabelAndMessage>

              <tr:panelLabelAndMessage label="Rol">
                <tr:selectOneChoice simple="true" value="#{agregarElementoConfigurableAccion.rol}" required="true" >
                	 <f:selectItems value="#{gestorListaPerfilesWF.listaElementos}" />
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

			  <tr:panelLabelAndMessage label="Estado" >
                <tr:selectOneChoice simple="true" value="#{agregarElementoConfigurableAccion.estado}" required="true" >
                	<f:selectItems value="#{gestorListaEstadosWorkflow.listaElementos}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>


            </tr:panelFormLayout>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Aceptar"
                              action="#{agregarElementoConfigurableAccion.aceptar}"
                              />
            <tr:commandButton text="Cancelar" immediate="true"
                              action="#{agregarElementoConfigurableAccion.cancelar}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>