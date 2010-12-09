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

		<trh:head title="#{resCore['GESTION_USUARIOS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnBuscar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{mtoUsuarioBean.titulo}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_USUARIO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0"
								rendered="#{mtoUsuarioBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LOGIN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{mtoUsuarioBean.login}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoUsuarioBean.nombre}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PERFILES']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoUsuarioBean.descPerfiles}" />
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0"
								rendered="#{!mtoUsuarioBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LOGIN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:inputText value="#{mtoUsuarioBean.login}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText value="#{mtoUsuarioBean.nombre}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PERFILES']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:selectManyListbox value="#{mtoUsuarioBean.perfiles}">
											<f:selectItems id="selectPerfiles"
												value="#{mtoUsuarioBean.selectPerfiles}" />
										</tr:selectManyListbox>
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<tr:spacer width="20" height="20" rendered="#{mtoUsuarioBean.detalles}"/>
							<tr:panelHorizontalLayout halign="center" rendered="#{mtoUsuarioBean.detalles}">
								<tr:commandButton text="#{resCore['PRESTAMOS']}"
									id="btnModificar" action="#{mtoUsuarioBean.verPrestamos}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center"
								rendered="#{!mtoUsuarioBean.detalles}">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{mtoUsuarioBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{mtoUsuarioBean.cancelar}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['VOLVER']}" id="volver"
									action="#{mtoUsuarioBean.volver}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout halign="center">
				<h:outputText value="#{resCore['VERSION']}" style="font-size:50%" />
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
