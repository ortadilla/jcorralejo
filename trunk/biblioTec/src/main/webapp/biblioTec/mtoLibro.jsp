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

		<trh:head title="#{resCore['GESTION_LIBROS']}" />

		<trh:body initialFocusId="user">

			<tr:form defaultCommand="btnBuscar">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelHeader text="#{resCore['DATOS_LIBRO']}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_LIBRO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0"
								rendered="#{mtoLibroBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TITULO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{mtoLibroBean.titulo}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['AUTOR']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoLibroBean.autor}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['ISBN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoLibroBean.isbn}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['UNIDADES_DISPONIBLES']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:outputText value="#{mtoLibroBean.unidadesDisponibles}" />
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0"
								rendered="#{!mtoLibroBean.detalles}">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TITULO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:inputText value="#{mtoLibroBean.titulo}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['AUTOR']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText value="#{mtoLibroBean.autor}" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['ISBN']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText value="#{mtoLibroBean.isbn}"  maximumLength="13"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['UNIDADES_DISPONIBLES']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<trh:cellFormat columnSpan="2">
										<tr:inputText value="#{mtoLibroBean.unidadesDisponibles}"/>
									</trh:cellFormat>
								</trh:rowLayout>
							</trh:tableLayout>

							<tr:spacer width="20" height="20" rendered="#{mtoLibroBean.detalles}"/>
							<tr:panelHorizontalLayout halign="center" rendered="#{mtoLibroBean.detalles}">
								<tr:commandButton text="#{resCore['PRESTAMOS']}"
									id="btnPrestamos" action="#{mtoLibroBean.verPrestamos}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center"
								rendered="#{!mtoLibroBean.detalles}">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{mtoLibroBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{mtoLibroBean.cancelar}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center"
							rendered="#{mtoLibroBean.detalles}">
								<tr:commandButton text="#{resCore['VOLVER']}" id="volver"
									action="#{mtoLibroBean.volver}" />
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
