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
      <trh:head title="Consulta procedimientos">
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
          <tr:panelHeader text="Configurar condiciones de flujo"/>
          <tr:table emptyText="No items were found" width="100%" rows="5"
                    selectionListener="#{tablaProcedimientosWorkflowBean.eventoSeleccion}"
                    value="#{tablaProcedimientosWorkflowBean.modelo}" autoSubmit="true" rowSelection="single"
                    selectedRowKeys="#{tablaProcedimientosWorkflowBean.seleccionadas}"
                     first="#{tablaProcedimientosWorkflowBean.filaInicial}"
                     rangeChangeListener="#{tablaProcedimientosWorkflowBean.eventoCambioRango}"
                    var="procedimiento" id="tablaProcedimientos">

            <tr:column  headerText="Procedimientos definidos en el Workflow"
                      >
              <tr:outputText value="#{procedimiento.descripcion}"/>
            </tr:column>
            <f:facet name="actions"/>
          </tr:table>
          <tr:spacer width="10" height="10" />

          <tr:panelBox  background="medium" partialTriggers="tablaProcedimientos tabla filtrar filtroTransicion filtroInicio filtroFin"
          		inlineStyle="width:100%" text="Transiciones definidas en el procedimiento" >

        <tr:panelBox background="medium" partialTriggers="filtrar filtroTransicion filtroInicio filtroFin"
          inlineStyle="width:100%"  >
           		<tr:showDetailHeader  text="Filtrar" id="filtrar" disclosed="#{filtroTablaTransicionesBean.filtrar}"
           		   		disclosureListener="#{filtroTablaTransicionesBean.cambioFiltrar}" >

           			<tr:panelFormLayout>

        		  <tr:panelHorizontalLayout>

						<tr:panelLabelAndMessage label="Transición" >
									<tr:inputText simple="true" columns="30" partialTriggers="filtrar"
									id="filtroTransicion" value="#{filtroTablaTransicionesBean.criterioTransicion}"
									autoSubmit="true" valueChangeListener="#{filtroTablaTransicionesBean.cambioTransicion}"
									 />
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" height="10" />
						<tr:panelLabelAndMessage label="Estado Inicio" >
									<tr:inputText simple="true" columns="35" partialTriggers="filtrar"
									value="#{filtroTablaTransicionesBean.criterioInicio}"
									id="filtroInicio" valueChangeListener="#{filtroTablaTransicionesBean.cambioInicio}"
									autoSubmit="true"
									 />
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" height="10" />
						<tr:panelLabelAndMessage label="Estado Fin" >
									<tr:inputText simple="true" columns="35" partialTriggers="filtrar"
									value="#{filtroTablaTransicionesBean.criterioFin}"
									id="filtroFin" valueChangeListener="#{filtroTablaTransicionesBean.cambioFin}"
									autoSubmit="true"
									 />
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" height="10" />
						<tr:commandLink id="limpiaSeleccion"
                                            actionListener="#{filtroTablaTransicionesBean.limpiaCriterios}"
                          					shortDesc="Limpiar">
                                <tr:image source="/imagenes/btnEliminar.gif"
                                            shortDesc="Limpiar"/>
                         </tr:commandLink>
				</tr:panelHorizontalLayout>



           			</tr:panelFormLayout>

			</tr:showDetailHeader>
			</tr:panelBox>

          <tr:table emptyText="No items were found" width="100%" rows="5"
                    selectionListener="#{tablaTransicionesWorkflowBean.eventoSeleccion}"
                    value="#{tablaTransicionesWorkflowBean.modelo}" rowSelection="single" autoSubmit="true"
                     selectedRowKeys="#{tablaTransicionesWorkflowBean.seleccionadas}"
                     first="#{tablaTransicionesWorkflowBean.filaInicial}" id="tabla"
                     rangeChangeListener="#{tablaTransicionesWorkflowBean.eventoCambioRango}"
                     sortListener="#{tablaTransicionesWorkflowBean.eventoOrdenacion}"
	                 var="transicion" partialTriggers="tablaProcedimientos filtrar">

           <f:facet name="header">

				</f:facet>

            <tr:column sortable="true" sortProperty="codigo"
            		 headerText="Código transición" >
              <tr:outputText value="#{transicion.codigo}"/>
            </tr:column>
            <tr:column sortable="true" sortProperty="descripcion"
                       headerText="Descripción de la transición">
              <tr:outputText value="#{transicion.descripcion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Estado inicial" sortProperty="estadosInicio">
              <tr:outputText value="#{transicion.estadosInicio}"/>
            </tr:column>
            <tr:column sortable="true" sortProperty="estadosFin"
                       headerText="Estados finales">
              <tr:outputText value="#{transicion.estadosFin}"/>
            </tr:column>
            <tr:column sortable="true" sortProperty="rutaImagenCondicionesParametrizadas"
                       headerText="Tiene condiciones parametrizadas"
                       >
                    <tr:image source="#{transicion.rutaImagenCondicionesParametrizadas}"/>
   	        </tr:column>
   	         <tr:column sortable="true" sortProperty="rutaImagenCondicionesNoParametrizadas"
                       headerText="Tiene condiciones prefijadas"
                       >
                    <tr:image source="#{transicion.rutaImagenCondicionesNoParametrizadas}"/>
   	        </tr:column>
            <f:facet name="actions">
             <tr:panelButtonBar>
                  <tr:commandButton text="Condiciones de flujo parametrizables" action="#{tablaTransicionesWorkflowBean.verCondiciones}"
                                    />

                  <tr:commandButton text="Condiciones de flujo prefijadas" action="#{modificarTransicionesNoParametrizables.ejecuta}"
                                    />

                </tr:panelButtonBar>
            </f:facet>
          </tr:table>
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
