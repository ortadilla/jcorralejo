<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:ctr="http://contratacion/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=ISO-8859-15" />
	<f:view>
		<trh:html>
		<trh:head title="Gestión de expediente de contratación">
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
				<tr:panelHeader text="Gestión de expediente de contratación" />
				<tr:messages />
     <tr:spacer width="10" height="10"/>

      <tr:panelBox background="medium" text="Cabecera" inlineStyle="width: 100%;" contentStyle="width: 100%;"  >

	  <tr:panelBorderLayout inlineStyle="width: 100%;" >
              <f:facet name="left">
                <h:panelGroup>
                  <tr:panelFormLayout inlineStyle="width: 100%;"  >
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Número del expediente:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.numeroExpediente}" />
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Fecha de inicio de resolución:">
                      <tr:inputDate value="#{frmCabeceraExpedienteBean.fechaResolucion}"
                                          readOnly="true"
                                          inlineStyle="border-style:none; font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Objeto del contrato:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.objetoContrato}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="CCA:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.codigoContratoAdministrativo}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Confeccionado por:">
                      <tr:outputText value="(usuario)"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:separator/>
                  </tr:panelFormLayout>
                  <tr:panelFormLayout labelWidth="100" fieldWidth="300">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Órgano gestor:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionCentro}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                  </tr:panelFormLayout>
                </h:panelGroup>
              </f:facet>
              <f:facet name="right">
                <h:panelGroup>
                  <tr:panelFormLayout fieldWidth="310" labelWidth="160" partialTriggers="cambioTipoLicitacion">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Tipo de contrato:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionTipoContrato}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Procedimiento de contratación:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionProcedimientoContratacion}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Forma de adjudicación:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionFormaAdjudicacion}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Modelo de Pliego:">
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionModeloPliego}"
                                     inlineStyle="font-size: small;"/>
                    </tr:panelLabelAndMessage>
                    <tr:panelLabelAndMessage label="Tipo de licitación:" >
                      <tr:outputText value="#{frmCabeceraExpedienteBean.descripcionTipoLicitacion}"
                                     inlineStyle="font-size: small;"/>
                        <tr:spacer width="10"></tr:spacer>

                    </tr:panelLabelAndMessage>
                    <tr:separator/>
                  </tr:panelFormLayout>
                  <tr:panelFormLayout fieldWidth="450" labelWidth="30">
                    <f:facet name="footer"/>
                    <tr:panelLabelAndMessage label="Estado:">
                      <tr:iterator rows="25" var="estados"
                                   value="#{frmCabeceraExpedienteBean.estadosActuales}">
                        <tr:panelList>
                          <tr:outputText value="#{estados.label}"
                                         inlineStyle="font-size: 12px;"/>
                        </tr:panelList>
                      </tr:iterator>
                    </tr:panelLabelAndMessage>
                  </tr:panelFormLayout>
                </h:panelGroup>
              </f:facet>
              <f:facet name="top"/>
              <f:facet name="innerRight">
                <tr:panelFormLayout>
                  <f:facet name="footer"/>
                </tr:panelFormLayout>
              </f:facet>
            </tr:panelBorderLayout>

          </tr:panelBox>

     <tr:panelBox  background="medium" inlineStyle="width:100%"
                  text="Necesidades del expediente">
        <tr:panelButtonBar rendered="#{configuracionGestionNecesidadesExpediente.botonesCambiarNecesidadesExpedienteVisibles}">
                    <tr:commandButton text="Eliminar" id="btnEliminar"
                                      action="#{eliminarNecesidadExpediente.ejecuta}"
                                      partialTriggers="tabNecesidades"
                                      partialSubmit="true"
                                      onclick="if(!confirm('¿Desea eliminar del expediente las necesidades seleccionadas?')) return false"
									  actionListener="#{actionListenerCompruebaBloqueo.procesaAccion}" />
              <tr:commandButton text="Agregar" id="btnAgregar"
                                action="#{seleccionarMasNecesidadesExpedienteCreado.ejecuta}"/>
            </tr:panelButtonBar>
            <tr:table emptyText="No se encontraron necesidades de contratación"
                      width="100%" rows="10" var="nodo"
                      partialTriggers="btnEliminar" autoSubmit="true" rowSelection="multiple"
                      value="#{tablaGestionNecesidadesExpediente.modeloAgrupado}"
                      selectionListener="#{tablaGestionNecesidadesExpediente.eventoSeleccion}"
                      selectedRowKeys="#{tablaGestionNecesidadesExpediente.seleccionadas}"
                      id="tabNecesidades" immediate="true">
              <tr:column headerText="Descripción">
                <tr:outputText value="#{nodo.nombreAgrupacion}"
                               rendered="#{nodo.agrupacion}"
                               inlineStyle="color:rgb(0,0,255);"/>
                <tr:outputText value="#{nodo.codigoDescripcionArticulo}"
                               rendered="#{!nodo.agrupacion}"/>
              </tr:column>

              <f:facet name="detailStamp">
                <tr:panelHorizontalLayout>
                  <tr:spacer width="90" height="10"/>
                  <tr:table emptyText="No se han encontrado necesidades de contratación"
                            var="fila" value="#{nodo.necesidadesHijas}"
                            rows="10" width="100%">

                    <tr:column  headerText="Clasificación">
                      <tr:outputText value="#{fila.codigoArticulo}"  />
                    </tr:column>
                    <tr:column  headerText="Nombre">
                      <tr:outputText value="#{fila.descripcionArticulo}"/>
                    </tr:column>
                    <tr:column  headerText="Cantidad">
                      <tr:outputText value="#{fila.cantidad}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6" muestraMoneda="false" /></tr:outputText>
                    </tr:column>
                    <tr:column align="right" noWrap="true"  headerText="Precio" rendered="#{!tablaGestionNecesidadesExpediente.algunAOCO}">
                      <tr:panelHorizontalLayout halign="right"> <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.precio.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>
                      <tr:outputText value="#{fila.precio.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                      <tr:panelHorizontalLayout halign="right"> <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.precio.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="6"/></tr:outputText> </tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column align="right" noWrap="true" rendered="#{tablaGestionNecesidadesExpediente.algunAOCO}"
                               headerText="Importe de arrendamiento">
                       <tr:panelHorizontalLayout halign="right">  <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.importeArrendamiento.valorSinIVA}" ><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>
                        <tr:outputText value="#{fila.importeArrendamiento.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                       <tr:panelHorizontalLayout halign="right">	 <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.importeArrendamiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>

                    </tr:column>
                    <tr:column align="right" noWrap="true" rendered="#{tablaGestionNecesidadesExpediente.algunAOCO}"
                               headerText="Importe de mantenimiento">
                      <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.importeMantenimiento.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText value="#{fila.importeMantenimiento.porcentajeIVA}"><tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.importeMantenimiento.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText> </tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column align="right" noWrap="true" rendered="#{tablaGestionNecesidadesExpediente.algunAOCO}"
                    			headerText="Importe opc. compra">
                          <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;"  value="#{fila.importeOpcionCompra.valorSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText   value="#{fila.importeOpcionCompra.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;"  value="#{fila.importeOpcionCompra.valorConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>

                    </tr:column>
                    <tr:column align="right" noWrap="true"
                               headerText="Presupuesto de licitación">
                      <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionSinIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                       	 <tr:outputText   value="#{fila.porcentajeIVA}" > <tr:convertNumber pattern="'IVA% '##"/></tr:outputText>
                       	 <tr:panelHorizontalLayout halign="right"><tr:outputText inlineStyle="white-space: nowrap;"  value="#{fila.presupuestoLicitacionConIVA}"><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/></tr:outputText></tr:panelHorizontalLayout>
                    </tr:column>
                    <tr:column  headerText="Tipo de contrato">
                      <tr:outputText value="#{fila.tipoContrato}"/>
                    </tr:column>
                    <tr:column  headerText="Urgencia"
                               >
                      <tr:image source="#{fila.rutaImagenUrgencia}"
                                      />
                    </tr:column>
                    <tr:column  headerText="Estado"
                               >
                      <tr:outputText value="#{fila.estado}"/>
                    </tr:column>
                    <tr:column  headerText="Centro"
                               >
                      <tr:outputText value="#{fila.centro}"/>
                    </tr:column>
                  </tr:table>
                </tr:panelHorizontalLayout>
              </f:facet>
            </tr:table>
     </tr:panelBox>
     <tr:panelHorizontalLayout halign="center">
					<tr:commandButton text="Volver"
                                            action="#{gestionarNecesidadesExpediente.volver}" />
					<tr:commandButton text="Menú principal"
                                                action="#{volverMenuPrincipalBean.ejecuta}" />
				</tr:panelHorizontalLayout>
    </h:form>
   </trh:body>
		</trh:html>
	</f:view>
</jsp:root>
