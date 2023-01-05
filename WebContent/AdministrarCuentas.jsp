<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cuentas" %>
<%@ page import="entidades.Usuarios" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="MasterPageAdmin.html" %>

<title>Administrar cuentas</title>
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
            $('#table_id_cuentas').DataTable({
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
                scrollY: 391,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange" : false,
                "bFilter": false,
                "bInfo": false
            });
        });
    </script>
    
 

<%
boolean estaModificando = false;
	if (request.getAttribute("estaModificado") != null)
		estaModificando = (boolean)request.getAttribute("estaModificado");
%>

</head>
<body>
	
   <br>
   
  <div class="container-fluid" style="width:90%;">
    <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
        <div class="card-header ">
          <h5>Cuentas</h5>
        </div>
      
		<%
		ArrayList <Cuentas> listaCuentas = null;
		if (request.getAttribute("listaCuentas")!=null) listaCuentas=(ArrayList <Cuentas>)request.getAttribute("listaCuentas");
		int posicion=0;
		%>  
        <table class="table table-hover" id="table_id_cuentas">
            <thead>
              <tr>
                <th> </th>  
                <th>Nro de cuenta</th>  
                <th>CBU</th> 
                <th>Tipo de cuenta</th>  
                <th>DNI</th>
                <th>Fecha creación</th> 
                <th>Saldo</th>
          
              </tr>
            </thead>
            <tbody>
            <%
                if (listaCuentas!=null)
                	for(Cuentas cuenta : listaCuentas){
                		if (cuenta.isEstado_Cuentas()==true){
                %>
              <tr>
  				<form action= "servletCuentas" method="post" name="formCuentas">
                <th scope="row">
                  <button type="submit" name="btnModificar" class="btn btn-outline-success">
                    <i class="fa-solid fa-pen-to-square"></i>
                  </button>
                  
                  <button type="submit"
                   name="btnEliminar" 
                   class="btn btn-outline-danger"
                   onclick="return confirm('¿Está seguro de que quiere eliminar la cuenta?')">
                    <i class="fa-solid fa-trash"></i>
                  </button>
                  
                </th>
                <td><%=cuenta.getNro_Cuentas() %> <input type="hidden" name="hiddenId" value="<%=cuenta.getNro_Cuentas()%>"> </td> 
                <td><%=cuenta.getCBU_Cuentas() %> <input type="hidden" name="hiddenCBU" value="<%=cuenta.getCBU_Cuentas()%>"></td>
                <td><%=cuenta.getTipoCuenta_Cuentas().getDescripcion_TipoCuenta() %></td>  
                <td><%=cuenta.getUsuario_Cuentas().getDNI_Usr() %></td>
                <td><%=cuenta.getFechaCreacion_Cuentas() %></td>   
                <td>$<%=cuenta.getSaldo_Cuentas() %></td>
				</form>
              </tr>
             
             	<%
                		}
                	}
             	%>
            </tbody>     
          </table>
        <div class="card-footer">
          <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
               
            </ul>
        </nav>
      </div>
    </div>
    <br>
  </div>
  <br>
  	
  	<%
		if (estaModificando){
				%>
				<br>
				<%@ include file="ModificarCuenta.html" %>
				<br>
				<%
			}
			else{
				%>
				<br>
				<%@ include file="AgregarCuenta.html" %>
				<br>
				<%
			}
		%>
  	
  	 <%
int mensajeMod=-2;
if (request.getAttribute("mensajeMod")!=null) mensajeMod=(int)request.getAttribute("mensajeMod");                        		
%>   
  <div style="display: flex; justify-content: center;">
        <%
        if (mensajeMod == -1){
        %>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
            <strong>Error</strong> Hubo un error al modificar la cuenta.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        else if (mensajeMod == 0){
        %>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
            <strong>Error</strong> El CBU elegido ya tiene una cuenta asignada.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        else if (mensajeMod == 1){
        %>
        
        <div ID="MsgCorrectoDiv" class="col-md-4 alert alert-success" runat="server" visible="false">
            <strong>Agregada</strong> Cuenta modificada correctamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        %>
   </div>
    <%@ include file="FooterPage.html" %>

</body>
</html>