package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.registro_visitante.LogAcaoDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LogGatewayRepository {
    public void registrarLog(UsuarioRequestDTO usuario, String acao, String descricao);

    Page<LogAcaoDTO> listaAcoes(Pageable page, String busca);
}
