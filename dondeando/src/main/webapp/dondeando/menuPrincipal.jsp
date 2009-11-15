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
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<trh:html>

		<trh:head>
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
		</trh:head>

		<trh:body>
			<geos:cabeceraPagina />
			<tr:form>
				<tr:panelPage>
					<tr:messages />

					<tr:spacer height="20" />
					<tr:panelBox inlineStyle="width: 100%;" background="light">
						<tr:panelHorizontalLayout halign="center"	inlineStyle="width: 100%;">
							<tr:image source="/imagenes/dondeando/iconoTransparente.png"
										inlineStyle="height: 30px;"/>
							<tr:commandLink rendered="#{menuPrincipalBean.mostrarGestionUsuarios}"
								action="#{menuPrincipalBean.gestionUsuarios}">
								<tr:image source="/imagenes/dondeando/gestionarUsuarios.png"
										shortDesc="#{resCore['GESTION_USUARIOS']}" 
										inlineStyle="height: 30px;"/>
							</tr:commandLink>
							<tr:spacer height="20" />
							<tr:commandLink rendered="#{menuPrincipalBean.mostrarGestionLocales}"
								action="#{menuPrincipalBean.gestionLocales}" >
								<tr:image source="/imagenes/dondeando/gestionarLocales2.png"
										shortDesc="#{resCore['GESTION_LOCALES']}" 
										inlineStyle="height: 30px;"/>
							</tr:commandLink>
							<tr:spacer height="20" />
							<tr:commandLink rendered="#{menuPrincipalBean.mostrarGestionForos}"
								action="#{menuPrincipalBean.gestionForos}" >
								<tr:image source="/imagenes/dondeando/gestionarForos.png"
										shortDesc="#{resCore['GESTION_FOROS']}" 
										inlineStyle="height: 30px;"/>
							</tr:commandLink>
						</tr:panelHorizontalLayout>

						<trh:tableLayout inlineStyle="width: 100%;" cellSpacing="20" cellPadding="0">
							<trh:rowLayout>
								<trh:cellFormat rowSpan="2" width="20%">
									<tr:panelBox background="medium" 
										inlineStyle="border-style: solid; border-width: 1px; width:100%">
										<tr:outputText value="#{resCore['LOCALES']}"
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;" />
										<tr:spacer height="20" />
										<tr:outputText value="#{resCore['DONDE_COMER']}"
											inlineStyle="font-weight: bolder;" />
										<tr:selectOneChoice value="#{menuPrincipalBean.provincia}">
											<f:selectItems id="selectProvincia"
												value="#{menuPrincipalBean.selectProvincia}" />
										</tr:selectOneChoice>
										<tr:spacer height="20" />
										
										<tr:outputText value="#{resCore['QUE_COMER']}"
											inlineStyle="font-weight: bolder;" />
										<tr:spacer height="5" />
										<tr:iterator var="item" varStatus="varStatus" id="iteradorTipoLocales"
											value="#{menuPrincipalBean.listaTiposLocales}">
											<tr:commandLink text="· #{item.descripcion}" 
												action="#{menuPrincipalBean.accionTipoLocal}"
												actionListener="#{menuPrincipalBean.accionListenerTipoLocal}">
												<tr:attribute name="idTipoLocal" value="#{item.id}" />
											</tr:commandLink>
										<tr:spacer height="5" />
										</tr:iterator>	
										
										<tr:spacer height="20" />
										<tr:commandLink action="#{menuPrincipalBean.accionTipoLocal}" 
											text="#{resCore['BUSQUEDA_AVANZADA']}" inlineStyle="font-weight: bolder;"/>
									</tr:panelBox>
								</trh:cellFormat>
								<trh:cellFormat rowSpan="3" width="60%">
									<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px; width: 100%;">
										<tr:outputText value="#{resCore['LOCALES_DESTACADOS']}" 
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
										<tr:spacer height="20" />
										<tr:iterator var="item" varStatus="varStatus" id="iteradorDestacados"
											value="#{menuPrincipalBean.listaDestacados}">
											<tr:panelBox inlineStyle="border-style: solid; border-width: 1px; width: 97%;">

												<trh:tableLayout inlineStyle="width: 100%;"
													cellSpacing="10" cellPadding="0">
													<trh:rowLayout>
														<tr:panelHorizontalLayout>
															<tr:commandLink text="#{item.nombre}"
																inlineStyle="font-weight: bolder; font-size: 150%;"
																action="#{menuPrincipalBean.accionLocalConcreto}"
																actionListener="#{menuPrincipalBean.accionLocalConcreto}">
																<tr:attribute name="idLocal" value="#{item.id}" />
															</tr:commandLink>
															<tr:spacer width="5" height="5" />
															<tr:outputText value="#{item.valoracion}"
																inlineStyle="font-weight: bolder; font-size: 150%; color:  #60901a;" />
														</tr:panelHorizontalLayout>
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat columnSpan="2">
														<tr:outputText
															value="#{resCore['TIPO_LOCAL']}: #{item.cadenaTiposLocal}"
															inlineStyle="font-style: italic; font-size: 80%;" />
														</trh:cellFormat>
														<trh:cellFormat halign="right">
															<tr:image shortDesc="#{item.shortDescPrecio}"
																source="#{item.imagenPrecio}"
																inlineStyle="height: 20px;" />
														</trh:cellFormat>
													</trh:rowLayout>
													<trh:rowLayout>
														<tr:goLink destination="#{item.urlVerMapa}"
																inlineStyle="font-style: italic; font-size: 80%;" 
																text="#{item.direccionHumana}" targetFrame="_blank"
																onclick="window.open(this.href, this.target, 'width=800,height=600'); return false;" />
													</trh:rowLayout>
													<trh:rowLayout>
														<trh:cellFormat columnSpan="2"  inlineStyle="text-align: justify;">
															<tr:outputText value="#{item.descripcion}" />
														</trh:cellFormat>
														<trh:cellFormat halign="right" rendered="#{item.tieneImagenes}">
															<tr:image shortDesc="#{item.descripcionPrimeraImagen}" 
																source="#{item.urlPrimeraImagen}"
																inlineStyle="height: 100px;border-style: solid; border-width: 1px; border-color:#60901a;" />
														</trh:cellFormat>
													</trh:rowLayout>
												</trh:tableLayout>
											</tr:panelBox>
											<tr:spacer height="10" />
										</tr:iterator>	
										<tr:spacer height="20" />
									</tr:panelBox>
								</trh:cellFormat>
								<trh:cellFormat width="20%">
									<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px; width: 100%;">
										<tr:outputText value="#{resCore['ULTIMAS_OPINIONES']}" 
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
										<tr:spacer height="20" />
										<tr:iterator var="item" varStatus="varStatus" id="iteradorOpiniones"
											value="#{menuPrincipalBean.listaOpiniones}">
											<tr:commandLink text="· #{item.resumen}" 
												action="#{menuPrincipalBean.accionOpiniones}"
												actionListener="#{menuPrincipalBean.accionListenerOpiniones}">
												<tr:attribute name="idOpinion" value="#{item.id}" />
											</tr:commandLink>
											<tr:spacer height="5" />
										</tr:iterator>	
										<tr:spacer height="20" />
									</tr:panelBox>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px; width: 100%;">
									<tr:outputText value="#{resCore['ULTIMAS_VALORACIONES']}" 
										inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
									<tr:spacer height="20" />
									<tr:iterator var="item" varStatus="varStatus" id="iteradorOpiniones"
										value="#{menuPrincipalBean.listaValoraciones}">
										<tr:commandLink text="· #{item.descripcion}" 
											action="#{menuPrincipalBean.accionValoraciones}"
											actionListener="#{menuPrincipalBean.accionListenerValoraciones}">
											<tr:attribute name="idValoracion" value="#{item.id}" />
										</tr:commandLink>
										<tr:spacer height="5" />
									</tr:iterator>	
									<tr:spacer height="20" />
								</tr:panelBox>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat width="20%">
									<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px; width: 100%;">
										<tr:outputText value="#{resCore['FOROS']}" 
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
										<tr:spacer height="20" />
										<tr:iterator var="item" varStatus="varStatus" id="iteradorValoraciones"
											value="#{menuPrincipalBean.listaForos}">
											<tr:commandLink text="· #{resCore['ACCESO_FORO']} #{item.titulo}" 
												action="#{menuPrincipalBean.accionForos}"
												actionListener="#{menuPrincipalBean.accionListenerForo}">
												<tr:attribute name="idForo" value="#{item.id}" />
											</tr:commandLink>
										<tr:spacer height="5" />
										</tr:iterator>	
										<tr:spacer height="20" />
									</tr:panelBox>
								</trh:cellFormat>
								<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px; width: 100%;">
									<tr:outputText value="#{resCore['ULTIMOS_MENSAJES']}" 
										inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
									<tr:spacer height="20" />
									<tr:iterator var="item" varStatus="varStatus" id="iteradorMensajes"
										value="#{menuPrincipalBean.listaMensajes}">
										<tr:commandLink text="· #{item.descripcion}" 
											action="#{menuPrincipalBean.accionMensajes}"
											actionListener="#{menuPrincipalBean.accionListenerMensajes}">
											<tr:attribute name="idMensaje" value="#{item.id}" />
										</tr:commandLink>
										<tr:spacer height="5" />
									</tr:iterator>	
									<tr:spacer height="20" />
								</tr:panelBox>
							</trh:rowLayout>
						</trh:tableLayout>

					</tr:panelBox>
					<tr:spacer width="10" />
				</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
