package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PessoaService {

    private final Logger logger = Logger.getLogger(PessoaService.class.getName());
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    public List<Pessoa> findAll() {
        logger.info("Encontrando todas as pessoas!");
        
        return pessoaRepository.findAll();
    }
    
    public Pessoa findById(long id) {
        
        logger.info("Encontrando uma pessoa!");

        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrou pessoa com id = " + id));
    }
    
    public Pessoa create(Pessoa pessoa) {
        
        logger.info("Crindo uma pessoa!");
        
        return pessoaRepository.save(pessoa);
    }
    
    public Pessoa update(long id, Pessoa pessoa) {
        
        logger.info("Atualiza uma Pessoa");
        
        var _pessoa = pessoaRepository.findById(id).orElseThrow();
        
        _pessoa.setNome(pessoa.getNome());
        _pessoa.setDataNascimento(pessoa.getDataNascimento());
        
        return pessoaRepository.save(_pessoa);
    }
    
    public void delete(long id) {
        
        logger.info("Deletando uma pessoa");
        
        var encontraPessoa = pessoaRepository.findById(id).orElseThrow();
        
        pessoaRepository.delete(encontraPessoa);
    }
}
