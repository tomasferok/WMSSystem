package com.iLog.app.IServices;

import java.util.List;

import com.iLog.app.entities.Carrito;
import com.iLog.app.entities.Producto;

public interface ICarritoServie {

	List<Carrito>getAll();
	Carrito getById(Long id);
	void remove(Long id);
	Carrito save( Carrito carrito);
	double calcularTotal(Carrito carrito);
	void comprar(Long id);
}
