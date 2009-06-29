<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesPed" var="mensajesPed" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />


		<trh:html>
		<trh:head title="Motivo del cambio de prioridad">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<tr:form  defaultCommand="btnAceptar">

				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{mensajesPed['MOTIVO_CAMBIO_PRIORIDAD']}"/>
					
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<f:facet name="actions" />
					<tr:panelBox text="#{mensajesPed['MOTIVO']}" inlineStyle="width: 100%;">
						<tr:panelLabelAndMessage label="#{mensajesPed['MOTIVO']}">
							<tr:selectOneChoice  value="#{pedidos_prioridadOfertaMotivoBean.codMotivo}">
                             <f:selectItems value="#{pedidos_prioridadOfertaMotivoBean.motivosPrioridad}" />
                          </tr:selectOneChoice>
					</tr:panelLabelAndMessage>					
                        <tr:spacer width="0" height="20" />
                    <tr:panelLabelAndMessage label="#{mensajesCore['OBSERVACIONES']}">
						<tr:inputText value="#{pedidos_prioridadOfertaMotivoBean.observaciones}" />
					</tr:panelLabelAndMessage>
					</tr:panelBox>
					<tr:spacer width="0" height="15" />
					<tr:panelHorizontalLayout>
						<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="btnAceptar"
							action="#{pedidos_prioridadOfertaMotivoBean.botonAceptarPopUP}" />
						<tr:spacer width="20" height="0" />
						<tr:commandButton text="#{mensajesCore['CANCELAR']}"
							action="#{pedidos_prioridadOfertaMotivoBean.botonCancelarPopUP}" />
					</tr:panelHorizontalLayout>
					<tr:messages/>
				</tr:panelPage>

			</tr:form>

		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>