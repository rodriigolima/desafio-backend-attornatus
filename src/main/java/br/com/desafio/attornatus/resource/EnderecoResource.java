package br.com.desafio.attornatus.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface EnderecoResource<T> {

    @GetMapping(value = "/{pessoaId}/enderecos", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<T>> findEnderecosByPessoaId(@PathVariable(value = "pessoaId") long id);

    @PostMapping(value = "/{pessoaId}/enderecos", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<T> createEnderecoByPessoaId(@PathVariable(value = "pessoaId") Long id, @RequestBody T Request);
}
