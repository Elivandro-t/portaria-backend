package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.LogGatewayRepository;
import com.portariacd.portaria.domain.models.registro_visitante.LogAcaoDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.LogRepository;
import com.portariacd.portaria.infrastructure.persistence.log.LogAcaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LogAdapter implements LogGatewayRepository {
    private final LogRepository repository;
    public LogAdapter(LogRepository repository){
        this.repository = repository;
    }
    @Override
    public void registrarLog(UsuarioRequestDTO usuario, String acao, String descricao) {
        LogAcaoEntity log = new LogAcaoEntity(usuario, acao, descricao,usuario.filial());
        repository.save(log);
    }

    @Override
    public Page<LogAcaoDTO> listaAcoes(Pageable page, String busca) {
        return repository.findAll(page).map(LogAcaoDTO::new);
    }
}
