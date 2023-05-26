package com.iLog.app.entities;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="carritos")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Carrito implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="carrito_id")
	private List<Producto> productos;
	
	private double amount; 
	
	private double total = 0;
	
	private static final long serialVersionUID = 1L;
	


}
