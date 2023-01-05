<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Provincias" %>  
<%@ page import="entidades.Localidades" %> 
<%@ page import="entidades.Usuarios" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar usuarios</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id_usuarios').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Agrupar de _MENU_ items",
                    info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
                    infoEmpty: "No existen datos.",
                    infoFiltered: "(filtrado de _MAX_ elementos en total)",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
                },
                scrollY: 350,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange" : false,
                "bFilter": false,
                "bInfo": false
            });
        });
    </script>
<%
	
	Usuarios usrSeleccionadoEditar = new Usuarios();
	if(request.getAttribute("usrSeleccionado") != null)
		usrSeleccionadoEditar = (Usuarios)request.getAttribute("usrSeleccionado");
	ArrayList <Usuarios> listaUser = null;
	if (request.getAttribute("listaUser")!=null)
		listaUser = (ArrayList <Usuarios>) request.getAttribute("listaUser");
%>
</head>
<body>
	<%@ include file="MasterPageAdmin.html" %>
	
	<br>
	<div class="container-fluid" style="width:95%;">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
            <div class="card-header "><h5>Usuarios</h5></div>
            <table class="table table-hover" id="table_id_usuarios" style="font-size: 11px;">
                <thead>
                    <tr>
                      <th> </th> 
                      <th>DNI</th>   
                      <th>CUIL</th> 
                      <th>Nombre</th> 
                      <th>Apellido</th>
                      <th>Sexo</th> 
                      <th>Nacionalidad</th> 
                      <th>Fecha de nacimiento</th> 
                      <th>Dirección</th>
                      <th>Provincia</th>
                      <th>Localidad</th>
                      <th>Email</th>
                      <th>Teléfono</th> 
                      <th>Tipo de usuario</th> 
                      <th>Usuario</th>
                      <th>Contraseña</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	if (listaUser!=null)
                		for(Usuarios user: listaUser){
                			if (user.isEstado_Usr()==true){
                	%>
                 	<tr>
                 		<form action= "servletUsuarios" method="post" name="formUsuario">
	                        <th scope="row">
								<!-- EN SERVLET NO PREGUNTO POR ESTE BTN, RECIEN LO AGREGO-->
								<button type="submit" name="btnEditar" onclick="" class="btn btn-outline-success btn-sm">
									<i class="fa-solid fa-pen-to-square"></i>
								</button>
								<button type="submit" name ="btnEliminarUsr"class="btn btn-outline-danger btn-sm" onclick="return confirm('¿Esta seguro de que quiere eliminar el usuario?')">
									<i class="fa-solid fa-trash"></i>
								</button>
	                        </th>
			                <td><%=user.getDNI_Usr() %>  <input type="hidden" name="hiddenDNI" value="<%=user.getDNI_Usr()%>"> </td>
			                <td><%=user.getCUIL_Usr() %></td>                                            
			                <td><%=user.getNombre_Usr() %></td>
			                <td><%=user.getApellido_Usr() %></td>                       
			                <td><%=user.getSexo_Usr() %></td>                        
			              	<td><%=user.getNacionalidad_Usr() %></td>            
			                <td><%=user.getFechaNacimiento_Usr()%></td>           
			                <td><%=user.getDireccion_Usr()%></td>           
			                <td><%=user.getProvincia_Usr().getDescripcion_Prov()%></td>          
			              	<td><%=user.getLocalidad_Usr().getDescripcion_Loc()%></td>           
			                <td><%=user.getEmail_Usr() %></td>           
			                <td><%=user.getTelefono_Usr() %></td>
			                <td><%=user.isTipo_Usr() %></td>
			                <td><%=user.getUsuario_Usr() %></td>  
			                <td><%=user.getContrasenia_Usr() %></td>  
			        	</form>
	                </tr>
	                <%
                			}
                		}
	                %>
            	</tbody>                                             
            </table>
        </div>
      </div>
      <br>
      
      <div style="display: flex; justify-content: center;">
		<%
			boolean resultadoEdit = false;
		    if (request.getAttribute("resultadoEdit")!=null) {
		    resultadoEdit = (boolean)request.getAttribute("resultadoEdit");
	    }
	    if (resultadoEdit == false && request.getAttribute("resultadoEdit") != null){
	    %>
       <div ID="MsgErrorDiv" style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-danger" runat="server" visible="false">
           <strong>Error</strong> No se pudo editadar correctamente el usuario
           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
       </div>
       <%
       }
       else if (resultadoEdit){
       %>
       <div ID="MsgCorrectoDiv"  style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-success" runat="server" visible="false">
           <strong>Agregado</strong> Usuario editado correctamente!
           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
       </div>
       <%
       }
       %> 
      </div>

	<div style="display: flex; justify-content: center;">
		<%
			boolean mensajeDeleteUsr=false;
			int mensajeDeleteUsrInt = -1;
			if (request.getAttribute("mensajeDeleteUsr")!=null) {
				mensajeDeleteUsr= (boolean) request.getAttribute("mensajeDeleteUsr");
				mensajeDeleteUsrInt =  mensajeDeleteUsr ? 1 : 0;
			}
			if (mensajeDeleteUsrInt == 0){
		%>
		<div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
			<strong>Error</strong> No se pudo eliminar el usuario!
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
		<%
		}
		else if (mensajeDeleteUsrInt == 1){
		%>

		<div ID="MsgCorrectoDiv" class="col-md-4 alert alert-success" runat="server" visible="false">
			<strong>Eliminado</strong> Usuario eliminado correctamente!
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
		<%
			}
		%>
	</div>
	
	
       <%
			if (!usrSeleccionadoEditar.getDNI_Usr().equals("-1")){
				%>
				<br>
				<%@ include file="ModificarUsuario.html" %>
				<br>
				<%
			}
			else{
				%>
				<br>
				<%@ include file="AgregarUsuario.html" %>
				<br>
				<%
			}
		%>

      
      
    <%@ include file="FooterPage.html" %> 
</body>
 
</html>