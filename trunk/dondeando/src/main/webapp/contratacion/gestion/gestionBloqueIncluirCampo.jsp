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
      <trh:head title="Selecciona el campo a incluir en el bloque">
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
          <tr:panelHeader text="Selecciona el campo a incluir en el bloque [#{gestionBloqueIncluirCampoBean.nombre}]"/>
     		<tr:messages />
          <tr:spacer width="10" height="10"/>
          	<tr:panelBox background="medium"  inlineStyle="width:100%" >

          		<tr:panelBox background="medium" inlineStyle="width:100%" text="Criterios de búsqueda" >

				<tr:panelHorizontalLayout>

						<tr:spacer width="40" height="10" />
						<tr:panelLabelAndMessage label="Descripcion" >
									<tr:inputText simple="true" columns="60"
									binding="#{filtroTablaCamposBindingIncluirCampoEnBloqueBean.filtroDescripcion}"
									id="filtroDescripcion"
									value="#{filtroTablaCamposIncluirCampoEnBloqueBean.filtroDescripcion}"
									 />
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" height="10" />
						<tr:selectBooleanCheckbox label="Ocultar campos incluidos"
							binding="#{filtroTablaCamposBindingIncluirCampoEnBloqueBean.ocultarCamposIncluidos}"
							id="filtroIncluidos"
							value="#{filtroTablaCamposIncluirCampoEnBloqueBean.filtrarIncluidos}"
						></tr:selectBooleanCheckbox>
						<tr:spacer width="40" height="10" />
						<tr:commandButton text="Buscar" action="#{filtroTablaCamposIncluirCampoEnBloqueBean.filtra}" />
				</tr:panelHorizontalLayout>

                </tr:panelBox>
					<tr:table emptyText="No items were found" width="100%" rows="10"
						selectionListener="#{tablaCamposIncluirCampoEnBloqueBean.eventoSeleccion}"
						selectedRowKeys="#{tablaCamposIncluirCampoEnBloqueBean.seleccionadas}"
						sortListener="#{tablaCamposIncluirCampoEnBloqueBean.eventoOrdenacion}"
						first="#{tablaCamposIncluirCampoEnBloqueBean.filaInicial}"
						rangeChangeListener="#{tablaCamposIncluirCampoEnBloqueBean.eventoCambioRango}"
						rowSelection="single" autoSubmit="true"
						value="#{tablaCamposIncluirCampoEnBloqueBean.modelo}"
						partialTriggers="filtroDescripcion filtroIncluidos"
						var="tabla">

						<tr:column headerText="Campo">
							<tr:outputText value="#{tabla.descripcion}" />
						</tr:column>

						<tr:column headerText="Incluido" noWrap="true">
							<tr:panelHorizontalLayout valign="top">
								<tr:iterator rows="25" var="incluido"
									value="#{tabla.incluidoEn}">
									<tr:panelList>
										<tr:outputText value="#{incluido}" />
									</tr:panelList>
								</tr:iterator>
							</tr:panelHorizontalLayout>
						</tr:column>
					</tr:table>
					<tr:separator></tr:separator>
          			<tr:panelLabelAndMessage label="Etiqueta del campo en el bloque: "  >
          				<tr:inputText columns="80" rows="3" simple="true" value="#{gestionBloqueIncluirCampoBean.etiqueta}" />
          			</tr:panelLabelAndMessage>

          </tr:panelBox>
          <tr:separator/>
           <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Aceptar"
                             action="#{gestionBloqueIncluirCampoBean.actualizarYSalir}"
                             />
            <tr:commandButton text="Cancelar" immediate="true"
                             action="#{gestionBloqueIncluirCampoBean.cancelar}" />

          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>