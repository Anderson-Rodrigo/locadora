package com.api.locadora.controller;

import com.api.locadora.dto.FilmeDto;
import com.api.locadora.models.Filme;
import com.api.locadora.service.FilmeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody FilmeDto filmeDto){
        var filme = new Filme();
        BeanUtils.copyProperties(filmeDto, filme, "id");
        try{
            filmeService.insert(filme);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody FilmeDto filmeDto){
        Optional<Filme> filme = filmeService.getFilme(id);
        FilmeDto filmeAtualizado = new FilmeDto();

        if(filme.isPresent()){
            BeanUtils.copyProperties(filmeDto, filme.get(), "id");
            BeanUtils.copyProperties(filme.get(), filmeAtualizado);
            try{
                filmeService.update(filme.get());
            }catch (Exception e){
                return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Optional<Filme> filme = filmeService.getFilme(id);
        FilmeDto dto = new FilmeDto();

        if(filme.isPresent()){
            BeanUtils.copyProperties(filme.get(), dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable){
        Page<Filme> todosFilmes = filmeService.getAll(pageable);
        List<FilmeDto> dtos = new ArrayList<>();

        if(todosFilmes.getSize() > 0){
            todosFilmes.get().forEach(filme -> {
                FilmeDto filmeDto = new FilmeDto();
                BeanUtils.copyProperties(filme, filmeDto);
                dtos.add(filmeDto);
            });

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<Filme> filme = filmeService.getFilme(id);
        if(filme.isPresent()){
            filmeService.delete(filme.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
