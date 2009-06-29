<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces"
          xmlns:c="http://java.sun.com/jsp/jstl/core">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <f:loadBundle basename="mensajesCore" var="resCore"/>
    <!-- Se añaden aquí todos los mensajes para las traducciones automáticas de la 
    	tabla de resultados -->    
    <f:loadBundle basename="mensajesCat" var="resCat"/>
    <f:loadBundle basename="mensajesEmp" var="resEmp"/>
    <f:loadBundle basename="mensajesOrg" var="resOrg"/>
    <f:loadBundle basename="mensajesOfe" var="resOfe"/>
    <f:loadBundle basename="mensajesPed" var="resPed"/>
    <f:loadBundle basename="mensajesNec" var="resNec"/>
    <f:loadBundle basename="mensajesCon" var="resCon"/>
    <f:loadBundle basename="mensajesAcr" var="resAcr"/>
    
    <trh:html>
      <trh:head title="#{core_metaMtoGenerico.objRespaldo.titulo}">
        <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-15"/>
        <trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
		</trh:script>
		
		<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />				
		
		<tr:outputText escape="false" value="#{htmlHead}"/>
			
      </trh:head>
      <trh:body initialFocusId="#{core_metaMtoGenerico.objRespaldo.focoInicial}" onload="_refrescarSelectMultiples();">
      			
		<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
<!--		<tr:statusIndicator id="indicador">-->
<!--			<f:facet name="busy">-->
<!--				<f:verbatim>-->
<!--						<div id="divEspera">-->
<!--							<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>-->
<!--						</div>-->
<!--				</f:verbatim>-->
<!--			</f:facet>-->
<!--			<f:facet name="ready">-->
<!--			</f:facet>-->
<!--		</tr:statusIndicator>-->
		
