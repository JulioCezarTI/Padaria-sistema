package com.javajulio.web.cadastrousuario.infrastructure.repository;

import com.javajulio.web.cadastrousuario.infrastructure.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);


    void deleteByEmail(String email);
}
