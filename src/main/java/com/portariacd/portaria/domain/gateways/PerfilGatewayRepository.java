package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.perfilDTO.PerfilResponseDTO;

import java.util.List;
import java.util.Map;

public interface PerfilGatewayRepository {
    public Map<String,List<PerfilResponseDTO>> listaPerfils();
}
