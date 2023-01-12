package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.repository.EnderecoRepository;
import br.com.desafio.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@ComponentScan("br.com.desafio.attornatus")
public class EnderecoService {

    private final Logger logger = Logger.getLogger(PessoaService.class.getName());

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaRepository pessoaRepository;
    
    public List<Endereco> findById(long id) {

        logger.info("Encontrando um endereco de uma pessoa!");

        if(!pessoaRepository.existsById(id)){
            throw new ResourceNotFoundException("Não encontrei pessoa com esse id = " + id);
        }
        return enderecoRepository.findEnderecosByPessoasId(id);
    }

    public Endereco create(Long pessoaId, Endereco enderecoRequest) {

        logger.info("Criando um endereco!");
        
        return pessoaRepository.findById(pessoaId).map(pessoa -> {
            Long enderecoId = enderecoRequest.getId();
            
            if(enderecoId != null && enderecoId != 0L) { 
                Endereco _endereco = enderecoRepository.findById(enderecoId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Não encontrei endereco com esse id = " + enderecoId));
                pessoa.addEndereco(_endereco);
                pessoaRepository.save(pessoa);
                return _endereco;
            }
            pessoa.addEndereco(enderecoRequest);
            return enderecoRepository.save(enderecoRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Não encontrei pessoa com esse id = " + pessoaId));
    }
 
}
