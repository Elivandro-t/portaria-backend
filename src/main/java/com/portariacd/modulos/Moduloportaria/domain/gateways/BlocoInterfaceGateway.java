package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.vo.bloco.RegistroBlocoDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.bloco.RequestBlocoDTO;

import java.util.List;

public interface BlocoInterfaceGateway {
     void registroBloco(RegistroBlocoDTO registro);
    public List<RequestBlocoDTO> lista();

    }
