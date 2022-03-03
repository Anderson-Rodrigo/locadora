package com.api.locadora.repository;

import com.api.locadora.models.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, UUID> {
}
