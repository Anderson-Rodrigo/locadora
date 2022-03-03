package com.api.locadora.service;

import com.api.locadora.infrastructure.exception.CadastroException;
import com.api.locadora.models.Filme;
import com.api.locadora.models.Locacao;
import com.api.locadora.repository.LocacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final FilmeService filmeService;

    public LocacaoService(LocacaoRepository locacaoRepository, FilmeService filmeService) {
        this.locacaoRepository = locacaoRepository;
        this.filmeService = filmeService;
    }

    @Transactional
    public Locacao insert(Locacao locacao) throws CadastroException {
        validacoes(locacao);
        Locacao loc = locacaoRepository.save(locacao);
        return converter(loc);
    }

    private void validacoes(Locacao locacao) throws CadastroException {
        if(locacao.getFilmes().size() <= 0){
            throw new CadastroException("Deve ser alugado pelo menos um filme ");
        }

        if(locacao.getFilmes() != null){
            List<Filme> filmes = locacao.getFilmes();

            filmes.forEach(filme -> {
                Optional<Filme> filmeBuscado = filmeService.getFilme(filme.getId());

                if(!filmeBuscado.isPresent()){
                    try {
                        throw new CadastroException("O filme nao existe");
                    }catch (CadastroException e){
                        e.getMessage();
                    }
                }
            });

            if(filmes.size() > 5){
                throw new CadastroException("O limite de filmes para locação é de 5 você selecionou " + filmes.size());
            }
        }
    }

    private Locacao converter(Locacao locacao){
        List<Filme> filmes = new ArrayList<>();

        locacao.getFilmes().forEach(filme -> {
            Optional<Filme> filmeBuscado = filmeService.getFilme(filme.getId());
            if(filmeBuscado.isPresent()){
                filmes.add(filmeBuscado.get());
            }
            locacao.setFilmes(filmes);
        });
        return locacao;
    }
}
