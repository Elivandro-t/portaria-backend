package com.portariacd.portaria.domain.models.vo.RegistroPortaria;

import com.portariacd.portaria.domain.models.registro_visitante.SaidaVisitante;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.SaidaVisitanteEntity;

import java.time.LocalDateTime;

public record SaidaVisitanteDTO(LocalDateTime dataSaida, String nomeFiscal, Long fiscalSaida, String imagem) {


    public SaidaVisitanteDTO(SaidaVisitante e) {
        this(e.getDataSaida(),e.getNomeFiscal(),e.getFicalSaida(),e.getImagem());
    }

    public SaidaVisitanteDTO(SaidaVisitanteEntity e) {
        this(e.getDataSaida(),e.getNomeFiscal(),e.getFicalSaidaId(),e.getImagem());

    }
}
