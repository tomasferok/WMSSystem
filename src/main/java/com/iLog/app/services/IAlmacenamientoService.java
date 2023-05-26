package com.iLog.app.services;

import java.util.List;

import com.iLog.app.entities.Almacenamiento;


public interface IAlmacenamientoService {

	List<Almacenamiento> getAll();
	Almacenamiento getById(Long id);
	void remove(Long id);
	Almacenamiento save( Almacenamiento carrito);

	
}
