package br.com.desafio.attornatus.controller;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.resource.Resource;
import br.com.desafio.attornatus.service.EnderecoService;
import br.com.desafio.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/api/pessoas")
public class PessoaController implements Resource<Pessoa>  {
    
    
    @Autowired
    PessoaService pessoaService;
    
    @Autowired
    EnderecoService enderecoService;

    @Override
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findById(long id) {
        return new ResponseEntity<>(pessoaService.findById(id), OK);
    }
    
    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> findAll(String name) {
        return new ResponseEntity<>(pessoaService.findAll(name), OK);
    }

    @Override
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> create(Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.create(pessoa), CREATED);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> update(long id, Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.update(id, pessoa), OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    // Mapeamento do relacionamento entre pessoa e endereco
    
    @GetMapping(value = "/{pessoaId}/enderecos", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Endereco>> findEnderecosByPessoaId(@PathVariable(value = "pessoaId") long id) {
        List<Endereco> enderecos = enderecoService.findById(id);
        if(enderecos.isEmpty()){
            return new ResponseEntity<>(NO_CONTENT);
        }
        return new ResponseEntity<>(enderecos, OK);
    }

    @PostMapping(value = "/{pessoaId}/enderecos", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> createEndereco(
            @PathVariable(value = "pessoaId") Long pessoaId,
            @RequestBody Endereco enderecoRequest) {
        Endereco endereco = enderecoService.create(pessoaId, enderecoRequest);
        return new ResponseEntity<>(endereco, CREATED);
    }
}

