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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />

		<trh:html>
		<trh:head title="#{resOrg['MOTIVO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:messages />
				<tr:panelBox inlineStyle="100%;">
					<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
						<trh:rowLayout>
							<trh:cellFormat columnSpan="2">
								<tr:outputText value="#{organizacion_rechazoSolicitudMovimiento.mensajeConfirmacion}" />
	 						</trh:cellFormat>
						</trh:rowLayout>
						<trh:rowLayout>
							<trh:cellFormat>
								<tr:outputText value="#{resOrg['MOTIVO']}" />
	 						</trh:cellFormat>
							<trh:cellFormat>
								<tr:inputText value="#{organizacion_rechazoSolicitudMovimiento.mensaje}" columns="50" rows="5" />
	 						</trh:cellFormat>
						</trh:rowLayout>
						<trh:rowLayout>
							<trh:cellFormat columnSpan="2">
						      <tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}"
									action="#{organizacion_rechazoSolicitudMovimiento.botonAceptarPopUP}" />
								<tr:spacer width="10" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}"
									action="#{organizacion_rechazoSolicitudMovimiento.botonCancelarPopUP}" />
						      </tr:panelHorizontalLayout>
							</trh:cellFormat>
						</trh:rowLayout>
					</trh:tableLayout>
				</tr:panelBox>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
