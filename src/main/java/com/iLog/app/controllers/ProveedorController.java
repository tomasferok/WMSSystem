package com.iLog.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iLog.app.IServices.IProveedorService;
import com.iLog.app.IServices.IRecepcionService;
import com.iLog.app.entities.Proveedor;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;

@RestController
@RequestMapping("/api")
public class ProveedorController {

	@Autowired
	IProveedorService provService;
	
	@GetMapping("/provs")
	public List<Proveedor> getAll(){
		
		return provService.getAll();
		}
	@GetMapping("/provs/{id}")
	public Proveedor getById(@PathVariable Long id) {
		return provService.getById(id);
		
	}
	
	@PostMapping("/provs")
	public Proveedor createRecep(@RequestBody Proveedor recep) {
		return provService.save(recep);
	}
	
	
	@DeleteMapping("/provs/{id}")
	public void delete(@PathVariable Long id) {
		provService.remove(id);
	}
	
}
