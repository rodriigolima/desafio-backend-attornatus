package br.com.desafio.attornatus.service;

import br.com.desafio.attornatus.model.Person;
import br.com.desafio.attornatus.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    @Autowired
    PersonDAO personDAO;
    
    public List<Person> findAll() {
        
        logger.info("Finding all people!");
        
        return personDAO.findAll();
    }
    
    public Person findById(Long id) {
        
        logger.info("Finding one person!");

        return personDAO.findById(id).orElseThrow();
    }
    
    public Person create(Person person) {
        
        logger.info("Creating one person!");
        
        return personDAO.save(person);
    }
    
    public Person update(Person person) {
        
        logger.info("Update one person");
        
        var updatePerson = personDAO.findById(person.getPersonId()).orElseThrow();
        
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setBirthDay(person.getBirthDay());
        
        return personDAO.save(updatePerson);
    }
    
    public void delete(Long id) {
        
        logger.info("Deleting one person");
        
        var findPerson = personDAO.findById(id).orElseThrow();
        
        personDAO.delete(findPerson);
    }
}
