package com.teknei.practicas.concesionario.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.teknei.practicas.concesionario.entidades.Coches;

@RepositoryRestResource(collectionResourceRel = "coches", path = "coches")
public interface CocheRepositorio extends CrudRepository<Coches, Long>{

}
