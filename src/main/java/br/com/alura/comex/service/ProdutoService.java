package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.dto.CadastroProdutoRequest;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.service.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Object listarTodosProdutos() {

        return produtoRepository.findAll();

    }


    public Optional<Produto> cadastrarProduto(CadastroProdutoRequest dados) {

        if(produtoRepository.existsByNome(dados.nome())){
            throw new ValidacaoException("Já existe um produto cadastrado com esse nome: " + dados.nome());
        }

        verificarCategorias(dados.id_categorias());

        Set<Categoria> categorias = new HashSet<>(categoriaRepository.findAllById(dados.id_categorias()));

        Produto novoProduto = new Produto(dados, categorias);

        Produto produtoCriado = produtoRepository.save(novoProduto);

        return Optional.of(produtoCriado);

    }

    private void verificarCategorias(Set<Long> idsCategorias) {

        long countCategoriasExistentes = categoriaRepository.countByIdIn(idsCategorias);

        // Se a contagem for diferente do tamanho da lista, uma ou mais categorias não existem.
        if (countCategoriasExistentes != idsCategorias.size()) {
            throw new ValidacaoException("Uma ou mais categorias fornecidas não existem.");
        }

    }



}
