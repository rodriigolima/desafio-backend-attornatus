package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findEnderecosPeloPessoaId(Long pessoaId);
}
