package com.api.locadora.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class UsuarioDto implements Serializable {

    private UUID id;

    @NotNull
    private String nome;

    private String sexo;

    @NotNull
    @CPF(message = "CPF inválido")
    private String cpf;

    private Integer idade;

    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date dataNascimento;

}
