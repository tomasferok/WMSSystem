package com.iLog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.entities.Venta;
import com.iLog.app.repositories.VentaRepository;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	VentaRepository ventaService;

	@Override
	public List<Venta> getAll() {
		// TODO Auto-generated method stub
		return (List<Venta>) ventaService.findAll();
	}

	@Override
	public Venta save(Venta venta) {
		// TODO Auto-generated method stub
		return ventaService.save(venta);
	}

}
