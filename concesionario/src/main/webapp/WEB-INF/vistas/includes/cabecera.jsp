<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="es_ES" />

<!doctype html>
<html lang="es">
<head>

<!-- base pa tener el mismo menu en todas las pag y no se rompa la ruta  -->
<base href="${pageContext.request.contextPath}/" />


<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- FontAwesome -->
<link rel="stylesheet" href="css/all.min.css">
<!-- Datatables -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<!-- Hoja de estilos personalizada -->
<link rel="stylesheet" href="css/concesionario.css">

<title>Concesionario</title>
</head>
<body>
	<nav class="navbar navbar-light justify-content-end" style="background-color: #9dc3e7;">
  		<a class="navbar-brand" href="index">
    	<img src="img/logo.jpg" width="100" height="70">
  		</a>
	</nav>
	
	<main class="container-justify"> 
	
	<div class="row">
	<div class="col-2" style="background-color: #4472c4">
		<div class="nav flex-column nav-pills container pt-3" id="v-pills-tab" role="tablist"
			aria-orientation="vertical">
			<a class="nav-link text-dark" id="index" href="index">Alta coche</a> 
			<a class="nav-link text-dark" id="listado" href="listado">Listado coches</a>
		</div>
	</div>
	