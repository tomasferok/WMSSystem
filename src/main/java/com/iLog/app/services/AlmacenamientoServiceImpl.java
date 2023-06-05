package com.iLog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.IServices.IAlmacenamientoService;
import com.iLog.app.entities.Almacenamiento;
import com.iLog.app.repositories.AlmacenamientoRepository;

@Service
public class AlmacenamientoServiceImpl implements IAlmacenamientoService {
	
	@Autowired
	AlmacenamientoRepository almaSrv;

	@Override
	public List<Almacenamiento> getAll() {
		// TODO Auto-generated method stub
		return (List<Almacenamiento>) almaSrv.findAll();
	}

	@Override
	public Almacenamiento getById(Long id) {
		// TODO Auto-generated method stub
		return almaSrv.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		almaSrv.deleteById(id);
		
	}

	@Override
	public Almacenamiento save(Almacenamiento alma) {
		// TODO Auto-generated method stub
		return almaSrv.save(alma);
	}

}
