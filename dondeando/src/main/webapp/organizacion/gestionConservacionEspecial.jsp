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
		<trh:head title="#{resOrg['GESTION_CONSERVACION_ESPECIAL']}">
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
						<tr:panelHeader text="#{organizacion_gestionConservacionEspecialBean.tituloPanel}"/>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox inlineStyle="width: 100%;" text="#{resOrg['ZONAS_CONSERVACION_TITULO']}">
							<tr:panelFormLayout fieldWidth="100%" inlineStyle="width: 100%;">
								<trh:tableLayout width="100%" cellSpacing="5" cellPadding="0" borderWidth="0">
									<trh:rowLayout valign="top">
										<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
											<tr:panelBox inlineStyle="width:100%;" text="#{resOrg['ZONAS_CONSERVACION_TITULO']}">
												<trh:tableLayout cellSpacing="0" cellPadding="2" borderWidth="1">
													<trh:rowLayout>
														<trh:cellFormat columnSpan="3">
															<tr:spacer height="15" width="5" />
														</trh:cellFormat>
														<trh:cellFormat halign="center">
															<tr:outputText value="#{resOrg['VOLUMEN']}" />
															<tr:outputText value="(#{resOrg['M3']})" />
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="2">
															<tr:outputText value="#{resOrg['TEMPERATURA_CONTROLADA']}" />
														</trh:cellFormat>
														<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['REFRIGERADA']}" />
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:panelHorizontalLayout>
																<tr:selectBooleanCheckbox id="selectActivarRefrigerada" selected="#{organizacion_gestionConservacionEspecialBean.mapaActivados[organizacion_gestionConservacionEspecialBean.zonaRefrigerada]}"/>
																<tr:spacer height="15" width="5" />
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"
																	value="#{organizacion_gestionConservacionEspecialBean.mapaValoresAtributos[organizacion_gestionConservacionEspecialBean.zonaRefrigerada]
																		[organizacion_gestionConservacionEspecialBean.atributoVolumen]}"
																	columns="12"/>
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionConservacionEspecialBean.mapaEstadosSolicitudes[organizacion_gestionConservacionEspecialBean.zonaRefrigerada]
																			[organizacion_gestionConservacionEspecialBean.atributoVolumen]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat halign="left" wrappingDisabled="true">
															<tr:panelHorizontalLayout>
																<tr:outputText value="#{resOrg['ISOTERMA']}" />
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
														<trh:cellFormat>
															<tr:selectBooleanCheckbox id="selectActivarIsoterma"   selected="#{organizacion_gestionConservacionEspecialBean.mapaActivados[organizacion_gestionConservacionEspecialBean.zonaIsoterma]}"/>
														</trh:cellFormat>
														<trh:cellFormat halign="left">
															<tr:panelHorizontalLayout>
																<tr:inputText contentStyle="text-align:right;"
																	value="#{organizacion_gestionConservacionEspecialBean.mapaValoresAtributos[organizacion_gestionConservacionEspecialBean.zonaIsoterma]
																		[organizacion_gestionConservacionEspecialBean.atributoVolumen]}"
																	columns="12" />
																<tr:spacer height="15" width="5" />
																<tr:image source="#{organizacion_gestionConservacionEspecialBean.mapaEstadosSolicitudes[organizacion_gestionConservacionEspecialBean.zonaIsoterma]
																			[organizacion_gestionConservacionEspecialBean.atributoVolumen]}"/>
															</tr:panelHorizontalLayout>
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<!--Al número de filas le sumamos 1 por la fila vacía que hay que colocar(ya que las filas con información se definen en el iterador)-->
														<trh:cellFormat halign="left" wrappingDisabled="true" rowSpan="#{organizacion_gestionConservacionEspecialBean.numMercanciasPeligrosas+1}">
															<tr:outputText value="#{resOrg['MERCANCIAS_PELIGROSAS']}" />
														</trh:cellFormat>
														<trh:cellFormat columnSpan="2" rendered="false"/>
													</trh:rowLayout>
													<tr:iterator id="it" var="item" varStatus="varStatus"
														value="#{organizacion_gestionConservacionEspecialBean.listaMercanciasPeligrosas}">
														<trh:rowLayout>
															<trh:cellFormat halign="left" wrappingDisabled="true">
																<tr:panelHorizontalLayout>
																	<tr:outputText value="#{item.descripcionEquipamiento}" />
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
															<trh:cellFormat>
																<tr:selectBooleanCheckbox id="select" selected="#{item.activar}"/>
															</trh:cellFormat>
<!--															Siempre va a tener un único atributo y será volumen-->
															<trh:cellFormat halign="left">
																<tr:panelHorizontalLayout>
																	<tr:inputText contentStyle="text-align:right;"
																		value="#{item.listaValorAtributoVista[0].valor}"
																		columns="12"/>
																	<tr:spacer height="15" width="5" />
																	<tr:image source="#{item.listaValorAtributoVista[0].imagenEstadoSolicitud}"/>
																</tr:panelHorizontalLayout>
															</trh:cellFormat>
														</trh:rowLayout>
													</tr:iterator>
												</trh:tableLayout>
											</tr:panelBox>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat halign="left" inlineStyle="border: solid 1px black;">
											<tr:panelBox text="#{resOrg['MOTIVOSOLICITUD']}">
												<tr:inputText columns="60" rows="5" value="#{organizacion_gestionConservacionEspecialBean.motivoSolicitud}"/>
											</tr:panelBox>
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
								<tr:spacer height="15" width="10" />
								<tr:panelHorizontalLayout halign="center">
									<tr:commandButton text="#{resCor['ACEPTAR']}"
										action="#{organizacion_gestionConservacionEspecialBean.aceptar}" />
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCor['CANCELAR']}"
										action="#{organizacion_gestionConservacionEspecialBean.cancelar}" />
									<tr:spacer width="10" />
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