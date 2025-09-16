package br.com.alura.comex.service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorErrosValidacao {

    // Este método continua tratando a nossa exceção de regra de negócio
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // NOVO MÉTODO: Trata os erros de validação do Bean Validation (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErro400(MethodArgumentNotValidException ex) {
        // Cria um mapa para armazenar os erros no formato "campo" -> "mensagem"
        Map<String, String> erros = new HashMap<>();

        // A exceção 'ex' contém uma lista de todos os erros de validação.
        // Usamos ex.getFieldErrors() para pegar cada erro de campo.
        for (FieldError erro : ex.getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        // Retorna o status HTTP 400 (Bad Request) com o mapa de erros no corpo
        return ResponseEntity.badRequest().body(erros);
    }

}
