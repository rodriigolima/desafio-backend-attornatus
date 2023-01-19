package br.com.desafio.attornatus.controller;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.resource.EnderecoResource;
import br.com.desafio.attornatus.resource.PessoaResource;
import br.com.desafio.attornatus.service.EnderecoService;
import br.com.desafio.attornatus.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController implements PessoaResource<Pessoa>, EnderecoResource<Endereco> {
    
   
    private PessoaService pessoaService;
    
    private EnderecoService enderecoService;

    @Override
    public ResponseEntity<Pessoa> findById(long id) {
        
        return new ResponseEntity<>(pessoaService.findById(id), OK);
    }
    
    @Override
    public ResponseEntity<List<Pessoa>> findAll(String name) {
        if(pessoaService.findAll(name).isEmpty()) return new ResponseEntity<>(NO_CONTENT);
        return new ResponseEntity<>(pessoaService.findAll(name), OK);
    }

    @Override
    public ResponseEntity<Pessoa> create(Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.create(pessoa), CREATED);
    }

    @Override
    public ResponseEntity<Pessoa> update(long id, Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.update(id, pessoa), OK);
    }
    
    // Mapeamento do relacionamento entre pessoa e endereco
    
    @Override
    public ResponseEntity<List<Endereco>> findEnderecosByPessoaId(long id) {;
        if(enderecoService.findById(id).isEmpty()) return new ResponseEntity<>(NO_CONTENT);
        return new ResponseEntity<>(enderecoService.findById(id), OK);
    }

    @Override
    public ResponseEntity<Endereco> createEnderecoByPessoaId(Long pessoaId, Endereco enderecoRequest) {
        return new ResponseEntity<>(enderecoService.create(pessoaId, enderecoRequest), CREATED);
    }
}

