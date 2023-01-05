<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuarios" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>

<%
	Usuarios user = (Usuarios)session.getAttribute("sessionUser");
%>
</head>
<body>
	<%@ include file="MasterPageAdmin.html" %>
	<br>
	<H1 style="text-align: center"> Bienvenido: <%=user.getNombre_Usr() + " " + user.getApellido_Usr() %> </H1>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@ include file="FooterPage.html" %>
</body>
</html>