package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    
}
