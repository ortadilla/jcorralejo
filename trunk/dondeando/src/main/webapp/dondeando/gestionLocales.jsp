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
				<tr:messages />
				<tr:panelHeader text="#{resCore['GESTION_LOCALES']}" />
<!-- 
				<tr:showDetailHeader text="#{resCore['CRITERIOS_BUSQUEDA']}"
					disclosed="#{gestionLocalesBean.desplegado}" id="showDetail"
					partialTriggers="showDetail"
					binding="#{gestionUsuariosBinding.busqueda}">
					<tr:panelBox inlineStyle="width:100%;" background="medium" partialTriggers="btnLimpiar">
						<tr:panelHorizontalLayout halign="center">
						<trh:tableLayout cellSpacing="5" cellPadding="0">
							<trh:rowLayout>
								<tr:outputText value="#{resCore['NOMBRE']}"
									inlineStyle="font-weight: bolder;" />
								<tr:inputText columns="40" partialTriggers=":::btnLimpiar"
									value="#{gestionLocalesBean.criterioNombre}"
									id="criterioNombre" simple="true" />
							</trh:rowLayout>
							<trh:rowLayout>
								<tr:outputText value="#{resCore['ACTIVO']}"
									inlineStyle="font-weight: bolder;" />
								<tr:selectBooleanCheckbox value="#{gestionLocalesBean.criterioActivo}" />
							</trh:rowLayout>
						</trh:tableLayout>
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['BUSCAR']}" id="btnBuscar"
								action="#{gestionLocalesBean.buscar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['LIMPIAR']}" id="btnLimpiar"
								immediate="true" action="#{gestionLocalesBean.limpiar}" />
						</tr:panelHorizontalLayout>
					</tr:panelBox>
				</tr:showDetailHeader>

				<tr:spacer width="10" height="20" />

				<tr:panelBox inlineStyle="width:100%;" text="#{resCore['USUARIOS']}">
					<tr:panelGroupLayout>
						<tr:panelButtonBar>
							<tr:commandButton text="#{resCore['DETALLES_USUARIO']}"
								id="btnDetalles" action="#{gestionLocalesBean.detalles}" />
							<tr:commandButton text="#{resCore['MODIFICAR_USUARIO']}"
								id="btnModificar" action="#{gestionLocalesBean.modificar}" />
							<tr:commandButton text="#{resCore['ELIMINAR_USUARIO']}"
								id="btnEliminar" action="#{gestionLocalesBean.eliminar}" 
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_USUARIO']}')"/>
							<tr:commandButton text="#{resCore['RECUPERAR_USUARIO']}"
								id="btnRecuperar" action="#{gestionLocalesBean.recuperar}"/>
						</tr:panelButtonBar>
					</tr:panelGroupLayout>
					<tr:spacer height="10"/>
					<tr:table var="var" first="0"
						emptyText="#{resCore['NO_ELEMENTOS']}" rows="20" width="100%"
						value="#{gestionLocalesBean.listaUsuarios}"
						rowBandingInterval="1" columnBandingInterval="0"
						selectedRowKeys="#{gestionLocalesBean.estadoDeSeleccionTabla}"
						rowSelection="multiple" id="listaUsuarios">
						<tr:column sortable="true" headerText="#{resCore['USUARIO']}">
							<tr:outputText value="#{var.login}" />
						</tr:column>
						<tr:column sortable="true"
							headerText="#{resCore['NOMBRE_COMPLETO']}">
							<tr:outputText value="#{var.nombreCompleto}" />
						</tr:column>
						<tr:column sortable="true" headerText="#{resCore['KARMA']}">
							<tr:outputText value="#{var.karma}" />
						</tr:column>
						<tr:column sortable="true" headerText="#{resCore['TIPO_USUARIO']}">
							<tr:outputText value="#{var.tipoUsuario.descripcion}" />
						</tr:column>
						<tr:column sortable="false" headerText="#{resCore['ACTIVO']}">
							<tr:image source="#{var.activo ? '/imagenes/checkbox_verde_si.png':'/imagenes/checkbox_verde_no.png'}"/>
						</tr:column>
					</tr:table>
				</tr:panelBox>
 -->
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>