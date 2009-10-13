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
			<trh:script source="/include/libreriaGEOS.js">
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}" />
		</trh:head>
		<trh:body>
			<tr:form usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resCore['ANIADIR_IMAGEN']}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:panelBox>
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="5" cellPadding="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['NUEVA_IMAGEN']} *"
											inlineStyle="font-weight: bolder;" />
										<tr:inputFile value="#{aniadirImagenBean.file}" columns="30" />
									</trh:rowLayout>
									<trh:rowLayout
										rendered="#{aniadirImagenBean.mostrarDescripcion}">
										<tr:outputText value="#{resCore['DESCRIPCION']} *"
											inlineStyle="font-weight: bolder;" />
										<tr:inputText value="#{aniadirImagenBean.descripcion}" />
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>
							<tr:spacer height="20" width="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
									action="#{aniadirImagenBean.aceptar}" />
								<tr:spacer width="20" height="10" />
								<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
									immediate="true" action="#{aniadirImagenBean.cancelar}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
