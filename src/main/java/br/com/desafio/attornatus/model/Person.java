package br.com.desafio.attornatus.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    
    private static final long serialVersionID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    
    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;
    
    @Column(name = "birth_day")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDay;
    
    @ManyToMany
    @JoinTable(
            name = "person_address",
            joinColumns = @JoinColumn(name = "personId"),
            inverseJoinColumns = @JoinColumn(name = "addressId")
    )
    private List<Address> addresses;

    public Person() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
