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
			<geos:cabeceraPagina />

			<tr:form usesUpload="true">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resCore['IMAGENES_LOCAL']}" />

					<tr:panelHorizontalLayout halign="center">
						<tr:panelBox inlineStyle="width=100%" partialTriggers="aniadir borrar">

							<tr:panelHorizontalLayout halign="center">
								<tr:outputLabel value="#{imagenesLocalBean.descripcionLocal}"
									inlineStyle="font-weight: bold; font-size: 120%;" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="20" height="20" />

							<tr:panelHorizontalLayout halign="center" partialTriggers="aniadir borrar">
								<tr:outputLabel rendered="#{!imagenesLocalBean.mostrarImagenes}"
									value="#{resCore['NO_IMAGENES']}" />
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout halign="center" partialTriggers="aniadir borrar"
								rendered="#{imagenesLocalBean.mostrarImagenes}">
								<tr:commandLink action="#{imagenesLocalBean.imagenAnterior}"
									id="anterior" rendered="#{imagenesLocalBean.mostrarAnterior}">
									<tr:image source="/imagenes/dondeando/anterior.png"
										inlineStyle="height: 40px;" shortDesc="#{resCore['ANTERIOR']}" />
								</tr:commandLink>
								<tr:spacer width="20" />
								<tr:image partialTriggers="anterior siguiente aniadir borrar"
									source="#{imagenesLocalBean.urlImagenMostrada}"
									inlineStyle="height: 300px;"
									shortDesc="#{imagenesLocalBean.descripcionImagenMostrada}" />
								<tr:spacer width="20" />
								<tr:commandLink action="#{imagenesLocalBean.imagenSiguiente}"
									id="siguiente" rendered="#{imagenesLocalBean.mostrarSiguiente}">
									<tr:image source="/imagenes/dondeando/siguiente.png"
										inlineStyle="height: 40px;"
										shortDesc="#{resCore['SIGUIENTE']}" />
								</tr:commandLink>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center"
								rendered="#{imagenesLocalBean.mostrarImagenes}">
								<tr:outputLabel partialTriggers="anterior siguiente"
									value="#{imagenesLocalBean.descripcionImagenMostrada}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center" rendered="#{imagenesLocalBean.mostrarAniadirYBorrar}">
								<tr:commandButton text="#{resCore['ANIADIR']}"
									id="aniadir"
									action="#{imagenesLocalBean.aniadir}" 
									returnListener="#{imagenesLocalBean.returnAniadir}"
									useWindow="true"/>
								<tr:spacer width="10" partialTriggers="aniadir"
									rendered="#{imagenesLocalBean.mostrarBorrar}" />
								<tr:commandButton text="#{resCore['BORRAR']}"
									id="borrar" partialTriggers="aniadir"
									action="#{imagenesLocalBean.borrar}"
									onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_IMAGEN']}')"
									rendered="#{imagenesLocalBean.mostrarBorrar}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" height="20" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['VOLVER']}"
									action="#{imagenesLocalBean.volver}" />
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
