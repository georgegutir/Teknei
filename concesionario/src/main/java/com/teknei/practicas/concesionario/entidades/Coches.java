package com.teknei.practicas.concesionario.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coches implements Serializable{
	
	private static final long serialVersionUID = 7623251760150200816L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false, length = 8)
	private String matricula;

	@JsonBackReference
	@JoinColumn(name = "marca_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Marcas marcas;
}
