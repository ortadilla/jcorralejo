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
		<f:loadBundle basename="mensajesCat" var="mensajesCat" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
		<trh:html>
		<trh:head title="#{mensajesCat['CONSULTAR_ARTICULOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
				<link rel="stylesheet" type="text/css"
					href="/geos/skins/hojiblanca/hojiblancaPrint.css" media="print" />
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>

			<tr:outputText escape="false" value="#{htmlHead}" />

		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="btnBuscar" id="formArticulo"
				onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader
						text="#{catalogo_consultarArticuloBean.tituloPagina}" />
					<tr:showDetailHeader text="#{mensajesCore['CRITERIOS_BUSQUEDA']}"
						binding="#{catalogo_consultarArticuloBinding.busqueda}"
						disclosed="#{catalogo_consultarArticuloBean.desplegado}"
						id="showDetail" partialTriggers="showDetail">
						<tr:panelBox inlineStyle="width:100%;" background="medium">
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0"
								partialTriggers="borrarClasificacion">
								<trh:rowLayout>
									<trh:cellFormat />
									<trh:cellFormat />
									<trh:cellFormat />
									<trh:cellFormat />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['CLASIFICACION']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:inputText wrap="off" columns="110" disabled="true"
												value="#{catalogo_consultarArticuloBean.valorTodasClasificaciones}"
												partialTriggers="condicionSituacion borrarClasificacion"
												id="clasificacionArticulo" />
											<tr:commandLink
												action="#{catalogo_consultarArticuloBean.accionBoton }">
												<!--												returnListener="#{catalogo_consultarArticuloBean.retornarPopUpSeleccionClasificacion}">-->
												<tr:image styleClass="imagenLupa"
													source="/imagenes/lupa3.gif" />
											</tr:commandLink>
											<tr:commandLink id="borrarClasificacion"
												actionListener="#{catalogo_consultarArticuloBean.limpiarClasificacion}"
												partialSubmit="true">
												<tr:image styleClass="imagenGoma"
													source="/imagenes/btnEliminar.gif" />
											</tr:commandLink>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['GENERICO_CENTRO']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:inputText
												value="#{catalogo_consultarArticuloBean.codigoArticulo}"
												columns="6" maximumLength="6" id="codigoArticulo" />
											<tr:spacer width="20" height="10" />
											<tr:outputText value="#{mensajesCat['NEMOTECNICO']}" />
											<tr:spacer width="20" height="10" />
											<tr:inputText
												value="#{catalogo_consultarArticuloBean.nemotecnicoArticulo}"
												columns="80" id="nemotecnicoArticulo" />
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCore['NOMBRE']}" />
									<trh:cellFormat columnSpan="3">
										<tr:inputText
											value="#{catalogo_consultarArticuloBean.descripcionArticulo}"
											rows="1" columns="110" id="nombreArticulo" />
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText
										value="#{mensajesCat['CREADO']} #{mensajesCore['DESDE']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:inputDate
												value="#{catalogo_consultarArticuloBean.fechaAltaArticulo}"
												id="fechaAltaDesdeArticulo" />
											<tr:spacer width="20" height="10" />
											<tr:outputText value="#{mensajesCore['HASTA']}" />
											<tr:spacer width="20" height="10" />
											<tr:inputDate
												value="#{catalogo_consultarArticuloBean.fechaAltaArticuloHasta}"
												id="fechaAltaHastaArticulo" />
											<tr:spacer width="20" height="10" />
											<tr:outputText
												value="#{mensajesCat['MODIFICADO']} #{mensajesCore['DESDE']}" />
											<tr:spacer width="20" height="10" />
											<tr:panelHorizontalLayout>
												<tr:inputDate
													value="#{catalogo_consultarArticuloBean.fechaModificacionArticulo}"
													id="fechaModificacionDesdeArticulo" />
												<tr:spacer width="20" height="10" />
												<tr:outputText value="#{mensajesCore['HASTA']}" />
												<tr:spacer width="20" height="10" />
												<tr:inputDate
													value="#{catalogo_consultarArticuloBean.fechaModificacionArticuloHasta}"
													id="fechaModificacionHastaArticulo" />
											</tr:panelHorizontalLayout>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout
									rendered="#{catalogo_consultarArticuloBean.mostrarAsocCentro}">
									<tr:outputText value="#{mensajesOrg['ASOC_CENTRO']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:selectBooleanCheckbox
												valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAlCentro}"
												id="asociadosAlCentroSi"
												value="#{catalogo_consultarArticuloBean.asociadoAlCentroSi}"
												autoSubmit="true" immediate="false"
												partialTriggers="asociadosAlCentroNo"
												disabled="#{catalogo_consultarArticuloBean.desabilitarAsociadoAlCentroSi}" />
											<tr:outputText value="#{mensajesCore['SI']}" />
											<tr:selectBooleanCheckbox
												value="#{catalogo_consultarArticuloBean.asociadoAlCentroNo}"
												id="asociadosAlCentroNo"
												valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAlCentro}"
												autoSubmit="true" partialTriggers="asociadosAlCentroSi"
												disabled="#{catalogo_consultarArticuloBean.desabilitarAsociadoAlCentroNo}" />
											<tr:outputText value="#{mensajesCore['NO']}" />
											<tr:spacer width="10" height="10" />
											<tr:selectOneChoice
												value="#{catalogo_consultarArticuloBean.organoGestor}"
												id="comboOrganoGestor"
												disabled="#{catalogo_consultarArticuloBean.deshabilitarCentros}"
												partialTriggers="asociadosAlCentroSi asociadosAlCentroNo">
												<f:selectItems
													value="#{catalogo_consultarArticuloBean.selectorOrganoGestor}"
													id="selectorOrganoGestor" />
											</tr:selectOneChoice>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout
									rendered="#{catalogo_consultarArticuloBean.mostrarAsocAlmacen}">
									<tr:outputText value="#{mensajesCat['ASOC_ALMACEN']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:selectBooleanCheckbox
												value="#{catalogo_consultarArticuloBean.asociadoAlAlmacenSi}"
												valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAlAlmacen}"
												autoSubmit="true" id="asociadosAlAlmacenSi"
												partialTriggers="asociadosAlAlmacenNo" />
											<tr:outputText value="#{mensajesCore['SI']}" />
											<tr:selectBooleanCheckbox
												value="#{catalogo_consultarArticuloBean.asociadoAlAlmacenNo}"
												valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAlAlmacen}"
												autoSubmit="true" id="asociadosAlAlmacenNo"
												partialTriggers="asociadosAlAlmacenSi" />
											<tr:outputText value="#{mensajesCore['NO']}" />
											<tr:spacer width="10" height="10" />
											<tr:selectOneChoice id="comboAlmacen"
												value="#{catalogo_consultarArticuloBean.almacen}"
												disabled="#{catalogo_consultarArticuloBean.deshabilitarAlmacenes}"
												partialTriggers="asociadosAlAlmacenSi asociadosAlAlmacenNo">
												<f:selectItems
													value="#{catalogo_consultarArticuloBean.selectorAlmacen}"
													id="selectorAlmacen" />
											</tr:selectOneChoice>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText
										value="#{(catalogo_consultarArticuloBean.mostrarConsultarGenerico)?
															mensajesCat['GENERICO_CENTROS']:mensajesCat['ZONA_CATALOGO_SAS']}"
										rendered="#{catalogo_consultarArticuloBean.mostrarConsultarGenerico || 
															catalogo_consultarArticuloBean.mostrarZonaCatalogoSAS}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:panelHorizontalLayout
												rendered="#{catalogo_consultarArticuloBean.mostrarConsultarGenerico}">
												<tr:selectBooleanCheckbox
													value="#{catalogo_consultarArticuloBean.genericoSi}"
													valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAGenerico}"
													autoSubmit="true" id="genericoSi"
													partialTriggers="genericoNo"
													disabled="#{catalogo_consultarArticuloBean.muestraCheckGenerico}" />
												<tr:outputText value="#{mensajesCore['SI']}" />
												<tr:selectBooleanCheckbox
													value="#{catalogo_consultarArticuloBean.genericoNo}"
													valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAGenerico}"
													autoSubmit="true" id="genericoNo"
													partialTriggers="genericoSi"
													disabled="#{catalogo_consultarArticuloBean.muestraCheckGenerico}" />
												<tr:outputText value="#{mensajesCore['NO']}" />
												<tr:spacer width="20" height="10" />
												<tr:outputText value="#{mensajesCat['ZONA_CATALOGO_SAS']}"
													rendered="#{catalogo_consultarArticuloBean.mostrarZonaCatalogoSAS}" />
											</tr:panelHorizontalLayout>
											<tr:panelHorizontalLayout
												rendered="#{catalogo_consultarArticuloBean.mostrarZonaCatalogoSAS}">
												<tr:selectBooleanCheckbox id="zonaCatalogoSASSi"
													partialTriggers="zonaCatalogoSASNo" autoSubmit="true"
													value="#{catalogo_consultarArticuloBean.zonaCatalogoSASSi}"
													disabled="#{catalogo_consultarArticuloBean.deshabilitaZonaCatalogoSASSi}"
													valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAZonaCatalogoSAS}" />
												<tr:outputText value="#{mensajesCore['SI']}" />
												<tr:selectBooleanCheckbox id="zonaCatalogoSASNo"
													partialTriggers="zonaCatalogoSASSi" autoSubmit="true"
													value="#{catalogo_consultarArticuloBean.zonaCatalogoSASNo}"
													disabled="#{catalogo_consultarArticuloBean.deshabilitaZonaCatalogoSASNo}"
													valueChangeListener="#{catalogo_consultarArticuloBean.cambiarCheckAsociadoAZonaCatalogoSAS}" />
												<tr:outputText value="#{mensajesCore['NO']}" />
											</tr:panelHorizontalLayout>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout
									rendered="#{catalogo_consultarArticuloBean.mostrarConsAtributosSeleccionables}">
									<tr:outputText value="#{mensajesCat['ATRIBUTOS_MEDIDAS']}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout>
											<tr:selectBooleanCheckbox id="atributosMedidaSi"
												value="#{catalogo_consultarArticuloBean.atributosMedidaSi}" />
											<tr:outputText value="#{mensajesCore['SI']}" />
											<tr:selectBooleanCheckbox id="atributosMedidaNo"
												value="#{catalogo_consultarArticuloBean.atributosMedidaNo}" />
											<tr:outputText value="#{mensajesCore['NO']}" />
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['CATALOGO']}"
										rendered="#{catalogo_consultarArticuloBean.mostrarSituacionCatalogo}" />
									<trh:cellFormat columnSpan="3">
										<tr:panelHorizontalLayout
											rendered="#{catalogo_consultarArticuloBean.mostrarSituacionCatalogo}">
											<tr:selectOneRadio id="condicionSituacion"
												value="#{catalogo_consultarArticuloBean.situacionCatalogo}"
												autoSubmit="true"
												valueChangeListener="#{catalogo_consultarArticuloBean.changeListenerSituacion}">
												<f:selectItem itemLabel="#{mensajesCat['REAL']}"
													itemValue="REAL" />
												<f:selectItem itemLabel="#{mensajesCat['PARALELO']}"
													itemValue="PARALELO" />
											</tr:selectOneRadio>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['ESTADO_ARTICULO']}" />
									<tr:selectManyListbox
										rendered="#{catalogo_consultarArticuloBean.mostrarEstado}"
										size="5" value="#{catalogo_consultarArticuloBean.estados}"
										disabled="#{catalogo_consultarArticuloBean.deshabilitaEstado}"
										id="comboEstadoArticulo">
										<f:selectItems
											value="#{catalogo_consultarArticuloBean.selectorEstadoArticulo}"
											id="selectorEstadoArticulo" />
									</tr:selectManyListbox>
									<tr:selectManyListbox size="5"
										value="#{catalogo_consultarArticuloBean.estadosCertificacion}"
										disabled="#{catalogo_consultarArticuloBean.deshabilitaEstadoCerti}"
										id="comboEstadoCertifica">
										<f:selectItems
											value="#{catalogo_consultarArticuloBean.selectorEstadoCertifica}"
											id="selectorEstadoCertifica" />
									</tr:selectManyListbox>
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:panelHorizontalLayout inlineStyle="width:100%;">
								<tr:commandLink id="indiceCatalogo"
									text="#{mensajesCat['INDICE_CATALOGO']}"
									action="#{catalogo_consultarArticuloBean.mostrarIndiceCatalogo}" />
								<tr:commandLink id="avisoLegal"
									text="#{mensajesCat['AVISO_LEGAL']}" action="dialog:avisoLegal"
									useWindow="true" windowWidth="500" windowHeight="200" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="0" height="5" />
							<tr:showDetailHeader inlineStyle="width:100%;"
								id="componenteAtributosDinamicos"
								text="Criterios por Clasificación (Atributos Dinámicos)"
								disclosed="#{catalogo_consultarArticuloBean.desplegadoPanelAtributos}"
								binding="#{catalogo_consultarArticuloBinding.desplegablePanelAtributos}"
								rendered="#{catalogo_consultarArticuloBean.clasificacion!=null}"
								partialTriggers="componenteAtributosDinamicos">
								<tr:panelHorizontalLayout>
									<tr:panelFormLayout
										binding="#{catalogo_consultarArticuloBinding.panelAtributosDinamicos}" />
								</tr:panelHorizontalLayout>
							</tr:showDetailHeader>
							<tr:spacer width="0" height="5" />
							<tr:panelHorizontalLayout halign="right">
								<tr:commandButton text="#{mensajesCore['BUSCAR']}"
									id="btnBuscar"
									action="#{catalogo_consultarArticuloBean.realizarConsulta}"
									blocking="true" />
								<tr:spacer width="10" height="0" />
								<tr:commandButton text="#{mensajesCore['LIMPIAR']}"
									action="#{catalogo_consultarArticuloBean.limpiarFormulario}"
									id="btnLimpiar">
									<tr:resetActionListener />
									<tr:resetActionListener />
								</tr:commandButton>
								<tr:spacer width="10" height="10" />
								<tr:commandLink id="mostrarDocsAsociados"
									rendered="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1] != null}"
									shortDesc="#{organizacion_seleccionarDocumentoPerfilBean.entidadConDoc[1]}"
									actionListener="#{organizacion_seleccionarDocumentoPerfilBean.guardarEntidad}"
									action="dialog:mostrarDocumentacion" useWindow="true"
									windowWidth="600" windowHeight="300">
									<f:attribute name="entidadMostrada" value="1" />
									<tr:image source="/imagenes/info_20.png" />
								</tr:commandLink>
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:showDetailHeader>
					<tr:spacer width="10" height="20" />
					<tr:panelBox inlineStyle="width:100%;" text="Resultados">
						<tr:panelGroupLayout>
							<tr:panelButtonBar>
								<tr:commandButton text="#{mensajesCore['AGREGAR']}"
									action="#{catalogo_consultarArticuloBean.agregarArticulo}"
									blocking="true"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonAgregar}"
									id="agregar" />
								<!--									returnListener="#{catalogo_consultarArticuloBean.retornarAgregarArticulo}"-->
								<tr:commandButton text="#{mensajesCore['EDITAR']}"
									action="#{catalogo_consultarArticuloBean.editarArticulo}"
									blocking="true" disabled="false"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonEditar}"
									id="editar" />
								<tr:commandButton text="#{mensajesCore['DETALLES']}"
									action="#{catalogo_consultarArticuloBean.verDetalles}"
									id="detalles" />
								<tr:commandButton text="#{mensajesCore['BORRAR']}"
									action="#{catalogo_consultarArticuloBean.borrarArticulo}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonBorrar}"
									returnListener="#{catalogo_consultarArticuloBean.retornarBorrarArticulo}"
									useWindow="true" windowHeight="450" windowWidth="450"
									id="borrar" />
								<!-- LA ACCESIBILIDAD DE LOS ARTICULOS SE HARÁ DESDE EL MTO DE VALORES DE CLASIFICACIÓN -->
								<!--<tr:commandButton text="#{mensajesCat['ACCESIBILIDAD']}"
									action="#{catalogo_consultarArticuloBean.bloquearArticulo}"
									disabled="false"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonBloquear}"
									id="accesibilidad" />-->
								<tr:spacer width="10" height="0" />
								<tr:commandButton text="#{mensajesCore['SOL_ALTA']}"
									action="#{catalogo_consultarArticuloBean.solicitarAlta}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonSolicitarAlta}"
									id="solicitudAlta" />
								<!--									returnListener="#{catalogo_consultarArticuloBean.retornarAgregarArticulo}"-->
								<tr:commandButton text="#{mensajesCore['SOL_MODIF']}"
									useWindow="true"
									action="#{catalogo_consultarArticuloBean.solicitarModificacion}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonSolicitarModificacion}"
									returnListener="#{catalogo_consultarArticuloBean.retornarSolicitarModificacion}"
									id="solicitudModificacion" />
								<tr:commandButton text="#{mensajesCore['SOL_BAJA']}"
									useWindow="true"
									action="#{catalogo_consultarArticuloBean.solicitarBaja}"
									returnListener="#{catalogo_consultarArticuloBean.retornarSolicitarBaja}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonSolicitarBaja}"
									id="solicitudBaja" />
							</tr:panelButtonBar>
							<tr:spacer width="0" height="10" />
							<tr:panelButtonBar>
								<tr:commandButton id="asociarFotos"
									text="#{mensajesCat['FOTOS']}"
									action="#{catalogo_consultarArticuloBean.mostrarAsociarFotosArticulo}"
									blocking="true"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonAsociarFotos}" />
								<tr:commandButton text="#{mensajesOrg['OFERTAS']}"
									action="#{catalogo_consultarArticuloBean.mostrarOfertas}"
									blocking="true"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonOfertas}"
									id="ofertas" />
								<tr:commandButton text="#{mensajesOrg['PRIORIZACION_OFERTAS']}"
									action="#{catalogo_consultarArticuloBean.verDetallesOferta}"
									blocking="true"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonPriOfertas}"
									id="priorizacionOfertas" />
								<tr:spacer width="20" height="0" />
								<tr:commandButton id="especificarConAtributos"
									text="#{mensajesCat['ESPECIFICAR_CON_ATRIBUTOS']}"
									action="#{catalogo_consultarArticuloBean.crearEspecifico}"
									blocking="true"
									shortDesc="#{mensajesCat['MENSAJE_INFORMACION_ESPECIFICAR_CON_ATRIBUTOS']}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonEspecConAtrib}" />
								<tr:commandButton text="#{mensajesCat['INCLUIR']}"
									action="#{catalogo_consultarArticuloBean.incluirArticuloEnOrganoGestor}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonIncluirEnCentro}"
									id="incluir" />
								<tr:commandButton text="#{mensajesCat['DESASOCIAR']}"
									action="#{catalogo_consultarArticuloBean.desasociarDeMiCentro}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonIncluirEnCentro}"
									id="desasociar" />
								<tr:commandButton text="#{mensajesCat['VER_ATRIBUTOS']}"
									action="#{catalogo_consultarArticuloBean.verAtributos}"
									id="verAtributos" />
								<tr:spacer width="20" height="0" />
								<tr:commandButton text="#{mensajesCat['RECUPERAR']}"
									id="recuperar"
									action="#{catalogo_consultarArticuloBean.recuperarArticulo}"
									rendered="#{catalogo_consultarArticuloBean.mostrarBotonRecuperar}"
									useWindow="true" windowHeight="350" windowWidth="450"
									returnListener="#{catalogo_consultarArticuloBean.returnConfirmarRecuperacion}" />
								<tr:spacer width="20" height="0" />
							</tr:panelButtonBar>
						</tr:panelGroupLayout>
						<!--							binding="#{catalogo_consultarArticuloBinding.tablaArticulos}"-->
						<tr:table var="var" first="0"
							emptyText="No hay elementos en la tabla" rows="20" width="100%"
							value="#{catalogo_consultarArticuloBean.listaResultados}"
							rowBandingInterval="1" columnBandingInterval="0"
							selectedRowKeys="#{catalogo_consultarArticuloBean.estadoDeSeleccionTabla}"
							sortListener="#{catalogo_consultarArticuloBean.columnOrderListener}"
							rowSelection="multiple" partialTriggers="::recuperar ::borrar editarEnTabla cancelarEditarEnTabla"
							id="tablaResultados">
							<tr:column rendered="#{catalogo_consultarArticuloBean.mostrarColumnaEditarEnTabla}">
								<tr:panelHorizontalLayout>
									<tr:commandLink id="editarEnTabla" partialSubmit="true"
										actionListener="#{catalogo_consultarArticuloBean.accionListenerEditarEnTabla}">
										<tr:attribute name="idArticulo" value="#{var.identificador}" />									
										<tr:image source="#{(var.identificador ==catalogo_consultarArticuloBean.idFilaSeleccionada and catalogo_consultarArticuloBean.esEditableEnTabla)?'/imagenes/aceptar_16.png':'/imagenes/editar_4_16.png'}"
											shortDesc="#{(var.identificador ==catalogo_consultarArticuloBean.idFilaSeleccionada and catalogo_consultarArticuloBean.esEditableEnTabla)?mensajesCore['ACEPTAR']:mensajesCore['EDITAR']}" >
										</tr:image>
									</tr:commandLink>
									<tr:spacer height="5" width="10"></tr:spacer>
									<tr:commandLink id="cancelarEditarEnTabla" partialSubmit="true"
										actionListener="#{catalogo_consultarArticuloBean.accionListenerCancelarEditarEnTabla}">
										<tr:attribute name="idArticulo" value="#{var.identificador}" />									
										<tr:image source="/imagenes/cancelar_16.png" rendered="#{(var.identificador ==catalogo_consultarArticuloBean.idFilaSeleccionada and catalogo_consultarArticuloBean.esEditableEnTabla)}"
											shortDesc="#{mensajesCore['CANCELAR']}">										
										</tr:image>
									</tr:commandLink>
								</tr:panelHorizontalLayout>
							</tr:column>
							<tr:column headerText="#{mensajesCore['SOLICITIDES_MOV_ABR']}"
								sortable="false" rendered="#{catalogo_consultarArticuloBean.mostrarInformacionSolicitudes}">
								<tr:commandLink
									actionListener="#{catalogo_consultarArticuloBean.accionListenerVerSolicitudes}"
									action="#{catalogo_consultarArticuloBean.accionVerSolicitudes}">
									<tr:attribute name="idArticulo" value="#{var.identificador}" />
									<tr:image rendered="#{var.mostrarIconoSolicitud}"
										source="#{var.iconoSolicitud}"
										shortDesc="#{var.iconoSolicitudDesc}" />
								</tr:commandLink>
							</tr:column>
							<tr:column sortable="false" rendered="#{catalogo_consultarArticuloBean.mostrarInformacionAsociacionOG}">
								<tr:image
									source="#{(var.asociadoAOGActivo) ?'/imagenes/centro.gif':'/imagenes/asterisco.gif'}"
									shortDesc="#{(var.asociadoAOGActivo) ?mensajesCat['ARTICULO_PERTENECE_A_OG']:mensajesCat['ARTICULO_NOPERTENECE_A_OG']}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="clasificacionUniversal"
								headerText="#{mensajesCat['CLASIFICACION_UNIVERSAL']}">
								<tr:outputText value="#{var.clasificacionUniversal}"
									inlineStyle="#{var.colorLetra}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="descripcion"
								headerText="#{mensajesCore['NOMBRE']}">
								<tr:outputText id="descripcion" value="#{var.descripcion}" rendered="#{(var.identificador ==catalogo_consultarArticuloBean.idFilaSeleccionada and catalogo_consultarArticuloBean.esEditableEnTabla)?false:true}"
									inlineStyle="#{var.colorLetra}" />
								<!--									readOnly="#{var.identificador!=catalogo_consultarArticuloBean.idElementoFilaTabla || !catalogo_consultarArticuloBean.filaEditable}" -->
								<tr:inputText id="descripcionInput" value="#{var.nuevaDescripcion}" inlineStyle="#{var.colorLetra}" rendered="#{(var.identificador ==catalogo_consultarArticuloBean.idFilaSeleccionada and catalogo_consultarArticuloBean.esEditableEnTabla)?true:false}" rows="2" columns="40">
								</tr:inputText>
							</tr:column>
							<tr:column sortable="true" sortProperty="nemotecnico"
								headerText="#{mensajesCat['NEMOTECNICO']}">
								<tr:outputText id="nemotecnico" value="#{var.nemotecnico}"
									inlineStyle="#{var.colorLetra}" />
								<!--									readOnly="#{var.identificador!=catalogo_consultarArticuloBean.idElementoFilaTabla || !catalogo_consultarArticuloBean.filaEditable}" -->
							</tr:column>
							<tr:column sortable="true" sortProperty="unidadMedida"
								headerText="#{mensajesCat['UD_MEDIDA']}">
								<tr:outputText value="#{var.unidadMedida}"
									inlineStyle="#{var.colorLetra}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="estado"
								headerText="#{mensajesCat['ESTADO']}">
								<tr:outputText value="#{var.estado}"
									inlineStyle="#{var.colorLetra}" />
							</tr:column>
							<tr:column sortable="true" sortProperty="codigoSiglo"
								headerText="#{mensajesCat['GENERICO_CENTRO']}">
								<tr:outputText value="#{var.codigoSiglo}"
									inlineStyle="#{var.colorLetra}" />
							</tr:column>
							<!--							<tr:column sortable="false"-->
							<!--								headerText="#{mensajesCat['TIPO_GESTION']}">-->
							<!--								<tr:outputText value="#{var.tipoGestion}" />-->
							<!--							</tr:column>-->
						</tr:table>
					</tr:panelBox>
					<tr:switcher
						facetName="#{catalogo_consultarArticuloBean.facetBotonAceptar}">
						<f:facet name="facetBotonAceptar">
							<tr:panelGroupLayout>
								<tr:commandButton text="#{mensajesCore['ACEPTAR_SELECCION']}"
									action="#{catalogo_consultarArticuloBean.terminarConsulta}"
									id="aceptarSeleccion" />
								<tr:commandButton text="#{mensajesCore['CANCELAR']}"
									action="#{catalogo_consultarArticuloBean.cancelarConsulta}"
									id="cancelarSeleccion" />
							</tr:panelGroupLayout>
						</f:facet>
					</tr:switcher>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>

					<!-- El siguiente spacer es sólo para que se lance un evento al final de la página -->
					<tr:spacer width="1" height="1"
						binding="#{catalogo_consultarArticuloBinding.spacerFinal}" />
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;"
				rendered="#{!catalogo_gestionClasificacionesAlternativa.mostrarComoPopUp}">
				<tr:outputText escape="false" value="#{htmlPie}" />
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
