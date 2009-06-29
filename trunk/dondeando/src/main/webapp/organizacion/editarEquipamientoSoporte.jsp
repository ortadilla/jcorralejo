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
		<f:loadBundle basename="mensajesCore" var="mensajesCore" />
		<f:loadBundle basename="mensajesOrg" var="mensajesOrg" />
		<trh:html>
		<trh:head title="#{mensajesOrg['EQUIPAMIENTO_SOPORTE_INSTALACIONES_LOGISTICAS']}">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<trh:script source="/include/libreriaGEOS.js">
        		<!-- No borrar este comentario. Puesto para que los navegadores interpreten bien esta etiqueta. -->
			</trh:script>
		<tr:outputText escape="false" value="#{htmlHead}"/>
		</trh:head>
		<trh:body>
			<geos:cabeceraPagina />
			<tr:form defaultCommand="aceptar" onsubmit="bloquearPantalla(this);">
				<tr:panelPage>
				<tr:panelHeader text="#{organizacion_editarEquipamientoSoporteBean.tituloPanel}"/>
				 <f:facet name="messages">
							<tr:messages />
					</f:facet>
				 	<tr:panelBox inlineStyle="width: 100%;" background="dark">
					<tr:panelHorizontalLayout halign="center">
						<trh:tableLayout width="100%" cellSpacing="20" inlineStyle="width: 100%">
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{mensajesOrg['PUNTOACCESO']}#{mensajesCore['REQUERIDO']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:selectBooleanCheckbox value="#{organizacion_editarEquipamientoSoporteBean.puntoAccesoARedCorporativa}"
									disabled="#{organizacion_editarEquipamientoSoporteBean.desdeMS}"/>
								</trh:cellFormat>							
							</trh:rowLayout>
							<trh:rowLayout rendered="#{organizacion_editarEquipamientoSoporteBean.mostrarTablaUbicaciones}">
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{organizacion_editarEquipamientoSoporteBean.nombreTipoEquipamientoUbicaciones}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout> 
										<tr:table value="#{organizacion_editarEquipamientoSoporteBean.listaEquipamientoVistaUbicaciones}" var="var"
											inlineStyle="vertical-align:middle;" rows="0">
											<tr:column inlineStyle="vertical-align:middle;font-weight:bold;">
												<tr:outputText value="#{var.descripcionEquipamiento}" />
											</tr:column>
											<f:facet name="detailStamp">
											<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
												value="#{var.listaValorAtributoVista}" var="var1" width="100%"
												rows="0">
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.atributo}"
														inlineStyle="font-weight:bold;" />
												</tr:column>
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.valor}" />
												</tr:column>
												<tr:column>
													<tr:image source="#{var1.imagenEstadoSolicitud}"/>
												</tr:column>
											</tr:table>
											</f:facet>
										</tr:table>
										<tr:commandLink action="#{organizacion_editarEquipamientoSoporteBean.irAUbicaciones}"
											rendered="#{!organizacion_editarEquipamientoSoporteBean.desdeMS}">
	        	           					<tr:image source="../imagenes/lupa3.gif"/>
					               		</tr:commandLink>
				               		</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout rendered="#{organizacion_editarEquipamientoSoporteBean.mostrarTablaMediosOperacion}">
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{organizacion_editarEquipamientoSoporteBean.nombreTipoEquipamientoMediosOperacion}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout>
										<tr:table value="#{organizacion_editarEquipamientoSoporteBean.listaEquipamientoVistaMediosOperacion}" var="var"
											inlineStyle="vertical-align:middle;" rows="0">
											<tr:column inlineStyle="vertical-align:middle;font-weight:bold;">
												<tr:outputText value="#{var.descripcionEquipamiento}" />
											</tr:column>
											<f:facet name="detailStamp">
											<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
												value="#{var.listaValorAtributoVista}" var="var1" width="100%"
												rows="0">
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.atributo}"
														inlineStyle="font-weight:bold;" />
												</tr:column>
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.valor}" />
												</tr:column>
												<tr:column>
													<tr:image source="#{var1.imagenEstadoSolicitud}"/>
												</tr:column>
											</tr:table>
											</f:facet>
										</tr:table>
										<tr:commandLink action="#{organizacion_editarEquipamientoSoporteBean.irAMediosOperacion}"
											rendered="#{!organizacion_editarEquipamientoSoporteBean.desdeMS}">
	        	           					<tr:image source="../imagenes/lupa3.gif"/>
					               		</tr:commandLink>
				               		</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout rendered="#{organizacion_editarEquipamientoSoporteBean.mostrarTablaConservacionEspecial}">
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{organizacion_editarEquipamientoSoporteBean.nombreTipoEquipamientoConservacionEspecial}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout>
										<tr:table value="#{organizacion_editarEquipamientoSoporteBean.listaEquipamientoVistaConservacionEspecial}" var="var"
											inlineStyle="vertical-align:middle;" rows="0">
											<tr:column inlineStyle="vertical-align:middle;font-weight:bold;">
												<tr:outputText value="#{var.descripcionEquipamiento}" />
											</tr:column>
											<f:facet name="detailStamp">
											<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
												value="#{var.listaValorAtributoVista}" var="var1" width="100%"
												rows="0">
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.atributo}"
														inlineStyle="font-weight:bold;" />
												</tr:column>
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.valor}" />
												</tr:column>
												<tr:column>
													<tr:image source="#{var1.imagenEstadoSolicitud}"/>
												</tr:column>
											</tr:table>
											</f:facet>
										</tr:table>
										<tr:commandLink action="#{organizacion_editarEquipamientoSoporteBean.irAConservacionEspecial}"
											rendered="#{!organizacion_editarEquipamientoSoporteBean.desdeMS}">
	        	           					<tr:image source="../imagenes/lupa3.gif"/>
					               		</tr:commandLink>
				               		</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout rendered="#{organizacion_editarEquipamientoSoporteBean.mostrarTablaLectores}">
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{organizacion_editarEquipamientoSoporteBean.nombreTipoEquipamientoLectores}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:panelHorizontalLayout>
										<tr:table value="#{organizacion_editarEquipamientoSoporteBean.listaEquipamientoVistaLectores}" var="var"
											inlineStyle="vertical-align:middle;" rows="0">
											<tr:column inlineStyle="vertical-align:middle;font-weight:bold;">
												<tr:outputText value="#{var.descripcionEquipamiento}" />
											</tr:column>
											<f:facet name="detailStamp">
											<tr:table emptyText="#{mensajesCore['NO_ELEMENTOS']}"
												value="#{var.listaValorAtributoVista}" var="var1" width="100%"
												rows="0">
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.atributo}"
														inlineStyle="font-weight:bold;" />
												</tr:column>
												<tr:column sortable="false" headerText="">
													<tr:outputText value="#{var1.obtenerValorReal}" />
												</tr:column>
												<tr:column>
													<tr:image source="#{var1.imagenEstadoSolicitud}"/>
												</tr:column>
											</tr:table>
											</f:facet>
										</tr:table>
										<tr:commandLink action="#{organizacion_editarEquipamientoSoporteBean.irALectores}"
											rendered="#{!organizacion_editarEquipamientoSoporteBean.desdeMS}">
	        	           					<tr:image source="../imagenes/lupa3.gif"/>
					               		</tr:commandLink>
				               		</tr:panelHorizontalLayout>
								</trh:cellFormat>
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{mensajesOrg['SWGESTION']}#{mensajesCore['REQUERIDO']}" />
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputText value="#{organizacion_editarEquipamientoSoporteBean.swGestion}"
										disabled="#{organizacion_editarEquipamientoSoporteBean.desdeMS}"/>
								</trh:cellFormat>							
							</trh:rowLayout>
							<trh:rowLayout>
								<trh:cellFormat>
									<tr:outputLabel styleClass="etiquetaForm" value="#{mensajesOrg['MOTIVOSOLICITUD']}#{mensajesCore['REQUERIDO']}" 
										rendered="#{organizacion_editarEquipamientoSoporteBean.verMotivo}"/>
								</trh:cellFormat>
								<trh:cellFormat>
									<tr:inputText columns="50" rows="5" value="#{organizacion_editarEquipamientoSoporteBean.motivoSolicitud}"
									rendered="#{organizacion_editarEquipamientoSoporteBean.verMotivo}"/>
								</trh:cellFormat>							
							</trh:rowLayout>							
						</trh:tableLayout>							
					</tr:panelHorizontalLayout>
               		<tr:spacer height="10" />
               		<tr:panelHorizontalLayout halign="center">
               			<tr:commandButton text="#{mensajesCore['ACEPTAR']}" id="aceptar"
               				rendered="#{!organizacion_editarEquipamientoSoporteBean.desdeMS}" action="#{organizacion_editarEquipamientoSoporteBean.aceptar}"/>
               			<tr:spacer width="10" height="10"/>
              			<tr:commandButton text="#{(!organizacion_editarEquipamientoSoporteBean.desdeMS)?mensajesCore['CANCELAR']:mensajesCore['VOLVER']}"
                                action="#{organizacion_editarEquipamientoSoporteBean.cancelar}"/>
               		</tr:panelHorizontalLayout>
               		<tr:spacer width="10" height="10"/>
               		<tr:panelHorizontalLayout halign="center">
               			<tr:commandButton text="#{mensajesCore['VALIDAR_SOLICITUD']}" id="validar"
               				action="#{organizacion_editarEquipamientoSoporteBean.validar}"
               				rendered="#{organizacion_editarEquipamientoSoporteBean.verValidar}"/>
               			<tr:spacer width="5" height="10"/>
              			<tr:commandButton text="#{mensajesCore['ACEPTAR_SOLICITUD']}" id="confirmar"
               				action="#{organizacion_editarEquipamientoSoporteBean.confirmar}"
               				rendered="#{organizacion_editarEquipamientoSoporteBean.verConfirmar}"/>
               			<tr:spacer width="5" height="10"/>
               			<tr:commandButton text="#{mensajesCore['RECHAZAR_SOLICITUD']}" id="rechazar"
               				useWindow="true"
               				windowHeight="300"
            				windowWidth="300"
               				action="#{organizacion_editarEquipamientoSoporteBean.rechazar}"
               				returnListener="#{organizacion_editarEquipamientoSoporteBean.accionPostRechazar}"   
               				rendered="#{organizacion_editarEquipamientoSoporteBean.verRechazar}"/>
               			<tr:spacer width="5" height="10"/>
               			<tr:commandButton text="#{mensajesCore['BORRAR_SOLICITUD']}" id="borrar"
               				action="#{organizacion_editarEquipamientoSoporteBean.borrar}"
               				rendered="#{organizacion_editarEquipamientoSoporteBean.verBorrar}"/>
               		</tr:panelHorizontalLayout>
               		</tr:panelBox>
               		<f:subview id="detallesSolicitud" rendered="#{organizacion_editarEquipamientoSoporteBean.verDetallesSolicitud}">
            			<jsp:include page="/core/detallesSolicitud.jsp" />
            		</f:subview>
				</tr:panelPage>
			</tr:form>
			<tr:panelHorizontalLayout inlineStyle="width: 100%;">
				<tr:outputText escape="false" value="#{htmlPie}"/>
			</tr:panelHorizontalLayout>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>

