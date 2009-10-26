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
					<tr:panelHeader text="#{editarMensajeForoBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_MENSAJE']}" inlineStyle="width:100%;">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['ASUNTO']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="53"
										value="#{editarMensajeForoBean.asunto}" id="titulo"
										maximumLength="50" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['MENSAJE']} *"
										inlineStyle="font-weight: bolder;" />
									<tr:inputText columns="50" rows="5"
										value="#{editarMensajeForoBean.mensaje}" id="descripcion"
										maximumLength="2000" />
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:spacer width="20" height="20" />
							<tr:table var="var" first="0"
								emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
								value="#{editarMensajeForoBean.listaMensajesTema}"
								rowBandingInterval="1" columnBandingInterval="0"
								rowSelection="none" id="listaRespuestas">
								<f:facet name="actions">
									<tr:panelHorizontalLayout inlineStyle="width: 350px">
										<tr:outputText id="elementosEncontrados"
											inlineStyle="font-weight: bold"
											value="#{resCore['ANTERIORES_RESPUESTAS']}" />
									</tr:panelHorizontalLayout>
								</f:facet>
								<tr:column sortable="true" headerText="#{resCore['MENSAJE']}"
									width="80%">
									<tr:outputText value="#{var.mensaje}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resCore['AUTOR']}">
									<tr:outputText value="#{var.autorYFecha}" />
								</tr:column>
							</tr:table>

							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{editarMensajeForoBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{editarMensajeForoBean.cancelar}" />
							</tr:panelHorizontalLayout>

						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
