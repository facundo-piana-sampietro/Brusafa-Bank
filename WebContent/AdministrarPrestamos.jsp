<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="entidades.Prestamos" %>
    <%@ page import="daoImpl.PrestamosDaoImpl" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar prestamos</title>
<link rel="stylesheet" type="text/css"
    href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css" >

<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
    src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id_prestamos').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "",
                    info: "",
                    infoEmpty: "No existen datos.",
                    infoFiltered: "(filtrado de MAX elementos en total)",
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
                scrollY: 330,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange" : false,
                "bFilter": false,
                "bInfo": false
            });
        });
    </script>
</head>
<body>
<%@ include file="MasterPageAdmin.html" %>
<br>
<br>
	<div class="container-fluid" style="width:95%;">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
            <div class="card-header "><h5>Prestamos</h5></div>
            <table class="table table-hover" id = "table_id_prestamos">
                <thead>
                    <tr>
                      <th>Resolver</th> 
                      <th>ID Prestamo</th>   
                      <th>DNI Solicitante</th> 
                      <th>Nro cuenta destino</th>
                      <th>Fecha</th> 
                      <th>Plazo meses</th>
                      <th>Cuotas pagadas</th>
                      <th>pago por cuota</th>
                      <th>Monto pedido</th>
                      <th>Monto resultante</th>  
                       
                    </tr>
                </thead>
			<%
			ArrayList<Prestamos> listaPrestamos = null;
			
			if (request.getAttribute("listaPrestamos") != null)
				listaPrestamos = (ArrayList<Prestamos>) request.getAttribute("listaPrestamos");
			%>
	                <tbody>
					<%
					if (request.getAttribute("listaPrestamos") != null)
					for (Prestamos prestamo : listaPrestamos)
					{ 
					%>
	                    <tr>
						<form method = "get" action = "ServletPrestamos">
						<div>
	                        <th scope="row">
	                            <button type="submit"
	                            name = "btnAceptarPrestamo"
	                            class="btn btn-outline-success btn-sm"
	                            value = "<%=prestamo.getId_Pr()%>"
	                            onclick="return confirm('¿Está seguro de que quiere aceptar este prestamo?')" title="Aceptar prestamo">
	                                <i class="fa-sharp fa-solid fa-check"></i>
	                            </button>
	                            <button type="submit"
	                            name = "btnRechazarPrestamo"
	                            class="btn btn-outline-danger btn-sm"
	                            value = "<%=prestamo.getId_Pr()%>"
	                            onclick="return confirm('¿Está seguro de que quiere rechazar este prestamo?')" title="Rechazar prestamo">
	                                <i class="fa-solid fa-x"></i>
	                            </button>
	                        </th>  
	                        <td><%= prestamo.getId_Pr() %></td>  
	                        <td><%= prestamo.getCuentaDestino_Pr().getUsuario_Cuentas().getDNI_Usr() %></td>
	                        <td><%= prestamo.getCuentaDestino_Pr().getNro_Cuentas() %></td>
	                        <td><%= prestamo.getFechaSolicitado_Pr().toString() %></td>
	                        <td><%= prestamo.getPlazoMeses_Pr() %></td>   
	                        <td><%= prestamo.getCantCuotas_Pr() %></td> 
                	        <td><%= prestamo.getImpPagoAlMes_Pr() %></td>
                	        <td><%= prestamo.getImpSolicitado_Pr() %></td>
	                        <td><%= prestamo.getImpResultante_Pr() %></td>
	                        

	          			</form>
	                    </tr>
            		<%}%>
	                </tbody>
            </table>
        </div>
      </div>
</form>
      <br>      
       <div style="display: flex; justify-content: center;">
      <%		int modificado = -1;
			if (request.getAttribute("modificado")!=null) {
				modificado= (int) request.getAttribute("modificado");
			}
			if (modificado == 0){
		%>
		 <div ID="MsgEliminadoDiv" class="col-md-4 alert alert-success" runat="server" visible="false">
            <strong>Rechazado</strong> Préstamo rechazado.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
		<%
		}
		else if (modificado == 1){
		%>

		 <div ID="MsgAgregadoDiv" class="col-md-4 alert alert-success" runat="server" visible="false">
            <strong>Agregado</strong> Prestamo agregado correctamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
		<%
			}
		%>
      
	</div>
  
    <%@ include file="FooterPage.html" %>
</body>
</html>