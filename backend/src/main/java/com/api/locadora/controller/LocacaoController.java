package com.api.locadora.controller;

import com.api.locadora.dto.LocacaoDto;
import com.api.locadora.infrastructure.exception.CadastroException;
import com.api.locadora.models.Locacao;
import com.api.locadora.service.LocacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody LocacaoDto locacaoDto){
        var locacao = new Locacao();
        BeanUtils.copyProperties(locacaoDto, locacao);
        try{
            locacaoService.insert(locacao);
        }catch (CadastroException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(locacao, HttpStatus.OK);
    }

}
