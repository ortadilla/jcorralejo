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
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOfe" var="resOfe" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>
		<trh:head title="#{resPed['CABECERA_PAGINA_CONSULTA_PEDIDOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body partialTriggers="">
			<geos:cabeceraPagina />
			<tr:form id="formConsultarPedidos" onsubmit="bloquearPantalla(this);">
				<tr:panelHeader text="#{resPed['TITULO_PAGINA_CONSULTA_PEDIDOS']}" />
				<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
					disclosed="true"
					binding="#{pedidos_consultarPedidosBinding.shwBusqueda}"
					id="criterios" partialTriggers="criterios"
					inlineStyle="width:100%;">
					<tr:spacer width="10" height="10" />
					<tr:panelBox background="medium" inlineStyle="width:100%;"
						partialTriggers="busquedaAvanzada">
						<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
							<trh:rowLayout>
								<trh:cellFormat />
								<trh:cellFormat />
								<trh:cellFormat />
								<trh:cellFormat />
								<trh:cellFormat />
							</trh:rowLayout>
							<trh:rowLayout
								rendered="#{pedidos_consultarPedidosBean.mostrarComboCentro}">
								<trh:cellFormat>
									<tr:outputText value="#{resOrg['ORGANO_GESTOR']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectOneChoice id="comboCentro"
										binding="#{pedidos_consultarPedidosBinding.comboOrganoGestor}"
										autoSubmit="true" 
										value="#{pedidos_consultarPedidosBean.valorOrganoGestor}"
										valueChangeListener="#{pedidos_consultarPedidosBean.cambiarOrganoGestor}"
										unselectedLabel=''>
										<f:selectItems
											value="#{pedidos_consultarPedidosBean.selectorCentro}"
											binding="#{pedidos_consultarPedidosBinding.selectorCentro}"
											id="selectorCentro" />
									</tr:selectOneChoice>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText
										value="#{resNec['TIPO_ORIGEN']}" />
								</trh:cellFormat>
								<trh:cellFormat>
												<tr:selectOneChoice id="comboTipoOrigen" unselectedLabel=""
													binding="#{pedidos_consultarPedidosBinding.comboTipoOrigen}"
													autoSubmit="true"
													value="#{pedidos_consultarPedidosBean.valorTipoOrigen}"
													partialTriggers="comboCentro" 
													valueChangeListener="#{pedidos_consultarPedidosBean.cambiarTipoOrigen}">
													<f:selectItems
														value="#{pedidos_consultarPedidosBean.selectorTipoOrigen}"
														id="selectorTipoOrigen" />
												</tr:selectOneChoice>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:outputText
										value="#{resNec['ORIGEN']}" />
								</trh:cellFormat>
								<trh:cellFormat>
												<tr:selectOneChoice
													binding="#{pedidos_consultarPedidosBinding.comboOrigen}"
													value="#{pedidos_consultarPedidosBean.valorOrigen}"
													autoSubmit="true"
													id="comboOrigen" unselectedLabel=""
													partialTriggers="comboTipoOrigen">
													<f:selectItems
														value="#{pedidos_consultarPedidosBean.selectorOrigen}"
														id="selectorOrigen" />
												</tr:selectOneChoice>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText
										value="#{resNec['PUNTO_ENTREGA']}" />
								</trh:cellFormat>
								<trh:cellFormat>
												<tr:selectOneChoice
													value="#{pedidos_consultarPedidosBean.valorPuntoEntrega}"
													id="comboPuntoEntrega" unselectedLabel=""
													partialTriggers="comboTipoOrigen comboOrigen">
													<f:selectItems
														value="#{pedidos_consultarPedidosBean.selectorPuntosEntregaDeOrigen}"
														id="selectorPuntosEntrega" />
												</tr:selectOneChoice>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{resPed['PROVEEDOR']}" />
								</trh:cellFormat>
								<trh:cellFormat columnSpan="4">
									<tr:panelHorizontalLayout inlineStyle="width:100%;">
										<tr:inputText disabled="true"
											value="#{pedidos_consultarPedidosBean.nombreProveedor}"
											columns="130"
											id="nombreProveedor" />
										<tr:commandLink
											rendered = "#{!pedidos_consultarPedidosBean.esUsuarioExterno}"
											action="#{pedidos_consultarPedidosBean.buscarProveedores}">
											<tr:image styleClass="imagenLupa" source="/imagenes/lupa3.gif" />
										</tr:commandLink>
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText
										value="#{resPed['FECHA_PEDIDO']} #{resCore['DESDE']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputDate id="fechaPedidoInicial" readOnly="false"
										disabled="false" autoSubmit="true"
										value="#{pedidos_consultarPedidosBean.fechaInicial}"
										binding="#{pedidos_consultarPedidosBinding.fechaPedidoInicial}">
										<tr:validateDateTimeRange
											maximum="#{pedidos_consultarPedidosBinding.fechaPedidoFinal.value}" />
									</tr:inputDate>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:outputText value="#{resCore['HASTA']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputDate id="fechaPedidoFinal" autoSubmit="true"
										binding="#{pedidos_consultarPedidosBinding.fechaPedidoFinal}"
										value="#{pedidos_consultarPedidosBean.fechaFinal}">
										<tr:validateDateTimeRange
											minimum="#{pedidos_consultarPedidosBinding.fechaPedidoInicial.value}" />
									</tr:inputDate>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{resPed['NPEDIDO']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputText columns="10" id="inputPedido"
										binding="#{pedidos_consultarPedidosBinding.inputNumeroPedido}"
										value="#{pedidos_consultarPedidosBean.valorNumeroPedido}">
										<f:converter converterId="javax.faces.Integer" />
									</tr:inputText>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:outputText value="#{resPed['EJERCICIO']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputText columns="10" id="inputEjercicio"
										binding="#{pedidos_consultarPedidosBinding.inputEjercicio}"
										value="#{pedidos_consultarPedidosBean.valorEjercicio}">
										<f:converter converterId="javax.faces.Integer" />
									</tr:inputText>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout partialTriggers="comboTipoCompra">
								<trh:cellFormat>
									<tr:outputText value="#{resPed['PROCEDIMIENTO_COMPRA']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectOneChoice id="comboTipoCompra"
										binding="#{pedidos_consultarPedidosBinding.comboTipoCompra}"
										value="#{pedidos_consultarPedidosBean.valorTipoCompra}"
										unselectedLabel='' autoSubmit="true">
										<f:selectItems
											value="#{pedidos_consultarPedidosBean.selectorTipoCompra}"
											binding="#{pedidos_consultarPedidosBinding.selectorTipoCompra}"
											id="selectorTipoCompra" />
									</tr:selectOneChoice>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:outputText value="#{resPed['EXPEDIENTE']}"
										rendered="#{pedidos_consultarPedidosBean.mostrarInputExpediente}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout>
										<tr:inputText columns="15" id="inputExpediente"
											binding="#{pedidos_consultarPedidosBinding.inputExpediente}"
											value="#{pedidos_consultarPedidosBean.numeroExpediente}"
											disabled="true"
											rendered="#{pedidos_consultarPedidosBean.mostrarInputExpediente}"
											partialTriggers="comboTipoCompra">
											<f:converter converterId="javax.faces.Integer" />
										</tr:inputText>
										<tr:commandLink
											action="#{pedidos_consultarPedidosBean.buscarExpediente}"
											rendered="#{pedidos_consultarPedidosBean.mostrarInputExpediente}">
											<tr:image styleClass="imagenLupa" source="/imagenes/lupa3.gif" />
										</tr:commandLink>
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{resCat['ARTICULOS']}" />
								</trh:cellFormat>
								<trh:cellFormat columnSpan="4">
									<tr:panelHorizontalLayout inlineStyle="width:100%;">
										<tr:inputText
											value="#{pedidos_consultarPedidosBean.codArticulos}"
											columns="130" 
											id="codArticulos"/>
										<tr:commandLink
											action="#{pedidos_consultarPedidosBean.buscarArticulos}">
											<tr:image styleClass="imagenLupa" source="/imagenes/lupa3.gif" />
										</tr:commandLink>
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{resOfe['OFERTAS']}" />
								</trh:cellFormat>
								<trh:cellFormat columnSpan="4">
									<tr:panelHorizontalLayout inlineStyle="width:100%;">
										<tr:inputText
											value="#{pedidos_consultarPedidosBean.cipProductos}"
											columns="130" 
											id="cipProductos"/>
										<tr:commandLink
											action="#{pedidos_consultarPedidosBean.buscarOfertas}">
											<tr:image styleClass="imagenLupa" source="/imagenes/lupa3.gif" />
										</tr:commandLink>
									</tr:panelHorizontalLayout>
								</trh:cellFormat>
								<trh:cellFormat />
								<trh:cellFormat />
								<trh:cellFormat />
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputText value="#{resNec['ESTADO_RECEPCION']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectManyListbox id="comboEstadoRecepcion"
										value="#{pedidos_consultarPedidosBean.valorEstado}">
										<f:selectItems
											value="#{pedidos_consultarPedidosBean.selectorEstado}"
											id="selectorEstado" />
									</tr:selectManyListbox>
								</trh:cellFormat>
							</trh:rowLayout>
						</trh:tableLayout>
						<tr:spacer width="10" height="10" />
						<tr:showDetailHeader text="#{resCore['BUSQUEDA_AVANZADA']}"
							disclosed="false" id="busquedaAvanzada"
							partialTriggers="busquedaAvanzada">
							<tr:panelBox inlineStyle="width:100%;" id="panelFormLayout4"
								background="medium">
								<tr:panelHorizontalLayout>
									<tr:panelFormLayout>
										<tr:panelLabelAndMessage label="#{resNec['FECHA_ENTREGA']}">
											<tr:panelHorizontalLayout>
												<tr:inputDate autoSubmit="true" id="fechaEntregaInicial"
													binding="#{pedidos_consultarPedidosBinding.fechaEntregaInicial}"
													value="#{pedidos_consultarPedidosBean.fechaEntregaInicial}"
													label="#{resCore['DESDE']}">
													<tr:validateDateTimeRange
														maximum="#{pedidos_consultarPedidosBinding.fechaEntregaFinal.value}" />
												</tr:inputDate>
												<tr:spacer width="10" />
												<tr:inputDate autoSubmit="true"
													binding="#{pedidos_consultarPedidosBinding.fechaEntregaFinal}"
													value="#{pedidos_consultarPedidosBean.fechaEntregaFinal}"
													id="fechaEntregaFinal" label="#{resCore['HASTA']}">
													<tr:validateDateTimeRange
														minimum="#{pedidos_consultarPedidosBinding.fechaEntregaInicial.value}" />
												</tr:inputDate>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage label="#{resPed['FECHA_REGISTRO']}">
											<tr:panelHorizontalLayout>
												<tr:inputDate label="#{resCore['DESDE']}" autoSubmit="true"
													id="fechaRegistroInicial"
													binding="#{pedidos_consultarPedidosBinding.fechaRegistroInicial}"
													value="#{pedidos_consultarPedidosBean.fechaRegistroInicial}">
													<tr:validateDateTimeRange
														maximum="#{pedidos_consultarPedidosBinding.fechaRegistroInicial.value}" />
												</tr:inputDate>
												<tr:spacer width="10" height="10" />
												<tr:inputDate label="#{resCore['HASTA']}" autoSubmit="true"
													id="fechaRegistroFinal"
													binding="#{pedidos_consultarPedidosBinding.fechaRegistroFinal}"
													value="#{pedidos_consultarPedidosBean.fechaRegistroFinal}">
													<tr:validateDateTimeRange
														maximum="#{pedidos_consultarPedidosBinding.fechaRegistroFinal.value}" />
												</tr:inputDate>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage label="#{resPed['FECHA_ALBARAN']}">
											<tr:panelHorizontalLayout>
												<tr:inputDate label="#{resCore['DESDE']}" autoSubmit="true"
													id="fechaAlbaranInicial"
													binding="#{pedidos_consultarPedidosBinding.fechaAlbaranInicial}"
													value="#{pedidos_consultarPedidosBean.fechaAlbaranInicial}">
													<tr:validateDateTimeRange
														maximum="#{pedidos_consultarPedidosBinding.fechaAlbaranInicial.value}" />
												</tr:inputDate>
												<tr:spacer width="10" height="10" />
												<tr:inputDate label="#{resCore['HASTA']}" autoSubmit="true"
													id="fechaAlbaranFinal"
													binding="#{pedidos_consultarPedidosBinding.fechaAlbaranFinal}"
													value="#{pedidos_consultarPedidosBean.fechaAlbaranFinal}">
													<tr:validateDateTimeRange
														minimum="#{pedidos_consultarPedidosBinding.fechaRegistroFinal.value}" />
												</tr:inputDate>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage label="#{resPed['IMPORTE']}">
											<tr:panelHorizontalLayout>
												<tr:inputText columns="10" id="importeInicial"
													binding="#{pedidos_consultarPedidosBinding.inputImporteInicial}"
													value="#{pedidos_consultarPedidosBean.importeInicial}"
													label="#{resCore['DESDE']}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
												<tr:spacer width="10" />
												<tr:inputText columns="10" id="importeFinal"
													binding="#{pedidos_consultarPedidosBinding.inputImporteFinal}"
													value="#{pedidos_consultarPedidosBean.importeFinal}"
													label="#{resCore['HASTA']}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage label="#{resCat['PRECIO']}">
											<tr:panelHorizontalLayout>
												<tr:inputText label="#{resCore['DESDE']}" columns="10"
													id="precioInicial"
													binding="#{pedidos_consultarPedidosBinding.inputPrecioInicial}"
													value="#{pedidos_consultarPedidosBean.precioInicial}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
												<tr:spacer width="10" />
												<tr:inputText label="#{resCore['HASTA']}" columns="10"
													id="precioFinal"
													binding="#{pedidos_consultarPedidosBinding.inputPrecioFinal}"
													value="#{pedidos_consultarPedidosBean.precioFinal}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage label="#{resPed['CANTIDAD_PEDIDA']}">
											<tr:panelHorizontalLayout>
												<tr:inputText columns="10" id="cantidadInicial"
													label="#{resCore['DESDE']}"
													binding="#{pedidos_consultarPedidosBinding.inputCantidadInicial}"
													value="#{pedidos_consultarPedidosBean.cantidadInicial}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
												<tr:spacer width="10" />
												<tr:inputText label="#{resCore['HASTA']}" columns="10"
													id="cantidadFinal"
													binding="#{pedidos_consultarPedidosBinding.inputCantidadFinal}"
													value="#{pedidos_consultarPedidosBean.cantidadFinal}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:panelLabelAndMessage
											label="#{resPed['CANTIDAD_PENDIENTE']}">
											<tr:panelHorizontalLayout>
												<tr:inputText label="#{resCore['DESDE']}" columns="10"
													id="cantidadPendienteMenor"
													binding="#{pedidos_consultarPedidosBinding.inputCantidadPendienteMenor}"
													value="#{pedidos_consultarPedidosBean.cantidadPendienteMenor}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
												<tr:spacer width="10" />
												<tr:inputText label="#{resCore['HASTA']}" columns="10"
													id="cantidadPendienteMayor"
													binding="#{pedidos_consultarPedidosBinding.inputCantidadPendienteMayor}"
													value="#{pedidos_consultarPedidosBean.cantidadPendienteMayor}">
													<f:converter converterId="javax.faces.BigDecimal" />
												</tr:inputText>
											</tr:panelHorizontalLayout>
										</tr:panelLabelAndMessage>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice
											binding="#{pedidos_consultarPedidosBinding.comboUsuario}"
											value="#{pedidos_consultarPedidosBean.usuarioId}"
											id="comboUsuario" unselectedLabel=""
											label="#{resOrg['USUARIO']}">
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorUsuario}"
												binding="#{pedidos_consultarPedidosBinding.selectorUsuario}"
												id="selectorUsuario" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resPed['TIPO_PEDIDO']}"
											binding="#{pedidos_consultarPedidosBinding.comboTipoPedido}"
											value="#{pedidos_consultarPedidosBean.valorTipoPedido}"
											id="comboTipoPedido" unselectedLabel=''>
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorTipoPedido}"
												id="selectorTipoPedido" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resPed['COND_FACTURAR']}"
											binding="#{pedidos_consultarPedidosBinding.comboCondFacturar}"
											value="#{pedidos_consultarPedidosBean.valorCondFacturar}"
											id="comboCondFacturar" unselectedLabel=''>
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorCondFacturar}"
												id="selectorCondFacturar" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resCat['FABRICANTE']}" columns="10"
											rows="1" id="referenciaFabricante"
											binding="#{pedidos_consultarPedidosBinding.inputReferenciaFabricante}"
											value="#{pedidos_consultarPedidosBean.referenciaFabricante}" />
										<tr:spacer width="10" height="10" />
										<tr:inputText columns="10"
											binding="#{pedidos_consultarPedidosBinding.inputCIP}"
											value="#{pedidos_consultarPedidosBean.codigoCip}" id="CIP"
											label="#{resCat['CODIGO_CIP']}" />
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resPed['CODIGO_EAN']}" columns="10"
											id="EAN" value="#{pedidos_consultarPedidosBean.codigoEAN}"
											binding="#{pedidos_consultarPedidosBinding.inputEAN}" />
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resCat['DISTRIBUIDOR']}" columns="10"
											id="referenciaDistribuidor"
											value="#{pedidos_consultarPedidosBean.referenciaDistribuidor}"
											binding="#{pedidos_consultarPedidosBinding.inputReferenciaDistribuidor}" />
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resPed['NUMERO_ALBARAN']}"
											id="numeroAlbaran"
											binding="#{pedidos_consultarPedidosBinding.numeroAlbaran}"
											value="#{pedidos_consultarPedidosBean.numAlbaran}"
											columns="10">
											<f:converter converterId="javax.faces.Integer" />
										</tr:inputText>
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resPed['NUMERO_LOTE']}" id="numeroLote"
											binding="#{pedidos_consultarPedidosBinding.numeroLote}"
											value="#{pedidos_consultarPedidosBean.numLote}" columns="10">
											<f:converter converterId="javax.faces.Integer" />
										</tr:inputText>
										<tr:spacer width="10" height="10" />
										<tr:inputText label="#{resPed['NUMERO_SERIE']}"
											id="numeroSerie"
											binding="#{pedidos_consultarPedidosBinding.numeroSerie}"
											value="#{pedidos_consultarPedidosBean.numSerie}" columns="10">
											<f:converter converterId="javax.faces.Integer" />
										</tr:inputText>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resNec['ESTADO_AUTORIZACION']}"
											id="comboEstadoAutorizacion"
											binding="#{pedidos_consultarPedidosBinding.comboEstadoAutorizacion}"
											value="#{pedidos_consultarPedidosBean.estadoAutorizacion}"
											unselectedLabel="">
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorEstadoAutorizacion}"
												binding="#{pedidos_consultarPedidosBinding.selectorEstadoAutorizacion}"
												id="selectorEstadoAutorizacion" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resNec['ESTADO_RECLAMACION']}"
											id="comboEstadoReclamacion"
											binding="#{pedidos_consultarPedidosBinding.comboEstadoReclamacion}"
											value="#{pedidos_consultarPedidosBean.estadoReclamacion}"
											unselectedLabel="">
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorEstadoReclamacion}"
												binding="#{pedidos_consultarPedidosBinding.selectorEstadoReclamacion}"
												id="selectorEstadoReclamacion" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resNec['ESTADO_COMUNICACION']}"
											id="comboEstadoComunicacion"
											binding="#{pedidos_consultarPedidosBinding.comboEstadoComunicacion}"
											value="#{pedidos_consultarPedidosBean.estadoComunicacion}"
											unselectedLabel="">
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorEstadoComunicacion}"
												binding="#{pedidos_consultarPedidosBinding.selectorEstadoComunicacion}"
												id="selectorEstadoComunicacion" />
										</tr:selectOneChoice>
										<tr:spacer width="10" height="10" />
										<tr:selectOneChoice label="#{resNec['ESTADO_PROGRAMACION']}"
											id="comboEstadoProgramacion"
											binding="#{pedidos_consultarPedidosBinding.comboEstadoProgramacion}"
											value="#{pedidos_consultarPedidosBean.estadoProgramacion}"
											unselectedLabel="">
											<f:selectItems
												value="#{pedidos_consultarPedidosBean.selectorEstadoProgramacion}"
												binding="#{pedidos_consultarPedidosBinding.selectorEstadoProgramacion}"
												id="selectorEstadoProgramacion" />
										</tr:selectOneChoice>
									</tr:panelFormLayout>
								</tr:panelHorizontalLayout>
							</tr:panelBox>
						</tr:showDetailHeader>
						<tr:panelHorizontalLayout halign="right">
							<tr:commandButton text="#{resCore['BUSCAR']}" id="consulta"
								action="#{pedidos_consultarPedidosBean.consultar}"
								blocking="true" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resCore['LIMPIAR']}" rendered="true"
								action="#{pedidos_consultarPedidosBean.limpiarFormulario}"
								id="limpiar" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
					<!-- 	<tr:spacer width="10" height="10" />
					<tr:panelGroupLayout>
						<tr:commandButton text="#{resCore['BUSCAR']}" id="consulta"
							action="#{pedidos_consultarPedidosBean.consultar}" />
						<tr:spacer width="10" height="10" />
						<tr:commandButton text="#{resCore['LIMPIAR']}" rendered="true"
							action="#{pedidos_consultarPedidosBean.limpiarFormulario}" />
					</tr:panelGroupLayout>-->
				</tr:showDetailHeader>
				<tr:spacer width="10" height="10" />
				<tr:messages />
				<!--<tr:switcher binding="#{pedidos_consultarPedidosBinding.swTabla}">-->
				<!--<f:facet name="resultados">-->
				<!--<tr:panelGroupLayout>-->
				<tr:panelBox background="medium"
					text="#{resPed['RESULTADO_BUSQUEDA']}" inlineStyle="width:100%;"
					partialTriggers="tablaPedidos resultados">
					<tr:spacer width="10" height="10" />
					<tr:showDetailHeader disclosed="true"
						binding="#{pedidos_consultarPedidosBinding.shwPedidos}"
						id="resultados" partialTriggers="resultados">
						<tr:panelHorizontalLayout>
							<tr:commandButton text="#{resPed['DUPLICAR_PEDIDO']}"
								action="#{pedidos_consultarPedidosBean.muestraDetallePedidos('DUPLICAR')}"
								partialSubmit="true" id="duplicarPedido"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonDuplicar}" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resCore['EDITAR']}"
								action="#{pedidos_consultarPedidosBean.editarPedido}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonEditar}" 
								blocking="true"
								id="editarPedido"/>
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resCore['BORRAR']}"
								action="#{pedidos_consultarPedidosBean.borrarPedido}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonBorrar}" 
								id="borrarPedido"/>
							<tr:spacer width="10" height="10" />
							<tr:commandButton
								text="#{resPed['VER_TOTALES_PROCEDIMIENTO']}"
								partialSubmit="true" id="totalesPedido"
								action="#{pedidos_consultarPedidosBean.linkTotales}"
								useWindow="true"
								launchListener="#{pedidos_consultarPedidosBean.consultarTotales}"
								inlineStyle="vertical-align:middle;"
								windowHeight="550" windowWidth="550" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resPed['VER_DETALLES']}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonDetalles}"
								partialSubmit="true" id="detallesPedido"
								action="#{pedidos_consultarPedidosBean.muestraDetallePedidos('DETALLES')}" />
								<tr:statusIndicator>
				              <f:facet name="busy">
				                <tr:image source="/imagenes/reloj.gif" />
				              </f:facet>
				              <f:facet name="ready">
