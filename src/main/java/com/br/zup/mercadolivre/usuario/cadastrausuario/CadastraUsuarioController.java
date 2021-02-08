package com.br.zup.mercadolivre.usuario.cadastrausuario;

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
@RequestMapping("/mercadolivre/usuario")
public class CadastraUsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@RequestBody @Valid CadastraUsuarioRequest request){
        Usuario usuario = request.toModel();
        entityManager.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
