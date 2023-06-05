package com.iLog.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iLog.app.entities.Recepcion;

@Repository
public interface RecepcionRepository extends CrudRepository<Recepcion, Long>{

}
