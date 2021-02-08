package com.br.zup.mercadolivre.produto.adicionaopiniao;

import com.br.zup.mercadolivre.produto.cadastraprodutoeimagem.Produto;
import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class CadastraOpiniaoRequest {

    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    public CadastraOpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produto produto,@NotNull @Valid Usuario consumidor) {
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, consumidor);
    }
}
