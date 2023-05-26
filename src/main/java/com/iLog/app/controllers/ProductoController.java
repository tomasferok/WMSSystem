package com.iLog.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.entities.Producto;
import com.iLog.app.services.IAlmacenamientoService;
import com.iLog.app.services.IProductoService;



@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private IProductoService prodServ;
	
	@Autowired
	IAlmacenamientoService almaSrv;
	
	@GetMapping("/productos/{id}")
	public Producto getById(@PathVariable Long id) {
		return prodServ.getById(id);
	}
	
	@GetMapping("/productos")
	public List<Producto>getAll(){
		
		return prodServ.getAll();
	}
	
	@PutMapping("/productos/{idProd}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long idProd, RedirectAttributes flash) {
		
		Producto productoActual = prodServ.getById(idProd);
		
		if(idProd > 0) {
			producto = prodServ.getById(idProd);
			flash.addFlashAttribute("error", "El ID de el producto no existe en la BBDD!");
			
		}else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
		}
			productoActual.setNameProd(producto.getNameProd());
			productoActual.setPrice(producto.getPrice());
			productoActual.setAmount(producto.getAmount());
		
		return prodServ.save(productoActual);
		
		
	}
	
	 @PostMapping("/productos")
	    public Producto createProducto(@RequestBody Producto producto) {
		 
//		 	Almacenamiento alma = almaSrv.getById(producto.getIdAlma());
//		 	List<Producto>a = new ArrayList();
//		 
//		 	AtomicReference<Boolean> enter = new AtomicReference<Boolean>();
//		 	alma.getProds().stream().parallel().forEach(prods ->{
//		 		
//		 			if(producto.getNameProd().equals(prods.getNameProd())) {
//		 				Producto productoActual = prodServ.getById(prods.getIdProd());
//		 				productoActual.setAmount(prods.getAmount() + producto.getAmount());
//		 				
//		 				enter.set(true);
//		 				prodServ.save(productoActual);
//		 			}else {
//		 				a.add(prods);
//		 			}
//		 			
//	 				 		
//		 	});
//		 	if(enter.get()!= null) {
//		 		return producto;
//		 	}else {
//		 		a.add(producto);
//			 	alma.setProds(a);
//			 	almaSrv.save(alma);
//		        return alma.getProds().get(0);
//		 	}
		 	
		 	return prodServ.save(producto);
	    }
	 
	 @DeleteMapping("/productos/{idProd}")
	    public void deleteProducto(@PathVariable Long idProd) {
	        prodServ.remove(idProd);
	    }

	

}
