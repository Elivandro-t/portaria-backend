package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.HistoryService;
import com.portariacd.portaria.domain.models.vo.historyDTO.HistoryDTO;
import com.portariacd.portaria.infrastructure.adapters.HistoryAdapter;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("historico")
public class HistoryControler {
    private final HistoryService service;
    public HistoryControler(HistoryService service){
        this.service = service;
    }
   @GetMapping("/lista")
    public ResponseEntity<Page<HistoryDTO>> historico(Pageable page, @RequestParam(value = "busca",required = false) String busca){
        var lista = service.historico(page,busca);
        return ResponseEntity.ok(lista);
    }
    @PutMapping("/delete")
    @Transactional
    public ResponseEntity<String> deletar(@RequestParam("id") Long id,@RequestParam("usuario_id") Long usuarioId){
        service.delete(id,usuarioId);
        return ResponseEntity.noContent().build();
    }
}
