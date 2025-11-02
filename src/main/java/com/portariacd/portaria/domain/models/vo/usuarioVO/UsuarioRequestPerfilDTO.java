package com.portariacd.portaria.domain.models.vo.usuarioVO;

import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;

public record UsuarioRequestPerfilDTO(
        long id,
         String nome,
         String email,
         boolean ativo,
         PerfilResponseDTO perfil
) {
    public UsuarioRequestPerfilDTO(UsuarioEntity usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail(),
                usuario.getAtivo()
                ,usuario.getPerfil()!=null?new PerfilResponseDTO(usuario.getPerfil()):null
        );
    }



}
