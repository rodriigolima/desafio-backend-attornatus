package br.com.desafio.attornatus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "pessoas")
public class Pessoa  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 180)
    @NotBlank
    @Size(max=180)
    private String nome;
    
    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dataNascimento;
    
    @ManyToMany(fetch = FetchType.EAGER,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE  })
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    @OrderBy("id ASC")
    private Set<Endereco> enderecos = new HashSet<>();

    
    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.getPessoas().remove(this);
    }
    
}
