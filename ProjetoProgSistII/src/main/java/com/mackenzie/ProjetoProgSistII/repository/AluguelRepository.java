package com.mackenzie.ProjetoProgSistII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.ProjetoProgSistII.models.Aluguel;
import com.mackenzie.ProjetoProgSistII.models.Inquilino;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findAluguelByInquilino(Inquilino inquilino);
    List<Aluguel> findByInquilinoIdAndImovel_Cidade(Long id, String cidade);
    List<Aluguel> findByInquilinoIdAndImovel_Bairro(Long id, String bairro);
}