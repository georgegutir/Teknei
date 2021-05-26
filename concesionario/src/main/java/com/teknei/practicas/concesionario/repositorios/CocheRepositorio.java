package com.teknei.practicas.concesionario.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.teknei.practicas.concesionario.entidades.Coches;

@RepositoryRestResource(collectionResourceRel = "coches", path = "coches")
public interface CocheRepositorio extends CrudRepository<Coches, Long>{
	//@Query("SELECT c.modelo, c.matricula FROM Coches c LEFT JOIN Marcas m ON c.marca_id = m.id")
	@Query("Select c From Coches c Where marca_id = :id")
	Iterable<Coches> findByMarca(Long id);
}
