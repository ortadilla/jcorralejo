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
      <trh:head title="Gestión de órganos de contratación">
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
          <tr:panelHeader text="Gestión de órganos de contratación"/>
          <tr:messages/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:showDetailHeader id="detalle" text="Criterios de búsqueda" disclosed="#{!consultaOrganoContratacionBean.haRecuperadoDatos}">
            <tr:spacer width="10" height="10"/>
              <tr:panelFormLayout>
                <tr:panelLabelAndMessage label="Órgano gestor">
                		<tr:selectOneChoice unselectedLabel="Todos" simple="true"
									value="#{formBusquedaOrganoContratacion.idOrganoGestor}"
									inlineStyle="width:680px">
									<f:selectItems
										value="#{gestorListaOrganoGestor.listaElementos}" />
						</tr:selectOneChoice>
                </tr:panelLabelAndMessage>
              </tr:panelFormLayout>
              <tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="right">
                 <tr:commandButton text="Buscar" action="#{consultaOrganoContratacionBean.encontrar}" />
              </tr:panelHorizontalLayout>
            </tr:showDetailHeader>
          </tr:panelBox>
            <tr:spacer width="10" height="10"/>
          <tr:table emptyText="No items were found" width="100%" selectionListener="#{tablaOrganoContratacionBean.eventoSeleccion}"
          	var="fila" autoSubmit="true" rowSelection="single"
          	value="#{tablaOrganoContratacionBean.modelo}"
          	selectedRowKeys="#{tablaOrganoContratacionBean.seleccionadas}">

            <tr:column  headerText="Código">
              <tr:outputText value="#{fila.codigo}"/>
            </tr:column>
             <tr:column  headerText="Descripción">
              <tr:outputText value="#{fila.descripcion}"/>
            </tr:column>
            <tr:column  headerText="Órgano gestor">
              <tr:outputText value="#{fila.organoGestor}"/>
            </tr:column>
            <f:facet name="actions">
            <tr:panelButtonBar>
              <tr:commandButton text="Agregar" action="#{agregarAccionTabla.ejecuta}" />
               <tr:commandButton text="Modificar"  action="#{modificarOrganoContratacion.ejecuta}" >
                </tr:commandButton>
                <tr:commandButton id="cbBaja" text="Baja" action="#{borrarOrganoContratacion.ejecuta}"/>
                   </tr:panelButtonBar>
            </f:facet>
          </tr:table>
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