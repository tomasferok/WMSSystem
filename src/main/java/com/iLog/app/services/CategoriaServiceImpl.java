package com.iLog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iLog.app.IServices.ICategoriaService;
import com.iLog.app.entities.Categoria;
import com.iLog.app.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	CategoriaRepository catServ;
	
	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return (List<Categoria>) catServ.findAll();
	}

	@Override
	public Categoria getById(Long id) {
		// TODO Auto-generated method stub
		return catServ.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		catServ.deleteById(id);
		
	}

	@Override
	public Categoria save(Categoria categoria) {
		// TODO Auto-generated method stub
		return catServ.save(categoria);
	}

}
