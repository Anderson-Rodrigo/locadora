package com.api.locadora.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tab_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usu_id")
    private UUID id;

    @Column(name = "usu_nome", length = 255)
    @NotNull
    private String nome;

    @Column(name = "usu_sexo", length = 10)
    private String sexo;

    @Column(name = "usu_cpf", length = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull
    @Column(name = "usu_idade")
    private Integer idade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_data_nascimento")
    private Date dataNascimento;
}
