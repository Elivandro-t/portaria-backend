package com.portariacd.modulos.Moduloportaria.infrastructure.persistence.modulosPerfil;

import jakarta.validation.Valid;

import java.util.List;

public record JsonModule(@Valid List<ModuleDTO> lista) {
}
