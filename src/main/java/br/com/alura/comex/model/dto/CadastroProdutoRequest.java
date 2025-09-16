package br.com.alura.comex.model.dto;

import br.com.alura.comex.model.Categoria;
import jakarta.validation.constraints.*;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public record CadastroProdutoRequest (

        @NotBlank
        @Size(min = 2)
        String nome,

        @NotNull
        @Positive
        BigDecimal preco,

        String descricao,

        @NotNull
        @Min(value = 0)
        BigInteger qtd_estoque,

        @NotEmpty
        Set<Long> id_categorias) {
}
