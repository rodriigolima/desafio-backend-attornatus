package br.com.desafio.attornatus.controller;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.PessoaRepository;
import br.com.desafio.attornatus.service.EnderecoService;
import br.com.desafio.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PessoaController {
    
    @Autowired
    PessoaService pessoaService;
    
    @Autowired
    EnderecoService enderecoService;
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    @GetMapping(value = "/pessoas",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> findAllPessoas(@RequestParam(required = false) String nome) {
        List<Pessoa> pessoas = pessoaService.findAll(nome);
        if(pessoas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pessoas, HttpStatus.OK);

    }
    
    @GetMapping(value = "/pessoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable(value = "id") long id) {
        Pessoa pessoa = pessoaService.findById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    } 
    
    @PostMapping(value = "/pessoas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa _pessoa = pessoaService.create(pessoa);
        return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/pessoas/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable(value = "id") long id, @RequestBody Pessoa pessoa) {
        Pessoa _pessoa = pessoaService.update(id, pessoa);
        return new ResponseEntity<>(_pessoa, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/pessoas/{id}")
    public ResponseEntity<?> deletePessoa(@PathVariable(value = "id") long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/pessoas/{pessoaId}/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Endereco>> findEnderecoById(@PathVariable(value = "pessoaId") long id) {
        List<Endereco> enderecos = enderecoService.findById(id);
        if(enderecos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
    
    @PostMapping(value = "/pessoas/{pessoaId}/enderecos", 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> createEndereco(
            @PathVariable(value = "pessoaId") Long pessoaId,
            @RequestBody Endereco enderecoRequest) { 
        Endereco endereco = enderecoService.create(pessoaId, enderecoRequest);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED); 
    }
}
