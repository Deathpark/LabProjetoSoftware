package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Vantagem;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
    
}
