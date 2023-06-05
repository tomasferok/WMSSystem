package com.iLog.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iLog.app.entities.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
