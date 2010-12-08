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
		<trh:head title="Menu">
		</trh:head>
		<trh:body>
			<tr:form>
				<tr:outputText value="MENU" />
			</tr:form>
		</trh:body>
		</trh:html>
	</f:view>
</jsp:root>
