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

		<trh:body initialFocusId="nombre">

			<geos:cabeceraPagina />

			<tr:form defaultCommand="btnAceptar" id="formCrearUsuario"
				usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resCore['DETALLES_USUARIO']}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_USUARIO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{detallesUsuarioBean.login}" />
									<tr:spacer height="20" />
									<trh:cellFormat rowSpan="3">
										<tr:image source="#{detallesUsuarioBean.urlImagenUsuario}"
											shortDesc="#{resCore['USUARIO']}  #{detallesUsuarioBean.login}"
											inlineStyle="height: 70px;border-style: solid; border-width: 1px;" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{detallesUsuarioBean.nombre}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['APELLIDOS']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{detallesUsuarioBean.apellidos}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['DIRECCION']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{detallesUsuarioBean.direccion}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['EMAIL']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{detallesUsuarioBean.email}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['KARMA']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{detallesUsuarioBean.karma}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout rendered="#{detallesUsuarioBean.mantenerUsuario}">
									<tr:outputText value="#{resCore['TIPO_USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText
											value="#{detallesUsuarioBean.tipoUsuario.descripcion}" />
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['VOLVER']}"
									action="#{detallesUsuarioBean.volver}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center" rendered="#{detallesUsuarioBean.mantenerUsuario}">
								<tr:commandButton text="#{resCore['MODIFICAR_USUARIO']}"
									id="btnModificar" action="#{detallesUsuarioBean.modificar}"/>
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['MODIFICAR_PASS']}"
									id="btnModContr" action="#{detallesUsuarioBean.modificarContr}"/>
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['ELIMINAR_USUARIO']}"
									id="btnEliminar" action="#{detallesUsuarioBean.eliminar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_USUARIO']}')" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="10" />

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
