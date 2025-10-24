package com.portariacd.portaria.domain.models.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDto(
        @NotBlank
         String nome,
         String email,
        @NotBlank
         String password,
        @NotBlank
         String ocupacaoOperacional,
         @NotNull(message = "Filial não pode ser nulo")
         int filial,
         @NotNull
         Long perfilId
) {
}
