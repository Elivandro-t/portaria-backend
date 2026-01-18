package com.portariacd.modulos.Moduloportaria.domain.models.dto.filialDTO;

import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.filial.Filial;

public record RequestFilialDTO(Long id, String nome,Integer numeroFilial){
    public RequestFilialDTO(Filial e) {
        this(e.getId(),e.getNome(),e.getNumeroFilial());
    }
}