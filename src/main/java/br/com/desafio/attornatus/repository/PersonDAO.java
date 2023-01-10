package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDAO extends JpaRepository<Person, Long> {
    List<Person> findPersonsByAddressesId(Long addressId);
}
