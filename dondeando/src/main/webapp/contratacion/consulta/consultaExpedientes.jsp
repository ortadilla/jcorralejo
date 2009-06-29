<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:tr="http://myfaces.apache.org/trinidad">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=ISO-8859-15" />
	<f:view>
		<trh:html>
		<trh:head title="Consulta de expedientes de contratación">
			<meta http-equiv="Content-Type"
				content="text/html; charset=ISO-8859-15" />
		</trh:head>
		<trh:body>

<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
            <tr:statusIndicator id="indicador">
                <f:facet name="busy">
                    <f:verbatim>
                            <div id="divEspera">
                                <p style="margin-top: 60px; text-align:center; width: 100%;">
                                [    Cargando datos, por favor espere...    ]</p>
                            </div>
                    </f:verbatim>
                </f:facet>
                <f:facet name="ready">
                </f:facet>
            </tr:statusIndicator>

			<trh:script source="/include/libreriaGEOS.js">
			                <!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

			<h:form onsubmit="bloquearPantalla(this)">
				<tr:panelHeader text="Consulta de expedientes de contratación" />
				<tr:messages />
				<tr:spacer width="10" height="10" />
				<tr:panelBox background="medium" partialTriggers="criterios criteriosADV" inlineStyle="width:100%" >
					<tr:showDetailHeader text="Criterios de búsqueda" id="criterios"
						disclosed="#{!consultaExpedientesContratacion.haRecuperadoDatos}">
						<tr:panelFormLayout labelWidth="230">
				            <tr:spacer width="10" height="10"/>
							<tr:panelLabelAndMessage label="Ámbito">
								<tr:panelHorizontalLayout>
									<tr:selectOneChoice simple="true"
										value="#{formBusquedaExpedienteContratacion.idAmbito}"
										autoSubmit="true" id="idAmbito"
										valueChangeListener="#{formBusquedaExpedienteContratacion.cambioAmbito}"
										unselectedLabel="Todos"
										inlineStyle="width:370px">
										<f:selectItems value="#{gestorListaAmbito.listaElementos}" />
									</tr:selectOneChoice>
									<tr:spacer width="10" height="10" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Estado">
								<tr:panelHorizontalLayout>
									<tr:selectOneChoice simple="true"
										value="#{formBusquedaExpedienteContratacion.codigoEstado}"
										partialTriggers="idAmbito" unselectedLabel="Todos"
										inlineStyle="width:680px">
										<f:selectItems
											value="#{formBusquedaExpedienteContratacion.estadosExpedientes}" />
									</tr:selectOneChoice>
									<tr:spacer width="10" height="10" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Rol"
								rendered="#{configuracionConsultaExpedientes.muestraRoles}">
								<tr:panelHorizontalLayout>
									<tr:selectOneChoice
										value="#{formBusquedaExpedienteContratacion.codigoRol}"
										unselectedLabel="Todos"
										inlineStyle="width:680px">
										<f:selectItems
											value="#{formBusquedaExpedienteContratacion.roles}" />
									</tr:selectOneChoice>
									<tr:spacer width="10" height="10" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="CCA">
								<tr:panelHorizontalLayout>
									<tr:inputText columns="10" simple="true"
										value="#{formBusquedaExpedienteContratacion.codigoCCA}" />
									<tr:spacer width="10" height="10" />

								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Número de expediente">
								<tr:inputText columns="10" simple="true"
									value="#{formBusquedaExpedienteContratacion.numeroExpediente}" />
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Objeto del contrato">
								<tr:inputText rows="2" columns="50" simple="true"
									value="#{formBusquedaExpedienteContratacion.objetoContrato}" />
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Tipo de contrato">
								<tr:selectOneChoice
									value="#{formBusquedaExpedienteContratacion.idTipoContrato}"
									unselectedLabel="Todos" simple="true"
									inlineStyle="width:370px">
									<f:selectItems
										value="#{gestorListaTipoContrato.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Procedimiento">
								<tr:selectOneChoice simple="true"
									value="#{formBusquedaExpedienteContratacion.idProcedimientoContratacion}"
									unselectedLabel="Todos"
									inlineStyle="width:370px">
									<f:selectItems
										value="#{gestorListaProcedimientoContratacion.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Forma de adjudicación">
								<tr:selectOneChoice simple="true"
									value="#{formBusquedaExpedienteContratacion.idFormaAdjudicacion}"
									unselectedLabel="Todos"
									inlineStyle="width:370px">
									<f:selectItems
										value="#{gestorListaFormaAdjudicacion.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Modelo de Pliego">
								<tr:selectOneChoice simple="true"
									value="#{formBusquedaExpedienteContratacion.idModeloPliego}"
									unselectedLabel="Todos"
									inlineStyle="width:680px">
									<f:selectItems
										value="#{gestorListaModeloPliego.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Fecha de inicio de resolución de">
								<tr:panelHorizontalLayout>
									<tr:inputDate simple="true"
										value="#{formBusquedaExpedienteContratacion.fechaInicioResolucionDesde}" />
									<tr:spacer width="10" height="10" />
									<tr:outputText value="a" />
									<tr:spacer width="10" height="10" />
									<tr:inputDate simple="true"
										value="#{formBusquedaExpedienteContratacion.fechaInicioResolucionHasta}" />
									<tr:spacer width="10" height="10" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
				            <tr:spacer width="10" height="10"/>
						</tr:panelFormLayout>
					</tr:showDetailHeader>
					<tr:showDetailHeader text="Criterios de búsqueda avanzada"  id="criteriosADV"
                          disclosed="#{!consultaExpedientesContratacion.busquedaAvanzada}">
						<tr:panelFormLayout labelWidth="230">
				            <tr:spacer width="10" height="10"/>
							<tr:panelLabelAndMessage label="Centro">
								<tr:selectOneChoice simple="true"
									value="#{formBusquedaExpedienteContratacion.centro}"
									unselectedLabel="Todos"
									inlineStyle="width:680px">
									<f:selectItems
										value="#{gestorListaOrganoGestor.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>

							<tr:panelLabelAndMessage label="Nº documento contable (Júpiter)">
								<tr:inputText columns="20" simple="true"
									value="#{formBusquedaExpedienteContratacion.numeroJupiter}" />
							</tr:panelLabelAndMessage>
                            <tr:panelLabelAndMessage label="Artículo">
                              <tr:panelHorizontalLayout  halign="left">
                                <tr:inputText columns="10" partialTriggers="seleccion" simple="true"
                                			value="#{selectorMultipleArticulosBean.codigoArticulo}"
                                			valueChangeListener="#{selectorMultipleArticulosBean.cambiaCodigoArticulo}"
                                			immediate="true" id="codigoArticulo"
                                			autoSubmit="true"/>
                                <tr:commandLink id="seleccion"
                                            action="#{selectorMultipleArticulosBean.navega}"
                                            actionListener="#{selectorMultipleArticulosBean.accionOutcome}">
                                  		<f:attribute name="outcomeVuelta" value="volverConsultaExpedientes"/>
                                  <tr:image source="/imagenes/lupa3.gif"/>
                                </tr:commandLink>
                                <tr:inputText partialTriggers="seleccion codigoArticulo" simple="true"
                                            value="#{selectorMultipleArticulosBean.descripcionArticulo}"
                                			disabled="#{selectorMultipleArticulosBean.descripcionArticuloDeshabilitada}"/>
                                <tr:spacer width="10" height="10"/>
                                <tr:commandLink id="agregarASeleccion" partialSubmit="true"
                                                actionListener="#{selectorMultipleArticulosBean.agregaASeleccion}" >
                                  <tr:image source="/imagenes/dcha3.png"
                                                  shortDesc="Agregar a selección"/>
                                </tr:commandLink>

                              <tr:spacer width="10" height="10"/>
                              <tr:inputText partialTriggers="agregarASeleccion limpiaSeleccion" simple="true"
                                value="#{selectorMultipleArticulosBean.descripcionSeleccionCompleta}"
                                disabled="true" rows="3" columns="40"/>
                              <tr:spacer width="10" height="10"/>
                              <tr:commandLink id="limpiaSeleccion" partialSubmit="true"
                                            actionListener="#{selectorMultipleArticulosBean.limpiaSeleccion}"
                          					shortDesc="O MAS">
                                <tr:image source="/imagenes/btnEliminar.gif"
                                            shortDesc="Limpiar selección"/>
                               </tr:commandLink>
                            </tr:panelHorizontalLayout>

              </tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Presupuesto de licitación (IVA incluido) de">
								<tr:panelHorizontalLayout>
									<tr:inputText rows="1" columns="10" simple="true"
										value="#{formBusquedaExpedienteContratacion.presupuestoLicitacionConIVADesde}" />
									<tr:spacer width="10" height="10" />
									<tr:outputText value="a" />
									<tr:spacer width="10" height="10" />
									<tr:inputText columns="10"
										value="#{formBusquedaExpedienteContratacion.presupuestoLicitacionConIVAHasta}" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Presupuesto de licitación (IVA excluido) de">
								<tr:panelHorizontalLayout>
									<tr:inputText rows="1" columns="10" simple="true"
										value="#{formBusquedaExpedienteContratacion.presupuestoLicitacionSinIVADesde}" />
									<tr:spacer width="10" height="10" />
									<tr:outputText value="a" />
									<tr:spacer width="10" height="10" />
									<tr:inputText columns="10"
										value="#{formBusquedaExpedienteContratacion.presupuestoLicitacionSinIVAHasta}" />
								</tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Provincia"
								rendered="#{configuracionConsultaExpedientes.muestraProvincias}">
								<tr:selectOneChoice simple="true"
									value="#{formBusquedaExpedienteContratacion.provincia}"
									unselectedLabel="Todas"
									inlineStyle="width:370px">
									<f:selectItems value="#{gestorListaProvincia.listaElementos}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage
								label="Publicado en diarios oficiales">
								<tr:panelHorizontalLayout>
									<tr:selectBooleanCheckbox text="BOJA"
										value="#{formBusquedaExpedienteContratacion.boja}" simple="true"
                                        autoSubmit="true"
										id="chkBoja" />
									<tr:selectBooleanCheckbox text="BOE"
										value="#{formBusquedaExpedienteContratacion.boe}"
                                        autoSubmit="true"
										id="chkBoe" />
									<tr:selectBooleanCheckbox text="DOCE"
										value="#{formBusquedaExpedienteContratacion.doce}"
                                        autoSubmit="true"
										id="chkDoce" />
          							<tr:spacer width="10" height="10"/>
						            <tr:outputText value="entre fechas"/>
                                    <tr:spacer width="10" height="10"/>
                                    <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaPublicacionDesde}"
                                                 		disabled="#{formBusquedaExpedienteContratacion.fechasPublicacionDeshabilitadas}"
                                                 		partialTriggers="chkBoja chkBoe chkDoce" />
                                    <tr:spacer width="10" height="10"/>
                                    <tr:outputText value="y"/>
                                    <tr:spacer width="10" height="10"/>
                                   <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaPublicacionHasta}"
                                                		disabled="#{formBusquedaExpedienteContratacion.fechasPublicacionDeshabilitadas}"
                                                 		partialTriggers="chkBoja chkBoe chkDoce" />
                                </tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Fecha de intervención entre"
								rendered="#{configuracionConsultaExpedientes.muestraFechasIntervencion}">
								<tr:panelHorizontalLayout>
                                    <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaIntervencionDesde}" />
                                    <tr:spacer width="10" height="10"/>
                                    <tr:outputText value="y"/>
                                    <tr:spacer width="10" height="10"/>
                                   <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaIntervencionHasta}" />
                                </tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
							<tr:panelLabelAndMessage label="Fecha de informe entre"
								rendered="#{configuracionConsultaExpedientes.muestraFechasInformeAJ}">
								<tr:panelHorizontalLayout>
                                    <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaInformeAJDesde}" />
                                    <tr:spacer width="10" height="10"/>
                                    <tr:outputText value="y"/>
                                    <tr:spacer width="10" height="10"/>
                                   <tr:inputDate value="#{formBusquedaExpedienteContratacion.fechaInformeAJHasta}" />
                                </tr:panelHorizontalLayout>
							</tr:panelLabelAndMessage>
						</tr:panelFormLayout>
					</tr:showDetailHeader>
					<h:panelGroup>
						<tr:panelHorizontalLayout halign="right">
							<tr:commandButton text="Buscar"
								action="#{consultaExpedientesContratacion.encontrar}" />
						</tr:panelHorizontalLayout>
					</h:panelGroup>
 				</tr:panelBox>

				<tr:switcher
					facetName="#{tablaExpedienteContratacionBean.facetModo}">
					<!-- Tabla Selección Simple -->
					<f:facet name="simple">
						<tr:table emptyText="No se han encontrado expedientes de contratación"
							value="#{tablaExpedienteContratacionBean.modeloSimple}"
							var="subfila" width="100%"
							rows="10">
							<tr:column headerText="Expediente">
								<tr:outputText value="#{subfila.codigo}" />
							</tr:column>
						  <tr:column  headerText="CCA">
							<tr:iterator rows="25" var="codigoCCA" value="#{subfila.codigosCCA}">
								<tr:panelList>
									<tr:outputText value="#{codigoCCA.label}" />
								</tr:panelList>
							</tr:iterator>
					  	  </tr:column>
							<tr:column  headerText="Objeto">
								<tr:outputText value="#{subfila.objetoContrato}" />
							</tr:column>
							<tr:column  headerText="Tramitación">
								<tr:outputText value="#{subfila.tipoTramitacion}" />
							</tr:column>
							<tr:column
								headerText="Fecha de inicio  de resolución">
								<tr:inputDate value="#{subfila.fechaInicioResolucion}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<f:facet name="actions">
								<h:panelGroup>
									<tr:commandButton text="Imprimir listado" onclick="noBloquearPantalla()"
										actionListener="#{imprimirListadoExpedientesSimple.ejecuta}"
										id="cbListado" >
									</tr:commandButton>

								</h:panelGroup>
							</f:facet>
							<tr:column  headerText="Estado">
								<tr:iterator rows="25" var="estados" value="#{subfila.estados}">
									<tr:panelList>
										<tr:outputText value="#{estados.label}" />
									</tr:panelList>
								</tr:iterator>
							</tr:column>
							<tr:column  headerText="Fecha de estado">

								<tr:iterator rows="25" var="estados"
									value="#{subfila.fechaEstado}">
									<tr:panelList>
										<tr:outputText value="#{estados.label}" />
									</tr:panelList>
								</tr:iterator>

							</tr:column>


							<tr:column  headerText="Fecha de inicio de plazo"
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:inputDate value="#{subfila.fechaInicioPlazo}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<tr:column  headerText="Plazo"
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:outputText value="#{subfila.plazo}" />
							</tr:column>
							<tr:column  headerText="Fecha fin "
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:inputDate value="#{subfila.fechaFinPlazo}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<tr:column  headerText="Días restantes"
								rendered="#{configuracionConsultaExpedientes.muestraDiasRestantes}">
								<tr:outputText value="#{subfila.plazoRestante}" />
							</tr:column>
							<tr:column  headerText="Ampliación solicitada"
								rendered="#{configuracionConsultaExpedientes.muestraDatosAmpliacionPlazo}" />
									<tr:column  headerText="Acciones"   >

										<tr:iterator rows="25" var="acciones" value="#{subfila.acciones}">
												<tr:commandLink action="#{ejecutorAccionPorNombre.ejecuta}"
																onclick="#{acciones.confirmacionAccion}">
													<tr:setActionListener to="#{tablaExpedienteContratacionBean.seleccionado}" from="#{subfila}"/>
													<tr:setActionListener to="#{ejecutorAccionPorNombre.nombreAccion}" from="#{acciones.nombreComponente}"/>
													<tr:outputText value="#{acciones.descripcion}" />
											</tr:commandLink>
										</tr:iterator>

										<tr:commandLink action="#{gestionProrrogasExpediente.ejecuta}"
												 rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}" >
												<tr:setActionListener to="#{tablaExpedienteContratacionBean.seleccionado}" from="#{subfila}"/>
												<tr:outputText value="[Prorrogas]" />
										</tr:commandLink>


									</tr:column>
									
									<tr:column  headerText="Correcciones y modificaciones" 
										 rendered="#{configuracionConsultaExpedientes.muestraCorreccionesYModificaciones}"  >

										<tr:commandLink action="#{corregirDatosAdjudicacionExpedienteAccionTabla.ejecuta}" useWindow="true"
												windowHeight="600" windowWidth="800"  >
												<tr:setActionListener to="#{tablaExpedienteContratacionBean.seleccionado}" from="#{subfila}"/>
												<tr:setActionListener to="#{corregirDatosAdjudicacionExpedienteAccionTabla.expedienteVista}" from="#{subfila}"/>
												<tr:outputText value="[Cambiar_importes_adjudicación]" />
										</tr:commandLink>


									</tr:column>
 							<f:facet name="footer">
				              		<tr:outputText value="Número de resultados encontrados  #{tablaExpedienteContratacionBean.modeloSimple.rowCount}" />
				              </f:facet>

						</tr:table>
					</f:facet>
					<!-- Tabla Selección Múltiple -->
					<f:facet name="multiple">
						<tr:table
							emptyText="No se han encontrado expedientes de contratación"
							value="#{tablaExpedienteContratacionBean.modeloSimple}"
							selectionListener="#{tablaExpedienteContratacionBean.eventoSeleccion}"
							selectedRowKeys="#{tablaExpedienteContratacionBean.filasSeleccionadas}"
							var="subfila" width="100%" autoSubmit="true" rowSelection="multiple"
							rows="10">
							<tr:column  headerText="Expediente">
								<tr:outputText value="#{subfila.codigo}" />
							</tr:column>
						  <tr:column  headerText="CCA">
							<tr:iterator rows="25" var="codigoCCA" value="#{adjudicacion.codigosCCA}">
								<tr:panelList>
									<tr:outputText value="#{codigoCCA.label}" />
								</tr:panelList>
							</tr:iterator>
					  	  </tr:column>
							<tr:column  headerText="Objeto">
								<tr:outputText value="#{subfila.objetoContrato}" />
							</tr:column>
							<tr:column  headerText="Tramitación">
								<tr:outputText value="#{subfila.tipoTramitacion}" />
							</tr:column>
							<tr:column
								headerText="Fecha de inicio  de resolución">
								<tr:inputDate value="#{subfila.fechaInicioResolucion}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<f:facet name="actions">
								<h:panelGroup>
									<tr:commandButton text="Devolver expedientes"
										action="#{tablaExpedienteContratacionBean.devuelveIdsExpedientesSeleccionados}"
										rendered="#{configuracionConsultaExpedientes.muestraDevolverExpedientes}" />
								</h:panelGroup>
							</f:facet>
							<tr:column  headerText="Estado">
								<tr:iterator rows="25" var="estado" value="#{subfila.estados}">
									<tr:panelList>
										<tr:outputText value="#{estado.label}" />
									</tr:panelList>
								</tr:iterator>
							</tr:column>
							<tr:column  headerText="Fecha de estado">

								<tr:iterator rows="25" var="fecha"
									value="#{subfila.fechaEstado}">
									<tr:panelList>
										<tr:outputText value="#{fecha.label}" />
									</tr:panelList>
								</tr:iterator>

							</tr:column>
							<tr:column  headerText="Fecha de inicio de plazo"
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:inputDate value="#{subfila.fechaInicioPlazo}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<tr:column  headerText="Plazo"
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:outputText value="#{subfila.plazo}" />
							</tr:column>
							<tr:column  headerText="Fecha fin "
								rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
								<tr:inputDate value="#{subfila.fechaFinPlazo}"
									readOnly="true" inlineStyle="border-style:none" />
							</tr:column>
							<tr:column  headerText="Días restantes"
								rendered="#{configuracionConsultaExpedientes.muestraDiasRestantes}">
								<tr:outputText value="#{subfila.plazoRestante}" />
							</tr:column>
							<tr:column  headerText="Ampliación solicitada"
								rendered="#{configuracionConsultaExpedientes.muestraDatosAmpliacionPlazo}" />

						</tr:table>
					</f:facet>
					<!-- Tabla Agrupada -->
					<f:facet name="agrupada">
						<tr:table
							emptyText="No se han encontrado expedientes de contratación"
							var="fila" partialTriggers="tabla" id="tabla"
							value="#{tablaExpedienteContratacionBean.modeloAgrupado}"
							allDetailsEnabled="true" rows="10" width="100%">
							<tr:column  headerText="Órgano gestor">
								<tr:outputText value="#{fila.cabecera}" />
							</tr:column>
							<f:facet name="actions">
								<h:panelGroup>
									<tr:commandButton text="Imprimir listado" onclick="noBloquearPantalla()"
										actionListener="#{imprimirListadoExpedientesAgrupado.ejecuta}"
									  	id="cbListado">
										</tr:commandButton>


								</h:panelGroup>
							</f:facet>
							<f:facet name="detailStamp">
								<tr:panelHorizontalLayout>
									<tr:spacer width="10" height="10" />
									<tr:table emptyText="No items were found"
										partialTriggers="tabla" varStatus=""
										value="#{fila.modelo}" var="subfila" width="100%">
										<tr:column  headerText="Expediente">
											<tr:outputText value="#{subfila.codigo}" />
										</tr:column>
										<tr:column  headerText="Objeto">
											<tr:outputText value="#{subfila.objetoContrato}" />
										</tr:column>
										<tr:column  headerText="Tramitación">
											<tr:outputText value="#{subfila.tipoTramitacion}" />
										</tr:column>
										<tr:column
											headerText="Fecha de inicio de resolución">
											<tr:inputDate value="#{subfila.fechaInicioResolucion}"
												readOnly="true" inlineStyle="border-style:none" />
										</tr:column>
										<tr:column  headerText="Estado">
											<tr:iterator rows="25" var="estado"
												value="#{subfila.estados}">
												<tr:panelList>
													<tr:outputText value="#{estado.label}" />
												</tr:panelList>
											</tr:iterator>
										</tr:column>
										<tr:column  headerText="Fecha de estado">

											<tr:iterator rows="25" var="fecha"
												value="#{subfila.fechaEstado}">
												<tr:panelList>
													<tr:outputText value="#{fecha.label}" />
												</tr:panelList>
											</tr:iterator>

										</tr:column>
										<tr:column
											headerText="Fecha de inicio de plazo"
											rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
											<tr:inputDate value="#{subfila.fechaInicioPlazo}"
												readOnly="true" inlineStyle="border-style:none" />
										</tr:column>
										<tr:column  headerText="Plazo"
											rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
											<tr:outputText value="#{subfila.plazo}" />
										</tr:column>
										<tr:column  headerText="Fecha fin "
											rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}">
											<tr:inputDate value="#{subfila.fechaFinPlazo}"
												readOnly="true" inlineStyle="border-style:none" />
										</tr:column>
										<tr:column  headerText="Dias restantes"
											rendered="#{configuracionConsultaExpedientes.muestraDiasRestantes}">
											<tr:outputText value="#{subfila.plazoRestante}" />
										</tr:column>
										<tr:column  headerText="Ampliación solicitada"
											rendered="#{configuracionConsultaExpedientes.muestraDatosAmpliacionPlazo}" />

										<tr:column  headerText="Acciones" >

											<tr:commandLink action="#{verExpediente.ejecuta}"  >
												<tr:setActionListener to="#{tablaExpedienteContratacionBean.seleccionado}" from="#{subfila}"/>
													<tr:outputText value="[Ver]" />
											</tr:commandLink>
											<tr:commandLink action="#{gestionProrrogasExpediente.ejecuta}" rendered="#{configuracionConsultaExpedientes.muestraDatosPlazo}" >
												<tr:setActionListener to="#{tablaExpedienteContratacionBean.seleccionado}" from="#{subfila}"/>
													<tr:outputText value="[Prorrogas]" />
											</tr:commandLink>


										</tr:column>
									</tr:table>
								</tr:panelHorizontalLayout>
							</f:facet>
						</tr:table>
					</f:facet>
				</tr:switcher>
				<tr:separator />
				<tr:panelHorizontalLayout halign="center">
					<tr:commandButton text="Menú principal"
						action="#{volverMenuPrincipalBean.ejecuta}"
						rendered="#{configuracionConsultaExpedientes.muestraMenuPrincipal}" />
				</tr:panelHorizontalLayout>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
