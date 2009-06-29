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
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resCat['DATOS_ARTICULO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />
			
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form id="formArticulo" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader text="#{resCat['DATOS_ARTICULO']}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					
					<tr:panelHorizontalLayout rendered="#{catalogo_editarArticuloBean.mostrarPanelParalelo}"
					   				inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 100%; color: white; text-align: center;">
						   <tr:outputText value="#{resCat['CATALOGO_PARALELO']}"/>
					</tr:panelHorizontalLayout>	
					
					<tr:panelHorizontalLayout halign="center">
					<tr:panelBox>
						<tr:panelFormLayout inlineStyle="width: 100%;">
							<f:facet name="footer" />
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">

								<trh:rowLayout rendered="#{catalogo_editarArticuloBean.mostrarClasificacionSeleccionadas}">
									<tr:outputText value="#{resCat['CLASIFICACION']}" />
									<trh:cellFormat>
										<tr:inputText value="#{catalogo_editarArticuloBean.clasificacionesSeleccionadas}"
													  wrap="off" disabled="true" rows="3" columns="100"
													  id="clasificacionArticulo"/>
									</trh:cellFormat>
								</trh:rowLayout>

								<trh:rowLayout rendered="#{catalogo_editarArticuloBean.renderedCodArticulo}" >
									<tr:outputText value="#{resCat['CODARTICULO']}#{resCor['REQUERIDO']}" />
									<tr:panelHorizontalLayout>
									<tr:selectOneChoice
										unselectedLabel=""
										value="#{catalogo_editarArticuloBean.digitoCodigoArticulo}"
										id="codigoArticuloArticulo"
										disabled="#{catalogo_editarArticuloBean.deshabilitarCodArticulo}" > 
										<f:selectItems
											value="#{catalogo_editarArticuloBean.selectItemDigitos}" />
									</tr:selectOneChoice>
									<tr:spacer width="10" />
									<tr:outputText value="#{resCat['INDICAR_1_DIGITO_COD_ARTICULO']}" />
									</tr:panelHorizontalLayout>
								</trh:rowLayout>

								<trh:rowLayout>
									<tr:outputText value="#{resCor['NOMBRE']}#{resCor['REQUERIDO']}" />
									<tr:panelHorizontalLayout>
									<tr:inputText columns="#{catalogo_editarArticuloBean.valorColumnasDescripcionArticulo}"
											value="#{catalogo_editarArticuloBean.valorDescripcionArticulo}"
											disabled="#{catalogo_editarArticuloBean.deshabilitaDescripcionArticulo}" 
											id="nombreArticulo"/>
									<tr:inputText columns="49"
											rendered="#{catalogo_editarArticuloBean.renderedDescripcionAdicional}"
											value="#{catalogo_editarArticuloBean.valorDescripcionAdicionalArticulo}" 
											id="descripcionAdicionalArticulo"
											disabled="#{catalogo_editarArticuloBean.deshabilitarDescAdicionalArtic}"/>
									</tr:panelHorizontalLayout>
								</trh:rowLayout>

								<trh:rowLayout>
									<tr:outputText value="#{resCor['DESCRIPCION']}" />
									<trh:cellFormat columnSpan="3">
										<tr:inputText wrap="off" rows="5" columns="100"
											maximumLength="4000"
											value="#{catalogo_editarArticuloBean.valorDescripcionDetallada}" 
											disabled="#{catalogo_editarArticuloBean.deshabilitarDesDetallada}"
											id="descripcionArticulo"/>
									</trh:cellFormat>
								</trh:rowLayout>
						
								<trh:rowLayout>
									<tr:outputText value="#{resCat['NEMOTECNICO']}" />
									<trh:cellFormat columnSpan="3">
										<tr:inputText columns="103"
											value="#{catalogo_editarArticuloBean.valorNemotecnicoArticulo}" 
											id="nemotecnicoArticulo"
											disabled="#{catalogo_editarArticuloBean.deshabilitarNemo}"/>
									</trh:cellFormat>
								</trh:rowLayout>
						
								<trh:rowLayout rendered="#{!catalogo_editarArticuloBean.articulo.generico}">
									<tr:outputText value="#{resCat['GENERICO_CENTRO']}" />
										<tr:inputText
											disabled="#{catalogo_editarArticuloBean.disabled}"
											value="#{catalogo_editarArticuloBean.valorCodigoArticulo}" 
											id="codigoArticulo"/>
								</trh:rowLayout>
		
								<trh:rowLayout>
									<tr:outputText value="#{resCat['ESTADO_ARTICULO']}" />
										<tr:inputText
											disabled="#{catalogo_editarArticuloBean.disabled}"
											value="#{catalogo_editarArticuloBean.valorEstado}" 
											id="estadoArticulo"/>
								</trh:rowLayout>
		
								<trh:rowLayout>
									<tr:outputText value="#{resCat['FECHA_ALTA']}" />
										<tr:inputText
											disabled="#{catalogo_editarArticuloBean.disabled}"
											value="#{catalogo_editarArticuloBean.valorFechaAltaArticulo}"
											id="fechaAltaArticulo">
											<f:convertDateTime timeStyle="short"
											 />
										</tr:inputText>
								</trh:rowLayout>
		
								<trh:rowLayout>
									<tr:outputText value="#{resCor['OBSERVACIONES']}" />
										<tr:inputText columns="100" rows="3"
											value="#{catalogo_editarArticuloBean.valorObservacionesArticulo}" 
											id="observacionesArticulo"
											disabled="#{catalogo_editarArticuloBean.deshabilitarObservaciones}"/>
								</trh:rowLayout>
	
								<trh:rowLayout>
									<tr:outputText value="#{resCat['UNIDAD_MEDIDA_LOGISTICA']}#{catalogo_editarArticuloBean.unidadMedidaRequerida?resCor['REQUERIDO']:''}" />
										<tr:selectOneChoice
											value="#{catalogo_editarArticuloBean.valorUnidadMedidaLogisticaArticulo}"
											unselectedLabel=""
											disabled="#{catalogo_editarArticuloBean.deshabilitarUnidadMedLogistica}"
											id="unidadMedLogArticulo"
											partialTriggers="unidadMedContArticulo">
											<f:selectItems
												value="#{catalogo_editarArticuloBean.selectItemUnidadMedida}" />
										</tr:selectOneChoice>
								</trh:rowLayout>
				
								<trh:rowLayout>
									<tr:outputText value="#{resCat['UNIDAD_CONTRATACION']}#{catalogo_editarArticuloBean.unidadContratacionRequerida?resCor['REQUERIDO']:''}" />
										<tr:selectOneChoice
											value="#{catalogo_editarArticuloBean.valorUnidadContratacionArticulo}"
											unselectedLabel=""
											disabled="#{catalogo_editarArticuloBean.deshabilitarUnidadContrat}"
											valueChangeListener="#{catalogo_editarArticuloBean.refrescarComboLogistica}"
											autoSubmit="true"
											id="unidadMedContArticulo">
											<f:selectItems
												value="#{catalogo_editarArticuloBean.selectItemUnidadContratacion}" />
										</tr:selectOneChoice>
								</trh:rowLayout>
			
								<trh:rowLayout
									rendered="#{catalogo_editarArticuloBean.mostrarInputMotivoSolicitud}">
									<tr:outputText value="#{resOrg['MOTIVO_SOLICITUD']}#{resCor['REQUERIDO']}" />
										<tr:inputText rows="5" columns="100"
											value="#{catalogo_editarArticuloBean.valorMotivoSolicitud}"
											rendered="#{catalogo_editarArticuloBean.mostrarInputMotivoSolicitud}"
											id="motivoSolArticulo" />
								</trh:rowLayout>
							</trh:tableLayout>
							
							<trh:rowLayout>
								<tr:panelHorizontalLayout>
									<tr:commandButton text="#{resCor['ACEPTAR']}"
										action="#{catalogo_editarArticuloBean.aceptar}" 
										id="aceptar"/>
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCor['CANCELAR']}"
										action="#{catalogo_editarArticuloBean.cancelar}" 
										id="cancelar"/>
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCat['VER_SOLICITUDES']}"
										action="#{catalogo_editarArticuloBean.verSolicitudesDeOtrosCentros}" 
										rendered="#{catalogo_editarArticuloBean.mostrarBotonVerSolicitudesOtrosOrganos}"
										id="verSolicitudes"/>
									<tr:spacer width="10" height="10" />
                                    <tr:commandLink id="mostrarDocsAsociados"
                                                    rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1] != null}"
                                                    shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1]}"
                                                    actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
                                                    action="dialog:mostrarDocumentacion"
                                                    useWindow="true" windowWidth="600" windowHeight="300">
                                        <f:attribute name="entidadMostrada" value="1"/>
                                        <tr:image source="/imagenes/info_20.png"/>
                                    </tr:commandLink>       
								</tr:panelHorizontalLayout>
							</trh:rowLayout>
						</tr:panelFormLayout>
					</tr:panelBox>
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
