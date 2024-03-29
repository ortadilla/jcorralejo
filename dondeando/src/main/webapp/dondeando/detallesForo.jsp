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

			<tr:form defaultCommand="volver" id="formDetallesForo">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resCore['DETALLES_FORO']}" />
					<tr:panelHorizontalLayout halign="center">
						<tr:spacer width="10" />
						<tr:panelBox text="#{resCore['DATOS_FORO']}">
							<tr:spacer height="20" />
							<trh:tableLayout
								inlineStyle="width: 100%;  border-style: solid; border-width: 1px;"
								cellSpacing="5" cellPadding="0">
								<trh:rowLayout>
									<tr:outputText value="#{resCore['TITULO']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{detallesForoBean.titulo}" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{resCore['DESCRIPCION']}"
										inlineStyle="font-weight: bolder;" />
									<tr:spacer height="20" />
									<tr:outputText value="#{detallesForoBean.descripcion}" />
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['VER_TEMAS']}"
									id="verTemas"
									action="#{detallesForoBean.verTemas}" />
								<tr:spacer width="20" height="20" />
								<tr:commandButton text="#{resCore['VOLVER']}"
									id="volver"
									action="#{detallesForoBean.volver}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
