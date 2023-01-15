package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@ComponentScan("br.com.desafio.attornatus")
public class PessoaService {

    private final Logger logger = Logger.getLogger(PessoaService.class.getName());
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    public List<Pessoa> findAll(String nome) {
        logger.info("Encontrando todas as pessoas!");
     
        if(nome == null)
            return pessoaRepository.findAll();
        else
            return pessoaRepository.findByNomeContaining(nome);
    }
    
    public Pessoa findById(long id) {
        
        logger.info("Encontrando uma pessoa!");

        return pessoaRepository.findById(id).orElseThrow(() -> 
                new ResourceNotFoundException("Não encontrou pessoa com id = " + id));
    }
    
    public Pessoa create(Pessoa pessoa) {
        
        logger.info("Crindo uma pessoa!");
        
        return pessoaRepository.save(pessoa);
    }
    
    public Pessoa update(long id, Pessoa pessoa) {
        
        logger.info("Atualiza uma Pessoa");
        
        var _pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Não encontrou pessoa com id = " + id));
        
        _pessoa.setNome(pessoa.getNome());
        _pessoa.setDataNascimento(pessoa.getDataNascimento());
        
        return pessoaRepository.save(_pessoa);
    }
    
}
