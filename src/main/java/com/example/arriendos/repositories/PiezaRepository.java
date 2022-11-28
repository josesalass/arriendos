package com.example.arriendos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arriendos.model.Pieza;

@Repository
public interface PiezaRepository extends JpaRepository<Pieza,Integer > {

}
