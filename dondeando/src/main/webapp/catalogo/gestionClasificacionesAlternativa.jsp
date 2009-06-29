<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html>
		<trh:head title="#{resCat['SELECCION_CLASIFICACION']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />			
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />				
		
			<tr:outputText escape="false" value="#{htmlHead}"/>
		
		</trh:head>
		<trh:body initialFocusId="inputBuscarPorCodigo">
						
			<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
			
			<!-- Cabecerá estándar de las páginas SIGLO -->		
			<tr:panelHorizontalLayout rendered="#{!catalogo_gestionClasificacionesAlternativa.mostrarComoPopUp}" inlineStyle="width:100%;">
				<geos:cabeceraPagina agregarCapaEspera="false"/>      	
			</tr:panelHorizontalLayout>
			
				<tr:panelPage>
					<tr:panelHeader
						text="#{catalogo_gestionClasificacionesAlternativa.obtenerTituloPagina}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox inlineStyle="width: 100%;">
							<tr:form defaultCommand="buscarPorCodigo" id="formClasificaciones" onsubmit="bloquearPantalla(this);">								
									<trh:tableLayout cellSpacing="5" cellPadding="0" partialTriggers="buscarPorCodigo iteradorCombos:comboCambiante comboClasificacion"
										borderWidth="0" inlineStyle="width:100%;">
										<trh:rowLayout>
											<trh:cellFormat />
											<trh:cellFormat />
											<trh:cellFormat />
											<trh:cellFormat />
										</trh:rowLayout>
										<trh:rowLayout>
											<trh:cellFormat halign="left">
												<tr:commandLink id="indiceCatalogo"
													text="#{resCat['INDICE_CATALOGO']}"
													action="#{catalogo_gestionClasificacionesAlternativa.mostrarIndiceCatalogo}"/>
											</trh:cellFormat>
											<trh:cellFormat halign="right">
												<tr:commandLink id="avisoLegal"
													text="#{resCat['AVISO_LEGAL']}" action="dialog:avisoLegal"
													useWindow="true" windowWidth="500" windowHeight="200" 
													/>
											</trh:cellFormat>																						
										</trh:rowLayout>
										<trh:rowLayout><tr:spacer width="10" /></trh:rowLayout>
										<trh:rowLayout><tr:spacer width="10" /></trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="Código" />
											<trh:cellFormat>
												<tr:panelHorizontalLayout halign="left">
													<tr:inputText id="inputBuscarPorCodigo" columns="100"
														value="#{catalogo_gestionClasificacionesAlternativa.codigoManual}" />
													<tr:commandButton id="buscarPorCodigo"
														text="#{resCat['BUSCAR_POR_CODIGO']}"
														action="#{catalogo_gestionClasificacionesAlternativa.buscarPorCodigo}" 
														partialSubmit="true"
														blocking="true"/>
												</tr:panelHorizontalLayout>
											</trh:cellFormat>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText
												rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarClasificacionesSeleccionadas}"
												value="#{resCat['CLASIFICACIONES_SELECCIONADAS']}" />
											<trh:cellFormat>
												<tr:inputText wrap="off" disabled="true" rows="5"
													columns="100"
													rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarClasificacionesSeleccionadas}"
													value="#{catalogo_gestionClasificacionesAlternativa.clasificacionesSeleccionadas}" 
													id="clasificacionSeleccionada"/>
											</trh:cellFormat>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['CLASIFICACION']}"
												rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarSelectorClasificaciones}" />
											<trh:cellFormat>
												<tr:selectOneChoice id="comboClasificacion"
													value="#{catalogo_gestionClasificacionesAlternativa.valorClasificacion}"
													valueChangeListener="#{catalogo_gestionClasificacionesAlternativa.cambiaClasificacion}"
													autoSubmit="true"
													rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarSelectorClasificaciones}">
													<f:selectItems
														value="#{catalogo_gestionClasificacionesAlternativa.selectItemClasificacion}" />
												</tr:selectOneChoice>
											</trh:cellFormat>
										</trh:rowLayout>
										<tr:iterator var="item" varStatus="varStatus" id="iteradorCombos"
											value="#{catalogo_gestionClasificacionesAlternativa.listaNivelConSelectItem}">
											<trh:rowLayout>
												<tr:outputText value="#{item.descripcionNivel}" />
												<trh:cellFormat>
													<tr:selectOneChoice value="#{item.nivelValor}" id="comboCambiante"
														valueChangeListener="#{catalogo_gestionClasificacionesAlternativa.cambiaNivel}"
														autoSubmit="true">
														<f:attribute name="nivelConSelectItemSeleccionado" value="#{item}"/>
