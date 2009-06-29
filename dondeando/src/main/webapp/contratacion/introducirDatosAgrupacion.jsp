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
      <trh:head title="Agrupación de necesidades de contratación">
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
          <tr:panelHeader text="Datos de la agrupación"/>
          <tr:messages></tr:messages>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" inlineStyle="width:100%">
                <tr:panelLabelAndMessage label="Nombre">
                <tr:inputText value="#{introducirDatosAgrupacionBean.nombreAgrupacion}"
                  			  disabled="#{!introducirDatosAgrupacionBean.nuevaAgrupacion}"/>
                </tr:panelLabelAndMessage>

                <tr:spacer height="10" ></tr:spacer>

                <tr:outputText value="Presupuesto de licitación total"></tr:outputText>
                   <tr:spacer height="2" ></tr:spacer>

				 <tr:table
                      width="100%" rows="10" var="fila"
                      value="#{introducirDatosAgrupacionBean.desgloseIVA.importesDetalle}" >

              		<tr:column  headerText="Sin IVA" align="right" noWrap="true">
                      <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.valorSinIVA}"  ><ctr:conversorMonedaBigDecimalSalida numeroDecimales="2" muestraMoneda="true" /></tr:outputText>
                    </tr:column>
                    <tr:column headerText="IVA" align="right" noWrap="true">
                      <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.porcentajeIVA}" ><tr:convertNumber pattern="'IVA% '##"></tr:convertNumber> </tr:outputText>
                    </tr:column>
                	<tr:column  headerText="Con IVA" align="right" noWrap="true">
                      <tr:outputText inlineStyle="white-space: nowrap;" value="#{fila.valorConIVA}">
                      		<ctr:conversorMonedaBigDecimalSalida numeroDecimales="2" muestraMoneda="true" /></tr:outputText>
                    </tr:column>
	              <f:facet name="footer">
	              	<tr:panelHorizontalLayout rendered="#{!introducirDatosAgrupacionBean.desgloseIVA.unSoloTipoDeIVA}">
	              		<tr:outputText value="TOTAL(IVA excluido): "/>
	              		<tr:spacer width="5" ></tr:spacer>
                 		 <tr:outputText value="#{introducirDatosAgrupacionBean.desgloseIVA.importeTotalSinIVA}">
                         		<ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
				  		</tr:outputText>

                    	 <tr:spacer width="30" ></tr:spacer>
                 		 <tr:outputText value="Total(IVA incluido): "></tr:outputText>
	                     <tr:spacer width="5" ></tr:spacer>
	                     <tr:outputText value="#{introducirDatosAgrupacionBean.desgloseIVA.importeTotalConIVA}">
	                     	 <ctr:conversorMonedaBigDecimalSalida numeroDecimales="2"/>
	                     </tr:outputText>

	              	</tr:panelHorizontalLayout>
	              </f:facet>
              	</tr:table>



                <tr:spacer width="10" height="10"/>
				<tr:panelHorizontalLayout halign="center">
                    <tr:commandButton text="Aceptar" useWindow="false"
                                      action="#{introducirDatosAgrupacionBean.aceptar}"/>
                    <tr:commandButton text="Cancelar" immediate="true"
                                      action="#{botonCerrarPopUpBean.volver}"
                                      />
				</tr:panelHorizontalLayout>

          </tr:panelBox>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>