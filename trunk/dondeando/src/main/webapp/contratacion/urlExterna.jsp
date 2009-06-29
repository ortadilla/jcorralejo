<?xml version='1.0' encoding='ISO-8859-15'?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core"
          xmlns:trh="http://myfaces.apache.org/trinidad/html"
          xmlns:tr="http://myfaces.apache.org/trinidad">
  <jsp:output omit-xml-declaration="true" doctype-root-element="HTML"
              doctype-system="http://www.w3.org/TR/html4/loose.dtd"
              doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
  <jsp:directive.page contentType="text/html;charset=ISO-8859-15"/>
  <f:view>
    <trh:html>
      <trh:head title="#{dialogoURLExterna.titulo}">
        <meta http-equiv="Content-Type"
              content="text/html; charset=ISO-8859-15"/>

<f:verbatim>
	<script language="javascript">

		function navegar() {
			hform=document.forms['redirigir'];	
			var URL=getURL(hform);
			// alert('URL='+URL);
			if (!(URL == null || URL == '')) {
				hform.action = getURL(hform);
				hform.submit();
			}
		}

		function getURL (form) {
			field = form[form.id+":url"];
			return field.value;
		}		
		
	</script>
</f:verbatim>

      </trh:head>
      <trh:body onload="navegar()">

        <h:form id="redirigir" >
			<tr:inputHidden id="url" value="#{dialogoURLExterna.urlExterna}"> </tr:inputHidden>
        </h:form>
      </trh:body>
    </trh:html>
  </f:view>
</jsp:root>