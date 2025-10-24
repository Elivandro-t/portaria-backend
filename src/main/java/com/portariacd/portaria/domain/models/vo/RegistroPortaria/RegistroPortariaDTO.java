package com.portariacd.portaria.domain.models.vo.RegistroPortaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroPortariaDTO(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        String numeroTelefone,
        @NotBlank
        String placaVeiculo,
        @NotBlank
        String tipoAcesso,
        Integer filial,
        @NotBlank
        String tipoMotorista,
        @NotBlank
        String tipPessoa,
        @NotNull
        String descricao,
        @NotNull
        Long criadorId,
        @NotBlank
        String ocupacao,
         @NotBlank
         String bloco

) {
}
