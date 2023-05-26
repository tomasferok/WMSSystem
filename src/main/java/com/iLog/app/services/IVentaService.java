package com.iLog.app.services;

import java.util.List;

import com.iLog.app.entities.Venta;

public interface IVentaService {

	List<Venta> getAll();
	Venta save(Venta venta);
}
