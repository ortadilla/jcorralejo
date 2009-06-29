<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
	xmlns:trh="http://myfaces.apache.org/trinidad/html"
	xmlns:geos="http://www.hp-cda.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=ISO-8859-15" />
	<f:view>
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<!-- Se añaden aquí todos los mensajes para las traducciones automáticas de la 
    	tabla de resultados -->
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesEmp" var="resEmp" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesOfe" var="resOfe" />
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesNec" var="resNec" />
		<f:loadBundle basename="mensajesCon" var="resCon" />
		<f:loadBundle basename="mensajesAcr" var="resAcr" />

		<trh:html>
		<trh:head
			title="#{core_mtoGenericoEditar.objRespaldo.titulo}">
			<meta http-equiv="Content-Type"
				content="text/html;charset=ISO-8859-15" />
			
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<link rel="stylesheet" type="text/css" href="/geos/skins/hojiblanca/hojiblancaEditarPrint.css" media="print" />
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
			
		</trh:head>
		<trh:body
			initialFocusId="#{core_mtoGenericoEditar.objRespaldo.focoInicial}">
			
			<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
<!--			<tr:statusIndicator id="indicador">-->
<!--				<f:facet name="busy">-->
<!--					<f:verbatim>-->
<!--							<div id="divEspera">-->
<!--								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>-->
<!--							</div>-->
<!--					</f:verbatim>-->
<!--				</f:facet>-->
<!--				<f:facet name="ready">-->
<!--				</f:facet>-->
<!--			</tr:statusIndicator>-->
			
			<!-- Cabecerá estándar de las páginas SIGLO -->			
			<geos:cabeceraPagina />
			
			<tr:form defaultCommand="botonBuscar" usesUpload="true" id="formMtoGenerico" onsubmit="bloquearPantalla(this);">
				<tr:panelPage inlineStyle="width: 100%;">
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader text="#{core_mtoGenericoEditar.objRespaldo.titulo}" />
					<!-- Esto es temporal. Habrá que modificarlo apropiadamente. -->
					<tr:panelBox rendered="#{core_mtoGenericoEditar.objRespaldo.mostrarPadre}"
								 background="light" inlineStyle="width: 100%;">
              			<!--  Este trozo muestra los padres como una miga de pan -->
						<tr:panelHorizontalLayout halign="center"
							rendered="#{core_mtoGenericoEditar.objRespaldo.servicioMantenimiento.mostrarPadresComoMiga}">
							<tr:iterator var="vs" varStatus="estado"
								value="#{core_mtoGenericoEditar.objRespaldo.listaPadres}">
								<tr:commandLink text="#{vs.descripcionCompleta}"
									action="#{core_mtoGenericoEditar.seleccionarPadre(estado.index)}"
									shortDesc="#{resCore['CAMBIAR']}" />
								<tr:outputText rendered="#{estado.index != (estado.model.rowCount-1)}" value=" >> " />
							</tr:iterator>
						</tr:panelHorizontalLayout>
						
						<!-- Este trozo muestra los padres en forma de texto y con un botón -->
						<tr:panelHorizontalLayout halign="center"
							rendered="#{!core_mtoGenericoEditar.objRespaldo.servicioMantenimiento.mostrarPadresComoMiga}">
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
								<tr:iterator var="vs" varStatus="estado"
									value="#{core_mtoGenericoEditar.objRespaldo.listaPadres}">
									<trh:rowLayout>
										<trh:cellFormat>
											<tr:outputText value="#{vs.descripcionCompleta}" />
										</trh:cellFormat>
										<trh:cellFormat>
											<tr:commandButton text="#{resCore['CAMBIAR']}"
												action="#{core_mtoGenericoEditar.seleccionarPadre(estado.index)}" 
												id="cambiar"/>
										</trh:cellFormat>
									</trh:rowLayout>
								</tr:iterator>
							</trh:tableLayout>
						</tr:panelHorizontalLayout>
					</tr:panelBox>

					<!-- Panel para los editar los detalles de la entidad o para agregar nuevas entidades -->
					<tr:panelBox text="#{core_mtoGenericoEditar.objRespaldo.tituloPanel}"
		 						 background="light"
		 						 rendered="#{core_mtoGenericoEditar.objRespaldo.mostrarCamposEdicion}"
								 inlineStyle="width: 100%;" contentStyle="width: 100%;">
					   
					   <tr:panelHorizontalLayout rendered="#{core_mtoGenericoEditar.objRespaldo.tituloResaltado != null}"
					   				inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 140%; color: white; text-align: center;">
						   <tr:outputText value="#{core_mtoGenericoEditar.objRespaldo.tituloResaltado}"/>
					   </tr:panelHorizontalLayout>

						<tr:spacer width="5" height="5" />						
						
						<tr:panelBox id="panelCampos"
							rendered="#{core_mtoGenericoEditar.objRespaldo.mostrarCamposEdicion}"
							binding="#{core_mtoGenericoEditarBinding.panelCampos}"
							inlineStyle="width: 100%;" />
						<!-- Campos -->
						<tr:spacer height="10" />
						<tr:panelHorizontalLayout halign="center"
							inlineStyle="width: 100%;">
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCore['ACEPTAR']}" id="botonAceptar"
									accessKey="#{resCore['ACEPTAR_TA']}"
									action="#{core_mtoGenericoEditar.accionAceptar}" 
									rendered="#{core_mtoGenericoEditar.objRespaldo.tipoOperacion!='VER_DETALLES'}"/>
								<tr:commandButton text="#{core_mtoGenericoEditar.objRespaldo.tipoOperacion!='VER_DETALLES' ? resCore['CANCELAR'] : resCore['VOLVER']}"
									id="botonCancelar" accessKey="#{resCore['CANCELAR_TA']}"
									action="#{core_mtoGenericoEditar.accionCancelar}" 
									onclick="document.getElementById('formMtoGenerico').reset();" /> <!-- Se llama al reset() del form para evitar la validación -->
								<tr:spacer width="10" height="10" />
								<tr:commandLink id="mostrarDocsAsociados"
												rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[core_mtoGenericoEditar.objRespaldo.entidad.id] != null}"
                                                shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[core_mtoGenericoEditar.objRespaldo.entidad.id]}"
												actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
												action="dialog:mostrarDocumentacion"
												useWindow="true" windowWidth="600" windowHeight="300">
									<f:attribute name="entidadMostrada" value="#{core_mtoGenericoEditar.objRespaldo.entidad.id}"/>
									<tr:image source="/imagenes/info_20.png"/>
								</tr:commandLink>		
							</tr:panelHorizontalLayout>
						</tr:panelHorizontalLayout>
						<tr:spacer height="10" />
						<tr:panelHorizontalLayout id="panelBotonesExtrasFormulario"
							halign="center"
							binding="#{core_mtoGenericoEditarBinding.panelBotonesExtras}" />
					</tr:panelBox>

					<!--  Panel para la descripción corta de la entidad al navegar por las colecciones -->
					<tr:panelBox
						text="#{core_mtoGenericoEditar.objRespaldo.tituloPanel}"
						rendered="#{!core_mtoGenericoEditar.objRespaldo.mostrarCamposEdicion}"
						background="light" inlineStyle="width: 100%;">
						
						<tr:panelHorizontalLayout rendered="#{core_mtoGenericoEditar.objRespaldo.tituloResaltado != null}"
					   				inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 140%; color: white; text-align: center;">
						   <tr:outputText value="#{core_mtoGenericoEditar.objRespaldo.tituloResaltado}"/>
					   </tr:panelHorizontalLayout>

						<tr:panelHorizontalLayout halign="center">
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
								<tr:iterator var="vs" varStatus="estado"
									value="#{core_mtoGenericoEditar.objRespaldo.descripcionCompletaResumen}">
									<trh:rowLayout>
										<tr:outputText value="#{vs}"
											inlineStyle="font-weight: bolder; font-size: 120%;" />
									</trh:rowLayout>
								</tr:iterator>
							</trh:tableLayout>
						</tr:panelHorizontalLayout>

						<tr:spacer height="10" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['DETALLES']}" id="botonEditar"
								accessKey="#{resCore['DETALLES_TA']}"
								actionListener="#{core_mtoGenericoEditar.accionEditarFormulario}"
								action="#{core_mtoGenericoEditar.getAccion}" />
							<tr:commandButton text="#{resCore['VOLVER']}" id="botonVolver"
								accessKey="#{resCore['VOLVER_TA']}"
								action="#{core_mtoGenericoEditar.accionCancelar}" />
						</tr:panelHorizontalLayout>

						<tr:spacer height="20" />
						<!-- Panel para los botones que mostrarán las colecciones asociadas a la entidad -->
						<!--
						<tr:panelHorizontalLayout halign="center" id="panelBotones"
							binding="#{core_mtoGenericoEditarBinding.panelBotones}" />
						-->
					</tr:panelBox>

					<tr:spacer width="10" height="10" />

					<!-- Pestañas con las colecciones asociadas a la entidad -->
					<tr:navigationPane id="pestaniasTabla" hint="tabs"
						               binding="#{core_mtoGenericoEditarBinding.pestaniasTabla}"
						               rendered="#{core_mtoGenericoEditar.objRespaldo.mostrarTabla}"/>

					<tr:panelBox
						text="#{core_mtoGenericoEditar.objRespaldo.tituloTabla}"
						background="light"
						rendered="#{core_mtoGenericoEditar.objRespaldo.mostrarTabla}"
						inlineStyle="width: 100%;" id="contenedorBotones">
						
						<!--  Panel para los botones con las acciones de mantenimiento de los elementos de la tabla. -->
						<tr:panelFormLayout id="panelBotonesTabla"
											binding="#{core_mtoGenericoEditarBinding.panelBotonesTabla}" />
