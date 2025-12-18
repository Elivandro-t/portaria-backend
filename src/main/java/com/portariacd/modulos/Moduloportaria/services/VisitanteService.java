package com.portariacd.modulos.Moduloportaria.services;

import com.portariacd.modulos.Moduloportaria.domain.gateways.VisitanteGatewaysRepository;
import com.portariacd.modulos.Moduloportaria.domain.models.visitante.StatusTypeDeleteVisitante;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.VisitanteDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.VisitanteFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VisitanteService  {
    private final VisitanteGatewaysRepository repository;
    public VisitanteService(VisitanteGatewaysRepository repository){
        this.repository = repository;
    }
    public Page<VisitanteDTO> listaVisitante(Pageable page, String busca) {
        return repository.listaVisitante(page,busca);
    }

    public String deletarVisitenate(Long visitanteid, Long usuarioId, StatusTypeDeleteVisitante t) {
       return   repository.deleteRegistro(visitanteid,usuarioId,t);
    }
    public VisitanteDTO buscaVisitante(Long visitanteId,String nome) {
        return repository.buscaVisitante(visitanteId,nome);
    }
    public Page<VisitanteFiltroDTO> listaVisitanteFiltro(Pageable page) {
        return repository.listaVisitanteFiltro(page);
    }

}
