package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findPessoasByEnderecosId(Long enderecosId);

    List<Pessoa> findByNomeContaining(String nome);
}
