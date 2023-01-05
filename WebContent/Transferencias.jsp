<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cuentas" %>
<%@ page import="entidades.Usuarios" %>  
<%@ page import="entidades.Movimientos" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Transferencias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<%
	ArrayList <Cuentas> listaCuentasOrigen = null;
	if (request.getAttribute("listaCuentasOrigen")!=null) listaCuentasOrigen=(ArrayList <Cuentas>)request.getAttribute("listaCuentasOrigen");
	
	ArrayList <Cuentas> listaCuentasDestino = null;
	if (request.getAttribute("listaCuentasDestino")!=null) listaCuentasDestino=(ArrayList <Cuentas>)request.getAttribute("listaCuentasDestino");
%>  
  </head>
  <body>
    <%@ include file="MasterPageAdmin.html" %>
	<br>
	
	<form action ="servletTransferencias" method="post">
    <div class="container-fluid" style="width: 50%">
      <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
        <div class="card-header">
          <H2 style="text-align: center">Transferencias</H2>
        </div>
        <div class="card-body" style="font-size: 13px">
          <form action="Transferencias.jsp" method="get">
          	<br>
            <div class="col-md-12">
              <div class="form-floating" name="">
                <select
                  name="ddlCuentaOrigen"
                  id=""
                  class="form-select"
                  id="floatingInput"
                  placeholder="-"
                  required
                >
                <%
                if (listaCuentasOrigen!=null)
                	for (Cuentas cuenta : listaCuentasOrigen){ 	
                %>
                  <option value=<%=cuenta.getNro_Cuentas() %>>  <%="ID " + cuenta.getNro_Cuentas() + " - $" + cuenta.getSaldo_Cuentas() + " - " + cuenta.getCBU_Cuentas() + " - " + cuenta.getUsuario_Cuentas().getNombre_Usr() + " " + cuenta.getUsuario_Cuentas().getApellido_Usr() %></option>
                <%} %>
                </select>
                <label for="floatingSelect">Cuenta origen</label>
              </div>
            </div>
			<br>
            <div class="col-md-12">
              <div class="form-floating" name="">
                <select
                  name="ddlCuentaDestino"
                  id=""
                  class="form-select"
                  id="floatingInput"
                  placeholder="-" >
                   <%
                if (listaCuentasDestino!=null)
                	for (Cuentas cuenta : listaCuentasDestino){
                %>
                  <option value= <%=cuenta.getNro_Cuentas()%>>  <%="ID " + cuenta.getNro_Cuentas() + " - " + cuenta.getCBU_Cuentas() + " - " + cuenta.getUsuario_Cuentas().getNombre_Usr() + " " + cuenta.getUsuario_Cuentas().getApellido_Usr() %></option>
                <%} %>
                </select>
                <label for="floatingSelect">Cuenta destino</label>
              </div>
            </div>
			<br>
            <div class="col-md-12">
              <div class="form-floating mb-3">
                <input
                   type="text"
                  class="form-control"
                  id="floatingInput"
                  name="txtDetalle"
                  placeholder="-"
                  required
                />
                <label for="floatingSelect">Detalle</label>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-floating mb-3">
                <input
                 
                  type="number"
                  step=0.01
                  class="form-control"
                  id="floatingInput"
                  name="txtImporte"
                  placeholder="-"
                  required
                />
                <label for="floatingSelect">Importe</label>
              </div>
            </div>
			<br>
            <div class="col-md-12">
              <input
                type="submit"
                class="btn btn-outline-dark form-control btn-lg"
                name="btnTransferir"
                value="Transferir"
                class="btn btn-outline-dark btn-sm"
                min=0.01
                onclick="return confirm('¿Esta seguro de que hacer esta transferencia?')"
              />
            </div>
          </form>
        </div>
      </div>
    </div>
    <br>
   
<%
int mensaje=-3;
if (request.getAttribute("mensaje")!=null) mensaje=(int)request.getAttribute("mensaje");   

                		
Boolean importeNegativo = false;
if (request.getAttribute("importeNegativo")!=null) {
	importeNegativo= (boolean)request.getAttribute("importeNegativo");
}
%>  



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
    <%@ include file="FooterPage.html" %>
    </form>
  </body>
</html>
