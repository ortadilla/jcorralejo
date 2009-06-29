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
		<trh:head title="#{resPed['GENERACION_PEDIDOS_EXTERNOS'] }">
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
			<tr:form id="formResumenPedidos" onsubmit="bloquearPantalla(this);">
				<tr:panelFormLayout>
					<tr:panelHeader text="#{resPed['PEDIDOS_EXTERNOS_GENERADOS'] }" />
					<tr:panelBox background="medium" inlineStyle="width: 100%;"
						partialTriggers="tablaDetalleId">
						<tr:table id="tablaDetalleId" rowBandingInterval="1"
							columnBandingInterval="0"
							emptyText="#{resPed['NO_SE_GENERA_PEDIDOS'] }" width="100%"
							value="#{pedidos_resumenPedidos.pedidos}" var="pedido" rows="10"
							binding="#{pedidos_resumenPedidosBinding.tablaDetalleId}"
							selectionListener="#{pedidos_resumenPedidos.mostrarLineasPedido}"
							rowSelection="multiple" autoSubmit="true">
							<!--							<f:facet name="selection">-->
							<!--								<tr:tableSelectMany autoSubmit="true" id="SelectorTablaMuchos" />-->
							<!--							</f:facet>-->
							<tr:column sortable="true" sortProperty="numeroPedido"
								headerText="#{resPed['NUMERO'] }">
								<tr:outputText value="#{pedido.numeroPedido}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="ejercicio"
								headerText="#{resPed['EJERCICIO'] }">
								<tr:outputText value="#{pedido.ejercicio}" />
							</tr:column>
							
							<!-- GEOS680 Se fusionan procedimiento Compra y Expediente -->
							<tr:column sortable="true" align="start"
								headerText="#{resPed['PROCEDIMIENTO_EXPEDIENTE']}"
								sortProperty="procedimientoExpediente">
								<tr:outputText value="#{pedido.procedimientoExpediente}" />
							</tr:column>
							
							
							<!-- 
							<tr:column sortable="true" sortProperty="procedimientoCompra"
								headerText="#{resPed['PROCEDIMIENTO_COMPRA']}">
								<tr:outputText value="#{pedido.procedimientoCompra}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="expediente"
								headerText="#{resPed['EXPEDIENTE'] }">
								<tr:outputText value="#{pedido.expediente}" />
							</tr:column>
							-->
							
							<tr:column sortable="true" sortProperty="fechaPedido"
								headerText="#{resCore['FECHA'] }">
								<tr:outputText value="#{pedido.fechaPedido}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" sortProperty="empresa"
								headerText="#{resPed['EMPRESA'] }">
								<tr:outputText value="#{pedido.empresa}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="importe"
								headerText="#{resPed['IMPORTE'] }">
								<tr:outputText value="#{pedido.importe}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="organoGestor"
								headerText="#{resOrg['ORGANO_GESTOR'] }">
								<tr:outputText value="#{pedido.organoGestor}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="fechaRegistro"
								headerText="#{resPed['FECHA_REGISTRO']}">
								<tr:outputText value="#{pedido.fechaRegistro}">
									<tr:convertDateTime pattern="dd/MM/yyyy" />
								</tr:outputText>
							</tr:column>
							<tr:column sortable="true" sortProperty="tipoPedido"
								align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_TIPO_PEDIDO']}" 
										shortDesc="#{resPed['TIPO_PEDIDO']}"/>
								</f:facet>
								<tr:image source="../imagenes/#{pedido.icTipoPedido}.gif"
									shortDesc="#{resPed[pedido.tipoPedido]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="condFacturar" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_CON_FACTURACION']}"
										shortDesc="#{resPed['COND_FACTURAR']}"/>
								</f:facet>
								<tr:image source="../imagenes/#{pedido.icCondiciones}.gif"
									shortDesc="#{resPed[pedido.condFacturar]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estadoAutorizacion" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_AUTORIZACION']}"
										shortDesc="#{resPed['ESTADOAUTORIZACION']}"/>
								</f:facet>
								<tr:image
									source="../imagenes/#{pedido.icEstadoAutorizacion}.gif"
									shortDesc="#{resPed[pedido.estadoAutorizacion]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estadoComunicacion" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_COMUNICACION']}"
										shortDesc="#{resPed['ESTADOCOMUNICACION']}"/>
								</f:facet>
								<tr:image
									source="../imagenes/#{pedido.icEstadoComunicacion}.gif"
									shortDesc="#{resPed[pedido.estadoComunicacion]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estadoProgramacion" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_PROGRAMACION']}"
										shortDesc="#{resPed['ESTADOPROGRAMACION']}"/>
								</f:facet>
								<tr:image
									source="../imagenes/#{pedido.icEstadoProgramacion}.gif"
									shortDesc="#{resPed[pedido.estadoProgramacion]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estadoRecepcion" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECEPCION']}"
										shortDesc="#{resPed['ESTADORECEPCION']}"/>
								</f:facet>
								<tr:image source="../imagenes/#{pedido.icEstadoRecepcion}.gif"
									shortDesc="#{resPed[pedido.estadoRecepcion]}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estadoReclamacion" align="center">
								<f:facet name="header">
									<tr:outputText value="#{resPed['INICIAL_EDO_RECLAMACION']}"
										shortDesc="#{resPed['ESTADORECLAMACION']}"/>
								</f:facet>
								<tr:image source="../imagenes/#{pedido.icEstadoReclamacion}.gif"
									shortDesc="#{resPed[pedido.estadoReclamacion]}" />
							</tr:column>
							<tr:column sortable="false" headerText="#{resPed['OBS'] }">
								<h:graphicImage url="/imagenes/observacion.gif"
									style="border-color:rgb(255,255,255);" alt="observaciones" />
							</tr:column>
							<f:facet name="footer">
								<tr:panelButtonBar>
									<tr:commandButton text="#{resCore['ACEPTAR'] }"
										action="#{pedidos_resumenPedidos.aceptar}" 
										id="aceptar"/>
									<tr:commandButton text="#{resCore['CANCELAR'] }"
										action="#{pedidos_resumenPedidos.cancelar}" 
										id="cancelar"/>
								</tr:panelButtonBar>
							</f:facet>
						</tr:table>
						<tr:showDetailHeader text="#{resPed['LINEAS_PEDIDOS'] }"
							disclosed="false" id="pedidoLineas"
							partialTriggers="tablaDetalleId pedidoLineas"
							binding="#{pedidos_resumenPedidosBinding.mostrarLineas}">
							<tr:table id="tablaDetalleLineasId" rowBandingInterval="1"
								columnBandingInterval="0"
								emptyText="#{resPed['NO_SELECCIONADO_LINEAS'] }" width="100%"
								value="#{pedidos_resumenPedidos.lineas}" var="lineas" rows="10"
								binding="#{pedidos_resumenPedidosBinding.tablaDetalleLineasId}">
								<tr:column sortable="true" align="end"
									headerText="#{resPed['NUMERO_LINEA']}"
									sortProperty="numeroLinea">
									<tr:outputText value="#{lineas.numeroLinea}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="codigoArticulo"
									headerText="#{resCat['CODIGO_ARTICULO'] }">
									<tr:outputText value="#{lineas.codigoArticulo}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="descripcionArticulo"
									headerText="#{resCat['ARTICULO'] }">
									<tr:outputText value="#{lineas.descripcionArticulo}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="productoCIP"
									headerText="#{resCat['CIP'] }">
									<tr:outputText value="#{lineas.productoCIP}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resCat['PRODUCTO']}"
									sortProperty="descripcionProducto">
									<tr:outputText value="#{lineas.descripcionProducto}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="referenciaDistribuidor"
									headerText="#{resCat['DISTRIBUIDOR'] }">
									<tr:outputText value="#{lineas.referenciaDistribuidor}" />
								</tr:column>
								<tr:column sortable="true" headerText="#{resCat['FABRICANTE']}"
									sortProperty="referenciaFabricante" align="start">
									<tr:outputText value="#{lineas.referenciaFabricante}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="cantidadCompra"
									headerText="#{resPed['CANTIDAD'] }">
									<tr:outputText value="#{lineas.cantidadCompra}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="presentacion"
									headerText="#{resCat['PRESENTACION'] }">
									<tr:outputText value="#{lineas.presentacion}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="precio"
									headerText="#{resCat['PRECIO'] }">
									<tr:outputText value="#{lineas.precio}" />
								</tr:column>
								<tr:column sortable="true" sortProperty="importe"
									headerText="#{resPed['IMPORTE'] }">
									<tr:outputText value="#{lineas.importe}" />
								</tr:column>
								<tr:column sortable="false" headerText="#{resPed['OBS'] }">
									<h:graphicImage url="/imagenes/observacion.gif"
										style="border-color:rgb(255,255,255);" alt="observaciones" />
								</tr:column>
								<tr:column sortable="false">
									<f:facet name="header">
													<tr:outputText value="#{resPed['INICIAL_MEDIDA_CONSERVACION'] }" 
														shortDesc="#{resCat['MEDIDAS_CONSERVACION_ESPECIAL']}"/>
									</f:facet>
									<tr:image source="../imagenes/#{lineas.icMedConservacion}.gif"
									shortDesc="#{lineas.medConservacion}" />
								</tr:column>
								<tr:column sortable="false">
											<f:facet name="header">
													<tr:outputText value="#{resPed['INICIAL_TIPO_MANIPULACION'] }" 
														shortDesc="#{resPed['TIPOMANIPULACION']}"/>
											</f:facet>
									<tr:image source="../imagenes/#{lineas.icTipoManipulacion}.gif"
									shortDesc="#{lineas.tipoManipulacion}"/>
								</tr:column>
							</tr:table>
						</tr:showDetailHeader>
					</tr:panelBox>
					<tr:spacer width="100%" height="10" />
					<tr:panelBox inlineStyle="width: 100%;"
						text="#{resPed['LINEAS_DENEGADAS']}">
						<tr:table id="tablaDetalleDenegadasId" rowBandingInterval="1"
							columnBandingInterval="0" emptyText="NO hay líneas denegadas"
							rows="10" width="100%"
							value="#{pedidos_resumenPedidos.denegadas}" var="propuesta">
							<tr:column sortable="true" sortProperty="codigoArticulo"
								headerText="#{resCat['CODIGO_ARTICULO']}">
								<tr:outputText value="#{propuesta.codigoArticulo}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="nombreArticulo"
								headerText="#{resCat['ARTICULO']}">
								<tr:outputText value="#{propuesta.nombreArticulo}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="empresa"
								headerText="#{resPed['EMPRESA'] }">
								<tr:outputText value="#{propuesta.empresa}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="mensajesLog"
								headerText="#{resCore['INCIDENCIAS'] }">
								<tr:outputText value="#{propuesta.mensajesLog}" />
							</tr:column>
						</tr:table>
					</tr:panelBox>
					<tr:spacer width="100%" height="10" />
					<tr:panelBox inlineStyle="width: 100%;"
						text="#{resPed['LINEAS_DENEGADAS_SIN_PROPUESTA']}">
						<tr:table id="tablaDetalleDenegadasSinPropuestaId"
							rowBandingInterval="1" columnBandingInterval="0"
							emptyText="NO hay líneas sin Propuesta" rows="10" width="100%"
							value="#{pedidos_resumenPedidos.denegadasSinPropuesta}"
							var="linea">
							<tr:column sortable="true" sortProperty="codigoArticulo"
								headerText="#{resCat['CODIGO_ARTICULO']}">
								<tr:outputText value="#{linea.codigoArticulo}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="nombreArticulo"
								headerText="#{resCat['ARTICULO'] }">
								<tr:outputText value="#{linea.nombreArticulo}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="cantidadAsociada"
								headerText="#{resPed['CANTIDAD'] }">
								<tr:outputText value="#{linea.cantidadAsociada}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="mensajesLog"
								headerText="#{resCore['INCIDENCIAS'] }">
								<tr:outputText value="#{linea.mensajesLog}" />
							</tr:column>
						</tr:table>
					</tr:panelBox>
				</tr:panelFormLayout>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
