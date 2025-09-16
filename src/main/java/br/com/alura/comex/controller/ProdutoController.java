package br.com.alura.comex.controller;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.dto.CadastroCategoriaRequest;
import br.com.alura.comex.model.dto.CadastroProdutoRequest;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.service.ProdutoService;
import br.com.alura.comex.service.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public Object listarTodos() {

        //return categoriaDao.listaTodos();
        return produtoService.listarTodosProdutos();

    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProduto(@RequestBody @Valid CadastroProdutoRequest dados) {

        System.out.println(dados);

        Optional<Produto> produtoAdicionado;

        produtoAdicionado = produtoService.cadastrarProduto(dados);

        return produtoAdicionado.<ResponseEntity<Object>>map(
                produto -> ResponseEntity.status(HttpStatus.CREATED).body(produto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());

    }

}