<!--						<tr:panelHorizontalLayout id="panelBotonesTabla" halign="left"-->
<!--							binding="#{core_mtoGenericoEditarBinding.panelBotonesTabla}" />-->
						<tr:spacer height="5" />
						<tr:table emptyText="#{resCore['NO_ELEMENTOS']}"
							binding="#{core_mtoGenericoEditarBinding.tabla}"
							id="tablaResultados" var="fila" rows="20" rowBandingInterval="1"
							columnBandingInterval="0"
							first="#{core_mtoGenericoEditar.objRespaldo.primeraFila}"
							rangeChangeListener="#{core_mtoGenericoEditar.listenerEventoCambioRango}"
							selectedRowKeys="#{core_mtoGenericoEditar.objRespaldo.estadoDeSeleccion}"
							value="#{core_mtoGenericoEditar.objRespaldo.listaResultados}"
							sortListener="#{core_mtoGenericoEditar.columnOrderListener}"
							width="100%" rowSelection="multiple">

						</tr:table>
					</tr:panelBox>

					<!-- OJO: Cte. definida en NombresBean del core -->
					<!-- Sólo mostraremos el panel con los detalles de la solicitud si estamos editando la entidad, no si estamos
					     navegando por sus colecciones. Esto implicaría tener que procesar las solicitudes para ver cuáles corresponden
					     a la colección que se está mostrando -->
		            <f:subview id="detallesSolicitud" rendered="#{core_solicitud_detalles != null and core_mtoGenericoEditar.objRespaldo.mostrarCamposEdicion}"> 
						<jsp:include page="/core/detallesSolicitud.jsp" />
					</f:subview>

					<!-- El siguiente spacer es sólo para que se lance un evento al final de la página -->
					<tr:spacer width="1" height="1" 
					           binding="#{core_mtoGenericoEditarBinding.spacerFinal}"/>
				</tr:panelPage>
			</tr:form>
			
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
			
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
