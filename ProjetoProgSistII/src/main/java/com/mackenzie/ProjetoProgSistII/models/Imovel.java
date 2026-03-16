package com.mackenzie.ProjetoProgSistII.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "imovel")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImovel;
    private String endereco;
    private String cidade;
    private String bairro;
    private Double area;
    private Integer quantidadeDeQuartos;
    private Double preco;
    private Boolean disponivel;

    public Imovel(){}

}
