package com.iLog.app.services;

import java.util.List;

import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;

public interface IProductoService {

	List<Producto> getAll();
	Producto getById(Long id);
	void remove(Long id);
	Producto save( Producto prod);
	Producto findByNameProd (String nombreProd);
	
	Producto findByNameProdAndState(String nombreProd, Estado e);
}
