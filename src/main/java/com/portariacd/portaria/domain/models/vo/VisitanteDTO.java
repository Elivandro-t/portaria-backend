package com.portariacd.portaria.domain.models.vo;

import com.portariacd.portaria.domain.models.Visitante;
import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.StatusTipoDeAcesso;

import java.time.LocalDateTime;

public record VisitanteDTO(
         Long id,
         String nomeCompleto,
         String imagem,
         String ocupacao,
        //opcional
         Integer filial,
         LocalDateTime dataCriacao,
         StatusTipoDeAcesso tipoAcesso,
         String tipoMotorista,
          String tipoPessoa
) {
    public VisitanteDTO(Visitante v) {
        this(v.getId(),
                v.getNomeCompleto(),
                v.getImagem(),
                v.getOcupacao(),
                v.getFilial(),
                v.getDataCriacao(),
                v.getTipoAcesso(),
                v.getTipoMotorista(),
                v.getTipoPessoa()
                );
    }

    public VisitanteDTO(VisitanteEntity v) {
        this(v.getId(),
                v.getNomeCompleto(),
                v.getImagem(),
                v.getOcupacao(),
                v.getFilial(),
                v.getDataCriacao(),
                v.getTipoAcesso(),
                v.getTipoMotorista(),
                v.getTipoPessoa()
        );
    }
}
