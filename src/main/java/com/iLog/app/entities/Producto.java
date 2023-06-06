package com.iLog.app.entities;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="productos")
@Data
public class Producto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProd;

	@Column(name = "name_prod", nullable = false)
	private String nameProd;

	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "amount", nullable = false)
	private double amount;

	private Estado state;
	
	private Long idAlma;
	
	private static final long serialVersionUID = 1L;

	
	


}
