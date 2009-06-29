<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"

          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:ctr="http://contratacion/faces">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Necesidades de contratación">
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

        <h:form onsubmit="bloquearPantalla(this)" id="formConsultaNecesidades">
          <tr:panelHeader text="#{configuracionConsultaNecesidades.titulo}" />
          <tr:spacer width="10" height="10"/>
          <tr:panelBox id="pnlBusqueda" background="medium" partialTriggers="detalle" inlineStyle="width:100%">
          	<tr:messages  />
            <tr:showDetailHeader text="Criterios de búsqueda" id="detalle" disclosed="true">
              <tr:panelFormLayout>
              <tr:panelGroupLayout>
                <tr:spacer width="10" height="10"/>
                <tr:panelLabelAndMessage label="Tipo de contrato" labelStyle="width: 175px;"
                                         rendered="#{configuracionConsultaNecesidades.comboTipoContratoVisible}">
                  <tr:selectOneChoice id="socTipoContrato" unselectedLabel="Todos" simple="true"
                                      value="#{formBusquedaNecesidades.idTipoContrato}"
                                      inlineStyle="width:370px">
                    <f:selectItems value="#{gestorListaTipoContrato.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Homologada" labelStyle="width: 175px;"
                                         rendered="#{configuracionConsultaNecesidades.comboDeterminacionTipoVisible}">
                  <tr:selectOneChoice id="socHomologada" unselectedLabel="Todos" simple="true"
                                      value="#{formBusquedaNecesidades.determinacionTipo}"
                                      inlineStyle="width:370px">
                    <f:selectItems value="#{gestorListaBooleano.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Urgente" labelStyle="width: 175px;">
                  <tr:panelHorizontalLayout>
                    <tr:selectOneChoice id="socUrgente" value="#{formBusquedaNecesidades.urgente}"
                                        simple="true" unselectedLabel="Todos"
                                        inlineStyle="width:370px">
                      <tr:selectItem label="Urgente" value="true"/>
                      <tr:selectItem label="No urgente" value="false"/>
                    </tr:selectOneChoice>
		            <tr:spacer width="30" height="10"/>
		            <tr:outputLabel value="Validación urgencia"  />
                    <tr:selectOneChoice id="socValidacionUrgencia" unselectedLabel="Todos" simple="true"
                                        value="#{formBusquedaNecesidades.validacionUrgencia}"
                                        inlineStyle="width:370px" >
                      <tr:selectItem label="Sin validar" value="sinValidar"/>
                      <tr:selectItem label="Aceptadas" value="aceptada"/>
                      <tr:selectItem label="Rechazadas" value="rechazada"/>
                    </tr:selectOneChoice>
                  </tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Estado" labelStyle="width: 175px;"
                                         rendered="#{configuracionConsultaNecesidades.comboEstadosVisible}">
                  <tr:selectOneChoice id="socEstado" unselectedLabel="Todos" simple="true"
                                      value="#{formBusquedaNecesidades.codigoEstado}"
                                      inlineStyle="width:680px">
                    <f:selectItems value="#{gestorListaEstadoWorkflowNecesidades.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Centro" labelStyle="width: 175px;"
                                         rendered="#{configuracionConsultaNecesidades.comboCentrosVisible}">
                  <tr:selectOneChoice id="socCentro" unselectedLabel="Todos" simple="true"
                                      value="#{formBusquedaNecesidades.idOrganoGestor}"
                                      inlineStyle="width:680px">
                    <f:selectItems value="#{gestorListaOrganoGestor.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>

				<tr:panelLabelAndMessage label="Fecha de registro de" labelStyle="width: 175px;">
					<tr:panelHorizontalLayout>
						<tr:inputDate id="indFechaRegistroDesde" simple="true"
							value="#{formBusquedaNecesidades.fechaRegistroDesde}" />
						<tr:spacer width="10" height="10" />
						<tr:outputLabel value="a" />
						<tr:spacer width="10" height="10" />
						<tr:inputDate id="indFechaRegistroHasta" simple="true"
							value="#{formBusquedaNecesidades.fechaRegistroHasta}" />
						<tr:spacer width="10" height="10" />
					</tr:panelHorizontalLayout>
				</tr:panelLabelAndMessage>
				</tr:panelGroupLayout>
			</tr:panelFormLayout>

              <tr:panelFormLayout>
               <tr:panelGroupLayout>
           	  	<ctr:selectorArticulo labelStyle="width: 175px;"  disabled="false" idCampoCodigo="campoArticulo"
           	  						  articulo="#{formBusquedaNecesidades.articulo}"
           	  						  outcomeVuelta="volverConsultaNecesidad">
           	  	</ctr:selectorArticulo>
              	</tr:panelGroupLayout>
              </tr:panelFormLayout>

               <tr:spacer width="10" height="20"/>
                    <tr:panelHorizontalLayout halign="right">
                      <tr:commandButton id="btnBuscar" text="Buscar"
                                        action="#{consultaNecesidadesBean.encontrar}"/>
                    </tr:panelHorizontalLayout>
            </tr:showDetailHeader>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox id="pnlResultados" background="medium" partialTriggers="btnAgrupar tabNecesidadesAplanada"
                       text="Necesidades de contratación" inlineStyle="width:100%">
            <tr:panelButtonBar>
              <tr:switcher defaultFacet="GenerarExpediente"
                           facetName="#{configuracionConsultaNecesidades.facetAccionesExpediente}">
                <f:facet name="GenerarExpediente">
                  <h:panelGroup>
  	                <tr:commandButton id="btnAgregar" text="Agregar"
		                              action="#{agregarAccionTabla.ejecuta}"
		                              rendered="#{configuracionConsultaNecesidades.botonAgregarVisible}" />
				  <!--  Comprobar si el botón de listado se controla por configuración -->
					<tr:commandButton id="btnImprimirListado" text="Imprimir listado" onclick="noBloquearPantalla()"
									  actionListener="#{imprimirListadoNecesidades.ejecuta}"
									  rendered="#{configuracionConsultaNecesidades.botonImprimirListadoVisible}"
									  >
					</tr:commandButton>
                    <tr:commandButton text="Articulo genérico"
                                      action="#{agruparArticuloGenerico.ejecuta}"
									  rendered="#{configuracionConsultaNecesidades.botonArticuloGenericoVisible}"
                                      disabled="#{!tablaNecesidadesConsultaBean.articuloGenericoHabilitado}"
                                      actionListener="#{dialogoSeleccionBooleanoAccion.configuraComponente}"
                                      returnListener="#{agruparArticuloGenerico.retornoAgregarAExitente}"
                                      partialTriggers="tabNecesidadesAplanada"
                                      id="articuloGenerico"
                                      windowWidth="500" windowHeight="200"
                                      >
                                      <tr:setActionListener from="#{'Ya existe una necesidad para el articulo generico. ¿Desea acumular?'}"
                                      						to="#{dialogoSeleccionBooleanoAccion.titulo}"/>
                                      </tr:commandButton>
					 <tr:commandButton text="Tramitar en grupo"
                                      action="#{tramitarEnGrupo.ejecuta}"
									  rendered="#{configuracionConsultaNecesidades.botonTransicionEnGrupoVisible}"
                                      partialTriggers="tabNecesidadesAplanada"
                                      id="transicionEnGrupo">
                    </tr:commandButton>

                    <tr:commandButton id="btnGenerarExpediente" text="Generar expediente"
                                      action="#{generarExpediente.ejecuta}"
									  rendered="#{configuracionConsultaNecesidades.botonGenerarExpedienteVisible}"
                                      disabled="#{!tablaNecesidadesConsultaBean.generarExpedienteHabilitado}"
                                      partialTriggers="tabNecesidadesAplanada"/>
                    <tr:commandButton text="Agrupar" id="btnAgrupar"
                                      action="#{agruparNecesidades.ejecuta}"
									  rendered="#{configuracionConsultaNecesidades.botonAgruparVisible}"
                                      useWindow="true"
                                      disabled="#{!tablaNecesidadesConsultaBean.agruparNecesidadesHabilitado}"
                                      partialTriggers="tabNecesidadesAplanada"
                                      launchListener="#{agruparNecesidades.parametrosDatosAgrupacion}"
                                      returnListener="#{agruparNecesidades.retornaDatosAgrupacion}"
                                      windowHeight="380" windowWidth="500"
                                      partialSubmit="true"/>
				    <!--retomar esto cuando se retome la integracion con el PdC -->                                      
					<tr:commandButton text="Enviar PdC" id="btnEnviarPdC" rendered="false"
                                      action="#{enviarNecesidadesPortalCompras.ejecuta}"
                                      useWindow="true"
                                      launchListener="#{enviarNecesidadesPortalCompras.parametrosEnviarPdc}"
                                      returnListener="#{enviarNecesidadesPortalCompras.retornaDatosEnviarPdc}"
                                      windowHeight="200" windowWidth="200"
									  />
                  </h:panelGroup>
                </f:facet>
                <f:facet name="AgregarLotes">
                  <h:panelGroup>
                    <tr:commandButton id="btnAceptar" text="Aceptar"
                                      action="#{aceptarGenerarExpediente.ejecuta}"
                                      disabled="#{!tablaNecesidadesConsultaBean.generarExpedienteHabilitado}"
                                      partialTriggers="tabNecesidadesAplanada"/>
                    <tr:commandButton id="btnCancelar" text="Cancelar"
                                      action="#{cancelarGenerarExpediente.ejecuta}"/>
                  </h:panelGroup>
                </f:facet>
                <f:facet name="AgregarLotesExpedienteCreado">
                  <h:panelGroup>
                    <tr:commandButton id="btnAceptar2" text="Aceptar"
                                      action="#{aceptarAgregarNecesidadesExpedienteCreado.ejecuta}"
                                      disabled="#{!tablaNecesidadesConsultaBean.generarExpedienteHabilitado}"
                                      partialTriggers="tabNecesidadesAplanada"
									  actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
                    <tr:commandButton id="btnCancelar2" text="Cancelar"
                                      action="#{cancelarAgregarNecesidadesExpedienteCreado.ejecuta}"/>
                  </h:panelGroup>
                </f:facet>
              </tr:switcher>
            </tr:panelButtonBar>

          <tr:table emptyText="No se encontraron necesidades de contratación"
                      width="100%" rows="10" var="fila"
                      partialTriggers="btnAgrupar articuloGenerico" varStatus="varStaus"
                      value="#{tablaNecesidadesConsultaBean.datosModeloAplanado}" autoSubmit="true"
                      selectionListener="#{tablaNecesidadesConsultaBean.eventoSeleccionTablaAplanada}"
                      selectedRowKeys="#{tablaNecesidadesConsultaBean.seleccionadasModeloAplanado}"
                      rowSelection="multiple"
                      id="tabNecesidadesAplanada" >

                    <tr:column  headerText="Clasificación">
                      <tr:outputText id="outCodigoArticulo" value="#{fila.codigoArticulo}"  />
                    </tr:column>
              		<tr:column  headerText="Nombre">
                      <tr:outputText id="outDescripcionArticulo" value="#{fila.descripcionArticulo}"  />
                    </tr:column>
                    <tr:column headerText="Agrupación">
                      <tr:outputText id="outAgrupacion" value="#{fila.padre.nombreAgrupacion}"
						inlineStyle="#{fila.estilo}" rendered="#{fila.padre.agrupacion}"/>
                    </tr:column>
                	<tr:column  headerText="Cantidad">
                      <tr:outputText id="outCantidad" value="#{fila.cantidad}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6" muestraMoneda="false" /></tr:outputText>
                    </tr:column>
                    <tr:column  headerText="Precio"  align="right" noWrap="true"  >
                      <tr:panelHorizontalLayout halign="right"> <tr:outputText inlineStyle="white-space: nowrap;" id="outPrecSIVA" value="#{fila.precio.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>
                      <tr:outputText id="outPrecPIVA" value="#{fila.precio.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                      <tr:panelHorizontalLayout halign="right"> <tr:outputText id="outPrecCIVA" inlineStyle="white-space: nowrap;" value="#{fila.precio.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column rendered="#{tablaNecesidadesConsultaBean.algunAOCO}" align="right" noWrap="true"
                               headerText="I. ARR">
                       <tr:panelHorizontalLayout halign="right" >  <tr:outputText id="outIArrSIVA" inlineStyle="white-space: nowrap;" value="#{fila.importeArrendamiento.valorSinIVA}" ><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>
                        <tr:outputText id="outIArrPIVA" value="#{fila.importeArrendamiento.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                       <tr:panelHorizontalLayout halign="right">	 <tr:outputText id="outIArrCIVA" inlineStyle="white-space: nowrap;" value="#{fila.importeArrendamiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>

                    </tr:column>
                    <tr:column rendered="#{tablaNecesidadesConsultaBean.algunAOCO}"    align="right" noWrap="true"
                               headerText="I. MAN">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIManSIVA" inlineStyle="white-space: nowrap;" value="#{fila.importeMantenimiento.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outIManPIVA" value="#{fila.importeMantenimiento.porcentajeIVA}"><tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIManCIVA" inlineStyle="white-space: nowrap;" value="#{fila.importeMantenimiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column rendered="#{tablaNecesidadesConsultaBean.algunAOCO}"    align="right" noWrap="true"
                    	 headerText="I. COM">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIComSIVA" inlineStyle="white-space: nowrap;"  value="#{fila.importeOpcionCompra.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outIComPIVA" value="#{fila.importeOpcionCompra.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIComCIVA" inlineStyle="white-space: nowrap;"  value="#{fila.importeOpcionCompra.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column headerText="Presup."   align="right" noWrap="true">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresupSIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outPresupPIVA" value="#{fila.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresupCIVA" inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column  headerText="Tipo de contrato">
                      <tr:outputText id="outTipoCon" value="#{fila.tipoContrato}"/>
                    </tr:column>
                    <tr:column  headerText="Urgencia"
                               >
                      <tr:image source="#{fila.rutaImagenUrgencia}" shortDesc="#{fila.descripcionUrgencia}"
                                     />
                    </tr:column>
                    <tr:column  headerText="Estado" rendered="#{configuracionConsultaNecesidades.columnaEstadoVisible}">
                      <tr:outputText id="outEstado" value="#{fila.estado}"/>
                    </tr:column>
                    <tr:column  headerText="Centro"
                          rendered="#{configuracionConsultaNecesidades.columnaCentroVisible}"    >
                      <tr:outputText id="outCentro" value="#{fila.centro}" />
                    </tr:column>
                    <tr:column id="caAcc" headerText="Acciones" rendered="#{configuracionConsultaNecesidades.columnaAccionesVisible}">
						<tr:iterator rows="25" var="acciones" value="#{fila.acciones}" id="itAcc">
							<tr:commandLink action="#{ejecutorAccionPorNombre.ejecuta}" id="cmlAcc"
											onclick="#{acciones.confirmacionAccion}">
								<tr:setActionListener to="#{tablaNecesidadesConsultaBean.seleccionado}" from="#{fila}"/>
								<tr:setActionListener to="#{ejecutorAccionPorNombre.nombreAccion}" from="#{acciones.nombreComponente}"/>
								<tr:outputText id="outAccDescr" value="#{acciones.descripcion}" />
							</tr:commandLink>
						</tr:iterator>
                    </tr:column>

	              <f:facet name="footer">
              		<tr:outputText id="outNumLin" value="Número de resultados encontrados  #{tablaNecesidadesConsultaBean.numeroLineasAplando}" />
              	  </f:facet>
              </tr:table>
              <tr:separator></tr:separator>
          </tr:panelBox>
          <tr:separator/>

          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton id="btnMenuPrincipal" text="Menú principal"
                              action="#{volverMenuPrincipalBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
