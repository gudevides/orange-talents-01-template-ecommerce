package com.br.zup.mercadolivre.produto.cadastraprodutoeimagem;

import com.br.zup.mercadolivre.usuario.UsuarioRepository;
import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/mercadolivre/produto")
public class CadastraProdutoEImagemController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid CadastraProdutoRequest request){

        Usuario usuarioLogado = usuarioRepository.findByEmail("dev@email.com").get();
        Produto produto = request.toModel(entityManager, usuarioLogado);
        entityManager.persist(produto);

        return produto.toString();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String adicionaImagem (@PathVariable Long id, @Valid CadastraImagensRequest request){

        Usuario dono = usuarioRepository.findByEmail("dev@email.com").get();
        Produto produto = entityManager.find(Produto.class, id);

        if (!produto.pertenceAoUsuario(dono)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        entityManager.merge(produto);

        return produto.toString();
    }
}
