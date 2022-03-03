package com.api.locadora.dto;

import com.api.locadora.models.Filme;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class LocacaoDto {

    private UUID id;

    private List<Filme> filmes = new ArrayList<>();

    private UUID usuario;

    private Date dataDevolucao;

    private Date dataLocacao;

}
