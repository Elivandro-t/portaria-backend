package com.portariacd.portaria.domain.models.vo.usuarioVO;

import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public record UsuarioRequestDTO(
        long id,
         String nome,
         String email,
         String ocupacaoOperacional,
         Integer filial,
         PerfilResponseDTO perfil
) {
    public UsuarioRequestDTO(UsuarioEntity usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial(),
               usuario.getPerfil()!= null ? new PerfilResponseDTO(usuario.getPerfil()):null);
    }

    public UsuarioRequestDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial(),
                usuario.getPerfil()!= null ? new PerfilResponseDTO(usuario.getPerfil()):null
        );

    }


}
