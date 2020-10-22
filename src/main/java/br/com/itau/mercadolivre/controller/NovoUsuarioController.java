package br.com.itau.mercadolivre.controller;

import br.com.itau.mercadolivre.model.Usuario;
import br.com.itau.mercadolivre.model.UsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/v1/usuario")
    @Transactional
    public ResponseEntity<?> cadastraNovoUsuario(@RequestBody @Valid UsuarioRequest novoUsuarioRequest, UriComponentsBuilder uriComponents) {
        Usuario novoUsuario = novoUsuarioRequest.toModel();
        manager.persist(novoUsuario);
        URI uri = uriComponents.path("/v1/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
