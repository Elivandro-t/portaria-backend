package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.VisitanteGatewaysRepository;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
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
}
