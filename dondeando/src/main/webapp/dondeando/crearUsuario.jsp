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
					<tr:panelHorizontalLayout halign="center">
					<tr:spacer width="10" />
					<tr:panelBox text="#{crearUsuarioBean.tituloPagina}">
						<tr:spacer height="20" />
						
						<tr:panelFormLayout inlineStyle="border-style: solid; border-width: 1px;" 
						partialTriggers="btnModificar :btnCancelar">
						<tr:spacer height="20" />
							<tr:panelLabelAndMessage label="#{resCore['USUARIO']} *">
								<tr:inputText columns="20" value="#{crearUsuarioBean.login}" id="login" simple="true"
									maximumLength="20" required="true" disabled="#{crearUsuarioBean.detalles}"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['CONTRASENA']} *" rendered="#{crearUsuarioBean.mostrarPass}">
								<tr:inputText columns="20" value="#{crearUsuarioBean.password}" id="pass" simple="true"
									maximumLength="20" secret="true" required="true"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['REPETIR_CONTRASENA']} *" rendered="#{crearUsuarioBean.mostrarPass}">
								<tr:inputText columns="20" value="#{crearUsuarioBean.password2}" id="pass2" simple="true"
									maximumLength="20" secret="true" required="true"/>
							</tr:panelLabelAndMessage>
							
							
							<tr:spacer width="10" height="30" />
							
							<tr:panelLabelAndMessage label="#{resCore['NOMBRE']} *">
								<tr:inputText columns="30" value="#{crearUsuarioBean.nombre}" id="nombre" simple="true"
									maximumLength="50" required="true" disabled="#{crearUsuarioBean.detalles}"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['APELLIDOS']}  ">
								<tr:inputText columns="60" value="#{crearUsuarioBean.apellidos}" id="apellidos" simple="true"
								disabled="#{crearUsuarioBean.detalles}"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['DIRECCION']}  ">
								<tr:inputText columns="80" value="#{crearUsuarioBean.direccion}" id="direccion" simple="true"
								disabled="#{crearUsuarioBean.detalles}"/>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['EMAIL']} *">
								<tr:inputText columns="80" value="#{crearUsuarioBean.email}" id="email" simple="true"
									required="true" styleClass="form" disabled="#{crearUsuarioBean.detalles}">
									<!-- Bug TRINIDAD-1339 para Trinidad 1.0.10 -->
<!--									<tr:validateRegExp pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.(\w{3-4})$"-->
<!--										messageDetailNoMatch="#{resCore['ERROR_PATRON_EMAIL']}" />-->
									<tr:validator binding="#{crearUsuarioBean.validatorEmail}"/>
								</tr:inputText>
							</tr:panelLabelAndMessage>
							<tr:spacer width="10" height="10" />
							<tr:panelLabelAndMessage label="#{resCore['TIPO_USUARIO']} *">
								<tr:selectOneChoice value = "#{crearUsuarioBean.tipoUsuario}" 
									disabled="#{crearUsuarioBean.deshabilitarTipoUsuario}">
									<f:selectItems id="selectTipoUsuario" 
										value="#{crearUsuarioBean.selectTipoUsuario}"/>
								</tr:selectOneChoice>
							</tr:panelLabelAndMessage>
						<tr:spacer width="20" height="20" />
							
						</tr:panelFormLayout>
						
						
						<tr:spacer width="20" height="20" />
						<tr:panelHorizontalLayout halign="center" rendered="#{!crearUsuarioBean.detalles}">
							<tr:commandButton text="#{resCore['ACEPTAR']}" id="btnAceptar"
								action="#{crearUsuarioBean.aceptar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['CANCELAR']}" id="btnCancelar" immediate="true"
								action="#{crearUsuarioBean.cancelar}" />
						</tr:panelHorizontalLayout>
						
						<tr:panelHorizontalLayout halign="center" rendered="#{crearUsuarioBean.detalles}">
							<tr:commandButton text="#{resCore['MODIFICAR_USUARIO']}" id="btnModificar"
								action="#{crearUsuarioBean.modificar}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['MODIFICAR_PASS']}" id="btnModContr"
								action="#{crearUsuarioBean.modificarContr}" />
							<tr:spacer width="20" height="10" />
							<tr:commandButton text="#{resCore['ELIMINAR_USUARIO']}" id="btnEliminar"
								action="#{crearUsuarioBean.eliminar}" 
								onclick="return confirm('#{resCore['CONFIRMAR_ELIMINAR_USUARIO']}')"/>
							</tr:panelHorizontalLayout>

							<tr:panelGroupLayout>
								<tr:spacer width="20" height="10" />
								<tr:inputFile label="#{resCore['MODIFICAR_IMAGEN_USUARIO']}"
									value="#{crearUsuarioBean.imagenUsuario}"
									valueChangeListener="#{crearUsuarioBean.cambiarImagen}"
									columns="1" />
								<tr:commandButton text="Continuar"
									action="#{crearUsuarioBean.continuar}" />
							</tr:panelGroupLayout>
								<tr:image source="#{crearUsuarioBean.urlImagenUsuario}"
									inlineStyle="height: 300px;"/>

						</tr:panelBox>
				</tr:panelHorizontalLayout>
			</tr:panelPage>
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
