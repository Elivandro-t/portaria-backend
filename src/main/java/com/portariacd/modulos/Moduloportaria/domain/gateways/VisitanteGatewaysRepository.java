package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.visitante.StatusTypeDeleteVisitante;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.VisitanteDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.VisitanteFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisitanteGatewaysRepository {
    Page<VisitanteDTO> listaVisitante(Pageable page, String busca);
    String deleteRegistro(Long visitanteid, Long usuarioId, StatusTypeDeleteVisitante T);
    VisitanteDTO buscaVisitante(Long visitanteId,String nome);
    Page<VisitanteFiltroDTO> listaVisitanteFiltro(Pageable page);
}
