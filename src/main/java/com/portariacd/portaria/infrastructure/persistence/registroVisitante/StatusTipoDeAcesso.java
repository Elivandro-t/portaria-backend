package com.portariacd.portaria.infrastructure.persistence.registroVisitante;

public enum StatusTipoDeAcesso {
    RECORRENTE("recorrente"),
    UNICO("unico");
    private final String nome;

    StatusTipoDeAcesso(String nome) {
        this.nome = nome;
    }
    public static StatusTipoDeAcesso StatusAdd(String nome){
      for (StatusTipoDeAcesso tipo :values()){
          if(tipo.nome.equals(nome)){
              return tipo;
          }
      }
        throw new IllegalArgumentException("Tipo de acesso inválido: " + nome);

    }
}
