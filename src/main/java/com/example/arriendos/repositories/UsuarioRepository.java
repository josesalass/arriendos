package com.example.arriendos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arriendos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
