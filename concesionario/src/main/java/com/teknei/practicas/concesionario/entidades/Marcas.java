package com.teknei.practicas.concesionario.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "MARCAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marcas implements Serializable{
	
	private static final long serialVersionUID = 7623251760150200816L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@Column(nullable = false)
	private String marca;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonBackReference
	@OneToMany(mappedBy = "marcas", fetch = FetchType.LAZY)
	private final Set<Coches> coches = new HashSet<>();
}
