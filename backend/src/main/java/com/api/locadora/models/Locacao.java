package com.api.locadora.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tab_locacao")
public class Locacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loc_id")
    private UUID id;

    @ManyToMany
    @JoinTable(name = "locacao_filme",
            joinColumns = {@JoinColumn(name = "lof_loc_id", referencedColumnName = "loc_id")},
            inverseJoinColumns = {@JoinColumn(name = "lof_fil_id", referencedColumnName = "fil_id")}
    )
    private List<Filme> filmes = new ArrayList<>();

    @Column(name = "loc_usuario_id")
    private UUID usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loc_data_devolucao")
    private Date dataDevolucao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loc_data_locacao")
    private Date dataLocacao;

}
