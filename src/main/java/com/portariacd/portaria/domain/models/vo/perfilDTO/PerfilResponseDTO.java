package com.portariacd.portaria.domain.models.vo.perfilDTO;

import com.portariacd.portaria.domain.models.auth.Perfil;
import com.portariacd.portaria.infrastructure.persistence.PerfilEntity;

public record PerfilResponseDTO(Long id, String nome) {
    public PerfilResponseDTO(PerfilEntity e) {
        this(e.getId(),e.getNome());
    }

    public PerfilResponseDTO(Perfil perfil) {
        this(perfil.getId(),perfil.getNome());
    }
}
