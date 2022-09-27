package com.lab.aluguelveiculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.aluguelveiculos.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}