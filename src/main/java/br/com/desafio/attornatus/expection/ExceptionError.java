package br.com.desafio.attornatus.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter@Setter
public class ExceptionError {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Propriedade> campos;
    
    @Getter
    @AllArgsConstructor
    public static class Propriedade {
        
        private String nome;
        private String mensagem;
    }
}

