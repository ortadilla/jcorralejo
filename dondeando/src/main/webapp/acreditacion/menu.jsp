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
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<f:loadBundle basename="mensajesPed" var="resPed" />
		<f:loadBundle basename="mensajesEmp" var="resEmp" />
		<f:loadBundle basename="mensajesAcr" var="resAcr" />


		<trh:html>
		<trh:head title="#{resAcr['MENU_SAL']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="shortcut icon" href="../faviconSAL.ico" type="image/x-icon" />
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
			
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina botonMenu="false" />
			<tr:form>
				<tr:messages />
				<trh:tableLayout width="100%" cellSpacing="20" inlineStyle="width: 100%">
				<tr:forEach items="#{organizacion_menu.filasSAL}" var="fila">
					<trh:rowLayout valign="top" inlineStyle="100%">
					<tr:forEach items="#{fila}" var="grupo">
						<trh:cellFormat width="50%">
							<tr:panelBox background="dark" text="#{grupo.descripcion}" inlineStyle="width: 100%">
								<tr:panelList>
								<tr:forEach items="#{grupo.opciones}" var="menuItem">
									<tr:goLink text="#{menuItem.descripcion}" rendered="#{menuItem.viewId!=null}"
										destination="#{menuItem.viewId}"/>
									<tr:commandLink text="#{menuItem.descripcion}" rendered="#{menuItem.action!=null or menuItem.metodoAction!=null}"
										actionListener="#{menuItem.actionListener}"
										action="#{menuItem.action}">
										<tr:attribute name="core_app" value="1"/>
									</tr:commandLink>
									<tr:outputText value="#{menuItem.descripcion}" rendered="#{menuItem.viewId==null and menuItem.action==null and menuItem.metodoAction==null}"
									    inlineStyle="font-size: 12px"/>
								</tr:forEach>
								</tr:panelList>
							</tr:panelBox>
						</trh:cellFormat>
					</tr:forEach>
					</trh:rowLayout>
				</tr:forEach>
				</trh:tableLayout>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
