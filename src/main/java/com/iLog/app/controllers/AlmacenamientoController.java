package com.iLog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.services.IAlmacenamientoService;

@RestController
@RequestMapping("/api")
public class AlmacenamientoController {

	@Autowired
	IAlmacenamientoService almaSrv;
	
	@GetMapping("/alma")
	public List<Almacenamiento> getAll(){
		
		return almaSrv.getAll();
		}
	@GetMapping("/alma/{id}")
	public Almacenamiento getById(@PathVariable Long id) {
		return almaSrv.getById(id);
		
	}
	
	@PostMapping("/alma")
	public Almacenamiento createAlma(@RequestBody Almacenamiento alma) {
		return almaSrv.save(alma);
	}
	
	@DeleteMapping("/alma/{id}")
	public void delete(@PathVariable Long id) {
		almaSrv.remove(id);
	}
}
