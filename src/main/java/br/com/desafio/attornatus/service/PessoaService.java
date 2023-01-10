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
    
    public Pessoa findById(Long id) {
        
        logger.info("Encontrando uma pessoa!");

        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrou pessoa com id = " + id));
    }
    
    public Pessoa create(Pessoa pessoa) {
        
        logger.info("Crindo uma pessoa!");
        
        return pessoaRepository.save(pessoa);
    }
    
    public Pessoa update(Pessoa pessoa) {
        
        logger.info("Atualiza uma Pessoa");
        
        var atualizaPessoa = pessoaRepository.findById(pessoa.getId()).orElseThrow();
        
        atualizaPessoa.setNome(pessoa.getNome());
        atualizaPessoa.setDataNascimento(pessoa.getDataNascimento());
        
        return pessoaRepository.save(atualizaPessoa);
    }
    
    public void delete(Long id) {
        
        logger.info("Deletando uma pessoa");
        
        var encontraPessoa = pessoaRepository.findById(id).orElseThrow();
        
        pessoaRepository.delete(encontraPessoa);
    }
}
