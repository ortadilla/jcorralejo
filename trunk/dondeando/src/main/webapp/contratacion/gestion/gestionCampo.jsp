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
		<trh:head title="Gestión de campos">
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
				<tr:panelHeader text="Gestión de campos" />
				<tr:messages></tr:messages>
				<tr:spacer width="10" height="10" />
				<tr:panelBox background="medium" partialTriggers="panelTab tab1 tab2 tipoDato tipoControl" >
				<tr:panelTabbed id="panelTab"
					partialTriggers="tab1 tab2 tipoDato tipoControl"
					>
					<tr:showDetailItem text="Datos generales" id="tab1">
						<tr:panelBox background="medium">
							<tr:spacer width="800" height="2"></tr:spacer>
							<tr:panelFormLayout>
								<f:facet name="footer"></f:facet>
								<tr:panelLabelAndMessage label="Descripción">
									<tr:inputText value="#{gestionCampoBean.descripcion}"
										simple="true" maximumLength="250" columns="100" rows="2" />
								</tr:panelLabelAndMessage>

								<tr:spacer height="5" />
								<tr:panelLabelAndMessage label="Tipo de dato">
									<tr:selectOneChoice value="#{gestionCampoBean.idTipoDato}"
										simple="true" id="tipoDato" autoSubmit="true"
										valueChangeListener="#{gestionCampoBean.cambiaTipoDato}">
										<f:selectItems value="#{gestorListaTipoDato.listaElementos}" />
									</tr:selectOneChoice>
								</tr:panelLabelAndMessage>
								<tr:panelLabelAndMessage label="">
									<tr:inputText columns="100" simple="true"
										partialTriggers="tipoDato" disabled="true"
										valueChangeListener="#{gestionCampoBean.cambiaTipoDato}"
										value="#{gestionCampoBean.ayudaTipoDato}" rendered="true" />
								</tr:panelLabelAndMessage>
								<tr:spacer height="5" />
								<tr:panelLabelAndMessage label="Tipo de control">
									<tr:selectOneChoice value="#{gestionCampoBean.idTipoControl}"
										simple="true" id="tipoControl" autoSubmit="true"
										partialTriggers="tipoDato"
										valueChangeListener="#{gestionCampoBean.cambiaTipoControl}">
										<f:selectItems value="#{gestionCampoBean.controlesPermitidos}" />
									</tr:selectOneChoice>
								</tr:panelLabelAndMessage>
								<tr:panelLabelAndMessage label="">
									<tr:inputText columns="100" partialTriggers="tipoControl"
										disabled="true" simple="true"
										value="#{gestionCampoBean.ayudaTipoControl}" />
								</tr:panelLabelAndMessage>
								<tr:spacer height="5" />
								<tr:panelLabelAndMessage label="Utilización" rendered="true">
									<tr:inputText columns="100" partialTriggers="tipoControl"
										disabled="true" simple="true" rows="2"
										valueChangeListener="#{gestionCampoBean.cambiaTipoControl}"
										value="#{gestionCampoBean.ayudaConfiguracion}" rendered="true" />
								</tr:panelLabelAndMessage>
								<tr:spacer width="10" height="10" />
								<tr:panelFormLayout partialTriggers="tipoControl">
									<tr:spacer height="10" />
									<f:facet name="footer" />
									<tr:panelHorizontalLayout halign="left">
										<tr:switcher facetName="#{gestionCampoBean.swConfiguracion}">
											<f:facet name="boolean">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Configuración de campo booleano"
														background="medium">
														<tr:panelLabelAndMessage label="Valor por defecto">
															<tr:selectOneRadio
																value="#{gestionCampoBean.valorDefecto}" simple="true"
																layout="horizontal">
																<tr:selectItem label="Inicial" value="" />
																<tr:selectItem label="Cierto" value="T" />
																<tr:selectItem label="Falso" value="F" />
															</tr:selectOneRadio>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="tabla">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Lista de columnas" background="medium">
														<tr:panelLabelAndMessage label="">
															<tr:table emptyText="No hay columnas" rows="6" var="fila"
																rowSelection="single"
																value="#{gestionCampoBean.modeloColumnas}">
																<tr:column  headerText="clave">
																	<tr:inputText value="#{fila.value}" />
																</tr:column>
																<tr:column  headerText="descripción">
																	<tr:inputText value="#{fila.label}" />
																</tr:column>
																<f:facet name="actions">
																	<tr:panelButtonBar>
																		<tr:commandButton text="Agregar"
																			action="#{gestionCampoBean.agregarFilaColumnas}" />
																		<tr:commandButton text="Borrar"
																			actionListener="#{botonBorraSelecionadoTabla.borraSeleccionado}"
																			onclick="if(!confirm('¿Desea borrar la columna?')) return false" />
																	</tr:panelButtonBar>
																</f:facet>
															</tr:table>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="lista">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Lista de opciones posibles"
														background="medium">
														<tr:panelLabelAndMessage label="Elementos">
															<tr:table emptyText="No hay elementos" rows="6" varStatus="estatus"
																var="fila" rowSelection="single" autoSubmit="true"
																value="#{gestionCampoBean.modeloSeleccion}">
																<tr:column  headerText="clave">
																	<tr:inputText value="#{fila.value}" />
																</tr:column>
																<tr:column  headerText="descripción">
																	<tr:inputText value="#{fila.label}" />
																</tr:column>
																<f:facet name="actions">
																	<tr:panelButtonBar>
																		<tr:commandButton text="Agregar"
																			action="#{gestionCampoBean.agregarFilaSeleccion}" />
																		<tr:commandButton text="Borrar"
																			actionListener="#{botonBorraSelecionadoTabla.borraSeleccionado}"
																			onclick="if(!confirm('¿Desea borrar el elemento?')) return false" >
																			</tr:commandButton>

																	</tr:panelButtonBar>
																</f:facet>
															</tr:table>
														</tr:panelLabelAndMessage>
														<tr:panelLabelAndMessage label="Clave por defecto">
															<tr:inputText value="#{gestionCampoBean.valorDefecto}"
																simple="true" />
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="fijo">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Configuración de campo de texto fijo"
														background="medium">
														<tr:panelLabelAndMessage label="Texto fijo">
															<tr:inputText value="#{gestionCampoBean.textoFijo}"
																simple="true" rows="3" />
														</tr:panelLabelAndMessage>
														<tr:panelLabelAndMessage label="Longitud máxima">
															<tr:inputText value="#{gestionCampoBean.longitudMaxima}"
																simple="true" rows="1" columns="5">
																<f:converter converterId="javax.faces.Integer" />
															</tr:inputText>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="input">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Configuración de caja de texto"
														background="medium">
														<tr:panelLabelAndMessage label="Valor por defecto">
															<tr:inputText value="#{gestionCampoBean.valorDefecto}"
																simple="true" autoSubmit="true"
																partialTriggers="valorDefectoInput"
																valueChangeListener="#{gestionCampoBean.eventoCambioValorPorDefectoCajaTexto}"
																rows="3" id="valorDefectoInput" />
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="fecha">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Configuración de fecha"
														background="medium">
														<tr:panelLabelAndMessage label="Fecha por defecto">
															<tr:inputDate simple="true"
																value="#{gestionCampoBean.valorDefecto}" />
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="campoEntidad">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox
														text="Configuración de propiedad del expediente"
														background="medium">
														<tr:panelLabelAndMessage label="Propiedad">
															<tr:selectOneChoice simple="true"
																value="#{gestionCampoBean.valorDefecto}">
																<f:selectItems
																	value="#{gestorListaPropiedadesExpediente.listaElementos}" />
															</tr:selectOneChoice>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="gestorLista">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox
														text="Configuración de referencia (entidad referenciada)"
														background="medium">
														<tr:selectOneChoice simple="true"
															value="#{gestionCampoBean.valorDefecto}">
															<f:selectItems
																value="#{manejadorConfiguracionCampoEntidadGestorLista.listaElementos}" />
														</tr:selectOneChoice>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="clasificacion">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox
														text="Configuración clasificación del artículo"
														background="medium">
														<tr:panelLabelAndMessage label="Clasificación">
															<tr:selectOneChoice simple="true"
																value="#{gestionCampoBean.idClasificacion}">
																<f:selectItems
																	value="#{gestorListaClasificacionSeleccionable.listaElementos}" />
															</tr:selectOneChoice>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
											<f:facet name="garantia">
												<tr:panelFormLayout>
													<f:facet name="footer" />
													<tr:panelBox text="Configuracion de la garantía"
														background="medium">
														<tr:panelLabelAndMessage label="Porcentaje">
															<tr:inputText simple="true"
																value="#{gestionCampoBean.porcentaje}">
															</tr:inputText>
														</tr:panelLabelAndMessage>
													</tr:panelBox>
												</tr:panelFormLayout>
											</f:facet>
										</tr:switcher>
									</tr:panelHorizontalLayout>
								</tr:panelFormLayout>
								<tr:panelLabelAndMessage label="Código">
									<tr:inputText value="#{gestionCampoBean.codigo}" simple="true"
										maximumLength="20" />
								</tr:panelLabelAndMessage>
							</tr:panelFormLayout>
						</tr:panelBox>

					</tr:showDetailItem>
					<tr:showDetailItem text="Dependencia y visualizacion" id="tab2">
						<tr:panelBox background="medium"
							text="Dependiencia entre campos">

							<tr:panelLabelAndMessage partialTriggers="dependiente">

										<tr:selectOneChoice
											value="#{gestionCampoBean.idCampoDependiente}" simple="true"
											autoSubmit="true" unselectedLabel="Sin dependencia"
											id="dependiente" >
											<f:selectItems
												value="#{gestionEstructuraVersionModeloPliegoBean.listaCamposDependiblesVersionModeloPliego}" />
										</tr:selectOneChoice>

										<tr:spacer width="10" height="1"></tr:spacer>
										<tr:switcher
											facetName="#{gestionCampoBean.campoDependiente?'seleccionValor':'nada'}">
											<f:facet name="seleccionValor">
													<tr:selectOneChoice
														value="#{gestionCampoBean.valorDepende}"
														label="Mostrar cuando el valor sea:" >
														<f:selectItems
															value="#{gestionCampoBean.listaValoresDependientes}" />
													</tr:selectOneChoice>
											</f:facet>
										</tr:switcher>

								</tr:panelLabelAndMessage>
								<tr:spacer width="800" height="10"></tr:spacer>
							</tr:panelBox>

								<tr:panelBox background="medium" text="Reglas sobre el expediente">
								<tr:spacer width="800" height="5"></tr:spacer>
									<tr:panelLabelAndMessage label="Cantidad o precio unitario" >
									 <tr:selectOneRadio unselectedLabel="No aplicar esta regla"
									 	value="#{gestionCampoBean.reglaCantidadOPrecio}" >
									 	<f:selectItem itemValue="C" itemLabel="El campo solo aparecera en expedientes por cantidad"/>
									 	<f:selectItem itemValue="P" itemLabel="El campo solo aparecera en expedientes por precio"/>
									 </tr:selectOneRadio>
								</tr:panelLabelAndMessage>

								<tr:spacer width="800" height="5"></tr:spacer>
								<tr:separator></tr:separator>
								<tr:spacer width="800" height="5"></tr:spacer>
						</tr:panelBox>
					</tr:showDetailItem>
				</tr:panelTabbed>
				</tr:panelBox>


				<tr:separator />
				<tr:panelHorizontalLayout halign="center">
					<tr:commandButton text="Aceptar"
						action="#{gestionCampoBean.actualizarYSalir}" />
					<tr:commandButton text="Cancelar"
						action="#{gestionCampoBean.cancelar}" immediate="true" />
				</tr:panelHorizontalLayout>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
