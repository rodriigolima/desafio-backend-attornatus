package br.com.desafio.attornatus.model;

public enum Principal {

    PRINCIPAL("principal"), NAO_PRINCIPAL("não principal");
    
    private final String descricao;
    
    Principal(String descricao) {
        this.descricao = descricao;
        
    }
    
    public String getDescricao() {
        return descricao;
    }
}

