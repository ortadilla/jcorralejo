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

		<f:loadBundle basename="mensajesOrg" var="resOrg" />

		<trh:html>
		<trh:head title="#{resOrg['MENU_PRINCIPAL']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="shortcut icon" href="../faviconCAT.ico" type="image/x-icon" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
			<tr:form onsubmit="bloquearPantalla(this);">
				<trh:tableLayout width="100%" cellSpacing="20">
					<trh:rowLayout valign="top">
						<trh:cellFormat width="50%">
							<tr:panelBox text="#{resOrg['MIGRACION_ARTICULOS']}" inlineStyle="100%;">
								<tr:panelList>
									<tr:goLink
										text="#{resOrg['MIGRACION_ARTICULOS_SAS_HOSPITALES_HIS']}"
										destination="/organizacion/migracionEntidad.jsf?entidad=1&amp;entidad2=93" />
								</tr:panelList>
							</tr:panelBox>
						</trh:cellFormat>
					</trh:rowLayout>
				</trh:tableLayout>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
