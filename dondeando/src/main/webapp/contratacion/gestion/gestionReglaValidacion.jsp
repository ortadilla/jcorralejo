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
      <trh:head title="Gestión de reglas de validación">
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
          <tr:panelHeader text="Editar detalles de reglas de validación de campos"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" >
            <tr:panelFormLayout >
              <f:facet name="footer"/>
              <tr:panelLabelAndMessage label="Campo a validar">
                <tr:selectOneChoice value="#{gestionReglaValidacionBean.idCampoValidacion}" simple="true"
                				    disabled="#{!modoEdicion.agregar}">
                  <f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaCamposEditablesVersionModeloPliego}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Tipo de validación">
                <tr:selectOneChoice value="#{gestionReglaValidacionBean.idTipoValidacion}" simple="true"
                                    valueChangeListener="#{gestionReglaValidacionBean.cambioTipoValidacion}"
                                    autoSubmit="true" id="idTipoValidacion"
                                    disabled="#{modoEdicion.ver}">
                  <f:selectItems value="#{gestorListaTipoValidacion.listaElementos}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Campo de la opción">
                <tr:selectOneChoice value="#{gestionReglaValidacionBean.idCampoOpcion}" simple="true"
                                    partialTriggers="idTipoValidacion"
                                    disabled="#{modoEdicion.ver}"
                                    binding="#{gestionReglaValidacionBinding.campoOpcion}">
                  <f:selectItems  value="#{gestionEstructuraVersionModeloPliegoBean.listaCamposEditablesVersionModeloPliego}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Valor de la opción">
                <tr:inputText value="#{gestionReglaValidacionBean.valorOpcion}" simple="true"
                              partialTriggers="idTipoValidacion"
                              disabled="#{modoEdicion.ver}" columns="100"
                              rows="2" maximumLength="250" immediate="true"
                              binding="#{gestionReglaValidacionBinding.valorCampoOpcion}"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Estado origen">
                <tr:selectOneChoice value="#{gestionReglaValidacionBean.codigoEstadoOrigen}" simple="true"
                			  disabled="#{modoEdicion.ver}" >
                  <f:selectItems value="#{estadosPosiblesVersionModeloPliego}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
              <tr:spacer width="10" height="10"/>
              <tr:selectBooleanCheckbox label="Aviso"
                                        disabled="#{modoEdicion.ver}"
                                        value="#{gestionReglaValidacionBean.aviso}"/>
            </tr:panelFormLayout>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Aceptar"
                              action="#{gestionReglaValidacionBean.actualizarYSalir}"
                              />
            <tr:commandButton text="Cancelar"
                              action="#{gestionReglaValidacionBean.cancelar}"/>
            <tr:commandButton text="Menú principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
