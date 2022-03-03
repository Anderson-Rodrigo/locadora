package com.api.locadora.service;

import com.api.locadora.models.Filme;
import com.api.locadora.repository.FilmeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Filme insert(Filme filme){
        return filmeRepository.save(filme);
    }

    @Transactional
    public Filme update(Filme filme){
        return filmeRepository.save(filme);
    }

    public Optional<Filme> getFilme(UUID id){
        return Optional.of(filmeRepository.getById(id));
    }

    public Page<Filme> getAll(Pageable pageable){
        return filmeRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Filme filme){
        filmeRepository.delete(filme);
    }
}
