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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>
		<trh:head title="#{resCat['FOTOS_ARTICULO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">  
				<tr:panelPage id="panel">
					<tr:panelHeader text="#{resCat['FOTOS_ARTICULO']}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHorizontalLayout halign="center" inlineStyle="border=none; width=100%">
						<tr:panelBox background="medium" inlineStyle="width=100%">
						
							<tr:statusIndicator>
								<f:facet name="busy">
									<tr:panelHorizontalLayout halign="center">
										<tr:outputText value="[    Cargando imagen, por favor espere...    ]" inlineStyle="font-weight: bold; padding-top: 2px; padding-bottom: 2px; padding-left: 20px; padding-right: 20px; font-size: 110%;" />
									</tr:panelHorizontalLayout>
								</f:facet>
								<f:facet name="ready">
									<tr:panelHorizontalLayout halign="center">
										<tr:outputText value="&#32;" inlineStyle="padding: 8px; font-size: 110%;" />
									</tr:panelHorizontalLayout>
								</f:facet>
							</tr:statusIndicator>
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="6" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<trh:cellFormat columnSpan="6">
											<tr:panelHorizontalLayout halign="center">
												<tr:commandLink partialSubmit="true" id="primera"
													disabled="#{catalogo_mostrarFotosArticuloBean.anteriorDeshabilitada}"
													action="#{catalogo_mostrarFotosArticuloBean.primeraFoto}"
													partialTriggers="siguiente ultima">
													<tr:image source="../imagenes/izda2.gif"
														inlineStyle="height: 18px;"
														shortDesc="#{resCat['PRIMERA']}" />
												</tr:commandLink>
												<tr:commandLink partialSubmit="true" id="anterior"
													disabled="#{catalogo_mostrarFotosArticuloBean.anteriorDeshabilitada}"
													action="#{catalogo_mostrarFotosArticuloBean.anteriorFoto}"
													partialTriggers="siguiente ultima">
													<tr:image source="../imagenes/izda.gif"
														inlineStyle="height: 18px;"
														shortDesc="#{resCat['ANTERIOR']}" />
												</tr:commandLink>
												<tr:spacer width="10" height="10" />
												<tr:commandLink partialSubmit="true" id="siguiente"
													disabled="#{catalogo_mostrarFotosArticuloBean.siguienteDeshabilitada}"
													action="#{catalogo_mostrarFotosArticuloBean.siguienteFoto}"
													partialTriggers="primera anterior">
													<tr:image source="../imagenes/dcha.gif"
														inlineStyle="height: 18px;"
														shortDesc="#{resCat['SIGUIENTE']}" />
												</tr:commandLink>
												<tr:commandLink partialSubmit="true" id="ultima"
													disabled="#{catalogo_mostrarFotosArticuloBean.siguienteDeshabilitada}"
													action="#{catalogo_mostrarFotosArticuloBean.ultimaFoto}"
													partialTriggers="primera anterior">
													<tr:image source="../imagenes/dcha2.gif"
														inlineStyle="height: 18px;" shortDesc="#{resCat['ULTIMA']}" />
												</tr:commandLink>
												<tr:spacer width="10" height="10" />
												<tr:outputLabel
													partialTriggers="primera anterior siguiente ultima"
													value="#{catalogo_mostrarFotosArticuloBean.descripcionTipoFoto}" />
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout halign="center">
								<tr:panelGroupLayout>
										<tr:image partialTriggers="primera anterior siguiente ultima"
											source="#{catalogo_mostrarFotosArticuloBean.urlFotoEntidad}"
											inlineStyle="height: 300px;"
											shortDesc="#{catalogo_mostrarFotosArticuloBean.descripcionNumeroFoto}" />
								</tr:panelGroupLayout>
								
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
											<tr:outputLabel
													partialTriggers="primera anterior siguiente ultima"
													value="#{catalogo_mostrarFotosArticuloBean.nombreDescripcion}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="6" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCore['CODIGO']}"/>
										<tr:inputText value="#{catalogo_mostrarFotosArticuloBean.codigo}"
											columns="70"/>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['ARTICULO']}"/>
										<tr:inputText value="#{catalogo_mostrarFotosArticuloBean.descripcionArticulo}"
											columns="67" rows="4"/>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['NEMOTECNICO']}"/>
										<tr:inputText value="#{catalogo_mostrarFotosArticuloBean.nemotecnico}"
											columns="70"/>
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
								<tr:panelBox background="medium" rendered="#{catalogo_mostrarFotosArticuloBean.editarFoto}">
									<trh:tableLayout cellSpacing="6" cellPadding="0" borderWidth="0">
										<trh:rowLayout>
											<tr:outputText value="#{resCat['SUBIR_FOTO']}"/>
											<tr:inputFile valueChangeListener="#{catalogo_mostrarFotosArticuloBean.subirFoto}" 
												columns="45"/>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['TIPO_VISTA_FOTO']}" />
											<tr:selectOneChoice id="comboTVistaFoto" unselectedLabel=""
												value="#{catalogo_mostrarFotosArticuloBean.tipoVistaFoto}">
													<f:selectItems
													value="#{catalogo_mostrarFotosArticuloBean.selectorTVistaFoto}"
													id="selectorTVistaFoto" />
											</tr:selectOneChoice>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['CATEGORIA_FOTO']}" />
											<tr:selectOneChoice id="comboCategoriaFoto" unselectedLabel=""
												value="#{catalogo_mostrarFotosArticuloBean.categoriaFoto}">
												<f:selectItems
													value="#{catalogo_mostrarFotosArticuloBean.selectorCategoriaFoto}"
													id="selectorCategoriaFoto" />
											</tr:selectOneChoice>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['DESCRIPCION_FOTO']}" />
											<tr:inputText value="#{catalogo_mostrarFotosArticuloBean.descripcionFoto}" 
												columns="60"/>
										</trh:rowLayout>
										<trh:rowLayout>
											<trh:cellFormat columnSpan="2">
												<tr:panelHorizontalLayout halign="center">
													<tr:panelButtonBar>
														<tr:commandButton text="#{resCore['GUARDAR']}"
															action="#{catalogo_mostrarFotosArticuloBean.guardarFoto}"/>
														<tr:commandButton text="#{resCore['BORRAR']}"
															action="#{catalogo_mostrarFotosArticuloBean.eliminarFoto}" />
													</tr:panelButtonBar>
												</tr:panelHorizontalLayout>
											</trh:cellFormat>
										</trh:rowLayout>
									</trh:tableLayout>
								</tr:panelBox>
	
							</tr:panelHorizontalLayout>
						</tr:panelBox>
					</tr:panelHorizontalLayout>
					<tr:spacer width="10" height="10" />
					<tr:panelHorizontalLayout halign="center">
						<tr:commandButton text="#{resCore['VOLVER']}"
										  action="#{catalogo_mostrarFotosArticuloBean.volver}" />
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
						<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
