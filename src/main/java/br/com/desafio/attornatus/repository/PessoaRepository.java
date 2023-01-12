package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNomeContaining(String nome);
}
