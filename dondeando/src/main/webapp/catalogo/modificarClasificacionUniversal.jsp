<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:tr="http://myfaces.apache.org/trinidad"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:geos="http://www.hp-cda.com/adf/faces">
 <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
 <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
	<f:view>
		<f:loadBundle basename="mensajesCore" var="resCore" />
		<f:loadBundle basename="mensajesCat" var="resCat" />
		<trh:html>
		<trh:head title="#{resCat['MOD_CLASIF_UNIVERSAL_ARTICULO']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        	<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
			<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
				<tr:panelPage>
					<tr:panelHeader text="#{resCat['MOD_CLASIF_UNIVERSAL_ARTICULO']}" />
					<tr:panelBox inlineStyle="width: 100%;">

						<tr:panelHorizontalLayout halign="center">
							<tr:panelBox text="#{resCat['MENSAJE_MODIFIC_CLASIF_UNIV_ARTI_CABECERA']}" 
										 inlineStyle="border-style: solid; border-width: 1px; border-color: 660033; ">
								<tr:outputFormatted styleUsage="instruction" value="#{resCat['MENSAJE_MODIFIC_CLASIF_UNIV_ARTI']}" />
							</tr:panelBox>											
						</tr:panelHorizontalLayout>
						
						<tr:spacer width="10" height="20" />
						<tr:form onsubmit="bloquearPantalla(this);">
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['NIVELVALORORIGEN']}" /> 
								<tr:spacer width="10" />
								<tr:inputText wrap="off" disabled="true" rows="1" columns="100"
									value="#{catalogo_modificarClasificacionUniversal.clasificacionOrigen}" 
									partialTriggers="seleccionarArticulo"/>
								<tr:commandLink id="seleccionarArticulo"
									action="#{catalogo_modificarClasificacionUniversal.seleccionarArticuloOrigen}"
									returnListener="#{catalogo_modificarClasificacionUniversal.returnSeleccionarArticuloOrigen}"
									useWindow="true" windowHeight="600" windowWidth="800" >
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink id="botonBorrarArticuloOrigen"
									action="#{catalogo_modificarClasificacionUniversal.borrarArticuloOrigen}">
									<tr:image source="../imagenes/btnEliminar.gif" />
									</tr:commandLink>
							</tr:panelHorizontalLayout>

							<tr:spacer width="10" height="10" />
						
							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['CLASIFICACION_UNIVERSAL']}" /> 
								<tr:spacer width="10" />
								<tr:inputText wrap="off" disabled="true" rows="1" columns="100"
									value="#{catalogo_modificarClasificacionUniversal.clasificacionDestino}" 
									partialTriggers="seleccionarFamilia"/>
								<tr:commandLink id="seleccionarFamilia"
									action="#{catalogo_modificarClasificacionUniversal.seleccionarFamiliaDestino}"
									returnListener="#{catalogo_modificarClasificacionUniversal.returnSeleccionarFamiliaDestino}"
									useWindow="true" windowHeight="600" windowWidth="800" >
									<tr:image source="../imagenes/lupa3.gif" />
								</tr:commandLink>
								<tr:commandLink id="botonBorrarArticuloOrigen"
									action="#{catalogo_modificarClasificacionUniversal.borrarFamiliaDestino}">
									<tr:image source="../imagenes/btnEliminar.gif" />
									</tr:commandLink>
							</tr:panelHorizontalLayout>

							<tr:spacer width="10" height="10" />

							<tr:panelHorizontalLayout>
								<tr:outputText value="#{resCat['CODARTICULO']}" />
								<tr:selectOneChoice unselectedLabel="" value="#{catalogo_modificarClasificacionUniversal.digitoCodigoArticulo}"
													id="codigoArticulo">
									<f:selectItems 	value="#{catalogo_modificarClasificacionUniversal.selectItemDigitos}" />
								</tr:selectOneChoice>
								<tr:spacer width="10" />
								<tr:outputText value="#{resCat['INDICAR_1_DIGITO_COD_ARTICULO']}" />
							</tr:panelHorizontalLayout>

							<tr:spacer width="10" height="20" />
							
							<tr:panelHorizontalLayout>
								<tr:commandButton text="#{resCore['ACEPTAR']}" 
									action="#{catalogo_modificarClasificacionUniversal.aceptar}" 
									returnListener="#{catalogo_modificarClasificacionUniversal.returnAceptar}"
									useWindow="true" windowHeight="600" windowWidth="800"/>
							</tr:panelHorizontalLayout>
							
						</tr:form>
					</tr:panelBox>
					<f:facet name="messages">
					<tr:messages />
            	</f:facet>
			</tr:panelPage>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
