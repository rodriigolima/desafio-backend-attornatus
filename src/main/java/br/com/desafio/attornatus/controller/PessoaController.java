package br.com.desafio.attornatus.controller;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.service.EnderecoService;
import br.com.desafio.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    
    @Autowired
    PessoaService pessoaService;
    
    @Autowired
    EnderecoService enderecoService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> findAllPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        if(pessoas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable(value = "id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    } 
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa _pessoa = pessoaService.create(pessoa);
        return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
    }
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa) {
        Pessoa _pessoa = pessoaService.update(pessoa);
        return new ResponseEntity<>(_pessoa, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePessoa(@PathVariable(value = "id")Long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/{pessoaId}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Endereco>> findEnderecoById(@PathVariable(value = "pessoaId") Long id) { 
        List<Endereco> enderecos = enderecoService.findById(id);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
    
    @PostMapping(value = "/{personId}/address", 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> createAddress(
            @PathVariable(value = "personId") Long id,
            @RequestBody Endereco enderecoReq) { 
        Endereco endereco = enderecoService.create(id, enderecoReq);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED); 
    }
}
