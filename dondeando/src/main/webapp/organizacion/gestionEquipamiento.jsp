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
		<trh:head title="#{resOrg['SELECCION_EQUIPAMIENTO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
				<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina/>
			<tr:panelFormLayout>
				<tr:form onsubmit="bloquearPantalla(this);">
					<tr:panelPage>
						<f:facet name="messages">
							<tr:messages />
						</f:facet>
						<tr:panelLabelAndMessage label="#{resOrg['TIPOEQUIP']}">
							<tr:selectOneChoice id="tipoEquipamiento"
								value="#{organizacion_gestionEquipamientoBean.valorTipoEquipamiento}"
								valueChangeListener="#{organizacion_gestionEquipamientoBean.cambiaTipoEquipamiento}"
								autoSubmit="true">
								<f:selectItems id="itemsClasificacion"
									value="#{organizacion_gestionEquipamientoBean.selectItemTipoEquipamiento}" />
							</tr:selectOneChoice>
						</tr:panelLabelAndMessage>
						<tr:spacer height="10" width="10" />
						<tr:iterator var="item" varStatus="varStatus"
							value="#{organizacion_gestionEquipamientoBean.listaEquipamientosConSelectItem}">
							<tr:panelLabelAndMessage label="#{item.descripcionEquipamiento}">
								<tr:selectOneChoice value="#{item.equipamiento}"
									valueChangeListener="#{organizacion_gestionEquipamientoBean.cambiaEquipamiento}"
									autoSubmit="true">
									<f:selectItems value="#{item.valores}" />
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
							<tr:spacer height="10" />
						</tr:iterator>
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCor['ACEPTAR']}"
								action="#{organizacion_gestionEquipamientoBean.aceptar}" />
							<tr:spacer width="10" />
							<tr:commandButton text="#{resCor['CANCELAR']}"
								action="#{organizacion_gestionEquipamientoBean.cancelar}" />
							<tr:spacer width="10" />
						</tr:panelHorizontalLayout>
					</tr:panelPage>
				</tr:form>
			</tr:panelFormLayout>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>