package com.javajulio.web.cadastrousuario.infrastructure.repository;


import com.javajulio.web.cadastrousuario.infrastructure.entitys.Padaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PadariaRepository extends JpaRepository<Padaria, Long> {
    Optional<Padaria> findByNome(String nome);
}