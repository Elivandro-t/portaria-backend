package com.portariacd.modulos.Moduloportaria.domain.models.vo.usuarioVO;

import com.portariacd.modulos.Moduloportaria.domain.models.auth.Usuario;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.SistemaAcessoDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.UsuarioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioRequestDTO(
        long id,
         String nome,
         String avatar,
         String email,
         String ocupacaoOperacional,
         Integer filial,
         PerfilResponseDTO perfil,
         Boolean ativo,
         String sessionDevice,// desktop, mobile, tablet...
         LocalDateTime sessionLastLogin,
        List<SistemaAcessoUsuarioDTO> acess
) {
    public UsuarioRequestDTO(UsuarioEntity usuario) {
        this(usuario.getId(),usuario.getNome(),
                usuario.getAvatar()!=null? usuario.getAvatar() : null
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial(),
               usuario.getPerfil()!= null ? new PerfilResponseDTO(usuario.getPerfil()):null,
                usuario.getAtivo(),
                usuario.getSessionDevice(),
                usuario.getSessionLastLogin(),
                usuario.getModulos()!=null?usuario.getModulos().stream().map(e->new SistemaAcessoUsuarioDTO(e.getModulo())).toList():null
        );
    }

    public UsuarioRequestDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome(),
                usuario.getAvatar()!=null? usuario.getAvatar() : null
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial(),
                usuario.getPerfil()!= null ? new PerfilResponseDTO(usuario.getPerfil()):null,
                usuario.getAtivo(),
                usuario.getSessionDevice(),
                usuario.getSessionLastLogin(),
                usuario.getAcess()
        );

    }


}
