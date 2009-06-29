<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="resCore" />	
		<f:loadBundle basename="mensajesOrg" var="resOrg" />	
		<f:loadBundle basename="mensajesOfe" var="resOfe" />
		<trh:html>
		<trh:head title="#{resOrg['ENVIAR_NOTIF_NO_CONFORMIDAD']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
				<tr:form>
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox background="light" inlineStyle="width: 100%;" text="#{resOrg['ENVIAR_NOTIF_NO_CONFORMIDAD']}">
						<trh:tableLayout cellSpacing="5" cellPadding="0"
											borderWidth="0" inlineStyle="width:100%;">
							<trh:rowLayout>
								<trh:cellFormat columnSpan="2">
									<tr:outputText value="#{resOfe['TEXTO_NOTIFICACION_NO_CONFORMIDAD_CAMBIO_COD_SAS']}" inlineStyle="font-weight: bolder;" />
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:spacer height="10" />
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resOrg['ASUNTO']}:" inlineStyle="font-weight: bolder;"/>
								<tr:panelLabelAndMessage>
									<tr:outputText value="#{ofertas_notificarNoConformidadBean.asunto}"/>
								</tr:panelLabelAndMessage>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resOrg['MENSAJE']}:" inlineStyle="font-weight: bolder;"/>
								<tr:inputText rows="6" columns="80" value="#{ofertas_notificarNoConformidadBean.mensaje}" />
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat />
								<trh:cellFormat>
									<tr:commandButton text="#{resOrg['ENVIAR_NOTIFICACION']}" action="#{ofertas_notificarNoConformidadBean.enviarNotificacion}" />
									<tr:commandButton text="#{resCore['CANCELAR']}" action="#{ofertas_notificarNoConformidadBean.cancelar}"/>
								</trh:cellFormat>
							</trh:rowLayout>							
						</trh:tableLayout>
					</tr:panelBox>
				</tr:panelPage>					
				</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>