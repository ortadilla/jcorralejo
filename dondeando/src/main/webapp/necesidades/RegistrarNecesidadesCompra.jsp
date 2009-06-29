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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<trh:html>
		<trh:head title="#{resNec['REGISTRO_PEDIDOS_INTERNOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="aceptar" id="formRegistrarNecesidadesCompra" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resNec['REGISTRO_PEDIDOS_INTERNOS']}" />
					<tr:panelBox background="medium" inlineStyle="width: 100%;">
						<tr:panelHorizontalLayout
							partialTriggers="tipoOrigen cancelar nueva">
							<tr:selectOneChoice label="#{resNec['ORIGEN']}" id="tipoOrigen"
								autoSubmit="true" unselectedLabel=""
								partialTriggers="aceptar cancelar nueva"
								valueChangeListener="#{necesidades_peticionNecesidades.cambiarTipoOrigen}"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
								value="#{necesidades_peticionNecesidades.tipoOrigenId}">
								<f:selectItems id="selectItems1"
									value="#{necesidades_peticionNecesidades.listaTipoOrigen}" />
							</tr:selectOneChoice>
							<tr:spacer width="10" height="10" />
							<tr:switcher
								facetName="#{necesidades_peticionNecesidades.switcherOrigen}">
								<f:facet name="centro">
									<tr:selectOneChoice id="valorComboCentroConsumo"
										autoSubmit="true" unselectedLabel=""
										partialTriggers="tipoOrigen aceptar"
										valueChangeListener="#{necesidades_peticionNecesidades.cambiarCentroConsumo}"
										disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
										value="#{necesidades_peticionNecesidades.centroConsumoId}">
										<f:selectItems id="selectItems3"
											value="#{necesidades_peticionNecesidades.listaCentroConsumo}" />
									</tr:selectOneChoice>
								</f:facet>
								<f:facet name="almacen">
									<tr:selectOneChoice id="valorComboAlmacen"
										partialTriggers="tipoOrigen aceptar" autoSubmit="true"
										unselectedLabel=""
										valueChangeListener="#{necesidades_peticionNecesidades.cambiarUbicacion}"
										disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
										value="#{necesidades_peticionNecesidades.ubicacionId}">
										<f:selectItems id="selectItemsComboAlmacen"
											value="#{necesidades_peticionNecesidades.listaUbicaciones}" />
									</tr:selectOneChoice>
								</f:facet>
								<f:facet name="otro">
									<tr:inputText columns="10" id="valorTexto"
										partialTriggers="tipoOrigen aceptar" autoSubmit="true"
										disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}" />
								</f:facet>
							</tr:switcher>
						</tr:panelHorizontalLayout>
						<tr:spacer width="10" height="10" />
						<tr:panelHorizontalLayout
							partialTriggers="prioridad tipoOrigen aceptar tipoPeticion">
							<tr:selectOneChoice label="#{resNec['TIPO_PETICION']}"
								autoSubmit="true" id="tipoPeticion"
								valueChangeListener="#{necesidades_peticionNecesidades.cambiarTipoPeticion}"
								binding="#{necesidades_peticionSuministrosBinding.tipoPeticion}"
								unselectedLabel="" partialTriggers="aceptar cancelar nueva"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera || necesidades_peticionNecesidades.deshabilitarTipoPeticion}"
								value="#{necesidades_peticionNecesidades.tipoPeticionId}">
								<f:selectItems id="selectItems6"
									value="#{necesidades_peticionNecesidades.listaTipoPeticion}" />
							</tr:selectOneChoice>
							<tr:spacer width="10" height="10" />
							<!--<tr:outputLabel value="Valor obtenido" />-->
							<!--<tr:outputLabel value="#{necesidades_peticionNecesidades.switcherPeticion}" />-->
							<tr:outputLabel
								rendered="#{necesidades_peticionNecesidades.catalogos}"
								value="#{resCat['CATALOGO']}" />
							<tr:selectOneChoice id="valorComboCatalogo" autoSubmit="true"
								partialTriggers="tipoOrigen aceptar valorComboCentroConsumo tipoPeticion"
								unselectedLabel=""
								valueChangeListener="#{necesidades_peticionNecesidades.cambiarCatalogo}"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
								rendered="#{necesidades_peticionNecesidades.catalogos}"
								value="#{necesidades_peticionNecesidades.catalogoId}">
								<f:selectItems id="selectItems4"
									value="#{necesidades_peticionNecesidades.listaCatalogo}" />
							</tr:selectOneChoice>
						</tr:panelHorizontalLayout>
						<tr:spacer width="10" height="10" />
						<tr:panelHorizontalLayout
							partialTriggers="prioridad tipoOrigen aceptar">
							<tr:selectOneChoice label="#{resNec['PRIORIDAD']}" id="prioridad"
								autoSubmit="true" unselectedLabel=""
								partialTriggers="aceptar cancelar nueva"
								valueChangeListener="#{necesidades_peticionNecesidades.cambiarPrioridad}"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
								value="#{necesidades_peticionNecesidades.tipoPrioridadId}">
								<f:selectItems id="selectItems5"
									value="#{necesidades_peticionNecesidades.listaTipoPrioridad}" />
							</tr:selectOneChoice>
							<tr:spacer width="10" height="10" />
							<tr:inputText columns="61" label="#{resNec['RAZON']}"
								id="razonPrioridad" partialTriggers="prioridad"
								autoSubmit="false"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera || !necesidades_peticionNecesidades.urgente}"
								rendered="#{necesidades_peticionNecesidades.urgente}"
								value="#{necesidades_peticionNecesidades.razonPrioridad}" />
						</tr:panelHorizontalLayout>
						<tr:spacer width="10" height="10" />
						<tr:panelHorizontalLayout partialTriggers="tipoOrigen aceptar">
							<tr:inputText label="#{resCore['OBSERVACIONES']}"
								id="observaciones" columns="75" rows="1"
								partialTriggers="aceptar tipoOrigen cancelar nueva"
								binding="#{necesidades_peticionSuministrosBinding.observaciones}"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}" />
						</tr:panelHorizontalLayout>	
						<tr:panelButtonBar halign="right" inlineStyle="width: 100%;"
							partialTriggers="aceptar cancelar nueva">
                            <tr:commandLink id="mostrarDocsAsociados"
                                            rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[3] != null}"
                                            shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[3]}"
                                            actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
                                            action="dialog:mostrarDocumentacion"
                                            useWindow="true" windowWidth="600" windowHeight="300">
                                <f:attribute name="entidadMostrada" value="3"/>
                                <tr:image source="/imagenes/info_20.png"/>
                            </tr:commandLink>       
							<tr:spacer width="10" height="0" />
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="aceptar"
								rendered="true" partialTriggers="aceptar cancelar nueva"
								disabled="#{necesidades_peticionNecesidades.deshabilitarCabecera}"
								action="#{necesidades_peticionNecesidades.btnaceptar}"
								blocking="true" />
						</tr:panelButtonBar>
					</tr:panelBox>
					<tr:spacer width="10" height="10" />
					<tr:switcher
						binding="#{necesidades_peticionSuministrosBinding.swTabla}"
						facetName="#{necesidades_peticionNecesidades.switcherMuestraTabla}">
						<f:facet name="muestraTabla">
							<tr:panelBox id="panelArticulos" background="medium"
								text="#{resNec['DETALLES_PEDIDO']}" inlineStyle="width: 100%;"
								partialTriggers="aceptar nuevo tablaLineasDirecta tablaLineas tablaAlmacen"
								rendered="true">
								<tr:panelButtonBar halign="right" inlineStyle="width: 100%;">
									<tr:commandButton text="#{resNec['ANADIR_NUEVA_LINEA']}"
										id="nuevo" partialTriggers="aceptar guardar"
										actionListener="#{necesidades_peticionNecesidades.anadirLinea}"
										disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
										rendered="#{necesidades_peticionNecesidades.directa}" />
									<tr:commandButton text="#{resNec['ANADIR_ARTICULOS']}"
										id="aniadir_articulos" partialTriggers="aceptar guardar"
										action="#{necesidades_peticionNecesidades.buscarArticulosPorUbicacion}"
										disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
										rendered="#{necesidades_peticionNecesidades.almacen}" />
									<tr:commandButton text="#{resNec['ANIADIR_PRODUCTO']}"
										id="aniadir_producto" partialTriggers="aceptar guardar"
										action="#{necesidades_peticionNecesidades.buscarProductoPorArticulo}"
										disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
										rendered="#{necesidades_peticionNecesidades.almacen}" />
									<tr:commandButton text="#{resNec['NUEVA_PETICION']}" id="nueva"
										partialTriggers="aceptar guardar"
										disabled="#{!necesidades_peticionNecesidades.peticionGuardada}"
										action="#{necesidades_peticionNecesidades.nuevaSolicitud}" />
									<tr:commandButton text="#{resCore['CANCELAR']}" id="cancelar"
										partialTriggers="aceptar guardar"
										disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
										action="#{necesidades_peticionNecesidades.cancelar}" />
									<tr:commandButton text="#{resCore['GUARDAR']}"
										partialTriggers="aceptar enviar" id="guardar"
										disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
										actionListener="#{necesidades_peticionNecesidades.guardarPeticion}"
										blocking="true" />
									<tr:commandButton text="#{resNec['ENVIAR_PETICION']}"
										id="enviar" partialTriggers="aceptar guardar"
										actionListener="#{necesidades_peticionNecesidades.enviarPeticion}"
										blocking="true"
										disabled="#{!necesidades_peticionNecesidades.peticionGuardada || necesidades_peticionNecesidades.peticionEnviada}" />
								</tr:panelButtonBar>
								<tr:switcher
									binding="#{necesidades_peticionSuministrosBinding.swTablaDirecta}"
									facetName="#{necesidades_peticionNecesidades.switcherTablaDirecta}">
									<f:facet name="directa">
										<tr:table width="100%"
											emptyText="#{resNec['ANADIR_NUEVA_LINEA_INDICACION']}"
											rows="10" rowBandingInterval="1" columnBandingInterval="0"
											allDetailsEnabled="false" var="articPeticion2"
											binding="#{necesidades_peticionSuministrosBinding.tablaLineasDirecta}"
											id="tablaLineasDirecta"
											rowDisclosureListener="#{necesidades_peticionNecesidades.calculaConsumoAnualDirecta}">
											<!--partialTriggers="selectOneChoiceArticuloDirecta unidadMedida">-->
											<tr:column sortable="true" headerText="#{resCat['ARTICULO']}"
												align="start" id="columnaSelectArticulo">
												<tr:selectOneChoice
													disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
													valueChangeListener="#{necesidades_peticionNecesidades.obtienePosicionArticulo}"
													id="selectOneChoiceArticuloDirecta" autoSubmit="true"
													value="#{articPeticion2.articuloSel}"
													binding="#{necesidades_peticionSuministrosBinding.selectOneChoiceArticuloDirecta}">
													<f:selectItems value="#{articPeticion2.articulos}"
														id="selectItemsArticuloDirecta" />
													<!--binding="#{necesidades_peticionSuministrosBinding.selectItemsArticuloDirecta }" />-->
												</tr:selectOneChoice>
											</tr:column>
											<tr:column sortable="true"
												headerText="#{resCat['PRODUCTO_REFERENCIA_FABRICANTE']}"
												align="start" id="cipRef">
												<tr:selectOneChoice
													partialTriggers="selectOneChoiceArticuloDirecta"
													disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
													valueChangeListener="#{necesidades_peticionNecesidades.obtieneProducto}"
													id="selectOneChoice1Productos" autoSubmit="true"
													value="#{articPeticion2.productoSel}"
													binding="#{necesidades_peticionSuministrosBinding.selectOneChoice1Productos}">
													<f:selectItems value="#{articPeticion2.productos}"
														id="selectItems2" />
													<!--binding="#{necesidades_peticionSuministrosBinding.selectItemsProductoDirecta}"/>-->
												</tr:selectOneChoice>
											</tr:column>
											<tr:column sortable="true" headerText="#{resNec['CANTIDAD']}"
												align="start" id="column4Catalogo">
												<tr:inputText value="#{articPeticion2.cantidad}"
													columns="10" id="inputText6Catalogo" autoSubmit="true"
													disabled="#{necesidades_peticionNecesidades.peticionGuardada}" />
											</tr:column>
											<tr:column sortable="true"
												headerText="#{resCat['UNIDAD_MEDIDA']}" align="start"
												id="unidadMedidaDirecta">
												<!--partialTriggers="selectOneChoiceArticuloDirecta">-->
												<tr:panelHorizontalLayout
													partialTriggers="selectOneChoiceArticuloDirecta">
													<tr:outputText value="#{resNec['ENVASES_DE']}" />
													<tr:selectOneChoice
														partialTriggers="selectOneChoice1Productos selectOneChoiceArticuloDirecta"
														disabled="#{necesidades_peticionNecesidades.peticionGuardada}"
														value="#{articPeticion2.presentacionId}"
														valueChangeListener="#{necesidades_peticionNecesidades.obtienePresentacion}"
														binding="#{necesidades_peticionSuministrosBinding.selectOneChoicePresentacion}"
														id="selectOneChoice1UnidadMedida">
														<f:selectItems value="#{articPeticion2.presentaciones}"
															id="selectItemsPresentacion" />
														<!--binding="#{necesidades_peticionSuministrosBinding.selectItemsPresentacionDirecta}"/>-->
													</tr:selectOneChoice>
													<tr:outputText value="#{articPeticion2.unidadDeMedida}"
														partialTriggers="selectOneChoiceArticuloDirecta" />
												</tr:panelHorizontalLayout>
											</tr:column>
											<f:facet name="detailStamp">
												<trh:tableLayout halign="center">
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['CONSUMO_ULTIMO_ANIO']}" />
														<tr:inputText columns="10"
															value="#{necesidades_peticionNecesidades.obtieneConsumoAnualCalculado}"
															disabled="true"
															partialTriggers="selectOneChoiceArticuloDirecta"
															id="consumoUltimoAnio" />
														<tr:outputLabel value="#{resNec['UNIDADES']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['PROYECCION_SOLICITUD']}" />
														<tr:inputText columns="10"
															value="#{necesidades_peticionNecesidades.actualizaProyeccionAnualDirecta}"
															disabled="true" partialTriggers="inputText6Catalogo" 
															id="proyeccionSolicitud"/>
														<tr:outputLabel value="#{resNec['DIAS']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['OBSERVACIONES']}" />
														<trh:cellFormat columnSpan="2">
															<tr:inputText rows="2"
																value="#{articPeticion2.observaciones}" disabled="true"
																partialTriggers="selectOneChoice1Productos selectOneChoiceArticuloDirecta" 
																id="articPeticion2observaciones"/>
														</trh:cellFormat>
													</trh:rowLayout>
												</trh:tableLayout>
											</f:facet>
										</tr:table>
									</f:facet>
									<f:facet name="catalogo">
										<tr:table emptyText="#{resCat['NO_ARTICULOS_DEFINIDOS']}"
											rows="10" allDetailsEnabled="false" var="articPeticion"
											rendered="true" rowBandingInterval="1"
											columnBandingInterval="0" width="100%"
											binding="#{necesidades_peticionSuministrosBinding.tablaLineas}"
											partialTriggers="nuevo" id="tablaLineas">
											<tr:column sortable="true" headerText="#{resCat['ARTICULO']}"
												align="start" id="column2" sortProperty="descripcion">
												<tr:outputText value="#{articPeticion.descripcion}"
													id="outputTextDirecta" truncateAt="60"
													shortDesc="#{articPeticion.descripcion}" />
											</tr:column>
											<tr:column sortable="true" headerText="#{resNec['CANTIDAD']}"
												align="start" id="column4">
												<tr:inputText value="#{articPeticion.cantidad}" columns="10"
													id="inputText6"
													disabled="#{necesidades_peticionNecesidades.peticionGuardada}" />
											</tr:column>
											<tr:column sortable="true"
												headerText="#{resCat['UNIDAD_MEDIDA']}" align="start"
												id="column3">
												<tr:outputText value="#{articPeticion.unidadDeMedida}" />
											</tr:column>
											<tr:column sortable="true" headerText="#{resCat['CIP']}"
												align="start" id="column5">
												<tr:outputText value="#{articPeticion.CIP}" id="cip" />
											</tr:column>
											<tr:column sortable="true"
												headerText="#{resCat['REF_FABRICANTE']}" align="start"
												id="column6">
												<tr:outputText value="#{articPeticion.refFabricante}"
													id="reffabricante" />
											</tr:column>
											<f:facet name="detailStamp">
												<trh:tableLayout halign="center">
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['CONSUMO_ULTIMO_ANIO']}" />
														<tr:inputText columns="10"
															value="#{necesidades_peticionNecesidades.calculaConsumoAnual}"
															disabled="true"
															id="calculoConsumoUltimoAnio" />
														<tr:outputLabel value="#{resNec['UNIDADES']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['PROYECCION_SOLICITUD']}" />
														<tr:inputText columns="10" partialTriggers="inputText6"
															value="#{necesidades_peticionNecesidades.actualizaProyeccionAnual}"
															disabled="true" 
															id="necesidadesPeticionProyeccionAnual"/>
														<tr:outputLabel value="#{resNec['DIAS']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['OBSERVACIONES']}" />
														<trh:cellFormat columnSpan="2">
															<tr:inputText rows="2"
																value="#{articPeticion.observaciones}" disabled="true"
																id="articPeticionObserv" />
														</trh:cellFormat>
													</trh:rowLayout>
												</trh:tableLayout>
											</f:facet>
										</tr:table>
									</f:facet>
									<f:facet name="almacen">
										<tr:table emptyText="#{resNec['ANADIR_NUEVAS_LINEAS']}"
											id="tablaAlmacen" var="articuloAlmacen"
											partialTriggers="aniadir_articulos" rendered="true"
											rowBandingInterval="1" columnBandingInterval="0" width="100%"
											value="#{necesidades_peticionNecesidades.lineasArticulosDeAlmacen}"
											binding="#{necesidades_peticionSuministrosBinding.tablaAlmacen}"
											rowSelection="single">
											<tr:column sortable="true" sortProperty="descripcion"
												headerText="#{resCat['ARTICULO']}" align="start">
												<tr:outputText value="#{articuloAlmacen.descripcion}" />
											</tr:column>
											<tr:column sortable="true" sortProperty="productoSel"
												headerText="#{resCat['PRODUCTO_REFERENCIA_FABRICANTE']}"
												align="start">
												<tr:outputText value="#{articuloAlmacen.productoSel}" />
											</tr:column>
											<tr:column sortable="true" sortProperty="cantidad"
												headerText="#{resNec['CANTIDAD']}" align="end">
												<tr:inputText value="#{articuloAlmacen.cantidad}"
													columns="10"
													disabled="#{necesidades_peticionNecesidades.peticionGuardada}" 
													id="articuloAlmacenCantidad"/>
											</tr:column>
											<tr:column sortable="true" sortProperty="unidadDeMedida"
												headerText="#{resCat['UNIDAD_MEDIDA']}" align="start">
												<tr:outputText value="#{articuloAlmacen.unidadDeMedida}" />
											</tr:column>
											<f:facet name="detailStamp">
												<trh:tableLayout halign="center">
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['CONSUMO_ULTIMO_ANIO']}" />
														<tr:inputText columns="10"
															value="#{necesidades_peticionNecesidades.calculaConsumoAnual}"
															disabled="true" 
															id="articuloAlmacenCalculoConsumoActual"/>
														<tr:outputLabel value="#{resNec['UNIDADES']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['PROYECCION_SOLICITUD']}" />
														<tr:inputText columns="10"
															value="#{necesidades_peticionNecesidades.actualizaProyeccionAnual}"
															disabled="true" 
															id="articuloAlmacenProyeccionAnual"/>
														<tr:outputLabel value="#{resNec['DIAS']}" />
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:outputLabel value="#{resNec['OBSERVACIONES']}" />
														<trh:cellFormat columnSpan="2">
															<tr:inputText rows="2"
																value="#{articuloAlmacen.observaciones}" disabled="true" 
																id="articuloAlmacenObservaciones"/>
														</trh:cellFormat>
													</trh:rowLayout>
												</trh:tableLayout>
											</f:facet>
										</tr:table>
									</f:facet>
								</tr:switcher>
							</tr:panelBox>
						</f:facet>
					</tr:switcher>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
