<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="col-10" style="background-color: #f2f2f2; height: 100vh;"
	id="formulario">
	<h2>Listado</h2>
	<form action="lista" method="get" class="form-group mb-2">
		<div class="form-group row">
			<label for="marcas" class="col-sm-2 col-form-label font-weight-bold">Marca</label>
			<div class="form-group mx-sm-3 mb-2">
				<select class="form-control custom-select" required id="marca_id"
					name="marca_id">
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
			<button type="submit" class="btn btn-primary mb-2">Consultar</button>
		</div>
	</form>
	<table class="table table-striped">
		<thead class="bg-primary">
			<tr>
				<th scope="col">Modelo</th>
				<th scope="col">Matrícula</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${coches}" var="coche">
				<tr>
					<td>${coche.modelo}</td>
					<td>${coche.matricula}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>