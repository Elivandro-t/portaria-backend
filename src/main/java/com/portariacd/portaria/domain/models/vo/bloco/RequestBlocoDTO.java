package com.portariacd.portaria.domain.models.vo.bloco;

import com.portariacd.portaria.infrastructure.persistence.blocos.BlocoEntity;

public record RequestBlocoDTO(Long id,String nome){
    public RequestBlocoDTO(BlocoEntity e) {
        this(e.getId(),e.getNome());
    }
}
