package com.iLog.app.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.iLog.app.entities.Carrito;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.helpers.CarritoHelper;
import com.iLog.app.repositories.CarritoRepository;

@Service
public class CarritoServiceImpl implements ICarritoServie{

	@Autowired
	CarritoRepository carritoService;
	@Autowired
	IProductoService productoService;
	@Autowired
	CarritoHelper carritoHelper;
	@Override
	public List<Carrito> getAll() {
		// TODO Auto-generated method stub
		return (List<Carrito>) carritoService.findAll();
	}

	@Override
	public Carrito getById(Long id) {
		// TODO Auto-generated method stub
		return carritoService.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		carritoService.deleteById(id);
		
	}

	@Override
	public Carrito save(Carrito carrito) {
		//recorrer arry de productos del carrito
		carrito.getProductos().stream().parallel().forEach(prod ->{
			//buscar producto por nombre
			Producto p = productoService.findByNameProdAndState(prod.getNameProd(), prod.getState());
			prod.setIdAlma(p.getIdAlma());
			prod.setPrice(p.getPrice());
			prod.setState(Estado.PEDIDO);
			productoService.save(prod);
			
			
		});
		
//		Carrito caritoSave = carritoHelper.addOrIncrement(carrito);
		return carritoService.save(carrito);
	}
	
	 public double calcularTotal(Carrito carrito) {
		 
			return carritoHelper.calcularTotal(carrito);
		 }
	 
	 public void comprar(Long id) {
		 carritoHelper.comprar(id);
	 }

	

}
