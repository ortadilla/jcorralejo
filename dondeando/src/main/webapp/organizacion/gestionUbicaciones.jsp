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
		<trh:head title="#{resOrg['GESTION_UBICACIONES']}">
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
				<tr:panelPage>
					<tr:panelHeader text="#{organizacion_gestionUbicacionesBean.tituloPanel}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox inlineStyle="width: 100%;" text="#{resOrg['UBICACIONES_TITULO']}">
						<tr:panelFormLayout inlineStyle="width: 100%;">
							<!-- Tabla Principal -->
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
								<!-- Inicio de la fila que contiene el panel de Estanterias -->
								<trh:rowLayout valign="top">
									<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
										<tr:panelBox inlineStyle="width:100%;" text="#{resOrg['ESTANTERIAS']}">
													<trh:tableLayout cellSpacing="0" cellPadding="2" borderWidth="1">
														<!-- Cabecera de los atributos -->
														<trh:rowLayout>
															<trh:cellFormat columnSpan="4">
																<tr:spacer height="15" width="5" />
															</trh:cellFormat>
															<trh:cellFormat halign="center" >
																<tr:outputText value="#{resOrg['NUMERO_ESTANTERIAS']}" />
															</trh:cellFormat>
															<trh:cellFormat halign="center">
																<tr:outputText value="#{resOrg['ANCHO']}" />
																<tr:outputText value="(#{resOrg['M']})" />
															</trh:cellFormat>
															<trh:cellFormat halign="center">
																<tr:outputText value="#{resOrg['ALTO']}" />
																<tr:outputText value="(#{resOrg['M']})" />
															</trh:cellFormat>
															<trh:cellFormat halign="center">
																<tr:outputText value="#{resOrg['PROFUNDIDAD']}" />
																<tr:outputText value="(#{resOrg['M']})" />
															</trh:cellFormat>
															<trh:cellFormat halign="center">
																<tr:outputText value="#{resOrg['NUMERO_NIVELES']}" />
															</trh:cellFormat>
														</trh:rowLayout>
														
														<!-- Primera fila para contener aquellas de estanterias desmontables -->
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="11">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['ESTANTERIAS_DESMONTABLES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.estanteriasDesmontables)}">
			        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
						               								</tr:commandLink>
					               								</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="3">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['CONVENCIONALES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.convencionales)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['DEBANDEJA']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.deBandeja)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:panelHorizontalLayout>
																	<tr:selectBooleanCheckbox id="selectActivarDeBandeja"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.deBandeja]}"/>
																	<tr:spacer height="15" width="5" />
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deBandeja]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deBandeja]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																			value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deBandeja]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"
																			columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deBandeja]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																			value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deBandeja]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"
																			columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deBandeja]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deBandeja]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deBandeja]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>	
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deBandeja]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deBandeja]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														
														<trh:rowLayout>
															<trh:cellFormat wrappingDisabled="true" halign="right">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['DEPALETIZACIONCOMPLETA']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.dePaletizacionCompleta)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarDePaletizacionCompleta"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionCompleta]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['DEPALETIZACIONMEDIA']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.dePaletizacionMedia)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarDePaletizacionMedia"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.dePaletizacionMedia]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																			columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.dePaletizacionMedia]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														
														
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="3">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['MOVILES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.moviles)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>	
															</trh:cellFormat>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['COMPACTAS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.movilesCompactas)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarMovilesCompactas"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.movilesCompactas]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.movilesCompactas]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.movilesCompactas]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.movilesCompactas]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.movilesCompactas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																		<tr:outputText value="#{resOrg['ROTATIVAS']} #{resOrg['HORIZONTALES']}" />
																		<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.horizontales)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
					               								</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarHorizontales"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.horizontales]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.horizontales]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.horizontales]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.horizontales]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.horizontales]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['ROTATIVAS']} #{resOrg['VERTICALES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.verticales)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarVerticales"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.verticales]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.verticales]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.verticales]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.verticales]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																	<tr:spacer height="15" width="5" />
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="2">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['COMPACTAS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.compactas)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarCompactas"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.compactas]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />	
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.compactas]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.compactas]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.compactas]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.compactas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['DINAMICAS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.dinamicas)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['DEGRAVEDAD']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.deGravedad)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarDeGravedad"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.deGravedad]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deGravedad]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deGravedad]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deGravedad]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.deGravedad]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['MOTORIZADAS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.motorizadas)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarMotorizadas"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.motorizadas]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.motorizadas]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.motorizadas]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.motorizadas]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.motorizadas]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['ESPECIALES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.especiales)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>	
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['CANTILEVER']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.cantilever)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarCantilever"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.cantilever]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>	
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.cantilever]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.cantilever]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>	
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.cantilever]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.cantilever]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>		
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['NICHOS']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.nichos)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarNichos"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.nichos]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.nichos]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																			columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.nichos]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.nichos]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.nichos]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true" columnSpan="3">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{resOrg['ESTANTERIAS_AUTOPORTANTES']}" />
																	<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.estanteriasAutoportantes)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>							
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="selectActivarestanteriasAutoportantes"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]}"/>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrCantidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrAncho]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																				[organizacion_gestionUbicacionesBean.atrAncho]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrAlto]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																				[organizacion_gestionUbicacionesBean.atrAlto]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																		value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																				[organizacion_gestionUbicacionesBean.atrProfundidad]}"
																		columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrProfundidad]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																	columns="12" />
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.estanteriasAutoportantes]
																			[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
													</trh:tableLayout>
										</tr:panelBox>
									</trh:cellFormat>
								</trh:rowLayout>
								<!-- Fin de la fila que contiene el panel de Estanterias -->
								<trh:rowLayout valign="top">
									<!-- Panel de Ubicacion Diáfana -->
										<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
											<tr:panelBox text="#{resOrg['UBICACIONES']}" inlineStyle="width:100%;">
												<trh:tableLayout cellSpacing="0" cellPadding="2" borderWidth="1">
													<trh:rowLayout>
														<trh:cellFormat columnSpan="3">
															<tr:spacer height="15" width="5" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['SUPERFICIE']}" />
															<tr:outputText value="(#{resOrg['M2']})" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['VOLUMEN']}" />
															<tr:outputText value="(#{resOrg['M3']})" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['NUMERO_NIVELES']}" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['TIPO_PRODUCTO']}" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['REGIMEN_PROPIEDAD']}" />
														</trh:cellFormat>														
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">
															<tr:outputText value="#{resOrg['UBICACION_DIAFANA']}" />
															<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.ubicacionDiafana)}">
	        	           										<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               							</tr:commandLink>
				               							</trh:cellFormat>
				               							<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['UN_SOLO_NIVEL']}" />
																<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.unSoloNivel)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:panelHorizontalLayout>
																<tr:selectBooleanCheckbox id="selectActivarUnSoloNivel"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.unSoloNivel]}"/>
																<tr:spacer height="15" width="5" />
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.unSoloNivel]
																		[organizacion_gestionUbicacionesBean.atrSuperficie]}"
																	columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.unSoloNivel]
																			[organizacion_gestionUbicacionesBean.atrSuperficie]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>	
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['APILABLE']}" />
																<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.apilable)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarApilable"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.apilable]}"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.apilable]
																		[organizacion_gestionUbicacionesBean.atrVolumen]}"
																	columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.apilable]
																			[organizacion_gestionUbicacionesBean.atrVolumen]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"  
																value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.apilable]
																	[organizacion_gestionUbicacionesBean.atrNumNiveles]}"
																columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.apilable]
																	[organizacion_gestionUbicacionesBean.atrNumNiveles]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['UBICACION_VARIAS']}" />
																<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.ubicacionVarias)}">
		        	           										<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
						               							</tr:commandLink>
						               						</tr:panelHorizontalLayout>	
				               							</trh:cellFormat>
				               							<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['TANQUES']}" />
																<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.tanques)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarTanques"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.tanques]}"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.tanques]
																		[organizacion_gestionUbicacionesBean.atrVolumen]}"
																	columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.tanques]
																			[organizacion_gestionUbicacionesBean.atrVolumen]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer height="15" width="5"/>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText 
																value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.tanques]
																	[organizacion_gestionUbicacionesBean.atrTipoProducto]}"
																columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.tanques]
																			[organizacion_gestionUbicacionesBean.atrTipoProducto]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText  
																value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.tanques]
																	[organizacion_gestionUbicacionesBean.atrRegimenPropiedad]}"
																columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.tanques]
																	[organizacion_gestionUbicacionesBean.atrRegimenPropiedad]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['SILOS']}" />
																<tr:commandLink useWindow="true" windowHeight="600" windowWidth="800" action="#{organizacion_gestionUbicacionesBean.detallesEquipamiento(organizacion_gestionUbicacionesBean.silos)}">
	        	           												<tr:image shortDesc="#{resOrg['DETALLES_ARCHIVOS']}" source="../imagenes/info_20.png"/>
					               									</tr:commandLink>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarSilos"   selected="#{organizacion_gestionUbicacionesBean.mapaActivados[organizacion_gestionUbicacionesBean.silos]}"/>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer width="10" height="10"/>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"  
																	value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.silos]
																		[organizacion_gestionUbicacionesBean.atrVolumen]}"
																	columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.silos]
																			[organizacion_gestionUbicacionesBean.atrVolumen]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:spacer width="10" height="10"/>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText  
																value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.silos]
																	[organizacion_gestionUbicacionesBean.atrTipoProducto]}"
																columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.silos]
																	[organizacion_gestionUbicacionesBean.atrTipoProducto]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText  
																value="#{organizacion_gestionUbicacionesBean.mapaValoresAtributos[organizacion_gestionUbicacionesBean.silos]
																	[organizacion_gestionUbicacionesBean.atrRegimenPropiedad]}"
																columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionUbicacionesBean.mapaEstadosSolicitudes[organizacion_gestionUbicacionesBean.silos]
																	[organizacion_gestionUbicacionesBean.atrRegimenPropiedad]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
													</trh:rowLayout>
												</trh:tableLayout>
											</tr:panelBox>
										</trh:cellFormat>
										<!-- Fin del Panel de Ubicación Diáfana -->
								</trh:rowLayout>
								<!--  Fin de la fila que contiene los paneles Ubicación Diáfana y Ubicación Varias -->
								<!-- Inicio de la fila que contiene los paneles de Estanterias Autoportantes y Motivo solicitud -->
								<trh:rowLayout valign="top">
									<!-- Inicio del panel de Motivo de solicitud -->
									<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
										<tr:panelBox inlineStyle="width:100%;" text="#{resOrg['MOTIVOSOLICITUD']}">
											<tr:inputText columns="60" rows="5" value="#{organizacion_gestionUbicacionesBean.motivoSolicitud}"/>
										</tr:panelBox>
									</trh:cellFormat>
									<!-- Fin del panel de Motivo de solicitud -->
								</trh:rowLayout>
								<!-- Fin de la fila que contiene los paneles de Estanterias Autoportantes y Motivo solicitud -->
							</trh:tableLayout>
							<!-- FIN DE LA TABLA PRINCIPAL -->
							
							<tr:spacer height="10" width="10" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCor['ACEPTAR']}"
									action="#{organizacion_gestionUbicacionesBean.aceptar}" />
								<tr:spacer height="15" width="10" />
								<tr:commandButton text="#{resCor['CANCELAR']}"
									action="#{organizacion_gestionUbicacionesBean.cancelar}" />
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