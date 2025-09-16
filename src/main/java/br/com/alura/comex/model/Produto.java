package br.com.alura.comex.model;

import br.com.alura.comex.model.dto.CadastroProdutoRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    @Getter
    @Setter
    @ManyToMany
    private Set<Categoria> categorias;

    @Column(nullable = false)
    @Getter
    @Setter
    private BigInteger qtd_estoque;

    public Produto(CadastroProdutoRequest dados, Set<Categoria> categorias) {

        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
        this.categorias = categorias;
        qtd_estoque = dados.qtd_estoque();

    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categorias=" + categorias +
                '}';
    }
}