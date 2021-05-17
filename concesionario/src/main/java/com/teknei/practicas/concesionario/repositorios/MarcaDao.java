package com.teknei.practicas.concesionario.repositorios;

import com.teknei.practicas.concesionario.entidades.Marcas;

public interface MarcaDao extends Dao<Marcas>{
	Marcas obtenerPorIdConCoches(Long id);
}