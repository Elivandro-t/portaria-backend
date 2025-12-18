package com.portariacd.modulos.Moduloportaria.domain.models.vo.usuarioVO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(@NotBlank String email,@NotBlank String password,String userAgent,String ip,String agentenav) {
}
