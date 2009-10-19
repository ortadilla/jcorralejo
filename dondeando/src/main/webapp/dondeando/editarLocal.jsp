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

			<tr:form defaultCommand="btnAceptar" id="formCrearLocal" usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{editarLocalBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_LOCAL']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NOMBRE']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="4">
										<tr:inputText columns="106" value="#{editarLocalBean.nombre}"
											id="login" maximumLength="100" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['DESCRIPCION']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="4">
										<tr:inputText columns="103" rows="5"
											value="#{editarLocalBean.descripcion}" id="descripcion"
											maximumLength="2000" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PROVINCIA']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:selectOneChoice value="#{editarLocalBean.provincia}">
											<f:selectItems id="selectProvincia"
												value="#{editarLocalBean.selectProvincia}" />
										</tr:selectOneChoice>
									</trh:cellFormat>
									<tr:spacer />
									<tr:outputText value="#{resCore['LOCALIDAD']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:inputText columns="50"
											value="#{editarLocalBean.localidad}" id="localidad"
											maximumLength="50" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TIPO_VIA']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat>
										<tr:selectOneChoice value="#{editarLocalBean.tipoVia}">
											<f:selectItems id="selectTipoVia"
												value="#{editarLocalBean.selectTipoVia}" />
										</tr:selectOneChoice>
									</trh:cellFormat>
									<tr:spacer />
									<tr:outputText value="#{resCore['NOMBRE_VIA']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:inputText columns="50"
											value="#{editarLocalBean.nombreVia}" id="nombreVia"
											maximumLength="50" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['NUMERO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="3" value="#{editarLocalBean.numero}"
										id="numero" maximumLength="3" />
									<tr:spacer />
									<tr:outputText value="#{resCore['CODIGO_POSTAL']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarLocalBean.codigoPostal}" id="codigoPostal"
										maximumLength="5" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TIPO_LOCAL']} *"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:selectManyListbox value="#{editarLocalBean.tiposLocal}">
											<f:selectItems id="selectTiposLocal"
												value="#{editarLocalBean.selectTiposLocal}" />
										</tr:selectManyListbox>
									</trh:cellFormat>
									<tr:spacer/>
									<tr:outputText value="#{resCore['SERVICIOS_DISPONIBLES']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="2">
										<tr:selectManyListbox value="#{editarLocalBean.servicios}">
											<f:selectItems id="selectPrecio"
												value="#{editarLocalBean.selectServicios}" />
										</tr:selectManyListbox>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['PRECIO_MEDIO']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarLocalBean.precioMedio}" id="precio" />
								</trh:rowLayout>

								<trh:rowLayout>
									<tr:outputText value="#{resCore['TELEFONO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="10" value="#{editarLocalBean.telefono}"
										id="telefono" maximumLength="9" />
									<tr:spacer />
									<tr:outputText value="#{resCore['EMAIL']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="0">
										<tr:inputText columns="50" value="#{editarLocalBean.email}"
											id="email" maximumLength="100">
											<tr:validator binding="#{editarLocalBean.validatorEmail}" />
										</tr:inputText>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['HORARIO']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="5">
										<tr:inputText columns="103" rows="2"
											value="#{editarLocalBean.horario}" id="horario"
											maximumLength="200" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['OTRA_INFORMACION']}"
										inlineStyle="font-weight: bolder;" />
									<trh:cellFormat columnSpan="5">
										<tr:inputText columns="103" rows="5"
											value="#{editarLocalBean.otraInformacion}"
											id="otraInformacion" maximumLength="2000" />
									</trh:cellFormat>
								</trh:rowLayout>

								<trh:rowLayout />
								<trh:rowLayout>
									<trh:cellFormat columnSpan="5">
										<tr:panelHorizontalLayout halign="center">
											<tr:commandButton text="#{resCore['IMAGENES']}"
												id="btnImagenes" action="#{editarLocalBean.irImagenes}" />
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout />
								<!-- Dejar estos huecos para que quepa bien la imagen -->
								<!-- FALTAN LAS FOTOS -->
							</trh:tableLayout>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{editarLocalBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{editarLocalBean.cancelar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