<!--		<tr:panelHorizontalLayout inlineStyle="width: 100%;">-->
<!--			<tr:outputText escape="false" value="#{htmlCabecera}"/>-->
<!--		</tr:panelHorizontalLayout>-->
						
		<!-- Cabecerá estándar de las páginas SIGLO -->		
      	<geos:cabeceraPagina/>      	
      	
        <tr:form defaultCommand="botonBuscar" id="formMtoGenerico" onsubmit="bloquearPantalla(this);"> 	        
				<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>			
					<tr:panelHeader text="#{core_metaMtoGenerico.objRespaldo.titulo}"/>
	
					<tr:showDetailHeader id="contenedorDesplegable"
											 text="#{resCore['CRITERIOS_BUSQUEDA']}" 
											 disclosed="#{core_metaMtoGenerico.objRespaldo.desplegado}"
											 disclosureListener="#{core_metaMtoGenerico.listenerDesplegadoCriterios}"
											 binding="#{core_mtoGenericoBinding.detailHeader}">

						<tr:panelBox background="medium" inlineStyle="width: 100%;" contentStyle="width: 100%;">
							<geos:botonesGuardarBusqueda mostrarBotonGuardar="true" />
							
							<tr:panelHorizontalLayout id="panelCriterios"
												binding="#{core_mtoGenericoBinding.panelCriterios}"
												inlineStyle="width: 100%; "/>									
								<tr:spacer width="10" height="10" />
								<!-- Campos -->
							<tr:panelHorizontalLayout halign="center">
											<tr:commandButton text="#{resCore['BUSCAR']}"
												id="botonBuscar"
												blocking="true"
												actionListener="#{core_metaMtoGenerico.accionBuscar}" 
												action=""/>
											<tr:commandButton text="#{resCore['LIMPIAR']}"
												id="botonLimpiar"
												blocking="true"
												actionListener="#{core_metaMtoGenerico.accionLimpiar}" 
												action=""/>
											<tr:spacer width="10" height="10" />
											<tr:commandLink id="mostrarDocsAsociados"
            												rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[core_metaMtoGenerico.objRespaldo.entidad.id] != null}"
                                                            shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[core_metaMtoGenerico.objRespaldo.entidad.id]}"
            												actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
            												action="dialog:mostrarDocumentacion"
            												useWindow="true" windowWidth="600" windowHeight="300">
												<f:attribute name="entidadMostrada" value="#{core_metaMtoGenerico.objRespaldo.entidad.id}"/>
												<tr:image source="/imagenes/info_20.png"/>
											</tr:commandLink>		
							</tr:panelHorizontalLayout>			
							
						</tr:panelBox>
					</tr:showDetailHeader>
					
					<tr:spacer width="10" height="10" />
					<tr:panelBox
						text="#{core_metaMtoGenerico.objRespaldo.tituloTabla}"
						background="medium"
						inlineStyle="width: 100%;"
						contentStyle="width: 100%;"
						partialTriggers="botonBuscar tablaResultados arbolResultados arbolResultadosSolicitudes">
						
						<tr:spacer height="5" width="10"/>
				  		<tr:panelFormLayout id="panelBotones" binding="#{core_mtoGenericoBinding.panelBotones}" />					
						<tr:spacer height="5" width="10"/>		
						<tr:table id="tablaResultados"
                            value="#{core_metaMtoGenerico.objRespaldo.listaResultados}"
                            rendered="#{!core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbol and !core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbolTrasSolicitudes}"
                            emptyText="#{core_metaMtoGenerico.textoTablaResSinElementos}"
							partialTriggers="tablaResultados ::botonBuscar"
							var="fila" rows="20"
							first="#{core_metaMtoGenerico.objRespaldo.primeraFila}"
							rangeChangeListener="#{core_metaMtoGenerico.listenerEventoCambioRango}"
							rowBandingInterval="1"
	                        columnBandingInterval="0"
							selectedRowKeys="#{core_metaMtoGenerico.objRespaldo.estadoDeSeleccion}"
							binding="#{core_mtoGenericoBinding.tabla}"
                            sortListener="#{core_metaMtoGenerico.columnOrderListener}"
                            rowSelection="multiple" width="100%">

                            <f:facet name="actions">
                                <tr:panelHorizontalLayout>
                                    <tr:commandLink id="descargarExcel" action="#{core_metaMtoGenerico.accionGenerarExcel}" 
                                                    onclick="noBloquearPantalla()">
                                        <tr:image source="/imagenes/excel16.gif" shortDesc="#{resCore['EXPORTAR_EXCEL']}"/>
                                    </tr:commandLink>
                                    <tr:spacer width="8" height="8" />
                                    <tr:commandLink id="botonVerArbol" actionListener="#{core_metaMtoGenerico.accionVerArbol}"
                                                    rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
                                        <tr:image source="/imagenes/arbol.gif" shortDesc="#{resCore['VER_ARBOL']}"/>
                                    </tr:commandLink>
                                    <tr:spacer width="8" height="8" />
                                    <tr:commandLink id="botonVerArbolTrasSolicitud" actionListener="#{core_metaMtoGenerico.accionVerArbolSolicitudes}"
                                    				rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
                                        <tr:image source="/imagenes/arbol_solic.gif" shortDesc="Comparar árbol con solicitudes."/>
                                    </tr:commandLink>
                                </tr:panelHorizontalLayout>
                            </f:facet>
						</tr:table>
                        <tr:treeTable id="arbolResultados"
                            value="#{core_metaMtoGenerico.objRespaldo.datosArbol.arbol}"
                            rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbol}"
                            binding="#{core_mtoGenericoBinding.arbol}"
                            emptyText="#{core_metaMtoGenerico.textoTablaResSinElementos}"
                            selectedRowKeys="#{core_metaMtoGenerico.objRespaldo.datosArbol.estadoDeSeleccion}"
                            rowSelection="multiple"
                            rootNodeRendered="false"
                            var="fila" partialTriggers="arbolResultados ::botonBuscar"
                            rowBandingInterval="0" initiallyExpanded="true" width="100%">
                            <f:facet name="actions">
                                <tr:panelHorizontalLayout>
                                    <tr:commandLink id="botonVerTabla" actionListener="#{core_metaMtoGenerico.accionVerTabla}"
                                                    rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
                                        <tr:image source="/imagenes/tabla.gif" shortDesc="#{resCore['VER_TABLA']}" />
                                    </tr:commandLink>
                                    <tr:spacer width="8" height="8" />
                                    <tr:commandLink id="botonVerArbolTrasSolicitud" actionListener="#{core_metaMtoGenerico.accionVerArbolSolicitudes}"
                                    				rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
                                        <tr:image source="/imagenes/arbol_solic.gif" shortDesc="Comparar árbol con solicitudes"/>
                                    </tr:commandLink>
                                </tr:panelHorizontalLayout>
                            </f:facet>
                            <f:facet name="nodeStamp">
                                <tr:column styleClass="#{fila.estiloNodo}">
                                    <f:facet name="header">
                                        <tr:outputText value="Descripción" />
                                    </f:facet>
                                    <tr:outputText value="#{fila.obj.descripcion}" />
                                </tr:column>
                            </f:facet>
                            <f:facet name="footer">
                            	<tr:outputText value="* Las filas que aparecen en blanco no se encontraban entre los resultados de búsqueda." />
                            </f:facet>
                        </tr:treeTable>
                        <!--<tr:panelHorizontalLayout 
                        		rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbol}"
                        		inlineStyle="padding: 5px; color: red;">
							<tr:outputText value="* Las filas que aparecen en blanco no se encontraban entre los resultados de búsqueda." />
						</tr:panelHorizontalLayout>						-->
                       <!-- <tr:treeTable id="arbolResultadosSolicitudes"
                            value="#{core_metaMtoGenerico.objRespaldo.datosArbol.arbolTrasSolicitudes}"
                            rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbol
                                    and core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbolTrasSolicitudes}"
                            binding="#{core_mtoGenericoBinding.arbolTrasSolicitudes}"
                            emptyText="#{core_metaMtoGenerico.textoTablaResSinElementos}"
                            rootNodeRendered="false"
                            var="fila" partialTriggers="arbolResultadosSolicitudes ::botonBuscar"
                            initiallyExpanded="true" 
                            width="100%">
                            <f:facet name="nodeStamp">
                                <tr:column>
                                    <f:facet name="header">
                                        <tr:outputText value="Descripción" />
                                    </f:facet>
                                    <tr:outputText value="#{fila.obj.descripcion}" />
                                </tr:column>
                            </f:facet>
                            <tr:column>
                                <f:facet name="header">
                                    <tr:outputText value="Id elemento" />
                                </f:facet>
                                <tr:outputText value="#{fila.obj.id}" />
                            </tr:column>
                        </tr:treeTable> -->
                        <tr:panelHorizontalLayout styleClass="fondoBotonVerTabla" rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbolTrasSolicitudes}">
	                        <tr:panelHorizontalLayout halign="left">
	                        	<tr:commandLink id="botonVerTabla" actionListener="#{core_metaMtoGenerico.accionVerTabla}"
	                                            rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
	                            	<tr:image source="/imagenes/tabla.gif" shortDesc="#{resCore['VER_TABLA']}" />
	                            </tr:commandLink>
	                            <tr:spacer width="8" height="8" />
	                            <tr:commandLink id="botonVerArbol" actionListener="#{core_metaMtoGenerico.accionVerArbol}"
	                                            rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarIconoArbol}">
	                                <tr:image source="/imagenes/arbol.gif" shortDesc="#{resCore['VER_ARBOL']}"/>
	                            </tr:commandLink>
	                        </tr:panelHorizontalLayout>
                        </tr:panelHorizontalLayout>
                        <trh:tableLayout styleClass="contenedorArbolesSolicitudes"                        
                        	rendered="#{core_metaMtoGenerico.objRespaldo.datosArbol.mostrarArbolTrasSolicitudes}">
                        	<trh:rowLayout>
                        		<tr:outputText value="Estructura actual" styleClass="tituloArbolSolic"/>
                        		<tr:outputText value="Estructura solicitada" styleClass="tituloArbolSolic"/>                        		
                        	</trh:rowLayout>
                        	<trh:rowLayout valign="top">
                        		<trh:cellFormat valign="top">
		                            <tr:tree var="nodo" value="#{core_metaMtoGenerico.objRespaldo.datosArbol.arbol}"
			                                 binding="#{core_mtoGenericoBinding.arbolAntesDeSolicitudes}">
										  <f:facet name="nodeStamp">
										    <tr:outputText value="#{nodo.obj.descripcion}"/>
										  </f:facet>
			                        </tr:tree>
		                        </trh:cellFormat>
                        		<trh:cellFormat valign="top">		                        
			                        <tr:tree var="nodo" value="#{core_metaMtoGenerico.objRespaldo.datosArbol.arbolTrasSolicitudes}"
			                                 binding="#{core_mtoGenericoBinding.arbolTrasSolicitudes}" >
										  <f:facet name="nodeStamp">
										  	<tr:panelHorizontalLayout>
											    <tr:outputText value="#{nodo.obj.descripcion}" styleClass="#{nodo.estiloNodo}"/>
												<tr:image rendered="#{nodo.srcImagenSol!=null}" source="#{nodo.srcImagenSol}"/>
											</tr:panelHorizontalLayout>
										  </f:facet>
			                        </tr:tree>
		                        </trh:cellFormat>
	                        </trh:rowLayout>
	                        <trh:rowLayout>
	                        	<trh:cellFormat columnSpan="2">
	                        		<tr:outputText value="* Solicitudes pendientes de Confirmar   " styleClass="estiloNodoArbolPendConfirmar"/>
	                        		<tr:outputText value="* Solicitudes pendientes de Validar   " styleClass="estiloNodoArbolPendValidar"/>
	                        		<tr:outputText value="* Solicitudes pendientes de Modificar   " styleClass="estiloNodoArbolPendModificar"/>	                        			                        		
	                        	</trh:cellFormat>
	                        </trh:rowLayout>
                        </trh:tableLayout>
                        <tr:commandButton text="#{resCore['ACEPTAR_SELECCION']}"
							rendered="#{core_metaMtoGenerico.objRespaldo.mostrarAceptarSeleccion}"
							action="#{core_metaMtoGenerico.accionAceptar}" 
							id="aceptarSeleccion"/>
						<tr:commandButton text="#{resCore['CANCELAR']}"
							rendered="#{core_metaMtoGenerico.objRespaldo.mostrarAceptarSeleccion}"
							action="#{core_metaMtoGenerico.accionCancelar}" 
							id="cancelarSeleccion"/>
						<tr:commandButton text="#{resCore['VOLVER']}"
							rendered="#{core_metaMtoGenerico.objRespaldo.mostrarVolver}"
							action="#{core_metaMtoGenerico.accionCancelar}" 
							id="volverSeleccion"/>
					</tr:panelBox>
					
					<!-- El siguiente spacer es sólo para que se lance un evento al final de la página -->
					<tr:spacer width="1" height="1" 
					           binding="#{core_mtoGenericoBinding.spacerFinal}"/>
					           
				</tr:panelPage>
			</tr:form>
				
				<tr:panelHorizontalLayout inlineStyle="width: 100%;">
					<tr:outputText escape="false" value="#{htmlPie}"/>
				</tr:panelHorizontalLayout>
			
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>
