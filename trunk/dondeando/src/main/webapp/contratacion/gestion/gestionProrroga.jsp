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
          <tr:panelBox background="medium" inlineStyle="width:100%" >
            <tr:panelFormLayout>


              <tr:panelLabelAndMessage label="Número interno expediente">
                <tr:inputText disabled="true" rows="1" columns="50" simple="true"
                              value="#{gestionProrrogaBean.numeroInternoExpediente}"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Objeto del contrato">
                <tr:inputText disabled="true" columns="50" simple="true"
                              value="#{gestionProrrogaBean.objetoContrato}"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Plazo de solicitud">
                <tr:inputText columns="50" simple="true"
                              disabled="#{gestionProrrogaBean.concediendoDenegando}"
                              value="#{gestionProrrogaBean.plazoSolicitud}" required="true" showRequired="false"
                              label="Plazo de solicitud">
                              <f:converter converterId="javax.faces.Integer" />
				</tr:inputText>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Motivo de solicitud"
                                       inlineStyle="vertical-align:text-top;">
                <tr:inputText columns="50" simple="true"
                              disabled="#{gestionProrrogaBean.concediendoDenegando}"
                              value="#{gestionProrrogaBean.motivoSolicitud}" required="true" showRequired="false"
                              rows="3" label="Motivo de solicitud" />
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Conceder" rendered="#{gestionProrrogaBean.concediendoDenegando}"
              	>
                <tr:selectOneRadio value="#{gestionProrrogaBean.concedido}"
                                   required="true" showRequired="false"
                                   label="Conceder" simple="true">
                  <f:selectItems value="#{gestorListaBooleano.listaElementos}"/>
                </tr:selectOneRadio>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Días concedidos" rendered="#{gestionProrrogaBean.concediendoDenegando}" >
                <tr:inputText columns="50"
                              value="#{gestionProrrogaBean.diasConcedidos}" required="true" showRequired="false"
                              label="Dias Concedidos" simple="true">
                  <f:converter converterId="javax.faces.Integer"/>
                </tr:inputText>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage rendered="#{gestionProrrogaBean.concediendoDenegando}" label="Motivo de Concesion/Rechazo"
                                       inlineStyle="vertical-align:text-top;">
                <tr:inputText columns="50"
                              value="#{gestionProrrogaBean.motivoConcesion}" required="true" showRequired="false"
                              rows="3" simple="true" label="Motivo concesión"/>
              </tr:panelLabelAndMessage>

            </tr:panelFormLayout>
          </tr:panelBox>
          <h:panelGroup>
                  <tr:panelHorizontalLayout halign="center">
                    <tr:commandButton text="Aceptar" action="#{gestionProrrogaBean.actualizarYSalir}"
                                      />
                    <tr:commandButton text="Cancelar" action="#{gestionProrrogaBean.cancelar}" immediate="true" />
                    <tr:commandButton text="Menú principal"
						action="#{volverMenuPrincipalBean.ejecuta}" />
                  </tr:panelHorizontalLayout>
                </h:panelGroup>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>