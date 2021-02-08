package com.br.zup.mercadolivre.categoria.cadastracategoria;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre/categoria")
public class CadastraCategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@RequestBody @Valid CadastraCategoriaRequest request) {
        Categoria categoria = request.toModel(entityManager);
        entityManager.persist(categoria);

        return ResponseEntity.ok().build();
    }
}
