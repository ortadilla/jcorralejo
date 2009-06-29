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
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html>
		<trh:head title="#{catalogo_gestionAlmacenes.titulo}">
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
				<tr:messages />
				<tr:panelBox text="#{resOrg['ALMACEN_QUE_GESTIONAN_EL_ARTICULO']}"
					binding="#{catalogo_gestionAlmacenesBinding.panelBox}"
					inlineStyle="width: 100%;">					
					<tr:spacer height="10" />
					<tr:panelHorizontalLayout>
						<tr:commandButton text="#{resCor['ACEPTAR']}"
							action="#{catalogo_gestionAlmacenes.aceptar}" />
						<tr:spacer width="10" height="10" />
						<tr:commandButton text="#{resCor['CANCELAR']}"
							action="#{catalogo_gestionAlmacenes.cancelar}" />
					</tr:panelHorizontalLayout>
				</tr:panelBox>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
