package com.teknei.practicas.concesionario.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.teknei.practicas.concesionario.entidades.Marcas;

@RepositoryRestResource(collectionResourceRel = "marcas", path = "marcas")
public interface MarcaRepositorio extends CrudRepository<Marcas, Long>{

}
