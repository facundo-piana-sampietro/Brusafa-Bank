<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cuentas" %>
<%@ page import="entidades.Usuarios" %>
<%@ page import="entidades.Prestamos" %>
<%@ include file="MasterPageAdmin.html" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
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
                scrollY: 225,
                lengthMenu: [ [4, 25, -1], [10, 25, "All"] ],
                "bLengthChange" : false,
                "bFilter": false,
                "bInfo": false
            });
        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Prestamos</title>

  </head>
  	<%
  		int mensaje=-3;
  			if (request.getAttribute("mensaje")!=null) mensaje=(int)request.getAttribute("mensaje");   
		
		Boolean importeNegativo = false;
  		if (request.getAttribute("importeNegativo")!=null) 
			importeNegativo= (boolean)request.getAttribute("importeNegativo");
  				
  		boolean prestamoInsert = false;
	  	if(request.getAttribute("prestamoInsert")!=null)
	  		prestamoInsert = (boolean)request.getAttribute("prestamoInsert");
  	
	  	Prestamos prestamoAPagar = new Prestamos();
	  	if(request.getAttribute("prestamoAPagar")!=null)
	  		prestamoAPagar = (Prestamos)request.getAttribute("prestamoAPagar");
	  	
	  	String dniUser = ((Usuarios)session.getAttribute("sessionUser")).getDNI_Usr();
	  	ArrayList <Cuentas> listaCuentasOrigen = null;
			if (request.getAttribute("listaCuentasOrigen")!=null) listaCuentasOrigen=(ArrayList <Cuentas>)request.getAttribute("listaCuentasOrigen");
		ArrayList <Prestamos> prestamosDeUsr = null;
			if (request.getAttribute("prestamosDeUsr")!=null) prestamosDeUsr=(ArrayList <Prestamos>)request.getAttribute("prestamosDeUsr");
	%> 
  <body >	
  
  <br>
  
  <div class="container-fluid" style="width:85%;">
    <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
        <div class="card-header "> <h5>Prestamos</h5> </div>
        <table class="table table-hover" id="table_id_cuentas">
            <thead>
              <tr>
                <th> </th>  
                <th>Cuenta destino</th>  
                <th>Fecha solicitado</th> 
                <th>Imp. solicitado</th>  
                <th>Imp. a pagar</th>
                <th>Imp. a pagar por mes</th> 
                <th>Plazo meses</th>
                <th>Cuotas pagadas</th>
                <th>Estado aprobacion</th>
              </tr>
            </thead>
            <tbody>
            <%
            	if (prestamosDeUsr!=null)
            		for (Prestamos pr : prestamosDeUsr){ 	
            %>
            <tr>
  				<form method="post" action="ServletPrestamosUsuario" name="prestamoSeleccionadoForm">
		  				<%if(pr.isAutorizado_Pr() && pr.getCantCuotas_Pr() < pr.getPlazoMeses_Pr()){ %>
			                <th scope="row">
			                  <button type="submit" name="btnPagar" class="btn btn-outline-success">
			                    <i class="fa-solid fa-comments-dollar"></i> pagar
			                  </button>
			                </th>
		                <%} else {%>
		                	<th scope="row">
			                  <button type="submit" disabled="true" class="btn btn-outline-success">
			                    <i class="fa-solid fa-comments-dollar"></i> pagar
			                  </button>
			                </th>
		                <%}%>
	                	<input type="hidden" name="prestamoSeleccionado" value="<%= pr.getId_Pr() %>">
		                <td><%= pr.getCuentaDestino_Pr().getNro_Cuentas() %> </td> 
		                <td><%=pr.getFechaSolicitado_Pr() %> </td>
		                <td><%=pr.getImpSolicitado_Pr() %></td>  
		                <td><%=pr.getImpResultante_Pr() %></td>
		                <td><%=pr.getImpPagoAlMes_Pr() %></td>   
		                <td><%=pr.getPlazoMeses_Pr() %></td>
		                <td><%=pr.getCantCuotas_Pr() %></td>
		                <td><%=pr.isAutorizado_Pr() %></td>	
                </form>
              </tr>
             	<%}%>
            </tbody>     
          </table>   	
       
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
               
            </ul>
        </nav>
         
    </div>
    <br>
    <form method="post" action="ServletPrestamosUsuario">	          	
		<button type="submit"
		name="estaPidiendoPrestamo"
		class="btn btn-success btn-lg d-flex justify-content-center"
		style="margin:auto;" >
	        <i style="margin-right:10px; font-size:25px;" class="fa-solid fa-hand-holding-dollar"></i> Solicitar prestamo
		</button>
  	</form> 
  </div>
  
 
  	
	
	<br>
	<%if(prestamoInsert){ %>
	<div style="display:flex; flex-direction: row;">
		<div class="container-fluid" style="width: 40%">
	      <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
	        <div class="card-header">
	          <H2 style="text-align: center">Solicitar prestamo</H2>
	        </div>
	        <form method="post" action="ServletPrestamosUsuario">
		        <div class="card-body row" style="font-size: 16px">
		        	<div class="col-md-6">
		                <div class="form-floating mb-3">
		                    <input 
		                    type="text"
		                    class="form-control"
		                    name="txtImporte"
		                    placeholder="-"
		                    step=0.01
		                    value="0.01"
		                    required>
		                    <label for="floatingInput">$ Importe a pedir</label>
		                </div>
		            </div>
		          	<div class="col-md-6">
		           		<div class="form-floating">
							<select name="ddlCuotas"
							 class="form-select" id="floatingInput" placeholder="-">
								<%for (int i=1; i<=24; i++){ %>
								<option value=<%=i%>><%=i%></option>
								<%} %>
							</select>
							<label for="floatingSelect">Numero de cuotas</label>
						</div>
					</div>
					<br>
					<!--   ddlCuentaOrigenPrestamo> -->
		             <div class="col-md-12">
		              <div class="form-floating" name="">
		                <select
		                  name="ddlCuentaOrigen"
		                  id=""
		                  class="form-select"
		                  id="floatingInput"
		                  placeholder="-"
		                >
		                <%if (listaCuentasOrigen!=null)
		                	for (Cuentas cuenta : listaCuentasOrigen){%>
		                  	<option value=<%=cuenta.getNro_Cuentas() %>>  <%="CBU " + cuenta.getCBU_Cuentas() + " - Saldo $" + cuenta.getSaldo_Cuentas() %></option>
		                <%}%>
		                </select>
		                <label for="floatingSelect">Cuenta origen</label>
		              </div>
		            </div>
		            
		            <div class="col-md-6">
		                <br>
		            	<input type="submit"
		            	name="btnCancelarInsert"
		            	class="btn btn-outline-secondary form-control btn-lg"
		            	value="Cancelar"
		            	onclick="return confirm('¿Esta seguro de cancelar la solicitud?')">
		            </div>
		            <div class="col-md-6">
		                <br>
		            	<input type="submit"
		            	name="btnPedirPrestamo"
		            	class="btn btn-outline-dark form-control btn-lg"
		            	value="Solicitar prestamo"
		            	onclick="return confirm('¿Esta seguro de que quiere solicitar este prestamo?')">
		            </div>
		               
		         </div>
		         </form>
	      		</div>
			</div>
	    <br>
		<%} if (prestamoAPagar.getId_Pr() != -1){ %>
		    <div class="container-fluid" style="width: 40%;" >
		      <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
		        <div class="card-header">
		          <H2 style="text-align: center">Cuotas</H2>
		        </div>
		        <div class="card-body " style="font-size: 13px">
		       	
		        <form method="post" action="ServletPrestamosUsuario" class="row">
		        	<div class="col-md-6">
		                <div class="form-floating mb-3">
		                    <input 
		                    type="number"
		                    class="form-control"
		                    name="txtCuotas"
		                    placeholder="-"
		                    value=<%= prestamoAPagar.getCantCuotas_Pr() + 1 %>
		                    readonly
		                    required>
		                    <label for="floatingInput">Cuota siguiente</label>
		                </div>
		            </div>
		          	<div class="col-md-6">
		           		<div class="form-floating">
							<input 
		                    type="text"
		                    class="form-control"
		                    name="txtParaPagar"
		                    placeholder="-"
		                    step=0.01
		                    value=<%= prestamoAPagar.getImpPagoAlMes_Pr() %>
		                    readonly
		                    required>
							<label for="floatingSelect">Monto</label>
						</div>
					</div>
					<input type="hidden" value=<%= prestamoAPagar.getId_Pr() %> name="prestamoCuotaPagar">
			        <div class="col-md-12">
			            <div class="form-floating">
			                <select
			                  name="ddlCuentaOrigenParaPagar"
			                  id=""
			                  class="form-select"
			                  id="floatingInput"
			                  placeholder="-"
			                >
			                <%if (listaCuentasOrigen!=null)
			                	for (Cuentas c : listaCuentasOrigen){%>
			                  		<option value=<%=c.getNro_Cuentas() %>>  <%="CBU " +c.getCBU_Cuentas() + " - Saldo $" + c.getSaldo_Cuentas() %></option>	
			                <%}%>
			                </select>
			                <label for="floatingSelect">Cuenta origen para debitar</label>
			            </div>
			        </div>
			        <div class="col-md-6">
		                <br>
		            	<input type="submit"
		            	 name= "btnCancelarCuota"
		            	 class="btn btn-outline-secondary form-control btn-lg"
		            	 value="Cancelar"
		            	 onclick="return confirm('¿Esta seguro de que cancelar el pago?')">
		           </div>
			        <div class="col-md-6">
		                <br>
		            	<input type="submit"
		            	 name= "btnPagarCuota"
		            	 class="btn btn-outline-dark form-control btn-lg"
		            	 value="Pagar"
		            	 onclick="return confirm('¿Esta seguro de que quiere pagar esta cuota?')">
		           </div>
		        </form>     
		          
		        </div>
		      </div>
		    </div>
		<%}%>
    </div>
    
    
    <div style="display: flex; justify-content: center;">
    
  		<%
	    if (importeNegativo == true){
	    %>
	   <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
	         <strong>Error</strong> El importe no debe ser negativo!
	         <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	   </div>
       <%}%>    
        <%
        if (mensaje == -2){
        %>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
            <strong>Error</strong> La cuenta de origen no puede ser la misma que la de destino.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        else if (mensaje == -1){
        %>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
            <strong>Error</strong> La cuenta no tiene saldo disponible para la transferencia.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        else if (mensaje == 0){
        %>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger" runat="server" visible="false">
            <strong>Error</strong> Hubo un error al realizar la transferencia.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        else if (mensaje == 1){
        %>
        
        <div ID="MsgCorrectoDiv" class="col-md-4 alert alert-success" runat="server" visible="false">
            <strong>Correcto</strong> Transferencia realizada correctamente!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <%
        }
        %>
   </div>
    
    <div style="display: flex; justify-content: center;">
   		<%
			boolean resultadoInsert = false;
		    if (request.getAttribute("resultadoPrestamo")!=null) resultadoInsert = (boolean)request.getAttribute("resultadoPrestamo");
	    	if (resultadoInsert == false && request.getAttribute("resultadoPrestamo") != null){
    	%>
	       <div ID="MsgErrorDiv" style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-danger" runat="server" visible="false">
	           <strong>Error</strong> No se pudo solicitar prestamo correctamente.
	           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	       </div>
       <%}else if (resultadoInsert){
       %>
	       <div ID="MsgCorrectoDiv"  style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-success" runat="server" visible="false">
	           <strong>Agregado</strong> Prestamo solicitado correctamente.
	           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	       </div>
       <%}%>
    </div>
    
    <div style="display: flex; justify-content: center;">
   		<%
			boolean resultadoPagado = false;
		    if (request.getAttribute("resultadoPagado")!=null) resultadoInsert = (boolean)request.getAttribute("resultadoPagado");
	    	if (resultadoPagado == false && request.getAttribute("resultadoPagado") != null){
    	%>
	       <div ID="MsgErrorDiv" style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-danger" runat="server" visible="false">
	           <strong>Error</strong> No se pudo pagar la cuota correctamente.
	           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	       </div>
       <%}else if (resultadoPagado){
       %>
	       <div ID="MsgCorrectoDiv"  style="font-size: 16px box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;" class="col-md-6 alert alert-success" runat="server" visible="false">
	           <strong>Agregado</strong> Cuota pagada correctamente.
	           <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	       </div>
       <%}%>
    </div>
    
    <br>
    <%@ include file="FooterPage.html" %>
  </body>
</html>
