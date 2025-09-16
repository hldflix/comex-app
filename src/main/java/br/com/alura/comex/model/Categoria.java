package br.com.alura.comex.model;


import br.com.alura.comex.model.dto.CadastroCategoriaRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private Boolean ativo;

    public Categoria(CadastroCategoriaRequest dados) {

        this.nome = dados.nome();
        this.ativo = dados.ativo();

    }
}