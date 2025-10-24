package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.VisitanteService;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("visitante")
public class VisitanteControler {
    private final VisitanteService service;
    public VisitanteControler(VisitanteService service){
        this.service = service;

    }
    @GetMapping("/lista")
    public ResponseEntity<Page<VisitanteDTO>> listVisitante(Pageable page,@RequestParam(name = "busca",required = false) String busca){
        var lista = service.listaVisitante(page,busca);
        return ResponseEntity.ok(lista);
    }
}
