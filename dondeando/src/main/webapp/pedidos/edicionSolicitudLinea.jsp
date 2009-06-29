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
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesEmp" var="resEmp" />
		<trh:html>
		<trh:head title="#{resPed['CABECERA_PAGINA_EDICION_SOLICITUD_LINEA']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form usesUpload="false" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader
						text="#{pedidos_edicionSolicitudLineaBean.tituloPagina}" />
					<!--					<f:facet name="menu1" />-->
					<!--					<f:facet name="menuGlobal" />-->
					<!--					<f:facet name="branding" />-->
					<!--					<f:facet name="brandingApp" />-->
					<!--					<f:facet name="appCopyright" />-->
					<!--					<f:facet name="appPrivacy" />-->
					<!--					<f:facet name="appAbout" />-->
					<tr:panelBox background="medium" inlineStyle="width: 100%;">
						<trh:tableLayout cellSpacing="3">
							<trh:rowLayout>
								<tr:outputText value="#{resPed['PEDIDO_INTERNO']}:" />
								<tr:inputText disabled="true"
									value="#{pedidos_edicionSolicitudLineaBean.identificador}"
									id="identificador"
									binding="#{pedidos_edicionSolicitudLineaBinding.identificador}" />
								<tr:outputText value="#{resPed['FECHA_SOLICITUD']}:" />
								<tr:inputText
									value="#{pedidos_edicionSolicitudLineaBean.fechaSolicitud}"
									binding="#{pedidos_edicionSolicitudLineaBinding.fechaSolicitud}"
									id="fechaSolicitud" disabled="true" columns="73" />
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resNec['ORIGEN']}:" />
								<trh:cellFormat columnSpan="3">
									<tr:inputText
										value="#{pedidos_edicionSolicitudLineaBean.origen}"
										id="origen"
										binding="#{pedidos_edicionSolicitudLineaBinding.origen}"
										disabled="true" rows="1" maximumLength="200" columns="120" />
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resOrg['PUNTO_ENTREGA']}:" />
								<trh:cellFormat columnSpan="3">
									<tr:selectOneChoice simple="true" autoSubmit="true"
										immediate="false" required="false" id="puntoEntrega"
										disabled="#{pedidos_edicionSolicitudLineaBean.edicionPropuesta}"
										value="#{pedidos_edicionSolicitudLineaBean.puntoEntrega}">
										<f:selectItems
											value="#{pedidos_edicionSolicitudLineaBean.puntosEntrega}"
											id="puntosEntrega" />
									</tr:selectOneChoice>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resNec['PRIORIDAD']}:" />
								<tr:selectOneChoice
									value="#{pedidos_edicionSolicitudLineaBean.prioridad}"
									valueChangeListener="#{pedidos_edicionSolicitudLineaBean.cambiaPrioridad}"
									id="comboPrioridades" autoSubmit="true"
									disabled="#{pedidos_edicionSolicitudLineaBean.edicionPropuesta}">
									<f:selectItems
										value="#{pedidos_edicionSolicitudLineaBean.prioridades}" />
								</tr:selectOneChoice>
								<tr:outputText value="#{resPed['MOTIVO_CAMBIO_PRIORIDAD']}"
									rendered="#{pedidos_edicionSolicitudLineaBean.mostrarInputMotivoPrioridad}" />
								<tr:inputText
									rendered="#{pedidos_edicionSolicitudLineaBean.mostrarInputMotivoPrioridad}"
									partialTriggers="comboPrioridades"
									value="#{pedidos_edicionSolicitudLineaBean.motivoCambioPrioridad}"
									disabled="#{pedidos_edicionSolicitudLineaBean.edicionPropuesta}"
									immediate="false" />
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resCat['ARTICULO']}" />
								<trh:cellFormat columnSpan="3">
									<tr:inputText disabled="true"
										value="#{pedidos_edicionSolicitudLineaBean.nombreArticulo}"
										columns="120" />
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resPed['CANTIDAD']}:" />
								<tr:inputText
									value="#{pedidos_edicionSolicitudLineaBean.cantidad}"
									binding="#{pedidos_edicionSolicitudLineaBinding.cantidad}"
									id="cantidad"
									disabled="#{pedidos_edicionSolicitudLineaBean.edicionPropuesta}" />
							</trh:rowLayout>
						</trh:tableLayout>
						<tr:spacer width="0" height="20" />
						<tr:panelGroupLayout
							rendered="#{pedidos_edicionSolicitudLineaBean.edicionPropuesta}">
							<tr:panelBox text="#{resPed['OFERTA_ASIGNADA_PARA_COMPRA']}">
								<tr:inputText label="#{resCat['DENOMINACION_COMERCIAL']}"
									value="#{pedidos_edicionSolicitudLineaBean.tarifa.infLogistica.oferta.producto.modelo}"
									disabled="true" />
								<tr:inputText label="#{resNec['PROVEEDOR']}" disabled="true"
									value="#{pedidos_edicionSolicitudLineaBean.tarifa.infLogistica.oferta.proveedor.nombreComercial}" />
								<tr:inputText label="#{resCat['CIP']}" disabled="true"
									value="#{pedidos_edicionSolicitudLineaBean.tarifa.infLogistica.oferta.producto.CIP}" />
								<tr:inputText label="#{resCat['FABRICANTE']}" disabled="true"
									value="#{pedidos_edicionSolicitudLineaBean.tarifa.infLogistica.oferta.producto.refFabricante}" />
								<tr:inputText label="#{resCat['DISTRIBUIDOR']}"
									value="#{pedidos_edicionSolicitudLineaBean.tarifa.infLogistica.oferta.refDistribuidor}"
									disabled="true" />
								<tr:table emptyText="No items were found" var="propuesta"
									rowBandingInterval="1" columnBandingInterval="0"
									value="#{pedidos_edicionSolicitudLineaBean.propuestasVista}">
									<tr:column sortable="false" headerText="Seleccionar">
										<tr:selectBooleanRadio label="Label 1" group="seleccionada"
											value="#{propuesta.seleccionada}" />
									</tr:column>
									<tr:column sortable="false" headerText="#{resPed['EAN']}">
										<tr:outputText value="#{propuesta.EAN}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['TIPO_COMPRA']}">
										<tr:outputText value="#{propuesta.tipoCompra}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['CANTIDAD_PEDIDA']}">
										<tr:outputText value="#{propuesta.cantidadSolicitada}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['CANTIDAD_MIN_SERVIR']}  (#{resPed['UNIDAD_DISTRIBUCION']}) ">
										<tr:outputText value="#{propuesta.cantServir}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['CANTIDAD_MIN_SERVIR']}   (#{resPed['UNIDAD_CONSUMO']})">
										<tr:outputText
											value="#{propuesta.calculos.totalUnidadesMinAServir}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['CANTIDAD_MIN_PEDIR']}   (#{resPed['UNIDAD_PEDIDO']})">
										<tr:outputText value="#{propuesta.cantPedir}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['CANTIDAD_MIN_PEDIR']}   (#{resPed['UNIDAD_CONSUMO']})">
										<tr:outputText
											value="#{propuesta.calculos.totalUnidadesAPedir}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['DIFERENCIA']}   (#{resPed['UNIDAD_CONSUMO']})">
										<tr:outputText value="#{propuesta.calculos.diferencia}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['PRECIO_UNITARIO']}">
										<tr:outputText value="#{propuesta.calculos.precioUnitario}" />
									</tr:column>
									<tr:column sortable="false" headerText="#{resEmp['IMPORTE']}">
										<tr:outputText value="#{propuesta.calculos.importe}" />
									</tr:column>
									<tr:column sortable="false"
										headerText="#{resPed['DIFERENCIA']}  #{resEmp['IMPORTE']}">
										<tr:outputText value="#{propuesta.calculos.diferenciaImporte}" />
									</tr:column>
								</tr:table>
								<tr:inputText label="#{resPed['MOTIVO_CAMBIO_OFERTA']}"
									value="#{pedidos_edicionSolicitudLineaBean.motivoCambioOferta}" />
								<tr:commandButton text="#{resCore['CAMBIAR']}"
									action="#{pedidos_edicionSolicitudLineaBean.cambiarOferta}" />
							</tr:panelBox>
							<tr:spacer width="0" height="10" />
							<tr:panelBox text="#{resCat['APLICACION_PRESUPUESTARIA']}">
								<tr:selectOneChoice label="#{resCat['CLASIFICACION_ORGANICA']}"
									value="#{pedidos_edicionSolicitudLineaBean.aplicacionPresupuestaria.organoGestor.id}"
									autoSubmit="true"
									valueChangeListener="#{pedidos_edicionSolicitudLineaBean.cambiaAplPresupuestariaOrganoGestor}">
									<f:selectItems
										value="#{pedidos_edicionSolicitudLineaBean.organosGestores}" />
								</tr:selectOneChoice>
								<tr:selectOneChoice label="#{resCat['CLASIFICACION_FUNCIONAL']}"
									value="#{pedidos_edicionSolicitudLineaBean.aplicacionPresupuestaria.funcional.codigo}"
									autoSubmit="true"
									valueChangeListener="#{pedidos_edicionSolicitudLineaBean.cambiaAplPresupuestariaFuncional}">
									<f:selectItems
										value="#{pedidos_edicionSolicitudLineaBean.funcionales}" />
								</tr:selectOneChoice>
								<tr:selectOneChoice label="#{resCat['CLASIFICACION_SERVICIO']}"
									value="#{pedidos_edicionSolicitudLineaBean.aplicacionPresupuestaria.servicio}"
									autoSubmit="true">
									<f:selectItems
										value="#{pedidos_edicionSolicitudLineaBean.servicios}" />
								</tr:selectOneChoice>
								<tr:selectOneChoice label="#{resCat['CLASIFICACION_ECONOMICA']}"
									value="#{pedidos_edicionSolicitudLineaBean.aplicacionPresupuestaria.economica.id}"
									autoSubmit="true"
									valueChangeListener="#{pedidos_edicionSolicitudLineaBean.cambiaAplPresupuestariaEconomica}">
									<f:selectItems
										value="#{pedidos_edicionSolicitudLineaBean.economicas}" />
								</tr:selectOneChoice>
							</tr:panelBox>
							<tr:spacer width="0" height="10" />
							<tr:panelBox background="medium">
								<tr:inputText label="#{resCore['OBSERVACIONES']}"
									value="#{pedidos_edicionSolicitudLineaBean.observaciones}" />
								<tr:selectBooleanCheckbox label="#{resPed['INTERNAS']}"
									value="#{pedidos_edicionSolicitudLineaBean.internas}" />
							</tr:panelBox>
						</tr:panelGroupLayout>
						<tr:panelHorizontalLayout>
							<tr:commandButton text="#{resCore['ACEPTAR']}"
								action="#{pedidos_edicionSolicitudLineaBean.aceptar}" />
							<tr:commandButton text="#{resCore['CANCELAR']}"
								action="#{pedidos_edicionSolicitudLineaBean.cancelar}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
