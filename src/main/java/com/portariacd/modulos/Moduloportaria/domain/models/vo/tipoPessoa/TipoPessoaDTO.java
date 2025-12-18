package com.portariacd.modulos.Moduloportaria.domain.models.vo.tipoPessoa;

import jakarta.validation.constraints.NotBlank;

public record TipoPessoaDTO(@NotBlank String nome) {
}
