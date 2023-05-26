package com.iLog.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iLog.app.entities.Carrito;

@Repository
public interface CarritoRepository extends CrudRepository<Carrito, Long>{

}
