package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    
}
