package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.PerfilService;
import com.portariacd.portaria.domain.gateways.PerfilGatewayRepository;
import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("perfil")
public class PerfilControler {
    private final PerfilService service;
    public PerfilControler(PerfilService service){
        this.service = service;
    }
    @GetMapping("/lista")
    public ResponseEntity<Map<String, List<PerfilResponseDTO>>> listaPerfils() {
        var response = service.listaPerfils();
        return ResponseEntity.ok(response);
    }
}
