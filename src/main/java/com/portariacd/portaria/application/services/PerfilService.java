package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.PerfilGatewayRepository;
import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class PerfilService {
    private final PerfilGatewayRepository repository;
    public  PerfilService(PerfilGatewayRepository repository){
        this.repository = repository;
    }
    public Map<String, List<PerfilResponseDTO>> listaPerfils() {
        return repository.listaPerfils();
    }
}
