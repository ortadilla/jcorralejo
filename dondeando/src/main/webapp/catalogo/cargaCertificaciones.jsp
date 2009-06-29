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
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
	
		<trh:html>
		<trh:head title="#{resCat['CARGA_CERTIFICACIONES_HOMOLOGACIONES']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
			
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
				<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>

						<tr:panelHorizontalLayout>
							<tr:inputText label="#{resCat['NUMERO_INCIDENCIA']}"
										  rendered="#{catalogo_cargaCertificacionesBean.mostrarIncidencia}"
										  value="#{catalogo_cargaCertificacionesBean.numeroIncidencia}" >
							</tr:inputText>
							<tr:spacer width="30" />
							<tr:inputFile label="#{resCat['ARCHIVO_ALTERNATIVO']}"
										  rendered="#{catalogo_cargaCertificacionesBean.mostrarArchivoAlternativo}"
				                          value="#{catalogo_cargaCertificacionesBean.fileHomologacionUpload}" />	
				                          
                            <tr:panelHorizontalLayout rendered="#{catalogo_cargaCertificacionesBean.mostrarFechaInicioHomologacion}">
<!--								<tr:outputText value="#{resCat['FECHA_INICIO_HOMOLOGACION']}" /> -->
								<tr:inputDate value="#{catalogo_cargaCertificacionesBean.fechaInicioCertificacion}" 
			  								  label="#{resCat['FECHA_INICIO_HOMOLOGACION']}"/>
							</tr:panelHorizontalLayout>
							<tr:inputFile label="#{resCat['ARCHIVO_CRITERIOS']}"
										  rendered="#{catalogo_cargaCertificacionesBean.mostrarArchivoProtocoloEvaluacion}"
				                          value="#{catalogo_cargaCertificacionesBean.fileProtocolosEvaluacionUpload}" />
						</tr:panelHorizontalLayout>
						<tr:outputText value="#{resCat['ARCHIVO_ALTERNATIVO_EXPLICACION']}" 
									   rendered="#{catalogo_cargaCertificacionesBean.mostrarArchivoAlternativo}"/>
						
						<tr:commandButton text="#{resCore['CONTINUAR']}" 
										  action="#{catalogo_cargaCertificacionesBean.obtenerIncidencia}"
										  rendered="#{catalogo_cargaCertificacionesBean.mostrarContinuar}"/>
						<tr:panelBox rendered="#{catalogo_cargaCertificacionesBean.mostrarCarga}">
							<tr:outputText rendered= "#{catalogo_cargaCertificacionesBean.mostrarCarga}" 
									   	   value="#{resCat['SELECCION_ARCHIVOS']}"/>
							<tr:spacer width="30" />
							<tr:selectOneChoice label = "#{resCat['FICHERO_PRINCIPAL']}"
												value = "#{catalogo_cargaCertificacionesBean.filePrincipal}">
												<f:selectItems id="selectItems1" 
													value="#{catalogo_cargaCertificacionesBean.listaArchivosPrincipal}"/>
							</tr:selectOneChoice>
							<tr:spacer width="20" />
							<tr:selectOneChoice label = "#{resCat['FICHERO_VAL_TEC']}"
												value = "#{catalogo_cargaCertificacionesBean.fileValTec}">
												<f:selectItems id="selectItems2" 
													value="#{catalogo_cargaCertificacionesBean.listaArchivosValTecnica}"/>
							</tr:selectOneChoice>
							<tr:spacer width="20" />
							<tr:selectOneChoice label = "#{resCat['FICHERO_VAL_EAN']}"
												value = "#{catalogo_cargaCertificacionesBean.fileValEAN}">
												<f:selectItems id="selectItems3" 
													value="#{catalogo_cargaCertificacionesBean.listaArchivosValEan}"/>
							</tr:selectOneChoice>
							<tr:spacer width="20" />
							<tr:selectOneChoice label = "#{resCat['FICHERO_PAQUETE_MED']}"
												value = "#{catalogo_cargaCertificacionesBean.filePaquetesMed}">
												<f:selectItems id="selectItems4" 
													value="#{catalogo_cargaCertificacionesBean.listaArchivosPaquetesMed}"/>
							</tr:selectOneChoice>
							<tr:spacer width="20" />
							<tr:panelHorizontalLayout>
								<tr:commandButton text="#{resCore['CONTINUAR']}" action="#{catalogo_cargaCertificacionesBean.subirFicheros}"/>					
								<tr:spacer width="20" />
								<tr:commandButton text="#{resCore['VOLVER']}" action="#{catalogo_cargaCertificacionesBean.cancelar}"/>					
							</tr:panelHorizontalLayout>
						</tr:panelBox>
						<tr:panelBox rendered="#{catalogo_cargaCertificacionesBean.mostrarAcciones}">
							<tr:outputText value="#{resCat['ACCIONES_PROD_NO_CERTIFICADOS']}"/>
							<tr:panelHorizontalLayout>
								<tr:spacer width="20" />
								<tr:selectBooleanCheckbox value="#{catalogo_cargaCertificacionesBean.aprobar}"/>
								<tr:outputText value="#{resCat['APROBAR']}" />
								<tr:spacer width="20" />
								<tr:selectBooleanCheckbox value="#{catalogo_cargaCertificacionesBean.borrar}"/>
								<tr:outputText value="#{resCore['BORRAR']}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="20" />
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['PROCESAR_VAL_LOGIST']}"/>
								<tr:spacer width="20" />
								<tr:selectBooleanCheckbox value="#{catalogo_cargaCertificacionesBean.procesarValidLog}"/>
							</tr:panelHorizontalLayout>
							<tr:spacer height="10" />
							<tr:panelGroupLayout>
								<tr:commandButton text="#{resCore['ACEPTAR']}"
									action="#{catalogo_cargaCertificacionesBean.aceptar}"
									rendered="#{!catalogo_cargaCertificacionesBean.errores}" />
								<tr:commandButton text="#{resCore['CANCELAR']}"
									action="#{catalogo_cargaCertificacionesBean.cancelar}" />
							</tr:panelGroupLayout>
						</tr:panelBox>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
