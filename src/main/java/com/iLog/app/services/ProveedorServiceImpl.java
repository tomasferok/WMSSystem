package com.iLog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.IServices.IProveedorService;
import com.iLog.app.entities.Proveedor;
import com.iLog.app.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements IProveedorService {
	
	@Autowired
	ProveedorRepository provedorServicios;

	@Override
	public List<Proveedor> getAll() {
		// TODO Auto-generated method stub
		return provedorServicios.findAll();
	}

	@Override
	public Proveedor getById(Long id) {
		// TODO Auto-generated method stub
		return provedorServicios.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		provedorServicios.deleteById(id);
	}

	@Override
	public Proveedor save(Proveedor prov) {
		// TODO Auto-generated method stub
		return provedorServicios.save(prov);
	}

}
