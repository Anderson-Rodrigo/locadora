package com.api.locadora.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tab_filme")
public class Filme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fil_id")
    private UUID id;

    @Column(name = "fil_nome", length = 255)
    private String nome;

    @Column(name = "fil_genero", length = 255)
    private String genero;

    @Column(name = "fil_diretor", length = 255)
    private String diretor;

    @Column(name = "fil_quantidade", length = 255)
    private Integer quantidade;

}
