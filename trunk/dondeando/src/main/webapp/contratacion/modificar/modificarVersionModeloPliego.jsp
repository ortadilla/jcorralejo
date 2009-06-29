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
      <trh:head title="Edición de versión del modelo de pliego">
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
          <tr:panelHeader text="Edición del modelo de pliego"/>
          <tr:messages></tr:messages>
          <tr:panelBox background="medium" text="Fechas de validez de la versión" inlineStyle="width:100%" >
            <tr:panelHorizontalLayout>
              <f:facet name="footer"/>
              <tr:inputText value="#{gestionEstructuraVersionModeloPliegoBean.descripcionModeloPliego}"
                                  label="Modelo de pliego" disabled="true"/>
              <tr:spacer width="10" height="10"/>
              <tr:inputDate label="Fecha inicio validez" value="#{gestionEstructuraVersionModeloPliegoBean.fechaInicioValided}" disabled="true" />
              <tr:spacer width="10" height="10"/>
              <tr:inputDate label="Fecha fin validez" value="#{gestionEstructuraVersionModeloPliegoBean.fechaFinValided}" disabled="true" />
            </tr:panelHorizontalLayout>
          </tr:panelBox>
          <tr:separator/>
          <tr:panelBox text="Definición del modelo de pliego" inlineStyle="width:100%"
          	background="medium" partialTriggers="panelTab tablaPaginas tablaBloqueCuadro tablaBloqueAnuncio filtrarCampos  filtroDescripcion filtroIncluidos pestana1 pestana2 pestana3 pestana4 pest5 pestana6" >
            <tr:panelTabbed id="panelTab" partialTriggers="tablaPaginas tablaBloqueCuadro tablaBloqueAnuncio" inlineStyle="width:100%" >
              <tr:showDetailItem text="Estructura del cuadro resumen" id="pestana1"
              					rendered="#{!gestionEstructuraVersionModeloPliegoBean.pliegoEspecifico}"
              					disclosureListener="#{gestionEstructuraVersionModeloPliegoBean.eventoPestana1}"
								disclosed="#{gestionEstructuraVersionModeloPliegoBean.pestana1}" >

				<tr:panelHorizontalLayout valign="top" halign="start" inlineStyle="width:100%;" >

              <tr:panelGroupLayout layout="vertical" inlineStyle="">
                <tr:table emptyText="No items were found" width="100%" var="pagina"
	                	rendered="#{!gestionEstructuraVersionModeloPliegoBean.pliegoEspecifico}" rowSelection="single"
		          		value="#{tablaPaginasBean.modelo}" id="tablaPaginas" autoSubmit="true"
        		  		selectedRowKeys="#{tablaPaginasBean.seleccionadas}"
        		  		sortListener="#{tablaPaginasBean.eventoOrdenacion}"
						first="#{tablaPaginasBean.filaInicial}"
						rangeChangeListener="#{tablaPaginasBean.eventoCambioRango}"
        		  		selectionListener="#{gestionEstructuraVersionModeloPliegoBean.eventoSeleccionPagina}">
            <tr:column  headerText="Página">
              <tr:outputText value="#{pagina.descripcion}"/>
            </tr:column>
             <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarPaginaAccionTabla.ejecuta}"
             				actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciasPagina}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{pagina}" to="#{tablaPaginasBean.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirPaginaAccionTabla.ejecuta}"
             		actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciasPagina}"
             		>
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{pagina}" to="#{tablaPaginasBean.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>

            <f:facet name="actions">
            <tr:panelButtonBar>
              <tr:commandButton text="Agregar" action="#{agregarPagina.ejecuta}" />

                <tr:commandButton text="Modificar" action="#{modificarPagina.ejecuta}"/>
                <tr:commandButton  text="Borrar" action="#{borrarPagina.ejecuta}"
                					onclick="if(!confirm('¿Desea borrar la página seleccionada?')) return false"/>
                <tr:spacer width="10" height="10"/>

             </tr:panelButtonBar>
            </f:facet>
          </tr:table>
          </tr:panelGroupLayout>
                 <tr:panelGroupLayout layout="vertical" inlineStyle="" >
                 <tr:spacer width="5px" ></tr:spacer>

                  </tr:panelGroupLayout>

                  <tr:panelGroupLayout layout="vertical" inlineStyle="" >

                    <tr:table emptyText="No items were found" rows="5"
                              width="100%" var="bloque"  id="tablaBloqueCuadro"
          					  partialTriggers="tablaPaginas" value="#{tablaBloqueCuadroResumenBean.modelo}"
          					  selectedRowKeys="#{tablaBloqueCuadroResumenBean.seleccionadas}" autoSubmit="true" rowSelection="single"
                    		  sortListener="#{tablaBloqueCuadroResumenBean.eventoOrdenacion}"
							  first="#{tablaBloqueCuadroResumenBean.filaInicial}"
							  rangeChangeListener="#{tablaBloqueCuadroResumenBean.eventoCambioRango}"
                    		  selectionListener="#{gestionEstructuraVersionModeloPliegoBean.eventoSeleccionBloqueCuadroResumen}">

            <tr:column  headerText="Bloque">
              <tr:outputText value="#{bloque.nombre}"/>
            </tr:column>

             <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarBloqueCuadroResumenAccionTabla.ejecuta}"
             			actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciasBloquesCuadroResumen}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{bloque}" to="#{tablaBloqueCuadroResumenBean.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirBloqueCuadroResumenAccionTabla.ejecuta}"
             					actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciasBloquesCuadroResumen}"
             	>
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{bloque}" to="#{tablaBloqueCuadroResumenBean.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>

            <f:facet name="actions">
              <tr:panelButtonBar>
              <tr:commandButton text="Agregar"  action="#{agregarBloqueCuadroResumen.ejecuta}"/>
              <tr:commandButton text="Modificar"  action="#{modificarBloqueCuadroResumen.ejecuta}"/>
                <tr:commandButton  text="Borrar" action="#{borrarBloqueCuadroResumen.ejecuta}"
                					onclick="if(!confirm('¿Desea borrar el bloque del cuadro resumen seleccionado?')) return false"/>
                       <tr:spacer width="10" height="10"/>

                </tr:panelButtonBar>
            </f:facet>
          </tr:table>
            <tr:spacer width="675px" height="2px"></tr:spacer>
			<tr:table emptyText="No items were found" width="100%" rows="10"
                    value="#{tablaCamposCuadroResumen.modelo}" partialTriggers="tablaBloqueCuadro tablaCamposCuadroResumen:modificarEtiquetaCR"
                    var="tabla"
                    selectedRowKeys="#{tablaCamposCuadroResumen.seleccionadas}"
	                selectionListener="#{tablaCamposCuadroResumen.eventoSeleccion}"
	                sortListener="#{tablaCamposCuadroResumen.eventoOrdenacion}"
					first="#{tablaCamposCuadroResumen.filaInicial}"
					rangeChangeListener="#{tablaCamposCuadroResumen.eventoCambioRango}"
	                rowSelection="single" id="tablaCamposCuadroResumen"
	                autoSubmit="true"
                    >
          	<f:facet name="actions">
	            <tr:panelButtonBar>
	              		<tr:commandButton text="Incluir"  action="#{incluirCampoBloqueCuadroResumen.ejecuta}"/>
	              		<tr:commandButton text="Modificar"
	              			action="#{modificarEtiquetaCampoBloqueCR.ejecuta}"
	                        returnListener="#{modificarEtiquetaCampoBloqueCR.ejecutarRetornoPopUp}"
	                        useWindow="true" windowHeight="560" windowWidth="700"
	                        partialSubmit="true" id="modificarEtiquetaCR"
	              		>
	              				<tr:setActionListener from="#{'Cambiar etiqueta'}" to="#{pageFlowScope.titulo}" />
								<tr:setActionListener from="#{'Etiqueta:'}" to="#{pageFlowScope.etiqueta}" />
								<tr:setActionListener from="#{tablaCamposCuadroResumen.seleccionado.etiqueta}" to="#{pageFlowScope.texto}" />
								<tr:setActionListener from="#{tablaBloqueCuadroResumenBean.seleccionado.identificador}" to="#{modificarEtiquetaCampoEnBloque.idBloqueSeleccionado}" />

	              		</tr:commandButton>
	              		<tr:commandButton text="Excluir"  action="#{excluyeCampoBloqueCR.ejecuta}"/>
	              </tr:panelButtonBar>
            </f:facet>


			<tr:column  headerText="Sangria" width="30px">
            <tr:panelHorizontalLayout halign="center" partialTriggers="tablaCamposCuadroResumen:cambioIndentacionCR1 tablaCamposCuadroResumen:cambioIndentacionCR2">

            <tr:commandLink id="cambioIndentacionCR1" inlineStyle="text-decoration: none"  
            	actionListener="#{cambiosIndentacionCamposEnBloque.reduceIndentacion}">
            	<tr:setActionListener from="#{tabla}"                        to="#{tablaCamposCuadroResumen.seleccionado}"/>
            	<tr:setActionListener from="#{tablaCamposCuadroResumen}"     to="#{cambiosIndentacionCamposEnBloque.tablaCampos}"/>
            	<tr:setActionListener from="#{tablaBloqueCuadroResumenBean}" to="#{cambiosIndentacionCamposEnBloque.tablaBloques}"/>
            	<tr:image source="/contratacion/imagenes/izda.png" />
             </tr:commandLink>
            <tr:spacer width="5"></tr:spacer>
				<tr:outputText value="#{tabla.indentacion}"></tr:outputText>
			<tr:spacer width="5"></tr:spacer>
            <tr:commandLink id="cambioIndentacionCR2"  inlineStyle="text-decoration: none" 
            	actionListener="#{cambiosIndentacionCamposEnBloque.aumentaIndentacion}">
            	<tr:setActionListener from="#{tabla}"                        to="#{tablaCamposCuadroResumen.seleccionado}"/>
            	<tr:setActionListener from="#{tablaCamposCuadroResumen}"     to="#{cambiosIndentacionCamposEnBloque.tablaCampos}"/>
            	<tr:setActionListener from="#{tablaBloqueCuadroResumenBean}" to="#{cambiosIndentacionCamposEnBloque.tablaBloques}"/>
            	<tr:image source="/contratacion/imagenes/dcha.png" />
             </tr:commandLink>
            </tr:panelHorizontalLayout>
            </tr:column>
			<tr:column  headerText="Etiqueta del campo en el bloque">
              <tr:panelHorizontalLayout>
                            <tr:spacer partialTriggers="tablaCamposCuadroResumen:cambioIndentacionCR1 tablaCamposCuadroResumen:cambioIndentacionCR2"
                            width="#{tabla.indentacion * 10}" height="1"></tr:spacer>
              		<tr:outputText value="#{tabla.etiqueta}"/>
              </tr:panelHorizontalLayout>
            </tr:column>
            <tr:column  headerText="Campo">
              <tr:outputText value="#{tabla.descripcion}"/>
            </tr:column>

            
            <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarCampoCR.ejecuta}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{tabla}" to="#{tablaCamposCuadroResumen.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirCampoCR.ejecuta}"
             	>
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{tabla}" to="#{tablaCamposCuadroResumen.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>


          </tr:table>


                  </tr:panelGroupLayout>
                </tr:panelHorizontalLayout>
              </tr:showDetailItem>
              <tr:showDetailItem text="Estructura del anuncio" id="pestana2"
                disclosureListener="#{gestionEstructuraVersionModeloPliegoBean.eventoPestana2}"
                                 disclosed="#{gestionEstructuraVersionModeloPliegoBean.pestana2}">


                    <tr:table emptyText="No existen bloques del anuncio" rows="5"
                              width="100%" var="bloque"
          	 	value="#{tablaBloqueAnuncioBean.modelo}"  id="tablaBloqueAnuncio"
          	 	selectedRowKeys="#{tablaBloqueAnuncioBean.seleccionadas}" autoSubmit="true" rowSelection="single"
          	 	sortListener="#{tablaBloqueAnuncioBean.eventoOrdenacion}"
				first="#{tablaBloqueAnuncioBean.filaInicial}"
				rangeChangeListener="#{tablaBloqueAnuncioBean.eventoCambioRango}"
          	    selectionListener="#{gestionEstructuraVersionModeloPliegoBean.eventoSeleccionBloqueAnuncio}">

            <tr:column  headerText="Bloque">
              <tr:outputText value="#{bloque.nombre}"/>
            </tr:column>
            <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarBloqueAnuncioAccionTabla.ejecuta}"
             			actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciaBloquesAnuncio}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{bloque}" to="#{tablaBloqueAnuncioBean.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirBloqueAnuncioAccionTabla.ejecuta}"
             			actionListener="#{gestionEstructuraVersionModeloPliegoBean.refrescaDependenciaBloquesAnuncio}">
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{bloque}" to="#{tablaBloqueAnuncioBean.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>

            <f:facet name="actions">
            <tr:panelButtonBar>
              <tr:commandButton text="Agregar"  action="#{agregarBloqueAnuncio.ejecuta}"/>
              <tr:commandButton text="Modificar"  action="#{modificarBloqueAnuncio.ejecuta}"/>
                <tr:commandButton  text="Borrar" action="#{borrarBloqueAnuncio.ejecuta}"
                					onclick="if(!confirm('¿Desea borrar el bloque del anuncio seleccionado?')) return false"/>
                        <tr:spacer width="10" height="10"/>

              </tr:panelButtonBar>
            </f:facet>
          </tr:table>
            <tr:spacer width="5px" ></tr:spacer>

 			<tr:table emptyText="No items were found" width="100%" rows="10"
                    value="#{tablaCamposAnuncio.modelo}" partialTriggers="tablaBloqueAnuncio tablaCamposAnuncio:modificarEtiquetaAnuncio"
                    var="tabla" id="tablaCamposAnuncio"
                    selectedRowKeys="#{tablaCamposAnuncio.seleccionadas}"
	                selectionListener="#{tablaCamposAnuncio.eventoSeleccion}"
	                sortListener="#{tablaCamposAnuncio.eventoOrdenacion}"
					first="#{tablaCamposAnuncio.filaInicial}"
					rangeChangeListener="#{tablaCamposAnuncio.eventoCambioRango}"
	                rowSelection="single"
	                autoSubmit="true"
                    >
            <f:facet name="actions">
	            <tr:panelButtonBar>
	              		<tr:commandButton text="Incluir"  action="#{incluirCampoBloqueAnuncio.ejecuta}"/>
	              		<tr:commandButton text="Modificar"
	              			action="#{modificarEtiquetaCampoBloqueAnuncio.ejecuta}"
	                        returnListener="#{modificarEtiquetaCampoBloqueAnuncio.ejecutarRetornoPopUp}"
	                        useWindow="true" windowHeight="560" windowWidth="700"
	                        partialSubmit="true" id="modificarEtiquetaAnuncio"
	              		>
	              				<tr:setActionListener from="#{'Cambiar etiqueta'}" to="#{pageFlowScope.titulo}" />
								<tr:setActionListener from="#{'Etiqueta:'}" to="#{pageFlowScope.etiqueta}" />
								<tr:setActionListener from="#{tablaCamposAnuncio.seleccionado.etiqueta}" to="#{pageFlowScope.texto}" />
								<tr:setActionListener from="#{tablaBloqueAnuncioBean.seleccionado.identificador}" to="#{modificarEtiquetaCampoEnBloque.idBloqueSeleccionado}" />

	              		</tr:commandButton>
	              		<tr:commandButton text="Excluir"  action="#{excluyeCampoBloqueAnuncio.ejecuta}"/>

		              <tr:spacer width="10" height="10"/>

	              </tr:panelButtonBar>
            </f:facet>
            
            <tr:column  headerText="Sangria" width="30px">
            <tr:panelHorizontalLayout halign="center" partialTriggers="tablaCamposAnuncio:cambioIndentacionA1 tablaCamposAnuncio:cambioIndentacionA2">

            <tr:commandLink id="cambioIndentacionA1" inlineStyle="text-decoration: none"  
            	actionListener="#{cambiosIndentacionCamposEnBloque.reduceIndentacion}">
            	<tr:setActionListener from="#{tabla}" to="#{tablaCamposAnuncio.seleccionado}"/>
            	<tr:setActionListener from="#{tablaCamposAnuncio}" to="#{cambiosIndentacionCamposEnBloque.tablaCampos}"/>
            	<tr:setActionListener from="#{tablaBloqueAnuncioBean}" to="#{cambiosIndentacionCamposEnBloque.tablaBloques}"/>
            	<tr:image source="/contratacion/imagenes/izda.png" />
             </tr:commandLink>
            <tr:spacer width="5"></tr:spacer>
				<tr:outputText value="#{tabla.indentacion}"></tr:outputText>
			<tr:spacer width="5"></tr:spacer>
            <tr:commandLink id="cambioIndentacionA2" inlineStyle="text-decoration: none" 
            	actionListener="#{cambiosIndentacionCamposEnBloque.aumentaIndentacion}">
            	<tr:setActionListener from="#{tabla}" to="#{tablaCamposAnuncio.seleccionado}"/>
            	<tr:setActionListener from="#{tablaCamposAnuncio}" to="#{cambiosIndentacionCamposEnBloque.tablaCampos}"/>
            	<tr:setActionListener from="#{tablaBloqueAnuncioBean}" to="#{cambiosIndentacionCamposEnBloque.tablaBloques}"/>
            	<tr:image source="/contratacion/imagenes/dcha.png" />
             </tr:commandLink>
            </tr:panelHorizontalLayout>
            </tr:column>
            
             <tr:column  headerText="Etiqueta del campo en el bloque">
	            <tr:panelHorizontalLayout>
                            <tr:spacer partialTriggers="tablaCamposAnuncio:cambioIndentacionA1 tablaCamposAnuncio:cambioIndentacionA2"
                            width="#{tabla.indentacion * 10}" height="1"></tr:spacer>
              <tr:outputText value="#{tabla.etiqueta}"/>
            </tr:panelHorizontalLayout>
            </tr:column>
            
            <tr:column  headerText="Campo">
               <tr:outputText value="#{tabla.descripcion}"/>
            </tr:column>

           

            <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarCampoAnuncio.ejecuta}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{tabla}" to="#{tablaCamposAnuncio.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirCampoAnuncio.ejecuta}"
             	>
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{tabla}" to="#{tablaCamposAnuncio.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>

          </tr:table>

              </tr:showDetailItem>
              <tr:showDetailItem text="Campos"  id="pestana3" disclosureListener="#{gestionEstructuraVersionModeloPliegoBean.eventoPestana3}"
              												  disclosed="#{gestionEstructuraVersionModeloPliegoBean.pestana3}"
                                 >
               <tr:panelBox background="medium" partialTriggers="detalle  filtroDescripcion filtroIncluidos" inlineStyle="width:100%" text="Criterios de búsqueda" >

				<tr:panelHorizontalLayout>

						<tr:spacer width="40" height="10" />
						<tr:panelLabelAndMessage label="Descripcion" >
									<tr:inputText simple="true" columns="60"
									binding="#{filtroTablaCamposBindingEstructuraBean.filtroDescripcion}"
									id="filtroDescripcion"
									value="#{filtroTablaCamposEstructuraBean.filtroDescripcion}"
									 />
						</tr:panelLabelAndMessage>
						<tr:spacer width="10" height="10" />
						<tr:selectBooleanCheckbox label="Ocultar campos incluidos"
							binding="#{filtroTablaCamposBindingEstructuraBean.ocultarCamposIncluidos}"
							id="filtroIncluidos"
							value="#{filtroTablaCamposEstructuraBean.filtrarIncluidos}"
						></tr:selectBooleanCheckbox>
						<tr:spacer width="40" height="10" />
						<tr:commandButton text="Buscar" action="#{filtroTablaCamposEstructuraBean.filtra}" />

				</tr:panelHorizontalLayout>

                </tr:panelBox>

                  <f:facet name="footer"/>
                  <tr:commandButton text="Modificar estados"
                            action="#{modificarEstadosEditableCampo.ejecuta}"
                            useWindow="true" windowWidth="800" windowHeight="500"
                            launchListener="#{modificarEstadosEditableCampo.parametrosEstadosCampoEditable}"
                            returnListener="#{modificarEstadosEditableCampo.retornaEstadosSeleccionados}"
                            partialSubmit="true" id="botonModificarEstados"
                            >
            		<f:attribute name="accion" value="editarValores"/>
          		  </tr:commandButton>
                  <tr:table emptyText="No items were found" width="100%" rows="10"
                    selectionListener="#{tablaCamposBean.eventoSeleccion}"
                     selectedRowKeys="#{tablaCamposBean.seleccionadas}" rowSelection="single" autoSubmit="true"
                     sortListener="#{tablaCamposBean.eventoOrdenacion}"
					first="#{tablaCamposBean.filaInicial}"
					rangeChangeListener="#{tablaCamposBean.eventoCambioRango}"

                    value="#{tablaCamposBean.modelo}" partialTriggers="botonModificarEstados  filtroDescripcion filtroIncluidos"
                    var="tabla">

            <tr:column  headerText="Descripción" sortable="true" sortProperty="descripcion" >
              <tr:outputText value="#{tabla.descripcion}"/>
            </tr:column>

            <tr:column  headerText="Tipo de dato">
              <tr:outputText value="#{tabla.tipoDato}"/>
            </tr:column>

            <tr:column  headerText="Tipo de control">
              <tr:outputText value="#{tabla.tipoControl}"/>
            </tr:column>

 			<tr:column  headerText="Estados editables">
              <tr:selectOneListbox label="Estados en los que se puede editar"
                                   size="3" partialTriggers="botonModificarEstados"
                                   rendered="#{tabla.pintaTablaEstados}">
                <f:selectItems value="#{tabla.descripcionEstados}"/>
              </tr:selectOneListbox>
            </tr:column>
            <tr:column  headerText="Incluido" noWrap="true" >
            	<tr:panelHorizontalLayout valign="top"  >
              		<tr:iterator rows="25" var="incluido" value="#{tabla.incluidoEn}">
									<tr:panelList>
										<tr:outputText value="#{incluido}" />
									</tr:panelList>
					</tr:iterator>
				</tr:panelHorizontalLayout >
            </tr:column>

            <tr:column headerText="Código" >
              <tr:outputText value="#{tabla.codigo}"/>
            </tr:column>

            <f:facet name="actions">
             <tr:panelButtonBar>
                  <tr:commandButton text="Agregar" action="#{agregarCampo.ejecuta}" />
                  <tr:commandButton text="Modificar" action="#{modificarCampo.ejecuta}"/>
                  <tr:commandButton text="Borrar" action="#{borrarCampo.ejecuta}"
  			onclick="if(!confirm('¿Desea borrar el campo seleccionado?')) return false"/>
               </tr:panelButtonBar>
            </f:facet>
          </tr:table>



              </tr:showDetailItem>
              <tr:showDetailItem text="Reglas de validación" id="pestana4" disclosureListener="#{gestionEstructuraVersionModeloPliegoBean.eventoPestana4}"
                                 disclosed="#{gestionEstructuraVersionModeloPliegoBean.pestana4}" >

            <tr:table emptyText="No se han encontrado reglas de validación de campos" rows="10"
                  var="regla"  width="100%"
                   value="#{tablaReglaValidacionBean.modelo}"
                    selectedRowKeys="#{tablaReglaValidacionBean.seleccionadas}" autoSubmit="true" rowSelection="single"
                   selectionListener="#{tablaReglaValidacionBean.eventoSeleccion}"
                   sortListener="#{tablaReglaValidacionBean.eventoOrdenacion}"
				   first="#{tablaReglaValidacionBean.filaInicial}"
				   rangeChangeListener="#{tablaReglaValidacionBean.eventoCambioRango}"

                    >

            <tr:column sortable="true" headerText="Campo validación"
                       sortProperty="campoValidacion">
              <tr:outputText value="#{regla.campoValidacion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Tipo validación"
                       sortProperty="tipoValidacion">
              <tr:outputText value="#{regla.tipoValidacion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Campo condición"
                       sortProperty="campoCondicion">
              <tr:outputText value="#{regla.campoCondicion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Valor condición"
                       sortProperty="valorCondicion">
              <tr:outputText value="#{regla.valorCondicion}"/>
            </tr:column>
            <tr:column sortable="true" headerText="Estado origen"
                       sortProperty="estadoOrigen">
              <tr:outputText value="#{regla.estadoOrigen}"/>
            </tr:column>

            <f:facet name="actions">
              <tr:panelButtonBar>
              <tr:commandButton text="Agregar"
                                action="#{agregarReglaValidacion.ejecuta}"/>
                                <tr:commandButton text="Modificar"
                                  action="#{modificarReglaValidacion.ejecuta}"/>
                <tr:commandButton text="Ver"
                				  action="#{verReglaValidacion.ejecuta}"/>
                <tr:commandButton text="Borrar"
                                  action="#{borrarReglaValidacion.ejecuta}"
                                  onclick="if(!confirm('¿Desea borrar la regla de validación seleccionada?')) return false"/>

                                  </tr:panelButtonBar>
            </f:facet>
          </tr:table>

              </tr:showDetailItem>

            <!-- Configuracion de Version de Pliego por Tipo de Fichero -->
			<tr:showDetailItem text="Configuración por Tipo de Fichero" id="pest5">
					<tr:table emptyText="No se ha encontrado tipos de ficheros disponibles" var="configuracionTipoFichero" width="100%"
                   value="#{tablaConfiguracionTipoFicheroBean.modelo}"
                   sortListener="#{tablaConfiguracionTipoFicheroBean.eventoOrdenacion}"
					first="#{tablaConfiguracionTipoFicheroBean.filaInicial}"
					rangeChangeListener="#{tablaConfiguracionTipoFicheroBean.eventoCambioRango}"

                   >
						<tr:column  headerText="Seccion">
							<tr:outputText value="#{configuracionTipoFichero.seccion}"/>
						</tr:column>
						<tr:column  headerText="descripcionTipoFichero">
							<tr:outputText value="#{configuracionTipoFichero.descripcionTipoFichero}"/>
						</tr:column>
						<tr:column  headerText="¿configurar?">
							<tr:selectOneChoice id="vinculado" value="#{configuracionTipoFichero.vinculadoAVersionModeloPliego}" autoSubmit="true">
								<f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaElementosSiNo}"/>
							</tr:selectOneChoice>
						</tr:column>
						<tr:column  headerText="historico">
							<tr:selectOneChoice value="#{configuracionTipoFichero.historico}" disabled="#{!configuracionTipoFichero.estamosConfigurandoTipoFichero}" partialTriggers="vinculado">
								<f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaElementosSiNo}"/>
							</tr:selectOneChoice>
						</tr:column>
						<tr:column  headerText="publicable">
							<tr:selectOneChoice value="#{configuracionTipoFichero.publicable}" disabled="#{!configuracionTipoFichero.estamosConfigurandoTipoFichero}" partialTriggers="vinculado">
								<f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaElementosSiNo}"/>
							</tr:selectOneChoice>
						</tr:column>
						<tr:column  headerText="obligatorio">
							<tr:selectOneChoice id="obligatorio" value="#{configuracionTipoFichero.obligatorio}" disabled="#{!configuracionTipoFichero.estamosConfigurandoTipoFichero}" partialTriggers="vinculado" autoSubmit="true">
								<f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaElementosSiNo}"/>
							</tr:selectOneChoice>
						</tr:column>
						<tr:column  headerText="varios">
							<tr:selectOneChoice value="#{configuracionTipoFichero.varios}" disabled="#{!configuracionTipoFichero.estamosConfigurandoTipoFichero}" partialTriggers="vinculado">
								<f:selectItems value="#{gestionEstructuraVersionModeloPliegoBean.listaElementosSiNo}"/>
							</tr:selectOneChoice>
						</tr:column>
						<tr:column  headerText="accion">
							<tr:panelHorizontalLayout halign="center" partialTriggers="vinculado">
								<tr:commandLink text="[ R ]"
									disabled="#{configuracionTipoFichero.anexo?!configuracionTipoFichero.estamosConfigurandoTipoFichero:true}"
									partialTriggers="vinculado"
									action="#{gestionEstructuraVersionModeloPliegoBean.mostrarRangos}"
									useWindow="true"
									launchListener="#{gestionEstructuraVersionModeloPliegoBean.pasarGestionRango}"
					        	    returnListener="#{gestionEstructuraVersionModeloPliegoBean.retornarGestionRango}"
							  		windowHeight="560" windowWidth="700">

								  	<f:param value="#{configuracionTipoFichero.idTipoFichero}" name="idTipoFichero" />

							  	</tr:commandLink>

								<tr:commandLink text="[ E ]"
									disabled="#{!configuracionTipoFichero.obligatorioAdjuntar}"
									partialTriggers="obligatorio"
									action="#{gestionEstructuraVersionModeloPliegoBean.mostrarFicherosEstados}"
									useWindow="true"
									launchListener="#{gestionEstructuraVersionModeloPliegoBean.pasarGestionFicherosEstados}"
					        	    returnListener="#{gestionEstructuraVersionModeloPliegoBean.retornarGestionFicherosEstados}"
							  		windowHeight="560" windowWidth="700">

							  		<f:param value="#{configuracionTipoFichero.idTipoFichero}" name="idTipoFichero" />

							  	</tr:commandLink>

							  </tr:panelHorizontalLayout>
						</tr:column>
						<f:facet name="actions">
							<tr:commandButton text="Guardar Cambios" action="#{gestionEstructuraVersionModeloPliegoBean.guardarCambiosConfiguracion}"/>
						</f:facet>
					</tr:table>
            </tr:showDetailItem>

            <!-- Documentación asociada a la Versión de modelo Pliego  -->
            <tr:showDetailItem text="Documentación asociada" id="pestana6" disclosureListener="#{gestionEstructuraVersionModeloPliegoBean.eventoPestana6}"
                                 disclosed="#{gestionEstructuraVersionModeloPliegoBean.pestana6}">
	            	<tr:table emptyText="No se ha encontrado documentación disponible" var="documentacion" width="100%"
                   value="#{tablaDocumentacionBean.modelo}"
                   selectionListener="#{tablaDocumentacionBean.eventoSeleccion}" autoSubmit="true" rowSelection="single"
                     selectedRowKeys="#{tablaDocumentacionBean.seleccionadas}"
                     sortListener="#{tablaDocumentacionBean.eventoOrdenacion}"
					 first="#{tablaDocumentacionBean.filaInicial}"
					 rangeChangeListener="#{tablaDocumentacionBean.eventoCambioRango}"

                     >

	                   	<tr:column sortable="true" headerText="Tipo">
							<tr:outputText value="#{documentacion.tipoFichero}"/>
					 	</tr:column>
					 	<tr:column sortable="true" headerText="descripcion">
							<tr:outputText value="#{documentacion.descripcion}"/>
					 	</tr:column>
					 	<tr:column sortable="true" headerText="nombre">
					 		<tr:commandLink onclick="noBloquearPantalla()" text="#{documentacion.nombre}" actionListener="#{muestraDocumentacion.descargaFichero}">
								<f:param value="#{documentacion.id}" name="id"/>
								<f:param value="#{documentacion.nombre}" name="nombreFichero"/>
							</tr:commandLink>
					 	</tr:column>
					 	 <tr:column  headerText="" width="30px">
             <tr:panelHorizontalLayout halign="center" >
             	<tr:commandLink action="#{bajarDocumentacion.ejecuta}"
             	>
             		 <tr:image source="/imagenes/flecha_abajo.gif" />
             		<tr:setActionListener from="#{documentacion}" to="#{tablaDocumentacionBean.seleccionado}"/>
             	</tr:commandLink>
             	<tr:commandLink  action="#{subirDocumentacion.ejecuta}"
             	>
             		<tr:image source="/imagenes/flecha_arriba.gif" />
             	    <tr:setActionListener from="#{documentacion}" to="#{tablaDocumentacionBean.seleccionado}"/>
             	</tr:commandLink>
              </tr:panelHorizontalLayout>
            </tr:column>

					 	<f:facet name="actions">
                                                <tr:panelButtonBar>
                                                      <tr:commandButton text="Agregar" action="#{agregarDocumentacion.ejecuta}"/>
                                                        <tr:commandButton text="Modificar" action="#{modificarDocumentacion.ejecuta}"/>
                                                    <tr:commandButton text="Borrar" action="#{borrarDocumentacion.ejecuta}"
                                                                                        onclick="if(!confirm('¿Desea borrar el documento asociado?')) return false"/>
                                       				<tr:spacer width="10" height="10"/>

                                                </tr:panelButtonBar>
			            </f:facet>
	               </tr:table>
            </tr:showDetailItem>

            </tr:panelTabbed>
          </tr:panelBox>
          <tr:separator/>
           <tr:panelHorizontalLayout halign="center">
            <tr:commandButton text="Volver"
                             action="#{gestionEstructuraVersionModeloPliegoBean.cancelar}" />

          </tr:panelHorizontalLayout>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
