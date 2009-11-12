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
								action="#{menuPrincipalBean.gestionUsuarios}" >
								<tr:image source="/imagenes/dondeando/gestionarUsuarios.png"
										shortDesc="#{resCore['GESTION_USUARIOS']}" 
										inlineStyle="height: 30px;"/>
							</tr:commandLink>
							<tr:spacer height="20" />
							<tr:commandLink rendered="#{menuPrincipalBean.mostrarGestionLocales}"
								action="#{menuPrincipalBean.gestionLocales}" >
								<tr:image source="/imagenes/dondeando/gestionarLocales.png"
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
								<trh:cellFormat rowSpan="2">
									<tr:panelBox background="medium" 
										inlineStyle="border-style: solid; border-width: 1px;">
										<tr:panelHorizontalLayout halign="center">
											<tr:outputText value="#{resCore['LOCALES']}" 
												inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
										</tr:panelHorizontalLayout>
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
								<trh:cellFormat rowSpan="3">
									<tr:panelBox inlineStyle="border-style: solid; border-width: 1px;">
										<tr:outputText value="MEJORES LOCALES" />
									</tr:panelBox>
								</trh:cellFormat>
								<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px;">
									<tr:panelHorizontalLayout halign="center">
										<tr:outputText value="#{resCore['ULTIMAS_OPINIONES']}" 
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
									</tr:panelHorizontalLayout>
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
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:panelBox inlineStyle="border-style: solid; border-width: 1px;">
									<tr:outputText value="ULTIMAS VOTACIONES" />
								</tr:panelBox>
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:panelBox  background="medium"  inlineStyle="border-style: solid; border-width: 1px;">
									<tr:panelHorizontalLayout halign="center">
										<tr:outputText value="#{resCore['FOROS']}" 
											inlineStyle="font-weight: bolder; font-size: 150%; color: #60901a;"/>
									</tr:panelHorizontalLayout>
									<tr:spacer height="20" />
									<tr:iterator var="item" varStatus="varStatus" id="iteradorForos"
										value="#{menuPrincipalBean.listaForos}">
										<tr:commandLink text="· #{item.titulo}" 
											action="#{menuPrincipalBean.accionForos}"
											actionListener="#{menuPrincipalBean.accionListenerForo}">
											<tr:attribute name="idForo" value="#{item.id}" />
										</tr:commandLink>
									<tr:spacer height="5" />
									</tr:iterator>	
									<tr:spacer height="20" />
								</tr:panelBox>
								<tr:panelBox inlineStyle="border-style: solid; border-width: 1px;">
									<tr:outputText value="ULTIMOS MENSAJES" />
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
