package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisitanteGatewaysRepository {
    Page<VisitanteDTO> listaVisitante(Pageable page, String busca);
}
