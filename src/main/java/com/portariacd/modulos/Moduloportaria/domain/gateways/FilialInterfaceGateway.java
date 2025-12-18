package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.vo.filialDTO.RegistroFilialDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.filialDTO.RequestFilialDTO;

import java.util.List;

public interface FilialInterfaceGateway {
     void registroFilial(RegistroFilialDTO registro);
    public List<RequestFilialDTO> lista();

    }
