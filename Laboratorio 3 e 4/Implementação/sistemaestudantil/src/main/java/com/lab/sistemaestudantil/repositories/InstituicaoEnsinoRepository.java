package com.lab.sistemaestudantil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.sistemaestudantil.models.InstituicaoEnsino;

@Repository
public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, Long> {

}