<!--														onchange="bloquearPantalla()">-->
														<f:selectItems value="#{item.valores}" />
													</tr:selectOneChoice>
												</trh:cellFormat>
											</trh:rowLayout>
										</tr:iterator>										
										<trh:rowLayout>
											<trh:cellFormat columnSpan="3">
												<tr:panelHorizontalLayout halign="center">
													<tr:commandLink
														action="#{catalogo_gestionClasificacionesAlternativa.seleccionarClasificacion}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.renderizarSeleccionMultiple}"
														id="seleccionarClasificacion">
														<tr:image source="../imagenes/abajo.gif" />
													</tr:commandLink>
													<tr:spacer width="10" />
													<tr:commandLink
														action="#{catalogo_gestionClasificacionesAlternativa.eliminarSeleccionClasificacion}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.renderizarSeleccionMultiple}"
														id="eliminarSeleccionClasificacion">
														<tr:image source="../imagenes/arriba.gif" />
													</tr:commandLink>
													<tr:spacer width="10" />
													<tr:commandLink
														action="#{catalogo_gestionClasificacionesAlternativa.eliminarTodaSeleccionClasificacion}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.renderizarSeleccionMultiple}"
														id="eliminarTodaSeleccionClasificacion">
														<tr:image source="../imagenes/arriba2.gif" />
													</tr:commandLink>
												</tr:panelHorizontalLayout>
												<tr:spacer height="10" />
												<tr:panelHorizontalLayout halign="center">
													<tr:selectManyListbox
														value="#{catalogo_gestionClasificacionesAlternativa.listaSeleccionSelectorSeleccionadas}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.renderizarSeleccionMultiple}"
														id="listaSeleccionSelectorSeleccionadas">
														<f:selectItems
															value="#{catalogo_gestionClasificacionesAlternativa.selectorClasificacionesSeleccionadas}" />
													</tr:selectManyListbox>
												</tr:panelHorizontalLayout>
												<tr:spacer height="10" />												
												<tr:panelHorizontalLayout halign="center">
													<tr:commandButton text="#{resCor['ACEPTAR']}"
														action="#{catalogo_gestionClasificacionesAlternativa.aceptar}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarSelectorClasificaciones}"
														id="aceptar" />
													<tr:spacer width="10" />
													<tr:commandButton text="#{resCor['CANCELAR']}"
														action="#{catalogo_gestionClasificacionesAlternativa.cancelar}" 
														id="cancelar"/>
													<tr:spacer width="10" />
													<tr:commandButton text="#{resCat['ACEPTAR_Y_SEGUIR']}"
														action="#{catalogo_gestionClasificacionesAlternativa.aceptarYSeguir}"
														rendered="#{catalogo_gestionClasificacionesAlternativa.mostrarAceptarYSeguir}" 
														id="aceptarYSeguir"/>
												</tr:panelHorizontalLayout>
											</trh:cellFormat>
										</trh:rowLayout>
									</trh:tableLayout>
							</tr:form>
					</tr:panelBox>
				</tr:panelPage>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;" rendered="#{!catalogo_gestionClasificacionesAlternativa.mostrarComoPopUp}">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
