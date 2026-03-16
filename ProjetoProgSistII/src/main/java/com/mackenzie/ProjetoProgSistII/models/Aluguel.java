package com.mackenzie.ProjetoProgSistII.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name= "Aluguel")
@Entity
@Getter
@Setter
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluguel;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    private Imovel imovel;

    @ManyToOne
    private Inquilino inquilino;


    public Aluguel() {}

    public Aluguel(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}