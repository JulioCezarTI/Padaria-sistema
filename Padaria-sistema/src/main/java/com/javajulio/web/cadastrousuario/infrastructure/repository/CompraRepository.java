package com.javajulio.web.cadastrousuario.infrastructure.repository;

import com.javajulio.web.cadastrousuario.infrastructure.entitys.Compradeproduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compradeproduto, Long> {
}
