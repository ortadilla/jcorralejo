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
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
		<f:loadBundle basename="mensajesOfe" var="mensajesOfe" />

		<trh:html>
		<trh:head title="#{mensajesOfe['CREAR_BLOQUEO_OFERTAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="btnBuscar" onsubmit="bloquearPantalla(this);">
				<tr:panelPage inlineStyle="width:100%;">
					<tr:panelHeader text="#{mensajesOfe['CREAR_BLOQUEO_OFERTAS']}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox partialTriggers="" inlineStyle="width:100%;"
						background="medium">
						<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
							<trh:rowLayout>
								<tr:spacer width="5" height="20" />
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText styleClass="etiquetaForm"
									value="#{mensajesCat['CLASIFICACION']}" />
								<tr:inputText id="nivelValorPadre"
									value="#{ofertas_crearBloqueoMasivoOfertasBean.descripcionNivelValorPadre}"
									disabled="true" columns="60" />
								<tr:commandLink
									action="#{ofertas_crearBloqueoMasivoOfertasBean.buscarNivelValor}">
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink
									action="#{ofertas_crearBloqueoMasivoOfertasBean.limpiarNivelValor}">
									<tr:image source="../imagenes/btnEliminar.gif" />
								</tr:commandLink>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText styleClass="etiquetaForm"
									value="#{mensajesOfe['VALIDACIONTECNICA']}" />
								<trh:cellFormat columnSpan="3">
									<tr:panelHorizontalLayout>
										<tr:spacer width="5" height="0" />
										<tr:selectBooleanCheckbox id="validacionTecnicaSI"
											value="#{ofertas_crearBloqueoMasivoOfertasBean.validacionTecnicaSI}" />
										<tr:outputText value="#{mensajesCore['SI']}" />
										<tr:spacer width="5" height="0" />
										<tr:selectBooleanCheckbox id="validacionTecnicaNO"
											value="#{ofertas_crearBloqueoMasivoOfertasBean.validacionTecnicaNO}" />
										<tr:outputText value="#{mensajesCore['NO']}" />
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText styleClass="etiquetaForm"
									value="#{mensajesOfe['VALIDACIONLOGISTICA']}" />
								<trh:cellFormat columnSpan="3">
									<tr:panelHorizontalLayout>
										<tr:spacer width="5" height="0" />
										<tr:selectBooleanCheckbox id="validacionLogisticaSI"
											value="#{ofertas_crearBloqueoMasivoOfertasBean.validacionLogisticaSI}" />
										<tr:outputText value="#{mensajesCore['SI']}" />
										<tr:spacer width="5" height="0" />
										<tr:selectBooleanCheckbox id="validacionLogisticaNO"
											value="#{ofertas_crearBloqueoMasivoOfertasBean.validacionLogisticaNO}" />
										<tr:outputText value="#{mensajesCore['NO']}" />
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:spacer width="5" height="20" />
							</trh:rowLayout>
						</trh:tableLayout>
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{mensajesCore['ACEPTAR']}"
								id="btnAceptar" useWindow="true" 
								windowHeight="800" windowWidth="700"
								action="#{ofertas_crearBloqueoMasivoOfertasBean.aceptar}"
								blocking="true" />
							<tr:spacer width="10" height="0" />
							<tr:commandButton text="#{mensajesCore['VOLVER']}"
								action="#{ofertas_crearBloqueoMasivoOfertasBean.volver}"
								id="btnCancelar">
							</tr:commandButton>
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
