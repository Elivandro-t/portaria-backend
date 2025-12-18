package com.portariacd.modulos.Moduloportaria.domain.models.vo.filialDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroFilialDTO(@NotBlank String nome, @NotNull Integer numeroFilial){}
