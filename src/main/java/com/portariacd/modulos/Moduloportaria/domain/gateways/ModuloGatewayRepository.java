package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.vo.SistemaAcessoDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.usuarioVO.SistemaAcessoUsuarioDTO;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.modulosPerfil.ModuleDTO;

import java.util.List;

public interface ModuloGatewayRepository {
    void addPermission(List<ModuleDTO> pemission, Long usuarioId);
    List<SistemaAcessoUsuarioDTO> listaPermission();
}
