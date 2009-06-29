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
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCore" var="resCore" />

		<trh:head title="#{resOrg['CARGA_FICHERO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>

		<trh:html>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{resOrg['CARGA_FICHERO']}"/>
					<tr:inputFile label="#{resCore['BUSCAR']}"
						valueChangeListener="#{carga.fileUploaded}" />
					<tr:commandButton text="#{resCore['CONTINUAR']}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
	</jsp:root>