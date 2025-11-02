package com.portariacd.portaria.domain.models.vo.RegistroPortaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaRegistro(
        @NotNull
        long id,
        @NotBlank
        String nomeCompleto,
        @NotBlank
        String numeroTelefone,
        @NotBlank
        String placaVeiculo,
        @NotBlank
        String bloco
) {
}

