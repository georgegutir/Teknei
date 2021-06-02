<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="col-10" style="background-color: #f2f2f2; height: 100vh;">
	<h2>Alta</h2>

	<!-- marca -->
	<form action="alta" method="post" class="container pt-3">
		<div class="form-group row">
			<label for="marcas" class="col-sm-2 col-form-label font-weight-bold">Marca</label>
			<div class="col-sm-10">
				<select class="form-control custom-select" required id="marcas"
					name="marcas">
					<option value="0">Seleccione una marca...</option>
					<%--
						<c:forEach items="${marcas}" var="m">
							<option value="${m.id}">${m.marca}</option>
						</c:forEach>
						--%>

					<option value="1">Seat</option>
					<option value="2">Renault</option>
					<option value="3">Citroen</option>

				</select>
			</div>
		</div>

		<!-- modelo -->
		<div class="form-group row">
			<label for="modelo" class="col-sm-2 col-form-label font-weight-bold">Modelo</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="modelo" id="modelo">
			</div>
		</div>

		<!-- matrícula -->
		<div class="form-group row">
			<label for="matricula"
				class="col-sm-2 col-form-label font-weight-bold">Matrícula</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="matricula"
					id="matricula" placeholder="Introduzca la matrícula, 1111-AAA">
			</div>
		</div>
		<button type="submit" name="alta" value="alta"
			class="btn btn-primary btn-lg">Dar de alta</button>
	</form>
</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>