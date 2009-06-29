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
		<f:loadBundle basename="mensajesOrg" var="resOrg" />

		<trh:html>
		<trh:head title="#{resCat['GESTION_ARTICULOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="stylesheet" type="text/css"
				href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />
			<trh:script source="/include/libreriaGEOS.js">
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

			<tr:outputText escape="false" value="#{htmlHead}" />
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form id="formArticulo" onsubmit="bloquearPantalla(this);">

				<tr:panelPage partialTriggers="rechazarSolicitud">

					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<tr:panelBox inlineStyle="width: 100%;"
						text="#{resCat['DEFINICION_ARTICULO']}">

						<tr:panelHorizontalLayout halign="end">
							<tr:commandLink
								action="#{catalogo_definirArticuloCatalogoBean.siguienteArticulo(-1)}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarArticulosAnterior}">
								<tr:image source="../imagenes/izda3.png"
									shortDesc="#{resCore['ANTERIOR']}" />
							</tr:commandLink>
							<tr:commandLink
								action="#{catalogo_definirArticuloCatalogoBean.siguienteArticulo(1)}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarArticulosPosterior}">
								<tr:image source="../imagenes/dcha3.png"
									shortDesc="#{resCore['POSTERIOR']}" />
							</tr:commandLink>
						</tr:panelHorizontalLayout>

						<tr:panelHorizontalLayout
							rendered="#{catalogo_definirArticuloCatalogoBean.mostrarPanelParalelo}"
							inlineStyle="width: 100%; background-color: #9b0000; font-weight: bolder; font-size: 140%; color: white; text-align: center;">
							<tr:outputText value="#{resCat['CATALOGO_PARALELO']}" />
						</tr:panelHorizontalLayout>
						<tr:panelHorizontalLayout>
							<tr:panelFormLayout>
								<tr:panelBox text="#{resCat['CLASIFICACION']}"
									inlineStyle="width: 100%;"
									partialTriggers="agregarClasificacion editarClasificacion borrarClasificacion">
									<tr:panelFormLayout>
										<tr:table emptyText="El artículo no tiene clasificaciones"
											value="#{catalogo_definirArticuloCatalogoBean.listaClasificacionesVista}"
											var="fila" rows="10" rowBandingInterval="1"
											columnBandingInterval="0" rowSelection="single"
											partialTriggers=":botonActivar :enviarSolicitud"
											id="tablaClasificaciones">
											<!--											<f:facet name="selection">-->
											<!--												<tr:tableSelectOne autoSubmit="true" >-->
											<!--												</tr:tableSelectOne>-->
											<!--											</f:facet>-->
											<tr:column sortable="false" headerText="Clasificacion">
												<tr:outputText
													value="#{fila.descripcionArticuloClasificacion}" />
											</tr:column>
											<tr:column sortable="false" headerText="#{resCat['DEFECTO']}"
												partialTriggers=":botonActivar :enviarSolicitud">
												<tr:selectBooleanCheckbox value="#{fila.defecto}"
													disabled="true" />
											</tr:column>
											<tr:column sortable="false"
												headerText="#{resCat['COMENTARIO']}">
												<tr:inputText value="#{fila.comentario}" disabled="true"
													id="comentario" />
											</tr:column>
										</tr:table>
										<tr:panelHorizontalLayout>
											<tr:selectBooleanCheckbox disabled="true"
												selected="#{catalogo_definirArticuloCatalogoBean.cipObligatorio}" />
											<tr:outputText value="#{resCat['PERTENECE_CIP_OBLIGATORIO']}" />
										</tr:panelHorizontalLayout>
										<tr:spacer height="10" />
										<tr:panelHorizontalLayout halign="left">
											<!-- Nota: No usar useWindow=true, porque no van las redirecciones y fallan más cosas -->
											<tr:commandButton text="#{resCor['AGREGAR']}"
												windowHeight="355" windowWidth="600"
												id="agregarClasificacion"
												action="#{catalogo_definirArticuloCatalogoBean.editarClasificacionesArticulo}"
												rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonesClasificaciones}"
												partialTriggers=":botonActivar :enviarSolicitud" />
											<tr:commandButton text="#{resCor['EDITAR']}"
												windowHeight="355" windowWidth="600"
												action="#{catalogo_definirArticuloCatalogoBean.editarClasificacionNavegacion}"
												actionListener="#{catalogo_definirArticuloCatalogoBean.editarClasificacion}"
												rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonesClasificaciones}"
												id="editarClasificacion"
												partialTriggers=":botonActivar :enviarSolicitud" />
											<tr:commandButton text="#{resCor['BORRAR']}"
												actionListener="#{catalogo_definirArticuloCatalogoBean.borrarClasificacion}"
												rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonesClasificaciones}"
												id="borrarClasificacion"
												partialTriggers=":botonActivar :enviarSolicitud" />
										</tr:panelHorizontalLayout>
									</tr:panelFormLayout>
								</tr:panelBox>
								<tr:spacer height="10" />
								<!--								<tr:selectOneChoice value="catalogo_definirArticuloCatalogoBean.codigoNivelArticulo">-->
								<!--								</tr:selectOneChoice>-->
								<tr:spacer height="10" />
								<tr:inputText columns="100"
									value="#{catalogo_definirArticuloCatalogoBean.descripcionArticulo}"
									label="#{resCor['NOMBRE']}" disabled="true" id="nombreArticulo" />
								<tr:spacer height="10" />
								<tr:inputText columns="100" label="#{resCor['DESCRIPCION']}"
									value="#{catalogo_definirArticuloCatalogoBean.descripcionDetalladaArticulo}"
									wrap="off" disabled="true" rows="5" id="descripcionArticulo" />
								<tr:spacer height="10" />
								<tr:inputText columns="100"
									value="#{catalogo_definirArticuloCatalogoBean.nemotecnicoArticulo}"
									label="#{resCat['NEMOTECNICO']}" disabled="true"
									id="nemotecnicoArticulo" />
								<tr:spacer height="10" />
								<tr:inputText
									value="#{catalogo_definirArticuloCatalogoBean.codigoArticulo}"
									label="#{resCat['GENERICO_CENTRO']}" disabled="true"
									rendered="#{!catalogo_definirArticuloCatalogoBean.articuloConsulta.generico}"
									id="codigoArticulo" />
								<tr:spacer height="10" />
								<tr:inputText columns="100"
									rendered="#{catalogo_definirArticuloCatalogoBean.mostrarEstado}"
									value="#{catalogo_definirArticuloCatalogoBean.estadoArticulo}"
									label="#{resCat['ESTADO_ARTICULO']}" disabled="true"
									id="estadoArticulo" />
								<tr:spacer height="10" />
								<tr:inputText
									value="#{catalogo_definirArticuloCatalogoBean.fechaAltaArticulo}"
									label="#{resCat['FECHA_ALTA']}" disabled="true"
									id="fechaAltaArticulo">
									<f:convertDateTime timeStyle="short" />
								</tr:inputText>
								<tr:spacer height="10" />
								<tr:inputText columns="100" rows="3"
									value="#{catalogo_definirArticuloCatalogoBean.observacionesArticulo}"
									label="#{resCor['OBSERVACIONES']}" disabled="true"
									id="observacionesArticulo" />
								<tr:spacer height="10" />
								<tr:inputText id="unidadMedidaArticulo" columns="100"
									value="#{catalogo_definirArticuloCatalogoBean.unidadMedidaArticulo}"
									label="#{resCat['UNIDAD_MEDIDA_LOGISTICA']}" disabled="true" />
								<tr:spacer height="10" />
								<tr:inputText id="unidadContratacionArticulo" columns="100"
									value="#{catalogo_definirArticuloCatalogoBean.unidadContratacionArticulo}"
									label="#{resCat['UNIDAD_CONTRATACION']}" disabled="true" />
								<tr:spacer height="10" />
								<tr:inputText columns="100" label="#{resCat['ORGANO_GESTOR']}"
									value="#{catalogo_definirArticuloCatalogoBean.organoGestor}"
									disabled="true" id="organoGestorArticulo" />
								<tr:spacer height="10" />

								<tr:inputText columns="100" label="#{resCat['ESTADOCERTIFICA']}"
									value="#{catalogo_definirArticuloCatalogoBean.estadoCertifica}"
									disabled="true" id="estadoCertificaArticulo" />
								<tr:spacer height="10" />

								<tr:spacer height="10" />
								<tr:panelHorizontalLayout>
									<tr:commandButton text="#{resCor['EDITAR']}"
										action="#{catalogo_definirArticuloCatalogoBean.editarDatosArticulo}"
										rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonesEdicion}"
										id="editar" />
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCat['INCLUIR']}"
										action="#{catalogo_definirArticuloCatalogoBean.incluirArticuloEnOrganoGestor}"
										rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonIncluir}"
										id="incluir" />
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCat['DESASOCIAR']}"
										action="#{catalogo_definirArticuloCatalogoBean.desasociarDeMiCentro}"
										rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonDesasociar}"
										id="desasociar" />
									<tr:spacer width="10" />
									<tr:commandButton text="#{resCat['VER_ATRIBUTOS']}"
										action="#{catalogo_definirArticuloCatalogoBean.verAtributos}"
										id="verAtributos" />
									<tr:spacer width="10" />
									<!--									<tr:commandButton text="#{resCat['SOLICITAR_INCLUIR']}"/>-->
								</tr:panelHorizontalLayout>
							</tr:panelFormLayout>
						</tr:panelHorizontalLayout>
						<tr:spacer height="10" />
						<tr:panelBox text="#{resCat['ATRIBUTOS_MEDIDAS']}"
							inlineStyle="width: 100%;"
							rendered="#{catalogo_definirArticuloCatalogoBean.mostrarPanelSeleccionables}">
							<tr:panelHorizontalLayout halign="left">
								<tr:commandButton text="#{resCor['EDITAR']}"
									rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonSeleccionables}"
									action="#{catalogo_definirArticuloCatalogoBean.editarAtributosSeleccionables}"
									id="editarAtributosMedidas" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:iterator
								value="#{catalogo_definirArticuloCatalogoBean.listaSeleccionablesVista}"
								var="grupo" id="iteratorAtributoMedidas">
								<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
									value="#{grupo.listaAtributosVista}" var="var1" rows="0"
									id="tablaAtributos">
									<tr:column sortable="false" headerText="">
										<tr:outputText value="#{var1.nombre}"
											inlineStyle="font-weight:bold;" />
									</tr:column>
									<tr:column sortable="false" headerText="">
										<tr:outputText value="#{var1.valor}" />
									</tr:column>
									<f:facet name="header">
										<tr:outputText value="#{grupo.nombre}" />
									</f:facet>
								</tr:table>
							</tr:iterator>
						</tr:panelBox>
						<tr:spacer height="10" />
						<tr:panelBox text="#{resCat['DATOS_ARTICULO_CLASIFICACION']}"
							inlineStyle="width: 100%;"
							rendered="#{catalogo_definirArticuloCatalogoBean.datosClasificacion}">
							<tr:commandButton text="#{resCor['EDITAR']}"
								action="#{catalogo_definirArticuloCatalogoBean.editarArticulo}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonesEdicionDatosClasificacion}"
								id="editarArticuloClasificacion" />
							<tr:spacer height="10" />
							<tr:iterator
								value="#{catalogo_definirArticuloCatalogoBean.listaBloquesVistaArticulo}"
								var="grupo" id="iteratorDatosArticulosClasificacion">
								<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
									value="#{grupo.listaAtributosVista}" var="var1" rows="0"
									id="tablaDatosArticulosClasificacion">
									<tr:column sortable="false" headerText="">
										<tr:outputText value="#{var1.nombre}"
											inlineStyle="font-weight:bold;" />
									</tr:column>
									<tr:column sortable="false" headerText="">
										<tr:outputText value="#{var1.valor}" />
									</tr:column>
									<f:facet name="header">
										<tr:outputText value="#{grupo.nombre}" />
									</f:facet>
								</tr:table>
							</tr:iterator>
						</tr:panelBox>
						<tr:spacer height="10" />
						<tr:panelBox
							text="#{catalogo_definirArticuloCatalogoBean.textoDatosCentro}"
							inlineStyle="width: 100%;"
							rendered="#{catalogo_definirArticuloCatalogoBean.datosCentro}">
							<tr:panelHorizontalLayout halign="left">
								<tr:commandButton text="#{resCor['EDITAR']}"
									rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonEditarAtributosCentro}"
									action="#{catalogo_definirArticuloCatalogoBean.editarOrganoGestor}"
									id="editarDatosCentro" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelFormLayout maxColumns="3" rows="1"
								inlineStyle="vertical-align:top;">
								<tr:iterator
									value="#{catalogo_definirArticuloCatalogoBean.listaBloquesDatosCentros}"
									var="grupo" id="iteratorDatosCentro">
									<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
										value="#{grupo.listaAtributosVista}" var="var1" rows="0"
										id="tablaDatosCentro">
										<tr:column sortable="false" headerText="">
											<tr:outputText value="#{var1.nombre}"
												inlineStyle="font-weight:bold;" />
										</tr:column>
										<tr:column sortable="false" headerText="">
											<tr:outputText value="#{var1.valor}" />
										</tr:column>
										<f:facet name="header">
											<tr:outputText value="#{grupo.nombre}" />
										</f:facet>
									</tr:table>
								</tr:iterator>
							</tr:panelFormLayout>
						</tr:panelBox>
						<tr:spacer height="10" />
						<tr:panelBox text="#{resCat['ASOCIADOS_AL_ALMACEN']}"
							inlineStyle="width: 100%;"
							rendered="#{catalogo_definirArticuloCatalogoBean.datosAlmacen}">
							<tr:panelFormLayout maxColumns="3" rows="1"
								inlineStyle="vertical-align:top;">
								<!--              <tr:forEach items="#{catalogo_definirArticuloCatalogoBean.listaAlmacenesVista}"-->
								<!--                          var="grupo" varStatus="status">-->
								<tr:commandButton text="#{resCat['ANIADIR_ALMACEN']}"
									action="#{catalogo_definirArticuloCatalogoBean.aniadirAlmacen}"
									rendered="#{catalogo_definirArticuloCatalogoBean.renderizarBotonesEdicion}"
									id="aniadirAlmacen">
								</tr:commandButton>
								<tr:iterator
									value="#{catalogo_definirArticuloCatalogoBean.listaAlmacenesVista}"
									var="grupo" varStatus="status" id="iteratorAsociarAlmacenes">
									<tr:commandButton text="#{resCor['EDITAR']}"
										shortDesc="#{status.index}"
										actionListener="#{catalogo_definirArticuloCatalogoBean.obtieneAlmacenSeleccionado}"
										action="#{catalogo_definirArticuloCatalogoBean.editarAlmacen}"
										rendered="#{catalogo_definirArticuloCatalogoBean.renderizarBotonesEdicion}"
										id="editarAlmacen">
									</tr:commandButton>
									<tr:commandButton text="#{resCat['ELIMINAR_ALMACEN']}"
										shortDesc="#{status.index}"
										actionListener="#{catalogo_definirArticuloCatalogoBean.eliminarAlmacen}"
										rendered="#{catalogo_definirArticuloCatalogoBean.renderizarBotonesEdicion}"
										id="eliminarAlmacen">
									</tr:commandButton>
									<tr:table value="#{grupo.listaBloquesVista}" var="var"
										inlineStyle="vertical-align:middle;" rows="0"
										id="listaBloquesVista">
										<tr:column inlineStyle="vertical-align:middle;">
											<f:facet name="header">
												<tr:outputText value="#{grupo.nombre}" />
											</f:facet>
											<tr:outputText value="#{var.nombre}" />
										</tr:column>
										<f:facet name="detailStamp">
											<tr:table emptyText="#{resCor['NO_ELEMENTOS']}"
												value="#{var.listaAtributosVista}" var="var1" width="100%"
												rows="0">
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.nombre}"
														inlineStyle="font-weight:bold;" />
												</tr:column>
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.valor}" />
												</tr:column>
												<!--                      <f:facet name="header">
                        <tr:outputText value="#{var.nombreBloque}"/>
                      </f:facet>-->
											</tr:table>
										</f:facet>
									</tr:table>
									<!--               </tr:forEach>-->
								</tr:iterator>
							</tr:panelFormLayout>
						</tr:panelBox>
						<tr:spacer width="10" />
						<tr:panelHorizontalLayout halign="left"
							partialTriggers="botonActivar enviarSolicitud">
							<tr:spacer width="10" />
							<tr:commandButton text="#{resCat['ACTIVAR']}" id="botonActivar"
								action="#{catalogo_definirArticuloCatalogoBean.validarYActivarArticulo}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonActivar}"
								returnListener="#{catalogo_definirArticuloCatalogoBean.retornarPopUpSeleccionClasificacionPorDefecto}"
								useWindow="true" windowWidth="500" windowHeight="400"
								partialTriggers="botonActivar" />
							<tr:spacer width="10" />
							<tr:commandButton text="#{resOrg['ENVIAR_SOLICITUD']}"
								action="#{catalogo_definirArticuloCatalogoBean.enviarSolicitud}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonEnviarSolicitud}"
								returnListener="#{catalogo_definirArticuloCatalogoBean.retornarPopUpSeleccionClasificacionPorDefecto}"
								useWindow="true" windowHeight="600" windowWidth="800"
								id="enviarSolicitud" partialTriggers="enviarSolicitud" />
							<tr:spacer width="10" height="0" />
							<tr:commandButton
								id="especificarConAtributosPaginaDefinirArticuloCatalogoBean"
								text="#{resCat['ESPECIFICAR_CON_ATRIBUTOS']}"
								action="#{catalogo_definirArticuloCatalogoBean.crearEspecifico}"
								shortDesc="#{resCat['MENSAJE_INFORMACION_ESPECIFICAR_CON_ATRIBUTOS']}"
								rendered="#{catalogo_definirArticuloCatalogoBean.especificar}" />
							<tr:spacer width="10" height="10" />
							<!-- 
							<tr:commandButton text="#{resCat['ACCESIBILIDAD']}"
								action="#{catalogo_definirArticuloCatalogoBean.bloquearArticulo}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonBloquear}" 
								id="botonBloquear"/>
							<tr:spacer width="10" />
							-->
							<tr:commandLink id="mostrarDocsAsociados"
								rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1] != null}"
								shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1]}"
								actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
								action="dialog:mostrarDocumentacion" useWindow="true"
								windowWidth="600" windowHeight="300">
								<f:attribute name="entidadMostrada" value="1" />
								<tr:image source="/imagenes/info_20.png" />
							</tr:commandLink>
							<tr:commandButton text="#{resCor['VOLVER']}"
								action="#{catalogo_definirArticuloCatalogoBean.volver}"
								id="botonVolver" />
							<tr:spacer width="10" height="10" />
							<tr:commandButton
								text="#{resCat['MOSTRAR_HISTORICO_ARTICULOS_BOTON']}"
								action="#{catalogo_definirArticuloCatalogoBean.mostrarHistoricoArticulo}"
								id="botonHistoricoArticulo" useWindow="true" windowHeight="450"
								windowWidth="650" partialSubmit="true" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
					<f:subview id="detallesSolicitud" rendered="#{catalogo_definirArticuloCatalogoBean.solicitud != null}">
						<jsp:include page="/core/detallesSolicitud.jsp" />
					</f:subview>
					<tr:panelHorizontalLayout rendered="#{catalogo_definirArticuloCatalogoBean.solicitud != null}" partialTriggers="rechazarSolicitud">
						<tr:commandButton text="#{resCor['ACEPTAR_SOLICITUD']}"
							action="#{catalogo_definirArticuloCatalogoBean.aceptarSolicitud}"
							rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonAceptarSolicitud}"
							useWindow="true" windowHeight="600" windowWidth="800"
							returnListener="#{catalogo_definirArticuloCatalogoBean.retornarPopUpSeleccionClasificacionPorDefecto}"
							id="aceptarSolicitud" />
						<tr:commandButton text="#{resCor['RECHAZAR_SOLICITUD']}"
							action="#{catalogo_definirArticuloCatalogoBean.rechazarSolicitud}"
							returnListener="#{catalogo_definirArticuloCatalogoBean.returnRechazarSolicitud}"
							useWindow="true" windowHeight="600" windowWidth="800"
							rendered="#{catalogo_definirArticuloCatalogoBean.mostrarBotonRechazarSolicitud}"
							id="rechazarSolicitud" />
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}" />
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>

