package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.LogGatewayRepository;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.LogRepository;
import com.portariacd.portaria.infrastructure.persistence.log.LogAcaoEntity;
import org.springframework.stereotype.Service;

@Service
public class LogAcaoService {
    private final LogGatewayRepository repository;
    public LogAcaoService(LogGatewayRepository repository){
        this.repository = repository;
    }
    public void registrarLog(UsuarioRequestDTO usuario, String acao, String descricao){
       repository.registrarLog(usuario,acao,descricao);

    }
}
