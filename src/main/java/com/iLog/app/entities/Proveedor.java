package com.iLog.app.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="proveedores")
@Data

public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProv;
	
	@Column(name="documento", unique=true)
	private int documento;
	
	@Column(nullable = false, length = 32)
	private String nombreProv;

	@Column(nullable = false)
	private String contacto;

	@Column(nullable = false, length = 32)
	private String email;

	
}
