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
		<f:loadBundle basename="mensajesAcr" var="mensajesAcr" />
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
		<trh:html>
		<trh:head title="#{acreditacion_mantValorDatGenOrgBean.tituloVentana}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
				<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form usesUpload="true" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
					<tr:panelHeader
						text="#{acreditacion_mantValorDatGenOrgBean.tituloVentana}" />
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox text="#{mensajesOrg['SELECCIONAR']}"
						inlineStyle="width: 100%;">
						<tr:selectOneChoice label="#{mensajesOrg['ORGANICA']}"
							id="organica" autoSubmit="true"
							disabled="#{acreditacion_mantValorDatGenOrgBean.orgGestNoEditable}"
							valueChangeListener="#{acreditacion_mantValorDatGenOrgBean.actualizarListaValores}"
							value="#{acreditacion_mantValorDatGenOrgBean.idOrganoGestor}">
							<f:selectItems
								value="#{acreditacion_mantValorDatGenOrgBean.listaOrganosGestores}" />
						</tr:selectOneChoice>
					</tr:panelBox>
					<tr:panelHorizontalLayout>
						<tr:commandButton text="#{mensajesCore['GUARDAR']}"
							action="#{acreditacion_mantValorDatGenOrgBean.guardar}"
							rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarBotones}" />
						<tr:commandButton text="#{mensajesAcr['DESHACER_CAMBIOS']}"
							action="#{acreditacion_mantValorDatGenOrgBean.cancelar}"
							rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarBotones}" />
					</tr:panelHorizontalLayout>
					<tr:panelBox inlineStyle="width: 100%;">
						<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
							var="valorDato" columnBandingInterval="0" rows="10"
							partialTriggers=":organica"
							value="#{acreditacion_mantValorDatGenOrgBean.listaValoresVista}">
							<tr:column sortable="false" noWrap="false"
								headerText="#{mensajesAcr['CAMPO']}"
								inlineStyle="vertical-align:middle">
								<tr:outputText value="#{valorDato.campo}#{((valorDato.obligatorio)?mensajesCore['REQUERIDO']:'')}" />
							</tr:column>
							<tr:column sortable="false" headerText="#{mensajesAcr['VALOR']}">
								<tr:panelHorizontalLayout rendered="#{valorDato.mostrarCombo}">
									<tr:selectManyListbox shortDesc="#{valorDato.campo}"
										inlineStyle="width:380px" size="5"
										disabled="#{valorDato.noEditable}"
										value="#{valorDato.opcionesSeleccionadas}">
										<f:selectItems value="#{valorDato.opcionesCombo}" />
									</tr:selectManyListbox>
									<tr:commandLink
										action="#{acreditacion_mantValorDatGenOrgBean.buscarUnidadesOrganizativas(valorDato.dato)}"
										shortDesc="#{mensajesCore['AGREGAR']}"
										rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarBotones}">
										<tr:image source="../imagenes/lupa3.gif" />
									</tr:commandLink>
									<tr:commandLink
										action="#{acreditacion_mantValorDatGenOrgBean.eliminarOpciones(valorDato.dato)}"
										shortDesc="#{mensajesCore['BORRAR']}"
										rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarBotones}">
										<tr:image source="../imagenes/btnEliminar.gif" />
									</tr:commandLink>
								</tr:panelHorizontalLayout>
								<tr:inputText value="#{valorDato.valor}"
									rendered="#{!valorDato.mostrarCombo}"
									disabled="#{valorDato.noEditable}"
									shortDesc="#{valorDato.campo}" columns="#{valorDato.ancho}"
									rows="#{valorDato.alto}" />
							</tr:column>
							<tr:column sortable="false"
								headerText="#{mensajesAcr['ARCHIVOS']}"  rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarColumnaArchivos}">
								<tr:panelLabelAndMessage rendered="#{valorDato.pintarInputFile}">
									<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
										columnBandingInterval="0" rowBandingInterval="1" rows="10"
										inlineStyle="width: 100%;" rowSelection="multiple"
										value="#{valorDato.listaArchivos}" var="archivo">
										<f:facet name="actions">
											<tr:panelHorizontalLayout halign="left"	inlineStyle="width: 100%;" 
												rendered="#{acreditacion_mantValorDatGenOrgBean.mostrarBotones}">
												<tr:inputFile label="Subir archivo: " simple="true"
													valueChangeListener="#{acreditacion_mantValorDatGenOrgBean.listenerSubidaArchivos}"	>
													<tr:attribute name="componenteAgregarArchivo" value="#{valorDato}"/>
													</tr:inputFile>
												<tr:commandLink >
													<tr:image shortDesc="#{mensajesCore['AGREGAR']}"
														source="../imagenes/anadir_16.png" />
												</tr:commandLink>
												<tr:commandLink actionListener="#{acreditacion_mantValorDatGenOrgBean.borrarArchivo}">
												<tr:attribute name="componenteBorrarArchivo" value="#{valorDato}"/>
													<tr:image shortDesc="#{mensajesCore['BORRAR']}"
														source="../imagenes/btnEliminar.gif" />
												</tr:commandLink>
											</tr:panelHorizontalLayout>
										</f:facet>
										<tr:column headerText="#{mensajesCore['NOMBRE']}">
											<tr:outputText value="#{archivo.nombre}" />
										</tr:column>
										<tr:column headerText="#{mensajesAcr['ARCHIVO']}">
											<tr:commandLink actionListener="#{acreditacion_mantValorDatGenOrgBean.descargarArchivo}"
											onclick="noBloquearPantalla()" text="Descargar archivo">
											<tr:attribute name="verArchivo" value="#{archivo}"/>
											</tr:commandLink>
										</tr:column>
									</tr:table>
								</tr:panelLabelAndMessage>
							</tr:column>
						</tr:table>
					</tr:panelBox>
					<tr:panelHorizontalLayout>
						<tr:commandButton text="#{mensajesCore['VOLVER']}"
							action="#{acreditacion_mantValorDatGenOrgBean.volver}" />
					</tr:panelHorizontalLayout>
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>

