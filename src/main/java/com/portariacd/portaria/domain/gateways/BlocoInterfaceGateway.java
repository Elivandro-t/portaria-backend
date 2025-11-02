package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.bloco.RegistroBlocoDTO;
import com.portariacd.portaria.domain.models.vo.bloco.RequestBlocoDTO;

import java.util.List;
import java.util.Set;

public interface BlocoInterfaceGateway {
     void registroBloco(RegistroBlocoDTO registro);
    public List<RequestBlocoDTO> lista();

    }
