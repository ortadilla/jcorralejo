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
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<tr:document>
		<trh:html>
		<trh:head title="#{resPed['FOTOS_OFERTA']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
		 <geos:cabeceraPagina/>
			<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
				<tr:panelPage id="panel">
					<tr:panelHeader text="#{resPed['FOTOS_OFERTA']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHorizontalLayout halign="center" inlineStyle="border=none; width=100%">
						<tr:panelBox background="medium" inlineStyle="width: 100%;">							
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandLink partialSubmit="true" id="primera"
									disabled="#{pedidos_mostrarFotosOfertaBean.anteriorDeshabilitada}"
									action="#{pedidos_mostrarFotosOfertaBean.primeraFoto}"
									partialTriggers="siguiente ultima">
									<tr:image source="../imagenes/izda2.gif" inlineStyle="height: 18px;"
										shortDesc="#{resCat['PRIMERA']}" />
								</tr:commandLink>
								<tr:commandLink partialSubmit="true" id="anterior"
									disabled="#{pedidos_mostrarFotosOfertaBean.anteriorDeshabilitada}"
									action="#{pedidos_mostrarFotosOfertaBean.anteriorFoto}"
									partialTriggers="siguiente ultima">
									<tr:image source="../imagenes/izda.gif" inlineStyle="height: 18px;"
										shortDesc="#{resCat['ANTERIOR']}" />
								</tr:commandLink>
								<tr:spacer width="10" height="10" />
								<tr:commandLink partialSubmit="true" id="siguiente"
									disabled="#{pedidos_mostrarFotosOfertaBean.siguienteDeshabilitada}"
									action="#{pedidos_mostrarFotosOfertaBean.siguienteFoto}"
									partialTriggers="primera anterior">
									<tr:image source="../imagenes/dcha.gif" inlineStyle="height: 18px;"
										shortDesc="#{resCat['SIGUIENTE']}" />
								</tr:commandLink>
								<tr:commandLink partialSubmit="true" id="ultima"
									disabled="#{pedidos_mostrarFotosOfertaBean.siguienteDeshabilitada}"
									action="#{pedidos_mostrarFotosOfertaBean.ultimaFoto}"
									partialTriggers="primera anterior">
									<tr:image source="../imagenes/dcha2.gif" inlineStyle="height: 18px;"
										shortDesc="#{resCat['ULTIMA']}" />
								</tr:commandLink>
								<tr:spacer width="10" height="10" />
								<tr:outputLabel
									partialTriggers="primera anterior siguiente ultima"
									value="#{pedidos_mostrarFotosOfertaBean.descripcionTipoFoto}" />
							</tr:panelHorizontalLayout>
							<tr:panelHorizontalLayout halign="center">
								<tr:image
									partialTriggers="primera anterior siguiente ultima"
									source="#{pedidos_mostrarFotosOfertaBean.urlFotoEntidad}"
									inlineStyle="height: 300px; border: solid 2px black;"
									shortDesc="#{pedidos_mostrarFotosOfertaBean.descripcionNumeroFoto}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
										<tr:outputLabel
												partialTriggers="primera anterior siguiente ultima"
												value="#{pedidos_mostrarFotosOfertaBean.nombreDescripcion}" />
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
								<trh:tableLayout cellSpacing="6" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<tr:outputText value="#{resCat['CIP']}"/>
										<tr:inputText value="#{pedidos_mostrarFotosOfertaBean.cipProducto}"
											columns="65"/>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['FABRICANTE']}"/>
										<tr:inputText value="#{pedidos_mostrarFotosOfertaBean.referenciaFabricanteProducto}"
											columns="65"/>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['DENOMINACION_COMERCIAL']}"/>
										<tr:inputText value="#{pedidos_mostrarFotosOfertaBean.denominacionComercialProducto}"
											columns="62" rows="4"/>
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelHorizontalLayout>
							<tr:spacer width="10" height="10" />
							<tr:panelHorizontalLayout halign="center">
								<tr:panelBox background="medium"
								rendered="#{pedidos_mostrarFotosOfertaBean.subirFoto}">
									<trh:tableLayout cellSpacing="6" cellPadding="0" borderWidth="0">
										<trh:rowLayout>
											<tr:outputText value="#{resCat['SUBIR_FOTO']}"/>
											<tr:inputFile valueChangeListener="#{pedidos_mostrarFotosOfertaBean.subirFoto}" 
												columns="45"/>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['TIPO_VISTA_FOTO']}" />
											<tr:selectOneChoice id="comboTVistaFoto" unselectedLabel=""
												value="#{pedidos_mostrarFotosOfertaBean.tipoVistaFoto}">
												<f:selectItems
													value="#{pedidos_mostrarFotosOfertaBean.selectorTVistaFoto}"
													id="selectorTVistaFoto" />
											</tr:selectOneChoice>
										</trh:rowLayout>
										<trh:rowLayout>
											<tr:outputText value="#{resCat['DESCRIPCION_FOTO']}" />
											<tr:inputText value="#{pedidos_mostrarFotosOfertaBean.descripcionFoto}" 
												columns="60"/>
										</trh:rowLayout>
										<trh:rowLayout>
											<trh:cellFormat columnSpan="2">
												<tr:panelHorizontalLayout halign="center">
													<tr:panelButtonBar>
														<tr:commandButton text="#{resCore['GUARDAR']}"
															rendered="#{pedidos_mostrarFotosOfertaBean.editarFoto}"
															action="#{pedidos_mostrarFotosOfertaBean.guardarFoto}" />
														<tr:commandButton text="#{resCore['BORRAR']}"
															action="#{pedidos_mostrarFotosOfertaBean.eliminarFoto}" 
															rendered="#{pedidos_mostrarFotosOfertaBean.editarFoto}"/>
														<tr:commandButton text="#{resCore['SOL_ALTA']}"
															action="#{pedidos_mostrarFotosOfertaBean.solicitarAltaFoto}"
															rendered="#{pedidos_mostrarFotosOfertaBean.solicitarFoto}" />
														<tr:commandButton text="#{resCore['SOL_BAJA']}"
															action="#{pedidos_mostrarFotosOfertaBean.solicitarBajaFoto}" 
															rendered="#{pedidos_mostrarFotosOfertaBean.solicitarFoto}" />
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
											action="#{pedidos_mostrarFotosOfertaBean.volver}" />
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</tr:document>
	</f:view>
</jsp:root>
