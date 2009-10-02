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
		
		<trh:body initialFocusId="pass">
			
			<geos:cabeceraPagina />
			
			<tr:form defaultCommand="btnAceptar" id="formModCon">
			<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader	text="#{resCore['MODIFICAR_PASS']}" />
					<tr:panelHorizontalLayout halign="center">
					<tr:spacer width="10" />
					<tr:panelBox>
						<tr:spacer height="20" />
						
						<tr:outputText value="#{resCore['ADVERTENCIA_MODIFICAR_PASS']}"
						inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 120%; color: white; text-align: center;"/>
						
						<tr:spacer height="20" />
						<tr:panelHorizontalLayout halign="center">
						<tr:panelFormLayout>
							<tr:panelLabelAndMessage label="#{resCore['CONTRASENA']} *">
								<tr:inputText columns="20" value="#{modificarContrasenaBean.password}" id="pass" 
									maximumLength="20" secret="true"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['REPETIR_CONTRASENA']} *">
								<tr:inputText columns="20" value="#{modificarContrasenaBean.password2}" id="pass2"
									maximumLength="20" secret="true"/>
							</tr:panelLabelAndMessage>
						</tr:panelFormLayout>
						</tr:panelHorizontalLayout>
						
						<tr:spacer width="20" height="20" />
						
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{modificarContrasenaBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar" immediate="true"
								action="#{modificarContrasenaBean.cancelar}" />
						</tr:panelHorizontalLayout>
						
					</tr:panelBox>
				</tr:panelHorizontalLayout>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
