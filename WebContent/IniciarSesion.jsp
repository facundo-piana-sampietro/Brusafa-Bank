<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/3c33daaf69.js" crossorigin="anonymous"></script>
    <title>Iniciar sesion</title>
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background: #23313e; font-weight: bold; font-size: 15px;">
<div class="container-fluid ">	
	<img src="assets/BRUSAFA.png"  alt="" width="80" style="border-radius: 80%;" >
 	<ul class="navbar-nav me-auto my-2 "></ul>
</nav>	

<br>
	<H1 style="font-family: 'Enriqueta', arial, serif; line-height: 1.25; margin: 0 0 10px; font-size: 40px; font-weight: bold; text-align:center;">Bienvenido al banco Brusafa</H1>
<br>

<form method="post" action="servletUsuarios" class="row">
<div style="width: 40%; margin: auto">
      <div class="card text-center" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 100px;">
        <div class="card-header">Iniciar sesión</div>
        <div class="card-body">
            <div class="col-md-12">
              <div class="form-floating mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="floatingInput"
                  name="txtUsuario"
                  placeholder="-"
                  required
                />
                <label for="floatingInput">Usuario</label>
              </div>
            </div>

            <div class="col-md-12">
              <div class="form-floating mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="floatingInput"
                  name= "txtContraseña"
                  placeholder="-"
                />
                <label for="floatingInput">Contraseña</label>
              </div>
            </div>

            <div class="col-md-12">
              <button
                type="submit"
                class="btn btn-outline-dark form-control btn-lg"
                name="btnIniciar"
                
              >
                Ingresar
              </button>
            </div>
        </div>
      </div>
    </div>
   </form>
<br><br>
 		<% 
        boolean noExiste=false;
        if (request.getAttribute("noExiste")!=null) noExiste=(boolean)request.getAttribute("noExiste");
        if (noExiste==true){
     	%>
      
	<div style="display: flex; justify-content: center; visibility="hidden";>
        <div ID="MsgErrorDiv" class="col-md-4 alert alert-danger">
            <strong>Error</strong> Usuario o contraseña incorrecta!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </div>
    <% 
    }
    %>    	


    <br>
<%@ include file="FooterPage.html" %>
</body>
</html>