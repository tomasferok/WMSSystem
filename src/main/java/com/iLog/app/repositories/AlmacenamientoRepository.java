package com.iLog.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iLog.app.entities.Almacenamiento;

@Repository
public interface AlmacenamientoRepository extends CrudRepository<Almacenamiento, Long>{

}
