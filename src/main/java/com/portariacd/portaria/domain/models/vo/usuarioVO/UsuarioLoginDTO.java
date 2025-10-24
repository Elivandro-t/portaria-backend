package com.portariacd.portaria.domain.models.vo.usuarioVO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(@NotBlank String email,@NotBlank String password) {
}
