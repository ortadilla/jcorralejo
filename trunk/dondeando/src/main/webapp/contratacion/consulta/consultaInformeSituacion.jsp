<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Informe de situación">
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
          <tr:panelHeader text="Informe de situación"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:showDetailHeader id="detalle" text="Criterios de búsqueda" disclosed="true" >
            <tr:spacer width="10" height="10"/>
              <tr:panelFormLayout>
                <tr:panelLabelAndMessage label="Fecha desde">
					<tr:panelHorizontalLayout>
                       <tr:inputDate value="#{formBusquedaInformeSituacion.fechaDesde}"
                       				simple="true"/>
                       <tr:spacer width="10" height="10"/>
                       <tr:outputText value="a"/>
                       <tr:spacer width="10" height="10"/>
                       <tr:inputDate value="#{formBusquedaInformeSituacion.fechaHasta}" simple="true"/>
                    </tr:panelHorizontalLayout>
				</tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Tipo de centro">
                   <tr:panelHorizontalLayout>
                		<tr:selectOneChoice id="tipoCentro"
                							unselectedLabel="Todos"
                							simple="true"
											value="#{formBusquedaInformeSituacion.idTipoCentro}"
											inlineStyle="width:680px"
											autoSubmit="true"
											valueChangeListener="#{formBusquedaInformeSituacion.cambiaValorTipoOrganoGestor}">
							<f:selectItems value="#{gestorListaTipoOrganoGestor.listaElementos}" />
						</tr:selectOneChoice>
					</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Centro">
                   <tr:panelHorizontalLayout>
                		<tr:selectOneChoice id="centro"
                							unselectedLabel="Todos"
		                					simple="true"
											value="#{formBusquedaInformeSituacion.idCentro}"
											inlineStyle="width:680px"
											partialTriggers="tipoCentro">
							<f:selectItems value="#{formBusquedaInformeSituacion.centros}" />
						</tr:selectOneChoice>
					</tr:panelHorizontalLayout>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
            </tr:showDetailHeader>
          <tr:spacer width="10" height="10"/>
          <tr:showDetailHeader text="Criterios de búsqueda avanzada" disclosed="true"
          						id="criteriosADV">
			<tr:panelFormLayout labelWidth="230">
	            <tr:spacer width="10" height="10"/>
				<tr:panelLabelAndMessage label="Tipo de dato">
                    <tr:selectOneChoice unselectedLabel="Todos"
                    					simple="true"
                                        value="#{formBusquedaInformeSituacion.tipoDato}"
                                        inlineStyle="width:370px">
                      <tr:selectItem label="Expedientes en licitación" value="expedientesEnLicitacion"/>
                      <tr:selectItem label="Expedientes adjudicados" value="expedientesAdjudicados"/>
                    </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
                <tr:spacer width="10" height="10"/>
			</tr:panelFormLayout>
		  </tr:showDetailHeader>
          <tr:separator/>
 				<tr:panelLabelAndMessage label="Tipo de informe">
					<tr:panelHorizontalLayout>
						<tr:selectBooleanCheckbox text="Por publicidad"
							value="#{pintadorResultadosInformeSituacionBean.porPublicidad}"
							simple="true"
                            autoSubmit="true"
							id="chkPorPublicidad" />
						<tr:selectBooleanCheckbox text="Por tipo de contrato"
							value="#{pintadorResultadosInformeSituacionBean.porTipoContrato}"
							simple="true"
                            autoSubmit="true"
							id="chkPorTipoContrato" />
						<tr:selectBooleanCheckbox text="Por tipo de procedimiento"
							value="#{pintadorResultadosInformeSituacionBean.porTipoProcedimiento}"
							simple="true"
                            autoSubmit="true"
							id="chkPorTipoProcedimiento" />
					</tr:panelHorizontalLayout>
				</tr:panelLabelAndMessage>
          <tr:panelHorizontalLayout halign="right">
            <tr:commandButton text="Buscar" onclick="noBloquearPantalla()"
            				partialTriggers="chkPorPublicidad chkPorTipoContrato chkPorTipoProcedimiento"
              				disabled="#{!pintadorResultadosInformeSituacionBean.porPublicidad
              							and	!pintadorResultadosInformeSituacionBean.porTipoContrato
              							and !pintadorResultadosInformeSituacionBean.porTipoProcedimiento}"
              				action="#{consultaInformeSituacionBean.encontrar}" />
          </tr:panelHorizontalLayout>
		  </tr:panelBox>
          <tr:separator/>
          <tr:panelHorizontalLayout halign="center">
           <tr:commandButton text="Menú principal"
                              action="#{botonMenuAdminBean.ejecuta}"/>
          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>