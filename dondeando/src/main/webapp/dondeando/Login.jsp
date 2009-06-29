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
		
		<trh:body
			initialFocusId="#{loginBean.username==null ? 'user' : 'passw' }">
			
			<geos:cabeceraPagina />
			
			<tr:form defaultCommand="btnAceptar" id="formLogin">
				<tr:panelHorizontalLayout halign="center">
					<tr:spacer width="10" />
					<tr:panelBox text="#{resCore['ACCEDER']}">
						<tr:spacer width="225" height="10" />
						<tr:panelFormLayout>
							<tr:panelLabelAndMessage label="#{resCore['USUARIO']}">
								<tr:inputText columns="20" id="user" value="#{loginBean.username}" simple="true" />
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['CONTRASENA']}">
								<tr:inputText columns="20" id="passw" value="#{loginBean.password}" secret="true" simple="true" />
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
						</tr:panelFormLayout>
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar" action="#{loginBean.login}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:panelHorizontalLayout>
				<tr:messages />
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
