package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByNome(String nome);
    List<Pessoa> findByNomeContaining(String nome);
}
