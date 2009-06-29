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
		<f:loadBundle basename="mensajesOrg" var="resOrg" />
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<trh:html>
		<trh:head title="#{resOrg['GESTION_LECTORAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<tr:panelHeader text="#{organizacion_gestionLectorasBean.tituloPanel}"/>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelBox inlineStyle="width: 100%;" partialTriggers=":borrar" text="#{resOrg['LECTORES_IDENTIFICACION_AUTOMATICA']}">
							<tr:panelFormLayout inlineStyle="width: 100%;" >
								<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
									<trh:rowLayout>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
										<trh:cellFormat><tr:spacer height="5" width="50"/></trh:cellFormat>
									</trh:rowLayout>
									<!--Inicio HOMOLOGADAS-->
									<trh:rowLayout>
										<trh:cellFormat columnSpan="7">
										<tr:panelHeader  text="#{resOrg['HOMOLOGADAS']}"/>  
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
									<trh:cellFormat columnSpan="7">
										<tr:panelHorizontalLayout>
											<tr:table rows="10" rowBandingInterval="1"
												columnBandingInterval="0" emptyText="#{resCore['NO_ELEMENTOS']}"
												var="homologada" id="lectorasHomologadas"
												value="#{organizacion_gestionLectorasBean.listaLectorasHomologadas}"
												partialTriggers="borrar">
												<!-- Columna de selección para borrar -->
												<tr:column>
													<tr:selectBooleanCheckbox selected="#{homologada.borrar}"/>
												</tr:column>
												<!-- Columna Nombre de Lector -->
												<tr:column headerText="#{resOrg['LECTORES']}">
													<tr:outputText value="#{homologada.descripcionEquipamiento}" />
												</tr:column>
	<!--											 Columna Activar/Desactivar el lector -->
	<!--											<tr:column headerText="#{resOrg['ACTIVAR_DESACTIVAR']}">-->
	<!--												<tr:selectBooleanCheckbox selected="#{homologada.activar}"/>-->
	<!--											</tr:column>-->
												<!-- Columnas de los atributos del lector -->
												<tr:forEach items="#{organizacion_gestionLectorasBean.cabecerasLectorasHomologadas}" var="atr" varStatus="vs">
						                    		<tr:column headerText="#{atr.descripcion}">
														<tr:panelHorizontalLayout>
																<tr:image source="#{homologada.listaValorAtributoVista[vs.index].imagenEstadoSolicitud}"/>
																<tr:inputText contentStyle="#{homologada.listaValorAtributoVista[vs.index].esNumerico ? 'text-align:right;': ''}"
																	value="#{homologada.listaValorAtributoVista[vs.index].valor}"
																	columns="25" rendered="#{!homologada.listaValorAtributoVista[vs.index].esDiccionario}"/>
																<tr:selectOneChoice
																	value="#{homologada.listaValorAtributoVista[vs.index].valor}"
																	unselectedLabel=""
																	rendered="#{homologada.listaValorAtributoVista[vs.index].esDiccionario}">
																	<f:selectItems
																		value="#{homologada.listaValorAtributoVista[vs.index].valoresDiccionario}"
																		id="selectorTipoLectura" />
																</tr:selectOneChoice>
																<tr:outputText value="#{homologada.listaValorAtributoVista[vs.index].infoExtra}" />	
																<tr:spacer height="15" width="20"/>													
																<tr:outputText value="#{resOrg['OTRO_VALOR']}" rendered="#{homologada.listaValorAtributoVista[vs.index].esDiccionario}" />
																<tr:inputText
																	value="#{homologada.listaValorAtributoVista[vs.index].valorOtro}"
																	columns="25" rendered="#{homologada.listaValorAtributoVista[vs.index].esDiccionario}"/>
															</tr:panelHorizontalLayout>
						                    		</tr:column>
						                    	</tr:forEach>
											</tr:table>
											<tr:commandLink
												action="#{organizacion_gestionLectorasBean.agregarLectoraHomologada}">
												<tr:image source="../imagenes/lupa3.gif" />
											</tr:commandLink>
											<tr:spacer height="0" width="15"/>
											<tr:commandLink id="borrar"
												action="#{organizacion_gestionLectorasBean.borrarLectoraHomologada}">
												<tr:image source="../imagenes/btnEliminar.gif" />
											</tr:commandLink>
										</tr:panelHorizontalLayout>
									</trh:cellFormat>
