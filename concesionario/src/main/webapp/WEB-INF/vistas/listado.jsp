<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="col-10" style="background-color: #f2f2f2;">
		<h2>Listado</h2>
		<form action="listado" method="post" class="form-group mb-2">
				<div class="form-group row">
					<label for="marcas" class="col-sm-2 col-form-label">Marca</label>
					<div class="form-group mx-sm-3 mb-2">
						<select class="form-control custom-select" required id="Marcas" name="Marcas">
							<option value="0">Seleccione una marca...</option>	
							<c:forEach items="${Marcas}" var="m">
								<option value="${m.id}"}>${m.marca}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary mb-2">Consultar</button>
				</div>	
			
			<table class="table table-striped">
			    <thead>
			      <tr>
			        <th scope="col">Modelo</th>
			        <th scope="col">Matrícula</th>
			      </tr>
			    </thead>
			    <tbody>
			      <c:forEach items="${coches}" var="c">
						<tr>
							<td>${c.modelo}</td>
							<td>${c.matricula}</td>	
						</tr>
					</c:forEach>
			    </tbody>
			 </table>
			 
		 </form>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>