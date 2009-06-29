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

		<trh:head title="#{resOrg['MIGRACION_ARTICULOS_SAS_HOSPITALES_HIS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>

		<trh:html>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
				<tr:panelPage id="panelPage1">
					<tr:panelHeader text="#{organizacion_migracionBean.crearCabecera}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHorizontalLayout>
						<tr:inputFile
							label="#{organizacion_migracionBean.textoSeleccionFichero1}"
							value="#{organizacion_migracionBean.file}" />						
					</tr:panelHorizontalLayout>
					<tr:goLink text="#{resOrg['LINK_FICHERO_LINEAS_PROCESADAS_MIGRACION']}"
                             destination="#{organizacion_migracionBean.ficheroLineasProcesadas}"
                             targetFrame="_blank"
                             rendered="#{organizacion_migracionBean.mostrarLinkFicheroLineasProcesadas}"/>	
                    <tr:goLink text="#{resOrg['LINK_FICHERO_ERRORES_MIGRACION']}"
                             destination="#{organizacion_migracionBean.ficheroLineasErroneas}"
                             targetFrame="_blank"
                             rendered="#{organizacion_migracionBean.mostrarLinkFicheroLineasErroneas}"/>	
					<tr:panelHorizontalLayout>
						<tr:inputFile
							label="#{organizacion_migracionBean.textoSeleccionFichero2}"
							value="#{organizacion_migracionBean.file2}" />						
					</tr:panelHorizontalLayout>
                    <tr:goLink text="#{resOrg['LINK_FICHERO_LINEAS_PROCESADAS_MIGRACION']}"
                             destination="#{organizacion_migracionBean.ficheroLineasProcesadas2}"
                             targetFrame="_blank"
                             rendered="#{organizacion_migracionBean.mostrarLinkFicheroLineasProcesadas2}"/>	
                    <tr:goLink text="#{resOrg['LINK_FICHERO_ERRORES_MIGRACION']}"
                             destination="#{organizacion_migracionBean.ficheroLineasErroneas2}"
                             targetFrame="_blank"
                             rendered="#{organizacion_migracionBean.mostrarLinkFicheroLineasErroneas2}"/>                             
					<tr:panelLabelAndMessage label="#{resOrg['CLASIFICACION_LOCAL']}">
						<tr:panelHorizontalLayout>
							<tr:inputText id="id12" columns="10"
								value="#{organizacion_migracionBean.clasificacionLocal}" 
								rendered = "#{organizacion_migracionBean.mostrarClasificacionLocal}"/>
						</tr:panelHorizontalLayout>						
					</tr:panelLabelAndMessage>
					<tr:commandButton text="#{resCore['CONTINUAR']}" action="#{organizacion_migracionBean.subirFichero}"/>					
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>