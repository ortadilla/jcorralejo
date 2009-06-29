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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resPed['GESTION_PEDIDOS_INT']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
			<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="realizarConsulta" id="formConsultarNecesidadesCompra" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{resPed['GESTION_PEDIDOS_INT']}" />
					<tr:showDetailHeader id="criteriosBusqueda"
						partialTriggers="criteriosBusqueda"
						text="#{resCore['CRITERIOS_BUSQUEDA']}" disclosed="true"
						binding="#{pedidos_consultarSolicitudBinding.shwBusqueda}">
						<tr:panelBox background="medium" inlineStyle="width: 100%;"
							partialTriggers="tablaPeticiones:enviar">
							<tr:panelHorizontalLayout>
								<tr:inputText label="#{resCore['CODIGO']}" maximumLength="10"
									columns="10" id="identificador"
									value="#{pedidos_consultarSolicitudBean.codigo}" />
								<tr:spacer width="10" />
								<tr:inputDate label="#{resPed['FECHA_REGISTRO']}"
									binding="#{pedidos_consultarSolicitudBinding.fecha}"
									id="fechaRegistro" autoSubmit="true"
									value="#{pedidos_consultarSolicitudBean.fecha}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout id="panelLabelAndMessage5"
								partialTriggers="tipoOrigen">
								<tr:selectOneChoice label="#{resNec['ORIGEN']}" id="tipoOrigen"
									autoSubmit="true" required="false" unselectedLabel=""
									valueChangeListener="#{pedidos_consultarSolicitudBean.cambiaTipoOrigen}"
									binding="#{pedidos_consultarSolicitudBinding.tipoOrigen}"
									value="#{pedidos_consultarSolicitudBean.identificadorTipoOrigen}">
									<f:selectItems
										value="#{pedidos_consultarSolicitudBean.selectorTipoOrigen}"
										id="selectorTipoOrigen" />
								</tr:selectOneChoice>
								<tr:spacer width="10" height="10" />
								<tr:switcher
									binding="#{pedidos_consultarSolicitudBinding.swOrigen}"
									id="swOrigen">
									<f:facet name="combo">
										<tr:selectOneChoice id="valorCombo"
											partialTriggers="tipoOrigen" disabled="true"
											autoSubmit="true" unselectedLabel=""
											binding="#{pedidos_consultarSolicitudBinding.valorCombo}"
											value="#{pedidos_consultarSolicitudBean.idOrigen}">
											<f:selectItems
												binding="#{pedidos_consultarSolicitudBinding.selectorValorOrigen}"
												id="selectorValorOrigen" />
										</tr:selectOneChoice>
									</f:facet>
								</tr:switcher>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout id="panelLabelAndMessage123">
								<tr:selectOneChoice label="#{resNec['ESTADO']}" id="estado"
									autoSubmit="true" required="false" unselectedLabel=''
									binding="#{pedidos_consultarSolicitudBinding.estado}"
									value="#{pedidos_consultarSolicitudBean.estado}">
									<f:selectItems
										value="#{pedidos_consultarSolicitudBean.selectorEstado}"
										id="selectorEstado"
										binding="#{pedidos_consultarSolicitudBinding.selectorEstado}" />
								</tr:selectOneChoice>
								<tr:spacer width="10" />
								<tr:selectOneChoice id="tipoPrioridad" autoSubmit="true"
									label="#{resNec['PRIORIDAD']}"
									binding="#{pedidos_consultarSolicitudBinding.tipoPrioridad}"
									required="false" disabled="false" unselectedLabel=""
									value="#{pedidos_consultarSolicitudBean.identificadorTipoPrioridad}">
									<f:selectItems
										value="#{pedidos_consultarSolicitudBean.selectorTipoPrioridad}"
										id="selectorTipoPrioridad"
										binding="#{pedidos_consultarSolicitudBinding.selectorTipoPrioridad}" />
								</tr:selectOneChoice>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout id="panel156">
								<tr:inputText label="#{resCat['ARTICULO']}"
									value="#{pedidos_consultarSolicitudBean.codigosArticulos}"
									id="articulo" />
								<tr:commandLink
									action="#{pedidos_consultarSolicitudBean.buscarArticulos}">
									<tr:image styleClass="imagenLupa" source="../imagenes/lupa3.gif" />
								</tr:commandLink>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="right">
								<tr:commandButton text="#{resCore['BUSCAR']}"
									action="#{pedidos_consultarSolicitudBean.realizarConsulta}"
									id="realizarConsulta" blocking="true" />
								<tr:commandButton text="#{resCore['LIMPIAR']}"
									action="#{pedidos_consultarSolicitudBean.limpiarFormulario}"
									blocking="true" 
									id="limpiar"/>
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:showDetailHeader>
					<tr:spacer width="40" height="10" />
					<tr:panelBox inlineStyle="width: 100%; border: solid 1px black;"
						partialTriggers="pedidosInternos criteriosBusqueda tablaPeticiones:enviar tablaPeticiones tablaSolicitudLinea"
						text="#{resPed['RESULTADO_BUSQUEDA']}">
						<tr:showDetailHeader id="pedidosInternos"
							partialTriggers="pedidosInternos"
							text="#{resPed['PEDIDOS_INTERNOS_CABECERAS']}"
							disclosureListener="#{pedidos_consultarSolicitudBean.seleccionarTodasSolicitudes}"
							binding="#{pedidos_consultarSolicitudBinding.shwSolicitudes}"
							disclosed="false">
							<tr:table rows="10" rowBandingInterval="1"
								columnBandingInterval="0" emptyText="#{resCore['NO_ELEMENTOS']}"
								var="pet" id="tablaPeticiones"
								value="#{pedidos_consultarSolicitudBean.listaSolicNecesidad}"
								partialTriggers="realizarConsulta tablaPeticiones:enviar generarPedido generarPropuesta"
								selectionListener="#{pedidos_consultarSolicitudBean.cambiaSeleccionLineas}"
								rowSelection="multiple" autoSubmit="true"
								selectedRowKeys="#{pedidos_consultarSolicitudBean.estadoTablaSolicitudes}">
								<f:facet name="actions">
									<tr:panelButtonBar>
										<tr:spacer height="40" width="0" />
										<tr:commandButton text="#{resNec['ENVIAR_PETICION']}"
											rendered="#{pedidos_consultarSolicitudBean.mostrarBotonEnviarPeticion}"
											id="enviar" partialSubmit="true"
											actionListener="#{pedidos_consultarSolicitudBean.enviarPeticion}" />
										<tr:spacer height="40" width="0" />
									</tr:panelButtonBar>
								</f:facet>
								<!--<f:facet name="selection">-->
								<!--	<tr:tableSelectMany-->
								<!--		text="#{resPed['SELECCIONE_PEDIDOS_DESPLEGAR']}"-->
								<!--		id="selectorTablaMuchos" autoSubmit="true"-->
								<!--		binding="#{pedidos_consultarSolicitudBinding.selectorTablaMuchos}" />-->
								<!--</f:facet>-->
								<tr:column sortable="true" headerText="#{resCore['CODIGO']}"
									sortProperty="codigo">
									<tr:outputText value="#{pet.codigo}"
										inlineStyle="color:#{pet.colorEstado} " />
								</tr:column>
								<tr:column sortable="true" headerText="#{resNec['TIPO_ORIGEN']}"
									align="start" sortProperty="tipoOrigen">
									<tr:outputText value="#{pet.tipoOrigen}"
										inlineStyle="color:#{pet.colorEstado}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resNec['ORIGEN']}"
									align="start" sortProperty="origen">
									<tr:outputText value="#{pet.origen}"
										inlineStyle="color:#{pet.colorEstado}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resCore['FECHA']}"
									align="end" sortProperty="fecha">
									<tr:outputText value="#{pet.fecha}"
										inlineStyle="color:#{pet.colorEstado}">
										<tr:convertDateTime pattern="dd/MM/yyyy" />
									</tr:outputText>
								</tr:column>
								<tr:column sortable="true" headerText="#{resNec['PRIORIDAD']}"
									align="start" sortProperty="tipoPrioridad">
									<tr:outputText value="#{pet.tipoPrioridad}"
										inlineStyle="color:#{pet.colorEstado}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resNec['ESTADO']}"
									align="start" sortProperty="estado">
									<tr:outputText value="#{pet.estado}"
										inlineStyle="color:#{pet.colorEstado}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="observaciones"
									headerText="#{resPed['OBS']}"
									inlineStyle="text-align:center; vertical-align:middle;">
									<tr:outputText rendered="false" id="observacionesSolicitud"
										value="#{pet.observaciones}"
										binding="#{pedidos_consultarSolicitudBinding.observacionesSolicitud}" />
									<tr:switcher facetName="#{pet.swObservaciones}">
										<f:facet name="mostrarObservacion">
											<tr:commandLink
												action="#{pedidos_consultarSolicitudBean.linkObservaciones}"
												useWindow="true" partialSubmit="true"
												launchListener="#{pedidos_consultarSolicitudBean.asociarObservacionesSolicitud}"
												windowHeight="450" windowWidth="450">
												<h:graphicImage url="/imagenes/observacion.gif"
													style="border-color:rgb(255,255,255);" />
											</tr:commandLink>
										</f:facet>
									</tr:switcher>
								</tr:column>
							</tr:table>
						</tr:showDetailHeader>
						<tr:spacer width="100%" height="5" />
						<tr:panelHeader text="#{resPed['PEDIDOS_INTERNOS_LINEAS']}" />
						<tr:spacer width="100%" height="5" />
						<tr:panelButtonBar>
							<tr:commandButton text="#{resPed['GENERAR_PROPUESTA']}"
								partialSubmit="true" id="generarPropuesta" useWindow="true"
								rendered="#{pedidos_consultarSolicitudBean.mostrarBotonGenerarPropuesta}"
								action="#{pedidos_consultarSolicitudBean.generarPropuesta}"
								windowHeight="800" windowWidth="700" />
							<tr:commandButton text="#{resPed['GENERAR_PEDIDOS_EXT']}"
								partialSubmit="true" id="generarPedido" useWindow="true"
								rendered="#{pedidos_consultarSolicitudBean.mostrarBotonGenerarPedidosExt}"
								action="#{pedidos_consultarSolicitudBean.generarPedido}"
								windowHeight="800" windowWidth="950"
								returnListener="#{pedidos_consultarSolicitudBean.pedidosAceptados}" />
							<tr:commandButton text="#{resPed['EDITAR_PEDIDO']}"
								partialSubmit="false" id="editar" useWindow="false"
								action="#{pedidos_consultarSolicitudBean.editarLinea('SOLICITUDLINEA')}"
								blocking="true" windowHeight="600" windowWidth="600"
								rendered="#{pedidos_consultarSolicitudBean.mostrarBotonEditarPedido}"
								returnListener="#{pedidos_consultarSolicitudBean.aplicarCambiosALinea}" />
							<tr:commandButton text="#{resPed['EDITAR_PROPUESTA']}"
								partialSubmit="false" id="editarProp" useWindow="false"
								action="#{pedidos_consultarSolicitudBean.editarLinea('PROPUESTA')}"
								blocking="true" windowHeight="600" windowWidth="600"
								rendered="#{pedidos_consultarSolicitudBean.mostrarBotonEditarPropuesta}"
								returnListener="#{pedidos_consultarSolicitudBean.aplicarCambiosALinea}" />							
						</tr:panelButtonBar>
						<tr:table emptyText="#{resCore['NO_ELEMENTOS']}"
							id="tablaSolicitudLinea"
							value="#{pedidos_consultarSolicitudBean.listaSolicitudesLineas}"
							rows="0" rowBandingInterval="1" columnBandingInterval="0"
							partialTriggers="realizarConsulta tablaPeticiones editar generarPedido generarPropuesta tablaPeticiones:enviar"
							var="solicitudLineaVista" rowSelection="multiple"
							selectedRowKeys="#{pedidos_consultarSolicitudBean.estadoTablaSolicitudesLineas}"
							disclosedRowKeys="#{pedidos_consultarSolicitudBean.desplegadosTablaSolicitudesLineas}">
							<tr:column sortable="true" sortProperty="origenTipo"
								headerText="#{resNec['TIPO_ORIGEN']}">
								<tr:outputText value="#{solicitudLineaVista.origenTipo}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="origenValor"
								headerText="#{resNec['ORIGEN']}">
								<tr:outputText value="#{solicitudLineaVista.origenValor}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="codigoArticulo"
								headerText="#{resCat['CODIGO_ARTICULO']}">
								<tr:outputText value="#{solicitudLineaVista.codigoArticulo}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="nombreArticulo"
								headerText="#{resCat['ARTICULO']}">
								<tr:outputText value="#{solicitudLineaVista.nombreArticulo}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="cantidadAsociada"
								align="end" headerText="#{resPed['CANTIDAD']}">
								<tr:outputText value="#{solicitudLineaVista.cantidadAsociada}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="unidadMedida"
								headerText="#{resCat['UNIDAD_MEDIDA']}">
								<tr:outputText value="#{solicitudLineaVista.unidadMedida}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="fechaSolicitud"
								headerText="#{resPed['FECHA_SOLICITUD']}">
								<tr:outputText value="#{solicitudLineaVista.fechaSolicitud}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" sortProperty="prioridad"
								headerText="#{resNec['PRIORIDAD']}">
								<tr:outputText value="#{solicitudLineaVista.prioridad}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estado"
								headerText="#{resNec['ESTADO']}">
								<tr:outputText value="#{solicitudLineaVista.estado}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="observaciones"
								headerText="#{resPed['OBS']}">
								<tr:outputText rendered="false" id="observacionesSolicitudLinea"
									value="#{solicitudLineaVista.observaciones}"
									binding="#{pedidos_consultarSolicitudBinding.observacionesSolicitudLinea}" />
								<tr:switcher facetName="#{solicitudLineaVista.swObservaciones}">
									<f:facet name="mostrarObservacion">
										<tr:commandLink
											action="#{pedidos_consultarSolicitudBean.mostrarDetailStamp}">
											<h:graphicImage url="/imagenes/observacion.gif"
												style="border-color:rgb(255,255,255);"
												title="#{solicitudLineaVista.observaciones}" />
										</tr:commandLink>
									</f:facet>
								</tr:switcher>
							</tr:column>
							<tr:column sortable="true" sortProperty="NPedido"
								headerText="#{resPed['N_PEDIDO']}">
								<tr:outputText
									inlineStyle="color:#{solicitudLineaVista.colorEstado}"
									value="#{solicitudLineaVista.NPedido}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="CIP"
								headerText="#{resCat['CIP']}">
								<tr:outputText value="#{solicitudLineaVista.CIP}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="referenciaFabricante"
								headerText="#{resCat['REFERENCIA_FABRICANTE']}">
								<tr:outputText
									value="#{solicitudLineaVista.referenciaFabricante}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="EAN"
								headerText="#{resPed['EAN']}">
								<tr:outputText value="#{solicitudLineaVista.EAN}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="empresa"
								headerText="#{resPed['EMPRESA']}">
								<tr:outputText value="#{solicitudLineaVista.empresa}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="precio"
								headerText="#{resCat['PRECIO']}" align="end">
								<tr:outputText value="#{solicitudLineaVista.precio}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="importe"
								headerText="#{resPed['IMPORTE']}" align="end">
								<tr:outputText value="#{solicitudLineaVista.importe}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="puntoEntrega"
								headerText="#{resOrg['PUNTO_ENTREGA']}">
								<tr:outputText value="#{solicitudLineaVista.puntoEntrega}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="referenciaDistribuidor"
								headerText="#{resCat['DISTRIBUIDOR']}">
								<tr:outputText
									value="#{solicitudLineaVista.referenciaDistribuidor}"
									inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
							</tr:column>
							<tr:column sortable="true"
								sortProperty="clasificacionEconomicaPorDefecto"
								headerText="#{resPed['VARIAS_ECONOMICAS']}">
								<tr:panelHorizontalLayout>
									<h:graphicImage url="/imagenes/asterisco.gif"
										style="border-color:rgb(255,255,255);"
										rendered="#{solicitudLineaVista.variasEconomicas}" />
									<tr:outputText
										value="#{solicitudLineaVista.clasificacionEconomicaPorDefecto}"
										inlineStyle="color:#{solicitudLineaVista.colorEstado}" />
								</tr:panelHorizontalLayout>
							</tr:column>
							<!--<f:facet name="selection">-->
							<!--	<tr:tableSelectMany-->
							<!--		text="#{resPed['SELECCIONE_LINEAS_PEDIDO_INTERNO']}"-->
							<!--		binding="#{pedidos_consultarSolicitudBinding.tablaSolicitudLineasMuchos}"-->
							<!--		id="tablaSocliitudLineasMuchos" />-->
							<!--</f:facet>-->
							<f:facet name="detailStamp">
								<trh:tableLayout halign="center">
									<trh:rowLayout>
										<tr:outputLabel value="#{resNec['CONSUMO_ULTIMO_ANIO']}" />
										<tr:outputText value="#{solicitudLineaVista.consumoAnio}" />
										<tr:outputLabel value="#{resNec['UNIDADES']}" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputLabel value="#{resNec['PROYECCION_SOLICITUD']}" />
										<tr:outputText partialTriggers="inputText6"
											value="#{pedidos_consultarSolicitudBean.calculaProyeccionAnual}" />
										<tr:outputLabel value="#{resNec['DIAS']}" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputLabel value="#{resNec['OBSERVACIONES']}" />
										<trh:cellFormat columnSpan="2">
											<tr:inputText rows="2"
												value="#{solicitudLineaVista.observaciones}" disabled="true" />
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
							</f:facet>
						</tr:table>
					</tr:panelBox>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>

