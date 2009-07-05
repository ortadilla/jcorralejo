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
		
		<trh:body initialFocusId="nombre">
			
			<geos:cabeceraPagina />

			<tr:form defaultCommand="btnAceptar" id="formCrearUsuario" usesUpload="true">
			<tr:panelPage>
					<f:facet name="messages">
						<tr:messages />
					</f:facet>
					<tr:panelHeader	text="#{crearUsuarioBean.tituloPagina}" />
					<tr:panelHorizontalLayout halign="center">
					<tr:spacer width="10" />
					<tr:panelBox text="#{resCore['DATOS_USUARIO']}">
						<tr:spacer height="20" />
					  <trh:tableLayout inlineStyle="border-style: solid; border-width: 1px;" 
					 	cellSpacing="5" cellPadding="0">
					 	<trh:rowLayout>
						 	<tr:outputText value="#{resCore['USUARIO']} *" inlineStyle="font-weight: bolder;"/>
							<tr:inputText columns="20" value="#{crearUsuarioBean.login}" id="login" simple="true"
								maximumLength="20" required="true" disabled="#{crearUsuarioBean.detalles}"/>
							<trh:cellFormat rowSpan="3">
								<tr:image source="#{crearUsuarioBean.urlImagenUsuario}"
										inlineStyle="height: 70px;border-style: solid; border-width: 1px;" />
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout rendered="#{crearUsuarioBean.mostrarPass}">
						 	<tr:outputText value="#{resCore['CONTRASENA']} *"  inlineStyle="font-weight: bolder;"/>
							<tr:inputText columns="20" value="#{crearUsuarioBean.password}" id="pass" simple="true"
								maximumLength="20" secret="true" required="true"/>
					 	</trh:rowLayout>
					 	<trh:rowLayout rendered="#{crearUsuarioBean.mostrarPass}">
						 	<tr:outputText value="#{resCore['REPETIR_CONTRASENA']} *"  inlineStyle="font-weight: bolder;"/>
							<tr:inputText columns="20" value="#{crearUsuarioBean.password2}" id="pass2" simple="true"
									maximumLength="20" secret="true" required="true"/>
					 	</trh:rowLayout>
					 	<trh:rowLayout />
					 	<trh:rowLayout />
					 	<trh:rowLayout>
						 	<tr:outputText value="#{resCore['NOMBRE']} *" inlineStyle="font-weight: bolder;"/>
						 	<trh:cellFormat columnSpan="2">
								<tr:inputText columns="30" value="#{crearUsuarioBean.nombre}" id="nombre" simple="true"
										maximumLength="50" required="true" disabled="#{crearUsuarioBean.detalles}"/>
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout>
						 	<tr:outputText value="#{resCore['APELLIDOS']}" inlineStyle="font-weight: bolder;"/>
					 		<trh:cellFormat columnSpan="2">
								<tr:inputText columns="60" value="#{crearUsuarioBean.apellidos}" id="apellidos" simple="true"
									disabled="#{crearUsuarioBean.detalles}"/>
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout>
						 	<tr:outputText value="#{resCore['DIRECCION']}" inlineStyle="font-weight: bolder;"/>
					 		<trh:cellFormat columnSpan="2">
								<tr:inputText columns="80" value="#{crearUsuarioBean.direccion}" id="direccion" simple="true"
								disabled="#{crearUsuarioBean.detalles}"/>
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout>
						 	<tr:outputText value="#{resCore['EMAIL']} *" inlineStyle="font-weight: bolder;"/>
					 		<trh:cellFormat columnSpan="2">
								<tr:inputText columns="80" value="#{crearUsuarioBean.email}" id="email" simple="true"
									required="true" styleClass="form" disabled="#{crearUsuarioBean.detalles}">
									<tr:validator binding="#{crearUsuarioBean.validatorEmail}"/>
								</tr:inputText>
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout rendered="#{crearUsuarioBean.mostrarTipoUsuario}">
						 	<tr:outputText value="#{resCore['TIPO_USUARIO']} *" inlineStyle="font-weight: bolder;"/>
					 		<trh:cellFormat columnSpan="2">
								<tr:selectOneChoice value = "#{crearUsuarioBean.tipoUsuario}" disabled="#{crearUsuarioBean.detalles}">
									<f:selectItems id="selectTipoUsuario" 
										value="#{crearUsuarioBean.selectTipoUsuario}"/>
								</tr:selectOneChoice>
							</trh:cellFormat>
					 	</trh:rowLayout>
					 	<trh:rowLayout rendered="#{!crearUsuarioBean.detalles}">
						 	<tr:outputText value="#{resCore['AVATAR']}" inlineStyle="font-weight: bolder;"/>
					 		<trh:cellFormat columnSpan="2">
					 			<tr:inputFile value="#{crearUsuarioBean.fileAvatar}" />	
							</trh:cellFormat>
					 	</trh:rowLayout>
					 </trh:tableLayout>
					 
					 	
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center" rendered="#{!crearUsuarioBean.detalles}">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{crearUsuarioBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar" immediate="true"
								action="#{crearUsuarioBean.cancelar}" />
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center">
							<tr:commandButton text="#{resCore['MODIFICAR_USUARIO']}" id="btnModificar"
								action="#{crearUsuarioBean.modificar}" rendered="#{crearUsuarioBean.modificarUsuario}"/>
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['MODIFICAR_PASS']}" id="btnModContr"
								action="#{crearUsuarioBean.modificarContr}" rendered="#{crearUsuarioBean.modificarPass}"/>
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['ELIMINAR_USUARIO']}" id="btnEliminar"
								action="#{crearUsuarioBean.eliminar}" rendered="#{crearUsuarioBean.eliminarUsuario}"
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_USUARIO']}')"/>
						</tr:panelHorizontalLayout>
						<tr:spacer width="20" height="10" />
						<tr:panelHorizontalLayout halign="center">
						<tr:commandButton text="#{resCore['VOLVER']}" id="btnVolver"
							rendered="#{crearUsuarioBean.mostrarVolver}"	
							action="#{crearUsuarioBean.volver}" immediate="true" />
						</tr:panelHorizontalLayout>

						</tr:panelBox>
				</tr:panelHorizontalLayout>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
