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
		<trh:head title="#{resCat['GESTION_PRODUCTOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader text="#{resCat['GESTION_PRODUCTOS']}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelGroupLayout>
						<tr:panelFormLayout>
							<tr:panelBox background="medium" inlineStyle="width: 100%;"
								partialTriggers="articuloBuscado">

								<tr:spacer height="5" />
								<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<trh:cellFormat />
										<trh:cellFormat />
										<trh:cellFormat />
										<trh:cellFormat />
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['ARTICULO']}" />
										<tr:panelHorizontalLayout>
											<tr:inputText id="articuloBuscado"
												value="#{catalogo_gestProductoBean.articulo}"
												required="false"
												valueChangeListener="#{catalogo_gestProductoBean.rellenaArticulo}"
												disabled="#{catalogo_gestProductoBean.inactivo}"
												autoSubmit="true" partialTriggers="articuloBuscado" 
												maximumLength="6"
												columns="6"/>
											<tr:commandLink
												action="#{catalogo_gestProductoBean.buscarArticulo}"
												disabled="#{catalogo_gestProductoBean.inactivo}" 
												rendered="#{!catalogo_gestProductoBean.inactivo}">
												<tr:image source="/imagenes/lupa3.gif" />
											</tr:commandLink>
										</tr:panelHorizontalLayout>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['DENOMINACION_COMERCIAL']}"/>							
										<trh:cellFormat columnSpan="3">
											<tr:inputText value="#{catalogo_gestProductoBean.modelo}" 
											disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['MARCA']}" />
										<trh:cellFormat columnSpan="3">
											<tr:inputText value="#{catalogo_gestProductoBean.marca}" 
											disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['CIP']}" />
										<trh:cellFormat columnSpan="3">
											<tr:inputText value="#{catalogo_gestProductoBean.cip}"
											disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['REF_FABRICANTE']}" />
										<trh:cellFormat columnSpan="3">
											<tr:inputText
												value="#{catalogo_gestProductoBean.refFabricante}" 
												disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat columnSpan="5">
											<tr:panelHorizontalLayout>
												<tr:outputText value="#{resCat['TIENE_DT']}" />
												<tr:selectOneRadio
													value="#{catalogo_gestProductoBean.tieneDT}"
													disabled="true">
													<f:selectItems
														value="#{catalogo_gestProductoBean.itemsTieneDT}" />
												</tr:selectOneRadio>
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['DESCRIPCION']}" />
										<tr:inputText wrap="off" rows="5" columns="110"
											maximumLength="4000"
											value="#{catalogo_gestProductoBean.descripcion}"
											required="false" 
											disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCore['ESTADO']}" />
										<tr:selectOneChoice
											value="#{catalogo_gestProductoBean.estado}" disabled="true">
											<f:selectItems
												value="#{catalogo_gestProductoBean.itemsEstados}" />
										</tr:selectOneChoice>
									</trh:rowLayout>
									<trh:rowLayout>
										<tr:outputText value="#{resCat['URL']}" />
										<trh:cellFormat columnSpan="3">
											<tr:inputText value="#{catalogo_gestProductoBean.url}"
											disabled="#{catalogo_gestProductoBean.desabilitaCampos}"/>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat columnSpan="5">
											<tr:panelHorizontalLayout halign="center"
												partialTriggers="articuloBuscado">
												<tr:panelFormLayout id="panelAtributos"
													binding="#{catalogo_gestionProductoBinding.panelAtributos}"
													partialTriggers="articuloBuscado" />
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat columnSpan="5">
											<tr:panelHorizontalLayout halign="right">
												<tr:commandButton text="#{resCore['ACEPTAR']}"
													action="#{catalogo_gestProductoBean.actualizarYSalir}" />
												<tr:commandButton text="#{resCore['CANCELAR']}"
													action="#{catalogo_gestProductoBean.cancelar}" />
											</tr:panelHorizontalLayout>
										</trh:cellFormat>
									</trh:rowLayout>
								</trh:tableLayout>
							</tr:panelBox>
						</tr:panelFormLayout>
					</tr:panelGroupLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
