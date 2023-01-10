package br.com.desafio.attornatus.controller;

import br.com.desafio.attornatus.model.Address;
import br.com.desafio.attornatus.model.Person;
import br.com.desafio.attornatus.service.AddressService;
import br.com.desafio.attornatus.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private AddressService addressService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personService.findAll();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPersonById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    } 
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody Person person) {
        return personService.create(person);
    }
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Address findAddressById(@PathVariable(value = "id") Long id) { return addressService.findById(id); }
    
    @PostMapping(value = "/address", 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Address createAddress(@RequestBody Address address) { return addressService.create(address); }
}