<!--										<trh:cellFormat>-->
<!--												<tr:commandLink-->
<!--													action="#{organizacion_gestionLectorasBean.agregarLectoraHomologada}">-->
<!--													<tr:image source="../imagenes/lupa3.gif" />-->
<!--												</tr:commandLink>-->
<!--												<tr:spacer height="0" width="15"/>-->
<!--												<tr:commandLink id="borrar"-->
<!--													action="#{organizacion_gestionLectorasBean.borrarLectoraHomologada}">-->
<!--													<tr:image source="../imagenes/btnEliminar.gif" />-->
<!--												</tr:commandLink>-->
<!--										</trh:cellFormat>-->
									</trh:rowLayout>
									<!--Fin HOMOLOGADAS-->
									<!--Inicio OTROLECTOR-->
									<trh:rowLayout>
										<trh:cellFormat columnSpan="7">
											<tr:panelHeader  text="#{resOrg['OTROLECTOR']}"/>  
										</trh:cellFormat>
									</trh:rowLayout>
									<tr:iterator var="item" varStatus="varStatusOtrosLectores"
										value="#{organizacion_gestionLectorasBean.listaOtrosLectores}">
										<trh:rowLayout>
											<tr:spacer height="15" width="20" />
											<trh:cellFormat columnSpan="5">
												<tr:panelHorizontalLayout>
													<tr:outputText value="#{item.equipamiento}" />
													<tr:selectBooleanCheckbox selected="#{item.activar}"/>
												</tr:panelHorizontalLayout>
											</trh:cellFormat>
										</trh:rowLayout>
										<!-- Inicio ITERADOR ATRIBUTOS DE OTROS LECTORES -->
										<tr:iterator var="itemAtributo" varStatus="varStatusAtributoOtrosLectores"
											value="#{item.listaValorAtributoVista}">
											<trh:rowLayout>
												<trh:cellFormat columnSpan="2">
													<tr:spacer height="15" width="40" />								
												</trh:cellFormat>
												<trh:cellFormat columnSpan="3">
													<tr:image source="#{itemAtributo.imagenEstadoSolicitud}"/>
													<tr:spacer height="15" width="15" />
													<tr:outputText value="#{itemAtributo.atributo}" />
												</trh:cellFormat>
												<trh:cellFormat>
													<tr:panelHorizontalLayout>
														<tr:inputText contentStyle="#{itemAtributo.esNumerico ? 'text-align:right;': ''}"
															value="#{itemAtributo.valor}"
															columns="25" rendered="#{!itemAtributo.esDiccionario}"/>
														<tr:selectOneChoice
															value="#{itemAtributo.valor}"
															unselectedLabel=""
															rendered="#{itemAtributo.esDiccionario}">
															<f:selectItems
																value="#{itemAtributo.valoresDiccionario}"
																id="selectorTipoLectura" />
														</tr:selectOneChoice>
														<tr:outputText value="#{itemAtributo.infoExtra}" />		
														<tr:spacer height="15" width="20" />												
														<tr:outputText value="#{resOrg['OTRO_VALOR']}" rendered="#{itemAtributo.esDiccionario}" />
														<tr:inputText
															value="#{itemAtributo.valorOtro}"
															columns="25" rendered="#{itemAtributo.esDiccionario}"/>
													</tr:panelHorizontalLayout>
												</trh:cellFormat>
											</trh:rowLayout>
										</tr:iterator>
									</tr:iterator>
									<!-- Fin ITERADOR ATRIBUTOS DE OTROS LECTORES -->
									<!-- Fin OTROLECTOR-->
									<trh:rowLayout>
										<trh:cellFormat columnSpan="6">
											<tr:commandButton text="#{resOrg['AGREGAR_OTRO_LECTOR']}"
													action="#{organizacion_gestionLectorasBean.agregarOtroLector}" />
										</trh:cellFormat>
									</trh:rowLayout>
									<trh:rowLayout>
										<trh:cellFormat columnSpan="2">
											<tr:outputLabel value="#{resOrg['MOTIVOSOLICITUD']}" />
										</trh:cellFormat>
										<trh:cellFormat columnSpan="4">
											<tr:inputText columns="60" rows="5" value="#{organizacion_gestionLectorasBean.motivoSolicitud}"/>
										</trh:cellFormat>							
									</trh:rowLayout>
							</trh:tableLayout>
							<tr:spacer height="10" width="10" />
							<tr:panelHorizontalLayout halign="center">
								<tr:commandButton text="#{resCor['ACEPTAR']}"
									action="#{organizacion_gestionLectorasBean.aceptar}" />
								<tr:spacer width="10" />
								<tr:commandButton text="#{resCor['CANCELAR']}"
									action="#{organizacion_gestionLectorasBean.cancelar}" />
								<tr:spacer width="10" />
							</tr:panelHorizontalLayout>
						</tr:panelFormLayout>
					</tr:panelBox>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>