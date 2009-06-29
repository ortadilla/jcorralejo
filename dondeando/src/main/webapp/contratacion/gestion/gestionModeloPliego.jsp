<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:tr="http://myfaces.apache.org/trinidad">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=iso-8859-15" />
	<f:view>
		<trh:html>
		<trh:head title="Gestión modelo de pliego">
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
				<tr:panelHeader text="Gestión modelo de pliego">
					<tr:messages />
					<tr:panelBox background="medium" partialTriggers="socEspecifico">
						<tr:panelGroupLayout >
							<f:facet name="footer" />
							<tr:panelLabelAndMessage label="Descripción"
								labelStyle="width: 150px;">
								<tr:inputText value="#{gestionModeloPliegoBean.descripcion}"
									simple="true"
									disabled="#{gestionModeloPliegoBean.modoEdicion.ver}"
									contentStyle="width:595px" />
							</tr:panelLabelAndMessage>

							<tr:panelLabelAndMessage label="Con publicidad"
								labelStyle="width: 150px;">
								<tr:selectBooleanCheckbox id="socConPublicidad" simple="true"
									value="#{gestionModeloPliegoBean.conPublicidad}"
									disabled="#{gestionModeloPliegoBean.modoEdicion.ver}" />
							</tr:panelLabelAndMessage>

							<tr:panelLabelAndMessage label="Licitación"
								labelStyle="width: 150px;">

								 <tr:selectOneRadio value="#{gestionModeloPliegoBean.porCantidad}"
                      					 	id="porCantiadad" layout="horizontal"
                                         	simple="true"
                                         	disabled="#{gestionModeloPliegoBean.modoEdicion.ver}"
                                         >
                        				<tr:selectItem label="por cantidad" value="#{true}"/>
                        				<tr:selectItem label="por precio unitario" value="#{false}"/>
                      			</tr:selectOneRadio>


							</tr:panelLabelAndMessage>


							<tr:panelLabelAndMessage label="Específico"
								labelStyle="width: 150px;">
								<tr:selectBooleanCheckbox id="socEspecifico" simple="true"
									autoSubmit="true" value="#{gestionModeloPliegoBean.especifico}"
									disabled="#{gestionModeloPliegoBean.modoEdicion.ver}" />
							</tr:panelLabelAndMessage>
						</tr:panelGroupLayout>
						<tr:switcher facetName="#{!gestionModeloPliegoBean.especifico?'panelParaMostrarLosCampos':'nada'}">
							<f:facet name="panelParaMostrarLosCampos">
								<tr:panelGroupLayout>
									<tr:panelLabelAndMessage label="Procedimiento"
										partialTriggers="socEspecifico" labelStyle="width: 150px;">
										<tr:selectOneChoice id="socProcedimientoContratacion"
											simple="true"
											value="#{gestionModeloPliegoBean.idProcedimientoContratacion}"
											disabled="#{gestionModeloPliegoBean.modoEdicion.ver}"
											contentStyle="width:600px">
											<f:selectItems
												value="#{gestorListaProcedimientoContratacion.listaElementos}" />
										</tr:selectOneChoice>
									</tr:panelLabelAndMessage>
									<tr:panelLabelAndMessage label="Tipo de contrato"
										partialTriggers="socEspecifico" labelStyle="width: 150px;">
										<tr:selectOneChoice id="socTipoContrato" autoSubmit="true"
											simple="true"
											valueChangeListener="#{gestionModeloPliegoBean.eventoCambioTipoContrato}"
											value="#{gestionModeloPliegoBean.idTipoContrato}"
											contentStyle="width:600px"
											disabled="#{gestionModeloPliegoBean.modoEdicion.ver}">
											<f:selectItems
												value="#{gestorListaTipoContrato.listaElementos}" />
										</tr:selectOneChoice>
									</tr:panelLabelAndMessage>
									
							<tr:panelLabelAndMessage label="Adjudicado en CM"
								labelStyle="width: 150px;">
								<tr:selectBooleanCheckbox id="socProductoHomologado" simple="true"
									value="#{gestionModeloPliegoBean.productoHomologado}"
									disabled="#{gestionModeloPliegoBean.modoEdicion.ver}" />
							</tr:panelLabelAndMessage>
									
									
									<tr:spacer height="10"></tr:spacer>
									<tr:table var="linea" id="tabla"
										value="#{gestionModeloPliegoBean.nivelesValor}" width="100%"
										partialTriggers="tabla:seleccion socEspecifico"
										emptyText="No hay niveles asociados">
										<tr:column headerText="Clasificación de artículo">
											<tr:outputText value="#{linea.label}"></tr:outputText>
											<tr:outputText />
										</tr:column>
										<tr:column>
											<tr:commandLink id="limpiaSeleccion"
												action="#{gestionModeloPliegoBean.limpiaNivelValor}"
												shortDesc="Limpiar">
												<tr:image source="/imagenes/btnEliminar.gif"
													shortDesc="Limpiar" />
												<tr:setActionListener from="#{linea}"
													to="#{gestionModeloPliegoBean.nivelValorABorrar}" />
											</tr:commandLink>

										</tr:column>
										<f:facet name="actions">
											<tr:commandButton id="seleccion" partialSubmit="true"
												text="Agregar"
												action="#{dialogoSeleccionNivelValor.ejecuta}"
												actionListener="#{dialogoSeleccionNivelValor.configuraComponente}"
												returnListener="#{dialogoSeleccionNivelValor.ejecutaRetorno}"
												windowWidth="800" windowHeight="600">

											</tr:commandButton>

										</f:facet>
									</tr:table>

								</tr:panelGroupLayout>
							</f:facet>
						</tr:switcher>

						<tr:spacer width="10" height="10" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="Aceptar"
								action="#{gestionModeloPliegoBean.actualizarYSalir}"
								rendered="#{!gestionModeloPliegoBean.modoEdicion.ver}" />
							<tr:commandButton text="Cancelar"
								action="#{gestionModeloPliegoBean.cancelar}" />
						</tr:panelHorizontalLayout>

					</tr:panelBox>
				</tr:panelHeader>
			</h:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
