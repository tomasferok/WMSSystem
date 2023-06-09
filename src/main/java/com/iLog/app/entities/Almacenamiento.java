package com.iLog.app.entities;

import java.io.Serializable;
import java.util.List;

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
@Table(name ="almacenamientos")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Almacenamiento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="prod_id")
	private List<Producto> prods;
	
	
	private static final long serialVersionUID = 1L;
	


	
	

}
