package com.iLog.app.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="proveedores")
@Getter @Setter
@ToString
@EqualsAndHashCode

public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProv;
	
	@Column(nullable = false, unique = true, length = 32)
	private String nombreProv;

	@Column(nullable = false)
	private String contacto;

	@Column(nullable = false, unique = true, length = 32)
	private String email;

	
}
