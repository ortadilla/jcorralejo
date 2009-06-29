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
      <trh:head title="Texto del anuncio">
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

        <h:form onsubmit="bloquearPantalla(this)">
          <tr:panelBox id="panelBox1" background="medium"
                       text="Confección del anuncio para #{editorDatosPublicacion.nombreBoletin}"
                       inlineStyle="width:90%">
            <tr:panelFormLayout>
              <f:facet name="footer"/>
              <tr:inputText rows="21" columns="100"
                            value="#{editorDatosPublicacion.texto}"
                            readOnly="#{!configuracionEditorDatosPublicador.campoTextoAnuncioEditable}"
                            binding="#{editorDatosPublicacion.inputTexto}"/>
              <tr:separator/>
              <tr:panelPageHeader>
                <f:facet name="branding">
                  <tr:panelGroupLayout>
                    <tr:outputLabel value="Número (aprox.) de caracteres:"/>
                    <tr:spacer/>
                    <tr:outputText value="#{editorDatosPublicacion.caracteresTexto}"
                                   partialTriggers="timer"/>
                  </tr:panelGroupLayout>
                </f:facet>
                <f:facet name="brandingAppContextual"/>
                <f:facet name="menuSwitch">
                  <tr:panelGroupLayout>
                    <tr:outputLabel value="Número (aprox.) de palabras:"/>
                    <tr:spacer/>
                    <tr:outputText value="#{editorDatosPublicacion.palabrasTexto}"
                                   partialTriggers="timer"/>
                  </tr:panelGroupLayout>
                </f:facet>
                <f:facet name="menuGlobal"/>
              </tr:panelPageHeader>
              <tr:poll interval="5000" id="timer"/>
            </tr:panelFormLayout>
            <tr:panelGroupLayout inlineStyle="width:100%">
              <f:facet name="footer"/>
              <tr:separator/>
              <tr:panelFormLayout id="bloqueFechaEnvioPublicar"
                                  rendered="#{configuracionEditorDatosPublicador.bloqueFechaEnvioPublicarVisible}"
                                 >
				<tr:panelLabelAndMessage label="Fecha de Envío a Publicar" labelStyle="width:350px">
                	<tr:inputDate simple="true"
                              value="#{editorDatosPublicacion.fechaEnvio}"
                              disabled="#{!configuracionEditorDatosPublicador.campoFechaEnvioEditable}"/>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
              <tr:separator/>
              <tr:panelFormLayout id="bloqueInformacionPublicacion"
                                  rendered="#{configuracionEditorDatosPublicador.bloqueInformacionPublicacionVisible}"
                                 >
               	<tr:panelLabelAndMessage label="Importe de Publicación en #{editorDatosPublicacion.nombreBoletin}" >
	            	<tr:panelHorizontalLayout>
	                  <tr:inputText simple="true" autoSubmit="true"
	                                value="#{editorDatosPublicacion.importePublicacionSinIVA}"
	                                disabled="#{!configuracionEditorDatosPublicador.campoImportePublicacionEditable}"
	                                id="importePublicacionSinIVA" partialTriggers="importePublicacionSinIVA" >
						<ctr:conversorBigDecimal numeroDecimales="2"/>
					  </tr:inputText>
                      <tr:outputText value="€ (IVA excluido)"></tr:outputText>
            		</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
               	<tr:panelLabelAndMessage label="Tipo IVA:" >
	            	<tr:panelHorizontalLayout>
                      <tr:selectOneChoice id="ivaImportePublicacion" autoSubmit="true" simple="true"
                    			      unselectedLabel=" "
                    			      disabled="#{!configuracionEditorDatosPublicador.campoImportePublicacionEditable}"
                                 	  value="#{editorDatosPublicacion.importePublicacionPorcentajeIVA}" >
	                    <f:selectItems value="#{gestorListaTiposIVA.listaElementos}"  >
	                    </f:selectItems>
                      </tr:selectOneChoice>
					  <tr:spacer width="10" ></tr:spacer>
                      <tr:outputText value="Total: "></tr:outputText>
                      <tr:spacer width="5" ></tr:spacer>
                      <tr:outputText partialTriggers="ivaImportePublicacion importePublicacionSinIVA"
                     	 			value="#{editorDatosPublicacion.importePublicacionConIVA}">
                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
                	</tr:outputText>
            		</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Fecha de Publicación en #{editorDatosPublicacion.nombreBoletin}"  labelStyle="width:350px">
                  <tr:inputDate simple="true"
                                value="#{editorDatosPublicacion.fechaPublicacion}"
                                disabled="#{!configuracionEditorDatosPublicador.campoFechaPublicacionEditable}"/>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Número de #{editorDatosPublicacion.nombreBoletin}" labelStyle="width:350px">
                  <tr:inputText simple="true"
                                value="#{editorDatosPublicacion.numeroBoletin}"
                                disabled="#{!configuracionEditorDatosPublicador.campoNumeroBoletinEditable}"/>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Número de Página en #{editorDatosPublicacion.nombreBoletin}" labelStyle="width:350px">
                  <tr:inputText simple="true"
                                value="#{editorDatosPublicacion.paginaEnBoletin}"
                                disabled="#{!configuracionEditorDatosPublicador.campoPaginaEnBoletinEditable}"/>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Enlace en #{editorDatosPublicacion.nombreBoletin}" labelStyle="width:350px">
                <tr:inputText simple="true"
                              value="#{editorDatosPublicacion.enlaceBoletin}"
                              disabled="#{!configuracionEditorDatosPublicador.campoEnlaceBoletinEditable}"/>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Fecha Límite de Presentación de Documentación" labelStyle="width:350px">
                  <tr:inputDate simple="true"
                                value="#{editorDatosPublicacion.fechaLimitePresentacion}"
                                disabled="#{!configuracionEditorDatosPublicador.campoFechaLimitePresentacionEditable}"/>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
              <tr:separator/>
              <tr:panelFormLayout id="bloqueFacturacionPublicacion"
                                  rendered="#{configuracionEditorDatosPublicador.bloqueFacturacionPublicacionVisible}"
                                  >
                <f:facet name="footer"/>
                <tr:panelLabelAndMessage label="Número de Factura en #{editorDatosPublicacion.nombreBoletin}" labelStyle="width:350px">
                  <tr:inputText simple="true"
                                value="#{editorDatosPublicacion.numeroFacturaEnBoletin}"
                                disabled="#{!configuracionEditorDatosPublicador.campoNumeroFacturaEnBoletinEditable}"/>
                </tr:panelLabelAndMessage>
               	<tr:panelLabelAndMessage label="Importe de Factura en #{editorDatosPublicacion.nombreBoletin}" >
	            	<tr:panelHorizontalLayout>
	                  <tr:inputText simple="true" autoSubmit="true"
	                                value="#{editorDatosPublicacion.importeFacturaEnBoletinSinIVA}"
	                                disabled="#{!configuracionEditorDatosPublicador.campoImporteFacturaEnBoletinEditable}"
	                                id="importeFacturaEnBoletinSinIVA" partialTriggers="importeFacturaEnBoletinSinIVA" >
						<ctr:conversorBigDecimal numeroDecimales="2"/>
					  </tr:inputText>
                      <tr:outputText value="€ (IVA excluido)"></tr:outputText>
            		</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
               	<tr:panelLabelAndMessage label="Tipo IVA:" >
	            	<tr:panelHorizontalLayout>
                      <tr:selectOneChoice id="ivaImporteFacturaEnBoletin" autoSubmit="true" simple="true"
                    			      unselectedLabel=" "
                    			      disabled="#{!configuracionEditorDatosPublicador.campoImporteFacturaEnBoletinEditable}"
                                 	  value="#{editorDatosPublicacion.importeFacturaEnBoletinPorcentajeIVA}" >
	                    <f:selectItems value="#{gestorListaTiposIVA.listaElementos}"  >
	                    </f:selectItems>
                      </tr:selectOneChoice>
					  <tr:spacer width="10" ></tr:spacer>
                      <tr:outputText value="Total: "></tr:outputText>
                      <tr:spacer width="5" ></tr:spacer>
                      <tr:outputText partialTriggers="ivaImporteFacturaEnBoletin importeFacturaEnBoletinSinIVA"
                     	 			value="#{editorDatosPublicacion.importeFacturaEnBoletinConIVA}">
                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
                	</tr:outputText>
            		</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Fecha de Envío a Centro de Factura" labelStyle="width:350px">
                  <tr:inputDate simple="true"
                                value="#{editorDatosPublicacion.fechaEnvioCentroFact}"
                                disabled="#{!configuracionEditorDatosPublicador.campoFechaEnvioCentroFactEditable}"/>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Factura Repercutida en #{editorDatosPublicacion.nombreBoletin}" labelStyle="width:350px">
                  <tr:selectOneRadio simple="true"
                                     value="#{editorDatosPublicacion.facturaRepercutidaEnBoletin}"
                                     disabled="#{!configuracionEditorDatosPublicador.campoFacturaRepercutidaEnBoletinEditable}"
                                     layout="horizontal">
                    <tr:selectItem label="No" value="false"/>
                    <tr:selectItem label="Sí" value="true"/>
                  </tr:selectOneRadio>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
            </tr:panelGroupLayout>
            <tr:panelHorizontalLayout halign="center">
              <tr:commandButton text="Aceptar" actionListener="#{editorDatosPublicacion.aceptar}"
                                />
              <tr:commandButton text="Cancelar"
                                action="#{editorDatosPublicacion.cancelar}"
                                />
            </tr:panelHorizontalLayout>
          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>