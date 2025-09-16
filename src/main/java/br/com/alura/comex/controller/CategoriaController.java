package br.com.alura.comex.controller;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.dto.CadastroCategoriaRequest;
import br.com.alura.comex.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    CategoriaDao categoriaDao = new CategoriaDao();

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public Object listarTodos() {

        //return categoriaDao.listaTodos();
        return categoriaDao.listaTodos();

    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCategoria(@RequestBody @Valid CadastroCategoriaRequest dados) {

        System.out.println(dados);

        if(categoriaRepository.existsByNome(dados.nome())){
            return new ResponseEntity<>("Categoria similar ou igual já cadastrada", HttpStatus.CONFLICT);
        }

        Categoria nova = new Categoria(dados);

        try {
            Categoria criada = categoriaRepository.save(nova);
            return new ResponseEntity<>(criada, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }


    }

}
