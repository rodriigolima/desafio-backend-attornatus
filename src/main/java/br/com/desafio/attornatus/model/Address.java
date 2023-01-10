package br.com.desafio.attornatus.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "person_address")
public class Address implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    
    private String logradouro;
    
    @Column(length = 80)
    private String cep;
    
    private int number;
    
    @Column(length = 100)
    private String city;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
