package com.api.locadora.controller;

import com.api.locadora.dto.FilmeDto;
import com.api.locadora.dto.UsuarioDto;
import com.api.locadora.infrastructure.exception.CadastroException;
import com.api.locadora.models.Usuario;
import com.api.locadora.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UsuarioDto usuarioDto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario, "id");
        try{
            usuarioService.insert(usuario);
        }catch (CadastroException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UsuarioDto usuarioDto){
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        UsuarioDto usarioAtualizado = new UsuarioDto();

        if(usuario.isPresent()){
            BeanUtils.copyProperties(usuarioDto, usuario.get(), "id");

            BeanUtils.copyProperties(usuario.get(), usarioAtualizado);
            try{
                usuarioService.update(usuario.get());
            }catch (CadastroException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(usarioAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        FilmeDto dto = new FilmeDto();

        if(usuario.isPresent()){
            BeanUtils.copyProperties(usuario.get(), dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable){
        Page<Usuario> todosUsuarios = usuarioService.getAll(pageable);
        List<UsuarioDto> dtos = new ArrayList<>();

        if(todosUsuarios.getSize() > 0){
            todosUsuarios.get().forEach(usuario -> {
                UsuarioDto usuarioDto = new UsuarioDto();
                BeanUtils.copyProperties(usuario, usuarioDto);
                dtos.add(usuarioDto);
            });

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        if(usuario.isPresent()){
            usuarioService.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
