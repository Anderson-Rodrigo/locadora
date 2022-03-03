package com.api.locadora.service;

import com.api.locadora.infrastructure.exception.CadastroException;
import com.api.locadora.models.Usuario;
import com.api.locadora.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario insert(Usuario usuario) throws CadastroException {
        validacoes(usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) throws CadastroException {
        validacoes(usuario);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuario(UUID id){
        return Optional.of(usuarioRepository.getById(id));
    }

    public Page<Usuario> getAll(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    private void validacoes(Usuario usuario) throws CadastroException {

        if(usuario.getCpf() == null){
            throw new CadastroException("Cpf não pode ser null");
        }
        if(usuario.getIdade() < 18) {
            throw new CadastroException("A idade minima do usuário é de 18 anos");
        }

    }

}
