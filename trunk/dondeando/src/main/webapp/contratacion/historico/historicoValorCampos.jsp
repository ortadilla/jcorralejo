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
      <trh:head>
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

        <h:form title="Historico de datos" onsubmit="bloquearPantalla(this)">
          <tr:panelHeader text="Historico de datos"/>
          <tr:panelFormLayout>
            <f:facet name="footer"/>
            <tr:panelBox background="medium" inlineStyle="width: 100%;" contentStyle="width: 100%;" text="Historico de datos"   >

            	<tr:iterator var="linea" varStatus="estatus" value="#{consultaHistoricoValorCamposExpedienteBean.modelo}">

	           		<tr:panelHorizontalLayout >
	           			<tr:panelFormLayout inlineStyle="width:300px">
	           				<tr:outputLabel value="#{linea.campo}"  ></tr:outputLabel>
	           			</tr:panelFormLayout>
            			<tr:panelGroupLayout>
            				<tr:panelHorizontalLayout>
            					<tr:panelFormLayout inlineStyle="width:100px">
	           						<tr:outputLabel value="Valor Anterior: " ></tr:outputLabel>
	           					</tr:panelFormLayout>

            					<tr:inputText readOnly="true" value="#{linea.valorAnterior}" rows="3" columns="60"  ></tr:inputText>
            				</tr:panelHorizontalLayout>
            				<tr:panelHorizontalLayout>
            					<tr:panelFormLayout inlineStyle="width:100px">
	           						<tr:outputLabel value="Valor Actual: " ></tr:outputLabel>
	           					</tr:panelFormLayout>
            					<tr:inputText readOnly="true" value="#{linea.valor}" rows="3"  columns="60"> </tr:inputText>
            				</tr:panelHorizontalLayout>
            			</tr:panelGroupLayout>
            		</tr:panelHorizontalLayout>
            		<tr:separator />
            	</tr:iterator>


            </tr:panelBox>
            <tr:separator/>
            <tr:panelHorizontalLayout halign="center">
              <tr:panelButtonBar>
              	<tr:commandButton text="Cerrar"
              					  action="#{botonCerrarPopUpBean.volver}"/>

              </tr:panelButtonBar>
            </tr:panelHorizontalLayout>
          </tr:panelFormLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
