package com.portariacd.portaria.domain.models.vo.RegistroPortaria;

import com.portariacd.portaria.domain.models.registro_visitante.RegistroVisitantePortaria;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;

import java.time.LocalDateTime;

public record RequestPortariaDTO(
        long id,
        String protocolo,
        String placaVeiculo,
        String nomeCompleto,
        String Obs,
        String tipPessoa,
        String bloco,
        VisitanteDTO visitante,
        UsuarioRequestDTO autorizador,
        LocalDateTime dataCriacao,
        String status,
        String ocupacaoLiberada,
        Boolean ativo,
        EntradaVisitanteDTO entrada,
        SaidaVisitanteDTO saida
) {
    public RequestPortariaDTO(RegistroVisitantePortaria e) {
        this(
                e.getId(),
                e.getProtocolo(),
                e.getPlacaVeiculo(),
                e.getNomeCompleto(),
                e.getDescricao(),
                e.getVisitante().getTipoPessoa(),
                e.getBloco(),
                e.getVisitante()!=null ? new VisitanteDTO(e.getVisitante()):null,
                e.getCriadorId()!=null ? new UsuarioRequestDTO(e.getCriadorId()):null,
                e.getDataCriacao(),
                e.getStatus().name(),
                e.getOcupacaoLiberad(),
                e.getAtivo(),
                e.getEntradaVisitante()!=null ? new EntradaVisitanteDTO(e.getEntradaVisitante()):null,
                e.getSaidaVisitante()!=null ? new SaidaVisitanteDTO(e.getSaidaVisitante()):null

        );
    }

    public RequestPortariaDTO(RegistroVisitantePortariaEntity e) {
        this(
                e.getId(),
                e.getProtocolo(),
                e.getPlacaVeiculo(),
                e.getNomeCompleto(),
                e.getDescricao(),
                e.getVisitante().getTipoPessoa(),
                e.getBloco(),
                e.getVisitante()!=null ? new VisitanteDTO(e.getVisitante()):null,
                e.getCriador()!=null ? new UsuarioRequestDTO(e.getCriador()):null,
                e.getDataCriacao(),
                e.getStatus().name(),
                e.getOcupacaoLiberada(),
                e.getAtivo(),
                e.getEntradaVisitante()!=null ? new EntradaVisitanteDTO(e.getEntradaVisitante()):null,
                e.getSaidaVisitante()!=null ? new SaidaVisitanteDTO(e.getSaidaVisitante()):null


        );
    }
}
