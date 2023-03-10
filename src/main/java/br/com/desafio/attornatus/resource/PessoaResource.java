package br.com.desafio.attornatus.resource;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface PessoaResource<T> {

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<T> findById(@PathVariable(value = "id") long id);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<T>> findAll(@RequestParam(required = false) String name);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<T> create(@Valid @RequestBody T t);

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<T> update(@PathVariable(value = "id") long id,@Valid @RequestBody T t);
 
    
}

