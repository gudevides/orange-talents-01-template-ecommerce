package com.br.zup.mercadolivre.produto.cadastraprodutoeimagem;

import com.br.zup.mercadolivre.categoria.cadastracategoria.Categoria;
import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valor;

    private int quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas;

    private String descricao;

    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    public Produto(String nome, BigDecimal valor, int quantidade,
                   List<CaracteristicaRequest> caracteristicas,
                   String descricao, Categoria categoria,
                   Usuario usuarioLogado) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuarioLogado;
        this.caracteristicas = caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toList());

    }

    @Deprecated
    public Produto(){}

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", imagens=" + imagens +
                '}';
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario dono) {
        return this.usuario.equals(dono);
    }
}
