package com.portariacd.modulos.Moduloportaria.domain.models.vo.RegistroPortaria;

import com.portariacd.modulos.Moduloportaria.domain.models.registro_visitante.EntradaVisitante;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.registroVisitante.EntradaVisitanteEntity;

import java.time.LocalDateTime;

public record EntradaVisitanteDTO(LocalDateTime dataEntrada,String nomeFiscal,Long fiscalEntradaId,String imagem) {


    public EntradaVisitanteDTO(EntradaVisitante e) {
        this(e.getDataEntrada(),e.getNomeFiscal(),e.getFiscalEntradaId(),e.getImagem());
    }

    public EntradaVisitanteDTO(EntradaVisitanteEntity e) {
        this(e.getDataEntrada(),e.getNomeFiscal(),e.getFiscalEntradaId(),e.getImagem());

    }
}