<!--									<tr:outputText value="Done!" />-->
				              </f:facet>
				     </tr:statusIndicator>
						</tr:panelHorizontalLayout>
						<tr:panelHorizontalLayout>
							<tr:commandButton text="#{resPed['VALIDAR']}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonValidar}"
								partialSubmit="#{pedidos_consultarPedidosBean.usarVen}"
								id="validar" useWindow="true" windowHeight="600"
								windowWidth="800"
								action="#{pedidos_consultarPedidosBean.linkValidar}"
								launchListener="#{pedidos_consultarPedidosBean.solicitarConfirmacionValidacion}"
								returnListener="#{pedidos_consultarPedidosBean.confirmarValidacion}" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resPed['RECHAZAR']}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonRechazar}"
								partialSubmit="true" id="rechazar"
								action="#{pedidos_consultarPedidosBean.rechazarPedidos}" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resPed['BLOQUEAR']}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonBloquear}"
								partialSubmit="true" id="bloquear"
								action="#{pedidos_consultarPedidosBean.corregirPedidos}" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton text="#{resPed['DESVALIDAR']}"
								rendered="#{pedidos_consultarPedidosBean.mostrarBotonPenValidar}"
								partialSubmit="true" id="desvalidar"
								action="#{pedidos_consultarPedidosBean.desvalidarPedidos}" />
							<tr:spacer width="10" height="10" />
						</tr:panelHorizontalLayout>
						<tr:table rows="10" rowBandingInterval="1"
							columnBandingInterval="0"
							emptyText="#{resPed['TABLA_PEDIDOS_VACIA']}" var="pedidos"
							width="100%" id="tablaPedidos"
							partialTriggers="validar rechazar desvalidar bloquear tablaPedidoLinea:aceptarEdicion tablaPedidoLinea:cancelarEdicion"
							immediate="false" allDetailsEnabled="false"
							rowSelection="multiple"
							selectedRowKeys="#{pedidos_consultarPedidosBean.estadoTablapedidos}"
							value="#{pedidos_consultarPedidosBean.listaPedidos}">
							<!--<f:facet name="selection">-->
							<!--	<tr:tableSelectMany-->
							<!--		binding="#{pedidos_consultarPedidosBinding.tablaPedidosSeleccion}"-->
							<!--		id="tablaPedidosSeleccion" />-->
							<!--</f:facet>-->
							<tr:column sortable="true" headerText="#{resPed['NPEDIDO']}"
								sortProperty="numeroPedido" align="start">
								<tr:outputText value="#{pedidos.numeroPedido}" />
							</tr:column>
							<!--<tr:column sortable="true" align="end"-->
							<!--	headerText="#{resPed['EJERCICIO']}"-->
							<!--	sortProperty="ejercicio">-->
							<!--	<tr:outputText value="#{pedidos.ejercicio}" />-->
							<!--</tr:column>-->
							<tr:column sortable="true" align="start"
								headerText="#{resPed['PROCEDIMIENTO_EXPEDIENTE']}"
								sortProperty="procedimientoExpediente">
								<tr:outputText value="#{pedidos.procedimientoExpediente}" />
							</tr:column>
							<!--<tr:column sortable="true" align="start"
								headerText="#{resPed['PROCEDIMIENTO_COMPRA']}"
								sortProperty="procedimientoCompra">
								<tr:outputText value="#{pedidos.procedimientoCompra}" />
							</tr:column>
							<tr:column sortable="true" align="start"
								headerText="#{resPed['EXPEDIENTE']}" sortProperty="expediente">
								<tr:outputText value="#{pedidos.expediente}" />
							</tr:column>-->
							<tr:column sortable="true" headerText="#{resCore['FECHA']}"
								sortProperty="fechaPedido" align="start">
								<tr:outputText value="#{pedidos.fechaPedido}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['PROVEEDOR']}"
								sortProperty="empresa" align="start">
								<tr:outputText value="#{pedidos.empresa}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['IMPORTE']}"
								sortProperty="importe" align="end" >
								<tr:outputText value="#{pedidos.importe}" />
							</tr:column>
							<tr:column sortable="false" sortProperty="observaciones"
								align="center">
								<tr:outputText value="#{pedidos.observaciones}" rendered="false"
									id="observacionesPedido"
									binding="#{pedidos_consultarPedidosBinding.observacionesPedidos}" />
								<tr:commandLink
									action="#{pedidos_consultarPedidosBean.linkObservaciones}"
									useWindow="true" partialSubmit="true"
									launchListener="#{pedidos_consultarPedidosBean.asociarObservacionesPedido}"
									windowHeight="450" windowWidth="450"
									inlineStyle="vertical-align:middle;"
									rendered="#{pedidos.observaciones !=null}">
									<h:graphicImage url="/imagenes/observacion.gif"
										style="border-color:rgb(255,255,255);" alt="Observaciones"
										title="#{pedidos.observaciones}" />
								</tr:commandLink>
							</tr:column>
							<!--<tr:column sortable="true"-->
							<!--	headerText="#{resPed['FECHA_REGISTRO']}"-->
							<!--	sortProperty="fechaRegistro">-->
							<!--	<tr:outputText value="#{pedidos.fechaRegistro}">-->
							<!--	<tr:convertDateTime pattern="dd/MM/yyyy" />-->
							<!--	</tr:outputText>-->
							<!--</tr:column>-->
							<tr:column sortable="true" align="center"
								sortProperty="tipoPedido">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_TIPO_PEDIDO']}"
										shortDesc="#{resPed['TIPO_PEDIDO']}" />
								</f:facet>
								<tr:image source="/imagenes/#{pedidos.icTipoPedido}.gif"
									shortDesc="#{resPed[pedidos.tipoPedido]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="condFacturar">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_CON_FACTURACION']}"
										shortDesc="#{resPed['COND_FACTURAR']}" />
								</f:facet>
								<tr:image source="/imagenes/#{pedidos.icCondiciones}.gif"
									shortDesc="#{resPed[pedidos.condFacturar]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoAutorizacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_AUTORIZACION']}"
										shortDesc="#{resPed['ESTADOAUTORIZACION']}" />
								</f:facet>
								<tr:image
									source="/imagenes/#{pedidos.icEstadoAutorizacion}.gif"
									shortDesc="#{resPed[pedidos.estadoAutorizacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoComunicacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_COMUNICACION']}"
										shortDesc="#{resPed['ESTADOCOMUNICACION']}" />
								</f:facet>
								<tr:image
									source="/imagenes/#{pedidos.icEstadoComunicacion}.gif"
									shortDesc="#{resPed[pedidos.estadoComunicacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoProgramacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_PROGRAMACION']}"
										shortDesc="#{resPed['ESTADOPROGRAMACION']}" />
								</f:facet>
								<tr:image
									source="/imagenes/#{pedidos.icEstadoProgramacion}.gif"
									shortDesc="#{resPed[pedidos.estadoProgramacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoRecepcion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECEPCION']}"
										shortDesc="#{resPed['ESTADORECEPCION']}" />
								</f:facet>
								<tr:image source="/imagenes/#{pedidos.icEstadoRecepcion}.gif"
									shortDesc="#{resPed[pedidos.estadoRecepcion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoReclamacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECLAMACION']}"
										shortDesc="#{resPed['ESTADORECLAMACION']}" />
								</f:facet>
								<tr:image
									source="/imagenes/#{pedidos.icEstadoReclamacion}.gif"
									shortDesc="#{resPed[pedidos.estadoReclamacion]}" />
							</tr:column>
						</tr:table>
						<tr:spacer width="10" height="10" />
					</tr:showDetailHeader>
					<tr:panelHorizontalLayout
						partialTriggers="tablaPedidos detallesPedido ">
