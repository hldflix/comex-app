package br.com.alura.comex.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroCategoriaRequest(

        @NotBlank
        @Size(min = 2)
        String nome,

        @NotNull
        Boolean ativo
) {
    // na hora de criar o campo ativo por padrao vem TRUE
    public CadastroCategoriaRequest {
        if (ativo == null) {
            ativo = true;
        }
    }
}
