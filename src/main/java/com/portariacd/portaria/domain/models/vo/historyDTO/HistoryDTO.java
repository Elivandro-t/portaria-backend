package com.portariacd.portaria.domain.models.vo.historyDTO;

import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.history.HistoryEntradaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record HistoryDTO(
        long id,
         long registroPortariaId,
         String nomeCompleto,
         VisitanteDTO visitante,
         String placaVeiculo,
         LocalDateTime dataCriacao,
         String descricao,
         StatusPortaria status,
         UsuarioRequestDTO criador,
         Integer filialSolicitado,
         Boolean ativo,

         String protocolo,

         String bloco,

         LocalDateTime dataEntrada,

         LocalDateTime dataSaida,

         Long ficalIdEntrada,

         Long ficalIdSaida,

         String nomeFiscaEntrada,

         String nomeFiscaSaida,

         String imagemEntrada,

         String imagemSaida
) {
    public HistoryDTO(HistoryEntradaEntity e) {
        this(
                e.getId(),
                e.getRegistroPortariaId(),
                e.getNomeCompleto(),
                e.getVisitante()!=null ? new VisitanteDTO((e.getVisitante())):null,
                e.getPlacaVeiculo(),
                e.getDataCriacao(),
                e.getDescricao(),
                e.getStatus(),
                e.getCriador()!=null?new UsuarioRequestDTO(e.getCriador()):null,
                e.getFilialSolicitado(),
                e.getAtivo(),
                e.getProtocolo(),
                e.getBloco(),
                e.getDataEntrada(),
                e.getDataSaida(),
                e.getFicalIdEntrada(),
                e.getFicalIdSaida(),
                e.getNomeFiscaEntrada(),
                e.getNomeFiscaSaida(),
                e.getImagemEntrada(),
                e.getImagemSaida()
        );
    }
}