<!--						<tr:switcher-->
<!--							binding="#{pedidos_consultarPedidosBinding.swTablaPedidoLinea}"-->
<!--							rendered="true">-->
<!--							<f:facet name="pedidoLinea">-->
								<tr:panelGroupLayout
									rendered="#{pedidos_consultarPedidosBean.mostrarTablaLineas}">
									<tr:spacer width="10" height="10" />
									<tr:panelGroupLayout>
										<tr:outputLabel value="#{resNec['DETALLES_PEDIDO']}" />
										<tr:spacer width="10" height="10" />
										<tr:outputLabel value="#{resPed['SELECCIONAR_PARA_DUPLICAR']}"
											rendered="#{pedidos_consultarPedidosBean.mostrarAceptarCancelar}"
											inlineStyle="color:rgb(255,0,0); font-weight:bold;" />
									</tr:panelGroupLayout>
									<tr:spacer width="10" height="30" />
									<tr:table inlineStyle="width:100%;"
										emptyText="#{resPed['TABLA_LINEAS_PEDIDO_VACIA']}"
										id="tablaPedidoLinea" var="pedidoLinea"
										partialTriggers="tablaPedidos tablaPedidoLinea:aceptarEdicion tablaPedidoLinea:cancelarEdicion"
										rowBandingInterval="1" columnBandingInterval="0" rows="0"
										value="#{pedidos_consultarPedidosBean.listaLineas}"
										selectedRowKeys="#{pedidos_consultarPedidosBean.estadoTablaLineas}"
										rowSelection="multiple">
										<tr:column sortable="false" align="center"
											rendered="#{pedidos_consultarPedidosBean.tienePermisoMantener}">
											<tr:commandLink
												actionListener="#{pedidos_consultarPedidosBean.editarLinea}"
												rendered="#{!pedidoLinea.enEdicion}" id="editarLinea">
												<h:graphicImage url="/imagenes/editar_4_16.png"
													style="border-color:rgb(255,255,255);"
													alt="#{resCore['EDITAR']}" />
												<tr:attribute name="idLinea" value="#{pedidoLinea.id}" />
											</tr:commandLink>
											<tr:panelHorizontalLayout>
												<tr:commandLink id="aceptarEdicion"
													returnListener="#{pedidos_consultarPedidosBean.confirmarEditarPedido}"
													action="#{pedidos_consultarPedidosBean.aceptarEdicionLinea()}"
													rendered="#{pedidoLinea.enEdicion}" useWindow="true"
													windowHeight="300" windowWidth="800" partialSubmit="true">
													<h:graphicImage url="/imagenes/aceptar_16.png"
														style="border-color:rgb(255,255,255);"
														alt="#{resCore['ACEPTAR']}" />
													<tr:attribute name="idLinea" value="#{pedidoLinea.id}" />
												</tr:commandLink>
												<tr:spacer width="10" height="0" />
												<tr:commandLink id="cancelarEdicion"
													action="#{pedidos_consultarPedidosBean.cancelarEdicionLinea}"
													rendered="#{pedidoLinea.enEdicion}" partialSubmit="true">
													<h:graphicImage url="/imagenes/cancelar_16.png"
														style="border-color:rgb(255,255,255);"
														alt="#{resCore['CANCELAR']}" />
													<tr:attribute name="idLinea" value="#{pedidoLinea.id}" />
												</tr:commandLink>
											</tr:panelHorizontalLayout>
										</tr:column>
										<tr:column sortable="true" align="end"
											headerText="#{resPed['NUMERO_LINEA']}"
											sortProperty="numeroLinea">
											<tr:outputText value="#{pedidoLinea.numeroLinea}" />
										</tr:column>
										<tr:column sortable="true" align="end"
											headerText="#{resCat['CODIGO_ARTICULO']}"
											sortProperty="codigoArticulo">
											<tr:outputText value="#{pedidoLinea.codigoArticulo}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resCat['ARTICULO']}"
											sortProperty="descripcionArticulo">
											<tr:outputText value="#{pedidoLinea.descripcionArticulo}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resCat['CIP']}"
											sortProperty="productoCIP" align="start">
											<tr:outputText value="#{pedidoLinea.productoCIP}" />
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resPed['NOMBRE_COMERCIAL']}"
											sortProperty="descripcionProducto">
											<tr:outputText value="#{pedidoLinea.descripcionProducto}" />
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resCat['DISTRIBUIDOR']}"
											sortProperty="referenciaDistribuidor" align="start">
											<tr:outputText value="#{pedidoLinea.referenciaDistribuidor}" />
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resCat['FABRICANTE']}"
											sortProperty="referenciaFabricante" align="start">
											<tr:outputText value="#{pedidoLinea.referenciaFabricante}" />
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resPed['CODIGO_EAN']}"
											sortProperty="codigoEAN" align="start">
											<tr:outputText value="#{pedidoLinea.codigoEAN}" />
										</tr:column>
										<tr:column sortable="true" align="end"
											headerText="#{resPed['CANTIDAD']}"
											sortProperty="cantidadCompra">
											<tr:outputText value="#{pedidoLinea.cantidadCompra}"
												rendered="#{!pedidoLinea.enEdicion}" />
											<tr:inputText columns="10" id="inputcantidad"
												value="#{pedidoLinea.nuevaCantidadCompra}"
												rendered="#{pedidoLinea.enEdicion}"
												contentStyle="text-align:right">
											</tr:inputText>
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resCat['UNIDAD_PEDIDO']}"
											sortProperty="presentacion">
											<tr:outputText value="#{pedidoLinea.presentacion}" />
										</tr:column>
										<tr:column sortable="true" align="end"
											headerText="#{resCat['PRECIO']}" sortProperty="precio">
											<tr:outputText value="#{pedidoLinea.precio}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['IMPORTE']}"
											sortProperty="importe" align="end">
											<tr:outputText value="#{pedidoLinea.importe}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['CCA']}"
											sortProperty="cca" align="end"
											rendered="#{pedidos_consultarPedidosBean.mostrarColumnaCca}">
											<tr:outputText value="#{pedidoLinea.cca}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['CENTRO_CONSUMO']}"
											sortProperty="centroConsumo" align="end"
											rendered="#{pedidos_consultarPedidosBean.mostrarColumnaCentroConsumo}">
											<tr:outputText value="#{pedidoLinea.centroConsumo}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['DESCRIPCION_PUNTO_ENTREGA']}"
											sortProperty="descripcionPuntoEntrega" align="end">
											<tr:outputText value="#{pedidoLinea.descripcionPuntoEntrega}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['PUNTO_OPERACIONAL_PUNTO_ENTREGA']}"
											sortProperty="ptoOperacionalPuntoEntrega" align="end">
											<tr:outputText value="#{pedidoLinea.ptoOperacionalPuntoEntrega}" />
										</tr:column>
										<tr:column sortable="true" headerText="#{resPed['USUARIO']}"
											sortProperty="usuario" align="end">
											<tr:outputText value="#{pedidoLinea.usuario}" />
										</tr:column>
										<tr:column sortable="false" sortProperty="observaciones"
											align="center">
											<tr:outputText value="#{pedidoLinea.observaciones}"
												rendered="false" id="observacionesPedidoLinea"
												binding="#{pedidos_consultarPedidosBinding.observacionesPedidoLinea}" />
											<tr:commandLink
												action="#{pedidos_consultarPedidosBean.linkObservaciones}"
												useWindow="true" partialSubmit="true"
												launchListener="#{pedidos_consultarPedidosBean.asociarObservacionesPedidoLinea}"
												windowHeight="450" windowWidth="450"
												inlineStyle="vertical-align:middle;"
												rendered="#{pedidoLinea.observaciones !=null}">
												<h:graphicImage url="/imagenes/observacion.gif"
													style="border-color:rgb(255,255,255);" alt="Observaciones"
													title="#{pedidoLinea.observaciones}" />
											</tr:commandLink>
										</tr:column>
										<tr:column sortable="false">
											<f:facet name="header">
												<tr:outputText
													value="#{resPed['INICIAL_MEDIDA_CONSERVACION'] }"
													shortDesc="#{resCat['MEDIDAS_CONSERVACION_ESPECIAL']}" />
											</f:facet>
											<tr:image
												source="/imagenes/#{pedidoLinea.icMedConservacion}.gif"
												shortDesc="#{pedidoLinea.medConservacion}" />
										</tr:column>
										<tr:column sortable="false">
											<f:facet name="header">
												<tr:outputText
													value="#{resPed['INICIAL_TIPO_MANIPULACION'] }"
													shortDesc="#{resPed['TIPOMANIPULACION']}" />
											</f:facet>
											<tr:image
												source="/imagenes/#{pedidoLinea.icTipoManipulacion}.gif"
												shortDesc="#{pedidoLinea.tipoManipulacion}" />
										</tr:column>
										<tr:column rendered="#{pedidos_consultarPedidosBean.tienePermisoMantener}">
											<tr:commandButton text="#{resPed['VER_DETALLES']}"
												action="#{pedidos_consultarPedidosBean.verDetallesLinea(pedidoLinea.id)}" />
										</tr:column>
										<!--<f:facet name="selection">-->
										<!--	<tr:tableSelectMany text="#{resCore['NO_ELEMENTOS']}" />-->
										<!--</f:facet>-->
										<f:facet name="footer" />
									</tr:table>
									<tr:panelGroupLayout
										rendered="#{pedidos_consultarPedidosBean.mostrarAceptarCancelar}">
										<tr:commandButton text="#{resCore['ACEPTAR']}"
											returnListener="#{pedidos_consultarPedidosBean.confirmarDuplicarPedido}"
											action="#{pedidos_consultarPedidosBean.duplicarPedido}"
											useWindow="true" windowHeight="300" windowWidth="800"
											partialSubmit="false" id="btnAceptarDuplicado" />
										<tr:commandButton text="#{resCore['CANCELAR']}"
											action="#{pedidos_consultarPedidosBean.cancelarDuplicar}"
											partialSubmit="false" id="btnCancelarDuplicado" />
									</tr:panelGroupLayout>
								</tr:panelGroupLayout>
