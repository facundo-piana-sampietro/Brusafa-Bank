<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuarios" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mi perfil</title>
</head>
<body>
	<%
	Usuarios user = (Usuarios)session.getAttribute("sessionUser");
	%>
		
	<%@ include file="MasterPageAdmin.html" %>
	
 
  <br>
  <div class="container-fluid" style="width:40%;">
    <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
        <div class="card-header"><h4>Mi perfil</h4></div>
            <table class="table table-hover" style="text-align: start; margin: 1px; font-size: 19px;">
              <tbody>
                <td><b>DNI</b></td>  
                <td><%=user.getDNI_Usr() %></td>      
            </tbody>
            <tbody>
                <td><b>CUIL</b></td>  
                <td><%=user.getCUIL_Usr() %></td>
            </tbody>
            <tbody>
                <td><b>Usuario</b></td>  
                <td><%=user.getUsuario_Usr() %></td>
            </tbody>
            <tbody>
                <td><b>Nombre y apellido</b></td>  
                <td><%= user.getNombre_Usr() + " " +user.getApellido_Usr()%></td>
            </tbody>
            <tbody>
                <td><b>Sexo</b></td>  
                <td><%=user.getSexo_Usr() %></td>
            </tbody>
            <tbody>
              <td><b>Nacionalidad</b></td>  
              <td><%=user.getNacionalidad_Usr() %></td>
            </tbody>
            <tbody>
                <td><b>Fecha nacimiento</b></td>  
                <td><%=user.getFechaNacimiento_Usr()%></td>
            </tbody>
            <tbody>
                <td><b>Dirección</b></td>  
                <td><%=user.getDireccion_Usr()%></td>
            </tbody>
            <tbody>
                <td><b>Provincia</b></td>  
                <td><%=user.getProvincia_Usr().getDescripcion_Prov()%></td>
            </tbody>
            <tbody>
              <td><b>Localidad</b></td>  
              <td><%= user.getLocalidad_Usr().getDescripcion_Loc()%></td>
            </tbody>
            <tbody>
                <td><b>Correo</b></td>  
                <td><%=user.getEmail_Usr() %></td>
            </tbody>
            <tbody>
            	<td><b>Telefono</b></td>  
                <td><%=user.getTelefono_Usr() %></td>
            </tbody>
      	</table>
      </div>
    </div>
  </div>
  
  <br>
  
  
  <%@ include file="FooterPage.html" %>


</body>
</html>