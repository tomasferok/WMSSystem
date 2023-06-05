package com.iLog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iLog.app.IServices.ICarritoServie;
import com.iLog.app.entities.Carrito;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;



@RestController
@RequestMapping("/api")
public class CarritoController {

	@Autowired
	private ICarritoServie carritoService;
	

	@GetMapping("/carrito")
	public List<Carrito>getAll(){
		
		return carritoService.getAll();
	}
	
	@PutMapping("/carrito/{idCarrito}")
	public Carrito update(@RequestBody Carrito carrito, @PathVariable Long idCarrito, RedirectAttributes flash) {
		
		Carrito carritoActual = carritoService.getById(idCarrito);
		
		if(idCarrito > 0) {
			carrito = carritoService.getById(idCarrito);
			flash.addFlashAttribute("error", "El ID de el carrito no existe en la BBDD!");
			
		}else {
			flash.addFlashAttribute("error", "El ID de la carrito no puede ser cero!");
		}
		
		carritoActual.setProductos(carrito.getProductos());
		
		
		return carritoService.save(carritoActual);
		
		
	}
	
	 @PostMapping("/carrito")
	    public Carrito createCarrito(@RequestBody Carrito carrito) {
		 
		carrito.setTotal(this.calcularTotal(carrito));

	        return carritoService.save(carrito);

	    }
	 
	 @DeleteMapping("/carrito/{id}")
	    public void deleteCarrito(@PathVariable Long id) {
		 carritoService.remove(id);
	    }
	 @PostMapping("/carrito/calcularTotal")
	 public double calcularTotal(@RequestBody Carrito carrito) {
		 
		return carritoService.calcularTotal(carrito);
	 }
	 @PostMapping("/carrito/comprar")
	 public void comprar (@RequestBody Long id) {
		 carritoService.comprar(id);
	 }
}
