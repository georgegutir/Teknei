<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<div class="col-10" style="background-color: #f2f2f2;">
		<h2>Alta</h2>
		
		<!-- marca -->
		<form action="alta" method="post" class="container pt-3">
			<div class="form-group row">
				<label for="marcas" class="col-sm-2 col-form-label">Marca</label>
				<div class="col-sm-10">
					<select class="form-control custom-select" required id="marcas" name="marcas">
						<option value="0">Seleccione una marca...</option>	
						<c:forEach items="${Marcas}" var="m">
							<option value="${m.id}"}>${m.marca}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<!-- modelo -->
			<div class="form-group row">
				<label for="modelo" class="col-sm-2 col-form-label">Modelo</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="modelo" id="modelo">
				</div>
			</div>
			
			<!-- matrícula -->
			<div class="form-group row">
				<label for="matricula" class="col-sm-2 col-form-label">Matrícula</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="matricula" id="matricula" placeholder="Introduzca la matrícula, 1111-AAA">
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>