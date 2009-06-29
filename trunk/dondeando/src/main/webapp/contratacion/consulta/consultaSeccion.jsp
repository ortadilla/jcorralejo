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
      <trh:head title="Gestión de secciones">
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
          <tr:panelHeader text="Gestión de secciones"/>
          <tr:spacer width="10" height="10"/>
          <tr:panelBox background="medium" partialTriggers="detalle" inlineStyle="width:100%">
            <tr:showDetailHeader id="detalle" text="Criterios de búsqueda" disclosed="#{!consultaSeccion.haRecuperadoDatos}">
            <tr:spacer width="10" height="10"/>
            <tr:panelHorizontalLayout halign="start">
             <tr:panelLabelAndMessage label="Código">
                  <tr:inputText value="#{formBusquedaSeccion.codigoSeccion}" simple="true" />
                  </tr:panelLabelAndMessage>
                </tr:panelHorizontalLayout>
              <tr:spacer width="10" height="10"/>
              <tr:panelHorizontalLayout halign="right">
	              <tr:commandButton text="Buscar" action="#{consultaSeccion.encontrar}"/>
              </tr:panelHorizontalLayout>
            </tr:showDetailHeader>
          </tr:panelBox>
          <tr:spacer width="10" height="10"/>
             <tr:table emptyText="No items were found" width="100%"
             selectionListener="#{tablaSeccionBean.eventoSeleccion}"
          	 var="fila" autoSubmit="true" rowSelection="single"
          	 value="#{tablaSeccionBean.modelo}"
          	 selectedRowKeys="#{tablaSeccionBean.seleccionadas}"
             sortListener="#{tablaSeccionBean.eventoOrdenacion}"
             first="#{tablaSeccionBean.filaInicial}"
             rangeChangeListener="#{tablaSeccionBean.eventoCambioRango}"

          	 >
            <f:facet name="actions">
               <tr:panelButtonBar>
                    <tr:commandButton text="Agregar"  action="#{agregarAccionTabla.ejecuta}"/>
                    <tr:commandButton text="Modificar" action="#{modificarSeccion.ejecuta}"/>
	            <tr:commandButton id="cbBaja" text="Baja" action="#{borrarSeccion.ejecuta}"/>
                </tr:panelButtonBar>
            </f:facet>
            <tr:column  headerText="Código">
              <tr:outputText value="#{fila.codigo}"/>
            </tr:column>
            <tr:column  headerText="Descripción">
              <tr:outputText value="#{fila.descripcion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Orden" sortProperty="orden">
              <tr:outputText value="#{fila.orden}"/>
            </tr:column>

            <tr:column headerText="Visible en:"  width="100px" >
              	<tr:commandLink text="[ Estados ]" rendered="#{!fila.cero}"
                            action="#{modificarEstadosVisibleSeccion.ejecuta}"
                            useWindow="true" windowWidth="800" windowHeight="500"
                            returnListener="#{modificarEstadosVisibleSeccion.retornaEstadosSeleccionados}">

	                           <tr:setActionListener to="#{modificarEstadosVisibleSeccion.idSeccion}" from="#{fila.identificador}"/>
	                           <tr:setActionListener from="#{'Seleccionar los estados en los que la seccion debe verse'}" to="#{pageFlowScope.titulo}" />
	                           <tr:setActionListener from="#{gestorListaEstadoWorkflowExpedientesContratacion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
          		  </tr:commandLink>
            </tr:column>

            <tr:column headerText="Ficheros editables en:" width="150px" >
              	<tr:commandLink text="[ Estados ]" rendered="#{!fila.cero}"
                            action="#{modificarEstadosEditableSeccion.ejecuta}"
                            useWindow="true" windowWidth="800" windowHeight="500"
                            returnListener="#{modificarEstadosEditableSeccion.retornaEstadosSeleccionados}">
	                           <tr:setActionListener to="#{modificarEstadosEditableSeccion.idSeccion}" from="#{fila.identificador}"/>
	                           <tr:setActionListener from="#{'Seleccionar los estados en los ficheros de la seccion son editables'}" to="#{pageFlowScope.titulo}" />
	                           <tr:setActionListener from="#{gestorListaEstadoWorkflowExpedientesContratacion.listaElementos}" to="#{pageFlowScope.elementosSeleccionables}" />
          		  </tr:commandLink>
            </tr:column>

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