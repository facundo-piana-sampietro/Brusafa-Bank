<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cuentas" %>
<%@ page import="entidades.Usuarios" %>  
<%@ page import="entidades.Movimientos" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id_movimientos').DataTable({
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
                scrollY: 271,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange" : false,
                "bFilter": false,
                "bInfo": false
            });
        });
    </script>



<%
	ArrayList <Cuentas> listaCuentas = null;
	if (request.getAttribute("listaCuentas")!=null) listaCuentas=(ArrayList <Cuentas>)request.getAttribute("listaCuentas");
	int posicion=0;
%>  

</head>
<body>
	<%@ include file="MasterPageAdmin.html" %>
	<br>
		<div class="container-fluid" style="width:85%;">
	        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
	            <div class="card-header "><H2> <b> <p style="text-align:center;"> Cuentas </b></H2></div>
	            <table class="table table-hover" style="font-size: 12px;">
	                <tr> 
						<th>Número de cuenta</th>  
						<th>Tipo de cuenta</th>  
						<th>CBU</th>  
						<th>Saldo</th> 
						<th>Operación</th>
					</tr>
					<%	
					int c=0;
					if (listaCuentas!=null)
						for (Cuentas cuenta : listaCuentas){
							c++;
					%>
					<tr> 
					<form action="servletCuentasUsr" method="post">
						<td><%=cuenta.getNro_Cuentas() %> <input type="hidden" name="hiddenNroCuenta" value="<%=cuenta.getNro_Cuentas()%>"> </td> 
						<td><%=cuenta.getTipoCuenta_Cuentas().getDescripcion_TipoCuenta()%></td>   
						<td><%=cuenta.getCBU_Cuentas()%></td> 
						<td><%=cuenta.getSaldo_Cuentas() %></td> 
						<td> <input type="submit" name= "btnVerMovimientos"value= "Movimientos" class="btn btn-outline-dark btn-sm"> </td>
					</form>
					</tr>
					<%		
						}
					%>
	            </table>
	        </div>
	    </div>

<br> <br>


<%

	if (request.getParameter("btnVerMovimientos")!=null){
			
	ArrayList <Movimientos> listaMovimientos = null;
	boolean transferencia = false;
	if (request.getAttribute("listaMovimientos")!=null) listaMovimientos=(ArrayList <Movimientos>)request.getAttribute("listaMovimientos");
	if (listaMovimientos.size()!=0){
		for (Movimientos movimiento : listaMovimientos){
			if (movimiento.getTiposMov_Mov().getId_TiposMov()==4) transferencia = true;
		}

%>

<div class="container-fluid" style="width:85%;">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
            <div class="card-header">
            <H3> Movimientos </H3>
            </div> 
            <table class="table table-hover" id= "table_id_movimientos" style="font-size: 12px;">
            <thead>
                <tr> 
					<th>Cuenta origen</th>
					<th>CBU de la cuenta origen</th>  
					<th>Remitente</th>
					<th>Fecha</th>  
					<th>Detalle</th>  
					<th>Importe</th>  
					<th>Tipo de movimiento</th> 
					<% if (transferencia == true)%>
					<th>Cuenta destino</th>
					<th>CBU de la cuenta destino</th> 
					<th>Destinatario</th> 
				</tr>
			</thead>
				<tbody>
				<%
					if (listaMovimientos!=null)
						for (Movimientos movimiento : listaMovimientos){
				%>
				<tr> 
					<td><%=movimiento.getCuenta_Mov().getNro_Cuentas() %></td> 
					<th><%=movimiento.getCuenta_Mov().getCBU_Cuentas()%></th>
					<th><%=movimiento.getCuenta_Mov().getUsuario_Cuentas().getNombre_Usr() + " " + movimiento.getCuenta_Mov().getUsuario_Cuentas().getApellido_Usr()%></th>
					<td><%=movimiento.getFecha_Mov() %></td> 
					<td><%=movimiento.getDetalle_Mov()%></td>    
					<td><%=movimiento.getImporte_Mov() %></td> 
					<td><%=movimiento.getTiposMov_Mov().getDescripcion_TiposMov()%></td>
					<% if (movimiento.getTiposMov_Mov().getId_TiposMov()==4){%>
					<th><%=movimiento.getCuentaDestino_Mov().getNro_Cuentas() %></th>
					<th><%=movimiento.getCuentaDestino_Mov().getCBU_Cuentas()%></th>
					<th><%=movimiento.getCuentaDestino_Mov().getUsuario_Cuentas().getNombre_Usr() + " " + movimiento.getCuentaDestino_Mov().getUsuario_Cuentas().getApellido_Usr()%></th>
					<%}else{%>
					<th> - </th>
					<th> - </th>
					<th> - </th>
					<%} %>	
				</tr>
				<%
				}
				%>
             </tbody>   
            </table>
        </div>
    </div>
    
<%
	}
	else{ %>
	<div style="display: flex; justify-content: center;">
		<div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
	            <strong>Error</strong> La cuenta seleccionada no poseé movimientos.
	            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    </div>
	</div> 	
<%  
	}
}
%>
    <br>
    
    <%@ include file="FooterPage.html" %>
</body>
</html>