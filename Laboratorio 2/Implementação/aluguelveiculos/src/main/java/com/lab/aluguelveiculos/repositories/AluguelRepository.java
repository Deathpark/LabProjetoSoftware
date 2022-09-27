package com.lab.aluguelveiculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.aluguelveiculos.models.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    
}
