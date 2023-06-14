package com.iLog.app.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.IServices.IRecepcionService;
import com.iLog.app.entities.Recepcion;
import com.iLog.app.helpers.RecepcionHelper;
import com.iLog.app.helpers.request.ControlarRecepcionRequest;
import com.iLog.app.repositories.ProveedorRepository;
import com.iLog.app.repositories.RecepcionRepository;

@Service
public class RecepcionServiceImpl implements IRecepcionService{

	@Autowired
	RecepcionRepository recepcionServ;
	@Autowired
	RecepcionHelper recepHelper;
	@Autowired
	ProveedorRepository provServ;
	@Override
	public List<Recepcion> getAll() {
		// TODO Auto-generated method stub
		return (List<Recepcion>) recepcionServ.findAll();
	}

	@Override
	public Recepcion getById(Long id) {
		// TODO Auto-generated method stub
		return recepcionServ.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		recepcionServ.deleteById(id);
		
	}

	@Override
	public Recepcion save(Recepcion recep) {
		// TODO Auto-generated method stub
		provServ.flush();
		return recepcionServ.save(recep);
	}

	@Override
	public HashMap<String, Object> controlarRecepcion(ControlarRecepcionRequest recepReq) {
		
		
		return recepHelper.controlarRecepcion(recepReq);
		
	}

	@Override
	public void confirmRecep(ControlarRecepcionRequest recepReq) {
	
		recepHelper.confirmRecep(recepReq);
		
	}

}
