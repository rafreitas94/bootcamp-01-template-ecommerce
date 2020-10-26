package br.com.itau.mercadolivre.novacategoria.controller;

import br.com.itau.mercadolivre.novacategoria.model.Categoria;
import br.com.itau.mercadolivre.novacategoria.model.CategoriaRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/v1/categoria")
    @Transactional
    public Categoria CadastraCategoria(@RequestBody @Valid CategoriaRequest novaCategoriaRequest) {
        Categoria categoria = novaCategoriaRequest.toModel(manager);
        manager.persist(categoria);
        return categoria;
    }

    @GetMapping("/teste/{id}")
    public Categoria ExibeCategorias(@PathVariable("id") Long id) {
        return manager.find(Categoria.class, id);
    }
}
