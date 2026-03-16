package com.mackenzie.ProjetoProgSistII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.ProjetoProgSistII.models.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    List<Imovel> findByCidade(String cidade);
    List<Imovel> findByBairro(String bairro);
    List<Imovel> findByDisponivel(boolean disponivel);
}