package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.LogAcaoService;
import com.portariacd.portaria.domain.models.registro_visitante.LogAcaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogsControler {
    private final LogAcaoService logAcaoService;
    public LogsControler(LogAcaoService logAcaoService){
        this.logAcaoService = logAcaoService;
    }
    @GetMapping("/lista")
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    public ResponseEntity<Page<LogAcaoDTO>> listaAcoes(Pageable page, @RequestParam("busca") String buca){
       var busca = logAcaoService.listaAcoes(page,buca);
        return ResponseEntity.ok(busca);
    }
}
