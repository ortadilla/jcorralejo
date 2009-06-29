<?xml version='1.0' encoding='windows-1252'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:tr="http://myfaces.apache.org/trinidad"
    xmlns:trh="http://myfaces.apache.org/trinidad/html">
	<jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" />
	<jsp:directive.page contentType="text/html;charset=windows-1252" />
	<f:view>

		<trh:html>
		<trh:head title="Hola Mundo">
			<meta http-equiv="Content-Type"
				content="text/html; charset=windows-1252" />
			<link rel="shortcut icon" href="../faviconORG.ico" type="image/x-icon" />
			
			<tr:outputText escape="false" value="#{htmlHead}"/>
			
		</trh:head>
		<trh:body>
			<tr:outputText escape="false" value="hola mundo"/>
			<tr:commandButton text="Aceptar"/>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
