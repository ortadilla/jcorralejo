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
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html>
		<trh:head title="#{resOrg['GESTION_MEDIOS_OPERACION']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage >
						<tr:panelHeader text="#{organizacion_gestionMediosOperacionBean.tituloPanel}"/>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox inlineStyle="width: 100%;" text="#{resOrg['MEDIOSOPERACION_TITULO']}">
							<tr:panelFormLayout fieldWidth="100%" inlineStyle="width: 100%;">
								<!-- Tabla Principal -->
								<trh:tableLayout width="100%" cellSpacing="5" cellPadding="0" borderWidth="0">
									<!-- Inicio de la fila que contiene el panel de Estanterias desmontables -->
									<trh:rowLayout valign="top">
										<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
											<tr:panelBox inlineStyle="width:100%;" text="#{resOrg['MEDIOSOPERACION']}">
												<trh:tableLayout cellSpacing="0" cellPadding="2" borderWidth="1" >
														<trh:rowLayout>
															<trh:cellFormat columnSpan="5">
																<tr:spacer height="15" width="5" />
															</trh:cellFormat>
															<trh:cellFormat halign="center">
																<tr:outputText value="#{resOrg['NUMERO_UNIDADES']}" />
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat rowSpan="12" halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['VEHICULOS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600"
																		windowWidth="800"
																		action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.vehiculos)}">
																		<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"
																			source="../imagenes/info_20.png" />
																	</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat columnSpan="3" halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['CARRITOS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600"
																		windowWidth="800"
																		action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.carritos)}">
																		<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"
																			source="../imagenes/info_20.png" />
																	</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:panelHorizontalLayout>
																	<tr:selectBooleanCheckbox id="selectActivarCarritos"
																			selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.carritos]}" />
																	<tr:spacer height="15" width="5" />
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																	<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.carritos]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12"/>																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.carritos]																	[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>
													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['TRANSPALETA']}" />																<tr:commandLink useWindow="true" windowHeight="600"																	windowWidth="800"																	action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.transpaleta)}">																	<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																		source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>														<trh:cellFormat columnSpan="2" halign="left" wrappingDisabled="true">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['MANUALES']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.manuales)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarManuales"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.manuales]}"/>
														</trh:cellFormat>														<trh:cellFormat columnSpan="2" halign="left">
															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.manuales]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.manuales]																	[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>
													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['MOTORIZADAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.motorizadas)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarMotorizadas"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.motorizadas]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 
																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.motorizadas]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.motorizadas]																	[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['APILADORAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.apiladoras)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarApiladoras"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.apiladoras]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.apiladoras]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.apiladoras]																	[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="5">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['CARRETILLAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																	windowWidth="800"																	action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.carretillas)}">																	<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																		source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['CONTRAPESADA']}" />																<tr:commandLink useWindow="true" windowHeight="600"																		windowWidth="800"																		action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.contrapesada)}">																		<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																			source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>														<trh:cellFormat halign="left" wrappingDisabled="true">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['DEPALETAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.depaletas)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarDePaletas"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.depaletas]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.depaletas]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.depaletas]																	[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['DECONTENEDORES']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.decontenedores)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarDeContenedores"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.decontenedores]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.decontenedores]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.decontenedores]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['RETRACTIL']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.retractil)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarRetractil"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.retractil]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.retractil]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.retractil]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['DEHORQUILLAGIRATORIA']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.deHorquillaGiratoria)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarDeHorquillaGiratoria"   
																	selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.deHorquillaGiratoria]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.deHorquillaGiratoria]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.deHorquillaGiratoria]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="2">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['PREPARADORAPEDIDOS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.preparadoraPedidos)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectActivarPreparador"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.preparadoraPedidos]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.preparadoraPedidos]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.preparadoraPedidos]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['TRANSELEVADORAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.transelevadoras)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectTranselevadoras"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.transelevadoras]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.transelevadoras]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.transelevadoras]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['PUENTESGRUA']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.puentesGrua)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectPuentesGrua"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.puentesGrua]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.puentesGrua]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.puentesGrua]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['AGV']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.agv)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectAGV"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.agv]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.agv]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.agv]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" rowSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['DISPOSITIVOSDESPLAZAMIENTO']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.dispositivosDesplazamiento)}">																	<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																		source="../imagenes/info_20.png" />																</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['CADENAS_TRANSPORTADORAS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.cadenasTransportadoras)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectCadenasTransportadoras"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.cadenasTransportadoras]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.cadenasTransportadoras]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.cadenasTransportadoras]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['TRANSPORTADORES_AEREOS']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.transportadoresAereos)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectTransportadoresAereos"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.transportadoresAereos]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.transportadoresAereos]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.transportadoresAereos]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>													<trh:rowLayout>														<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">															<tr:panelHorizontalLayout>																<tr:outputText value="#{resOrg['CLASIFICADORES']}" />																<tr:commandLink useWindow="true" windowHeight="600"																windowWidth="800"																action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionMediosOperacionBean.clasificadores)}">																<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}"																	source="../imagenes/info_20.png" />															</tr:commandLink>															</tr:panelHorizontalLayout>														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox  id="selectClasificadores"   
																selected="#{organizacion_gestionMediosOperacionBean.mapaActivados[organizacion_gestionMediosOperacionBean.clasificadores]}"/>
														</trh:cellFormat>														<trh:cellFormat halign="left">															<tr:panelHorizontalLayout>																<tr:inputText contentStyle="text-align:right;" 																value="#{organizacion_gestionMediosOperacionBean.mapaValoresAtributos[organizacion_gestionMediosOperacionBean.clasificadores]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"																columns="12" />																<tr:spacer height="15" width="5" />																<tr:image source="#{organizacion_gestionMediosOperacionBean.mapaEstadosSolicitudes[organizacion_gestionMediosOperacionBean.clasificadores]																[organizacion_gestionMediosOperacionBean.atributoCantidad]}"/>															</tr:panelHorizontalLayout>														</trh:cellFormat>													</trh:rowLayout>
												</trh:tableLayout>
											</tr:panelBox>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
											<tr:panelBox inlineStyle="width: 100%;" text="#{resOrg['MOTIVOSOLICITUD']}">
												<tr:inputText columns="60" rows="5" value="#{organizacion_gestionMediosOperacionBean.motivoSolicitud}"/>
											</tr:panelBox>
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
								<tr:spacer height="15" width="10" />
								<tr:panelHorizontalLayout halign="center">
									<tr:commandButton text="#{resCor['ACEPTAR']}"
										action="#{organizacion_gestionMediosOperacionBean.aceptar}" />
									<tr:spacer height="15" width="10" />
									<tr:commandButton text="#{resCor['CANCELAR']}"
										action="#{organizacion_gestionMediosOperacionBean.cancelar}" />
									<tr:spacer height="15" width="10" />
								</tr:panelHorizontalLayout>
							</tr:panelFormLayout>
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