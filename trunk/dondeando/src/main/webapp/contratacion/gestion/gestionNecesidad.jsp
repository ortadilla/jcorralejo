<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:ctr="http://contratacion/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Gestión de necesidades">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>
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

        <h:form onsubmit="bloquearPantalla(this)" id="formGestionNecesidades">
          <tr:panelHeader text="Gestión de necesidades"/>
          <tr:messages/>
          <tr:panelBox background="medium" inlineStyle="width: 100%;"
                       id="bloqueDatosNecesidad" partialTriggers="codigoArticulo porCantiadad cantidad precioUnitiario ivaPrecio importeArrendamiento ivaarrendamiento importeMantenimiento ivamantenimiento importeOpcionCompra ivaOpcionCompra presupuestoMaximo urgente" >

	    	<tr:panelFormLayout>
              <tr:panelGroupLayout>

              <ctr:selectorArticulo labelStyle="width: 175px;"
	               disabled="#{!configuracionGestionNecesidadesContratacion.campoArticuloEditable}"
	               idCampoCodigo="codigoArticulo"
	               articulo="#{gestionNecesidadesBean.articuloSeleccionado}"
	               cambiaArticuloListener="#{gestionNecesidadesBean.cambiaArticuloSeleccionado}"
	               outcomeVuelta="volverGestionNecesidad" mantenerBusqueda="true">
	          </ctr:selectorArticulo>



              </tr:panelGroupLayout>
            </tr:panelFormLayout>
            <tr:separator/>

           <tr:panelFormLayout partialTriggers="codigoArticulo">
              <tr:switcher facetName="#{gestionDatosEconomicosNecesidadBean.necesidadAOCO?'configuracionCampos2':'configuracionCampos1'}">
                  <f:facet name="configuracionCampos1">
                  <tr:group>
                    <tr:panelLabelAndMessage label="Licitación"
                                             labelStyle="width: 175px;">
                      <tr:panelHorizontalLayout>
                      <tr:selectOneRadio value="#{gestionDatosEconomicosNecesidadBean.tipoLicitacion}" autoSubmit="true"
                      					 id="porCantiadad" layout="horizontal"
                                         simple="true" partialTriggers="presupuestoMaximo codigoArticulo"
                                         binding="#{gestionDatosEconomicosNecesidadBinding.sorTipoLicitacion}"
                                         disabled="#{!configuracionGestionNecesidadesContratacion.campoTipoLicitacionEditable}">
                        <tr:selectItem label="por cantidad" value="C"/>
                        <tr:selectItem label="por precio unitario" value="P"/>
                      </tr:selectOneRadio>
                      <tr:spacer height="1" width="60"></tr:spacer>
                      	<tr:inputDate id="indFecRegis" columns="20" simple="false" label="Fecha registro"
                                value="#{gestionNecesidadesBean.fechaRegistro}"
                                disabled="true"/>
                      </tr:panelHorizontalLayout>
                    </tr:panelLabelAndMessage>
             	 </tr:group>
             	</f:facet>
             </tr:switcher>
            </tr:panelFormLayout>
            <tr:panelFormLayout partialTriggers="codigoArticulo">
              <tr:panelGroupLayout>
              <tr:panelHorizontalLayout>
                	<tr:panelLabelAndMessage label="Cantidad"
                                             labelStyle="width: 175px;">
                      <tr:inputText columns="20"
                                    value="#{gestionDatosEconomicosNecesidadBean.cantidad}"
                                    binding="#{gestionDatosEconomicosNecesidadBinding.citCantidad}"
                                    simple="true" autoSubmit="true"
                                    valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambioValorCantidad}"
                                    id="cantidad"
                                    partialTriggers="presupuestoMaximo codigoArticulo porCantiadad"
                                    disabled="#{!gestionDatosEconomicosNecesidadBean.porCantidad  || !configuracionGestionNecesidadesContratacion.campoCantidadEditable}">
						<ctr:conversorBigDecimal numeroDecimales="6"/>
                      </tr:inputText>

                      </tr:panelLabelAndMessage>
                    </tr:panelHorizontalLayout>

              </tr:panelGroupLayout>
            </tr:panelFormLayout>


              <tr:panelFormLayout partialTriggers="codigoArticulo">
              <tr:switcher facetName="#{gestionDatosEconomicosNecesidadBean.necesidadAOCO?'configuracionCampos2':'configuracionCampos1'}">
                  <f:facet name="configuracionCampos1">
                  <tr:group>

                    <tr:panelHorizontalLayout>
                    <tr:panelLabelAndMessage label="Precio Unitario"
                                             labelStyle="width: 175px;">
                      <tr:inputText columns="20" simple="true"
                                    value="#{gestionDatosEconomicosNecesidadBean.precioUnitarioSinIVA}"
                                    binding="#{gestionDatosEconomicosNecesidadBinding.citPrecio}"
                                    autoSubmit="true"
                                    valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambiaValorPrecioUnitario}"
                                    id="precioUnitiario"
                                    partialTriggers="presupuestoMaximo codigoArticulo porCantiadad"
                                    disabled="#{gestionDatosEconomicosNecesidadBean.porCantidad || !configuracionGestionNecesidadesContratacion.campoPrecioUnitarioEditable}">
						<ctr:conversorBigDecimal numeroDecimales="6"/>
                      </tr:inputText>
                     </tr:panelLabelAndMessage>

                      <tr:outputText value="€ (IVA excluido)"></tr:outputText>
                     <tr:spacer width="30" height="1" ></tr:spacer>

                      <tr:selectOneChoice id="ivaPrecio" autoSubmit="true" simple="true"
                      				      label="IVA:" unselectedLabel=" "
                      				      disabled="#{gestionNecesidadesBean.tipoIVAestablecido || !configuracionGestionNecesidadesContratacion.campoPrecioUnitarioEditable}"
                      					  binding="#{gestionDatosEconomicosNecesidadBinding.csoPrecioPorcentajeIVA}"
                                    	  value="#{gestionDatosEconomicosNecesidadBean.precioUnitarioPorcentajeIVA}" >
                      			<f:selectItems
                      					value="#{gestorListaTiposIVAArticulo.listaElementos}"  >

                      	</f:selectItems>

                      </tr:selectOneChoice>
                      <tr:spacer width="10" height="1" ></tr:spacer>
                     <tr:outputText value="Total: "></tr:outputText>
                     <tr:spacer width="5" height="1" ></tr:spacer>
                     <tr:outputText id="outPreCIVA" partialTriggers="ivaPrecio precioUnitiario"
                     	 value="#{gestionDatosEconomicosNecesidadBean.precioUnitarioConIVA}">
                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/>
                     </tr:outputText>
                    </tr:panelHorizontalLayout>

                  </tr:group>
                </f:facet>
                  <f:facet name="configuracionCampos2">
                  <tr:group>

                   <tr:panelHorizontalLayout>

                    <tr:panelLabelAndMessage label="Importe arrendamiento"
                                             labelStyle="width: 175px;">
                      <tr:inputText columns="20" simple="true"
                                    value="#{gestionDatosEconomicosNecesidadBean.importeArrendamientoSinIVA}"
                                    binding="#{gestionDatosEconomicosNecesidadBinding.citImporteArrendamiento}"
                                    valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambiaValorImporte}"
                                    autoSubmit="true" id="importeArrendamiento"
                                    partialTriggers="importeArrendamiento"
                                    disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteArrendamientoEditable}">
                        <ctr:conversorBigDecimal numeroDecimales="2"/>
                      </tr:inputText>
                    </tr:panelLabelAndMessage>
                     <tr:outputText value="€ (IVA excluido)"></tr:outputText>
                     <tr:spacer width="30" height="1" ></tr:spacer>
                      <tr:selectOneChoice id="ivaarrendamiento" autoSubmit="true" label="IVA:"
                      					  unselectedLabel=" " disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteArrendamientoEditable}"
                      					  binding="#{gestionDatosEconomicosNecesidadBinding.csoImporteArrendamientoPorcentajeIVA}"
                      					  value="#{gestionDatosEconomicosNecesidadBean.importeArrendamientoPorcentajeIVA}" >
                      	<f:selectItems    value="#{gestorListaTiposIVA.listaElementos}"  >
                      	</f:selectItems>
                      </tr:selectOneChoice>
                      <tr:spacer width="30"  height="1"></tr:spacer>
                     <tr:outputText value="Total: "></tr:outputText>
                     <tr:spacer width="5" height="1"></tr:spacer>
                     <tr:outputText id="outIArrCIVA" partialTriggers="ivaarrendamiento importeArrendamiento"
                     	 value="#{gestionDatosEconomicosNecesidadBean.importeArrendamientoConIVA}">
                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
                     </tr:outputText>
                     </tr:panelHorizontalLayout>
                    <tr:panelHorizontalLayout>


                    <tr:panelLabelAndMessage label="Importe mantenimiento"
                                             labelStyle="width: 175px;">
                      <tr:inputText columns="20" simple="true"
                                    value="#{gestionDatosEconomicosNecesidadBean.importeMantenimientoSinIVA}"
                                    binding="#{gestionDatosEconomicosNecesidadBinding.citImporteMantenimiento}"
                                    valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambiaValorImporte}"
                                    id="importeMantenimiento" autoSubmit="true"
                                    partialTriggers="codigoArticulo"
                                    disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteMantenimientoEditable}">
                        <ctr:conversorBigDecimal numeroDecimales="2"/>
                      </tr:inputText>

                    </tr:panelLabelAndMessage>
	                    <tr:outputText value="€ (IVA excluido)"></tr:outputText>
	                     <tr:spacer width="30" height="1" ></tr:spacer>
	                      <tr:selectOneChoice id="ivamantenimiento" unselectedLabel=" "
	                      					  autoSubmit="true" label="IVA:" disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteMantenimientoEditable}"
	                      					  binding="#{gestionDatosEconomicosNecesidadBinding.csoImporteMantenimientoPorcentajeIVA}"
	                      					  value="#{gestionDatosEconomicosNecesidadBean.importeMantenimientoPorcentajeIVA}" >
	                      	<f:selectItems    value="#{gestorListaTiposIVA.listaElementos}"  >
	                      	</f:selectItems>
	                      </tr:selectOneChoice>
	                      <tr:spacer width="30" height="1" ></tr:spacer>
	                     <tr:outputText value="Total: "></tr:outputText>
	                     <tr:spacer width="5"  height="1"></tr:spacer>
	                     <tr:outputText id="outIMANCIVA" partialTriggers="ivamantenimiento importeMantenimiento"
	                     	 value="#{gestionDatosEconomicosNecesidadBean.importeMantenimientoConIVA}">
	                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
	                     </tr:outputText>

                     </tr:panelHorizontalLayout>
                    <tr:panelHorizontalLayout>
                    <tr:panelLabelAndMessage label="Importe opción compra"
                                             labelStyle="width: 175px;">
                      <tr:inputText columns="20"
                                    value="#{gestionDatosEconomicosNecesidadBean.importeOpcionCompraSinIVA}"
                                    autoSubmit="true" simple="true"
                                    binding="#{gestionDatosEconomicosNecesidadBinding.citImporteOpcionCompra}"
                                    id="importeOpcionCompra"
                                    valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambiaValorImporte}"
                                    disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteOpcionCompraEditable}">
                        <ctr:conversorBigDecimal numeroDecimales="2"/>
                      </tr:inputText>
                    </tr:panelLabelAndMessage>
                       <tr:outputText value="€ (IVA excluido)"></tr:outputText>
	                     <tr:spacer width="30" height="1" ></tr:spacer>
	                      <tr:selectOneChoice id="ivaOpcionCompra" autoSubmit="true"
	                      					  unselectedLabel=" " label="IVA:" disabled="#{!configuracionGestionNecesidadesContratacion.campoImporteOpcionCompraEditable}"
	                      					  binding="#{gestionDatosEconomicosNecesidadBinding.csoImporteOpcionCompraPorcentajeIVA}"
	                      					  value="#{gestionDatosEconomicosNecesidadBean.importeOpcionCompraPorcentajeIVA}" >
	                      	<f:selectItems    value="#{gestorListaTiposIVA.listaElementos}"  >
	                      	</f:selectItems>
	                      </tr:selectOneChoice>
	                      <tr:spacer width="30" height="1" ></tr:spacer>
	                     <tr:outputText value="Total: "></tr:outputText>
	                     <tr:spacer width="5" height="1" ></tr:spacer>
	                     <tr:outputText id="outOComCIVA" partialTriggers="ivaOpcionCompra importeOpcionCompra"
	                     	 value="#{gestionDatosEconomicosNecesidadBean.importeOpcionCompraConIVA}">
	                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
	                     </tr:outputText>

                     </tr:panelHorizontalLayout>

                  </tr:group>
                </f:facet>
                </tr:switcher>
            </tr:panelFormLayout>

            <tr:panelFormLayout >
              <tr:panelGroupLayout>
                <tr:panelHorizontalLayout>
                <tr:panelLabelAndMessage label="Presupuesto"
                                         labelStyle="width: 175px;">
                  <tr:inputText columns="20" simple="true"
                                value="#{gestionDatosEconomicosNecesidadBean.presupuestoMaximoSinIVA}"
                                binding="#{gestionDatosEconomicosNecesidadBinding.citPresupuesto}"
                                disabled="#{gestionDatosEconomicosNecesidadBean.necesidadAOCO  || !configuracionGestionNecesidadesContratacion.campoPresupuestoMaximoEditable}"
                                autoSubmit="true"
                                valueChangeListener="#{gestionDatosEconomicosNecesidadBean.cambiaValorPresupuestoMaximo}"
                                partialTriggers="cantidad precioUnitiario importeArrendamiento importeMantenimiento importeOpcionCompra"
                                id="presupuestoMaximo">
                        <ctr:conversorBigDecimal numeroDecimales="2"/>
				  </tr:inputText>
                </tr:panelLabelAndMessage>
                 <tr:outputText value="€ (IVA excluido)"></tr:outputText>
	                     <tr:spacer width="30" height="1" ></tr:spacer>
	                     <tr:outputText value="Total: "></tr:outputText>
	                     <tr:spacer width="5" height="1" ></tr:spacer>
	                     <tr:outputText id="outPMaxCIVA" partialTriggers="ivaPrecio ivaOpcionCompra ivamantenimiento ivaarrendamiento presupuestoMaximo cantidad precioUnitiario importeArrendamiento importeMantenimiento importeOpcionCompra"
	                     	 value="#{gestionDatosEconomicosNecesidadBean.presupuestoMaximoConIVA}">
	                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
	                     </tr:outputText>
                     </tr:panelHorizontalLayout>
                <tr:spacer height="10"></tr:spacer>
				<tr:panelHorizontalLayout valign="middle">
                <tr:panelLabelAndMessage label="Urgente"
                                         labelStyle="width: 175px;">

                  <tr:selectBooleanCheckbox autoSubmit="true" id="urgente"
                                            simple="true"
                                            value="#{gestionNecesidadesBean.urgente}"
                                            valueChangeListener="#{gestionNecesidadesBean.cambiaValorUrgente}"
                                            disabled="#{!configuracionGestionNecesidadesContratacion.campoUrgenciaEditable}"/>


                </tr:panelLabelAndMessage>

                <tr:panelLabelAndMessage label="Motivo de urgencia" >
                 <tr:inputText id="intMotUrg" rows="2" columns="80"
                  				partialTriggers="urgente"
                                simple="true"
                                binding="#{gestionNecesidadContratacionBinding.citMotivoUrgencia}"
                                value="#{gestionNecesidadesBean.motivoUrgencia}"
                                disabled="#{gestionNecesidadesBean.motivoUrgenciaDeshabilitado  || !configuracionGestionNecesidadesContratacion.campoUrgenciaEditable}"/>
				</tr:panelLabelAndMessage>
               	</tr:panelHorizontalLayout>
               <tr:group rendered="#{configuracionGestionNecesidadesContratacion.bloqueEstadoWorkflowVisible}">
               <tr:panelLabelAndMessage label="Estado" labelStyle="width: 175px;"
                                       rendered="#{!gestionNecesidadesBean.modoEdicion.agregar}">
                <tr:iterator id="itEsta" rows="25" var="estados" value="#{workflowBeanNecesidades.estadosActuales}">
         				<tr:outputText id="outEsta" value="#{estados.label}"/>

      			</tr:iterator>

              </tr:panelLabelAndMessage>
               </tr:group>
              </tr:panelGroupLayout>
            </tr:panelFormLayout>
          </tr:panelBox>
          <tr:panelBox background="medium" inlineStyle="width: 100%;"
                       id="bloqueValidacionUrgencia"
                       rendered="#{configuracionGestionNecesidadesContratacion.bloqueValidacionUrgenciaVisible and gestionNecesidadesBean.urgente}">
            <tr:selectOneRadio label="Validar necesidad urgente"
                               value="#{gestionNecesidadesBean.validacionUrgencia}"
                               id="validacionUrgencia" layout="horizontal"
                               disabled="#{!configuracionGestionNecesidadesContratacion.campoValidacionUrgenciaEditable}">
              <tr:selectItem label="Sin validar"/>
              <tr:selectItem label="Aceptada" value="aceptada"/>
              <tr:selectItem label="Rechazada" value="rechazada"/>
            </tr:selectOneRadio>
          </tr:panelBox>

           <tr:group id="tramitacionWorkflow"
                     rendered="#{configuracionGestionNecesidadesContratacion.bloqueTramitacionVisible}">
            		<tr:panelBox id="pnlTramit" background="medium" text="Trámites posibles"
						inlineStyle="width: 100%;"
						rendered="#{workflowBeanNecesidades.frameHabilitado}">
						<tr:selectOneRadio id="sorTramite" value="#{workflowBeanNecesidades.idTramite}">
							<f:selectItems value="#{workflowBeanNecesidades.transicionesPosibles}" />
						</tr:selectOneRadio>
						<tr:panelFormLayout>
							<f:facet name="footer">
								<tr:panelHorizontalLayout halign="right">
									<tr:commandButton id="btnTramitar" text="Tramitar"
										action="#{gestionNecesidadesBean.tramitar}">
									</tr:commandButton>
								</tr:panelHorizontalLayout>
							</f:facet>
							<tr:inputText id="intObs" label="Observaciones" rows="2" columns="110"
								value="#{workflowBeanNecesidades.observaciones}"/>
							<tr:inputText id="intObsAnt" label="Observaciones del trámite anterior" rows="2"
								columns="110" value="#{workflowBeanNecesidades.observacionesTramiteAnterior}"
								disabled="true" rendered="#{workflowBeanNecesidades.muestraObservacionesTramiteAnterior}"
							 />
						</tr:panelFormLayout>
					</tr:panelBox>
          </tr:group>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton id="btnGuardar" text="Guardar" rendered="#{gestionNecesidadesBean.modoEdicion.modificar}"
                              action="#{gestionNecesidadesBean.actualizaYSigue}"/>
            <tr:commandButton id="btnAceptar" text="Aceptar" rendered="#{gestionNecesidadesBean.modoEdicion.agregar}"
                              action="#{aceptarNecesidadAccion.ejecuta}" />
            <tr:commandButton id="btnAcepYMas" text="Aceptar y agregar mas" rendered="#{gestionNecesidadesBean.modoEdicion.agregar}"
                              action="#{gestionNecesidadesBean.agregarMas}"/>
            <tr:commandButton id="btnVolver" text="Volver" action="#{gestionNecesidadesBean.cancelar}" immediate="true" />
            <tr:commandButton id="btnMenPrin" text="Menú principal"
                              action="#{volverMenuPrincipalBean.ejecuta}" immediate="true" />
          </tr:panelHorizontalLayout>
          <tr:panelBox background="medium" text="Necesidades agregadas" inlineStyle="width: 100%;"
                       id="panelNecesidadesAgregadas"
                       rendered="#{gestionNecesidadesBean.bloqueNecesidadesAgregadasVisible}" >
          <tr:commandButton id="cmdTramitar" text="Tramitar en grupo"
                            action="#{gestionNecesidadesBean.tramitarEnGrupo}" />
          <tr:table emptyText="No deberías estar viendo esto"
                      width="100%" rows="10" var="fila"
                      value="#{tablaNecesidadesAgregadas.datosModeloAplanado}"
                      id="tabNecesidadesAgregadas" >
              		<tr:column  headerText="Artículo">
                      <tr:outputText id="outArticulo" value="#{fila.articulo}"  />
                    </tr:column>
                	<tr:column  headerText="Cantidad">
                      <tr:outputText id="outCantidad" value="#{fila.cantidad}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6" muestraMoneda="false" /></tr:outputText>
                    </tr:column>
                    <tr:column
                               headerText="Presupuesto"   align="right" noWrap="true">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresLicSIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText  id="outPresLicPIVA" value="#{fila.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresLicCIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column  headerText="Tipo de contrato">
                      <tr:outputText id="outTipCon" value="#{fila.tipoContrato}"/>
                    </tr:column>
	              <f:facet name="footer">
              		<tr:outputText id="outNumLin" value="Número de necesidades agregadas  #{tablaNecesidadesAgregadas.numeroLineasAplando}" />
              	  </f:facet>
              </tr:table>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
