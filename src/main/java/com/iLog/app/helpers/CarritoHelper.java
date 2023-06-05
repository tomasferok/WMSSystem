package com.iLog.app.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iLog.app.IServices.ICarritoServie;
import com.iLog.app.IServices.IProductoService;
import com.iLog.app.IServices.IProductoVentaService;
import com.iLog.app.IServices.IVentaService;
import com.iLog.app.entities.Carrito;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.entities.ProductoEnVenta;
import com.iLog.app.entities.Venta;
import com.iLog.app.repositories.ProductoRepository;

@Component
public class CarritoHelper {

	@Autowired
	IProductoService productoServiceImpl;
	@Autowired
	ProductoRepository prodServ;
	@Autowired
	ICarritoServie carritoService;
	@Autowired
	IProductoVentaService ventaService;
	@Autowired
	IVentaService ventSrv;
	
	public Carrito addOrIncrement(Carrito c) {
	List<Producto>listaProds = new ArrayList<Producto>();
		c.getProductos().stream().parallel().forEach(p ->{
			Producto a = productoServiceImpl.findByNameProdAndState(p.getNameProd(), Estado.PEDIDO);
			if(a != null) {
				a.setAmount(a.getAmount()+p.getAmount());
				prodServ.save(a);
			}else {
				listaProds.add(p);
			}
		});
		c.setProductos(listaProds);
		return c;
	}
	public Carrito seterPrecioProd(Carrito carrito) {
		carrito.getProductos().stream().parallel().forEach(prod ->{
			//buscar producto por nombre
			Producto p = productoServiceImpl.findByNameProdAndState(prod.getNameProd(), prod.getState());
			
			prod.setPrice(p.getPrice());
			
		});
		return carrito;
	}
public double calcularTotal(Carrito carrito) {
		Carrito carre = this.seterPrecioProd(carrito);
	AtomicReference<Double>total = new AtomicReference<Double>();
	total.set(0.0);
	carre.getProductos().stream().parallel().forEach(p->{
		
	 double a =+ p.getAmount() * p.getPrice();
	 total.set(total.get()+ a);
	});
	
		return total.get();
	}
public void comprar(Long id) {
	Carrito carrito = carritoService.getById(id);
	List<ProductoEnVenta> productoVenta = new ArrayList<ProductoEnVenta>();
	
	
	carrito.getProductos().stream().parallel().forEach(p ->{
		ProductoEnVenta pVenta = new ProductoEnVenta();
		pVenta.setNameProd(p.getNameProd());
		pVenta.setPrice(p.getPrice());
		pVenta.setAmount(p.getAmount());	
		productoVenta.add(pVenta);
	});
	
	Venta venta = new Venta();
	venta.setProductosEnVenta(productoVenta);
	carritoService.remove(carrito.getId());
	ventSrv.save(venta);
}
}
