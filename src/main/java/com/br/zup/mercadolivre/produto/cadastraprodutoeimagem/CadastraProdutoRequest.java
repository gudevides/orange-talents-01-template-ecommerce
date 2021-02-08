package com.br.zup.mercadolivre.produto.cadastraprodutoeimagem;

import com.br.zup.mercadolivre.categoria.cadastracategoria.Categoria;
import com.br.zup.mercadolivre.config.validacao.ExistsId;
import com.br.zup.mercadolivre.config.validacao.UniqueValueInList;
import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CadastraProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull @PositiveOrZero
    private int quantidade;
    @Size(min = 3) @NotNull @UniqueValueInList(message = "O Produto não pode ter características iguais!")
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
    @NotNull @Size(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "O Id de Categoria informado não está cadastrado no sistema!")
    private Long idCategoria;

    public CadastraProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                                  @NotNull @PositiveOrZero int quantidade, @Size(min = 3) List<CaracteristicaRequest> caracteristicas,
                                  @NotNull @Size(max = 1000) String descricao, @NotNull Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public void setCaracteristicas(List<CaracteristicaRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "CadastraProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

    public Produto toModel(EntityManager entityManager, Usuario usuarioLogado) {
        Categoria categoria = entityManager.find(Categoria.class, this.idCategoria);
        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao, categoria, usuarioLogado);
    }
}
