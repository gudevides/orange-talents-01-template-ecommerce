package com.br.zup.mercadolivre.produto.adicionaopiniao;

import com.br.zup.mercadolivre.produto.cadastraprodutoeimagem.Produto;
import com.br.zup.mercadolivre.usuario.UsuarioRepository;
import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre/produto")
public class OpiniaoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/produtos")
    @Transactional
    public String opinar(@PathVariable Long id, @RequestBody @Valid CadastraOpiniaoRequest request){
        Produto produto = entityManager.find(Produto.class, id);
        Usuario consumidor = usuarioRepository.findByEmail("dev@email.com").get();
        Opiniao opiniao = request.toModel(produto, consumidor);
        entityManager.persist(opiniao);

        return opiniao.toString();
    }
}
