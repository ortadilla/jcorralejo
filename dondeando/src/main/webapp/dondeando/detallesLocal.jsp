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

			<tr:form defaultCommand="btnVolver" id="formDetallesLocal">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resCore['DETALLES_LOCAL']}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_LOCAL']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="4">
										<tr:outputText value="#{detallesLocalBean.nombre}" id="nombre" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['DESCRIPCION']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="4">
										<tr:outputText value="#{detallesLocalBean.descripcion}" id="descripcion"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PROVINCIA']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:outputText value="#{detallesLocalBean.provincia}" id="provincia"/>
									</trh:cellFormat>
									<tr:spacer />
									<tr:outputText value="#{resCore['LOCALIDAD']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:outputText	value="#{detallesLocalBean.localidad}" id="localidad"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TIPO_VIA']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat>
										<tr:outputText	value="#{detallesLocalBean.tipoVia}" id="tipoVia"/>
									</trh:cellFormat>
									<tr:spacer />
									<tr:outputText value="#{resCore['NOMBRE_VIA']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:outputText value="#{detallesLocalBean.nombreVia}" id="nombreVia"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NUMERO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:outputText value="#{detallesLocalBean.numero}" id="numero" />
									<tr:spacer />
									<tr:outputText value="#{resCore['CODIGO_POSTAL']}"
										inlineStyle="font-weight: bolder;" />
									<tr:outputText value="#{detallesLocalBean.codigoPostal}" id="codigoPostal"/>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TIPO_LOCAL']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:selectManyListbox value="#{detallesLocalBean.tiposLocal}">
											<f:selectItems id="selectTiposLocal"
												value="#{detallesLocalBean.selectTiposLocal}" />
										</tr:selectManyListbox>
									</trh:cellFormat>
									<tr:spacer/>
									<tr:outputText value="#{resCore['SERVICIOS_DISPONIBLES']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:selectManyListbox value="#{detallesLocalBean.servicios}">
											<f:selectItems id="selectPrecio"
												value="#{detallesLocalBean.selectServicios}" />
										</tr:selectManyListbox>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PRECIO_MEDIO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:outputText 	value="#{detallesLocalBean.precioMedio}" id="precio" />
								</trh:rowLayout>

								<trh:rowLayout>
									<tr:outputText value="#{resCore['TELEFONO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:outputText value="#{detallesLocalBean.telefono}"
										id="telefono" />
									<tr:spacer />
									<tr:outputText value="#{resCore['EMAIL']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:outputText value="#{detallesLocalBean.email}"
											id="email" />
										</tr:inputText>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['HORARIO']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="5">
										<tr:outputText value="#{detallesLocalBean.horario}" id="horario"/>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['OTRA_INFORMACION']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="5">
										<tr:outputText value="#{detallesLocalBean.otraInformacion}"
											id="otraInformacion" />
									</trh:cellFormat>
								</trh:rowLayout>

								<trh:rowLayout />
								<trh:rowLayout>
									<trh:cellFormat columnSpan="5">
										<tr:panelHorizontalLayout halign="center">
											<tr:commandButton text="#{resCore['IMAGENES']}"
												id="btnImagenes" action="#{detallesLocalBean.irImagenes}" />
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout />
							</trh:tableLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['VOLVER']}" id="btnVolver"
									action="#{detallesLocalBean.volver}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
