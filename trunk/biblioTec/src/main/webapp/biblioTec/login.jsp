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
		<trh:html>

		<trh:head>
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnAceptar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['LOGIN']}" />
					
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['LOGIN']}">
							<tr:spacer width="225" height="10" />
							<tr:panelFormLayout>
					
								<tr:panelLabelAndMessage label="#{resCore['USUARIO']}">
									<tr:inputText columns="20" id="user" value="#{loginBean.user}"
										simple="true" />
								</tr:panelLabelAndMessage>
					
								<tr:spacer width="10" height="10" />
					
								<tr:panelLabelAndMessage label="#{resCore['CLAVE']}">
									<tr:inputText columns="20" id="passw" value="#{loginBean.pass}"
										secret="true" simple="true" />
								</tr:panelLabelAndMessage>
					
								<tr:spacer width="10" height="10" />
							</tr:panelFormLayout>
					
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{loginBean.login}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
