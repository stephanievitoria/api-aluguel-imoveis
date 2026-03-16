package com.mackenzie.ProjetoProgSistII.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.ProjetoProgSistII.models.Inquilino;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Long> {
    Optional<Inquilino> findByIdAndSenha(Long id, String senha);
}
