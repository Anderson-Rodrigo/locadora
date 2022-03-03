package com.api.locadora.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class FilmeDto implements Serializable {

    private UUID id;

    private String nome;

    private String genero;

    private String diretor;

    private Integer quantidade;

}
