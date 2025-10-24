package com.portariacd.portaria.domain.models.vo.usuarioVO;

import com.portariacd.portaria.domain.models.auth.Usuario;

public record ResponseUsuarioDTO(
         String nome,
         String email,
         String ocupacaoOperacional,
         Integer filial
) {
    public ResponseUsuarioDTO(Usuario e) {
        this(e.getNome(),e.getEmail(),e.getOcupacaoOperacional(), e.getFilial());
    }


}
