<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="Consulta de modelos de pliego">
        <meta http-equiv="Content-Type"
              content="text/html; charset=iso-8859-15"/>
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
          <tr:panelHeader text="Gestión de modelos de pliego"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%" >
          <tr:showDetailHeader id="detalle"
          					text="Criterios de búsqueda"
          					disclosed="#{!consultaModeloPliegoBean.haRecuperadoDatos}"
          					inlineStyle="width:100%">
			<tr:panelFormLayout>
             <tr:panelLabelAndMessage label="Descripción" id="labelMes">
                  <tr:panelHorizontalLayout>
                    <tr:inputText value="#{formBusquedaModeloPliego.descripcion}" simple="true"
                    			  inlineStyle="width:365px"/>
                    <tr:spacer width="10" height="10"/>
                  </tr:panelHorizontalLayout>
                  </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Procedimiento">
                  <tr:selectOneChoice id="socProcedimientoContratacion" simple="true"
                                      value="#{formBusquedaModeloPliego.idProcedimientoContratacion}"
                                      unselectedLabel="Todos"
                                      inlineStyle="width:370px">
                    <f:selectItems value="#{gestorListaProcedimientoContratacion.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
                <tr:panelLabelAndMessage label="Tipo de contrato">
                  <tr:selectOneChoice id="socTipoContrato" autoSubmit="true" simple="true"
                                      value="#{formBusquedaModeloPliego.idTipoContrato}"
                                      unselectedLabel="Todos"
                                      inlineStyle="width:370px">
                    <f:selectItems value="#{gestorListaTipoContrato.listaElementos}"/>
                  </tr:selectOneChoice>
                </tr:panelLabelAndMessage>
			</tr:panelFormLayout>
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout halign="right">
               <tr:commandButton text="Buscar"
                                  action="#{consultaModeloPliegoBean.encontrar}"/>
            </tr:panelHorizontalLayout>
          </tr:showDetailHeader>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
          <tr:table id="tblModelosPliego"
          			 emptyText="No existen pliegos" rows="10" var="pliego"
                     width="100%" value="#{tablaModeloPliegoBean.modelo}"
                     sortListener="#{tablaModeloPliegoBean.eventoOrdenacionYLimpiaVersiones}"
                     selectedRowKeys="#{tablaModeloPliegoBean.seleccionadas}"
                     first="#{tablaModeloPliegoBean.filaInicial}"
                     rangeChangeListener="#{tablaModeloPliegoBean.eventoCambioRango}"
                     autoSubmit="true"
                     rowSelection="single"
                     selectionListener="#{tablaModeloPliegoBean.eventoSeleccion}">
            <tr:column sortable="true" headerText="Descripción del modelo de pliego"
                       sortProperty="descripcion">
              <tr:outputText value="#{pliego.descripcion}"/>
            </tr:column>
            <f:facet name="actions">
		        <tr:panelButtonBar >
                                        <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}"/>
                        		<tr:commandButton text="Ver" action="#{verModeloPliego.ejecuta}" />
					<tr:commandButton text="Modificar" action="#{modificarModeloPliego.ejecuta}" />
					<tr:commandButton text="Borrar" action="#{borrarModeloPliego.ejecuta}"
									onclick="if(!confirm('¿Desea borrar el modelo de pliego?')) return false"/>
				</tr:panelButtonBar>
            </f:facet>
          </tr:table>
          <tr:spacer width="10" height="10"/>
          <tr:panelGroupLayout partialTriggers="tblModelosPliego">
            <tr:switcher defaultFacet="versiones" >
              <f:facet name="vacio">

              </f:facet>
              <f:facet name="versiones">
                <tr:panelBox text="Versiones" background="medium" inlineStyle="width: 100%;" >
                  <tr:table emptyText="No existen versiones" rows="10"
                            var="version" width="100%" autoSubmit="true" rowSelection="single"
                            value="#{tablaVersionesModeloPliegoBean.modelo}"
                            selectedRowKeys="#{tablaVersionesModeloPliegoBean.seleccionadas}"

                     		sortListener="#{tablaVersionesModeloPliegoBean.eventoOrdenacion}"
                            first="#{tablaVersionesModeloPliegoBean.filaInicial}"
                     		rangeChangeListener="#{tablaVersionesModeloPliegoBean.eventoCambioRango}"


	                        selectionListener="#{tablaVersionesModeloPliegoBean.eventoSeleccion}">
                    <tr:column sortable="true" headerText="Fecha inicio validez"
                               sortProperty="fechaInicioValidez">
                      <tr:inputDate value="#{version.fechaInicioValidez}" readOnly="true"
                      			inlineStyle="border-style:none"/>
                    </tr:column>
                    <tr:column sortable="true" headerText="Fecha fin validez"
                               sortProperty="fechaFinValidez">
                      <tr:inputDate value="#{version.fechaFinValidez}" readOnly="true"
                      			inlineStyle="border-style:none"/>
                    </tr:column>

		            <f:facet name="actions">
		            	<tr:panelButtonBar >
				      <tr:commandButton text="Agregar" action="#{agregarVersionModeloPliego.ejecuta}" />
				      <tr:commandButton text="Copiar" action="#{clonarVersionModeloPliego.ejecuta}" />
                                            <tr:commandButton text="Modificar" action="#{modificarVersionModeloPliego.ejecuta}" />
					    <tr:commandButton text="Borrar" action="#{borrarVersionModeloPliego.ejecuta}"
							onclick="if(!confirm('¿Desea borrar la versión del modelo de pliego?')) return false"/>
					    <tr:commandButton text="Mostrar esquema" action="#{verEsquemaVersionModeloPliego.ejecuta}"/>
					    <tr:commandButton text="Editar estructura" action="#{modificarEstructuraVersionModeloPliego.ejecuta}"  />
				      </tr:panelButtonBar>
		            </f:facet>
                    <tr:column
                               headerText="Idenficador del procedimiento">
                      <tr:outputText value="#{version.descripcionProcedimiento}"/>
                    </tr:column>
                  </tr:table>
                </tr:panelBox>
              </f:facet>
            </tr:switcher>
          </tr:panelGroupLayout>
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
