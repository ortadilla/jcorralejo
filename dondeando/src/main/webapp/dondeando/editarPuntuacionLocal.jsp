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
					<tr:panelHeader text="#{editarPuntuacionLocalBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_VALORACION']}" inlineStyle="width:100%;">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['COMIDA']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarPuntuacionLocalBean.comida}" id="comida"
										maximumLength="2" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['AMBIENTE']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarPuntuacionLocalBean.ambiente}" id="ambiente"
										maximumLength="2" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['SERVICIO']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarPuntuacionLocalBean.servicio}" id="servicio"
										maximumLength="2" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['CALIDAD_PRECIO']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="5"
										value="#{editarPuntuacionLocalBean.calidadPrecio}" id="calidadPrecio"
										maximumLength="2" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LO_MEJOR']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50" rows="5"
										value="#{editarPuntuacionLocalBean.loMejor}" id="loMejor"
										maximumLength="500" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['LO_PEOR']}"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50" rows="5"
										value="#{editarPuntuacionLocalBean.loPeor}" id="loPeor"
										maximumLength="500" />
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{editarPuntuacionLocalBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{editarPuntuacionLocalBean.cancelar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
