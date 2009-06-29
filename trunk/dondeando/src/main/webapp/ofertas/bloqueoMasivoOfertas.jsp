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
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
		<f:loadBundle basename="mensajesOfe" var="mensajesOfe" />

		<trh:html>
		<trh:head title="#{mensajesOfe['GESTION_BLOQUEOS_OFERTAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="btnBuscar" onsubmit="bloquearPantalla(this);">
				<tr:panelPage inlineStyle="width:100%;">
					<tr:panelHeader text="#{ofertas_bloqueoMasivoOfertasBean.titulo}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:showDetailHeader text="#{ofertas_bloqueoMasivoOfertasBean.titulo}"
						disclosed="true"
						id="showDetail" partialTriggers="showDetail">
						<tr:panelBox
								partialTriggers=""
								inlineStyle="width:100%;"
								background="medium">
								<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<tr:spacer width="5" height="20" />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText styleClass="etiquetaForm" value="#{mensajesCat['CLASIFICACION']}" />
										<tr:inputText id="nivelValorPadre"
												value="#{ofertas_bloqueoMasivoOfertasBean.descripcionNivelValorPadre}"
												disabled="true"
												columns="60" />
										<tr:commandLink action="#{ofertas_bloqueoMasivoOfertasBean.buscarNivelValor}">
						                       <tr:image source="../imagenes/lupa3.gif"/>
					                    </tr:commandLink>
					                    <tr:commandLink action="#{ofertas_bloqueoMasivoOfertasBean.limpiarNivelValor}">
						                       <tr:image source="../imagenes/btnEliminar.gif"/>
					                    </tr:commandLink>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText styleClass="etiquetaForm" value="#{mensajesOfe['VALIDACIONTECNICA']}" />
										<trh:cellFormat columnSpan="3">
											<tr:panelHorizontalLayout>
												<tr:spacer width="5" height="0" />
												<tr:selectBooleanCheckbox id="validacionTecnicaSI"
														value="#{ofertas_bloqueoMasivoOfertasBean.validacionTecnicaSI}"/>
												<tr:outputText value="#{mensajesCore['SI']}" />
												<tr:spacer width="5" height="0" />
												<tr:selectBooleanCheckbox id="validacionTecnicaNO"
														value="#{ofertas_bloqueoMasivoOfertasBean.validacionTecnicaNO}"/>
												<tr:outputText value="#{mensajesCore['NO']}" />
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText styleClass="etiquetaForm" value="#{mensajesOfe['VALIDACIONLOGISTICA']}" />
										<trh:cellFormat columnSpan="3">
											<tr:panelHorizontalLayout>
												<tr:spacer width="5" height="0" />
												<tr:selectBooleanCheckbox id="validacionLogisticaSI"
														value="#{ofertas_bloqueoMasivoOfertasBean.validacionLogisticaSI}"/>
												<tr:outputText value="#{mensajesCore['SI']}" />
												<tr:spacer width="5" height="0" />
												<tr:selectBooleanCheckbox id="validacionLogisticaNO"
														value="#{ofertas_bloqueoMasivoOfertasBean.validacionLogisticaNO}"/>
												<tr:outputText value="#{mensajesCore['NO']}" />
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText styleClass="etiquetaForm" value="#{mensajesOfe['PENDIENTES_BLOQUEAR']}" 
														rendered="#{ofertas_bloqueoMasivoOfertasBean.mostrarPendientesBloquear}"/>
										<trh:cellFormat columnSpan="3">
											<tr:panelHorizontalLayout rendered="#{ofertas_bloqueoMasivoOfertasBean.mostrarPendientesBloquear}">
												<tr:spacer width="5" height="0" />
												<tr:selectBooleanCheckbox id="ofertasBloqueadas"
														value="#{ofertas_bloqueoMasivoOfertasBean.pendientesBloquear}"/>
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:spacer width="5" height="20" />
									</trh:rowLayout>
								</trh:tableLayout>
								<tr:panelHorizontalLayout halign="right">
									<tr:commandButton text="#{mensajesCore['BUSCAR']}"
										id="btnBuscar"
										action="#{ofertas_bloqueoMasivoOfertasBean.realizarConsulta}" 
										blocking="true"/>
									<tr:spacer width="10" height="0" />
									<tr:commandButton text="#{mensajesCore['LIMPIAR']}"
										action="#{ofertas_bloqueoMasivoOfertasBean.limpiarFormulario}"
										id="btnLimpiar"/>
								</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:showDetailHeader>
					<tr:spacer width="10" height="20" />
					<tr:panelBox text="Resultados" inlineStyle="width:100%;">
						<tr:table 
							var="var" first="0" emptyText="No hay elementos en la tabla"
							rows="10" width="100%"							
							value="#{ofertas_bloqueoMasivoOfertasBean.listaRamasBloqueadas}"
							rowBandingInterval="0"
							columnBandingInterval="0"
							selectedRowKeys="#{ofertas_bloqueoMasivoOfertasBean.estadoDeSeleccionTabla}"
							rowSelection="multiple">
							<f:facet name="actions">
								<h:panelGroup>
									<tr:panelButtonBar>
										<tr:commandButton text="#{mensajesOfe['BLOQUEO_MASIVO']}"
											id="btnBloqueo"
											action="#{ofertas_bloqueoMasivoOfertasBean.bloqueoMasivo}" 
											blocking="true"/>
										<tr:spacer width="10" height="0" />
										<tr:commandButton text="#{mensajesOfe['DESBLOQUEO_MASIVO']}"
											action="#{ofertas_bloqueoMasivoOfertasBean.desbloqueoMasivo}"
											id="btnDesbloqueo"/>
									</tr:panelButtonBar>
								</h:panelGroup>
							</f:facet>
							<tr:column headerText="#{mensajesCore['CODIGO']}" inlineStyle="#{!var.esNivelPadre?'background:white;':''}">
								<tr:outputText value="#{var.codigoConcatenado}" />
							</tr:column>
							<tr:column 
								headerText="#{mensajesCore['DESCRIPCION']}" inlineStyle="#{!var.esNivelPadre?'background:white;':''}">
								<tr:outputText value="#{var.descripcion}"/>
							</tr:column>
							<tr:column
								headerText="#{mensajesOfe['NUM_OFERTAS']}" inlineStyle="#{!var.esNivelPadre?'background:white;':''}">
								<tr:outputText rendered="#{!var.esNivelPadre}" value="#{var.numOfertas}" />
							</tr:column>
						</tr:table>
					</tr:panelBox>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>