package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findEnderecosByPessoasId(Long pessoaId);
    boolean existsByPrincipal(boolean b);
}
