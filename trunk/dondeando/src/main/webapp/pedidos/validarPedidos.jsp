<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<trh:html>
		<trh:head title="#{resPed['SELECCIONAR_PEDIDOS_VALIDAR']}">
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
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelFormLayout>
					<tr:messages />
					<tr:spacer width="10" height="10" />
					<tr:panelBox background="medium" partialTriggers="tablaPedidos">
						<tr:spacer width="10" height="10" />
						<tr:panelHeader text="#{resPed['SELECCIONAR_PEDIDOS_VALIDAR']}"
							messageType="none" />
						<tr:table rows="10" rowBandingInterval="1"
							columnBandingInterval="0"
							emptyText="#{resPed['TABLA_PEDIDOS_VACIA']}" var="pedidos"
							value="#{pedidos_validarPedidosBean.recogerPedidos}" width="100%"
							id="tablaPedidos"
							binding="#{pedidos_validarPedidosBinding.tablaPedidos}"
							partialTriggers="validar" immediate="false"
							allDetailsEnabled="true" rowSelection="multiple">
							<!--<f:facet name="selection">-->
							<!--	<tr:tableSelectMany-->
							<!--	binding="#{pedidos_validarPedidosBinding.tablaPedidosSeleccion}"-->
							<!--	partialTriggers="validar"-->
							<!--	id="tablaPedidosSeleccion" />-->
							<!--</f:facet>-->
							<tr:column sortable="true" headerText="#{resPed['NPEDIDO']}"
								sortProperty="numeroPedido" align="end">
								<tr:outputText value="#{pedidos.numeroPedido}" />
							</tr:column>
							<tr:column sortable="true" align="end"
								headerText="#{resPed['EJERCICIO']}" sortProperty="ejercicio">
								<tr:outputText value="#{pedidos.ejercicio}" />
							</tr:column>
							
							<!-- GEOS680 Se fusionan procedimiento Compra y Expediente -->
							<tr:column sortable="true" align="start"
								headerText="#{resPed['PROCEDIMIENTO_EXPEDIENTE']}"
								sortProperty="procedimientoExpediente">
								<tr:outputText value="#{pedidos.procedimientoExpediente}" />
							</tr:column>
							
							<!--
							<tr:column sortable="true" align="start"
								headerText="#{resPed['PROCEDIMIENTO_COMPRA']}"
								sortProperty="procedimientoCompra">
								<tr:outputText value="#{pedidos.procedimientoCompra}" />
							</tr:column>
							<tr:column sortable="true" align="end"
								headerText="#{resPed['EXPEDIENTE']}" sortProperty="expediente">
								<tr:outputText value="#{pedidos.expediente}" />
							</tr:column>
							-->
							
							<tr:column sortable="true" headerText="#{resCore['FECHA']}"
								sortProperty="fechaPedido">
								<tr:outputText value="#{pedidos.fechaPedido}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['PROVEEDOR']}"
								sortProperty="empresa">
								<tr:outputText value="#{pedidos.empresa}" />
							</tr:column>
							<tr:column sortable="true" headerText="#{resPed['IMPORTE']}"
								sortProperty="importe" align="end">
								<tr:outputText value="#{pedidos.importe}" />
							</tr:column>
							<tr:column sortable="true"
								headerText="#{resPed['FECHA_REGISTRO']}"
								sortProperty="fechaRegistro">
								<tr:outputText value="#{pedidos.fechaRegistro}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="tipoPedido">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_TIPO_PEDIDO']}"
										shortDesc="#{resPed['TIPO_PEDIDO']}" />
								</f:facet>
								<tr:image source="../imagenes/#{pedidos.icTipoPedido}.gif"
									shortDesc="#{resPed[pedidos.tipoPedido]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="condFacturar">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_CON_FACTURACION']}"
										shortDesc="#{resPed['COND_FACTURAR']}" />
								</f:facet>
								<tr:image source="../imagenes/#{pedidos.icCondiciones}.gif"
									shortDesc="#{resPed[pedidos.condFacturar]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoAutorizacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_AUTORIZACION']}"
										shortDesc="#{resPed['ESTADOAUTORIZACION']}" />
								</f:facet>
								<tr:image
									source="../imagenes/#{pedidos.icEstadoAutorizacion}.gif"
									shortDesc="#{resPed[pedidos.estadoAutorizacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoComunicacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_COMUNICACION']}"
										shortDesc="#{resPed['ESTADOCOMUNICACION']}" />
								</f:facet>
								<tr:image
									source="../imagenes/#{pedidos.icEstadoComunicacion}.gif"
									shortDesc="#{resPed[pedidos.estadoComunicacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoProgramacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_PROGRAMACION']}"
										shortDesc="#{resPed['ESTADOPROGRAMACION']}" />
								</f:facet>
								<tr:image
									source="../imagenes/#{pedidos.icEstadoProgramacion}.gif"
									shortDesc="#{resPed[pedidos.estadoProgramacion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoRecepcion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECEPCION']}"
										shortDesc="#{resPed['ESTADORECEPCION']}" />
								</f:facet>
								<tr:image source="../imagenes/#{pedidos.icEstadoRecepcion}.gif"
									shortDesc="#{resPed[pedidos.estadoRecepcion]}" />
							</tr:column>
							<tr:column sortable="true" align="center"
								sortProperty="estadoReclamacion">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECLAMACION']}"
										shortDesc="#{resPed['ESTADORECLAMACION']}" />
								</f:facet>
								<tr:image
									source="../imagenes/#{pedidos.icEstadoReclamacion}.gif"
									shortDesc="#{resPed[pedidos.estadoReclamacion]}" />
							</tr:column>
							<tr:column sortable="false"
								sortProperty="observaciones" align="center">
								<tr:outputText value="#{pedidos.observaciones}" rendered="false"
									id="observacionesPedido"
									binding="#{pedidos_validarPedidosBinding.observacionesPedidos}" />
								<tr:commandLink
									action="#{pedidos_validarPedidosBean.linkObservaciones}"
									useWindow="true" partialSubmit="true"
									launchListener="#{pedidos_validarPedidosBean.asociarObservacionesPedido}"
									windowHeight="450" windowWidth="450"
									inlineStyle="vertical-align:middle;"
									rendered="#{pedidos.observaciones !=null}">
									<h:graphicImage url="/imagenes/observacion.gif"
										style="border-color:rgb(255,255,255);" alt="Observaciones"
										title="#{pedidos.observaciones}" />
								</tr:commandLink>
							</tr:column>
							<f:facet name="detailStamp">
								<tr:panelFormLayout>
									<tr:spacer width="10" height="10" />
									<tr:outputLabel value="#{resNec['DETALLES_PEDIDO']}" />
									<tr:spacer width="10" height="10" />
									<tr:table width="100%"
										emptyText="#{resPed['TABLA_LINEAS_PEDIDO_VACIA']}"
										id="tablaPedidoLinea" var="pedidoLinea"
										value="#{pedidos.lineasVista}"
										binding="#{pedidos_validarPedidosBinding.tablaPedidoLinea}"
										partialTriggers="" rowBandingInterval="1"
										columnBandingInterval="0" rows="0">
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
										<tr:column sortable="true" align="end"
											headerText="#{resPed['CANTIDAD']}"
											sortProperty="cantidadCompra">
											<tr:outputText value="#{pedidoLinea.cantidadCompra}" />
										</tr:column>
										<tr:column sortable="true"
											headerText="#{resCat['PRESENTACION']}"
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
										<tr:column sortable="false"
											sortProperty="observaciones" align="center">
											<tr:outputText value="#{pedidoLinea.observaciones}"
												rendered="false" id="observacionesPedidoLinea"
												binding="#{pedidos_validarPedidosBinding.observacionesPedidoLinea}" />
											<tr:commandLink
												action="#{pedidos_validarPedidosBean.linkObservaciones}"
												useWindow="true" partialSubmit="true"
												launchListener="#{pedidos_validarPedidosBean.asociarObservacionesPedidoLinea}"
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
												source="../imagenes/#{pedidoLinea.icMedConservacion}.gif"
												shortDesc="#{pedidoLinea.medConservacion}" />
										</tr:column>
										<tr:column sortable="false">
											<f:facet name="header">
												<tr:outputText
													value="#{resPed['INICIAL_TIPO_MANIPULACION'] }"
													shortDesc="#{resPed['TIPOMANIPULACION']}" />
											</f:facet>
											<tr:image
												source="../imagenes/#{pedidoLinea.icTipoManipulacion}.gif"
												shortDesc="#{pedidoLinea.tipoManipulacion}" />
										</tr:column>
										<f:facet name="footer" />
									</tr:table>
									<tr:spacer width="10" height="10" />
								</tr:panelFormLayout>
							</f:facet>
						</tr:table>
					</tr:panelBox>
				</tr:panelFormLayout>
				<tr:spacer width="10" height="10" />
				<tr:panelHorizontalLayout halign="right">
					<tr:commandButton text="#{resPed['VALIDAR']}" rendered="true"
						id="validar" action="#{pedidos_validarPedidosBean.botonValidar}" />
					<tr:spacer width="10" height="10" />
					<tr:commandButton text="#{resCore['CANCELAR']}" rendered="true"
						id="rechazar" action="#{pedidos_validarPedidosBean.botonCancelar}" />
				</tr:panelHorizontalLayout>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
