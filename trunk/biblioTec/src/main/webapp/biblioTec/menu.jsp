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
		<trh:html>
		<trh:head title="Menú" />
		<trh:body>

			<tr:form>
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['MENU']}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:panelBox text="#{resCore['OPCIONES']}">
							<tr:commandButton text="#{resCore['GESTION_USUARIOS']}"
								action="#{menuBean.gestionarUsuarios}"
								disabled="#{!menuBean.mostrarGestionUsuarios}" />
							<tr:commandButton text="#{resCore['GESTION_LIBROS']}"
								action="#{menuBean.gestionarLibros}"
								disabled="#{!menuBean.mostrarGestionLibros}" />
							<tr:commandButton text="#{resCore['GESTION_PRESTAMOS']}"
								action="#{menuBean.gestionarPrestamos}"
								disabled="#{!menuBean.mostrarGestionPrestamos}" />
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
				<tr:panelHorizontalLayout halign="center">
					<h:outputText value="#{resCore['VERSION']}" style="font-size:50%" />
				</tr:panelHorizontalLayout>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
