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
      <trh:head title="Historial del expediente">
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
          <tr:panelHeader text="Historial del expediente"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox text="Datos Expediente" background="medium" inlineStyle="width:100%">
          	<tr:messages />
            <f:facet name="footer"/>
            <tr:panelLabelAndMessage label="Numero Expediente Interno:">
              <tr:outputText value="#{consultaHistoricoBean.numeroExpedienteInterno}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Objeto Contrato:">
              <tr:outputText value="#{consultaHistoricoBean.objetoContrato}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Organo Gestor:">
              <tr:outputText value="#{consultaHistoricoBean.organoGestor}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Estados Actuales:">

            	<tr:iterator rows="25" var="estados" value="#{consultaHistoricoBean.estadoActual}">
         				 <tr:panelList>
           			 		<tr:outputText value="#{estados.label}"/>
         			 	</tr:panelList>
      			</tr:iterator>

            </tr:panelLabelAndMessage>
          </tr:panelBox>
          <tr:spacer height="10"></tr:spacer>
          <tr:panelTabbed>
            <tr:showDetailItem text="Histórico" disclosed="true">
              <tr:table emptyText="No items were found" width="100%"
                        var="fila"
                        value="#{consultaHistoricoBean.modeloHistorico}" rows="15">
                <tr:column  headerText="Estado">
                  <tr:outputText value="#{fila.estado}"/>
                </tr:column>

                <tr:column  headerText="Fecha Entrada">
                  <tr:outputText value="#{fila.fechaEntrada}">
                  	<tr:convertDateTime pattern="dd/MM/yyyy HH:mm:ss"/>
                  </tr:outputText>
                </tr:column>
                <tr:column  headerText="Fecha Salida">
                  <tr:outputText value="#{fila.fechaSalida}">
                  	<tr:convertDateTime pattern="dd/MM/yyyy HH:mm:ss"/>
                  </tr:outputText>
                </tr:column>
                <tr:column  headerText="Tiempo">
                  <tr:outputText value="#{fila.tiempo}">
                    </tr:outputText>
                </tr:column>
                <tr:column  headerText="Usuario">
                  <tr:outputText value="#{fila.usuarioProcedencia}"/>
                </tr:column>
                <f:facet name="detailStamp">
                  <tr:panelFormLayout rows="1" fieldWidth="300">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Estado Origen:">
                      <tr:outputText value="#{fila.estadoProcedencia}"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Transicion Origen:">
                      <tr:outputText value="#{fila.transicionProcedencia}"/>
                    </tr:panelLabelAndMessage>
                  </tr:panelFormLayout>
                </f:facet>
              </tr:table>
            </tr:showDetailItem>
            <tr:showDetailItem text="Resumen" disclosed="false">
              <tr:table emptyText="No items were found" rows="15" var="fila"  width="100%"
               		value="#{consultaHistoricoBean.modeloResumen}"
              >
                <tr:column  headerText="Estado">
                    <tr:outputText value="#{fila.estado}"/>
                </tr:column>
                <tr:column  headerText="%Tiempo">
                    <tr:outputText value="#{fila.tiempo}">
                    	<tr:convertNumber pattern="#00.00 %" />
                    </tr:outputText>
                </tr:column>
                <tr:column  headerText="Nº Veces">
                    <tr:outputText value="#{fila.numeroVeces}"/>
                </tr:column>
              </tr:table>
            </tr:showDetailItem>
          </tr:panelTabbed>
          <tr:panelHorizontalLayout halign="center">
          		<tr:commandButton text="Volver" action="#{consultaHistoricoBean.volver}"/>
		  </tr:panelHorizontalLayout>

        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>