package com.api.locadora.repository;

import com.api.locadora.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID> {
}
