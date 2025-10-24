package com.portariacd.portaria.domain.models.registro_visitante;

import com.portariacd.portaria.infrastructure.persistence.registroVisitante.SaidaVisitanteEntity;

import java.time.LocalDateTime;

public class SaidaVisitante {
    private long id;
    private LocalDateTime dataSaida;
    private Long ficalSaida;
    private String nomeFiscal;
    private String imagem;
    public SaidaVisitante(SaidaVisitanteEntity saidaVisitante) {
        this.dataSaida = saidaVisitante.getDataSaida();
        this.nomeFiscal = saidaVisitante.getNomeFiscal();
        this.ficalSaida = saidaVisitante.getFicalSaidaId();
        this.imagem = saidaVisitante.getImagem();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomeFiscal() {
        return nomeFiscal;
    }

    public void setNomeFiscal(String nomeFiscal) {
        this.nomeFiscal = nomeFiscal;
    }


    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Long getFicalSaida() {
        return ficalSaida;
    }

    public void setFicalSaida(Long ficalSaida) {
        this.ficalSaida = ficalSaida;
    }
}
