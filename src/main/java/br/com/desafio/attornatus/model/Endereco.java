package br.com.desafio.attornatus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
@JsonPropertyOrder({ "id", "logradouro", "cep", "numero", "cidade", "principal" })
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 180)
    private String logradouro;
    
    @Column(length = 80)
    private String cep;
    
    @Column(nullable = false, length = 6)
    private int numero;
    
    @Column(length = 80)
    private String cidade;
    
    private boolean principal;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.ALL
            },
            mappedBy = "enderecos")
    @JsonIgnore
    private Set<Pessoa> pessoas = new HashSet<>();

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    public void addPessoa(Pessoa pessoa){
        this.pessoas.add(pessoa);
        pessoa.getEnderecos().remove(this);
    }
}
