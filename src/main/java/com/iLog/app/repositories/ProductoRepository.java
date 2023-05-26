package com.iLog.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iLog.app.entities.Estado;
import com.iLog.app.entities.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{
	Producto findByNameProd(String nameProd);
	Producto findByNameProdAndState(String nameProd, Estado e);
}
