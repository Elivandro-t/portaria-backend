package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;

public interface LogGatewayRepository {
    public void registrarLog(UsuarioRequestDTO usuario, String acao, String descricao);
}
