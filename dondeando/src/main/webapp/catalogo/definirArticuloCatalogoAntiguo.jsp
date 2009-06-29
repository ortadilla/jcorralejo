<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:afh="http://xmlns.oracle.com/adf/faces/html"
	xmlns:af="http://xmlns.oracle.com/adf/faces">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>

		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesCore" var="resCor" />
		<f:loadBundle basename="mensajesOrg" var="resOrg" />

		<afh:html>
		<afh:head title="#{resCat['GESTION_ARTICULOS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</afh:head>
		<afh:body>
			<h:form enctype="application/x-www-form-urlencoded">

				<af:panelPage id="panelPage"
                  partialTriggers="panelAlmacenes panelUniversal"
                  binding="#{catalogo_definirArticuloCatalogoBinding.panelPage}">

					<f:facet name="messages">
						<af:messages />
					</f:facet>

					<af:panelBox width="100%" text="#{resCat['DEFINICION_ARTICULO']}"
						id="global" partialTriggers="panelAlmacenes panelUniversal">

						<af:panelBox text="Datos Generales" width="100%" id="id6">

							<af:panelBox text="#{resCat['CLASIFICACION']}" width="100%"
								id="i132f">
								<af:panelHorizontal>
									<af:panelForm>
										<af:inputText wrap="off" disabled="true" rows="5"
											columns="100" id="ird12365"
											value="#{catalogo_definirArticuloCatalogoBean.valorTodasClasificaciones}" />
										<af:panelHorizontal halign="left">
											<af:commandButton text="#{resOrg['SELECCIONAR']}"
												id="adfa452"
												rendered="#{catalogo_definirArticuloCatalogoBean.habilitarBotones}"
												action="#{catalogo_definirArticuloCatalogoBean.accionBoton }"
												useWindow="true"
												binding="#{catalogo_definirArticuloCatalogoBinding.btnSelec}">
											</af:commandButton>
										</af:panelHorizontal>
									</af:panelForm>

									<af:objectSpacer width="10" height="10" />

									<af:panelHorizontal id="menu3" halign="right"
										rendered="#{catalogo_definirArticuloCatalogoBean.habilitarPanelAtributos}">
										<af:panelBox
											text="#{resCat['SELECCIONAR_LOS_ATRIBUTOS_A_USAR']}"
											width="300">
											<af:panelList
												binding="#{catalogo_definirArticuloCatalogoBinding.panelUniversal }"
												inlineStyle="list-style-type:none;" id="panelUniversal" />
										</af:panelBox>
									</af:panelHorizontal>
								</af:panelHorizontal>
							</af:panelBox>


							<af:objectSpacer width="10" height="20" />

							<af:panelHorizontal id="panelHorizontalAtrib"
								binding="#{catalogo_definirArticuloCatalogoBinding.panelHorizontalAtrib}">
								<af:panelForm id="id7">
									<af:inputText id="id9" columns="100" showRequired="true"
										value="#{catalogo_definirArticuloCatalogoBean.valorDescripcionArticulo}"
										readOnly="#{catalogo_definirArticuloCatalogoBean.readOnlyDescripcion}"
										required="false" label="#{resCor['DESCRIPCION']}"
										disabled="#{catalogo_definirArticuloCatalogoBean.deshabilitaDescripcionArticulo}" />
									<af:inputText id="id10" columns="100" showRequired="true"
										value="#{catalogo_definirArticuloCatalogoBean.valorDescripcionAdicionalArticulo}"
										rendered="#{catalogo_definirArticuloCatalogoBean.renderedDescripcionAdicional}"
										required="false" />
									<af:objectSpacer height="10" />
									<af:inputText id="id12" columns="100" showRequired="true"
										value="#{catalogo_definirArticuloCatalogoBean.valorNemotecnicoArticulo}"
										required="false" label="#{resCat['NEMOTECNICO']}" />
									<af:objectSpacer height="10" />
									<af:inputText id="id14"
										disabled="#{catalogo_definirArticuloCatalogoBean.disabled}"
										value="#{catalogo_definirArticuloCatalogoBean.valorCodigoArticulo}"
										label="#{resCor['CODIGO']}" />
									<af:objectSpacer height="10" />
									<af:selectBooleanCheckbox id="id16" showRequired="true"
										selected="#{catalogo_definirArticuloCatalogoBean.valorActivoArticulo}"
										label="#{resCat['ACTIVO']}" />
									<af:objectSpacer height="10" />
									<af:inputText id="id18"
										disabled="#{catalogo_definirArticuloCatalogoBean.disabled}"
										value="#{catalogo_definirArticuloCatalogoBean.valorFechaAltaArticulo}"
										label="#{resCat['FECHA_ALTA']}">
										<f:convertDateTime timeStyle="short" />
									</af:inputText>
									<af:objectSpacer height="10" />
									<af:inputText id="id20" columns="100" rows="3"
										value="#{catalogo_definirArticuloCatalogoBean.valorObservacionesArticulo}"
										label="#{resCor['OBSERVACIONES']}" />
									<af:objectSpacer height="10" />
									<af:selectOneChoice id="unidadMedidaArticulo"
										showRequired="true"
										value="#{catalogo_definirArticuloCatalogoBean.valorUnidadMedidaLogisticaArticulo}"
										readOnly="#{catalogo_definirArticuloCatalogoBean.readOnlyUnidadMedidaLogistica}"
										required="false" unselectedLabel=""
										label="#{resCat['UNIDAD_MEDIDA_LOGISTICA']}">
										<f:selectItems id="itemsUnidadMedida"
											value="#{catalogo_definirArticuloCatalogoBean.itemsUnidadMedida}" />
									</af:selectOneChoice>
									<af:objectSpacer height="10" />
									<af:selectOneChoice id="unidadContratacionArticulo"
										showRequired="true"
										value="#{catalogo_definirArticuloCatalogoBean.valorUnidadContratacionArticulo}"
										readOnly="#{catalogo_definirArticuloCatalogoBean.readOnlyUnidadContratacion}"
										required="false" unselectedLabel=""
										label="#{resCat['UNIDAD_CONTRATACION']}">
										<f:selectItems id="itemsUnidadContratacion"
											value="#{catalogo_definirArticuloCatalogoBean.itemsUnidadContratacion}" />
									</af:selectOneChoice>
								</af:panelForm>
							</af:panelHorizontal>

							<af:objectSpacer height="10" width="10" />

							<af:panelHorizontal>
								<af:panelForm id="panelArticulo"
									binding="#{catalogo_definirArticuloCatalogoBinding.panelArticulo}"
									partialTriggers="menu3 global panelPage panelHorizontalAtrib" />
							</af:panelHorizontal>

							<af:objectSpacer height="10" />

						</af:panelBox>

						<af:objectSpacer width="10" height="20" />

						<af:panelBox
							text="Datos particulares para #{catalogo_definirArticuloCatalogoBean.organoGestorDelUsuario.descripcion}"
							width="100%">
							<af:panelHorizontal halign="left">
								<af:panelForm id="panelCentros" width="100%"
									binding="#{catalogo_definirArticuloCatalogoBinding.panelCentros}"
									partialTriggers="menu3 global panelPage panelArticulo panelHorizontalAtrib">
								</af:panelForm>
							</af:panelHorizontal>
						</af:panelBox>

						<af:objectSpacer height="10" />

						<af:panelHorizontal halign="right">
							<af:menuTabs startDepth="10"
								rendered="#{catalogo_definirArticuloCatalogoBean.almacenable}"
								binding="#{catalogo_definirArticuloCatalogoBinding.menuTab}" >
								<af:forEach var="item" varStatus="varStatus"
									items="#{catalogo_definirArticuloCatalogoBean.listaAlmacenesPestana}">
									<af:commandMenuItem text="#{item.almacen.descripcion}"
										actionListener="#{catalogo_definirArticuloCatalogoBean.seleccionAlmacen}"
										selected="#{catalogo_definirArticuloCatalogoBean.numPestanaAlmacenSeleccionada == varStatus.index}" />
								</af:forEach>
							</af:menuTabs>
						</af:panelHorizontal>
	
       <af:panelBox text="#{resCat['DATOS_POR_ALMACEN']}" width="100%"
							id="panelBox"
							rendered="#{catalogo_definirArticuloCatalogoBean.almacenable}"
                    binding="#{catalogo_definirArticuloCatalogoBinding.panelBox}">
							<af:panelHorizontal>
         <af:panelForm id="panelAlmacenes" width="100%"
                       binding="#{catalogo_definirArticuloCatalogoBinding.panelAlmacenes}"/>
         <af:outputLabel value="#{resCat['NINGUN_ALMACEN_GESTIONA_EL_ARTICULO']}"
                              
                         rendered="#{catalogo_definirArticuloCatalogoBean.etiquetaAlmacenes}"/>
        </af:panelHorizontal>
							
							<af:objectSpacer height="10" />
							
							<af:panelHorizontal halign="right">
								<af:commandButton text="Añadir"
									binding="#{catalogo_definirArticuloCatalogoBinding.btnAnadir}"
									action="#{catalogo_definirArticuloCatalogoBean.accionAlmacenes}"
									rendered="true" />
							</af:panelHorizontal>
       </af:panelBox>

						<af:objectSpacer width="10" height="20" />

						<af:panelHorizontal halign="right">
							<af:commandButton text="#{resCor['ACEPTAR']}" rendered="true"
								id="id24"
								action="#{catalogo_definirArticuloCatalogoBean.guardarArticulo }"
								binding="#{catalogo_definirArticuloCatalogoBinding.btnGuardar}" />
							<af:objectSpacer width="10" />
							<af:commandButton text="#{resCat['CREAR_NUEVO_ARTICULO']}"
								rendered="#{catalogo_definirArticuloCatalogoBean.habilitarBotones}"
								id="btnCrearArticulo" partialSubmit="true"
								action="#{catalogo_definirArticuloCatalogoBean.crearNuevoArticulo}" />
							<af:objectSpacer width="10" />
							<af:commandButton text="#{resCor['CANCELAR']}"
								action="#{catalogo_definirArticuloCatalogoBean.volver}"
								rendered="#{catalogo_definirArticuloCatalogoBean.mostrarVolver}" />
       </af:panelHorizontal>

					</af:panelBox>
					<af:commandButton text="#{resCor['VOLVER']}"
									action="#{catalogo_definirArticuloCatalogoBean.accionVolver}" />
				</af:panelPage>
			</h:form>
		</afh:body>
		</afh:html>
	</f:view>
</jsp:root>
