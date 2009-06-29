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
      <trh:head title="Gestión de prórrogas">
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
          <tr:panelHeader text="Gestión de prórrogas"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelFormLayout>
          	<tr:messages />
            <f:facet name="footer"/>
            <tr:panelLabelAndMessage label="Numero Expediente Interno:">
              <tr:outputText value="#{gestionExpedienteContratacionProrrogaBean.numeroExpedienteInterno}"/>
            </tr:panelLabelAndMessage>
            <tr:panelLabelAndMessage label="Objeto Contrato:">
              <tr:outputText value="#{gestionExpedienteContratacionProrrogaBean.objetoContrato}"/>
            </tr:panelLabelAndMessage>
          </tr:panelFormLayout>
          <tr:table emptyText="No items were found" width="800" rowSelection="single" autoSubmit="true"
          	selectionListener="#{tablaProrrogaBean.eventoSeleccion}" var="fila"
          	value="#{tablaProrrogaBean.modelo}"  >

            <tr:column  headerText="Fecha Solicitud">
              <tr:outputText value="#{fila.fechaSolicitud}"/>
            </tr:column>

            <tr:column  headerText="Plazo Solicitud">
              <tr:outputText value="#{fila.plazoSolicitud}"/>
            </tr:column>

            <tr:column  headerText="Estado">
              <tr:outputText value="#{fila.estadoSolicitud}"/>
            </tr:column>
            <tr:column  headerText="Fecha Respuesta">
              <tr:outputText value="#{fila.fechaRespuesta}"/>
            </tr:column>
            <tr:column  headerText="Dias Concedidos">
              <tr:outputText value="#{fila.diasConcedidos}"/>
            </tr:column>

            <f:facet name="actions">

                <tr:panelButtonBar>
                     <tr:commandButton text="Solicitar" action="#{agregarProrroga.ejecuta}"
                         rendered="#{tablaProrrogaBean.muestraSolicitar}"  />
                    <tr:commandButton text="Modificar" action="#{modificarProrroga.ejecuta}"
                        rendered="#{tablaProrrogaBean.muestraModificar}" />
                    <tr:commandButton text="Conceder/Denegar"
               		action="#{modificarProrroga.ejecuta}"
               		rendered="#{tablaProrrogaBean.muestraCondederDenegar}" />
                </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:commandButton text="Volver" action="#{gestionExpedienteContratacionProrrogaBean.actualizarYSalir}"/>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>