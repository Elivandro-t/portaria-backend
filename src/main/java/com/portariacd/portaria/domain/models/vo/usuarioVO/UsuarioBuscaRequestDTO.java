package com.portariacd.portaria.domain.models.vo.usuarioVO;

import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;

public record UsuarioBuscaRequestDTO(
        long id,
         String nome,
         String email,
         String ocupacaoOperacional,
         Integer filial,
        String avatar
) {
    public UsuarioBuscaRequestDTO(UsuarioEntity usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial(),
                usuario.getAvatar()
        );
    }


}
