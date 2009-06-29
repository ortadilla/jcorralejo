<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carga externa de Pedidos Internos</title>
</head>
<body>
	<h2>Carga de Pedidos Internos</h2>
	<form method="post" action="CargaFicheros.jsp" name="upform" enctype="multipart/form-data" target="_blank">
		<table width="60%" border="0" cellspacing="1" cellpadding="1" align="center" class="style1">
			<tr>
				<td align="left">Cargar a partir de un fichero externo:</td>
			</tr>
			<tr>
				<td align="left">       
					<input type="file" name="uploadfile" size="50"/>
				</td>
			</tr>
			<tr>
				<td align="left">
					<input type="submit" name="Submit" value="Subir"/>	
					<input type="reset" name="Reset" value="Cancelar"/>
				</td>
			</tr>
		</table>
	</form>      
</body>
</html>