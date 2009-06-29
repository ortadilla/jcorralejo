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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<tr:document>
		<trh:html>
		<trh:head title="#{resOrg['DETALLES_EQUIPAMIENTO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
		 <tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
				<tr:panelPage id="panel">
					<tr:panelHeader text="#{organizacion_detallesEquipamientoBean.descripcionEquipamiento}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:spacer width="10" height="10" />
					<tr:panelHorizontalLayout halign="center" inlineStyle="border=none; width=100%">
						<tr:panelBox background="medium" inlineStyle="width: 100%;">
								<tr:spacer width="10" height="20" />
								<tr:panelHorizontalLayout halign="left">
									<tr:outputLabel	inlineStyle="font-weight: bold;" value="#{resOrg['DESCRIPCION']}:" />
								</tr:panelHorizontalLayout>
								<tr:spacer width="10" height="10" />
								<tr:panelBox background="medium" inlineStyle="width: 100%;">
									<tr:panelHorizontalLayout halign="left">
											<tr:outputLabel
													value="#{organizacion_detallesEquipamientoBean.textoExplicativo}" />
									</tr:panelHorizontalLayout>
								</tr:panelBox>
								<tr:spacer width="10" height="20" />
								<tr:panelHorizontalLayout halign="left">
									<tr:outputLabel	inlineStyle="font-weight: bold;" value="#{resOrg['ARCHIVOS']}:" />
								</tr:panelHorizontalLayout>
								<tr:spacer width="10" height="10" />
								<tr:panelBox background="medium" inlineStyle="width: 100%;">
									<tr:panelHorizontalLayout halign="center">
										<tr:commandLink partialSubmit="true" id="primera"
											rendered="#{organizacion_detallesEquipamientoBean.mostrarBotones}"
											disabled="#{organizacion_detallesEquipamientoBean.anteriorDeshabilitada}"
											action="#{organizacion_detallesEquipamientoBean.archivoPrimero}"
											partialTriggers="siguiente ultima">
											<tr:image source="../imagenes/izda2.gif"
												inlineStyle="height: 18px;" shortDesc="#{resCat['PRIMERA']}" />
										</tr:commandLink>
										<tr:commandLink partialSubmit="true" id="anterior"
											rendered="#{organizacion_detallesEquipamientoBean.mostrarBotones}"
											disabled="#{organizacion_detallesEquipamientoBean.anteriorDeshabilitada}"
											action="#{organizacion_detallesEquipamientoBean.archivoAnterior}"
											partialTriggers="siguiente ultima">
											<tr:image source="../imagenes/izda.gif"
												inlineStyle="height: 18px;"
												shortDesc="#{resCat['ANTERIOR']}" />
										</tr:commandLink>
										<tr:spacer width="10" height="10" />
										<tr:commandLink partialSubmit="true" id="siguiente"
											rendered="#{organizacion_detallesEquipamientoBean.mostrarBotones}"
											disabled="#{organizacion_detallesEquipamientoBean.siguienteDeshabilitada}"
											action="#{organizacion_detallesEquipamientoBean.archivoSiguiente}"
											partialTriggers="primera anterior">
											<tr:image source="../imagenes/dcha.gif"
												inlineStyle="height: 18px;"
												shortDesc="#{resCat['SIGUIENTE']}" />
										</tr:commandLink>
										<tr:commandLink partialSubmit="true" id="ultima"
											rendered="#{organizacion_detallesEquipamientoBean.mostrarBotones}"
											disabled="#{organizacion_detallesEquipamientoBean.siguienteDeshabilitada}"
											action="#{organizacion_detallesEquipamientoBean.archivoUltimo}"
											partialTriggers="primera anterior">
											<tr:image source="../imagenes/dcha2.gif"
												inlineStyle="height: 18px;" shortDesc="#{resCat['ULTIMA']}" />
										</tr:commandLink>
										<tr:spacer width="10" height="10" />
									</tr:panelHorizontalLayout>
									<tr:panelHorizontalLayout halign="center">
										<tr:image partialTriggers="primera anterior siguiente ultima"
											rendered="#{organizacion_detallesEquipamientoBean.contieneArchivos}"
											source="/ficheros/#{organizacion_detallesEquipamientoBean.urlArchivo}"
											inlineStyle="height: 300px; border: solid 2px black;"
											shortDesc="#{organizacion_detallesEquipamientoBean.nombreArchivo}" />
									</tr:panelHorizontalLayout>
									<tr:spacer width="10" height="10" />
									<tr:panelHorizontalLayout halign="center">
										<tr:outputLabel
											rendered="#{organizacion_detallesEquipamientoBean.contieneArchivos}"
											partialTriggers="primera anterior siguiente ultima"
											value="#{organizacion_detallesEquipamientoBean.nombreArchivo}" />
									</tr:panelHorizontalLayout>
								</tr:panelBox>
							</tr:panelBox>
					</tr:panelHorizontalLayout>
					<tr:spacer width="10" height="10" />
					<tr:panelHorizontalLayout halign="center">
						<tr:commandButton text="#{resCore['VOLVER']}"
											action="#{organizacion_detallesEquipamientoBean.volver}" />
					</tr:panelHorizontalLayout>					
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</tr:document>
	</f:view>
</jsp:root>
