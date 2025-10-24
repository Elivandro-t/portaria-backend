package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.historyDTO.HistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryGatewayReposity {
    Page<HistoryDTO> historico(Pageable page,String busca);
    void deleteHistory(Long id,Long usuarioId);
}
