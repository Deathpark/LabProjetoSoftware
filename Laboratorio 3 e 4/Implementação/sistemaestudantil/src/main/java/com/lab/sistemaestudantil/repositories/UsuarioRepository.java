package com.lab.sistemaestudantil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNome(String nome);
}
