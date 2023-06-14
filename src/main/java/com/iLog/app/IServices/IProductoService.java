package com.iLog.app.IServices;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;

public interface IProductoService {

	List<Producto> getAll();
	Producto getById(Long id);
	void remove(Long id);
	Producto save( Producto prod);
	Producto findByNameProd (String nombreProd);
	List<Producto>findByIdAlma(Long idAlma);
	Producto findByNameProdAndState(String nombreProd, Estado e);
	ResponseEntity<byte[]> generarEtiquetas(List<Producto> prods);
}
