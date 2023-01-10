package br.com.desafio.attornatus.repository;

import br.com.desafio.attornatus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDAO extends JpaRepository<Address, Long> {}
