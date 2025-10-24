package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.VisitanteGatewaysRepository;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import com.portariacd.portaria.infrastructure.persistence.VisitanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class VisitanteAdapter implements VisitanteGatewaysRepository {
    private final VisitanteRepository repository;
    public VisitanteAdapter(VisitanteRepository visitanteRepository){
        this.repository = visitanteRepository;
    }
    @Override
    public Page<VisitanteDTO> listaVisitante(Pageable page, String busca) {
        Page<VisitanteDTO>  pageLista;
        if(busca!=null && !busca.isEmpty()){
            pageLista =  repository.findbyBusca(page,busca).map(VisitanteDTO::new);
        }else {
            pageLista = repository.findAll(page).map(VisitanteDTO::new);
        }
        return pageLista;
    }
}
