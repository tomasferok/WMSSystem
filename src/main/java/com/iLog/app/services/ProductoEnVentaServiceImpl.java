package com.iLog.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.entities.ProductoEnVenta;
import com.iLog.app.repositories.ProductoVentaRepository;

@Service
public class ProductoEnVentaServiceImpl implements IProductoVentaService {

	@Autowired
	ProductoVentaRepository pService;
	@Override
	public ProductoEnVenta save(ProductoEnVenta p) {
		// TODO Auto-generated method stub
		return pService.save(p);
	}

}
