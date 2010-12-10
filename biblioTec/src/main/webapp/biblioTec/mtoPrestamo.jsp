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

		<trh:head title="#{resCore['GESTION_PRESTAMOS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnBuscar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['DATOS_PRESTAMO']}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_PRESTAMO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0"
								rendered="#{mtoPrestamoBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{mtoPrestamoBean.usuario.nombre}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LIBRO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoPrestamoBean.libro.titulo}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['FECHA_INICIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoPrestamoBean.fechaInicio}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['FECHA_FIN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoPrestamoBean.fechaFin}" />
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<trh:tableLayout cellSpacing="5" cellPadding="0"
								rendered="#{!mtoPrestamoBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['USUARIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50"
										value="#{mtoPrestamoBean.usuario!=null ? mtoPrestamoBean.usuario.nombre : ''}"
										id="usuario" disabled="true" />
									<tr:commandLink action="#{mtoPrestamoBean.buscarUsuario}">
										<tr:image shortDesc="#{resCore['SELECCIONAR_USUARIO']}"
											source="#{'/imagenes/buscar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
									<tr:commandLink
										action="#{mtoPrestamoBean.eliminarUsuario}">
										<tr:image shortDesc="#{resCore['BORRAR_USUARIO']}"
											source="#{'/imagenes/borrar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LIBRO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50"
										value="#{mtoPrestamoBean.libro!=null ? mtoPrestamoBean.libro.titulo : ''}"
										id="libro" disabled="true" />
									<tr:commandLink action="#{mtoPrestamoBean.buscarLibro}">
										<tr:image shortDesc="#{resCore['SELECCIONAR_LIBRO']}"
											source="#{'/imagenes/buscar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
									<tr:commandLink action="#{mtoPrestamoBean.eliminarLibro}">
										<tr:image shortDesc="#{resCore['BORRAR_LIBRO']}"
											source="#{'/imagenes/borrar.png'}"
											inlineStyle="height: 20px;" />
									</tr:commandLink>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['FECHA_INICIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputDate columns="20" 
										value="#{mtoPrestamoBean.fechaInicio}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['FECHA_FIN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputDate columns="20" 
										value="#{mtoPrestamoBean.fechaFin}" />
								</trh:rowLayout>
							</trh:tableLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center"
								rendered="#{!mtoPrestamoBean.detalles}">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{mtoPrestamoBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{mtoPrestamoBean.cancelar}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center"
							rendered="#{mtoPrestamoBean.detalles}">
								<tr:commandButton text="#{resCore['VOLVER']}" id="volver"
									action="#{mtoPrestamoBean.volver}" />
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
