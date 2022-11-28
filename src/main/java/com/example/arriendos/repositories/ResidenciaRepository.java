package com.example.arriendos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arriendos.model.Residencia;

@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia, Integer > {

}
