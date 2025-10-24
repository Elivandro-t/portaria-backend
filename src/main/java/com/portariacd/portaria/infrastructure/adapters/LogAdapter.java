package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.LogGatewayRepository;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.LogRepository;
import com.portariacd.portaria.infrastructure.persistence.log.LogAcaoEntity;
import org.springframework.stereotype.Component;

@Component
public class LogAdapter implements LogGatewayRepository {
    private final LogRepository repository;
    public LogAdapter(LogRepository repository){
        this.repository = repository;
    }
    @Override
    public void registrarLog(UsuarioRequestDTO usuario, String acao, String descricao) {
        LogAcaoEntity log = new LogAcaoEntity(usuario, acao, descricao);
        repository.save(log);
    }
}
