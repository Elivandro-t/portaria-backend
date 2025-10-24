package com.portariacd.portaria.domain.models.vo.RegistroPortaria;

import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;

public record StatusAtualizadoDTO(
        long id,
        String nomeCompleto,
        String placaVeiculo,
        String status,
        String msg
) {
    public StatusAtualizadoDTO(RegistroVisitantePortariaEntity resp, String statusAlterado) {
    this(resp.getId()
            ,resp.getNomeCompleto()
            ,resp.getPlacaVeiculo(),
            resp.getStatus().name(),
            statusAlterado
    );
    }
}
