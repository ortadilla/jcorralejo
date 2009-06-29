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
      <trh:head title="Alta de nuevo expediente de contratación">
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

        <h:form onsubmit="bloquearPantalla(this)" id="formGestionCabeceraExpediente">
          <tr:panelHeader text="Alta de nuevo expediente de contratación"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox id="pnlCabExp" background="medium" inlineStyle="width: 100%;"
                       text="Cabecera del expediente" partialTriggers="socTipoContrato socProcedimientoContratacion" >
            <tr:panelFormLayout  inlineStyle="width: 100%;" partialTriggers="socTipoContrato socProcedimientoContratacion">
              <f:facet name="footer"/>
              <tr:panelLabelAndMessage label="Objeto del contrato">
                <tr:inputText id="itxObjetoContrato" rows="3" columns="30" maximumLength="250" simple="true"
                              value="#{frmGestionCabeceraExpedienteContratacionBean.objetoContrato}"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Fecha de resolución de inicio de expediente">
                <tr:inputDate columns="15" id="sidFechaResolucion"      simple="true"
                                    value="#{frmGestionCabeceraExpedienteContratacionBean.fechaResolucion}"
                                    autoSubmit="true"/>
              </tr:panelLabelAndMessage>
              <tr:panelLabelAndMessage label="Tipo de contrato">
                <tr:selectOneChoice id="socTipoContrato"   simple="true"
                                    valueChangeListener="#{altaExpedienteContratacionBean.escuchaCambioValorDeTipoContrato}"
                                    autoSubmit="true"
                                    value="#{frmGestionCabeceraExpedienteContratacionBean.idTipoContrato}"
                                    inlineStyle="width:370px"
                                    >
                  <f:selectItems value="#{tiposContratoPosibles}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>

              <tr:switcher facetName="#{altaExpedienteContratacionBean.gestorListaProcedimientoContratacionVacio ? 'nada' : 'combo'}" >
					<f:facet name="combo">
					<tr:panelLabelAndMessage label="Procedimiento">
	  			        <tr:selectOneChoice id="socProcedimientoContratacion" simple="true"
		                                    valueChangeListener="#{altaExpedienteContratacionBean.escuchaCambioValorDeProcedimiento}"
		                                    autoSubmit="true"
		                                    value="#{frmGestionCabeceraExpedienteContratacionBean.idProcedimiento}"
		                                    inlineStyle="width:370px"
		                                    partialTriggers="socTipoContrato" >
		                  <f:selectItems value="#{gestorListaProcedimientoContratacionAltaExpediente.listaElementos}"/>
		                </tr:selectOneChoice>
		                </tr:panelLabelAndMessage>
		            </f:facet>
	          </tr:switcher>

              <tr:switcher facetName="#{altaExpedienteContratacionBean.gestorListaFormaAdjudicacionVacio ? 'nada' : 'combo'}" >
				<f:facet name="combo">
	              <tr:panelLabelAndMessage label="Forma de adjudicación">
	                <tr:selectOneChoice id="socFormaAdjudicacion" simple="true"
	                                    value="#{frmGestionCabeceraExpedienteContratacionBean.idFormaAdjudicacion}"
	                                    inlineStyle="width:370px"
	                                    partialTriggers="socProcedimientoContratacion" >
	                  <f:selectItems value="#{gestorListaFormaAdjudicacionAltaExpediente.listaElementos}"/>
	                </tr:selectOneChoice>
	              </tr:panelLabelAndMessage>
	            </f:facet>
	          </tr:switcher>
  			  <tr:switcher facetName="#{altaExpedienteContratacionBean.gestorListaProcedimientoComplementarioSeleccionVacio ? 'nada' : 'combo'}" >
				<f:facet name="combo">
	              <tr:panelLabelAndMessage label="Procedimientos complementarios de selección">
	                <tr:selectOneChoice id="socProcedimientoComplementarioSeleccion" simple="true"
	                                    value="#{frmGestionCabeceraExpedienteContratacionBean.idProcedimientoComplementarioSeleccion}"
	                                    inlineStyle="width:370px"
	                                    partialTriggers="socProcedimientoContratacion" >
	                  <f:selectItems value="#{gestorListaProcedimientoComplementarioSeleccionAltaExpediente.listaElementos}"/>
	                </tr:selectOneChoice>
	              </tr:panelLabelAndMessage>
	            </f:facet>
	          </tr:switcher>
              <tr:panelLabelAndMessage label="Tipo de licitación">
                <tr:selectOneChoice id="socTipoLicitacion" simple="true"
                                    value="#{frmGestionCabeceraExpedienteContratacionBean.idTipoLicitacion}"
                                    inlineStyle="width:370px">
                  <f:selectItems value="#{gestorListaTipoLicitacion.listaElementos}"/>
                </tr:selectOneChoice>
              </tr:panelLabelAndMessage>
            </tr:panelFormLayout>
            <tr:panelHeader text="Modelo de pliego asociado"/>
            <tr:table emptyText="No existen modelos de pliego" rows="10" var="pliego"
            		  value="#{tablaVersionesModeloPliegoBean.modelo}" autoSubmit="true" rowSelection="single"
                      width="100%" selectionListener="#{tablaVersionesModeloPliegoBean.eventoSeleccion}"
                      selectedRowKeys="#{tablaVersionesModeloPliegoBean.seleccionadas}"

                       sortListener="#{tablaVersionesModeloPliegoBean.eventoOrdenacion}"
                       first="#{tablaVersionesModeloPliegoBean.filaInicial}"
                       rangeChangeListener="#{tablaVersionesModeloPliegoBean.eventoCambioRango}"

                      partialTriggers="socTipoContrato socProcedimientoContratacion sidFechaResolucion"
                      id="tabModeloPliegoAsociado">
              <tr:column sortable="true" headerText="Descripción del modelo de pliego"
                         sortProperty="descripcion">
                <tr:outputText id="outDescPliego" value="#{pliego.descripcion}"/>
              </tr:column>

            </tr:table>
            <tr:panelHeader text="Necesidades asociadas"/>
            <tr:table emptyText="No existen necesidades asociadas" rows="10" rowSelection="multiple"
                      var="nodo" width="100%" selectedRowKeys="#{tablaNecesidadesExpedienteContratacionBean.seleccionadas}"
                      value="#{tablaNecesidadesExpedienteContratacionBean.modelo}"
                      id="tabNecesidadesAsociadas">


            <tr:column  headerText="Clasificación">
                      <tr:outputText id="outCodArt" value="#{nodo.codigoArticulo}"  />
                    </tr:column>
                    <tr:column  headerText="Nombre">
                      <tr:outputText id="outDescArt" value="#{nodo.descripcionArticulo}" rendered="#{!nodo.agrupacion}"/>
                      <tr:outputText id="outNomAgru" value="#{nodo.nombreAgrupacion}" rendered="#{nodo.agrupacion}"/>
             </tr:column>
            <tr:column
                       headerText="Cantidad">
              <tr:outputText id="outCant" value="#{nodo.cantidad}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6" muestraMoneda="false" /></tr:outputText>
            </tr:column>
            <tr:column rendered="#{!tablaNecesidadesExpedienteContratacionBean.algunAOCO}" align="right" noWrap="true"
                       headerText="Precio">

              			<tr:panelHorizontalLayout halign="right"> <tr:outputText id="outPrecSIVA" inlineStyle="white-space: nowrap;" value="#{nodo.precio.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>
                      <tr:outputText id="outPrecPIVA" value="#{nodo.precio.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                      <tr:panelHorizontalLayout halign="right"> <tr:outputText id="outPrecCIVA" inlineStyle="white-space: nowrap;" value="#{nodo.precio.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>


            </tr:column>
            <tr:column  rendered="#{tablaNecesidadesExpedienteContratacionBean.algunAOCO}" align="right" noWrap="true"
                       headerText="Importe de arrendamiento">

              <tr:panelHorizontalLayout halign="right">  <tr:outputText id="outIArrSIVA" inlineStyle="white-space: nowrap;" value="#{nodo.importeArrendamiento.valorSinIVA}" ><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>
                        <tr:outputText id="outIArrPIVA" value="#{nodo.importeArrendamiento.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                       <tr:panelHorizontalLayout halign="right">	 <tr:outputText id="outIArrCIVA" inlineStyle="white-space: nowrap;" value="#{nodo.importeArrendamiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>

            </tr:column>
            <tr:column rendered="#{tablaNecesidadesExpedienteContratacionBean.algunAOCO}" align="right" noWrap="true"
                       headerText="Importe de mantenimiento">
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIManSIVA" inlineStyle="white-space: nowrap;" value="#{nodo.importeMantenimiento.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outIManPIVA" value="#{nodo.importeMantenimiento.porcentajeIVA}"><tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outIManCIVA" inlineStyle="white-space: nowrap;" value="#{nodo.importeMantenimiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>

            </tr:column>
            <tr:column  rendered="#{tablaNecesidadesExpedienteContratacionBean.algunAOCO}" align="right" noWrap="true"
                       headerText="Importe de opc. compra">
              			  <tr:panelHorizontalLayout halign="right"><tr:outputText id="outOComSIVA" inlineStyle="white-space: nowrap;"  value="#{nodo.importeOpcionCompra.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText  id="outOComPIVA" value="#{nodo.importeOpcionCompra.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outOComCIVA" inlineStyle="white-space: nowrap;"  value="#{nodo.importeOpcionCompra.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
            </tr:column>
            <tr:column  headerText="Presupuesto de licitación" align="right" noWrap="true"
                       >
                         <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresuSIVA" inlineStyle="white-space: nowrap;"  value="#{nodo.presupuestoLicitacionSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText id="outPresuPIVA" value="#{nodo.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText id="outPresuCIVA" inlineStyle="white-space: nowrap;"  value="#{nodo.presupuestoLicitacionConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
            </tr:column>
            <tr:column  headerText="Tipo de contrato "
                       >
              <tr:outputText id="outTipCont" value="#{nodo.tipoContrato}"/>
            </tr:column>

              <f:facet name="actions">

                <tr:panelButtonBar>
                <tr:commandButton id="btnAgregar" text="Agregar"
                                  action="#{seleccionarMasNecesidades.ejecuta}"/>
                <tr:commandButton id="btnEliminar" text="Eliminar"
                                    action="#{borrarNecesidadesExpediente.ejecuta}"
                                    onclick="if(!confirm('¿Desea eliminar del expediente las necesidades seleccionadas?')) return false"/>
                </tr:panelButtonBar>
              </f:facet>
            </tr:table>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
            <tr:commandButton id="btnGuardar" text="Guardar"
                              action="#{guardarExpedienteControlTipoLicitacion.ejecuta}"
                              actionListener="#{dialogoSeleccionBooleanoAccion.configuraComponente}"
                              returnListener="#{guardarExpedienteControlTipoLicitacion.retornoConfirmacion}">
            </tr:commandButton>

            <tr:commandButton id="btnMenuPrin" text="Menú principal"
                              action="#{volverMenuPrincipalBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
