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
		<trh:head title="#{mensajesOrg['MANTENIMIENTO_ARTICULOS_POR_CENTRO_Y_ORIGEN']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
				
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="btnBuscar" onsubmit="bloquearPantalla(this);">
				<tr:panelPage inlineStyle="width:100%;">
					<tr:panelHeader text="#{mensajesOrg['MANTENIMIENTO_ARTICULOS_POR_CENTRO_Y_ORIGEN']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelBox
							partialTriggers=""
							inlineStyle="width:100%;"
							background="medium">															
							<trh:tableLayout cellSpacing="5" cellPadding="0" borderWidth="0">
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['GRUPO']}" />
									<trh:cellFormat columnSpan="3">
										<tr:selectOneChoice id="comboGrupos"
											value="#{catalogo_consultarArticuloPorCentroBean.grupoSeleccionado}"
											unselectedLabel="" autoSubmit="true" 
											valueChangeListener="#{catalogo_consultarArticuloPorCentroBean.cambioGrupo}">
											<f:selectItems
												value="#{catalogo_consultarArticuloPorCentroBean.selectorGrupos}"/>
										</tr:selectOneChoice>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['SUBGRUPO']}" />
									<trh:cellFormat columnSpan="3">
										<tr:selectOneChoice id="comboSubGrupos"
											value="#{catalogo_consultarArticuloPorCentroBean.subGrupoSeleccionado}"
											unselectedLabel="" autoSubmit="true" partialTriggers="comboGrupos btnLimpiar"
											valueChangeListener="#{catalogo_consultarArticuloPorCentroBean.cambioSubGrupo}">
											<f:selectItems
												value="#{catalogo_consultarArticuloPorCentroBean.selectorSubGrupos}"
												id="selectorSubGrupos" />
										</tr:selectOneChoice>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['FAMILIA']}" />
									<trh:cellFormat columnSpan="3">
										<tr:selectOneChoice id="comboFamilias"
											value="#{catalogo_consultarArticuloPorCentroBean.familiaSeleccionado}"
											partialTriggers="comboGrupos comboSubGrupos btnLimpiar"
											unselectedLabel="">
											<f:selectItems
												value="#{catalogo_consultarArticuloPorCentroBean.selectorFamilias}"
												id="selectorFamilia" />
										</tr:selectOneChoice>
									</trh:cellFormat>
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesCat['CODIGO_SAS']}" />
									<tr:inputText
											value="#{catalogo_consultarArticuloPorCentroBean.codigoSAS}"
											columns="25" />
									<tr:outputText value="#{mensajesCore['DESCRIPCION']}" />
									<tr:inputText
											value="#{catalogo_consultarArticuloPorCentroBean.descripcion}"
											columns="60" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value="#{mensajesOrg['CENTRO']}" />
									<tr:selectOneChoice id="comboOrganos"
										value="#{catalogo_consultarArticuloPorCentroBean.organoSeleccionado}"
										unselectedLabel="">
										<f:selectItems
											value="#{catalogo_consultarArticuloPorCentroBean.selectorOrganos}"
											id="selectorOrganos" />
									</tr:selectOneChoice>
									<tr:outputText value="#{mensajesCat['NEMOTECNICO']}" />
									<tr:inputText
											value="#{catalogo_consultarArticuloPorCentroBean.nemoTecnico}"
											columns="60" />
								</trh:rowLayout>
								<trh:rowLayout>
									<tr:outputText value=""/>
									<tr:spacer width="400" height="10" />
									<tr:outputText value=""/>
									<tr:panelHorizontalLayout>
										<tr:outputText value="#{mensajesOrg['CATALOGO']}" />
										<tr:selectBooleanCheckbox id="catalogo"
											value="#{catalogo_consultarArticuloPorCentroBean.catalogo}"/>
										<tr:outputText value="#{mensajesCat['PUBLILINE']}" />
										<tr:selectBooleanCheckbox id="publiline"
											value="#{catalogo_consultarArticuloPorCentroBean.publiline}"/>
										<tr:outputText value="#{mensajesCat['CONTRATO_MENOR']}" />
										<tr:selectBooleanCheckbox id="contratoMenor"
											value="#{catalogo_consultarArticuloPorCentroBean.compraMenor}"/>
									</tr:panelHorizontalLayout>
								</trh:rowLayout>
							</trh:tableLayout>
							<tr:panelHorizontalLayout halign="right">
										<tr:commandButton text="#{mensajesCore['BUSCAR']}"
											id="btnBuscar"
											action="#{catalogo_consultarArticuloPorCentroBean.realizarConsulta}" 
											blocking="true"/>
										<tr:spacer width="10" height="0" />
										<tr:commandButton text="#{mensajesCore['LIMPIAR']}"
											action="#{catalogo_consultarArticuloPorCentroBean.limpiarFormulario}"
											id="btnLimpiar">
										</tr:commandButton>
									</tr:panelHorizontalLayout>
						</tr:panelBox>
					<tr:spacer width="10" height="20" />
					<tr:panelBox text="Resultados" inlineStyle="width:100%;">
						<tr:table 
							binding="#{catalogo_consultarArticuloPorCentroBinding.tablaArticulos}" 
							var="var" first="0" emptyText="No hay elementos en la tabla"
							rows="10" width="100%"							
							value="#{catalogo_consultarArticuloPorCentroBean.listaResultado}"
							rowBandingInterval="1"
							columnBandingInterval="0"
							selectedRowKeys="#{catalogo_consultarArticuloPorCentroBean.estadoDeSeleccionTabla}"
							rowSelection="single">
							<f:facet name="actions">
								<h:panelGroup>
									<tr:panelButtonBar>
										<tr:commandButton text="#{mensajesCat['VER_DETALLES']}"
												id="btnVerDetalles"
												action="#{catalogo_consultarArticuloPorCentroBean.verDetalles}" 
												blocking="true"/>
									</tr:panelButtonBar>
								</h:panelGroup>
							</f:facet>
							<tr:column sortable="true"
								sortProperty="codigoSAS"
								headerText="#{mensajesCat['CODIGO_SAS']}">
								<tr:outputText value="#{var.codigoSAS}" />
							</tr:column>
							<tr:column headerText="#{mensajesCat['USADO']}">
								<tr:panelHorizontalLayout halign="center">
