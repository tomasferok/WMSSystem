package com.iLog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iLog.app.entities.Venta;
import com.iLog.app.services.IVentaService;

@RestController
@RequestMapping("/api")
public class VentasController {
	@Autowired
	IVentaService ventaSrv;
	
	@GetMapping("/ventas")
	public List<Venta> getAll(){
		
		return ventaSrv.getAll();
	}
}
