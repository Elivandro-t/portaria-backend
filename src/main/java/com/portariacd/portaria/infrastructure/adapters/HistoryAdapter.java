package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.application.services.LogAcaoService;
import com.portariacd.portaria.domain.gateways.HistoryGatewayReposity;
import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.domain.models.vo.historyDTO.HistoryDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.HistoryRepository;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import com.portariacd.portaria.infrastructure.persistence.history.HistoryEntradaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HistoryAdapter implements HistoryGatewayReposity {
    private final HistoryRepository repository;
    private UsuarioRepository usuarioRepository;
    private final LogAcaoService service;
    public HistoryAdapter(HistoryRepository repository,LogAcaoService service,UsuarioRepository usuarioRepository){
        this.repository = repository;
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    @Transactional
    public Page<HistoryDTO> historico(Pageable page, String busca) {
        Page<HistoryDTO>  pageLista;
        if(busca!=null && !busca.isEmpty()){
            pageLista =  repository.findbyBusca(page,busca).map(HistoryDTO::new);
        }else {
            pageLista = repository.findAll(page).map(e->new HistoryDTO(e));
        }
        return pageLista;
    }

    @Override
    public void deleteHistory(Long id,Long usuarioId) {
        var usuario =  usuarioRepository.findById(usuarioId);
        if(usuario.isPresent()){
            var usuarioCadastrado = new UsuarioRequestDTO(usuario.get());

          var historico =  repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Id não encontrado")
            );

            if(historico.getStatus()!=null
                    && historico.getStatus().equals(StatusPortaria.SAIDA_LIBERADA)) {
                repository.delete(historico);
                service.registrarLog(
                        usuarioCadastrado,
                        "DELETE_HISTORY",
                        String.format(
                                "Usuário %s deletou histórico ID %d com status %s",
                                usuarioCadastrado.nome(),
                                historico.getId(),
                                historico.getStatus()
                        )
                );
            }
            else {
                throw new RuntimeException("Erro ao deletar histórico, histórico incompleto");
            }        }
    }
}
