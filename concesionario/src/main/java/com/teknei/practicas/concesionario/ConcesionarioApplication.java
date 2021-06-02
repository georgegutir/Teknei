package com.teknei.practicas.concesionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.teknei.practicas.controlador","com.teknei.practicas.repositorios","com.teknei.practicas.entidades"})
public class ConcesionarioApplication{

	public static void main(String[] args) {
		SpringApplication.run(ConcesionarioApplication.class, args);
	}
}
