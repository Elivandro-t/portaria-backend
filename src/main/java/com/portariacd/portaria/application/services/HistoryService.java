package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.HistoryGatewayReposity;
import com.portariacd.portaria.domain.models.vo.historyDTO.HistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Service
public class HistoryService {
    private final HistoryGatewayReposity reposity;
     public HistoryService(HistoryGatewayReposity reposity){
         this.reposity = reposity;
     }

    public Page<HistoryDTO> historico(Pageable page,String busca){
       return  reposity.historico(page,busca);
    }
    public void delete(Long id, Long usuarioId){
         reposity.deleteHistory(id,usuarioId);
    }

}
