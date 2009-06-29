<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/strict.dtd"
		doctype-public="-//W3C//DTD HTML 4.01//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>
		<trh:head title="#{resPed['CABECERA_PAGINA_RESUMEN_PROPUESTA']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>
		<trh:body>
			<h:form>
				<tr:panelHeader text="#{resPed['PROPUESTAS_GENERADAS']}" />
				<tr:panelBox background="medium" inlineStyle="width: 100%;"
					partialTriggers="tablaPropuestas">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{resPed['NO_SE_GENERA_PROPUESTA']}" rows="10"
						width="100%" var="propuesta" id="tablaPropuestas"
						value="#{pedidos_resumenPropuestaCompraBean.propuestas}">
						<f:facet name="detailStamp">
							<tr:table rowBandingInterval="1" columnBandingInterval="0"
								emptyText="#{resPed['NO_SE_GENERA_PROPUESTA']}" rows="10"
								width="100%" var="propuestasNoSeleccionadas"
								value="#{propuesta.propuestasNoSeleccionadasPorDefecto}">
								<tr:column sortable="false"
									headerText="#{resOrg['CENTRO_CONSUMO']}" align="start">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.centroConsumo}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_SOLICITADA']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.cantidadSolicitada}"
										shortDesc="#{redPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_SOLICITADA_TIP']}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_MINIMA_A_SERVIR']}"
									align="start">
									<tr:outputText value="#{propuestasNoSeleccionadas.cantServir}"
										shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_MINIMA_A_SERVIR_TIP']}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_SERIAN']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.totalUnidadesMinAServir}"
										shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_SERIAN_TIP']}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_PEDIR']}"
									align="start">
									<tr:outputText value="#{propuestasNoSeleccionadas.cantPedir}"
										shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_PEDIR_TIP']}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_SERVIR']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.totalUnidadesAPedir}"
										shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_SERVIR_TIP']}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_DIFERENCIA']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.diferencia}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_PRECIO_UNITARIO']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.precioUnitario}"
										shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_PRECIO_UNITARIO_TIP']}" />
								</tr:column>
								<tr:column sortable="false" headerText="#{resPed['IMPORTE']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.importe}" />
								</tr:column>
								<tr:column sortable="false"
									headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_DIFERENCIA_IMPORTE']}"
									align="end">
									<tr:outputText
										value="#{propuestasNoSeleccionadas.calculos.diferenciaImporte}" />
								</tr:column>
							</tr:table>
						</f:facet>
						<tr:column sortable="true" sortProperty="nombreArticulo"
							headerText="#{resCat['ARTICULO']}" align="start">
							<tr:outputText value="#{propuesta.nombreArticulo}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="CIP"
							headerText="#{resCat['CIP']}" align="start">
							<tr:outputText value="#{propuesta.CIP}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="refFabricante"
							headerText="#{resCat['REF_FABRICANTE']}" align="start">
							<tr:outputText value="#{propuesta.refFabricante}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="empresa"
							headerText="#{resPed['EMPRESA']}" align="start">
							<tr:outputText value="#{propuesta.empresa}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="EAN"
							headerText="#{resPed['EAN']}" align="start">
							<tr:outputText value="#{propuesta.EAN}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="precio"
							headerText="#{resPed['IMPORTE']}" align="end">
							<tr:outputText value="#{propuesta.precio}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="tipoCompra"
							headerText="#{resPed['TIPO_COMPRA']}" align="start">
							<tr:outputText value="#{propuesta.tipoCompra}" />
						</tr:column>
						<tr:column sortable="false"
							headerText="#{resOrg['CENTRO_CONSUMO']}" align="start">
							<tr:outputText value="#{propuesta.centroConsumo}" />
						</tr:column>
						<tr:column sortable="false"
							headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_SOLICITADA']}"
							align="end">
							<tr:outputText value="#{propuesta.cantidadSolicitada}"
								shortDesc="#{redPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_SOLICITADA_TIP']}" />
						</tr:column>
						<tr:column sortable="false"
							headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_PEDIR']}"
							align="start">
							<tr:outputText value="#{propuesta.cantPedir}"
								shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_PEDIR_TIP']}" />
						</tr:column>
						<tr:column sortable="false"
							headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_SERVIR']}"
							align="end">
							<tr:outputText value="#{propuesta.calculos.totalUnidadesAPedir}"
								shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_CANTIDAD_A_SERVIR_TIP']}" />
						</tr:column>
						<tr:column sortable="false"
							headerText="#{resPed['TABLA_RESUMEN_PROPUESTAS_PRECIO_UNITARIO']}"
							align="end">
							<tr:outputText value="#{propuesta.calculos.precioUnitario}"
								shortDesc="#{resPed['TABLA_RESUMEN_PROPUESTAS_PRECIO_UNITARIO_TIP']}" />
						</tr:column>
					</tr:table>
				</tr:panelBox>
				<tr:spacer width="100%" height="10" />
				<tr:panelBox inlineStyle="width: 100%;"
					text="#{resPed['LINEAS_PEDIDO_SIN_PROCESAR']}">
					<tr:table rowBandingInterval="1" columnBandingInterval="0"
						emptyText="#{resOrg['NO_HAY_LINEAS_SIN_PROCESAR']}" rows="10"
						width="100%" var="lineasDenegadas"
						value="#{pedidos_resumenPropuestaCompraBean.lineasDenegadas}">
						<tr:column sortable="true" sortProperty="codigoArticulo"
							headerText="#{resCat['CODIGO_ARTICULO']}">
							<tr:outputText value="#{lineasDenegadas.codigoArticulo}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="nombreArticulo"
							headerText="#{resCat['ARTICULO']}">
							<tr:outputText value="#{lineasDenegadas.nombreArticulo}" />
						</tr:column>
						<tr:column sortable="true" sortProperty="mensajesLog"
							headerText="#{resCore['INCIDENCIAS']}">
							<tr:outputText value="#{lineasDenegadas.mensajesLog}" />
						</tr:column>
					</tr:table>
					<tr:panelButtonBar halign="end">
						<tr:commandButton text="#{resCore['CERRAR']}"
							action="#{pedidos_resumenPropuestaCompraBean.cerrar}" />
					</tr:panelButtonBar>
				</tr:panelBox>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