<!--							</f:facet>-->
<!--						</tr:switcher>-->
					</tr:panelHorizontalLayout>
					<tr:spacer width="10" height="10" />
					
					<tr:panelHorizontalLayout halign="right">
						<tr:showDetailHeader text="#{resOrg['LEYENDA']}" id="showDetail" partialTriggers="showDetail">
							<tr:table var="var" rows="10" value="#{pedidos_consultarPedidosBean.leyenda}">
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false">
									<tr:image source="#{var.srcIcono}" />
								</tr:column>
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false" headerText="#{resPed['INICIAL_EDO_AUTORIZACION']} (#{resPed['ESTADOAUTORIZACION']})" >
									<tr:outputText value="#{var.significadoAutorizacion}" />
								</tr:column>
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false" headerText="#{resPed['INICIAL_EDO_COMUNICACION']} (#{resPed['ESTADOCOMUNICACION']})" >
									<tr:outputText value="#{var.significadoComunicacion}" />
								</tr:column>
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false" headerText="#{resPed['INICIAL_EDO_PROGRAMACION']} (#{resPed['ESTADOPROGRAMACION']})" >
									<tr:outputText value="#{var.significadoProgramacion}" />
								</tr:column>
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false" headerText="#{resPed['INICIAL_EDO_RECEPCION']} (#{resPed['ESTADORECEPCION']})" >
									<tr:outputText value="#{var.significadoRecepcion}" />
								</tr:column>
								<tr:column inlineStyle="vertical-align:middle" align="center" sortable="false" headerText="#{resPed['INICIAL_EDO_RECLAMACION']} (#{resPed['ESTADORECLAMACION']})" >
									<tr:outputText value="#{var.significadoReclamacion}" />
								</tr:column>
							</tr:table>
							<!--<tr:image source="/imagenes/Leyenda_Estados.jpg" />-->
						</tr:showDetailHeader>
					</tr:panelHorizontalLayout>
				</tr:panelBox>
				<!--</f:facet>-->
				<!--</tr:switcher>-->
				<tr:spacer width="10" height="10" />
				<tr:panelHorizontalLayout halign="left">
					<tr:commandButton text="#{resCore['ACEPTAR']}"
						action="#{pedidos_consultarPedidosBean.aceptar}"
						rendered="#{pedidos_consultarPedidosBean.mostrarVolver}"
						id="aceptarPedido" />
					<tr:commandButton text="#{resCore['CANCELAR']}"
						action="#{pedidos_consultarPedidosBean.cancelar}"
						rendered="#{pedidos_consultarPedidosBean.mostrarVolver}"
						id="cancelarPedido" />
				</tr:panelHorizontalLayout>
				<tr:spacer width="10" height="10" />
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
