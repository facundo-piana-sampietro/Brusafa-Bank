<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cuentas" %>
<%@ page import="entidades.Usuarios" %>
<%@ page import="entidades.Movimientos" %>
<%@ page import="entidades.Provincias" %>
<%@ page import="entidades.Prestamos" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Reportes</title>
    
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
                scrollY: 320,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange": false,
                "bFilter": false,
                "bInfo": false,
            });
        });
    </script>
    
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
                scrollY: 179,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange": false,
                "bFilter": false,
                "bInfo": false,
            });
        });
    </script>
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
                scrollY: 185,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange": false,
                "bFilter": false,
                "bInfo": false,
            });
        });
    </script>
    
    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id_prestamos').DataTable({
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
                scrollY: 320,
                lengthMenu: [ [5, 25, -1], [10, 25, "All"] ],
                "bLengthChange": false,
                "bFilter": false,
                "bInfo": false,
            });
        });
    </script>
    
    <style>
    	span {
    		font-size: 11px;
    		font-weight: bold;
    	}
    </style>
    
    
    
  </head>
  <body>
    <%@ include file="MasterPageAdmin.html" %>
    	<br>
			<H1 style="font-family: 'Enriqueta', arial, serif; line-height: 1.25; margin: 0 0 10px; font-size: 40px; font-weight: bold; text-align:center;">Movimientos</H1>
		<br>
	<div style="display:flex; justify-content:center;">
      <div class="container-fluid" style="width: 20%">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
          <table class="table table-hover" style="text-align: start; margin: 1px; font-size: 13px">
        	<form action="servletReportes" method="get">
        	<tbody>
              <td>
	              <span>Propietario de cuenta remitente</span>
	              <input
	                  type="text"
	                  class="form-control form-control-sm"
	                  id="floatingInput"
	                  name="txtDniRemitenteMovimiento"
	                  placeholder = "Dni del propietario"/>
              </td>
            </tbody>
            <tbody>
              <td>
              <span>Propietario de cuenta receptora</span>
              <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtDniReceptorMovimiento"
                  placeholder = "Dni del propietario"
                />
              </td>
            </tbody>
            <tbody>
              <td>
              	<span>tipo movimiento</span>
                <select
                  name="ddlTipos"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  id="ddlTiposMovimiento"
                >
                  <option value="-1"> Tipo movimientos</option>
                  <option value="1">Alta de cuenta</option>
                  <option value="2">Alta de un prestamo</option>
                  <option value="3">Pago de prestamo</option>
                  <option value="4">Transferencia</option>
                </select>
              </td> 
			</tbody>
			<tbody>
              <td>
              	<span>Importe</span>            
                <div class="form-floating row p-0 m-0">
                            <input
			                  type="number"
			                  step=0.01
			                  min=0.01
			                  class="form-control form-control-sm w-50"
			                  id="floatingInput"
			                  name="importe1Movimiento"
			                  placeholder = "Desde"/>

			                  <input
			                  type="number"
			                  step=0.01
			                  min=0.01
			                  class="form-control form-control-sm w-50"
			                  id="floatingInput"
			                  name="importe2Movimiento"
			                  placeholder = "Hasta"/>
                </div>         
              </td>
            </tbody>     
            <tbody>	
              <td>
                    <button type="submit" class="btn btn-outline-dark btn-sm" name = "btnFiltrarMovimientos">Filtrar</button>
                    <button type="submit" class="btn btn-outline-secondary btn-sm" name = "btnQuitarFiltrosMovimientos">Quitar filtros</button>
              </td>
            </tbody>
          </form>
          </table>
        </div>
      </div>
	<br>
      <div class="container-fluid" style="width: 90%">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
          <div class="card-header"><h5>Movimientos</h5></div>
          <form action= "servletReportes" method="get">
          <table class="table table-hover" id = "table_id_movimientos" style="font-size: 12px">
            <thead>
              <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Detalle</th>
                <th>Importe</th>
                <th>Tipo</th>
                <th>Cuenta</th>
                <th>Cuenta destino</th>
              </tr>
            </thead>
            <tbody>
            <%
            ArrayList<Movimientos> listaMovimientos = null;
            
            if (request.getAttribute("listaMovimientosFiltrada") != null)
            	listaMovimientos = (ArrayList<Movimientos>) request.getAttribute("listaMovimientosFiltrada");
            %>
	            <%
	            if (request.getAttribute("listaMovimientosFiltrada") != null)
		            for (Movimientos movimiento : listaMovimientos)
		            { 
	            %>
              <tr>   
                <td><%=movimiento.getId_Mov() %></td>
                <td><%=movimiento.getFecha_Mov() %></td>
                <td><%=movimiento.getDetalle_Mov() %></td>
                <td><%=movimiento.getImporte_Mov() %></td>
                <td><%=movimiento.getTiposMov_Mov().getDescripcion_TiposMov() %></td>
                
                <%if (!movimiento.getCuenta_Mov().getUsuario_Cuentas().getNombre_Usr().equals("Brusafa")){ %>
                <th><%=movimiento.getCuenta_Mov().getUsuario_Cuentas().getNombre_Usr() %> <%=movimiento.getCuenta_Mov().getUsuario_Cuentas().getApellido_Usr() %></th>
				<%}else{%>
				<th> - </th>
				<%}if (!movimiento.getCuentaDestino_Mov().getUsuario_Cuentas().getNombre_Usr().equals("Brusafa")){ %>
                <th><%=movimiento.getCuentaDestino_Mov().getUsuario_Cuentas().getNombre_Usr() %> <%=movimiento.getCuentaDestino_Mov().getUsuario_Cuentas().getApellido_Usr() %></th>
              	<%}else{%>
              	<th> - </th>
              	<%} %>
              </tr>
              <%}%>
            </tbody>
          </table>
   		</form>
        </div>
      </div>
      </div>
	
	<br>
		<H1 style="font-family: 'Enriqueta', arial, serif; line-height: 1.25; margin: 0 0 10px; font-size: 40px; font-weight: bold; text-align:center;">Clientes</H1>
	<br>
   <div style="display:flex; justify-content:center;">
      <div class="container-fluid" style="width: 20%">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
          <table
            class="table table-hover"
            style="text-align: start; margin: 1px; font-size: 13px">
			<form action= "servletReportes" method="get" class="row">
       		<tbody>
              <td>
              <span>Nombre</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtNombreCliente"
                  placeholder="nombre"
                />
              </td>
            </tbody>
         	<tbody>
              <td>
              	<span>Apellido</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtApellidoCliente"
                  placeholder="apellido"
                />
              </td>
            </tbody>
            <tbody>
              <td>
              	<span>Dni</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtDniCliente"
                  placeholder="DNI"
                />
              </td>
            </tbody>
            <tbody>
              <td>
              	<span>Provincia</span>
                <select
                  name="ddlProvinciasCliente"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  id="ddlProvinciasCliente"
                >
                	<option value = "-1" selected>Provincias</option>
                  <%
		            ArrayList<Provincias> listaProvincias = null;
		            if (request.getAttribute("listaProvincias") != null)
		            	listaProvincias = (ArrayList<Provincias>) request.getAttribute("listaProvincias");
		            %>
		            <%
		            if (request.getAttribute("listaProvincias") != null)
			            for (Provincias provincia : listaProvincias)
			            {
		            %>
		            	<option value = <%=provincia.getIdProvincia_Prov() %>><%=provincia.getDescripcion_Prov()%> </option>
		            <%	} %>
                  
                </select>
              </td>
              </tbody>
            <tbody>
              <td>
           		<button type="submit" name ="btnFiltrarUser" class="btn btn-outline-dark btn-sm">Filtrar</button>
                <button type="submit" class="btn btn-outline-secondary btn-sm" name = "btnQuitarFiltrosUsuarios">Quitar filtros</button>
              </td>
            </tbody>
      		</form>
          </table>
        </div>
      </div>
      <br>
     	
    <% 
    ArrayList <Usuarios> listaUser = null;
	if (request.getAttribute("listaUser") != null)
		listaUser = (ArrayList<Usuarios>)request.getAttribute("listaUser");
	%>
      <div class="container-fluid" style="width: 81.5%">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
          <div class="card-header"><h5>Clientes</h5></div>
          <table class="table table-hover" id="table_id_usuarios" style="text-align: start; margin: 1px; font-size: 13px"> 
            <thead>
              <tr>
                <th>DNI</th>   
                <th>CUIL</th> 
                <th>Nombre</th> 
                <th>Apellido</th>
                <th>Sexo</th> 
                <th>Fecha de nacimiento</th> 
                <th>Dirección</th>
                <th>Provincia</th>
                <th>Email</th>
                <th>Teléfono</th> 
                <th>Usuario</th>
                <th>Contraseña</th>
              </tr>
            </thead>
            <tbody>
            <%
              	if (listaUser != null){
              		for(Usuarios user: listaUser){
              			if (user.isTipo_Usr()==false && user.isEstado_Usr()==true){
             %>
              <tr>
                <td><%=user.getDNI_Usr() %> </td>
                <td><%=user.getCUIL_Usr() %></td>                                            
                <td><%=user.getNombre_Usr() %></td>
                <td><%=user.getApellido_Usr() %></td>                       
                <td><%=user.getSexo_Usr() %></td>                                
                <td><%=user.getFechaNacimiento_Usr()%></td>           
                <td><%=user.getDireccion_Usr()%></td>           
                <td><%=user.getProvincia_Usr().getDescripcion_Prov()%></td>                 
                <td><%=user.getEmail_Usr() %></td>           
                <td><%=user.getTelefono_Usr() %></td>
                <td><%=user.getUsuario_Usr() %></td>  
                <td><%=user.getContrasenia_Usr() %></td>  
              </tr>
              <%
              		}
              }
              }
              %>
            </tbody>
          </table>         
        </div>
      </div>
	</div>

    
    	<br>
			<H1 style="font-family: 'Enriqueta', arial, serif; line-height: 1.25; margin: 0 0 10px; font-size: 40px; font-weight: bold; text-align:center;">Cuentas</H1>
		<br>
	<div style="display:flex; justify-content:center;">
      <div class="container-fluid" style="width: 20%">
        <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
          <table
            class="table table-hover"
            style="text-align: start; margin: 1px; font-size: 13px"
          >
          <form action= "servletReportes" method="get">
            <tbody>
              <td>
              	<span>Propietario de cuenta:</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtNombreCuentas"
                  placeholder="DNI"
                />
              </td>
           </tbody>
           <tbody>
              <td>
              	<span>Tipo </span>
                <select
                  name="ddlTiposCuentas"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  id="ddlTipos"
                >
                  <option value="-1" selected> Tipo cuentas</option>
                  <option value=1>Caja de ahorro</option>
                  <option value=2>Cuenta corriente</option>               
                </select>
              </td>
			</tbody>
			<tbody>
              <td>
              	<span>Saldo</span>
              	<div class="form-floating row p-0 m-0">
	                <input
	                  type="number"
	                  step=0.01
	                  min=0.01
	                  class="form-control form-control-sm w-50"
	                  name="txtSaldoMenorCuenta"
	                  placeholder = "Desde"
	                />
	                <input
	                  type="number"
	                  step=0.01
	                  min=0.01
	                  class="form-control form-control-sm w-50"
	                  name="txtSaldoMayorCuenta"
	                  placeholder = "Hasta"
	                />
                </div>
              </td>
              </tbody>
            <tbody>
              <td>
                    <button type="submit" name = "btnFiltrarCuentas" class="btn btn-outline-dark btn-sm ">Filtrar</button>
                    <button type="submit" class="btn btn-outline-secondary btn-sm" name = "btnQuitarFiltrosCuentas">Quitar filtros</button>
              </td>
            </tbody>
            </form>
          </table>
        </div>
      </div>
     
      
      
      	<%
			ArrayList <Cuentas> listaCuentas = null;
			if (request.getAttribute("listaCuentas")!=null)
				listaCuentas=(ArrayList <Cuentas>)request.getAttribute("listaCuentas");
		%>  
		<br>
	
      <div class="container-fluid" style="width: 88%">
        <div class="card text-center">
          <div class="card-header"><h5>Cuentas</h5></div>
          <table class="table table-hover" id="table_id_cuentas" style="font-size: 12px">
            <thead>
              <tr>
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
                <td><%=cuenta.getNro_Cuentas() %> </td> 
                <td><%=cuenta.getCBU_Cuentas() %></td>
                <td><%=cuenta.getTipoCuenta_Cuentas().getDescripcion_TipoCuenta() %></td>  
                <td><%=cuenta.getUsuario_Cuentas().getDNI_Usr() %></td>
                <td><%=cuenta.getFechaCreacion_Cuentas() %></td>   
                <td><%=cuenta.getSaldo_Cuentas() %></td>
              </tr>
              <%
              	}
              }
              %>
            </tbody>
          </table>
        </div>
   
      </div>
      </div>   
	
		<br>
			<H1 style="font-family: 'Enriqueta', arial, serif; line-height: 1.25; margin: 0 0 10px; font-size: 40px; font-weight: bold; text-align:center;">Prestamos</H1>
		<br>
	<div style="display:flex; justify-content:center;">
      <div class="container-fluid" style="width: 20%">
        <div class="card text-center">
          <table class="table table-hover" style="text-align: start; margin: 1px; font-size: 13px">
          <form action= "servletReportes" method="get">
       		<tbody>
              <td>
              	<span>Propietario de cuenta:</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtNombrePrestamo"
                  placeholder="DNI"/>
              </td>
            </tbody>
            <tbody>  
              <td>
              	<span>Cantidad de cuotas</span>
                <input
                  type="text"
                  class="form-control form-control-sm"
                  id="floatingInput"
                  name="txtCuotasPrestamo"
                  placeholder="Cantidad"
                />
              </td>
            </tbody>
            <tbody>
              <td>
              	<span>Importe a pagar por cliente</span>
                <div class="form-floating row p-0 m-0">
                    <input type="number"
                    class="form-control form-control-sm w-50"
                    type="number"
	                step=0.01 
	                min=0.01
                    name="txtImportePagar1"
                    placeholder="Desde"
                    >
                    <input type="number"
                    class="form-control form-control-sm w-50"
	                step=0.01 
	                min=0.01
	                name="txtImportePagar2"
	                placeholder="Hasta">
                </div>
              </td>
			</tbody>
			<tbody>
              <td>
           		<span>Importe pedido por cliente</span>
           		<div class="form-floating row p-0 m-0">
                    <input type="number"
                    class="form-control form-control-sm w-50"
                    name="txtImportePedido1"
                    min=0.01
                    step=0.01
                    placeholder="Desde">
                    <input type="number"
                    class="form-control form-control-sm w-50"
                    name="txtImportePedido2"
                    min=0.01
                    step=0.01
                    placeholder="Hasta"
                    >
                </div>
              </td>
            </tbody>
             <tbody>
           	  <td>
               	<button type="submit" name = "btnFiltrarPrestamos" class="btn btn-outline-dark btn-sm ">Filtrar</button>
                <button type="submit" class="btn btn-outline-secondary btn-sm" name = "btnQuitarFiltrosPrestamos">Quitar filtros</button>
              </td>
            </tbody>
            </form>
          </table>
        </div>
      </div>
      <%
		ArrayList <Prestamos> listaPrestamos = null;
		if (request.getAttribute("listaPrestamos")!=null)
			listaPrestamos = (ArrayList <Prestamos>)request.getAttribute("listaPrestamos");
		%> 
		<br>
      <div class="container-fluid" style="width: 90%">
        <div class="card text-center">
          <div class="card-header"><h5>Prestamos</h5></div>
          <table class="table table-hover" id = table_id_prestamos style="font-size: 12px">
            <thead>
              <tr>
                <th>ID</th>
                <th>Propietario de cuenta</th>
                <th>Fecha</th>
                <th>Importe a pagar por cliente</th>
                <th>Importe pedido</th>
                <th>Plazo de pago en meses</th>
                <th>Monto a pagar x cuota</th>
                <th>Cantidad de cuotas</th>
              </tr>
            </thead>
            <%
                if (listaPrestamos!=null)
                	for(Prestamos prestamo : listaPrestamos){
                		if (prestamo.isEstado_Pr()){
                %>
            <tbody>
              <tr>
                <td><%= prestamo.getId_Pr() %></td>
                <td><%= prestamo.getCuentaDestino_Pr().getUsuario_Cuentas().getNombre_Usr() + " " + prestamo.getCuentaDestino_Pr().getUsuario_Cuentas().getApellido_Usr() %></td>
                <td><%= prestamo.getFechaSolicitado_Pr().toString() %></td>
                <td><%= prestamo.getImpResultante_Pr() %></td>
                <td><%= prestamo.getImpSolicitado_Pr() %></td>
                <td><%= prestamo.getPlazoMeses_Pr() %></td>
                <td><%= prestamo.getImpPagoAlMes_Pr() %></td>
                <td><%= prestamo.getCantCuotas_Pr() %></td>
              </tr>
            </tbody>
            <%}}%>
          </table>
        </div>
      </div>
      </div>
      <br>
      <%@ include file="FooterPage.html" %>
  </body>
</html>
