<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/strict.dtd"
		doctype-public="-//W3C//DTD HTML 4.01//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resCat['DESCARGA_ARTICULOS_ASOCIADOS_A_CENTRO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHorizontalLayout
						rendered="#{catalogo_seleccionarOrganoGestorDescargaArticulosBean.renderizaPanel}">
						<tr:panelLabelAndMessage label="#{resOrg['ORGANO_GESTOR']}">
							<tr:selectOneChoice
								value="#{catalogo_seleccionarOrganoGestorDescargaArticulosBean.valorOrganoGestor}">
								<f:selectItems
									value="#{catalogo_seleccionarOrganoGestorDescargaArticulosBean.selectItemOrganoGestor}" />
							</tr:selectOneChoice>
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" />
						<tr:commandButton text="#{resCor['ACEPTAR']}"
							action="#{catalogo_seleccionarOrganoGestorDescargaArticulosBean.aceptar}" />
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>