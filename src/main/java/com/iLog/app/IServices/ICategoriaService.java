package com.iLog.app.IServices;

import java.util.List;


import com.iLog.app.entities.Categoria;

public interface ICategoriaService {
	List<Categoria>getAll();
	Categoria getById(Long id);
	void remove(Long id);
	Categoria save( Categoria categoria);
}
