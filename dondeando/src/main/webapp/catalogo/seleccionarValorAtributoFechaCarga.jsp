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
		<trh:head title="#{resCat['SELECCIONAR_VALOR_FECHA_ATRIBUTO_FECHA_CARGA']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		</trh:head>
		<trh:body>
		<!-- DIV flotante para bloquear la pantalla en eventos PPR -->
			<tr:statusIndicator id="indicador">
				<f:facet name="busy">
					<f:verbatim>
							<div id="divEspera">
								<p style="margin-top: 60px; text-align:center; width: 100%;">[    Cargando datos, por favor espere...    ]</p>
							</div>
					</f:verbatim>
				</f:facet>
				<f:facet name="ready">
				</f:facet>
			</tr:statusIndicator>
			
			<tr:panelFormLayout>
				<tr:form onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
				<tr:panelHeader text="#{resCat['SELECCIONAR_LA_FECHA_CARGA_MASIVA']}"/>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					
						<tr:inputDate
							value="#{catalogo_seleccionarValorAtributoFechaCargaBean.fecha}"
							columns="12" />

				</tr:panelPage>
					<tr:panelButtonBar>
					<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
						action="#{catalogo_seleccionarValorAtributoFechaCargaBean.aceptar}" />
					<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar"
						action="#{catalogo_seleccionarValorAtributoFechaCargaBean.cancelar}" />
				</tr:panelButtonBar>
				</tr:form>
			</tr:panelFormLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>