<!--									Aqui cada imagen tendrá una propiedad que indicará si se ve o no-->
									<tr:image source="../imagenes/catalogo.png" rendered="#{var.catalogo}"/>
									<tr:image source="../imagenes/publiline.png" rendered="#{var.publiline}"/>
									<tr:image source="../imagenes/contratoMenor.png" rendered="#{var.compraMenor}"/>
								</tr:panelHorizontalLayout>
							</tr:column>
							<tr:column sortable="true"
								sortProperty="descripcion"
								headerText="#{mensajesCore['DESCRIPCION']}">
								<tr:outputText value="#{var.descripcion}"/>
							</tr:column>
							<tr:column sortable="true"
								sortProperty="organoGestor"
								headerText="#{mensajesOrg['ORGANO_GESTOR']}">
								<tr:outputText value="#{var.organoGestor}" />
							</tr:column>
							<tr:column sortable="true"
								sortProperty="nemotecnico"
								headerText="#{mensajesCat['NEMOTECNICO']}">
								<tr:outputText value="#{var.nemotecnico}" />
							</tr:column>
						</tr:table>
						<tr:spacer width="10" height="10" />
						<tr:panelHorizontalLayout>															
							<tr:outputText value="#{mensajesCat['ORGIEN_DATOS']}" />
							<tr:spacer width="10" height="10" />
							<tr:image source="../imagenes/catalogo.png"/>
							<tr:spacer width="10" height="10" />
							<tr:outputText value="#{mensajesOrg['CATALOGO']}  " />
							<tr:spacer width="10" height="10" />
							<tr:image source="../imagenes/publiline.png"/>
							<tr:spacer width="10" height="10" />
							<tr:outputText value="#{mensajesCat['PUBLILINE']}  " />
							<tr:spacer width="10" height="10" />
							<tr:image source="../imagenes/contratoMenor.png"/>
							<tr:spacer width="10" height="10" />
							<tr:outputText value="#{mensajesCat['CONTRATO_MENOR']}  " />
						</tr:panelHorizontalLayout>
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