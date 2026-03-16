package com.mackenzie.ProjetoProgSistII.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Apartamento extends Imovel {
    private Integer andarAPT;
    private Double condominioAPT;

    public Apartamento() {
    }
}
