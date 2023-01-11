package br.com.desafio.attornatus.model;

public enum Principal {

    PRINCIPAL("principal"), NAO_PRINCIPAL("nao principal");
    
    private final String indice;
    
    Principal(String indice) {
        this.indice = indice;
        
    }
    
    public String getIndice() {
        return indice;
    }
}

