package com.portariacd.portaria.domain.models.vo.usuarioVO;

import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public record UsuarioRequestDTO(
        long id,
         String nome,
         String email,
         String ocupacaoOperacional,
         Integer filial
) {
    public UsuarioRequestDTO(UsuarioEntity usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial());
    }

    public UsuarioRequestDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getOcupacaoOperacional()
                ,usuario.getFilial());
    }


}
