package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.repository.EnderecoRepository;
import br.com.desafio.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EnderecoService {

    private final Logger logger = Logger.getLogger(PessoaService.class.getName());

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaRepository pessoaRepository;
    
    public List<Endereco> findById(Long id) {

        logger.info("Encontrando um endereco de uma pessoa!");

        if(!pessoaRepository.existsById(id)){
            throw new ResourceNotFoundException("Não encontrei pessoa com esse id = " + id);
        }
        return enderecoRepository.findEnderecosPeloPessoaId(id);
    }

    public Endereco create(Long id, Endereco enderecoReq) {

        logger.info("Criando um endereco!");
        
        return pessoaRepository.findById(id).map(pessoa -> {
            long enderecoId = enderecoReq.getId();
            
            if(enderecoId != 0L) { 
                Endereco _endereco = enderecoRepository.findById(enderecoId)
                        .orElseThrow(() -> new ResourceNotFoundException("Não encontrei endereco com esse id = " + enderecoId));
                pessoa.addEndereco(_endereco);
                pessoaRepository.save(pessoa);
                return _endereco;
            }
            pessoa.addEndereco(enderecoReq);
            return enderecoRepository.save(enderecoReq);
        }).orElseThrow(() -> new ResourceNotFoundException("Não encontrei pessoa com esse id = " + id));
    }
 
}
