package com.iLog.app.helpers;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iLog.app.IServices.IProductoService;
import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;
import com.iLog.app.repositories.ProductoRepository;


@Component
public class ProductoHelper {

	@Autowired
    ProductoRepository prodServ;
	@Autowired
	IProductoService productoServiceImpl;
	
	public List<Producto> addNewProdOrIncrement(Producto producto, List<Producto> a, Almacenamiento alma) {
		alma.getProds().stream().parallel().forEach(prods -> {
			// Si el producto pasado por parametro tiene el mismo nombre de uno de los
			// productos del almacen se le resta la cantidad de ese producto al actual
			if (producto.getNameProd().equals(prods.getNameProd()) && producto.getState().equals(prods.getState())) {
				Producto productoActual = productoServiceImpl.getById(prods.getIdProd());
				productoActual.setAmount(prods.getAmount() + producto.getAmount());
				prodServ.save(productoActual);
				a.add(productoActual);
			} else {
				// si no coincide con el nombre de uno de los productos del almacen se agregan
				// los productos del almacen a la lista
				a.add(prods);
			}

		});
		return a;

	}
	public List<Producto> checkStateProd(Producto producto, List<Producto> a, Almacenamiento alma){
		if (producto.getState().equals(Estado.ENSTOCK)) {
			a = this.addNewProdOrIncrement(producto, a, alma);
		}
		if(producto.getState().equals(Estado.PEDIDO)) {
			a= this.addPedidoProd(producto, a, alma);
		}
		
		return a;
	}
	
	public void addProdToListOrNot (Producto producto, List<Producto> a, Almacenamiento alma) {
		AtomicReference<Boolean> enter = new AtomicReference<Boolean>();
		alma.getProds().stream()
		.forEach(p ->{
			String nombreProductoParam = producto.getNameProd();
			String nombreRecorrido = p.getNameProd();
			if(nombreRecorrido.equals(nombreProductoParam)){
				enter.set(true);
			}
		});
		
	if(enter.get()== null) {
		a.add(producto);
	}
	}
	public List<Producto> addPedidoProd(Producto producto, List<Producto> a, Almacenamiento alma){
		alma.getProds().stream().parallel().forEach(prods -> {
			
			if (producto.getNameProd().equals(prods.getNameProd())) {
				Producto productoActual = productoServiceImpl.getById(prods.getIdProd());
				productoActual.setAmount(prods.getAmount() - producto.getAmount());
				prodServ.save(productoActual);
				a.add(productoActual);
			} else {
				
				a.add(prods);
			}

		});
		return a;
	}
}
