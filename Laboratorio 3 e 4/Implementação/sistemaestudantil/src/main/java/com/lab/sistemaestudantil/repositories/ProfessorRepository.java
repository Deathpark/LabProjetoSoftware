package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
