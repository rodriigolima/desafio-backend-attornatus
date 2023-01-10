package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Address;
import br.com.desafio.attornatus.model.Person;
import br.com.desafio.attornatus.repository.AddressDAO;
import br.com.desafio.attornatus.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AddressService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    AddressDAO addressDAO;

    
    public Address findById(Long id) {

        logger.info("Finding one address person!");

        return addressDAO.findById(id).orElseThrow();
    }

    public Address create(Address address) {

        logger.info("Creating one address!");

        return addressDAO.save(address);
    }

    

   
}