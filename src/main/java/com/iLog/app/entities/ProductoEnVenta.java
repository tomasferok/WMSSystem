package com.iLog.app.entities;

import java.io.Serializable;

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
@Table(name="productos_venta")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class ProductoEnVenta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProdVenta;

	@Column(name = "name_prod", nullable = false)
	private String nameProd;

	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "amount", nullable = false)
	private double amount;
}
