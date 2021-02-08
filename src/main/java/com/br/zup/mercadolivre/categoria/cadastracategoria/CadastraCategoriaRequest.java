package com.br.zup.mercadolivre.categoria.cadastracategoria;

import com.br.zup.mercadolivre.config.validacao.ExistsId;
import com.br.zup.mercadolivre.config.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CadastraCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada no sistema!")
    private String nome;
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "Não existe uma categoria com o Id informado!")
    private Long idCategoriaMae;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CadastraCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(EntityManager entityManager) {
        Categoria categoria = new Categoria(this.nome);
        if (this.idCategoriaMae != null){
            Categoria categoriaMae = entityManager.find(Categoria.class, this.idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
