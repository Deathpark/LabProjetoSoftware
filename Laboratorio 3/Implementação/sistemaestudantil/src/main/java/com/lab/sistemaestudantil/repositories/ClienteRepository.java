package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Cliente;
import com.lab.sistemaestudantil.models.Empresa;

@Repository
public interface ClienteRepository extends JpaRepository<Empresa, Long> {

}