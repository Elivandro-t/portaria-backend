package com.portariacd.modulos.Moduloportaria.domain.models.dto.RegistroPortaria;

import com.portariacd.modulos.Moduloportaria.domain.models.registro_visitante.SaidaVisitante;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.registroVisitante.SaidaVisitanteEntity;

import java.time.LocalDateTime;

public record SaidaVisitanteDTO(LocalDateTime dataSaida, String nomeFiscal, Long fiscalSaida, String imagem) {


    public SaidaVisitanteDTO(SaidaVisitante e) {
        this(e.getDataSaida(),e.getNomeFiscal(),e.getFicalSaida(),e.getImagem());
    }

    public SaidaVisitanteDTO(SaidaVisitanteEntity e) {
        this(e.getDataSaida(),e.getNomeFiscal(),e.getFicalSaidaId(),e.getImagem());

    }
}
