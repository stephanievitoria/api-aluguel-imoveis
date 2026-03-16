package com.mackenzie.ProjetoProgSistII.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa extends Imovel{
    private Boolean quintal;
    private Boolean garagem;

    public Casa() {
    }


}
