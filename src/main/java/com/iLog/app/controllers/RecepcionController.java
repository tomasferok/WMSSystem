package com.iLog.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iLog.app.IServices.IRecepcionService;

import com.iLog.app.entities.Recepcion;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;

@RestController
@RequestMapping("/api")
public class RecepcionController {

	@Autowired
	IRecepcionService recepServ;
	
	@GetMapping("/recep")
	public List<Recepcion> getAll(){
		
		return recepServ.getAll();
		}
	@GetMapping("/recep/{id}")
	public Recepcion getById(@PathVariable Long id) {
		return recepServ.getById(id);
		
	}
	
	@PostMapping("/recep")
	public Recepcion createRecep(@RequestBody Recepcion recep) {
		return recepServ.save(recep);
	}
	@PostMapping("/recep/controlar")
	public ResponseEntity<?> controlarRecep(@RequestBody ControlarRecepcionRequest recepReq) {
		Map<String, Object>response = recepServ.controlarRecepcion(recepReq);
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/recep/{id}")
	public void delete(@PathVariable Long id) {
		recepServ.remove(id);
	}
	@PostMapping("/recep/confirmation")
	public void confirmControlRecep(@RequestBody ControlarRecepcionRequest confirm) {
		recepServ.confirmRecep(confirm);
	}
}
